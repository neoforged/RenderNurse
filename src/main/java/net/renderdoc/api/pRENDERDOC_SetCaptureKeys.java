// Generated by jextract

package net.renderdoc.api;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * void (*pRENDERDOC_SetCaptureKeys)(enum RENDERDOC_InputButton* keys,int num);
 * }
 */
public interface pRENDERDOC_SetCaptureKeys {

    void apply(java.lang.foreign.MemorySegment keys, int num);
    static MemorySegment allocate(pRENDERDOC_SetCaptureKeys fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$2.const$4, fi, constants$2.const$1, scope);
    }
    static pRENDERDOC_SetCaptureKeys ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (java.lang.foreign.MemorySegment _keys, int _num) -> {
            try {
                constants$2.const$3.invokeExact(symbol, _keys, _num);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


