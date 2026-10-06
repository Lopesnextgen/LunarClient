package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.ImmutableMap;
import com.google.common.graph.ElementOrder;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;

final class MixinHelper33_2<N, V> implements MixinHelper3_13<N, V> {
   private final Map<N, V> field1;

   private MixinHelper33_2(Map<N, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   static <N, V> MixinHelper33_2<N, V> method1(ElementOrder<N> var0) {
      switch (var0.method6()) {
         case UNORDERED:
            return new MixinHelper33_2<>(new HashMap<>(2, 1.0F));
         case STABLE:
            return new MixinHelper33_2<>(new LinkedHashMap<>(2, 1.0F));
         default:
            throw new AssertionError(var0.method6());
      }
   }

   static <N, V> MixinHelper33_2<N, V> method2(Map<N, V> var0) {
      return new MixinHelper33_2<>(ImmutableMap.method9(var0));
   }

   @Override
   public Set<N> adjacentNodes() {
      return Collections.unmodifiableSet(this.field1.keySet());
   }

   @Override
   public Set<N> predecessors() {
      return this.adjacentNodes();
   }

   @Override
   public Set<N> successors() {
      return this.adjacentNodes();
   }

   @Override
   public Iterator<IterableBase<N>> incidentEdgeIterator(final N var1) {
      return Iterators.method17(this.field1.keySet().iterator(), new MixinHelper24_2<N, IterableBase<N>>() {
         public IterableBase<N> method1(N var1x) {
            return IterableBase.method2((N)var1, (N)var1x);
         }
      });
   }

   @Override
   public V value(N var1) {
      return this.field1.get(var1);
   }

   @Override
   public void removePredecessor(N var1) {
      Object var2 = this.removeSuccessor((N)var1);
   }

   @Override
   public V removeSuccessor(N var1) {
      return this.field1.remove(var1);
   }

   @Override
   public void addPredecessor(N var1, V var2) {
      Object var3 = this.addSuccessor((N)var1, (V)var2);
   }

   @Override
   public V addSuccessor(N var1, V var2) {
      return this.field1.put((N)var1, (V)var2);
   }
}
