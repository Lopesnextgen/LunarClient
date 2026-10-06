package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;

final class EdgesConnecting<E> extends AbstractSet<E> {
   private final Map<?, E> field1;
   private final Object field2;

   EdgesConnecting(Map<?, E> map1, Object obj2) {
      this.field1 = (Map<?, E>)Preconditions.checkNotNull(map1);
      this.field2 = Preconditions.checkNotNull(obj2);
   }

   public UnmodifiableIterator<E> method1() {
      Object obj1 = this.getConnectingEdge();
      return obj1 == null ? ImmutableSet.method3().method1() : Iterators.method20(obj1);
   }

   @Override
   public int size() {
      return this.getConnectingEdge() == null ? 0 : 1;
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      Object obj2 = this.getConnectingEdge();
      return obj2 != null && obj2.equals(obj1);
   }

   private @Nullable E getConnectingEdge() {
      return this.field1.get(this.field2);
   }
}
