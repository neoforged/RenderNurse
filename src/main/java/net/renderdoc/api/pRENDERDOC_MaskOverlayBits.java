// Generated by jextract

package net.renderdoc.api;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * void (*pRENDERDOC_MaskOverlayBits)(unsigned int And,unsigned int Or);
 * }
 */
public interface pRENDERDOC_MaskOverlayBits {

    void apply(int And, int Or);
    static MemorySegment allocate(pRENDERDOC_MaskOverlayBits fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$3.const$3, fi, constants$3.const$2, scope);
    }
    static pRENDERDOC_MaskOverlayBits ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (int _And, int _Or) -> {
            try {
                constants$3.const$4.invokeExact(symbol, _And, _Or);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}

