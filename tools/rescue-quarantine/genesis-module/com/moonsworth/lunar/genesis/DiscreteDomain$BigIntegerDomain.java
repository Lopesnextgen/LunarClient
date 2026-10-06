package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.math.BigInteger;

final class DiscreteDomain$BigIntegerDomain extends MixinHelper40<BigInteger> implements Serializable {
   private static final DiscreteDomain$BigIntegerDomain field2 = new DiscreteDomain$BigIntegerDomain();
   private static final BigInteger field3 = BigInteger.valueOf(Long.MIN_VALUE);
   private static final BigInteger field4 = BigInteger.valueOf(Long.MAX_VALUE);
   private static final long field5 = 0L;

   DiscreteDomain$BigIntegerDomain() {
      super(true, null);
   }

   public BigInteger next(BigInteger number1) {
      return number1.add(BigInteger.ONE);
   }

   public BigInteger previous(BigInteger number1) {
      return number1.subtract(BigInteger.ONE);
   }

   BigInteger offset(BigInteger number1, long number2) {
      CollectPreconditions.checkNonnegative(number2, "distance");
      return number1.add(BigInteger.valueOf(number2));
   }

   public long distance(BigInteger number1, BigInteger number2) {
      return number2.subtract(number1).max(field3).min(field4).longValue();
   }

   private Object readResolve() {
      return field2;
   }

   @Override
   public String toString() {
      return "DiscreteDomain.bigIntegers()";
   }
}
