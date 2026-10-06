package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterators;
import java.util.Spliterator.OfInt;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;

@GwtCompatible(emulated = true)
public final class MixinHelper122 extends MixinHelper12_4 {
   public static final int field1 = 4;
   public static final int field2 = 1073741824;

   private MixinHelper122() {
   }

   public static int hashCode(int var0) {
      return var0;
   }

   public static int checkedCast(long var0) {
      int var2 = (int)var0;
      Preconditions.checkArgument(var2 == var0, "Out of range: %s", var0);
      return var2;
   }

   public static int saturatedCast(long var0) {
      if (var0 > 2147483647L) {
         return Integer.MAX_VALUE;
      } else {
         return var0 < -2147483648L ? Integer.MIN_VALUE : (int)var0;
      }
   }

   public static int compare(int var0, int var1) {
      return var0 < var1 ? -1 : (var0 > var1 ? 1 : 0);
   }

   public static boolean contains(int[] var0, int var1) {
      for (int var5 : var0) {
         if (var5 == var1) {
            return true;
         }
      }

      return false;
   }

   public static int indexOf(int[] var0, int var1) {
      return indexOf(var0, var1, 0, var0.length);
   }

   private static int indexOf(int[] var0, int var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   public static int indexOf(int[] var0, int[] var1) {
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

   public static int lastIndexOf(int[] var0, int var1) {
      return lastIndexOf(var0, var1, 0, var0.length);
   }

   private static int lastIndexOf(int[] var0, int var1, int var2, int var3) {
      for (int var4 = var3 - 1; var4 >= var2; var4--) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   @Annotation3("Available in GWT! GwtCompatible is to avoid conflict with GWT specialization of base class.")
   public static int min(int... var0) {
      Preconditions.checkArgument(var0.length > 0);
      int var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         if (var0[var2] < var1) {
            var1 = var0[var2];
         }
      }

      return var1;
   }

   @Annotation3("Available in GWT! GwtCompatible is to avoid conflict with GWT specialization of base class.")
   public static int max(int... var0) {
      Preconditions.checkArgument(var0.length > 0);
      int var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         if (var0[var2] > var1) {
            var1 = var0[var2];
         }
      }

      return var1;
   }

   @Annotation2
   public static int constrainToRange(int var0, int var1, int var2) {
      Preconditions.checkArgument(var1 <= var2, "min (%s) must be less than or equal to max (%s)", var1, var2);
      return Math.min(Math.max(var0, var1), var2);
   }

   public static int[] concat(int[]... var0) {
      int var1 = 0;

      for (int[] var5 : var0) {
         var1 += var5.length;
      }

      int[] var8 = new int[var1];
      int var9 = 0;

      for (int[] var7 : var0) {
         System.arraycopy(var7, 0, var8, var9, var7.length);
         var9 += var7.length;
      }

      return var8;
   }

   public static byte[] toByteArray(int var0) {
      return new byte[]{(byte)(var0 >> 24), (byte)(var0 >> 16), (byte)(var0 >> 8), (byte)var0};
   }

   public static int fromByteArray(byte[] var0) {
      Preconditions.checkArgument(var0.length >= 4, "array too small: %s < %s", var0.length, 4);
      return fromBytes(var0[0], var0[1], var0[2], var0[3]);
   }

   public static int fromBytes(byte var0, byte var1, byte var2, byte var3) {
      return var0 << 24 | (var1 & 0xFF) << 16 | (var2 & 0xFF) << 8 | var3 & 0xFF;
   }

   @Annotation2
   public static Converter<String, Integer> method1() {
      return MixinHelper122.Data.field3;
   }

   public static int[] ensureCapacity(int[] var0, int var1, int var2) {
      Preconditions.checkArgument(var1 >= 0, "Invalid minLength: %s", var1);
      Preconditions.checkArgument(var2 >= 0, "Invalid padding: %s", var2);
      return var0.length < var1 ? Arrays.copyOf(var0, var1 + var2) : var0;
   }

   public static String join(String var0, int... var1) {
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

   public static java.util.Comparator<int[]> lexicographicalComparator() {
      return MixinHelper122.Type.INSTANCE;
   }

   public static void sortDescending(int[] var0) {
      Preconditions.checkNotNull(var0);
      sortDescending(var0, 0, var0.length);
   }

   public static void sortDescending(int[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      Arrays.sort(var0, var1, var2);
      reverse(var0, var1, var2);
   }

   public static void reverse(int[] var0) {
      Preconditions.checkNotNull(var0);
      reverse(var0, 0, var0.length);
   }

   public static void reverse(int[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      int var3 = var1;

      for (int var4 = var2 - 1; var3 < var4; var4--) {
         int var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static int[] toArray(Collection<? extends Number> var0) {
      if (var0 instanceof MixinHelper122.Data2) {
         return ((MixinHelper122.Data2)var0).toIntArray();
      }

      Object[] var1 = var0.toArray();
      int var2 = var1.length;
      int[] var3 = new int[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = ((Number)Preconditions.checkNotNull(var1[var4])).intValue();
      }

      return var3;
   }

   public static List<Integer> asList(int... var0) {
      return var0.length == 0 ? Collections.emptyList() : new MixinHelper122.Data2(var0);
   }

   @Annotation2
   public static @Nullable Integer tryParse(String var0) {
      return tryParse(var0, 10);
   }

   @Annotation2
   public static @Nullable Integer tryParse(String var0, int var1) {
      Long var2 = Longs.tryParse(var0, var1);
      return var2 != null && var2 == var2.intValue() ? var2.intValue() : null;
   }

   private static final class Data extends Converter<String, Integer> implements Serializable {
      static final MixinHelper122.Data field3 = new MixinHelper122.Data();
      private static final long field4 = 1L;

      protected Integer doForward(String var1) {
         return Integer.decode(var1);
      }

      protected String doBackward(Integer var1) {
         return var1.toString();
      }

      @Override
      public String toString() {
         return "Ints.stringConverter()";
      }

      private Object readResolve() {
         return field3;
      }
   }

   @GwtCompatible
   private static class Data2 extends AbstractList<Integer> implements Serializable, RandomAccess {
      final int[] field1;
      final int field2;
      final int field3;
      private static final long field4 = 0L;

      Data2(int[] var1) {
         this(var1, 0, var1.length);
      }

      Data2(int[] var1, int var2, int var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @Override
      public int size() {
         return this.field3 - this.field2;
      }

      @Override
      public boolean isEmpty() {
         return false;
      }

      public Integer get(int var1) {
         Preconditions.checkElementIndex(var1, this.size());
         return this.field1[this.field2 + var1];
      }

      public OfInt spliterator() {
         return Spliterators.spliterator(this.field1, this.field2, this.field3, 0);
      }

      @Override
      public boolean contains(Object var1) {
         return var1 instanceof Integer && MixinHelper122.indexOf(this.field1, (Integer)var1, this.field2, this.field3) != -1;
      }

      @Override
      public int indexOf(Object var1) {
         if (var1 instanceof Integer) {
            int var2 = MixinHelper122.indexOf(this.field1, (Integer)var1, this.field2, this.field3);
            if (var2 >= 0) {
               return var2 - this.field2;
            }
         }

         return -1;
      }

      @Override
      public int lastIndexOf(Object var1) {
         if (var1 instanceof Integer) {
            int var2 = MixinHelper122.lastIndexOf(this.field1, (Integer)var1, this.field2, this.field3);
            if (var2 >= 0) {
               return var2 - this.field2;
            }
         }

         return -1;
      }

      public Integer set(int var1, Integer var2) {
         Preconditions.checkElementIndex(var1, this.size());
         int var3 = this.field1[this.field2 + var1];
         this.field1[this.field2 + var1] = Preconditions.checkNotNull(var2);
         return var3;
      }

      @Override
      public List<Integer> subList(int var1, int var2) {
         int var3 = this.size();
         Preconditions.checkPositionIndexes(var1, var2, var3);
         return var1 == var2 ? Collections.emptyList() : new MixinHelper122.Data2(this.field1, this.field2 + var1, this.field2 + var2);
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (var1 == this) {
            return true;
         }

         if (var1 instanceof MixinHelper122.Data2) {
            MixinHelper122.Data2 var2 = (MixinHelper122.Data2)var1;
            int var3 = this.size();
            if (var2.size() != var3) {
               return false;
            }

            for (int var4 = 0; var4 < var3; var4++) {
               if (this.field1[this.field2 + var4] != var2.field1[var2.field2 + var4]) {
                  return false;
               }
            }

            return true;
         } else {
            return super.equals(var1);
         }
      }

      @Override
      public int hashCode() {
         int var1 = 1;

         for (int var2 = this.field2; var2 < this.field3; var2++) {
            var1 = 31 * var1 + MixinHelper122.hashCode(this.field1[var2]);
         }

         return var1;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder(this.size() * 5);
         var1.append('[').append(this.field1[this.field2]);

         for (int var2 = this.field2 + 1; var2 < this.field3; var2++) {
            var1.append(", ").append(this.field1[var2]);
         }

         return var1.append(']').toString();
      }

      int[] toIntArray() {
         return Arrays.copyOfRange(this.field1, this.field2, this.field3);
      }
   }

   private enum Type implements java.util.Comparator<int[]> {
      INSTANCE;

      public int compare(int[] var1, int[] var2) {
         int var3 = Math.min(var1.length, var2.length);

         for (int var4 = 0; var4 < var3; var4++) {
            int var5 = MixinHelper122.compare(var1[var4], var2[var4]);
            if (var5 != 0) {
               return var5;
            }
         }

         return var1.length - var2.length;
      }

      @Override
      public String toString() {
         return "Ints.lexicographicalComparator()";
      }
   }
}
