package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.hash.Funnel;
import com.google.common.hash.BloomFilter;

class NumberBase2$Data<T> implements Serializable {
   final long[] field1;
   final int field2;
   final Funnel<? super T> field3;
   final NumberBase2$Extension field4;
   private static final long field5 = 1L;

   NumberBase2$Data(BloomFilter<T> var1) {
      this.field1 = MixinHelperType$Data2.toPlainArray(BloomFilter.method14(var1).field2);
      this.field2 = BloomFilter.method15(var1);
      this.field3 = BloomFilter.method16(var1);
      this.field4 = BloomFilter.method17(var1);
   }

   Object readResolve() {
      return new BloomFilter(new MixinHelperType$Data2(this.field1), this.field2, this.field3, this.field4);
   }
}
