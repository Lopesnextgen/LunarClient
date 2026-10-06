package com.moonsworth.webosr.wrappers.image;

import com.moonsworth.webosr.NativeHandle;

public final class ChildImageSource extends NativeHandle implements ImageSource {
   private ChildImageSource(long number1) {
      super(number1);
   }

   @Override
   public native void bind();

   @Override
   public native void unbind();

   @Override
   public native void uv(int number1, int number2, int number3, int number4);

   protected native void destroy();
}
