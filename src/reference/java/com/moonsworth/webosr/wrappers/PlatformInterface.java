package com.moonsworth.webosr.wrappers;

import com.moonsworth.webosr.NativeHandle;
import com.moonsworth.webosr.input.Mouse;
import java.util.function.BiConsumer;

public final class PlatformInterface extends NativeHandle {
   private PlatformInterface(long var1) {
      super(var1);
   }

   public native void setScrollCallback(BiConsumer<Double, Double> var1);

   public void setCursor(Mouse.Cursor var1) {
      this.setCursor0(var1.getValue());
   }

   @Override
   protected native void destroy();

   private native void setCursor0(int var1);
}
