package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

@Immutable
abstract class MixinHelper524 extends MixinHelper52 {
   @Override
   public MixinHelper42_2 method1() {
      return this.method2(32);
   }

   @Override
   public MixinHelper42_2 method2(int var1) {
      Preconditions.checkArgument(var1 >= 0);
      return new MixinHelper524.Data2(var1);
   }

   @Override
   public HashCode method3(int var1) {
      return this.method5(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(var1).array());
   }

   @Override
   public HashCode method4(long var1) {
      return this.method5(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(var1).array());
   }

   @Override
   public HashCode method8(CharSequence var1) {
      int var2 = var1.length();
      ByteBuffer var3 = ByteBuffer.allocate(var2 * 2).order(ByteOrder.LITTLE_ENDIAN);

      for (int var4 = 0; var4 < var2; var4++) {
         var3.putChar(var1.charAt(var4));
      }

      return this.method5(var3.array());
   }

   @Override
   public HashCode method9(CharSequence var1, Charset var2) {
      return this.method5(var1.toString().getBytes(var2));
   }

   @Override
   public abstract HashCode method6(byte[] var1, int var2, int var3);

   @Override
   public HashCode method7(ByteBuffer var1) {
      return this.method2(var1.remaining()).method5(var1).method15();
   }

   private static final class Data extends ByteArrayOutputStream {
      Data(int var1) {
         super(var1);
      }

      void write(ByteBuffer var1) {
         int var2 = var1.remaining();
         if (this.count + var2 > this.buf.length) {
            this.buf = Arrays.copyOf(this.buf, this.count + var2);
         }

         var1.get(this.buf, this.count, var2);
         this.count += var2;
      }

      byte[] byteArray() {
         return this.buf;
      }

      int length() {
         return this.count;
      }
   }

   private final class Data2 extends MixinHelper422_2 {
      final MixinHelper524.Data field1;

      Data2(int var2) {
         this.field1 = new MixinHelper524.Data(var2);
      }

      @Override
      public MixinHelper42_2 method2(byte var1) {
         this.field1.write(var1);
         return this;
      }

      @Override
      public MixinHelper42_2 method4(byte[] var1, int var2, int var3) {
         this.field1.write(var1, var2, var3);
         return this;
      }

      @Override
      public MixinHelper42_2 method5(ByteBuffer var1) {
         this.field1.write(var1);
         return this;
      }

      @Override
      public HashCode method15() {
         return MixinHelper524.this.method6(this.field1.byteArray(), 0, this.field1.length());
      }
   }
}
