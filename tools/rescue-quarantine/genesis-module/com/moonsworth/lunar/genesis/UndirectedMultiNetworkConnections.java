package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.common.collect.HashMultiset;
import com.google.common.base.Preconditions;

final class UndirectedMultiNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {
   @LazyInit
   private transient Reference<Multiset<N>> adjacentNodesReference;

   private UndirectedMultiNetworkConnections(Map<E, N> map1) {
      super(map1);
   }

   static <N, E> UndirectedMultiNetworkConnections<N, E> method1() {
      return new UndirectedMultiNetworkConnections<>(new HashMap<>(2, 1.0F));
   }

   static <N, E> UndirectedMultiNetworkConnections<N, E> method2(Map<E, N> map0) {
      return new UndirectedMultiNetworkConnections<>(ImmutableMap.method9(map0));
   }

   public Set<N> adjacentNodes() {
      return Collections.unmodifiableSet(this.method3().elementSet());
   }

   private Multiset<N> method3() {
      Object obj1 = getReference(this.adjacentNodesReference);
      if (obj1 == null) {
         obj1 = HashMultiset.method3(this.HHROHROHOCHIRCCRIIHCHOIOIOOIIC.values());
         this.adjacentNodesReference = new SoftReference<>((Multiset<N>)obj1);
      }

      return (Multiset<N>)obj1;
   }

   public Set<E> edgesConnecting(N value1) {
      return new MixinHelper1132$1(this, this.HHROHROHOCHIRCCRIIHCHOIOIOOIIC, value1, value1);
   }

   @Override
   public N removeInEdge(E value1, boolean flag2) {
      return !flag2 ? this.removeOutEdge((E)value1) : null;
   }

   @Override
   public N removeOutEdge(E value1) {
      Object obj2 = super.removeOutEdge((E)value1);
      Multiset collectionextension3 = getReference(this.adjacentNodesReference);
      if (collectionextension3 != null) {
         Preconditions.checkState(collectionextension3.remove(obj2));
      }

      return (N)obj2;
   }

   @Override
   public void addInEdge(E value1, N value2, boolean flag3) {
      if (!flag3) {
         this.addOutEdge((E)value1, (N)value2);
      }
   }

   @Override
   public void addOutEdge(E value1, N value2) {
      super.addOutEdge((E)value1, (N)value2);
      Multiset collectionextension3 = getReference(this.adjacentNodesReference);
      if (collectionextension3 != null) {
         Preconditions.checkState(collectionextension3.add(value2));
      }
   }

   private static <T> @Nullable T getReference(@Nullable Reference<T> reference0) {
      return (T)(reference0 == null ? null : reference0.get());
   }
}
