package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashFunction;
import com.google.common.base.Preconditions;
@Immutable
abstract class AbstractCompositeHashFunction extends AbstractHashFunction {
   final HashFunction[] field1;
   private static final long field2 = 0L;

   AbstractCompositeHashFunction(HashFunction... items1) {
      for (HashFunction mixinhelper5_85 : items1) {
         Preconditions.checkNotNull(mixinhelper5_85);
      }

      this.field1 = items1;
   }

   abstract HashCode method1(Hasher[] items1);

   public Hasher method1() {
      Hasher[] items1 = new Hasher[this.field1.length];

      for (int index2 = 0; index2 < items1.length; index2++) {
         items1[index2] = this.field1[index2].method1();
      }

      return this.method4(items1);
   }

   public Hasher method2(int number1) {
      Preconditions.checkArgument(number1 >= 0);
      Hasher[] items2 = new Hasher[this.field1.length];

      for (int index3 = 0; index3 < items2.length; index3++) {
         items2[index3] = this.field1[index3].method2(number1);
      }

      return this.method4(items2);
   }

   private Hasher method4(Hasher[] items1) {
      return new AbstractCompositeHashFunction$1(this, items1);
   }
}
