package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Preconditions;

final class DiscreteDomain$LongDomain extends MixinHelper40<Long> implements Serializable {
   private static final DiscreteDomain$LongDomain field2 = new DiscreteDomain$LongDomain();
   private static final long field3 = 0L;

   DiscreteDomain$LongDomain() {
      super(true, null);
   }

   public Long next(Long number1) {
      long number2 = number1;
      return number2 == Long.MAX_VALUE ? null : number2 + 1L;
   }

   public Long previous(Long number1) {
      long number2 = number1;
      return number2 == Long.MIN_VALUE ? null : number2 - 1L;
   }

   Long offset(Long number1, long number2) {
      CollectPreconditions.checkNonnegative(number2, "distance");
      long number4 = number1 + number2;
      if (number4 < 0L) {
         Preconditions.checkArgument(number1 < 0L, "overflow");
      }

      return number4;
   }

   public long distance(Long number1, Long number2) {
      long number3 = number2 - number1;
      if (number2 > number1 && number3 < 0L) {
         return Long.MAX_VALUE;
      } else {
         return number2 < number1 && number3 > 0L ? Long.MIN_VALUE : number3;
      }
   }

   public Long minValue() {
      return Long.MIN_VALUE;
   }

   public Long maxValue() {
      return Long.MAX_VALUE;
   }

   private Object readResolve() {
      return field2;
   }

   @Override
   public String toString() {
      return "DiscreteDomain.longs()";
   }
}
