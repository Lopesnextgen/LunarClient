package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import com.google.common.primitives.Doubles;
import com.google.common.math.Stats;
import com.google.common.base.Preconditions;

@Annotation2
@Annotation3
public final class MixinHelper9_9 {
   private long count = 0L;
   private double mean = 0.0;
   private double sumOfSquaresOfDeltas = 0.0;
   private double min = Double.NaN;
   private double max = Double.NaN;

   public void add(double var1) {
      if (this.count == 0L) {
         this.count = 1L;
         this.mean = var1;
         this.min = var1;
         this.max = var1;
         if (!Doubles.isFinite(var1)) {
            this.sumOfSquaresOfDeltas = Double.NaN;
         }
      } else {
         this.count++;
         if (Doubles.isFinite(var1) && Doubles.isFinite(this.mean)) {
            double var3 = var1 - this.mean;
            this.mean = this.mean + var3 / this.count;
            this.sumOfSquaresOfDeltas = this.sumOfSquaresOfDeltas + var3 * (var1 - this.mean);
         } else {
            this.mean = calculateNewMeanNonFinite(this.mean, var1);
            this.sumOfSquaresOfDeltas = Double.NaN;
         }

         this.min = Math.min(this.min, var1);
         this.max = Math.max(this.max, var1);
      }
   }

   public void addAll(Iterable<? extends Number> var1) {
      for (Number var3 : var1) {
         this.add(var3.doubleValue());
      }
   }

   public void addAll(Iterator<? extends Number> var1) {
      while (var1.hasNext()) {
         this.add(((Number)var1.next()).doubleValue());
      }
   }

   public void addAll(double... var1) {
      for (double var5 : var1) {
         this.add(var5);
      }
   }

   public void addAll(int... var1) {
      for (int var5 : var1) {
         this.add(var5);
      }
   }

   public void addAll(long... var1) {
      for (long var5 : var1) {
         this.add(var5);
      }
   }

   public void addAll(DoubleStream var1) {
      this.method2(var1.collect(MixinHelper9_9::new, MixinHelper9_9::add, MixinHelper9_9::method2));
   }

   public void addAll(IntStream var1) {
      this.method2(var1.collect(MixinHelper9_9::new, MixinHelper9_9::add, MixinHelper9_9::method2));
   }

   public void addAll(LongStream var1) {
      this.method2(var1.collect(MixinHelper9_9::new, MixinHelper9_9::add, MixinHelper9_9::method2));
   }

   public void method1(Stats var1) {
      if (var1.count() != 0L) {
         this.merge(var1.count(), var1.mean(), var1.sumOfSquaresOfDeltas(), var1.min(), var1.max());
      }
   }

   public void method2(MixinHelper9_9 var1) {
      if (var1.count() != 0L) {
         this.merge(var1.count(), var1.mean(), var1.sumOfSquaresOfDeltas(), var1.min(), var1.max());
      }
   }

   private void merge(long var1, double var3, double var5, double var7, double var9) {
      if (this.count == 0L) {
         this.count = var1;
         this.mean = var3;
         this.sumOfSquaresOfDeltas = var5;
         this.min = var7;
         this.max = var9;
      } else {
         this.count += var1;
         if (Doubles.isFinite(this.mean) && Doubles.isFinite(var3)) {
            double var11 = var3 - this.mean;
            this.mean = this.mean + var11 * var1 / this.count;
            this.sumOfSquaresOfDeltas = this.sumOfSquaresOfDeltas + (var5 + var11 * (var3 - this.mean) * var1);
         } else {
            this.mean = calculateNewMeanNonFinite(this.mean, var3);
            this.sumOfSquaresOfDeltas = Double.NaN;
         }

         this.min = Math.min(this.min, var7);
         this.max = Math.max(this.max, var9);
      }
   }

   public Stats method3() {
      return new Stats(this.count, this.mean, this.sumOfSquaresOfDeltas, this.min, this.max);
   }

   public long count() {
      return this.count;
   }

   public double mean() {
      Preconditions.checkState(this.count != 0L);
      return this.mean;
   }

   public final double method4() {
      return this.mean * this.count;
   }

   public final double method5() {
      Preconditions.checkState(this.count != 0L);
      if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
         return Double.NaN;
      } else {
         return this.count == 1L ? 0.0 : MixinHelper5_11.ensureNonNegative(this.sumOfSquaresOfDeltas) / this.count;
      }
   }

   public final double method6() {
      return Math.sqrt(this.method5());
   }

   public final double method7() {
      Preconditions.checkState(this.count > 1L);
      return Double.isNaN(this.sumOfSquaresOfDeltas) ? Double.NaN : MixinHelper5_11.ensureNonNegative(this.sumOfSquaresOfDeltas) / (this.count - 1L);
   }

   public final double method8() {
      return Math.sqrt(this.method7());
   }

   public double min() {
      Preconditions.checkState(this.count != 0L);
      return this.min;
   }

   public double max() {
      Preconditions.checkState(this.count != 0L);
      return this.max;
   }

   double sumOfSquaresOfDeltas() {
      return this.sumOfSquaresOfDeltas;
   }

   static double calculateNewMeanNonFinite(double var0, double var2) {
      if (Doubles.isFinite(var0)) {
         return var2;
      } else {
         return !Doubles.isFinite(var2) && var0 != var2 ? Double.NaN : var0;
      }
   }
}
