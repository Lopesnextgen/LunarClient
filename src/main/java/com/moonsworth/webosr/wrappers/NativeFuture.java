package com.moonsworth.webosr.wrappers;

import com.moonsworth.webosr.NativeHandle;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class NativeFuture extends NativeHandle implements Future<String> {
   private NativeFuture(long number1) {
      super(number1);
   }

   public native boolean isUndefined();

   public String get(long number1, TimeUnit timeunit3) {
      return this.get0(timeunit3.toNanos(number1));
   }

   public String get() {
      return this.get0(0L);
   }

   @Override
   public native boolean isDone();

   @Override
   public boolean isCancelled() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean cancel(boolean flag) {
      throw new UnsupportedOperationException();
   }

   protected native void destroy();

   private native String get0(long number1);
}
