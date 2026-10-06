package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@GwtCompatible(serializable = true)
final class Comparator12 extends Ordering<Object> implements Serializable {
   static final Comparator12 field3 = new Comparator12();
   private static final long field4 = 0L;

   @Override
   public int compare(@Nullable Object var1, @Nullable Object var2) {
      return 0;
   }

   @Override
   public <E> List<E> sortedCopy(Iterable<E> var1) {
      return Lists.newArrayList(var1);
   }

   @Override
   public <E> ImmutableList<E> method17(Iterable<E> var1) {
      return ImmutableList.method14(var1);
   }

   @Override
   public <S> Ordering<S> method9() {
      return this;
   }

   private Object readResolve() {
      return field3;
   }

   @Override
   public String toString() {
      return "Ordering.allEqual()";
   }
}
