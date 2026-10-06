package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashCode;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;

@CanIgnoreReturnValue
final class Murmur3_32HashFunction$Murmur3_32Hasher extends AbstractHasher {
   private int h1;
   private long buffer;
   private int shift;
   private int length;
   private boolean isDone;

   Murmur3_32HashFunction$Murmur3_32Hasher(int number1) {
      this.h1 = number1;
      this.length = 0;
      this.isDone = false;
   }

   private void update(int number1, long number2) {
      this.buffer = this.buffer | (number2 & 4294967295L) << this.shift;
      this.shift += number1 * 8;
      this.length += number1;
      if (this.shift >= 32) {
         this.h1 = MixinHelper5210.access$100(this.h1, MixinHelper5210.access$000((int)this.buffer));
         this.buffer >>>= 32;
         this.shift -= 32;
      }
   }

   public Hasher method2(byte number1) {
      this.update(1, number1 & 0xFF);
      return this;
   }

   public Hasher method4(byte[] items1, int index2, int number3) {
      Preconditions.checkPositionIndexes(index2, index2 + number3, items1.length);

      int index4;
      for (index4 = 0; index4 + 4 <= number3; index4 += 4) {
         this.update(4, MixinHelper5210.access$200(items1, index2 + index4));
      }

      while (index4 < number3) {
         this.method2(items1[index2 + index4]);
         index4++;
      }

      return this;
   }

   public Hasher method5(ByteBuffer buffer1) {
      ByteOrder byteorder2 = buffer1.order();
      buffer1.order(ByteOrder.LITTLE_ENDIAN);

      while (buffer1.remaining() >= 4) {
         this.method7(buffer1.getInt());
      }

      while (buffer1.hasRemaining()) {
         this.method2(buffer1.get());
      }

      buffer1.order(byteorder2);
      return this;
   }

   public Hasher method7(int number1) {
      this.update(4, number1);
      return this;
   }

   public Hasher method8(long number1) {
      this.update(4, (int)number1);
      this.update(4, number1 >>> 32);
      return this;
   }

   public Hasher method12(char character1) {
      this.update(2, character1);
      return this;
   }

   public Hasher method14(CharSequence text1, Charset charset2) {
      if (!Charsets.field3.equals(charset2)) {
         return super.method14(text1, charset2);
      }

      int number3 = text1.length();

      int index4;
      for (index4 = 0; index4 + 4 <= number3; index4 += 4) {
         char character5 = text1.charAt(index4);
         char character6 = text1.charAt(index4 + 1);
         char character7 = text1.charAt(index4 + 2);
         char character8 = text1.charAt(index4 + 3);
         if (character5 >= 128 || character6 >= 128 || character7 >= 128 || character8 >= 128) {
            break;
         }

         this.update(4, character5 | character6 << '\b' | character7 << 16 | character8 << 24);
      }

      for (; index4 < number3; index4++) {
         char character9 = text1.charAt(index4);
         if (character9 < 128) {
            this.update(1, character9);
         } else if (character9 < 2048) {
            this.update(2, MixinHelper5210.access$300(character9));
         } else if (character9 >= '\ud800' && character9 <= '\udfff') {
            int number10 = Character.codePointAt(text1, index4);
            if (number10 == character9) {
               this.method3(text1.subSequence(index4, number3).toString().getBytes(charset2));
               return this;
            }

            index4++;
            this.update(4, MixinHelper5210.access$500(number10));
         } else {
            this.update(3, MixinHelper5210.access$400(character9));
         }
      }

      return this;
   }

   public HashCode method15() {
      Preconditions.checkState(!this.isDone);
      this.isDone = true;
      this.h1 = this.h1 ^ MixinHelper5210.access$000((int)this.buffer);
      return MixinHelper5210.method8(this.h1, this.length);
   }
}
