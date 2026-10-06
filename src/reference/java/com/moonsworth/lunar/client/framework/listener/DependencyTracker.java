package com.moonsworth.lunar.client.framework.listener;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.client.event.LunarEventBus;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Supplier;

public class DependencyTracker {
   private static Set<DependencyValue> field1 = null;
   private final Set<DependencyValue> field2 = Sets.newSetFromMap(new IdentityHashMap());
   private final Runnable field3;

   private DependencyTracker(Runnable runnable1) {
      this.field3 = runnable1;
   }

   public static void method1(DependencyValue guirewindhandlers20) {
      if (field1 != null) {
         if (LunarEventBus.method29().method26()) {
            field1.add(guirewindhandlers20);
         }
      }
   }

   public static DependencyTracker method2(Runnable runnable0) {
      return new DependencyTracker(runnable0);
   }

   public <T> T method3(Supplier<T> supplier1) {
      return LunarEventBus.method29().method25(() -> {
         Set set2 = Sets.newSetFromMap(new IdentityHashMap());
         field1 = set2;
         Object obj3 = supplier1.get();
         field1 = null;
         Iterator iterator4 = this.field2.iterator();

         while (iterator4.hasNext()) {
            DependencyValue guirewindhandlers25 = (DependencyValue)iterator4.next();
            if (!set2.contains(guirewindhandlers25)) {
               guirewindhandlers25.method2(this.field3);
               iterator4.remove();
            }
         }

         for (DependencyValue guirewindhandlers27 : set2) {
            if (this.field2.add(guirewindhandlers27)) {
               guirewindhandlers27.method1(this.field3);
            }
         }

         return (T)obj3;
      });
   }
}
