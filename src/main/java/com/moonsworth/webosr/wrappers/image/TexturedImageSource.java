package com.moonsworth.webosr.wrappers.image;

import com.moonsworth.webosr.NativeHandle;
import java.nio.ByteBuffer;

public final class TexturedImageSource extends NativeHandle implements ImageSource {
   private TexturedImageSource(long number1) {
      super(number1);
   }

   public native void update(ByteBuffer buffer1);

   public native void size(int number1, int number2);

   public native ChildImageSource makeChild(String text1);

   @Override
   public native void uv(int number1, int number2, int number3, int number4);

   @Override
   public native void bind();

   @Override
   public native void unbind();

   protected native void destroy();
}
