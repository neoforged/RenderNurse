// Generated by jextract

package net.renderdoc.api;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * unsigned int (*pRENDERDOC_GetOverlayBits)();
 * }
 */
public interface pRENDERDOC_GetOverlayBits {

    int apply();
    static MemorySegment allocate(pRENDERDOC_GetOverlayBits fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$3.const$0, fi, constants$2.const$5, scope);
    }
    static pRENDERDOC_GetOverlayBits ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return () -> {
            try {
                return (int)constants$3.const$1.invokeExact(symbol);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


