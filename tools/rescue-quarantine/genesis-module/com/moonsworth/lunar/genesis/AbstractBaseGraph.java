package com.moonsworth.lunar.genesis;

import java.util.Set;
import com.google.common.graph.ElementOrder;
import com.google.common.base.Preconditions;

abstract class AbstractBaseGraph<N> implements BaseGraph<N> {
   AbstractBaseGraph() {
   }

   protected long edgeCount() {
      long number1 = 0L;

      for (Object obj4 : this.nodes()) {
         number1 += this.degree((N)obj4);
      }

      Preconditions.checkState((number1 & 1L) == 0L);
      return number1 >>> 1;
   }

   public Set<IterableBase<N>> edges() {
      return new MixinHelper623$1(this);
   }

   public ElementOrder<N> method2() {
      return ElementOrder.method1();
   }

   public Set<IterableBase<N>> incidentEdges(N value1) {
      Preconditions.checkNotNull(value1);
      Preconditions.checkArgument(this.nodes().contains(value1), "Node %s is not an element of this graph.", value1);
      return new MixinHelper623$2(this, this, value1);
   }

   public int degree(N value1) {
      if (this.isDirected()) {
         return MixinHelper7_3.saturatedAdd(this.predecessors(value1).size(), this.successors(value1).size());
      }

      Set set2 = this.adjacentNodes(value1);
      int number3 = this.allowsSelfLoops() && set2.contains(value1) ? 1 : 0;
      return MixinHelper7_3.saturatedAdd(set2.size(), number3);
   }

   public int inDegree(N value1) {
      return this.isDirected() ? this.predecessors(value1).size() : this.degree((N)value1);
   }

   public int outDegree(N value1) {
      return this.isDirected() ? this.successors(value1).size() : this.degree((N)value1);
   }

   public boolean hasEdgeConnecting(N value1, N value2) {
      Preconditions.checkNotNull(value1);
      Preconditions.checkNotNull(value2);
      return this.nodes().contains(value1) && this.successors(value1).contains(value2);
   }

   public boolean method3(IterableBase<N> iterablebase1) {
      Preconditions.checkNotNull(iterablebase1);
      if (!this.method5(iterablebase1)) {
         return false;
      }

      Object obj2 = iterablebase1.method5();
      Object obj3 = iterablebase1.method6();
      return this.nodes().contains(obj2) && this.successors(obj2).contains(obj3);
   }

   protected final void method4(IterableBase<?> iterablebase1) {
      Preconditions.checkNotNull(iterablebase1);
      Preconditions.checkArgument(this.method5(iterablebase1), "Mismatch: unordered endpoints cannot be used with directed graphs");
   }

   protected final boolean method5(IterableBase<?> iterablebase1) {
      return iterablebase1.isOrdered() || !this.isDirected();
   }
}
