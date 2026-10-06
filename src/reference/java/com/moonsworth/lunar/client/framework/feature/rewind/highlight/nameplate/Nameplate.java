package com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate;

import java.util.HashMap;
import java.util.Map;

public interface Nameplate<K, V> {
   default void method1(Nameplate2 var1, Runnable var2) {
      if (!var1.method3()) {
         var1.method1();
         var2.run();
         var1.endBatch();
      } else {
         var2.run();
      }
   }

   V get(K var1);

   default V method2(Nameplate2 var1, Nameplate<K, V> var2, K var3, V var4) {
      var1.method4(() -> var2.method5(var3), () -> var2.method3(var3, var4));
      var2.method3(var3, var4);
      return (V)var4;
   }

   V method3(K var1, V var2);

   default V method4(Nameplate2 var1, Nameplate<K, V> var2, K var3) {
      Object var4 = var2.get(var3);
      if (var4 == null) {
         return null;
      }

      var1.method4(() -> var2.method3(var3, var4), () -> var2.method5(var3));
      var2.method5(var3);
      return (V)var4;
   }

   V method5(Object var1);

   default <MAP extends Nameplate<K, V> & Map<K, V>> void method6(Nameplate2 var1, MAP var2) {
      HashMap var3 = new HashMap((Map<? extends K, ? extends V>)var2);
      var1.method4(() -> ((Map)var2).putAll(var3), ((Map)var2)::clear);
      var2.method7();
   }

   void method7();
}
