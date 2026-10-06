package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import com.google.common.graph.Network;

class Graphs$TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
   private final Network<N, E> field1;

   Graphs$TransposedNetwork(Network<N, E> mixinhelper631) {
      this.field1 = mixinhelper631;
   }

   protected Network<N, E> method4() {
      return this.field1;
   }

   public Set<N> predecessors(N value1) {
      return this.method4().successors(value1);
   }

   public Set<N> successors(N value1) {
      return this.method4().predecessors(value1);
   }

   public int inDegree(N value1) {
      return this.method4().outDegree(value1);
   }

   public int outDegree(N value1) {
      return this.method4().inDegree(value1);
   }

   public Set<E> inEdges(N value1) {
      return this.method4().outEdges(value1);
   }

   public Set<E> outEdges(N value1) {
      return this.method4().inEdges(value1);
   }

   public IterableBase<N> method4(E value1) {
      IterableBase iterablebase2 = this.method4().method4(value1);
      return IterableBase.method4(this.field1, iterablebase2.method6(), iterablebase2.method5());
   }

   public Set<E> edgesConnecting(N value1, N value2) {
      return this.method4().edgesConnecting(value2, value1);
   }

   public Set<E> method5(IterableBase<N> iterablebase1) {
      return this.method4().method5(MixinHelper9_5.method10(iterablebase1));
   }

   public Optional<E> edgeConnecting(N value1, N value2) {
      return this.method4().edgeConnecting(value2, value1);
   }

   public Optional<E> method6(IterableBase<N> iterablebase1) {
      return this.method4().method6(MixinHelper9_5.method10(iterablebase1));
   }

   public E edgeConnectingOrNull(N value1, N value2) {
      return (E)this.method4().edgeConnectingOrNull(value2, value1);
   }

   public E method7(IterableBase<N> iterablebase1) {
      return (E)this.method4().method7(MixinHelper9_5.method10(iterablebase1));
   }

   public boolean hasEdgeConnecting(N value1, N value2) {
      return this.method4().hasEdgeConnecting(value2, value1);
   }

   public boolean method8(IterableBase<N> iterablebase1) {
      return this.method4().method8(MixinHelper9_5.method10(iterablebase1));
   }
}
