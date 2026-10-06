package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Spliterator.OfDouble;
import java.util.function.DoubleConsumer;
import java.util.stream.DoubleStream;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;

@Immutable
@Annotation2
@GwtCompatible
public final class SerializableImpl3 implements Serializable {
   private static final SerializableImpl3 field1 = new SerializableImpl3(new double[0]);
   private final double[] field2;
   private final transient int field3;
   private final int field4;

   public static SerializableImpl3 method1() {
      return field1;
   }

   public static SerializableImpl3 method2(double var0) {
      return new SerializableImpl3(new double[]{var0});
   }

   public static SerializableImpl3 method3(double var0, double var2) {
      return new SerializableImpl3(new double[]{var0, var2});
   }

   public static SerializableImpl3 method4(double var0, double var2, double var4) {
      return new SerializableImpl3(new double[]{var0, var2, var4});
   }

   public static SerializableImpl3 method5(double var0, double var2, double var4, double var6) {
      return new SerializableImpl3(new double[]{var0, var2, var4, var6});
   }

   public static SerializableImpl3 method6(double var0, double var2, double var4, double var6, double var8) {
      return new SerializableImpl3(new double[]{var0, var2, var4, var6, var8});
   }

   public static SerializableImpl3 method7(double var0, double var2, double var4, double var6, double var8, double var10) {
      return new SerializableImpl3(new double[]{var0, var2, var4, var6, var8, var10});
   }

   public static SerializableImpl3 method8(double var0, double... var2) {
      Preconditions.checkArgument(var2.length <= 2147483646, "the total number of elements must fit in an int");
      double[] var3 = new double[var2.length + 1];
      var3[0] = var0;
      System.arraycopy(var2, 0, var3, 1, var2.length);
      return new SerializableImpl3(var3);
   }

   public static SerializableImpl3 method9(double[] var0) {
      return var0.length == 0 ? field1 : new SerializableImpl3(Arrays.copyOf(var0, var0.length));
   }

   public static SerializableImpl3 method10(Collection<Double> var0) {
      return var0.isEmpty() ? field1 : new SerializableImpl3(Doubles.toArray(var0));
   }

   public static SerializableImpl3 method11(Iterable<Double> var0) {
      return var0 instanceof Collection ? method10((Collection<Double>)var0) : method14().method3(var0).method7();
   }

   public static SerializableImpl3 method12(DoubleStream var0) {
      double[] var1 = var0.toArray();
      return var1.length == 0 ? field1 : new SerializableImpl3(var1);
   }

   public static SerializableImpl3.Data2 method13(int var0) {
      Preconditions.checkArgument(var0 >= 0, "Invalid initialCapacity: %s", var0);
      return new SerializableImpl3.Data2(var0);
   }

   public static SerializableImpl3.Data2 method14() {
      return new SerializableImpl3.Data2(10);
   }

   private SerializableImpl3(double[] var1) {
      this(var1, 0, var1.length);
   }

