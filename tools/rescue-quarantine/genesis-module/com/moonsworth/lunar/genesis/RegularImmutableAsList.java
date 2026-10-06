package com.moonsworth.lunar.genesis;

import java.util.function.Consumer;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible(emulated = true)
class RegularImmutableAsList<E> extends AbstractCollectionIterator33<E> {
   private final ImmutableCollection<E> field3;
   private final ImmutableList<? extends E> field4;

   RegularImmutableAsList(ImmutableCollection<E> abstractcollectioniterator1, ImmutableList<? extends E> abstractcollectioniterator32) {
      this.field3 = abstractcollectioniterator1;
      this.field4 = abstractcollectioniterator32;
   }

   RegularImmutableAsList(ImmutableCollection<E> abstractcollectioniterator1, Object[] items2) {
      this(abstractcollectioniterator1, ImmutableList.method21(items2));
   }

   ImmutableCollection<E> method4() {
      return this.field3;
   }

   ImmutableList<? extends E> method5() {
      return this.field4;
   }

   public UnmodifiableListIterator<E> method25(int number1) {
      return this.field4.method25(number1);
   }

   @GwtIncompatible
   public void forEach(Consumer<? super E> consumer1) {
      this.field4.forEach(consumer1);
   }

   @GwtIncompatible
   int copyIntoArray(Object[] items1, int number2) {
      return this.field4.copyIntoArray(items1, number2);
   }

   Object[] internalArray() {
      return this.field4.internalArray();
   }

   int internalArrayStart() {
      return this.field4.internalArrayStart();
   }

   int internalArrayEnd() {
      return this.field4.internalArrayEnd();
   }

   public E get(int index1) {
      return (E)this.field4.get(index1);
   }
}
