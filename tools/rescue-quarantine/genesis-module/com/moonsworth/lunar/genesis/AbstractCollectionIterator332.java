package com.moonsworth.lunar.genesis;

import java.util.function.Consumer;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible(emulated = true)
class AbstractCollectionIterator332<E> extends AbstractCollectionIterator33<E> {
   private final ImmutableCollection<E> field3;
   private final ImmutableList<? extends E> field4;

   AbstractCollectionIterator332(ImmutableCollection<E> var1, ImmutableList<? extends E> var2) {
      this.field3 = var1;
      this.field4 = var2;
   }

   AbstractCollectionIterator332(ImmutableCollection<E> var1, Object[] var2) {
      this(var1, ImmutableList.method21(var2));
   }

   @Override
   ImmutableCollection<E> method4() {
      return this.field3;
   }

   ImmutableList<? extends E> method5() {
      return this.field4;
   }

   @Override
   public MixinHelperIterator34<E> method25(int var1) {
      return (MixinHelperIterator34<E>)this.field4.method25(var1);
   }

   @Annotation3
   @Override
   public void forEach(Consumer<? super E> var1) {
      this.field4.forEach(var1);
   }

   @Annotation3
   @Override
   int copyIntoArray(Object[] var1, int var2) {
      return this.field4.copyIntoArray(var1, var2);
   }

   @Override
   Object[] internalArray() {
      return this.field4.internalArray();
   }

   @Override
   int internalArrayStart() {
      return this.field4.internalArrayStart();
   }

   @Override
   int internalArrayEnd() {
      return this.field4.internalArrayEnd();
   }

   @Override
   public E get(int var1) {
      return (E)this.field4.get(var1);
   }
}
