package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import com.google.common.hash.Hasher;
import com.google.common.base.Preconditions;

@CanIgnoreReturnValue
abstract class AbstractByteHasher extends AbstractHasher {
   private final ByteBuffer field1 = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

   AbstractByteHasher() {
   }

   protected abstract void update(byte number1);

   protected void update(byte[] items1) {
      this.update(items1, 0, items1.length);
   }

   protected void update(byte[] items1, int number2, int number3) {
      for (int index4 = number2; index4 < number2 + number3; index4++) {
         this.update(items1[index4]);
      }
   }

   protected void update(ByteBuffer buffer1) {
      if (buffer1.hasArray()) {
         this.update(buffer1.array(), buffer1.arrayOffset() + buffer1.position(), buffer1.remaining());
         ((Buffer)buffer1).position(buffer1.limit());
      } else {
         for (int index2 = buffer1.remaining(); index2 > 0; index2--) {
            this.update(buffer1.get());
         }
      }
   }

   private Hasher method1(int number1) {
      try {
         this.update(this.field1.array(), 0, number1);
      } finally {
         ((Buffer)this.field1).clear();
      }

      return this;
   }

   public Hasher method2(byte number1) {
      this.update(number1);
      return this;
   }

   public Hasher method3(byte[] items1) {
      Preconditions.checkNotNull(items1);
      this.update(items1);
      return this;
   }

   public Hasher method4(byte[] items1, int number2, int number3) {
      Preconditions.checkPositionIndexes(number2, number2 + number3, items1.length);
      this.update(items1, number2, number3);
      return this;
   }

   public Hasher method5(ByteBuffer buffer1) {
      this.update(buffer1);
      return this;
   }

   public Hasher method6(short number1) {
      this.field1.putShort(number1);
      return this.method1(2);
   }

   public Hasher method7(int number1) {
      this.field1.putInt(number1);
      return this.method1(4);
   }

   public Hasher method8(long number1) {
      this.field1.putLong(number1);
      return this.method1(8);
   }

   public Hasher method12(char character1) {
      this.field1.putChar(character1);
      return this.method1(2);
   }
}
