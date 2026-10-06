package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.function.Consumer;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableCollection;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true)
abstract class AbstractCollectionIterator53<E> extends ImmutableSet<E> {
   abstract E get(int var1);

   @Override
   public MixinHelperIterator3<E> method1() {
      return this.method17().method1();
   }

   @Override
   public Spliterator<E> spliterator() {
      return MixinHelper3_5.indexed(this.size(), 1297, this::get);
   }

   @Override
   public void forEach(Consumer<? super E> var1) {
      Preconditions.checkNotNull(var1);
      int var2 = this.size();

      for (int var3 = 0; var3 < var2; var3++) {
         var1.accept(this.get(var3));
      }
   }

   @Annotation3
   @Override
   int copyIntoArray(Object[] var1, int var2) {
      return this.method17().copyIntoArray(var1, var2);
   }

   @Override
   ImmutableList<E> method17() {
      return new AbstractCollectionIterator33<E>() {
         @Override
         public E get(int var1) {
            return (E)AbstractCollectionIterator53.this.get(var1);
         }

         @Override
         boolean isPartialView() {
            return AbstractCollectionIterator53.this.isPartialView();
         }

         @Override
         public int size() {
            return AbstractCollectionIterator53.this.size();
         }

         @Override
         ImmutableCollection<E> method4() {
            return AbstractCollectionIterator53.this;
         }
      };
   }
}
