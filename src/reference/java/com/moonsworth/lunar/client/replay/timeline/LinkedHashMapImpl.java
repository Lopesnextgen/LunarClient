package com.moonsworth.lunar.client.replay.timeline;

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import lombok.Generated;

public class LinkedHashMapImpl<K, V> extends LinkedHashMap<K, V> implements UndoRedoStore<K, V> {
   private final UndoRedoManager field1;
   private Consumer<K> field2;

   public void method1(Runnable runnable1) {
      this.method3(this.field1, runnable1);
   }

   @Override
   public V get(Object obj1) {
      return super.get(obj1);
   }

   @Override
   public V put(K value1, V value2) {
      return (V)this.method3(this.field1, this, value1, value2);
   }

   @Override
   public V method3(K value1, V value2) {
      return super.put((K)value1, (V)value2);
   }

   @Override
   public V remove(Object obj1) {
      return (V)this.method3(this.field1, this, obj1);
   }

   @Override
   public V method5(Object obj1) {
      if (this.field2 != null) {
         this.field2.accept((K)obj1);
      }

      return super.remove(obj1);
   }

   @Override
   public void clear() {
      this.method3(this.field1, this);
   }

   @Override
   public void method7() {
      super.clear();
   }

   @Generated
   public LinkedHashMapImpl(UndoRedoManager nameplate21) {
      this.field1 = nameplate21;
   }

   @Generated
   public void method5(Consumer<K> consumer1) {
      this.field2 = consumer1;
   }
}
