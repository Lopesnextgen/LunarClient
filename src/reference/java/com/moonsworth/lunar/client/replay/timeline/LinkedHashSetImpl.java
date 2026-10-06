package com.moonsworth.lunar.client.replay.timeline;

import java.util.LinkedHashSet;
import lombok.Generated;

public class LinkedHashSetImpl<E> extends LinkedHashSet<E> implements TransactionalSet<E> {
   private final UndoRedoManager field1;

   public void method1(Runnable runnable1) {
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field1, runnable1);
   }

   @Override
   public boolean add(E value1) {
      return this.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field1, this, value1);
   }

   @Override
   public boolean method3(E value1) {
      return super.add((E)value1);
   }

   @Override
   public boolean remove(Object obj1) {
      return this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(this.field1, this, obj1);
   }

   @Override
   public boolean method5(Object obj1) {
      return super.remove(obj1);
   }

   @Override
   public void clear() {
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field1, this);
   }

   @Override
   public void method7() {
      super.clear();
   }

   @Generated
   public LinkedHashSetImpl(UndoRedoManager nameplate21) {
      this.field1 = nameplate21;
   }
}
