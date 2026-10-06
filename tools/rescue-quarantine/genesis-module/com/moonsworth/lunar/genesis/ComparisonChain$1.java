package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Booleans;

final class ComparisonChain$1 extends MixinHelper42 {
   ComparisonChain$1() {
      super(null);
   }

   public MixinHelper42 method2(Comparable comparable1, Comparable comparable2) {
      return this.method9(comparable1.compareTo(comparable2));
   }

   public <T> MixinHelper42 method3(@Nullable T value1, @Nullable T value2, Comparator<T> comparator3) {
      return this.method9(comparator3.compare(value1, value2));
   }

   public MixinHelper42 method4(int number1, int number2) {
      return this.method9(MixinHelper122.compare(number1, number2));
   }

   public MixinHelper42 method5(long number1, long number3) {
      return this.method9(Longs.compare(number1, number3));
   }

   public MixinHelper42 method6(float value1, float value2) {
      return this.method9(Float.compare(value1, value2));
   }

   public MixinHelper42 method7(double value1, double value3) {
      return this.method9(Double.compare(value1, value3));
   }

   public MixinHelper42 method9(boolean flag1, boolean flag2) {
      return this.method9(Booleans.compare(flag2, flag1));
   }

   public MixinHelper42 method10(boolean flag1, boolean flag2) {
      return this.method9(Booleans.compare(flag1, flag2));
   }

   MixinHelper42 method9(int number1) {
      return number1 < 0 ? MixinHelper42.method11() : (number1 > 0 ? MixinHelper42.method12() : MixinHelper42.method13());
   }

   public int result() {
      return 0;
   }
}
