package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

abstract class TypeToken$TypeCollector<K> {
   static final TypeToken$TypeCollector<MixinHelper23_2<?>> field1 = new Data17$1();
   static final TypeToken$TypeCollector<Class<?>> field2 = new Data17$2();

   private TypeToken$TypeCollector() {
   }

   final TypeToken$TypeCollector<K> method1() {
      return new Data17$3(this, this);
   }

   final ImmutableList<K> method2(K value1) {
      return this.method3(ImmutableList.method2((K)value1));
   }

   ImmutableList<K> method3(Iterable<? extends K> list1) {
      HashMap map2 = Maps.newHashMap();

      for (Object obj4 : list1) {
         this.collectTypes((K)obj4, map2);
      }

      return method4(map2, Ordering.method1().method9());
   }

   @CanIgnoreReturnValue
   private int collectTypes(K value1, Map<? super K, Integer> map2) {
      Integer number3 = (Integer)map2.get(value1);
      if (number3 != null) {
         return number3;
      }

      int number4 = this.getRawType((K)value1).isInterface() ? 1 : 0;

      for (Object obj6 : this.getInterfaces((K)value1)) {
         number4 = Math.max(number4, this.collectTypes((K)obj6, map2));
      }

      Object obj7 = this.getSuperclass((K)value1);
      if (obj7 != null) {
         number4 = Math.max(number4, this.collectTypes((K)obj7, map2));
      }

      map2.put(value1, number4 + 1);
      return number4 + 1;
   }

   private static <K, V> ImmutableList<K> method4(Map<K, V> map0, java.util.Comparator<? super V> comparator1) {
      Data17$4 data17$42 = new Data17$4(comparator1, map0);
      return data17$42.method17(map0.keySet());
   }

   abstract Class<?> getRawType(K value1);

   abstract Iterable<? extends K> getInterfaces(K value1);

   abstract @Nullable K getSuperclass(K value1);
}
