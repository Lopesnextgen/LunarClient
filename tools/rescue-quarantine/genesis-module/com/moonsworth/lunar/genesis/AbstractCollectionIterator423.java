package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multisets;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableMultiset;

@GwtCompatible
final class AbstractCollectionIterator423<E> extends ImmutableMultiset<E> {
   private final Map<E, Integer> field5;
   private final ImmutableList<Multiset.Extension<E>> field6;
   private final long field7;
   private transient ImmutableSet<E> field8;

   static <E> ImmutableMultiset<E> method1(Collection<? extends Multiset.Extension<? extends E>> var0) {
      Multiset.Extension[] var1 = var0.toArray(new Multiset.Extension[0]);
      HashMap var2 = Maps.newHashMapWithExpectedSize(var1.length);
      long var3 = 0L;

      for (int var5 = 0; var5 < var1.length; var5++) {
         Multiset.Extension var6 = var1[var5];
         int var7 = var6.getCount();
         var3 += var7;
         Object var8 = Preconditions.checkNotNull(var6.getElement());
         var2.put(var8, var7);
         if (!(var6 instanceof MixinHelper33$Data7)) {
            var1[var5] = Multisets.method4(var8, var7);
         }
      }

      return new AbstractCollectionIterator423<>(var2, ImmutableList.method21(var1), var3);
   }

   private AbstractCollectionIterator423(Map<E, Integer> var1, ImmutableList<Multiset.Extension<E>> var2, long var3) {
      this.field5 = var1;
      this.field6 = var2;
      this.field7 = var3;
   }

   @Override
   public int count(@Nullable Object var1) {
      return this.field5.getOrDefault(var1, 0);
   }

   @Override
   public ImmutableSet<E> method15() {
      ImmutableSet var1 = this.field8;
      return var1 == null ? (this.field8 = new ImmutableMultiset.Data<>(this.field6, this)) : var1;
   }

   @Override
   Multiset.Extension<E> method18(int var1) {
      return this.field6.get(var1);
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   public int size() {
      return MixinHelper122.saturatedCast(this.field7);
   }
}
