package com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface Nameplate3<E> {
   default void method1(Nameplate2 var1, Runnable var2) {
      if (!var1.method3()) {
         var1.method1();
         var2.run();
         var1.endBatch();
      } else {
         var2.run();
      }
   }

   default boolean method2(Nameplate2 var1, Nameplate3<E> var2, E var3) {
      var1.method4(() -> var2.method5(var3), () -> var2.method3(var3));
      return var2.method3(var3);
   }

   boolean method3(E var1);

   default boolean method4(Nameplate2 var1, Nameplate3<E> var2, E var3) {
      var1.method4(() -> var2.method3(var3), () -> var2.method5(var3));
      return var2.method5(var3);
   }

   boolean method5(Object var1);

   default <SET extends Nameplate3<E> & Set<E>> void method6(Nameplate2 var1, SET var2) {
      HashSet var3 = new HashSet((Collection<? extends E>)var2);
      var1.method4(() -> ((Set)var2).addAll(var3), ((Set)var2)::clear);
      var2.method7();
   }

   void method7();
}
