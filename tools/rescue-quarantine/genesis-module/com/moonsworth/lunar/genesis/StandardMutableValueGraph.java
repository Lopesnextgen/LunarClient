package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.common.base.Preconditions;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.Graphs;

final class StandardMutableValueGraph<N, V> extends StandardValueGraph<N, V> implements MutableValueGraph<N, V> {
   private final ElementOrder<N> field5;

   StandardMutableValueGraph(AbstractGraphBuilder<? super N> mixinhelper81) {
      super(mixinhelper81);
      this.field5 = mixinhelper81.field3.method7();
   }

   public ElementOrder<N> method2() {
      return this.field5;
   }

   @CanIgnoreReturnValue
   public boolean addNode(N value1) {
      Preconditions.checkNotNull(value1, "node");
      if (this.ORCCIIORRCICHIORRRHICHHCRIRHRO(value1)) {
         return false;
      }

      this.method2((N)value1);
      return true;
   }

   @CanIgnoreReturnValue
   private GraphConnections<N, V> method2(N value1) {
      GraphConnections mixinhelper3_132 = this.method5();
      Preconditions.checkState(this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.method1(value1, mixinhelper3_132) == null);
      return mixinhelper3_132;
   }

   @CanIgnoreReturnValue
   public V putEdgeValue(N value1, N value2, V value3) {
      Preconditions.checkNotNull(value1, "nodeU");
      Preconditions.checkNotNull(value2, "nodeV");
      Preconditions.checkNotNull(value3, "value");
      if (!this.allowsSelfLoops()) {
         Preconditions.checkArgument(
            !value1.equals(value2),
            "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.",
            value1
         );
      }

      GraphConnections mixinhelper3_134 = (GraphConnections)this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.get(value1);
      if (mixinhelper3_134 == null) {
         mixinhelper3_134 = this.method2((N)value1);
      }

      Object obj5 = mixinhelper3_134.addSuccessor(value2, value3);
      GraphConnections mixinhelper3_136 = (GraphConnections)this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.get(value2);
      if (mixinhelper3_136 == null) {
         mixinhelper3_136 = this.method2((N)value2);
      }

      mixinhelper3_136.addPredecessor(value1, value3);
      if (obj5 == null) {
         Graphs.checkPositive(++this.edgeCount);
      }

      return (V)obj5;
   }

   @CanIgnoreReturnValue
   public V method1(IterableBase<N> iterablebase1, V value2) {
      this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(iterablebase1);
      return this.putEdgeValue((N)iterablebase1.method5(), (N)iterablebase1.method6(), (V)value2);
   }

   @CanIgnoreReturnValue
   public boolean removeNode(N value1) {
      Preconditions.checkNotNull(value1, "node");
      GraphConnections mixinhelper3_132 = (GraphConnections)this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.get(value1);
      if (mixinhelper3_132 == null) {
         return false;
      }

      if (this.allowsSelfLoops() && mixinhelper3_132.removeSuccessor(value1) != null) {
         mixinhelper3_132.removePredecessor(value1);
         this.edgeCount--;
      }

      for (Object obj4 : mixinhelper3_132.successors()) {
         ((GraphConnections)this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.method4(obj4)).removePredecessor(value1);
         this.edgeCount--;
      }

      if (this.isDirected()) {
         for (Object obj6 : mixinhelper3_132.predecessors()) {
            Preconditions.checkState(((GraphConnections)this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.method4(obj6)).removeSuccessor(value1) != null);
            this.edgeCount--;
         }
      }

      this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.method2(value1);
      Graphs.checkNonNegative(this.edgeCount);
      return true;
   }

   @CanIgnoreReturnValue
   public V removeEdge(N value1, N value2) {
      Preconditions.checkNotNull(value1, "nodeU");
      Preconditions.checkNotNull(value2, "nodeV");
      GraphConnections mixinhelper3_133 = (GraphConnections)this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.get(value1);
      GraphConnections mixinhelper3_134 = (GraphConnections)this.RIRHICRIRHRIIHRHRIIHRROHHIROOI.get(value2);
      if (mixinhelper3_133 != null && mixinhelper3_134 != null) {
         Object obj5 = mixinhelper3_133.removeSuccessor(value2);
         if (obj5 != null) {
            mixinhelper3_134.removePredecessor(value1);
            Graphs.checkNonNegative(--this.edgeCount);
         }

         return (V)obj5;
      } else {
         return null;
      }
   }

   @CanIgnoreReturnValue
   public V method2(IterableBase<N> iterablebase1) {
      this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(iterablebase1);
      return this.removeEdge((N)iterablebase1.method5(), (N)iterablebase1.method6());
   }

   private GraphConnections<N, V> method5() {
      return (GraphConnections<N, V>)(this.isDirected() ? MixinHelper32_2.method1(this.field5) : MixinHelper33_2.method1(this.field5));
   }
}
