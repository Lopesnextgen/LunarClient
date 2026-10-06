package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import com.google.common.eventbus.Subscribe;

@Subscribe
class UnsignedBytes$LexicographicalComparatorHolder {
   static final String field1 = UnsignedBytes$LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator";
   static final Comparator<byte[]> field2 = getBestComparator();

   UnsignedBytes$LexicographicalComparatorHolder() {
   }

   static Comparator<byte[]> getBestComparator() {
      try {
         Class clazz0 = Class.forName(field1);
         return (Comparator<byte[]>)clazz0.getEnumConstants()[0];
      } catch (Throwable exception2) {
         return MixinHelper8_7.lexicographicalComparatorJavaImpl();
      }
   }
}
