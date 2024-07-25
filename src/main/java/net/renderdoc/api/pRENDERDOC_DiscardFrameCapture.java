// Generated by jextract

package net.renderdoc.api;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * unsigned int (*pRENDERDOC_DiscardFrameCapture)(void* device,void* wndHandle);
 * }
 */
public interface pRENDERDOC_DiscardFrameCapture {

    int apply(java.lang.foreign.MemorySegment device, java.lang.foreign.MemorySegment wndHandle);
    static MemorySegment allocate(pRENDERDOC_DiscardFrameCapture fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$10.const$2, fi, constants$9.const$5, scope);
    }
    static pRENDERDOC_DiscardFrameCapture ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (java.lang.foreign.MemorySegment _device, java.lang.foreign.MemorySegment _wndHandle) -> {
            try {
                return (int)constants$10.const$1.invokeExact(symbol, _device, _wndHandle);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


