package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.AbstractValueGraph;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.ValueGraph;

abstract class ForwardingValueGraph<N, V> extends AbstractValueGraph<N, V> {
   ForwardingValueGraph() {
   }

   protected abstract ValueGraph<N, V> method4();

   public Set<N> nodes() {
      return this.method4().nodes();
   }

   protected long edgeCount() {
      return this.method4().edges().size();
   }

   public boolean isDirected() {
      return this.method4().isDirected();
   }

   public boolean allowsSelfLoops() {
      return this.method4().allowsSelfLoops();
   }

   public ElementOrder<N> method1() {
      return this.method4().method1();
   }

   public ElementOrder<N> method2() {
      return this.method4().method2();
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

   public int degree(N value1) {
      return this.method4().degree(value1);
   }

   public int inDegree(N value1) {
      return this.method4().inDegree(value1);
   }

   public int outDegree(N value1) {
      return this.method4().outDegree(value1);
   }

   public boolean hasEdgeConnecting(N value1, N value2) {
      return this.method4().hasEdgeConnecting(value1, value2);
   }

   public boolean method3(IterableBase<N> iterablebase1) {
      return this.method4().method3(iterablebase1);
   }

   public Optional<V> edgeValue(N value1, N value2) {
      return this.method4().edgeValue(value1, value2);
   }

   public Optional<V> method6(IterableBase<N> iterablebase1) {
      return this.method4().method6(iterablebase1);
   }

   public @Nullable V edgeValueOrDefault(N value1, N value2, @Nullable V value3) {
      return (V)this.method4().edgeValueOrDefault(value1, value2, value3);
   }

   public @Nullable V method6(IterableBase<N> iterablebase1, @Nullable V value2) {
      return (V)this.method4().method6(iterablebase1, value2);
   }
}
