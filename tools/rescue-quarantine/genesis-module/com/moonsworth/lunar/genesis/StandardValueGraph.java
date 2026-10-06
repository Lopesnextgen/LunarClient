package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.AbstractValueGraph;
import com.google.common.base.Preconditions;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.Graphs;

class StandardValueGraph<N, V> extends AbstractValueGraph<N, V> {
   private final boolean field1;
   private final boolean field2;
   private final ElementOrder<N> field3;
   protected final MixinHelper5_6<N, GraphConnections<N, V>> field4;
   protected long edgeCount;

   StandardValueGraph(AbstractGraphBuilder<? super N> mixinhelper81) {
      this(mixinhelper81, mixinhelper81.field2.createMap(mixinhelper81.field4.or(10)), 0L);
   }

   StandardValueGraph(AbstractGraphBuilder<? super N> mixinhelper81, Map<N, GraphConnections<N, V>> map2, long number3) {
      this.field1 = mixinhelper81.field1;
      this.field2 = mixinhelper81.allowsSelfLoops;
      this.field3 = mixinhelper81.field2.method7();
      this.field4 = (MixinHelper5_6<N, GraphConnections<N, V>>)(map2 instanceof TreeMap ? new MixinHelper52_2(map2) : new MixinHelper5_6(map2));
      this.edgeCount = Graphs.checkNonNegative(number3);
   }

   public Set<N> nodes() {
      return this.field4.method6();
   }

   public boolean isDirected() {
      return this.field1;
   }

   public boolean allowsSelfLoops() {
      return this.field2;
   }

   public ElementOrder<N> method1() {
      return this.field3;
   }

   public Set<N> adjacentNodes(N value1) {
      return this.method4((N)value1).adjacentNodes();
   }

   public Set<N> predecessors(N value1) {
      return this.method4((N)value1).predecessors();
   }

   public Set<N> successors(N value1) {
      return this.method4((N)value1).successors();
   }

   public Set<IterableBase<N>> incidentEdges(N value1) {
      GraphConnections mixinhelper3_132 = this.method4((N)value1);
      return new MixinHelper62333$1(this, this, value1, mixinhelper3_132);
   }

   public boolean hasEdgeConnecting(N value1, N value2) {
      return this.method6((N)Preconditions.checkNotNull(value1), (N)Preconditions.checkNotNull(value2));
   }

   public boolean method3(IterableBase<N> iterablebase1) {
      Preconditions.checkNotNull(iterablebase1);
      return this.HRICOROOOCCOCOROCRHHCRRIRCOICO(iterablebase1) && this.method6((N)iterablebase1.method5(), (N)iterablebase1.method6());
   }

   public @Nullable V edgeValueOrDefault(N value1, N value2, @Nullable V value3) {
      return this.method7((N)Preconditions.checkNotNull(value1), (N)Preconditions.checkNotNull(value2), (V)value3);
   }

   public @Nullable V method6(IterableBase<N> iterablebase1, @Nullable V value2) {
      this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(iterablebase1);
      return this.method7((N)iterablebase1.method5(), (N)iterablebase1.method6(), (V)value2);
   }

   protected long edgeCount() {
      return this.edgeCount;
   }

   protected final GraphConnections<N, V> method4(N value1) {
      GraphConnections mixinhelper3_132 = (GraphConnections)this.field4.get(value1);
      if (mixinhelper3_132 == null) {
         Preconditions.checkNotNull(value1);
         throw new IllegalArgumentException("Node " + value1 + " is not an element of this graph.");
      } else {
         return mixinhelper3_132;
      }
   }

   protected final boolean method5(@Nullable N value1) {
      return this.field4.method5(value1);
   }

   protected final boolean method6(N value1, N value2) {
      GraphConnections mixinhelper3_133 = (GraphConnections)this.field4.get(value1);
      return mixinhelper3_133 != null && mixinhelper3_133.successors().contains(value2);
   }

   protected final V method7(N value1, N value2, V value3) {
      GraphConnections mixinhelper3_134 = (GraphConnections)this.field4.get(value1);
      Object obj5 = mixinhelper3_134 == null ? null : mixinhelper3_134.value(value2);
      return (V)(obj5 == null ? value3 : obj5);
   }
}
