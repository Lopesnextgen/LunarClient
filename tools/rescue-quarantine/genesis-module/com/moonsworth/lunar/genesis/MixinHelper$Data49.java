package com.moonsworth.lunar.genesis;

import java.math.RoundingMode;
import java.util.Collection;
import com.google.common.math.Quantiles;
import com.google.common.primitives.Doubles;
import com.google.common.math.LongMath;
import com.google.common.base.Preconditions;

public final class MixinHelper$Data49 {
   private final int field1;
   private final int field2;

   private MixinHelper$Data49(int var1, int var2) {
      Quantiles.access$300(var2, var1);
      this.field1 = var1;
      this.field2 = var2;
   }

   public double compute(Collection<? extends Number> var1) {
      return this.computeInPlace(Doubles.toArray(var1));
   }

   public double compute(double... var1) {
      return this.computeInPlace((double[])var1.clone());
   }

   public double compute(long... var1) {
      return this.computeInPlace(Quantiles.access$400(var1));
   }

   public double compute(int... var1) {
      return this.computeInPlace(Quantiles.access$500(var1));
   }

   public double computeInPlace(double... var1) {
      Preconditions.checkArgument(var1.length > 0, "Cannot calculate quantiles of an empty dataset");
      if (Quantiles.access$600(var1)) {
         return Double.NaN;
      }

      long var2 = (long)this.field2 * (var1.length - 1);
      int var4 = (int)LongMath.divide(var2, this.field1, RoundingMode.DOWN);
      int var5 = (int)(var2 - (long)var4 * this.field1);
      Quantiles.access$700(var4, var1, 0, var1.length - 1);
      if (var5 == 0) {
         return var1[var4];
      }

      Quantiles.access$700(var4 + 1, var1, var4 + 1, var1.length - 1);
      return Quantiles.access$800(var1[var4], var1[var4 + 1], var5, this.field1);
   }
}
