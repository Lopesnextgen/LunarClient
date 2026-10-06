package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;

final class AbstractSetImpl<E> extends AbstractSet<E> {
   private final Map<?, E> field1;
   private final Object field2;

   AbstractSetImpl(Map<?, E> var1, Object var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   public MixinHelperIterator3<E> method1() {
      Object var1 = this.getConnectingEdge();
      return var1 == null ? ImmutableSet.<E>method3().method1() : Iterators.method20((E)var1);
   }

   @Override
   public int size() {
      return this.getConnectingEdge() == null ? 0 : 1;
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      Object var2 = this.getConnectingEdge();
      return var2 != null && var2.equals(var1);
   }

   private @Nullable E getConnectingEdge() {
      return this.field1.get(this.field2);
   }
}
