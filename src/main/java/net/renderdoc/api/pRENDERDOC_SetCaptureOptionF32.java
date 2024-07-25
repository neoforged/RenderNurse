// Generated by jextract

package net.renderdoc.api;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * int (*pRENDERDOC_SetCaptureOptionF32)(enum RENDERDOC_CaptureOption opt,float val);
 * }
 */
public interface pRENDERDOC_SetCaptureOptionF32 {

    int apply(int opt, float val);
    static MemorySegment allocate(pRENDERDOC_SetCaptureOptionF32 fi, Arena scope) {
        return RuntimeHelper.upcallStub(constants$0.const$5, fi, constants$0.const$4, scope);
    }
    static pRENDERDOC_SetCaptureOptionF32 ofAddress(MemorySegment addr, Arena arena) {
        MemorySegment symbol = addr.reinterpret(arena, null);
        return (int _opt, float _val) -> {
            try {
                return (int)constants$1.const$0.invokeExact(symbol, _opt, _val);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}

