// Generated by jextract

package net.renderdoc.api;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * char* (*pRENDERDOC_GetCaptureFilePathTemplate)();
 * }
 */
public interface pRENDERDOC_GetCaptureFilePathTemplate {

    java.lang.foreign.MemorySegment apply();
    static MemorySegment allocate(pRENDERDOC_GetCaptureFilePathTemplate fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$5.const$2, fi, constants$5.const$1, scope);
    }
    static pRENDERDOC_GetCaptureFilePathTemplate ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return () -> {
            try {
                return (java.lang.foreign.MemorySegment)constants$5.const$3.invokeExact(symbol);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


