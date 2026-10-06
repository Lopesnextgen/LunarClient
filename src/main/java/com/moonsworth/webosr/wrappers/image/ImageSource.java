package com.moonsworth.webosr.wrappers.image;

public interface ImageSource {
   void bind();

   void unbind();

   void uv(int number1, int number2, int number3, int number4);
}
