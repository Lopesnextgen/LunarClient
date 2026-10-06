package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableList;

@GwtCompatible(emulated = true)
final class AbstractCollectionIterator3322<E> extends AbstractCollectionIterator332<E> implements IterableExtension<E> {
   AbstractCollectionIterator3322(ImmutableSortedSet<E> var1, ImmutableList<E> var2) {
      super(var1, var2);
   }

   ImmutableSortedSet<E> method6() {
      return (ImmutableSortedSet<E>)super.method4();
   }

   @Override
   public java.util.Comparator<? super E> comparator() {
      return this.method6().comparator();
   }

   @Annotation3
   @Override
   public int indexOf(@Nullable Object var1) {
      int var2 = this.method6().indexOf(var1);
      return var2 >= 0 && this.get(var2).equals(var1) ? var2 : -1;
   }

   @Annotation3
   @Override
   public int lastIndexOf(@Nullable Object var1) {
      return this.indexOf(var1);
   }

   @Override
   public boolean contains(Object var1) {
      return this.indexOf(var1) >= 0;
   }

   @Annotation3
   @Override
   ImmutableList<E> method27(int var1, int var2) {
      ImmutableList var3 = super.method27(var1, var2);
      return new AbstractCollectionIterator5624<>(var3, this.comparator()).method17();
   }

   @Override
   public Spliterator<E> spliterator() {
      return MixinHelper3_5.indexed(this.size(), 1301, this.method5()::get, this.comparator());
   }
}
