package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.google.common.hash.Hasher;
import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;

@CanIgnoreReturnValue
abstract class AbstractHasher implements Hasher {
   AbstractHasher() {
   }

   public final Hasher method11(boolean flag1) {
      return this.method12((byte)(flag1 ? 1 : 0));
   }

   public final Hasher method10(double value1) {
      return this.method8(Double.doubleToRawLongBits(value1));
   }

   public final Hasher method9(float value1) {
      return this.method7(Float.floatToRawIntBits(value1));
   }

   public Hasher method13(CharSequence text1) {
      int index2 = 0;

      for (int index3 = text1.length(); index2 < index3; index2++) {
         this.method12(text1.charAt(index2));
      }

      return this;
   }

   public Hasher method14(CharSequence text1, Charset charset2) {
      return this.method3(text1.toString().getBytes(charset2));
   }

   public Hasher method3(byte[] items1) {
      return this.method4(items1, 0, items1.length);
   }

   public Hasher method4(byte[] items1, int index2, int number3) {
      Preconditions.checkPositionIndexes(index2, index2 + number3, items1.length);

      for (int index4 = 0; index4 < number3; index4++) {
         this.method12(items1[index2 + index4]);
      }

      return this;
   }

   public Hasher method5(ByteBuffer buffer1) {
      if (buffer1.hasArray()) {
         this.method4(buffer1.array(), buffer1.arrayOffset() + buffer1.position(), buffer1.remaining());
         ((Buffer)buffer1).position(buffer1.limit());
      } else {
         for (int index2 = buffer1.remaining(); index2 > 0; index2--) {
            this.method12(buffer1.get());
         }
      }

      return this;
   }

   public Hasher method6(short number1) {
      this.method12((byte)number1);
      this.method12((byte)(number1 >>> 8));
      return this;
   }

   public Hasher method7(int number1) {
      this.method12((byte)number1);
      this.method12((byte)(number1 >>> 8));
      this.method12((byte)(number1 >>> 16));
      this.method12((byte)(number1 >>> 24));
      return this;
   }

   public Hasher method8(long number1) {
      for (byte index3 = 0; index3 < 64; index3 += 8) {
         this.method12((byte)(number1 >>> index3));
      }

      return this;
   }

   public Hasher method12(char character1) {
      this.method12((byte)character1);
      this.method12((byte)(character1 >>> '\b'));
      return this;
   }

   public <T> Hasher method14(T value1, Funnel<? super T> serializableextension2) {
      serializableextension2.funnel(value1, this);
      return this;
   }
}
