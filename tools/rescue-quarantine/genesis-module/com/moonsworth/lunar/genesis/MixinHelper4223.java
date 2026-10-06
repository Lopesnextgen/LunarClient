package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import com.google.common.base.Preconditions;

@CanIgnoreReturnValue
abstract class MixinHelper4223 extends MixinHelper422_2 {
   private final ByteBuffer field1 = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

   protected abstract void update(byte var1);

   protected void update(byte[] var1) {
      this.update(var1, 0, var1.length);
   }

   protected void update(byte[] var1, int var2, int var3) {
      for (int var4 = var2; var4 < var2 + var3; var4++) {
         this.update(var1[var4]);
      }
   }

   protected void update(ByteBuffer var1) {
      if (var1.hasArray()) {
         this.update(var1.array(), var1.arrayOffset() + var1.position(), var1.remaining());
         ((Buffer)var1).position(var1.limit());
      } else {
         for (int var2 = var1.remaining(); var2 > 0; var2--) {
            this.update(var1.get());
         }
      }
   }

   private MixinHelper42_2 method1(int var1) {
      try {
         this.update(this.field1.array(), 0, var1);
      } finally {
         ((Buffer)this.field1).clear();
      }

      return this;
   }

   @Override
   public MixinHelper42_2 method2(byte var1) {
      this.update(var1);
      return this;
   }

   @Override
   public MixinHelper42_2 method3(byte[] var1) {
      Preconditions.checkNotNull(var1);
      this.update(var1);
      return this;
   }

   @Override
   public MixinHelper42_2 method4(byte[] var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);
      this.update(var1, var2, var3);
      return this;
   }

   @Override
   public MixinHelper42_2 method5(ByteBuffer var1) {
      this.update(var1);
      return this;
   }

   @Override
   public MixinHelper42_2 method6(short var1) {
      this.field1.putShort(var1);
      return this.method1(2);
   }

   @Override
   public MixinHelper42_2 method7(int var1) {
      this.field1.putInt(var1);
      return this.method1(4);
   }

   @Override
   public MixinHelper42_2 method8(long var1) {
      this.field1.putLong(var1);
      return this.method1(8);
   }

   @Override
   public MixinHelper42_2 method12(char var1) {
      this.field1.putChar(var1);
      return this.method1(2);
   }
}
