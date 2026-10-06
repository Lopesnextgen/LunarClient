package com.moonsworth.webosr.wrappers;

import com.moonsworth.webosr.NativeHandle;

public final class Session extends NativeHandle {
   private Session(long value) {
      super(value);
   }

   public native boolean isPersistent();

   public native String name();

   @Override
   protected native void destroy();
}
