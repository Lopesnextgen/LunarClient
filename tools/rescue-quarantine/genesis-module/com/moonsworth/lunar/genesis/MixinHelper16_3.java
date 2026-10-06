package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
public final class MixinHelper16_3 {
   public static final byte field1 = 64;

   private MixinHelper16_3() {
   }

   public static byte checkedCast(long var0) {
      byte var2 = (byte)var0;
      Preconditions.checkArgument(var2 == var0, "Out of range: %s", var0);
      return var2;
   }

   public static byte saturatedCast(long var0) {
      if (var0 > 127L) {
         return 127;
      } else {
         return var0 < -128L ? -128 : (byte)var0;
      }
   }

   public static int compare(byte var0, byte var1) {
      return var0 - var1;
   }

   public static byte min(byte... var0) {
      Preconditions.checkArgument(var0.length > 0);
      byte var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         if (var0[var2] < var1) {
            var1 = var0[var2];
         }
      }

      return var1;
   }

   public static byte max(byte... var0) {
      Preconditions.checkArgument(var0.length > 0);
      byte var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         if (var0[var2] > var1) {
            var1 = var0[var2];
         }
      }

      return var1;
   }

   public static String join(String var0, byte... var1) {
      Preconditions.checkNotNull(var0);
      if (var1.length == 0) {
         return "";
      }

      StringBuilder var2 = new StringBuilder(var1.length * 5);
      var2.append(var1[0]);

      for (int var3 = 1; var3 < var1.length; var3++) {
         var2.append(var0).append(var1[var3]);
      }

      return var2.toString();
   }

   public static java.util.Comparator<byte[]> lexicographicalComparator() {
      return MixinHelper16$Type2.INSTANCE;
   }

   public static void sortDescending(byte[] var0) {
      Preconditions.checkNotNull(var0);
      sortDescending(var0, 0, var0.length);
   }

   public static void sortDescending(byte[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      Arrays.sort(var0, var1, var2);
      MixinHelper4_5.reverse(var0, var1, var2);
   }
}
