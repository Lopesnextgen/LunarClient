package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true)
public final class MixinHelper7_8 {
   public static final int field1 = 2;

   private MixinHelper7_8() {
   }

   public static int hashCode(char var0) {
      return var0;
   }

   public static char checkedCast(long var0) {
      char var2 = (char)var0;
      Preconditions.checkArgument(var2 == var0, "Out of range: %s", var0);
      return var2;
   }

   public static char saturatedCast(long var0) {
      if (var0 > 65535L) {
         return '\uffff';
      } else {
         return var0 < 0L ? '\u0000' : (char)var0;
      }
   }

   public static int compare(char var0, char var1) {
      return var0 - var1;
   }

   public static boolean contains(char[] var0, char var1) {
      for (char var5 : var0) {
         if (var5 == var1) {
            return true;
         }
      }

      return false;
   }

   public static int indexOf(char[] var0, char var1) {
      return indexOf(var0, var1, 0, var0.length);
   }

   private static int indexOf(char[] var0, char var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   public static int indexOf(char[] var0, char[] var1) {
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

   public static int lastIndexOf(char[] var0, char var1) {
      return lastIndexOf(var0, var1, 0, var0.length);
   }

   private static int lastIndexOf(char[] var0, char var1, int var2, int var3) {
      for (int var4 = var3 - 1; var4 >= var2; var4--) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   public static char min(char... var0) {
      Preconditions.checkArgument(var0.length > 0);
      char var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         if (var0[var2] < var1) {
            var1 = var0[var2];
         }
      }

      return var1;
   }

   public static char max(char... var0) {
      Preconditions.checkArgument(var0.length > 0);
      char var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         if (var0[var2] > var1) {
            var1 = var0[var2];
         }
      }

      return var1;
   }

   @Annotation2
   public static char constrainToRange(char var0, char var1, char var2) {
      Preconditions.checkArgument(var1 <= var2, "min (%s) must be less than or equal to max (%s)", var1, var2);
      return var0 < var1 ? var1 : (var0 < var2 ? var0 : var2);
   }

   public static char[] concat(char[]... var0) {
      int var1 = 0;

      for (char[] var5 : var0) {
         var1 += var5.length;
      }

      char[] var8 = new char[var1];
      int var9 = 0;

      for (char[] var7 : var0) {
         System.arraycopy(var7, 0, var8, var9, var7.length);
         var9 += var7.length;
      }

      return var8;
   }

   @Annotation3
   public static byte[] toByteArray(char var0) {
      return new byte[]{(byte)(var0 >> '\b'), (byte)var0};
   }

   @Annotation3
   public static char fromByteArray(byte[] var0) {
      Preconditions.checkArgument(var0.length >= 2, "array too small: %s < %s", var0.length, 2);
      return fromBytes(var0[0], var0[1]);
   }

   @Annotation3
   public static char fromBytes(byte var0, byte var1) {
      return (char)(var0 << '\b' | var1 & 0xFF);
   }

   public static char[] ensureCapacity(char[] var0, int var1, int var2) {
      Preconditions.checkArgument(var1 >= 0, "Invalid minLength: %s", var1);
      Preconditions.checkArgument(var2 >= 0, "Invalid padding: %s", var2);
      return var0.length < var1 ? Arrays.copyOf(var0, var1 + var2) : var0;
   }

   public static String join(String var0, char... var1) {
      Preconditions.checkNotNull(var0);
      int var2 = var1.length;
      if (var2 == 0) {
         return "";
      }

      StringBuilder var3 = new StringBuilder(var2 + var0.length() * (var2 - 1));
      var3.append(var1[0]);

      for (int var4 = 1; var4 < var2; var4++) {
         var3.append(var0).append(var1[var4]);
      }

      return var3.toString();
   }

   public static java.util.Comparator<char[]> lexicographicalComparator() {
      return MixinHelper7$Type9.INSTANCE;
   }

   public static char[] toArray(Collection<Character> var0) {
      if (var0 instanceof MixinHelper7$Data18) {
         return ((MixinHelper7$Data18)var0).toCharArray();
      }

      Object[] var1 = var0.toArray();
      int var2 = var1.length;
      char[] var3 = new char[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = (Character)Preconditions.checkNotNull(var1[var4]);
      }

      return var3;
   }

   public static void sortDescending(char[] var0) {
      Preconditions.checkNotNull(var0);
      sortDescending(var0, 0, var0.length);
   }

   public static void sortDescending(char[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      Arrays.sort(var0, var1, var2);
      reverse(var0, var1, var2);
   }

   public static void reverse(char[] var0) {
      Preconditions.checkNotNull(var0);
      reverse(var0, 0, var0.length);
   }

   public static void reverse(char[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      int var3 = var1;

      for (int var4 = var2 - 1; var3 < var4; var4--) {
         char var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static List<Character> asList(char... var0) {
      return var0.length == 0 ? Collections.emptyList() : new MixinHelper7$Data18(var0);
   }
}
