package com.moonsworth.webosr;

import java.nio.ByteBuffer;

public interface TextureHandler {
   int createTexture(int number1, int number2, int number3);

   void resizeTexture(int number1, int number2, int number3, int number4);

   void uploadTexture(int number1, ByteBuffer buffer2, int number3, int number4, int number5, int number6, int number7);

   void readTexture(int number1, ByteBuffer buffer2, int number3, int number4, int number5);

   void deleteTexture(int number1);
}
