package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.ValueGraph;

class Graphs$TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
   private final ValueGraph<N, V> field1;

   Graphs$TransposedValueGraph(ValueGraph<N, V> mixinhelper6241) {
      this.field1 = mixinhelper6241;
   }

   protected ValueGraph<N, V> method4() {
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

   public boolean hasEdgeConnecting(N value1, N value2) {
      return this.method4().hasEdgeConnecting(value2, value1);
   }

   public boolean method3(IterableBase<N> iterablebase1) {
      return this.method4().method3(MixinHelper9_5.method10(iterablebase1));
   }

   public Optional<V> edgeValue(N value1, N value2) {
      return this.method4().edgeValue(value2, value1);
   }

   public Optional<V> method6(IterableBase<N> iterablebase1) {
      return this.method4().method6(MixinHelper9_5.method10(iterablebase1));
   }

   public @Nullable V edgeValueOrDefault(N value1, N value2, @Nullable V value3) {
      return (V)this.method4().edgeValueOrDefault(value2, value1, value3);
   }

   public @Nullable V method6(IterableBase<N> iterablebase1, @Nullable V value2) {
      return (V)this.method4().method6(MixinHelper9_5.method10(iterablebase1), value2);
   }
}
