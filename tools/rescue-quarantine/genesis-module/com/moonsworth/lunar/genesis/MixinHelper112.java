package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true)
public final class MixinHelper112 extends MixinHelper11 {
   public static final int field1 = 2;
   public static final short field2 = 16384;

   private MixinHelper112() {
   }

   public static int hashCode(short var0) {
      return var0;
   }

   public static short checkedCast(long var0) {
      short var2 = (short)var0;
      Preconditions.checkArgument(var2 == var0, "Out of range: %s", var0);
      return var2;
   }

   public static short saturatedCast(long var0) {
      if (var0 > 32767L) {
         return 32767;
      } else {
         return var0 < -32768L ? -32768 : (short)var0;
      }
   }

   public static int compare(short var0, short var1) {
      return var0 - var1;
   }

   public static boolean contains(short[] var0, short var1) {
      for (short var5 : var0) {
         if (var5 == var1) {
            return true;
         }
      }

      return false;
   }

   public static int indexOf(short[] var0, short var1) {
      return indexOf(var0, var1, 0, var0.length);
   }

   private static int indexOf(short[] var0, short var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   public static int indexOf(short[] var0, short[] var1) {
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

   public static int lastIndexOf(short[] var0, short var1) {
      return lastIndexOf(var0, var1, 0, var0.length);
   }

   private static int lastIndexOf(short[] var0, short var1, int var2, int var3) {
      for (int var4 = var3 - 1; var4 >= var2; var4--) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   @Annotation3("Available in GWT! GwtCompatible is to avoid conflict with GWT specialization of base class.")
   public static short min(short... var0) {
      Preconditions.checkArgument(var0.length > 0);
      short var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         if (var0[var2] < var1) {
            var1 = var0[var2];
         }
      }

      return var1;
   }

   @Annotation3("Available in GWT! GwtCompatible is to avoid conflict with GWT specialization of base class.")
   public static short max(short... var0) {
      Preconditions.checkArgument(var0.length > 0);
      short var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         if (var0[var2] > var1) {
            var1 = var0[var2];
         }
      }

      return var1;
   }

   @Annotation2
   public static short constrainToRange(short var0, short var1, short var2) {
      Preconditions.checkArgument(var1 <= var2, "min (%s) must be less than or equal to max (%s)", var1, var2);
      return var0 < var1 ? var1 : (var0 < var2 ? var0 : var2);
   }

   public static short[] concat(short[]... var0) {
      int var1 = 0;

      for (short[] var5 : var0) {
         var1 += var5.length;
      }

      short[] var8 = new short[var1];
      int var9 = 0;

      for (short[] var7 : var0) {
         System.arraycopy(var7, 0, var8, var9, var7.length);
         var9 += var7.length;
      }

      return var8;
   }

   @Annotation3
   public static byte[] toByteArray(short var0) {
      return new byte[]{(byte)(var0 >> 8), (byte)var0};
   }

   @Annotation3
   public static short fromByteArray(byte[] var0) {
      Preconditions.checkArgument(var0.length >= 2, "array too small: %s < %s", var0.length, 2);
      return fromBytes(var0[0], var0[1]);
   }

   @Annotation3
   public static short fromBytes(byte var0, byte var1) {
      return (short)(var0 << 8 | var1 & 0xFF);
   }

   @Annotation2
   public static Converter<String, Short> method1() {
      return MixinHelper112.Data2.field3;
   }

   public static short[] ensureCapacity(short[] var0, int var1, int var2) {
      Preconditions.checkArgument(var1 >= 0, "Invalid minLength: %s", var1);
      Preconditions.checkArgument(var2 >= 0, "Invalid padding: %s", var2);
      return var0.length < var1 ? Arrays.copyOf(var0, var1 + var2) : var0;
   }

   public static String join(String var0, short... var1) {
      Preconditions.checkNotNull(var0);
      if (var1.length == 0) {
         return "";
      }

      StringBuilder var2 = new StringBuilder(var1.length * 6);
      var2.append(var1[0]);

      for (int var3 = 1; var3 < var1.length; var3++) {
         var2.append(var0).append(var1[var3]);
      }

      return var2.toString();
   }

   public static java.util.Comparator<short[]> lexicographicalComparator() {
      return MixinHelper112.Type.INSTANCE;
   }

   public static void sortDescending(short[] var0) {
      Preconditions.checkNotNull(var0);
      sortDescending(var0, 0, var0.length);
   }

   public static void sortDescending(short[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      Arrays.sort(var0, var1, var2);
      reverse(var0, var1, var2);
   }

   public static void reverse(short[] var0) {
      Preconditions.checkNotNull(var0);
      reverse(var0, 0, var0.length);
   }

   public static void reverse(short[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      int var3 = var1;

      for (int var4 = var2 - 1; var3 < var4; var4--) {
         short var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static short[] toArray(Collection<? extends Number> var0) {
      if (var0 instanceof MixinHelper112.Data) {
         return ((MixinHelper112.Data)var0).toShortArray();
      }

      Object[] var1 = var0.toArray();
      int var2 = var1.length;
      short[] var3 = new short[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = ((Number)Preconditions.checkNotNull(var1[var4])).shortValue();
      }

      return var3;
   }

   public static List<Short> asList(short... var0) {
      return var0.length == 0 ? Collections.emptyList() : new MixinHelper112.Data(var0);
   }

   @GwtCompatible
   private static class Data extends AbstractList<Short> implements Serializable, RandomAccess {
      final short[] field1;
      final int field2;
      final int field3;
      private static final long field4 = 0L;

      Data(short[] var1) {
         this(var1, 0, var1.length);
      }

      Data(short[] var1, int var2, int var3) {
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

      public Short get(int var1) {
         Preconditions.checkElementIndex(var1, this.size());
         return this.field1[this.field2 + var1];
      }

      @Override
      public boolean contains(@Nullable Object var1) {
         return var1 instanceof Short && MixinHelper112.indexOf(this.field1, (Short)var1, this.field2, this.field3) != -1;
      }

      @Override
      public int indexOf(@Nullable Object var1) {
         if (var1 instanceof Short) {
            int var2 = MixinHelper112.indexOf(this.field1, (Short)var1, this.field2, this.field3);
            if (var2 >= 0) {
               return var2 - this.field2;
            }
         }

         return -1;
      }

      @Override
      public int lastIndexOf(@Nullable Object var1) {
         if (var1 instanceof Short) {
            int var2 = MixinHelper112.lastIndexOf(this.field1, (Short)var1, this.field2, this.field3);
            if (var2 >= 0) {
               return var2 - this.field2;
            }
         }

         return -1;
      }

      public Short set(int var1, Short var2) {
         Preconditions.checkElementIndex(var1, this.size());
         short var3 = this.field1[this.field2 + var1];
         this.field1[this.field2 + var1] = Preconditions.checkNotNull(var2);
         return var3;
      }

      @Override
      public List<Short> subList(int var1, int var2) {
         int var3 = this.size();
         Preconditions.checkPositionIndexes(var1, var2, var3);
         return var1 == var2 ? Collections.emptyList() : new MixinHelper112.Data(this.field1, this.field2 + var1, this.field2 + var2);
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (var1 == this) {
            return true;
         }

         if (var1 instanceof MixinHelper112.Data) {
            MixinHelper112.Data var2 = (MixinHelper112.Data)var1;
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
            var1 = 31 * var1 + MixinHelper112.hashCode(this.field1[var2]);
         }

         return var1;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder(this.size() * 6);
         var1.append('[').append(this.field1[this.field2]);

         for (int var2 = this.field2 + 1; var2 < this.field3; var2++) {
            var1.append(", ").append(this.field1[var2]);
         }

         return var1.append(']').toString();
      }

      short[] toShortArray() {
         return Arrays.copyOfRange(this.field1, this.field2, this.field3);
      }
   }

   private static final class Data2 extends Converter<String, Short> implements Serializable {
      static final MixinHelper112.Data2 field3 = new MixinHelper112.Data2();
      private static final long field4 = 1L;

      protected Short doForward(String var1) {
         return Short.decode(var1);
      }

      protected String doBackward(Short var1) {
         return var1.toString();
      }

      @Override
      public String toString() {
         return "Shorts.stringConverter()";
      }

      private Object readResolve() {
         return field3;
      }
   }

   private enum Type implements java.util.Comparator<short[]> {
      INSTANCE;

      public int compare(short[] var1, short[] var2) {
         int var3 = Math.min(var1.length, var2.length);

         for (int var4 = 0; var4 < var3; var4++) {
            int var5 = MixinHelper112.compare(var1[var4], var2[var4]);
            if (var5 != 0) {
               return var5;
            }
         }

         return var1.length - var2.length;
      }

      @Override
      public String toString() {
         return "Shorts.lexicographicalComparator()";
      }
   }
}
