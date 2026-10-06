package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
public final class MixinHelper4_5 {
   private MixinHelper4_5() {
   }

   public static int hashCode(byte var0) {
      return var0;
   }

   public static boolean contains(byte[] var0, byte var1) {
      for (byte var5 : var0) {
         if (var5 == var1) {
            return true;
         }
      }

      return false;
   }

   public static int indexOf(byte[] var0, byte var1) {
      return indexOf(var0, var1, 0, var0.length);
   }

   private static int indexOf(byte[] var0, byte var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   public static int indexOf(byte[] var0, byte[] var1) {
      Preconditions.checkNotNull(var0, "array");
      Preconditions.checkNotNull(var1, "target");
      if (var1.length == 0) {
         return 0;
      }

      label28:
      for (int var2 = 0; var2 < var0.length - var1.length + 1; var2++) {
         for (int var3 = 0; var3 < var1.length; var3++) {
            if (var0[var2 + var3] != var1[var3]) {
               continue label28;
            }
         }

         return var2;
      }

      return -1;
   }

   public static int lastIndexOf(byte[] var0, byte var1) {
      return lastIndexOf(var0, var1, 0, var0.length);
   }

   private static int lastIndexOf(byte[] var0, byte var1, int var2, int var3) {
      for (int var4 = var3 - 1; var4 >= var2; var4--) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   public static byte[] concat(byte[]... var0) {
      int var1 = 0;

      for (byte[] var5 : var0) {
         var1 += var5.length;
      }

      byte[] var8 = new byte[var1];
      int var9 = 0;

      for (byte[] var7 : var0) {
         System.arraycopy(var7, 0, var8, var9, var7.length);
         var9 += var7.length;
      }

      return var8;
   }

   public static byte[] ensureCapacity(byte[] var0, int var1, int var2) {
      Preconditions.checkArgument(var1 >= 0, "Invalid minLength: %s", var1);
      Preconditions.checkArgument(var2 >= 0, "Invalid padding: %s", var2);
      return var0.length < var1 ? Arrays.copyOf(var0, var1 + var2) : var0;
   }

   public static byte[] toArray(Collection<? extends Number> var0) {
      if (var0 instanceof MixinHelper4$Data14) {
         return ((MixinHelper4$Data14)var0).toByteArray();
      }

      Object[] var1 = var0.toArray();
      int var2 = var1.length;
      byte[] var3 = new byte[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = ((Number)Preconditions.checkNotNull(var1[var4])).byteValue();
      }

      return var3;
   }

   public static List<Byte> asList(byte... var0) {
      return var0.length == 0 ? Collections.emptyList() : new MixinHelper4$Data14(var0);
   }

   public static void reverse(byte[] var0) {
      Preconditions.checkNotNull(var0);
      reverse(var0, 0, var0.length);
   }

   public static void reverse(byte[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      int var3 = var1;

      for (int var4 = var2 - 1; var3 < var4; var4--) {
         byte var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }
}
