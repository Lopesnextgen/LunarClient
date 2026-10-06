package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;

final class MixinHelper4$Data10 extends MixinHelper4$Data12 {
   MixinHelper4$Data10(String var1, String var2, @Nullable Character var3) {
      this(new MixinHelper4$Data9(var1, var2.toCharArray()), var3);
   }

   private MixinHelper4$Data10(MixinHelper4$Data9 var1, @Nullable Character var2) {
      super(var1, var2);
      Preconditions.checkArgument(MixinHelper4$Data9.method3(var1).length == 64);
   }

   @Override
   void encodeTo(Appendable var1, byte[] var2, int var3, int var4) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkPositionIndexes(var3, var3 + var4, var2.length);
      int var5 = var3;

      for (int var6 = var4; var6 >= 3; var6 -= 3) {
         int var7 = (var2[var5++] & 255) << 16 | (var2[var5++] & 255) << 8 | var2[var5++] & 255;
         var1.append(this.field6.encode(var7 >>> 18));
         var1.append(this.field6.encode(var7 >>> 12 & 63));
         var1.append(this.field6.encode(var7 >>> 6 & 63));
         var1.append(this.field6.encode(var7 & 63));
      }

      if (var5 < var3 + var4) {
         this.encodeChunkTo(var1, var2, var5, var3 + var4 - var5);
      }
   }

   @Override
   int decodeTo(byte[] var1, CharSequence var2) {
      Preconditions.checkNotNull(var1);
      var2 = this.trimTrailingPadding(var2);
      if (!this.field6.isValidPaddingStartPosition(var2.length())) {
         throw new MixinHelper4$Data11("Invalid input length " + var2.length());
      }

      int var3 = 0;
      int var4 = 0;

      while (var4 < var2.length()) {
         int var5 = this.field6.decode(var2.charAt(var4++)) << 18;
         var5 |= this.field6.decode(var2.charAt(var4++)) << 12;
         var1[var3++] = (byte)(var5 >>> 16);
         if (var4 < var2.length()) {
            var5 |= this.field6.decode(var2.charAt(var4++)) << 6;
            var1[var3++] = (byte)(var5 >>> 8 & 0xFF);
            if (var4 < var2.length()) {
               var5 |= this.field6.decode(var2.charAt(var4++));
               var1[var3++] = (byte)(var5 & 0xFF);
            }
         }
      }

      return var3;
   }

   @Override
   BaseEncoding method6(MixinHelper4$Data9 var1, @Nullable Character var2) {
      return new MixinHelper4$Data10(var1, var2);
   }
}
