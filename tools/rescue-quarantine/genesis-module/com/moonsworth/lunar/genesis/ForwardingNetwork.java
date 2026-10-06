package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.Network;
import com.google.common.graph.AbstractNetwork;

abstract class ForwardingNetwork<N, E> extends AbstractNetwork<N, E> {
   ForwardingNetwork() {
   }

   protected abstract Network<N, E> method4();

   public Set<N> nodes() {
      return this.method4().nodes();
   }

   public Set<E> edges() {
      return this.method4().edges();
   }

   public boolean isDirected() {
      return this.method4().isDirected();
   }

   public boolean allowsParallelEdges() {
      return this.method4().allowsParallelEdges();
   }

   public boolean allowsSelfLoops() {
      return this.method4().allowsSelfLoops();
   }

   public ElementOrder<N> method2() {
      return this.method4().method2();
   }

   public ElementOrder<E> method3() {
      return this.method4().method3();
   }

   public Set<N> adjacentNodes(N value1) {
      return this.method4().adjacentNodes(value1);
   }

   public Set<N> predecessors(N value1) {
      return this.method4().predecessors(value1);
   }

   public Set<N> successors(N value1) {
      return this.method4().successors(value1);
   }

   public Set<E> incidentEdges(N value1) {
      return this.method4().incidentEdges(value1);
   }

   public Set<E> inEdges(N value1) {
      return this.method4().inEdges(value1);
   }

   public Set<E> outEdges(N value1) {
      return this.method4().outEdges(value1);
   }

   public IterableBase<N> method4(E value1) {
      return this.method4().method4(value1);
   }

   public Set<E> adjacentEdges(E value1) {
      return this.method4().adjacentEdges(value1);
   }

   public int degree(N value1) {
      return this.method4().degree(value1);
   }

   public int inDegree(N value1) {
      return this.method4().inDegree(value1);
   }

   public int outDegree(N value1) {
      return this.method4().outDegree(value1);
   }

   public Set<E> edgesConnecting(N value1, N value2) {
      return this.method4().edgesConnecting(value1, value2);
   }

   public Set<E> method5(IterableBase<N> iterablebase1) {
      return this.method4().method5(iterablebase1);
   }

   public Optional<E> edgeConnecting(N value1, N value2) {
      return this.method4().edgeConnecting(value1, value2);
   }

   public Optional<E> method6(IterableBase<N> iterablebase1) {
      return this.method4().method6(iterablebase1);
   }

   public E edgeConnectingOrNull(N value1, N value2) {
      return (E)this.method4().edgeConnectingOrNull(value1, value2);
   }

   public E method7(IterableBase<N> iterablebase1) {
      return (E)this.method4().method7(iterablebase1);
   }

   public boolean hasEdgeConnecting(N value1, N value2) {
      return this.method4().hasEdgeConnecting(value1, value2);
   }

   public boolean method8(IterableBase<N> iterablebase1) {
      return this.method4().method8(iterablebase1);
   }
}
