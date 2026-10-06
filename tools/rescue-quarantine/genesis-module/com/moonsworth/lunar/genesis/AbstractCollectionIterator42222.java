package com.moonsworth.lunar.genesis;

import java.util.function.ObjIntConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Multisets;
import com.google.common.collect.Multiset;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.common.base.Preconditions;

@Annotation3
final class AbstractCollectionIterator42222<E> extends ImmutableSortedMultiset<E> {
   private static final long[] field6 = new long[]{0L};
   static final ImmutableSortedMultiset<Comparable> field7 = new AbstractCollectionIterator42222<>(Ordering.method1());
   @Annotation4
   final transient AbstractCollectionIterator5624<E> field8;
   private final transient long[] field9;
   private final transient int field10;
   private final transient int field11;

   AbstractCollectionIterator42222(java.util.Comparator<? super E> var1) {
      this.field8 = ImmutableSortedSet.method1(var1);
      this.field9 = field6;
      this.field10 = 0;
      this.field11 = 0;
   }

   AbstractCollectionIterator42222(AbstractCollectionIterator5624<E> var1, long[] var2, int var3, int var4) {
      this.field8 = var1;
      this.field9 = var2;
      this.field10 = var3;
      this.field11 = var4;
   }

   private int getCount(int var1) {
      return (int)(this.field9[this.field10 + var1 + 1] - this.field9[this.field10 + var1]);
   }

   @Override
   Multiset.Extension<E> method18(int var1) {
      return Multisets.method4((E)this.field8.method17().get(var1), this.getCount(var1));
   }

   @Override
   public void forEachEntry(ObjIntConsumer<? super E> var1) {
      Preconditions.checkNotNull(var1);

      for (int var2 = 0; var2 < this.field11; var2++) {
         var1.accept(this.field8.method17().get(var2), this.getCount(var2));
      }
   }

   @Override
   public Multiset.Extension<E> method3() {
      return this.isEmpty() ? null : this.method18(0);
   }

   @Override
   public Multiset.Extension<E> method4() {
      return this.isEmpty() ? null : this.method18(this.field11 - 1);
   }

   @Override
   public int count(@Nullable Object var1) {
      int var2 = this.field8.indexOf(var1);
      return var2 >= 0 ? this.getCount(var2) : 0;
   }

   @Override
   public int size() {
      long var1 = this.field9[this.field10 + this.field11] - this.field9[this.field10];
      return MixinHelper122.saturatedCast(var1);
   }

   @Override
   public ImmutableSortedSet<E> method18() {
      return this.field8;
   }

   @Override
   public ImmutableSortedMultiset<E> method20(E var1, MixinHelperType_3 var2) {
      return this.method7(0, this.field8.headIndex((E)var1, Preconditions.checkNotNull(var2) == MixinHelperType_3.CLOSED));
   }

   @Override
   public ImmutableSortedMultiset<E> method22(E var1, MixinHelperType_3 var2) {
      return this.method7(this.field8.tailIndex((E)var1, Preconditions.checkNotNull(var2) == MixinHelperType_3.CLOSED), this.field11);
   }

   ImmutableSortedMultiset<E> method7(int var1, int var2) {
      Preconditions.checkPositionIndexes(var1, var2, this.field11);
      if (var1 == var2) {
         return RIIIOHCCHRRRORICCHIIHHOORIIOIR(this.comparator());
      }

      if (var1 == 0 && var2 == this.field11) {
         return this;
      }

      AbstractCollectionIterator5624 var3 = this.field8.method6(var1, var2);
      return new AbstractCollectionIterator42222<>(var3, this.field9, this.field10 + var1, var2 - var1);
   }

   @Override
   boolean isPartialView() {
      return this.field10 > 0 || this.field11 < this.field9.length - 1;
   }
}
