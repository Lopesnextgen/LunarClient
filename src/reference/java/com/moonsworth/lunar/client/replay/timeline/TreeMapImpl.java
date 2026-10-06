package com.moonsworth.lunar.client.replay.timeline;

import java.util.Comparator;
import java.util.TreeMap;
import lombok.Generated;

public class TreeMapImpl<K, V> extends TreeMap<K, V> implements UndoRedoStore<K, V> {
   private final UndoRedoManager field1;

   public TreeMapImpl(UndoRedoManager nameplate21, Comparator<K> comparator2) {
      super(comparator2);
      this.field1 = nameplate21;
   }

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
   public TreeMapImpl(UndoRedoManager nameplate21) {
      this.field1 = nameplate21;
   }

   @Generated
   public UndoRedoManager method5() {
      return this.field1;
   }
}
