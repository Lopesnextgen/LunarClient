package com.moonsworth.lunar.genesis;
import com.google.common.escape.UnicodeEscaper;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@Annotation2
@GwtCompatible
public final class MixinHelper32_3 extends UnicodeEscaper {
   private static final char[] field3 = new char[]{'+'};
   private static final char[] field4 = "0123456789ABCDEF".toCharArray();
   private final boolean field5;
   private final boolean[] field6;

   public MixinHelper32_3(String var1, boolean var2) {
      Preconditions.checkNotNull(var1);
      if (var1.matches(".*[0-9A-Za-z].*")) {
         throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
      }

      var1 = var1 + "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      if (var2 && var1.contains(" ")) {
         throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
      }

      this.field5 = var2;
      this.field6 = createSafeOctets(var1);
   }

   private static boolean[] createSafeOctets(String var0) {
      int var1 = -1;
      char[] var2 = var0.toCharArray();

      for (char var6 : var2) {
         var1 = Math.max(var6, var1);
      }

      boolean[] var8 = new boolean[var1 + 1];

      for (char var7 : var2) {
         var8[var7] = true;
      }

      return var8;
   }

   @Override
   protected int nextEscapeIndex(CharSequence var1, int var2, int var3) {
      Preconditions.checkNotNull(var1);

      while (var2 < var3) {
         char var4 = var1.charAt(var2);
         if (var4 >= this.field6.length || !this.field6[var4]) {
            break;
         }

         var2++;
      }

      return var2;
   }

   @Override
   public String escape(String var1) {
      Preconditions.checkNotNull(var1);
      int var2 = var1.length();

      for (int var3 = 0; var3 < var2; var3++) {
         char var4 = var1.charAt(var3);
         if (var4 >= this.field6.length || !this.field6[var4]) {
            return this.method1(var1, var3);
         }
      }

      return var1;
   }

   @Override
   protected char[] escape(int var1) {
      if (var1 < this.field6.length && this.field6[var1]) {
         return null;
      } else if (var1 == 32 && this.field5) {
         return field3;
      } else if (var1 <= 127) {
         char[] var18 = new char[]{'%', '\u0000', field4[var1 & 15]};
         var18[1] = field4[var1 >>> 4];
         return var18;
      } else if (var1 <= 2047) {
         char[] var17 = new char[]{'%', '\u0000', '\u0000', '%', '\u0000', field4[var1 & 15]};
         var1 >>>= 4;
         var17[4] = field4[8 | var1 & 3];
         var1 >>>= 2;
         var17[2] = field4[var1 & 15];
         var1 >>>= 4;
         var17[1] = field4[12 | var1];
         return var17;
      } else if (var1 <= 65535) {
         char[] var16 = new char[9];
         var16[0] = '%';
         var16[1] = 'E';
         var16[3] = '%';
         var16[6] = '%';
         var16[8] = field4[var1 & 15];
         var1 >>>= 4;
         var16[7] = field4[8 | var1 & 3];
         var1 >>>= 2;
         var16[5] = field4[var1 & 15];
         var1 >>>= 4;
         var16[4] = field4[8 | var1 & 3];
         var1 >>>= 2;
         var16[2] = field4[var1];
         return var16;
      } else if (var1 <= 1114111) {
         char[] var2 = new char[12];
         var2[0] = '%';
         var2[1] = 'F';
         var2[3] = '%';
         var2[6] = '%';
         var2[9] = '%';
         var2[11] = field4[var1 & 15];
         var1 >>>= 4;
         var2[10] = field4[8 | var1 & 3];
         var1 >>>= 2;
         var2[8] = field4[var1 & 15];
         var1 >>>= 4;
         var2[7] = field4[8 | var1 & 3];
         var1 >>>= 2;
         var2[5] = field4[var1 & 15];
         var1 >>>= 4;
         var2[4] = field4[8 | var1 & 3];
         var1 >>>= 2;
         var2[2] = field4[var1 & 7];
         return var2;
      } else {
         throw new IllegalArgumentException("Invalid unicode character value " + var1);
      }
   }
}
