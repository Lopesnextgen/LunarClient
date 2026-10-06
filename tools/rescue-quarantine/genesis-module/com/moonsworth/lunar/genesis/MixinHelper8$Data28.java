package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.hash.HashCode;
import com.google.common.primitives.UnsignedInts;

final class MixinHelper8$Data28 extends HashCode implements Serializable {
   final int field2;
   private static final long field3 = 0L;

   MixinHelper8$Data28(int var1) {
      this.field2 = var1;
   }

   @Override
   public int bits() {
      return 32;
   }

   @Override
   public byte[] asBytes() {
      return new byte[]{(byte)this.field2, (byte)(this.field2 >> 8), (byte)(this.field2 >> 16), (byte)(this.field2 >> 24)};
   }

   @Override
   public int asInt() {
      return this.field2;
   }

   @Override
   public long asLong() {
      throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
   }

   @Override
   public long padToLong() {
      return UnsignedInts.toLong(this.field2);
   }

   @Override
   void writeBytesToImpl(byte[] var1, int var2, int var3) {
      for (int var4 = 0; var4 < var3; var4++) {
         var1[var2 + var4] = (byte)(this.field2 >> var4 * 8);
      }
   }

   @Override
   boolean method1(HashCode var1) {
      return this.field2 == var1.asInt();
   }
}
