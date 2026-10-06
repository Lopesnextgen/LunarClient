package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.ImmutableSortedMultiset;

@Annotation3
final class AbstractCollectionIterator42223<E> extends ImmutableSortedMultiset<E> {
   private final transient ImmutableSortedMultiset<E> field6;

   AbstractCollectionIterator42223(ImmutableSortedMultiset<E> var1) {
      this.field6 = var1;
   }

   @Override
   public int count(@Nullable Object var1) {
      return this.field6.count(var1);
   }

   @Override
   public Multiset.Extension<E> method3() {
      return this.field6.IRRICCIOCHOCROOHCHIIIHCCHRORRR();
   }

   @Override
   public Multiset.Extension<E> method4() {
      return this.field6.CHORCCOHOCOIRICHICICHHHRCROHIO();
   }

   @Override
   public int size() {
      return this.field6.size();
   }

   @Override
   public ImmutableSortedSet<E> method18() {
      return this.field6.method18().method31();
   }

   @Override
   Multiset.Extension<E> method18(int var1) {
      return (Multiset.Extension<E>)this.field6.method15().method2().method29().get(var1);
   }

   @Override
   public ImmutableSortedMultiset<E> method20() {
      return this.field6;
   }

   @Override
   public ImmutableSortedMultiset<E> method20(E var1, MixinHelperType_3 var2) {
      return this.field6.method22((E)var1, var2).method20();
   }

   @Override
   public ImmutableSortedMultiset<E> method22(E var1, MixinHelperType_3 var2) {
      return this.field6.method20((E)var1, var2).method20();
   }

   @Override
   boolean isPartialView() {
      return this.field6.isPartialView();
   }
}
