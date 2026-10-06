package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.NoSuchElementException;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
public abstract class MixinHelper40<C extends Comparable> {
   final boolean field1;

   public static MixinHelper40<Integer> method1() {
      return MixinHelper40.Data.field2;
   }

   public static MixinHelper40<Long> method2() {
      return MixinHelper40.Data3.field2;
   }

   public static MixinHelper40<BigInteger> method3() {
      return MixinHelper40.Data2.field2;
   }

   protected MixinHelper40() {
      this(false);
   }

   private MixinHelper40(boolean var1) {
      this.field1 = var1;
   }

   C offset(C var1, long var2) {
      MixinHelper18_3.checkNonnegative(var2, "distance");

      for (long var4 = 0L; var4 < var2; var4++) {
         var1 = this.next((C)var1);
      }

      return (C)var1;
   }

   public abstract C next(C var1);

   public abstract C previous(C var1);

   public abstract long distance(C var1, C var2);

   @CanIgnoreReturnValue
   public C minValue() {
      throw new NoSuchElementException();
   }

   @CanIgnoreReturnValue
   public C maxValue() {
      throw new NoSuchElementException();
   }

   private static final class Data extends MixinHelper40<Integer> implements Serializable {
      private static final MixinHelper40.Data field2 = new MixinHelper40.Data();
      private static final long field3 = 0L;

      Data() {
         super(true);
      }

      public Integer next(Integer var1) {
         int var2 = var1;
         return var2 == Integer.MAX_VALUE ? null : var2 + 1;
      }

      public Integer previous(Integer var1) {
         int var2 = var1;
         return var2 == Integer.MIN_VALUE ? null : var2 - 1;
      }

      Integer offset(Integer var1, long var2) {
         MixinHelper18_3.checkNonnegative(var2, "distance");
         return MixinHelper122.checkedCast(var1.longValue() + var2);
      }

      public long distance(Integer var1, Integer var2) {
         return (long)var2.intValue() - var1.intValue();
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

   private static final class Data2 extends MixinHelper40<BigInteger> implements Serializable {
      private static final MixinHelper40.Data2 field2 = new MixinHelper40.Data2();
      private static final BigInteger field3 = BigInteger.valueOf(Long.MIN_VALUE);
      private static final BigInteger field4 = BigInteger.valueOf(Long.MAX_VALUE);
      private static final long field5 = 0L;

      Data2() {
         super(true);
      }

      public BigInteger next(BigInteger var1) {
         return var1.add(BigInteger.ONE);
      }

      public BigInteger previous(BigInteger var1) {
         return var1.subtract(BigInteger.ONE);
      }

      BigInteger offset(BigInteger var1, long var2) {
         MixinHelper18_3.checkNonnegative(var2, "distance");
         return var1.add(BigInteger.valueOf(var2));
      }

      public long distance(BigInteger var1, BigInteger var2) {
         return var2.subtract(var1).max(field3).min(field4).longValue();
      }

      private Object readResolve() {
         return field2;
      }

      @Override
      public String toString() {
         return "DiscreteDomain.bigIntegers()";
      }
   }

   private static final class Data3 extends MixinHelper40<Long> implements Serializable {
      private static final MixinHelper40.Data3 field2 = new MixinHelper40.Data3();
      private static final long field3 = 0L;

      Data3() {
         super(true);
      }

      public Long next(Long var1) {
         long var2 = var1;
         return var2 == Long.MAX_VALUE ? null : var2 + 1L;
      }

      public Long previous(Long var1) {
         long var2 = var1;
         return var2 == Long.MIN_VALUE ? null : var2 - 1L;
      }

      Long offset(Long var1, long var2) {
         MixinHelper18_3.checkNonnegative(var2, "distance");
         long var4 = var1 + var2;
         if (var4 < 0L) {
            Preconditions.checkArgument(var1 < 0L, "overflow");
         }

         return var4;
      }

      public long distance(Long var1, Long var2) {
         long var3 = var2 - var1;
         if (var2 > var1 && var3 < 0L) {
            return Long.MAX_VALUE;
         } else {
            return var2 < var1 && var3 > 0L ? Long.MIN_VALUE : var3;
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
}
