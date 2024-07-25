package net.neoforged.doctor;

import net.renderdoc.api.RENDERDOC_API_1_6_0;
import net.renderdoc.api.pRENDERDOC_GetAPI;
import net.renderdoc.api.renderdoc_app_h;

import java.lang.foreign.*;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;

public class RenderDocLaunchAgent {

    public static void load() {
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
        final var librarySystemProp = System.getProperty("neoforge.doctor.renderdoc.library", "<NOT_SET>");
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

    @SuppressWarnings("CallToPrintStackTrace")
    private static void startRenderDoc() {
        try(Arena arena = Arena.ofConfined()) {
            MemorySegment pointerToApi = arena.allocate(ValueLayout.ADDRESS);

            SymbolLookup lookup = SymbolLookup.loaderLookup();
            MemorySegment apiRetrievalFunctionAddress = lookup.find("RENDERDOC_GetAPI").get();

            final pRENDERDOC_GetAPI getApiFunction = pRENDERDOC_GetAPI.ofAddress(apiRetrievalFunctionAddress, arena);

            //Fill the API pointer
            final int result = getApiFunction.apply(renderdoc_app_h.eRENDERDOC_API_Version_1_6_0(), pointerToApi);
            if (result != 1) {
                System.err.printf("Failed to load the 1.6 version of RenderDoc. Please update RenderDoc.%n");
                System.exit(150);
            }

            final MemorySegment api = RENDERDOC_API_1_6_0.ofAddress(pointerToApi.get(ValueLayout.ADDRESS, 0), arena);
            final int replayUiPid = RENDERDOC_API_1_6_0.LaunchReplayUI(api, arena).apply(1, MemorySegment.NULL);

            if (replayUiPid == 0) {
                System.err.printf("Failed to start RenderDoc replay UI.%n");
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
