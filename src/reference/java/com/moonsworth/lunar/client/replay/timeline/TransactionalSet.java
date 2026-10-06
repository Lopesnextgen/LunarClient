package com.moonsworth.lunar.client.replay.timeline;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface TransactionalSet<E> {
   default void method1(UndoRedoManager nameplate21, Runnable runnable2) {
      if (!nameplate21.method3()) {
         nameplate21.method1();
         runnable2.run();
         nameplate21.endBatch();
      } else {
         runnable2.run();
      }
   }

   default boolean method2(UndoRedoManager nameplate21, TransactionalSet<E> nameplate32, E value3) {
      nameplate21.method4(() -> nameplate32.method5(value3), () -> nameplate32.method3(value3));
      return nameplate32.method3(value3);
   }

   boolean method3(E value1);

   default boolean method4(UndoRedoManager nameplate21, TransactionalSet<E> nameplate32, E value3) {
      nameplate21.method4(() -> nameplate32.method3(value3), () -> nameplate32.method5(value3));
      return nameplate32.method5(value3);
   }

   boolean method5(Object obj1);

   default <SET extends TransactionalSet<E> & Set<E>> void method6(UndoRedoManager nameplate21, SET set2) {
      HashSet set3 = new HashSet((Collection<? extends E>)set2);
      nameplate21.method4(() -> ((Set)set2).addAll(set3), ((Set)set2)::clear);
      set2.method7();
   }

   void method7();
}
