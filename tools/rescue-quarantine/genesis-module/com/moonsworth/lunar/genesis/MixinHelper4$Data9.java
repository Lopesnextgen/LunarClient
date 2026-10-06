package com.moonsworth.lunar.genesis;

import java.math.RoundingMode;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;

final class MixinHelper4$Data9 {
   private final String field1;
   private final char[] field2;
   final int field3;
   final int field4;
   final int field5;
   final int field6;
   private final byte[] field7;
   private final boolean[] field8;

   MixinHelper4$Data9(String var1, char[] var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);

      try {
         this.field4 = MixinHelper7_3.log2(var2.length, RoundingMode.UNNECESSARY);
      } catch (ArithmeticException var8) {
         throw new IllegalArgumentException("Illegal alphabet length " + var2.length, var8);
      }

      int var3 = Math.min(8, Integer.lowestOneBit(this.field4));

      try {
         this.field5 = 8 / var3;
         this.field6 = this.field4 / var3;
      } catch (ArithmeticException var7) {
         throw new IllegalArgumentException("Illegal alphabet " + new String(var2), var7);
      }

      this.field3 = var2.length - 1;
      byte[] var4 = new byte[128];
      Arrays.fill(var4, (byte)-1);

      for (int var5 = 0; var5 < var2.length; var5++) {
         char var6 = var2[var5];
         Preconditions.checkArgument(var6 < var4.length, "Non-ASCII character: %s", var6);
         Preconditions.checkArgument(var4[var6] == -1, "Duplicate character: %s", var6);
         var4[var6] = (byte)var5;
      }

      this.field7 = var4;
      boolean[] var9 = new boolean[this.field5];

      for (int var10 = 0; var10 < this.field6; var10++) {
         var9[MixinHelper7_3.divide(var10 * 8, this.field4, RoundingMode.CEILING)] = true;
      }

      this.field8 = var9;
   }

   char encode(int var1) {
      return this.field2[var1];
   }

   boolean isValidPaddingStartPosition(int var1) {
      return this.field8[var1 % this.field5];
   }

   boolean canDecode(char var1) {
      return var1 <= 127 && this.field7[var1] != -1;
   }

   int decode(char var1) {
      if (var1 > 127) {
         throw new MixinHelper4$Data11("Unrecognized character: 0x" + Integer.toHexString(var1));
      }

      byte var2 = this.field7[var1];
      if (var2 == -1) {
         if (var1 > ' ' && var1 != 127) {
            throw new MixinHelper4$Data11("Unrecognized character: " + var1);
         } else {
            throw new MixinHelper4$Data11("Unrecognized character: 0x" + Integer.toHexString(var1));
         }
      } else {
         return var2;
      }
   }

   private boolean hasLowerCase() {
      for (char var4 : this.field2) {
         if (Ascii.isLowerCase(var4)) {
            return true;
         }
      }

      return false;
   }

   private boolean hasUpperCase() {
      for (char var4 : this.field2) {
         if (Ascii.isUpperCase(var4)) {
            return true;
         }
      }

      return false;
   }

   MixinHelper4$Data9 method1() {
      if (!this.hasLowerCase()) {
         return this;
      }

      Preconditions.checkState(!this.hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
      char[] var1 = new char[this.field2.length];

      for (int var2 = 0; var2 < this.field2.length; var2++) {
         var1[var2] = Ascii.toUpperCase(this.field2[var2]);
      }

      return new MixinHelper4$Data9(this.field1 + ".upperCase()", var1);
   }

   MixinHelper4$Data9 method2() {
      if (!this.hasUpperCase()) {
         return this;
      }

      Preconditions.checkState(!this.hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
      char[] var1 = new char[this.field2.length];

      for (int var2 = 0; var2 < this.field2.length; var2++) {
         var1[var2] = Ascii.toLowerCase(this.field2[var2]);
      }

      return new MixinHelper4$Data9(this.field1 + ".lowerCase()", var1);
   }

   public boolean matches(char var1) {
      return var1 < this.field7.length && this.field7[var1] != -1;
   }

   @Override
   public String toString() {
      return this.field1;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper4$Data9) {
         MixinHelper4$Data9 var2 = (MixinHelper4$Data9)var1;
         return Arrays.equals(this.field2, var2.field2);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode(this.field2);
   }
}
