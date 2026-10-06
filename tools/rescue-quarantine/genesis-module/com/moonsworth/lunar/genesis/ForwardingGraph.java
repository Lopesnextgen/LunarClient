package com.moonsworth.lunar.genesis;

import java.util.Set;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.AbstractGraph;

abstract class ForwardingGraph<N> extends AbstractGraph<N> {
   ForwardingGraph() {
   }

   protected abstract BaseGraph<N> method3();

   public Set<N> nodes() {
      return this.method3().nodes();
   }

   protected long edgeCount() {
      return this.method3().edges().size();
   }

   public boolean isDirected() {
      return this.method3().isDirected();
   }

   public boolean allowsSelfLoops() {
      return this.method3().allowsSelfLoops();
   }

   public ElementOrder<N> method1() {
      return this.method3().method1();
   }

   @Override
   public ElementOrder<N> method2() {
      return this.method3().method2();
   }

   public Set<N> adjacentNodes(N value1) {
      return this.method3().adjacentNodes(value1);
   }

   public Set<N> predecessors(N value1) {
      return this.method3().predecessors(value1);
   }

   public Set<N> successors(N value1) {
      return this.method3().successors(value1);
   }

   @Override
   public Set<IterableBase<N>> incidentEdges(N value1) {
      return this.method3().incidentEdges(value1);
   }

   @Override
   public int degree(N value1) {
      return this.method3().degree(value1);
   }

   @Override
   public int inDegree(N value1) {
      return this.method3().inDegree(value1);
   }

   @Override
   public int outDegree(N value1) {
      return this.method3().outDegree(value1);
   }

   @Override
   public boolean hasEdgeConnecting(N value1, N value2) {
      return this.method3().hasEdgeConnecting(value1, value2);
   }

   @Override
   public boolean method3(IterableBase<N> iterablebase1) {
      return this.method3().method3(iterablebase1);
   }
}
