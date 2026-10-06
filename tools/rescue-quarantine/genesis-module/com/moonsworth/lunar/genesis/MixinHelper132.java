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
import com.google.common.primitives.Doubles;

@GwtCompatible(emulated = true)
public final class MixinHelper132 extends MixinHelper13_4 {
   public static final int field1 = 4;

   private MixinHelper132() {
   }

   public static int hashCode(float var0) {
      return Float.valueOf(var0).hashCode();
   }

   public static int compare(float var0, float var1) {
      return Float.compare(var0, var1);
   }

   public static boolean isFinite(float var0) {
      return Float.NEGATIVE_INFINITY < var0 && var0 < Float.POSITIVE_INFINITY;
   }

   public static boolean contains(float[] var0, float var1) {
      for (float var5 : var0) {
         if (var5 == var1) {
            return true;
         }
      }

      return false;
   }

   public static int indexOf(float[] var0, float var1) {
      return indexOf(var0, var1, 0, var0.length);
   }

   private static int indexOf(float[] var0, float var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   public static int indexOf(float[] var0, float[] var1) {
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

   public static int lastIndexOf(float[] var0, float var1) {
      return lastIndexOf(var0, var1, 0, var0.length);
   }

   private static int lastIndexOf(float[] var0, float var1, int var2, int var3) {
      for (int var4 = var3 - 1; var4 >= var2; var4--) {
         if (var0[var4] == var1) {
            return var4;
         }
      }

      return -1;
   }

   @Annotation3("Available in GWT! GwtCompatible is to avoid conflict with GWT specialization of base class.")
   public static float min(float... var0) {
      Preconditions.checkArgument(var0.length > 0);
      float var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         var1 = Math.min(var1, var0[var2]);
      }

      return var1;
   }

   @Annotation3("Available in GWT! GwtCompatible is to avoid conflict with GWT specialization of base class.")
   public static float max(float... var0) {
      Preconditions.checkArgument(var0.length > 0);
      float var1 = var0[0];

      for (int var2 = 1; var2 < var0.length; var2++) {
         var1 = Math.max(var1, var0[var2]);
      }

      return var1;
   }

   @Annotation2
   public static float constrainToRange(float var0, float var1, float var2) {
      Preconditions.checkArgument(var1 <= var2, "min (%s) must be less than or equal to max (%s)", var1, var2);
      return Math.min(Math.max(var0, var1), var2);
   }

   public static float[] concat(float[]... var0) {
      int var1 = 0;

      for (float[] var5 : var0) {
         var1 += var5.length;
      }

      float[] var8 = new float[var1];
      int var9 = 0;

      for (float[] var7 : var0) {
         System.arraycopy(var7, 0, var8, var9, var7.length);
         var9 += var7.length;
      }

      return var8;
   }

   @Annotation2
   public static Converter<String, Float> method1() {
      return MixinHelper132.Data.field3;
   }

   public static float[] ensureCapacity(float[] var0, int var1, int var2) {
      Preconditions.checkArgument(var1 >= 0, "Invalid minLength: %s", var1);
      Preconditions.checkArgument(var2 >= 0, "Invalid padding: %s", var2);
      return var0.length < var1 ? Arrays.copyOf(var0, var1 + var2) : var0;
   }

   public static String join(String var0, float... var1) {
      Preconditions.checkNotNull(var0);
      if (var1.length == 0) {
         return "";
      }

      StringBuilder var2 = new StringBuilder(var1.length * 12);
      var2.append(var1[0]);

      for (int var3 = 1; var3 < var1.length; var3++) {
         var2.append(var0).append(var1[var3]);
      }

      return var2.toString();
   }

   public static java.util.Comparator<float[]> lexicographicalComparator() {
      return MixinHelper132.Type.INSTANCE;
   }

   public static void sortDescending(float[] var0) {
      Preconditions.checkNotNull(var0);
      sortDescending(var0, 0, var0.length);
   }

   public static void sortDescending(float[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      Arrays.sort(var0, var1, var2);
      reverse(var0, var1, var2);
   }

   public static void reverse(float[] var0) {
      Preconditions.checkNotNull(var0);
      reverse(var0, 0, var0.length);
   }

   public static void reverse(float[] var0, int var1, int var2) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkPositionIndexes(var1, var2, var0.length);
      int var3 = var1;

      for (int var4 = var2 - 1; var3 < var4; var4--) {
         float var5 = var0[var3];
         var0[var3] = var0[var4];
         var0[var4] = var5;
         var3++;
      }
   }

   public static float[] toArray(Collection<? extends Number> var0) {
      if (var0 instanceof MixinHelper132.Data2) {
         return ((MixinHelper132.Data2)var0).toFloatArray();
      }

      Object[] var1 = var0.toArray();
      int var2 = var1.length;
      float[] var3 = new float[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = ((Number)Preconditions.checkNotNull(var1[var4])).floatValue();
      }

      return var3;
   }

   public static List<Float> asList(float... var0) {
      return var0.length == 0 ? Collections.emptyList() : new MixinHelper132.Data2(var0);
   }

   @Annotation2
   @Annotation3
   public static @Nullable Float tryParse(String var0) {
      if (Doubles.field2.matcher(var0).matches()) {
         try {
            return Float.parseFloat(var0);
         } catch (NumberFormatException var2) {
         }
      }

      return null;
   }

   private static final class Data extends Converter<String, Float> implements Serializable {
      static final MixinHelper132.Data field3 = new MixinHelper132.Data();
      private static final long field4 = 1L;

      protected Float doForward(String var1) {
         return Float.valueOf(var1);
      }

      protected String doBackward(Float var1) {
         return var1.toString();
      }

      @Override
      public String toString() {
         return "Floats.stringConverter()";
      }

      private Object readResolve() {
         return field3;
      }
   }

   @GwtCompatible
   private static class Data2 extends AbstractList<Float> implements Serializable, RandomAccess {
      final float[] field1;
      final int field2;
      final int field3;
      private static final long field4 = 0L;

      Data2(float[] var1) {
         this(var1, 0, var1.length);
      }

      Data2(float[] var1, int var2, int var3) {
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

      public Float get(int var1) {
         Preconditions.checkElementIndex(var1, this.size());
         return this.field1[this.field2 + var1];
      }

      @Override
      public boolean contains(Object var1) {
         return var1 instanceof Float && MixinHelper132.indexOf(this.field1, (Float)var1, this.field2, this.field3) != -1;
      }

      @Override
      public int indexOf(Object var1) {
         if (var1 instanceof Float) {
            int var2 = MixinHelper132.indexOf(this.field1, (Float)var1, this.field2, this.field3);
            if (var2 >= 0) {
               return var2 - this.field2;
            }
         }

         return -1;
      }

      @Override
      public int lastIndexOf(Object var1) {
         if (var1 instanceof Float) {
            int var2 = MixinHelper132.lastIndexOf(this.field1, (Float)var1, this.field2, this.field3);
            if (var2 >= 0) {
               return var2 - this.field2;
            }
         }

         return -1;
      }

      public Float set(int var1, Float var2) {
         Preconditions.checkElementIndex(var1, this.size());
         float var3 = this.field1[this.field2 + var1];
         this.field1[this.field2 + var1] = Preconditions.checkNotNull(var2);
         return var3;
      }

      @Override
      public List<Float> subList(int var1, int var2) {
         int var3 = this.size();
         Preconditions.checkPositionIndexes(var1, var2, var3);
         return var1 == var2 ? Collections.emptyList() : new MixinHelper132.Data2(this.field1, this.field2 + var1, this.field2 + var2);
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (var1 == this) {
            return true;
         }

         if (var1 instanceof MixinHelper132.Data2) {
            MixinHelper132.Data2 var2 = (MixinHelper132.Data2)var1;
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
            var1 = 31 * var1 + MixinHelper132.hashCode(this.field1[var2]);
         }

         return var1;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder(this.size() * 12);
         var1.append('[').append(this.field1[this.field2]);

         for (int var2 = this.field2 + 1; var2 < this.field3; var2++) {
            var1.append(", ").append(this.field1[var2]);
         }

         return var1.append(']').toString();
      }

      float[] toFloatArray() {
         return Arrays.copyOfRange(this.field1, this.field2, this.field3);
      }
   }

   private enum Type implements java.util.Comparator<float[]> {
      INSTANCE;

      public int compare(float[] var1, float[] var2) {
         int var3 = Math.min(var1.length, var2.length);

         for (int var4 = 0; var4 < var3; var4++) {
            int var5 = Float.compare(var1[var4], var2[var4]);
            if (var5 != 0) {
               return var5;
            }
         }

         return var1.length - var2.length;
      }

      @Override
      public String toString() {
         return "Floats.lexicographicalComparator()";
      }
   }
}