   private SerializableImpl3(double[] var1, int var2, int var3) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
   }

   public int length() {
      return this.field4 - this.field3;
   }

   public boolean isEmpty() {
      return this.field4 == this.field3;
   }

   public double get(int var1) {
      Preconditions.checkElementIndex(var1, this.length());
      return this.field2[this.field3 + var1];
   }

   public int indexOf(double var1) {
      for (int var3 = this.field3; var3 < this.field4; var3++) {
         if (areEqual(this.field2[var3], var1)) {
            return var3 - this.field3;
         }
      }

      return -1;
   }

   public int lastIndexOf(double var1) {
      for (int var3 = this.field4 - 1; var3 >= this.field3; var3--) {
         if (areEqual(this.field2[var3], var1)) {
            return var3 - this.field3;
         }
      }

      return -1;
   }

   public boolean contains(double var1) {
      return this.indexOf(var1) >= 0;
   }

   public void forEach(DoubleConsumer var1) {
      Preconditions.checkNotNull(var1);

      for (int var2 = this.field3; var2 < this.field4; var2++) {
         var1.accept(this.field2[var2]);
      }
   }

   public DoubleStream stream() {
      return Arrays.stream(this.field2, this.field3, this.field4);
   }

   public double[] toArray() {
      return Arrays.copyOfRange(this.field2, this.field3, this.field4);
   }

   public SerializableImpl3 method15(int var1, int var2) {
      Preconditions.checkPositionIndexes(var1, var2, this.length());
      return var1 == var2 ? field1 : new SerializableImpl3(this.field2, this.field3 + var1, this.field3 + var2);
   }

   private OfDouble spliterator() {
      return Spliterators.spliterator(this.field2, this.field3, this.field4, 1040);
   }

   public List<Double> asList() {
      return new SerializableImpl3.Data3(this);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof SerializableImpl3)) {
         return false;
      }

      SerializableImpl3 var2 = (SerializableImpl3)var1;
      if (this.length() != var2.length()) {
         return false;
      }

      for (int var3 = 0; var3 < this.length(); var3++) {
         if (!areEqual(this.get(var3), var2.get(var3))) {
            return false;
         }
      }

      return true;
   }

   private static boolean areEqual(double var0, double var2) {
      return Double.doubleToLongBits(var0) == Double.doubleToLongBits(var2);
   }

   @Override
   public int hashCode() {
      int var1 = 1;

      for (int var2 = this.field3; var2 < this.field4; var2++) {
         var1 *= 31;
         var1 += Doubles.hashCode(this.field2[var2]);
      }

      return var1;
   }

   @Override
   public String toString() {
      if (this.isEmpty()) {
         return "[]";
      }

      StringBuilder var1 = new StringBuilder(this.length() * 5);
      var1.append('[').append(this.field2[this.field3]);

      for (int var2 = this.field3 + 1; var2 < this.field4; var2++) {
         var1.append(", ").append(this.field2[var2]);
      }

      var1.append(']');
      return var1.toString();
   }

   public SerializableImpl3 method16() {
      return this.isPartialView() ? new SerializableImpl3(this.toArray()) : this;
   }

   private boolean isPartialView() {
      return this.field3 > 0 || this.field4 < this.field2.length;
   }

   Object writeReplace() {
      return this.method16();
   }

   Object readResolve() {
      return this.isEmpty() ? field1 : this;
   }

   @CanIgnoreReturnValue
   public static final class Data2 {
      private double[] array;
      private int count = 0;

      Data2(int var1) {
         this.array = new double[var1];
      }

      public SerializableImpl3.Data2 method1(double var1) {
         this.ensureRoomFor(1);
         this.array[this.count] = var1;
         this.count++;
         return this;
      }

      public SerializableImpl3.Data2 method2(double[] var1) {
         this.ensureRoomFor(var1.length);
         System.arraycopy(var1, 0, this.array, this.count, var1.length);
         this.count += var1.length;
         return this;
      }

      public SerializableImpl3.Data2 method3(Iterable<Double> var1) {
         if (var1 instanceof Collection) {
            return this.method4((Collection<Double>)var1);
         }

         for (Double var3 : var1) {
            this.method1(var3);
         }

         return this;
      }

      public SerializableImpl3.Data2 method4(Collection<Double> var1) {
         this.ensureRoomFor(var1.size());

         for (Double var3 : var1) {
            this.array[this.count++] = var3;
         }

         return this;
      }

      public SerializableImpl3.Data2 method5(DoubleStream var1) {
         OfDouble var2 = var1.spliterator();
         long var3 = var2.getExactSizeIfKnown();
         if (var3 > 0L) {
            this.ensureRoomFor(MixinHelper122.saturatedCast(var3));
         }

         var2.forEachRemaining(this::method1);
         return this;
      }

      public SerializableImpl3.Data2 method6(SerializableImpl3 var1) {
         this.ensureRoomFor(var1.length());
         System.arraycopy(var1.field2, var1.field3, this.array, this.count, var1.length());
         this.count = this.count + var1.length();
         return this;
      }

      private void ensureRoomFor(int var1) {
         int var2 = this.count + var1;
         if (var2 > this.array.length) {
            double[] var3 = new double[expandedCapacity(this.array.length, var2)];
            System.arraycopy(this.array, 0, var3, 0, this.count);
            this.array = var3;
         }
      }

      private static int expandedCapacity(int var0, int var1) {
         if (var1 < 0) {
            throw new AssertionError("cannot store more than MAX_VALUE elements");
         }

         int var2 = var0 + (var0 >> 1) + 1;
         if (var2 < var1) {
            var2 = Integer.highestOneBit(var1 - 1) << 1;
         }

         if (var2 < 0) {
            var2 = Integer.MAX_VALUE;
         }

         return var2;
      }

      @CheckReturnValue
      public SerializableImpl3 method7() {
         return this.count == 0 ? SerializableImpl3.field1 : new SerializableImpl3(this.array, 0, this.count);
      }
   }

   static class Data3 extends AbstractList<Double> implements Serializable, RandomAccess {
      private final SerializableImpl3 field1;

      private Data3(SerializableImpl3 var1) {
         this.field1 = var1;
      }

      @Override
      public int size() {
         return this.field1.length();
      }

      public Double get(int var1) {
         return this.field1.get(var1);
      }

      @Override
      public boolean contains(Object var1) {
         return this.indexOf(var1) >= 0;
      }

      @Override
      public int indexOf(Object var1) {
         return var1 instanceof Double ? this.field1.indexOf((Double)var1) : -1;
      }

      @Override
      public int lastIndexOf(Object var1) {
         return var1 instanceof Double ? this.field1.lastIndexOf((Double)var1) : -1;
      }

      @Override
      public List<Double> subList(int var1, int var2) {
         return this.field1.method15(var1, var2).asList();
      }

      @Override
      public Spliterator<Double> spliterator() {
         return this.field1.spliterator();
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (var1 instanceof SerializableImpl3.Data3) {
            SerializableImpl3.Data3 var6 = (SerializableImpl3.Data3)var1;
            return this.field1.equals(var6.field1);
         }

         if (!(var1 instanceof List)) {
            return false;
         }

         List var2 = (List)var1;
         if (this.size() != var2.size()) {
            return false;
         }

         int var3 = this.field1.field3;

         for (Object var5 : var2) {
            if (!(var5 instanceof Double) || !SerializableImpl3.areEqual(this.field1.field2[var3++], (Double)var5)) {
               return false;
            }
         }

         return true;
      }

      @Override
      public int hashCode() {
         return this.field1.hashCode();
      }

      @Override
      public String toString() {
         return this.field1.toString();
      }
   }
}
