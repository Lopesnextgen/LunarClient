package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@GwtCompatible(serializable = true)
final class AllEqualOrdering extends Ordering<Object> implements Serializable {
   static final AllEqualOrdering field3 = new AllEqualOrdering();
   private static final long field4 = 0L;

   AllEqualOrdering() {
   }

   public int compare(@Nullable Object obj1, @Nullable Object obj2) {
      return 0;
   }

   public <E> List<E> sortedCopy(Iterable<E> list1) {
      return Lists.newArrayList(list1);
   }

   public <E> ImmutableList<E> method17(Iterable<E> list1) {
      return ImmutableList.method14(list1);
   }

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
