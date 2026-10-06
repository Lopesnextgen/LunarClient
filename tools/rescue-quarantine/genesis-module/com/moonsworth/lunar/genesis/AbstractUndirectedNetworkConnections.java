package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import com.google.common.base.Preconditions;

abstract class AbstractUndirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
   protected final Map<E, N> field1;

   protected AbstractUndirectedNetworkConnections(Map<E, N> map1) {
      this.field1 = (Map<E, N>)Preconditions.checkNotNull(map1);
   }

   public Set<N> predecessors() {
      return this.adjacentNodes();
   }

   public Set<N> successors() {
      return this.adjacentNodes();
   }

   public Set<E> incidentEdges() {
      return Collections.unmodifiableSet(this.field1.keySet());
   }

   public Set<E> inEdges() {
      return this.incidentEdges();
   }

   public Set<E> outEdges() {
      return this.incidentEdges();
   }

   public N adjacentNode(E value1) {
      return (N)Preconditions.checkNotNull(this.field1.get(value1));
   }

   public N removeInEdge(E value1, boolean flag2) {
      return !flag2 ? this.removeOutEdge((E)value1) : null;
   }

   public N removeOutEdge(E value1) {
      Object obj2 = this.field1.remove(value1);
      return (N)Preconditions.checkNotNull(obj2);
   }

   public void addInEdge(E value1, N value2, boolean flag3) {
      if (!flag3) {
         this.addOutEdge((E)value1, (N)value2);
      }
   }

   public void addOutEdge(E value1, N value2) {
      Object obj3 = this.field1.put((E)value1, (N)value2);
      Preconditions.checkState(obj3 == null);
   }
}
