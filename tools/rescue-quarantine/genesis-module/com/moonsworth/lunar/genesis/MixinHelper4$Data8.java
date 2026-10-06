package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;

final class MixinHelper4$Data8 extends MixinHelper4$Data12 {
   final char[] field10 = new char[512];

   MixinHelper4$Data8(String var1, String var2) {
      this(new MixinHelper4$Data9(var1, var2.toCharArray()));
   }

   private MixinHelper4$Data8(MixinHelper4$Data9 var1) {
      super(var1, null);
      Preconditions.checkArgument(MixinHelper4$Data9.method3(var1).length == 16);

      for (int var2 = 0; var2 < 256; var2++) {
         this.field10[var2] = var1.encode(var2 >>> 4);
         this.field10[var2 | 256] = var1.encode(var2 & 15);
      }
   }

   @Override
   void encodeTo(Appendable var1, byte[] var2, int var3, int var4) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkPositionIndexes(var3, var3 + var4, var2.length);

      for (int var5 = 0; var5 < var4; var5++) {
         int var6 = var2[var3 + var5] & 255;
         var1.append(this.field10[var6]);
         var1.append(this.field10[var6 | 256]);
      }
   }

   @Override
   int decodeTo(byte[] var1, CharSequence var2) {
      Preconditions.checkNotNull(var1);
      if (var2.length() % 2 == 1) {
         throw new MixinHelper4$Data11("Invalid input length " + var2.length());
      }

      int var3 = 0;

      for (byte var4 = 0; var4 < var2.length(); var4 += 2) {
         int var5 = this.field6.decode(var2.charAt(var4)) << 4 | this.field6.decode(var2.charAt(var4 + 1));
         var1[var3++] = (byte)var5;
      }

      return var3;
   }

   @Override
   BaseEncoding method6(MixinHelper4$Data9 var1, @Nullable Character var2) {
      return new MixinHelper4$Data8(var1);
   }
}
