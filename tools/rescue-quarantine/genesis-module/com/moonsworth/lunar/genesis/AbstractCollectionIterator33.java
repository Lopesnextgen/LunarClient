package com.moonsworth.lunar.genesis;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible(serializable = true, emulated = true)
abstract class AbstractCollectionIterator33<E> extends ImmutableList<E> {
   abstract ImmutableCollection<E> method4();

   @Override
   public boolean contains(Object var1) {
      return this.method4().contains(var1);
   }

   @Override
   public int size() {
      return this.method4().size();
   }

   @Override
   public boolean isEmpty() {
      return this.method4().isEmpty();
   }

   @Override
   boolean isPartialView() {
      return this.method4().isPartialView();
   }

   @Annotation3
   private void readObject(ObjectInputStream var1) {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @Annotation3
   @Override
   Object writeReplace() {
      return new AbstractCollectionIterator33.Data(this.method4());
   }

   @Annotation3
   static class Data implements Serializable {
      final ImmutableCollection<?> field1;
      private static final long field2 = 0L;

      Data(ImmutableCollection<?> var1) {
         this.field1 = var1;
      }

      Object readResolve() {
         return this.field1.method2();
      }
   }
}
