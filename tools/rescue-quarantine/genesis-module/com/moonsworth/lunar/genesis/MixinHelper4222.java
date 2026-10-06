package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

@CanIgnoreReturnValue
abstract class MixinHelper4222 extends MixinHelper422_2 {
   private final ByteBuffer field1;
   private final int field2;
   private final int field3;

   protected MixinHelper4222(int var1) {
      this(var1, var1);
   }

   protected MixinHelper4222(int var1, int var2) {
      Preconditions.checkArgument(var2 % var1 == 0);
      this.field1 = ByteBuffer.allocate(var2 + 7).order(ByteOrder.LITTLE_ENDIAN);
      this.field2 = var2;
      this.field3 = var1;
   }

   protected abstract void process(ByteBuffer var1);

   protected void processRemaining(ByteBuffer var1) {
      ((Buffer)var1).position(var1.limit());
      ((Buffer)var1).limit(this.field3 + 7);

      while (var1.position() < this.field3) {
         var1.putLong(0L);
      }

      ((Buffer)var1).limit(this.field3);
      ((Buffer)var1).flip();
      this.process(var1);
   }

   @Override
   public final MixinHelper42_2 method4(byte[] var1, int var2, int var3) {
      return this.method3(ByteBuffer.wrap(var1, var2, var3).order(ByteOrder.LITTLE_ENDIAN));
   }

   @Override
   public final MixinHelper42_2 method5(ByteBuffer var1) {
      ByteOrder var2 = var1.order();

      try {
         var1.order(ByteOrder.LITTLE_ENDIAN);
         return this.method3(var1);
      } finally {
         var1.order(var2);
      }
   }

   private MixinHelper42_2 method3(ByteBuffer var1) {
      if (var1.remaining() <= this.field1.remaining()) {
         this.field1.put(var1);
         this.munchIfFull();
         return this;
      }

      int var2 = this.field2 - this.field1.position();

      for (int var3 = 0; var3 < var2; var3++) {
         this.field1.put(var1.get());
      }

      this.munch();

      while (var1.remaining() >= this.field3) {
         this.process(var1);
      }

      this.field1.put(var1);
      return this;
   }

   @Override
   public final MixinHelper42_2 method2(byte var1) {
      this.field1.put(var1);
      this.munchIfFull();
      return this;
   }

   @Override
   public final MixinHelper42_2 method6(short var1) {
      this.field1.putShort(var1);
      this.munchIfFull();
      return this;
   }

   @Override
   public final MixinHelper42_2 method12(char var1) {
      this.field1.putChar(var1);
      this.munchIfFull();
      return this;
   }

   @Override
   public final MixinHelper42_2 method7(int var1) {
      this.field1.putInt(var1);
      this.munchIfFull();
      return this;
   }

   @Override
   public final MixinHelper42_2 method8(long var1) {
      this.field1.putLong(var1);
      this.munchIfFull();
      return this;
   }

   @Override
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
