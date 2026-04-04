/*
 * Copyright (c) NeoForge Development and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.doctor;

import net.renderdoc.api.RENDERDOC_API_1_6_0;
import net.renderdoc.api.pRENDERDOC_GetAPI;
import net.renderdoc.api.pRENDERDOC_LaunchReplayUI;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

import static net.renderdoc.api.renderdoc_app_h.eRENDERDOC_API_Version_1_6_0;

public class RenderDocLaunchAgent {

    private static AtomicBoolean hasLoaded = new AtomicBoolean();

    public static void load() {
        //Avoid loading multiple times if we were specified multiple times by accident
        if (hasLoaded.compareAndExchange(false, true)) {
            return;
        }

        System.out.printf("Loading RenderDoc...%n");

        //Check if we are on mac:
        if (OSUtils.isMac()) {
            System.err.printf("RenderDoc is not supported on MacOS. Use Linux or Windows.%n");
            System.exit(120);
        }

        loadRenderDocLibrary();

        if (OSUtils.isLinux()) {
            if (!System.getenv().containsKey("LD_PRELOAD")) {
                System.err.printf("Please pre-load the RenderDoc API.%n");
                System.exit(140);
            }
        }

        startRenderDoc();
    }

    @SuppressWarnings("CallToPrintStackTrace") //No logger available!
    private static void loadRenderDocLibrary() {
        final var librarySystemProp = System.getProperty("neoforge.rendernurse.renderdoc.library", "<NOT_SET>");
        final var libraryPath = Path.of(librarySystemProp).toAbsolutePath();

        if (!Files.exists(libraryPath)) {
            System.err.printf("The RenderDoc library: %s does not exist.%n", libraryPath);
            System.exit(100);
        }

        if (!Files.isRegularFile(libraryPath)) {
            System.err.printf("The RenderDoc library: %s is not a library file.%n", libraryPath);
            System.exit(101);
        }

        try {
            System.load(librarySystemProp);
        } catch (Throwable throwable) {
            System.err.println("Failed to pre-load the RenderDoc library!");
            System.err.println(throwable.getMessage());
            throwable.printStackTrace();
            System.exit(102);
        }

        System.out.printf("Preloaded the RenderDoc library from: %s%n", libraryPath);
    }

    private static void startRenderDoc() {
        try(Arena arena = Arena.ofConfined()) {
            SymbolLookup lookup = SymbolLookup.loaderLookup();

            // Get the API pointer
            MemorySegment GetAPI_addr = lookup.findOrThrow("RENDERDOC_GetAPI");
            MemorySegment api_1_6_0_addr = arena.allocate(ValueLayout.ADDRESS);
            final int result = pRENDERDOC_GetAPI.invoke(GetAPI_addr, eRENDERDOC_API_Version_1_6_0(), api_1_6_0_addr);
            if (result != 1) {
                System.err.println("Failed to load the 1.6 version of RenderDoc. Please update RenderDoc.");
                System.exit(150);
            }

            // Launch the replay UI
            MemorySegment LaunchReplayUI_addr = RENDERDOC_API_1_6_0.LaunchReplayUI(api_1_6_0_addr);
            final int replayUiPid = pRENDERDOC_LaunchReplayUI.invoke(LaunchReplayUI_addr, 1, MemorySegment.NULL);
            if (replayUiPid == 0) {
                System.err.println("Failed to start RenderDoc replay UI.");
                System.exit(151);
            }
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        load();
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        load();
    }

    public static class OSUtils {
        public static boolean isMac() {
            String osName = System.getProperty("os.name").toLowerCase();
            return osName.contains("mac");
        }

        public static boolean isLinux() {
            String osName = System.getProperty("os.name").toLowerCase();
            return osName.contains("linux");
        }
    }
}
