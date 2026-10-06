package com.moonsworth.lunar.genesis;

import java.util.Set;
import com.google.common.graph.Graph;

class Graphs$TransposedGraph<N> extends ForwardingGraph<N> {
   private final Graph<N> field1;

   Graphs$TransposedGraph(Graph<N> mixinhelper6221) {
      this.field1 = mixinhelper6221;
   }

   protected Graph<N> method4() {
      return this.field1;
   }

   public Set<N> predecessors(N value1) {
      return this.method4().successors(value1);
   }

   public Set<N> successors(N value1) {
      return this.method4().predecessors(value1);
   }

   public Set<IterableBase<N>> incidentEdges(N value1) {
      return new Data18$1(this, this, value1);
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
}
