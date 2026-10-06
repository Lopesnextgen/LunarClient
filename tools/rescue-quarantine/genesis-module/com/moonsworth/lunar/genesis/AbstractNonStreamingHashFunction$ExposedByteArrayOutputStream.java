package com.moonsworth.lunar.genesis;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

final class AbstractNonStreamingHashFunction$ExposedByteArrayOutputStream extends ByteArrayOutputStream {
   AbstractNonStreamingHashFunction$ExposedByteArrayOutputStream(int number1) {
      super(number1);
   }

   void write(ByteBuffer buffer1) {
      int index2 = buffer1.remaining();
      if (this.count + index2 > this.buf.length) {
         this.buf = Arrays.copyOf(this.buf, this.count + index2);
      }

      buffer1.get(this.buf, this.count, index2);
      this.count += index2;
   }

   byte[] byteArray() {
      return this.buf;
   }

   int length() {
      return this.count;
   }
}
