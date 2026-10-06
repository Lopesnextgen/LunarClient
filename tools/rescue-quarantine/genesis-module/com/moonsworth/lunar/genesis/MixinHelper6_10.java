package com.moonsworth.lunar.genesis;
import com.google.common.math.PairedStats;
import com.google.common.primitives.Doubles;
import com.google.common.math.Stats;
import com.google.common.base.Preconditions;

@Annotation2
@Annotation3
public final class MixinHelper6_10 {
   private final MixinHelper9_9 field1 = new MixinHelper9_9();
   private final MixinHelper9_9 field2 = new MixinHelper9_9();
   private double sumOfProductsOfDeltas = 0.0;

   public void add(double var1, double var3) {
      this.field1.add(var1);
      if (Doubles.isFinite(var1) && Doubles.isFinite(var3)) {
         if (this.field1.count() > 1L) {
            this.sumOfProductsOfDeltas = this.sumOfProductsOfDeltas + (var1 - this.field1.mean()) * (var3 - this.field2.mean());
         }
      } else {
         this.sumOfProductsOfDeltas = Double.NaN;
      }

      this.field2.add(var3);
   }

   public void method1(PairedStats var1) {
      if (var1.count() != 0L) {
         this.field1.method1(var1.method1());
         if (this.field2.count() == 0L) {
            this.sumOfProductsOfDeltas = var1.sumOfProductsOfDeltas();
         } else {
            this.sumOfProductsOfDeltas = this.sumOfProductsOfDeltas
               + (var1.sumOfProductsOfDeltas() + (var1.method1().mean() - this.field1.mean()) * (var1.method2().mean() - this.field2.mean()) * var1.count());
         }

         this.field2.method1(var1.method2());
      }
   }

   public PairedStats method2() {
      return new PairedStats(this.field1.method3(), this.field2.method3(), this.sumOfProductsOfDeltas);
   }

   public long count() {
      return this.field1.count();
   }

   public Stats method3() {
      return this.field1.method3();
   }

   public Stats method4() {
      return this.field2.method3();
   }

   public double populationCovariance() {
      Preconditions.checkState(this.count() != 0L);
      return this.sumOfProductsOfDeltas / this.count();
   }

   public final double method5() {
      Preconditions.checkState(this.count() > 1L);
      return this.sumOfProductsOfDeltas / (this.count() - 1L);
   }

   public final double method6() {
      Preconditions.checkState(this.count() > 1L);
      if (Double.isNaN(this.sumOfProductsOfDeltas)) {
         return Double.NaN;
      }

      double var1 = this.field1.sumOfSquaresOfDeltas();
      double var3 = this.field2.sumOfSquaresOfDeltas();
      Preconditions.checkState(var1 > 0.0);
      Preconditions.checkState(var3 > 0.0);
      double var5 = this.ensurePositive(var1 * var3);
      return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(var5));
   }

   public final MixinHelper11_4 method7() {
      Preconditions.checkState(this.count() > 1L);
      if (Double.isNaN(this.sumOfProductsOfDeltas)) {
         return MixinHelper11_4.method4();
      }

      double var1 = this.field1.sumOfSquaresOfDeltas();
      if (var1 > 0.0) {
         return this.field2.sumOfSquaresOfDeltas() > 0.0
            ? MixinHelper11_4.method1(this.field1.mean(), this.field2.mean()).method2(this.sumOfProductsOfDeltas / var1)
            : MixinHelper11_4.method3(this.field2.mean());
      }

      Preconditions.checkState(this.field2.sumOfSquaresOfDeltas() > 0.0);
      return MixinHelper11_4.method2(this.field1.mean());
   }

   private double ensurePositive(double var1) {
      return var1 > 0.0 ? var1 : Double.MIN_VALUE;
   }

   private static double ensureInUnitRange(double var0) {
      return Doubles.constrainToRange(var0, -1.0, 1.0);
   }
}
