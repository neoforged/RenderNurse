// Generated by jextract

package net.renderdoc.api;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$6 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$6() {}
    static final MethodHandle const$0 = RuntimeHelper.upcallHandle(pRENDERDOC_GetNumCaptures.class, "apply", constants$2.const$5);
    static final FunctionDescriptor const$1 = FunctionDescriptor.of(JAVA_INT,
        JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$2 = RuntimeHelper.upcallHandle(pRENDERDOC_GetCapture.class, "apply", constants$6.const$1);
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        constants$6.const$1
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$5 = RuntimeHelper.upcallHandle(pRENDERDOC_SetCaptureFileComments.class, "apply", constants$6.const$4);
}


