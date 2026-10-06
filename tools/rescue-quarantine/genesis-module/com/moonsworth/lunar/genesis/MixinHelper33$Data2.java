package com.moonsworth.lunar.genesis;
import com.google.common.collect.Multiset;

final class MixinHelper33$Data2 implements java.util.Comparator<Multiset.Extension<?>> {
   static final MixinHelper33$Data2 field1 = new MixinHelper33$Data2();

   private MixinHelper33$Data2() {
   }

   public int method1(Multiset.Extension<?> var1, Multiset.Extension<?> var2) {
      return var2.getCount() - var1.getCount();
   }
}
