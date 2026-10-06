package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

@CanIgnoreReturnValue
abstract class AbstractStreamingHasher extends AbstractHasher {
   private final ByteBuffer field1;
   private final int field2;
   private final int field3;

   protected AbstractStreamingHasher(int number1) {
      this(number1, number1);
   }

   protected AbstractStreamingHasher(int number1, int number2) {
      Preconditions.checkArgument(number2 % number1 == 0);
      this.field1 = ByteBuffer.allocate(number2 + 7).order(ByteOrder.LITTLE_ENDIAN);
      this.field2 = number2;
      this.field3 = number1;
   }

   protected abstract void process(ByteBuffer buffer1);

   protected void processRemaining(ByteBuffer buffer1) {
      ((Buffer)buffer1).position(buffer1.limit());
      ((Buffer)buffer1).limit(this.field3 + 7);

      while (buffer1.position() < this.field3) {
         buffer1.putLong(0L);
      }

      ((Buffer)buffer1).limit(this.field3);
      ((Buffer)buffer1).flip();
      this.process(buffer1);
   }

   public final Hasher method4(byte[] items1, int number2, int number3) {
      return this.method3(ByteBuffer.wrap(items1, number2, number3).order(ByteOrder.LITTLE_ENDIAN));
   }

   public final Hasher method5(ByteBuffer buffer1) {
      ByteOrder byteorder2 = buffer1.order();

      try {
         buffer1.order(ByteOrder.LITTLE_ENDIAN);
         return this.method3(buffer1);
      } finally {
         buffer1.order(byteorder2);
      }
   }

   private Hasher method3(ByteBuffer buffer1) {
      if (buffer1.remaining() <= this.field1.remaining()) {
         this.field1.put(buffer1);
         this.munchIfFull();
         return this;
      }

      int number2 = this.field2 - this.field1.position();

      for (int index3 = 0; index3 < number2; index3++) {
         this.field1.put(buffer1.get());
      }

      this.munch();

      while (buffer1.remaining() >= this.field3) {
         this.process(buffer1);
      }

      this.field1.put(buffer1);
      return this;
   }

   public final Hasher method2(byte number1) {
      this.field1.put(number1);
      this.munchIfFull();
      return this;
   }

   public final Hasher method6(short number1) {
      this.field1.putShort(number1);
      this.munchIfFull();
      return this;
   }

   public final Hasher method12(char character1) {
      this.field1.putChar(character1);
      this.munchIfFull();
      return this;
   }

   public final Hasher method7(int number1) {
      this.field1.putInt(number1);
      this.munchIfFull();
      return this;
   }

   public final Hasher method8(long number1) {
      this.field1.putLong(number1);
      this.munchIfFull();
      return this;
   }

   public final HashCode method15() {
      this.munch();
      ((Buffer)this.field1).flip();
      if (this.field1.remaining() > 0) {
         this.processRemaining(this.field1);
         ((Buffer)this.field1).position(this.field1.limit());
      }

      return this.method10();
   }

   protected abstract HashCode method10();

   private void munchIfFull() {
      if (this.field1.remaining() < 8) {
         this.munch();
      }
   }

   private void munch() {
      ((Buffer)this.field1).flip();

      while (this.field1.remaining() >= this.field3) {
         this.process(this.field1);
      }

      this.field1.compact();
   }
}
