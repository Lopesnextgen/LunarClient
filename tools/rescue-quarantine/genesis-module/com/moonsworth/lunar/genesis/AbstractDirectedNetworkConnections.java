package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.graph.Graphs;

abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
   protected final Map<E, N> field1;
   protected final Map<E, N> field2;
   private int selfLoopCount;

   protected AbstractDirectedNetworkConnections(Map<E, N> map1, Map<E, N> map2, int number3) {
      this.field1 = Preconditions.checkNotNull(map1);
      this.field2 = Preconditions.checkNotNull(map2);
      this.selfLoopCount = Graphs.checkNonNegative(number3);
      Preconditions.checkState(number3 <= map1.size() && number3 <= map2.size());
   }

   @Override
   public Set<N> adjacentNodes() {
      return Sets.method3(this.predecessors(), this.successors());
   }

   @Override
   public Set<E> incidentEdges() {
      return new MixinHelper112$1(this);
   }

   @Override
   public Set<E> inEdges() {
      return Collections.unmodifiableSet(this.field1.keySet());
   }

   @Override
   public Set<E> outEdges() {
      return Collections.unmodifiableSet(this.field2.keySet());
   }

   @Override
   public N adjacentNode(E value1) {
      return Preconditions.checkNotNull(this.field2.get(value1));
   }

   @Override
   public N removeInEdge(E value1, boolean flag2) {
      if (flag2) {
         Graphs.checkNonNegative(--this.selfLoopCount);
      }

      Object obj3 = this.field1.remove(value1);
      return Preconditions.checkNotNull((N)obj3);
   }

   @Override
   public N removeOutEdge(E value1) {
      Object obj2 = this.field2.remove(value1);
      return Preconditions.checkNotNull((N)obj2);
   }

   @Override
   public void addInEdge(E value1, N value2, boolean flag3) {
      Preconditions.checkNotNull(value1);
      Preconditions.checkNotNull(value2);
      if (flag3) {
         Graphs.checkPositive(++this.selfLoopCount);
      }

      Object obj4 = this.field1.put((E)value1, (N)value2);
      Preconditions.checkState(obj4 == null);
   }

   @Override
   public void addOutEdge(E value1, N value2) {
      Preconditions.checkNotNull(value1);
      Preconditions.checkNotNull(value2);
      Object obj3 = this.field2.put((E)value1, (N)value2);
      Preconditions.checkState(obj3 == null);
   }
}
