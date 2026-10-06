package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

final class MixinHelper8$Data29 extends HashCode implements Serializable {
   final byte[] field2;
   private static final long field3 = 0L;

   MixinHelper8$Data29(byte[] var1) {
      this.field2 = Preconditions.checkNotNull(var1);
   }

   @Override
   public int bits() {
      return this.field2.length * 8;
   }

   @Override
   public byte[] asBytes() {
      return (byte[])this.field2.clone();
   }

   @Override
   public int asInt() {
      Preconditions.checkState(this.field2.length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", this.field2.length);
      return this.field2[0] & 0xFF | (this.field2[1] & 0xFF) << 8 | (this.field2[2] & 0xFF) << 16 | (this.field2[3] & 0xFF) << 24;
   }

   @Override
   public long asLong() {
      Preconditions.checkState(this.field2.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", this.field2.length);
      return this.padToLong();
   }

   @Override
   public long padToLong() {
      long var1 = this.field2[0] & 0xFF;

      for (int var3 = 1; var3 < Math.min(this.field2.length, 8); var3++) {
         var1 |= (this.field2[var3] & 255L) << var3 * 8;
      }

      return var1;
   }

   @Override
   void writeBytesToImpl(byte[] var1, int var2, int var3) {
      System.arraycopy(this.field2, 0, var1, var2, var3);
   }

   @Override
   byte[] getBytesInternal() {
      return this.field2;
   }

   @Override
   boolean method1(HashCode var1) {
      if (this.field2.length != var1.getBytesInternal().length) {
         return false;
      }

      boolean var2 = true;

      for (int var3 = 0; var3 < this.field2.length; var3++) {
         var2 &= this.field2[var3] == var1.getBytesInternal()[var3];
      }

      return var2;
   }
}
