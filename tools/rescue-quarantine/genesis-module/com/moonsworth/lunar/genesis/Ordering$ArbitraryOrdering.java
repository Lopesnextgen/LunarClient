package com.moonsworth.lunar.genesis;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.common.eventbus.Subscribe;

@Subscribe
class Ordering$ArbitraryOrdering extends Comparator<Object> {
   private final AtomicInteger field3 = new AtomicInteger(0);
   private final ConcurrentMap<Object, Integer> field4 = CollectPlatform.method1(new MixinHelper26()).makeMap();

   Ordering$ArbitraryOrdering() {
   }

   private Integer getUid(Object obj1) {
      Integer number2 = this.field4.get(obj1);
      if (number2 == null) {
         number2 = this.field3.getAndIncrement();
         Integer number3 = this.field4.putIfAbsent(obj1, number2);
         if (number3 != null) {
            number2 = number3;
         }
      }

      return number2;
   }

   public int compare(Object obj1, Object obj2) {
      if (obj1 == obj2) {
         return 0;
      } else if (obj1 == null) {
         return -1;
      } else if (obj2 == null) {
         return 1;
      } else {
         int number3 = this.identityHashCode(obj1);
         int number4 = this.identityHashCode(obj2);
         if (number3 != number4) {
            return number3 < number4 ? -1 : 1;
         } else {
            int number5 = this.getUid(obj1).compareTo(this.getUid(obj2));
            if (number5 == 0) {
               throw new AssertionError();
            } else {
               return number5;
            }
         }
      }
   }

   public String toString() {
      return "Ordering.arbitrary()";
   }

   int identityHashCode(Object obj1) {
      return System.identityHashCode(obj1);
   }
}
