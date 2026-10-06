package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.hash.HashCode;

final class MixinHelper8$Data30 extends HashCode implements Serializable {
   final long field2;
   private static final long field3 = 0L;

   MixinHelper8$Data30(long var1) {
      this.field2 = var1;
   }

   @Override
   public int bits() {
      return 64;
   }

   @Override
   public byte[] asBytes() {
      return new byte[]{
         (byte)this.field2,
         (byte)(this.field2 >> 8),
         (byte)(this.field2 >> 16),
         (byte)(this.field2 >> 24),
         (byte)(this.field2 >> 32),
         (byte)(this.field2 >> 40),
         (byte)(this.field2 >> 48),
         (byte)(this.field2 >> 56)
      };
   }

   @Override
   public int asInt() {
      return (int)this.field2;
   }

   @Override
   public long asLong() {
      return this.field2;
   }

   @Override
   public long padToLong() {
      return this.field2;
   }

   @Override
   void writeBytesToImpl(byte[] var1, int var2, int var3) {
      for (int var4 = 0; var4 < var3; var4++) {
         var1[var2 + var4] = (byte)(this.field2 >> var4 * 8);
      }
   }

   @Override
   boolean method1(HashCode var1) {
      return this.field2 == var1.asLong();
   }
}
