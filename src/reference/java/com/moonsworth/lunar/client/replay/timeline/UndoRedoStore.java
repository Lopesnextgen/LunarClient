package com.moonsworth.lunar.client.replay.timeline;

import java.util.HashMap;
import java.util.Map;

public interface UndoRedoStore<K, V> {
   default void method1(UndoRedoManager nameplate21, Runnable runnable2) {
      if (!nameplate21.method3()) {
         nameplate21.method1();
         runnable2.run();
         nameplate21.endBatch();
      } else {
         runnable2.run();
      }
   }

   V get(K value1);

   default V method2(UndoRedoManager nameplate21, UndoRedoStore<K, V> nameplate2, K value3, V value4) {
      nameplate21.method4(() -> nameplate2.method5(value3), () -> nameplate2.method3(value3, value4));
      nameplate2.method3(value3, value4);
      return (V)value4;
   }

   V method3(K value1, V value2);

   default V method4(UndoRedoManager nameplate21, UndoRedoStore<K, V> nameplate2, K value3) {
      Object obj4 = nameplate2.get(value3);
      if (obj4 == null) {
         return null;
      }

      nameplate21.method4(() -> nameplate2.method3(value3, obj4), () -> nameplate2.method5(value3));
      nameplate2.method5(value3);
      return (V)obj4;
   }

   V method5(Object obj1);

   default <MAP extends UndoRedoStore<K, V> & Map<K, V>> void method6(UndoRedoManager nameplate21, MAP map2) {
      HashMap map3 = new HashMap((Map<? extends K, ? extends V>)map2);
      nameplate21.method4(() -> ((Map)map2).putAll(map3), ((Map)map2)::clear);
      map2.method7();
   }

   void method7();
}
