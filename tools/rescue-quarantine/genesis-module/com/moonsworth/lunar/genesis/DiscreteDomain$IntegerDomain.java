package com.moonsworth.lunar.genesis;

import java.io.Serializable;

final class DiscreteDomain$IntegerDomain extends MixinHelper40<Integer> implements Serializable {
   private static final DiscreteDomain$IntegerDomain field2 = new DiscreteDomain$IntegerDomain();
   private static final long field3 = 0L;

   DiscreteDomain$IntegerDomain() {
      super(true, null);
   }

   public Integer next(Integer number1) {
      int number2 = number1;
      return number2 == Integer.MAX_VALUE ? null : number2 + 1;
   }

   public Integer previous(Integer number1) {
      int number2 = number1;
      return number2 == Integer.MIN_VALUE ? null : number2 - 1;
   }

   Integer offset(Integer number1, long number2) {
      CollectPreconditions.checkNonnegative(number2, "distance");
      return MixinHelper122.checkedCast(number1.longValue() + number2);
   }

   public long distance(Integer number1, Integer number2) {
      return (long)number2.intValue() - number1.intValue();
   }

   public Integer minValue() {
      return Integer.MIN_VALUE;
   }

   public Integer maxValue() {
      return Integer.MAX_VALUE;
   }

   private Object readResolve() {
      return field2;
   }

   @Override
   public String toString() {
      return "DiscreteDomain.integers()";
   }
}
