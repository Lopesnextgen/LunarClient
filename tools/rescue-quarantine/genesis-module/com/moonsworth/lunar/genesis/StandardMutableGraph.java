package com.moonsworth.lunar.genesis;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;

final class StandardMutableGraph<N> extends ForwardingGraph<N> implements MutableGraph<N> {
   private final MutableValueGraph<N, MixinHelper2$Type6> field1;

   StandardMutableGraph(AbstractGraphBuilder<? super N> mixinhelper81) {
      this.field1 = new StandardMutableValueGraph(mixinhelper81);
   }

   protected BaseGraph<N> method3() {
      return this.field1;
   }

   public boolean addNode(N value1) {
      return this.field1.addNode(value1);
   }

   public boolean putEdge(N value1, N value2) {
      return this.field1.putEdgeValue(value1, value2, MixinHelper2$Type6.EDGE_EXISTS) == null;
   }

   public boolean method1(IterableBase<N> iterablebase1) {
      this.method4(iterablebase1);
      return this.putEdge((N)iterablebase1.method5(), (N)iterablebase1.method6());
   }

   public boolean removeNode(N value1) {
      return this.field1.removeNode(value1);
   }

   public boolean removeEdge(N value1, N value2) {
      return this.field1.removeEdge(value1, value2) != null;
   }

   public boolean method2(IterableBase<N> iterablebase1) {
      this.method4(iterablebase1);
      return this.removeEdge((N)iterablebase1.method5(), (N)iterablebase1.method6());
   }
}
