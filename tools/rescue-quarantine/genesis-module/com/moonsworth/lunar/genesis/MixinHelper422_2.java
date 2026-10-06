package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.google.common.hash.Funnel;
import com.google.common.base.Preconditions;

@CanIgnoreReturnValue
abstract class MixinHelper422_2 implements MixinHelper42_2 {
   @Override
   public final MixinHelper42_2 method11(boolean var1) {
      return this.method12((byte)(var1 ? 1 : 0));
   }

   @Override
   public final MixinHelper42_2 method10(double var1) {
      return this.method8(Double.doubleToRawLongBits(var1));
   }

   @Override
   public final MixinHelper42_2 method9(float var1) {
      return this.method7(Float.floatToRawIntBits(var1));
   }

   @Override
   public MixinHelper42_2 method13(CharSequence var1) {
      int var2 = 0;

      for (int var3 = var1.length(); var2 < var3; var2++) {
         this.method12(var1.charAt(var2));
      }

      return this;
   }

   @Override
   public MixinHelper42_2 method14(CharSequence var1, Charset var2) {
      return this.method3(var1.toString().getBytes(var2));
   }

   @Override
   public MixinHelper42_2 method3(byte[] var1) {
      return this.method4(var1, 0, var1.length);
   }

   @Override
   public MixinHelper42_2 method4(byte[] var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);

      for (int var4 = 0; var4 < var3; var4++) {
         this.method12(var1[var2 + var4]);
      }

      return this;
   }

   @Override
   public MixinHelper42_2 method5(ByteBuffer var1) {
      if (var1.hasArray()) {
         this.method4(var1.array(), var1.arrayOffset() + var1.position(), var1.remaining());
         ((Buffer)var1).position(var1.limit());
      } else {
         for (int var2 = var1.remaining(); var2 > 0; var2--) {
            this.method12(var1.get());
         }
      }

      return this;
   }

   @Override
   public MixinHelper42_2 method6(short var1) {
      this.method12((byte)var1);
      this.method12((byte)(var1 >>> 8));
      return this;
   }

   @Override
   public MixinHelper42_2 method7(int var1) {
      this.method12((byte)var1);
      this.method12((byte)(var1 >>> 8));
      this.method12((byte)(var1 >>> 16));
      this.method12((byte)(var1 >>> 24));
      return this;
   }

   @Override
   public MixinHelper42_2 method8(long var1) {
      for (byte var3 = 0; var3 < 64; var3 += 8) {
         this.method12((byte)(var1 >>> var3));
      }

      return this;
   }

   @Override
   public MixinHelper42_2 method12(char var1) {
      this.method12((byte)var1);
      this.method12((byte)(var1 >>> '\b'));
      return this;
   }

   @Override
   public <T> MixinHelper42_2 method14(T var1, Funnel<? super T> var2) {
      var2.funnel(var1, this);
      return this;
   }
}
