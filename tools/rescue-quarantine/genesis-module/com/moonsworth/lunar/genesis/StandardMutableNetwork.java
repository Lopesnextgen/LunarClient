package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.common.graph.MutableNetwork;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableList;

final class StandardMutableNetwork<N, E> extends StandardNetwork<N, E> implements MutableNetwork<N, E> {
   StandardMutableNetwork(MixinHelper83<? super N, ? super E> mixinhelper831) {
      super(mixinhelper831);
   }

   @CanIgnoreReturnValue
   public boolean addNode(N value1) {
      Preconditions.checkNotNull(value1, "node");
      if (this.CIIIOIOHCCIOIIIRRHCRROIOIICCOO(value1)) {
         return false;
      }

      this.method1((N)value1);
      return true;
   }

   @CanIgnoreReturnValue
   private NetworkConnections<N, E> method1(N value1) {
      NetworkConnections mixinhelper11_22 = this.method4();
      Preconditions.checkState(this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.method1(value1, mixinhelper11_22) == null);
      return mixinhelper11_22;
   }

   @CanIgnoreReturnValue
   public boolean addEdge(N value1, N value2, E value3) {
      Preconditions.checkNotNull(value1, "nodeU");
      Preconditions.checkNotNull(value2, "nodeV");
      Preconditions.checkNotNull(value3, "edge");
      if (this.CICIOOHRIHORIICCRRCOROCROOHORH(value3)) {
         IterableBase iterablebase7 = this.ROCOICHICCRHRORRRHOHIHCRHHIRRR(value3);
         IterableBase iterablebase8 = IterableBase.method4(this, value1, value2);
         Preconditions.checkArgument(
            iterablebase7.equals(iterablebase8),
            "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.",
            value3,
            iterablebase7,
            iterablebase8
         );
         return false;
      }

      NetworkConnections mixinhelper11_24 = (NetworkConnections)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(value1);
      if (!this.allowsParallelEdges()) {
         Preconditions.checkArgument(
            mixinhelper11_24 == null || !mixinhelper11_24.successors().contains(value2),
            "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.",
            value1,
            value2
         );
      }

      boolean flag5 = value1.equals(value2);
      if (!this.allowsSelfLoops()) {
         Preconditions.checkArgument(
            !flag5,
            "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.",
            value1
         );
      }

      if (mixinhelper11_24 == null) {
         mixinhelper11_24 = this.method1((N)value1);
      }

      mixinhelper11_24.addOutEdge(value3, value2);
      NetworkConnections mixinhelper11_26 = (NetworkConnections)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(value2);
      if (mixinhelper11_26 == null) {
         mixinhelper11_26 = this.method1((N)value2);
      }

      mixinhelper11_26.addInEdge(value3, value1, flag5);
      this.RROCCOOOCIIHIOORHHHHRCRIHRCCCC.method1(value3, value1);
      return true;
   }

   @CanIgnoreReturnValue
   public boolean method1(IterableBase<N> iterablebase1, E value2) {
      this.ORCHOHHCOHCORRICRIHCHHRORHHCHH(iterablebase1);
      return this.addEdge((N)iterablebase1.method5(), (N)iterablebase1.method6(), (E)value2);
   }

   @CanIgnoreReturnValue
   public boolean removeNode(N value1) {
      Preconditions.checkNotNull(value1, "node");
      NetworkConnections mixinhelper11_22 = (NetworkConnections)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(value1);
      if (mixinhelper11_22 == null) {
         return false;
      }

      UnmodifiableIterator mixinhelperiterator33 = ImmutableList.method15(mixinhelper11_22.incidentEdges()).method1();

      while (mixinhelperiterator33.hasNext()) {
         Object obj4 = mixinhelperiterator33.next();
         this.removeEdge((E)obj4);
      }

      this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.method2(value1);
      return true;
   }

   @CanIgnoreReturnValue
   public boolean removeEdge(E value1) {
      Preconditions.checkNotNull(value1, "edge");
      Object obj2 = this.RROCCOOOCIIHIOORHHHHRCRIHRCCCC.get(value1);
      if (obj2 == null) {
         return false;
      }

      NetworkConnections mixinhelper11_23 = (NetworkConnections)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(obj2);
      Object obj4 = mixinhelper11_23.adjacentNode(value1);
      NetworkConnections mixinhelper11_25 = (NetworkConnections)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(obj4);
      mixinhelper11_23.removeOutEdge(value1);
      mixinhelper11_25.removeInEdge(value1, this.allowsSelfLoops() && obj2.equals(obj4));
      this.RROCCOOOCIIHIOORHHHHRCRIHRCCCC.method2(value1);
      return true;
   }

   private NetworkConnections<N, E> method4() {
      return (NetworkConnections<N, E>)(this.isDirected()
         ? (this.allowsParallelEdges() ? MixinHelper1123.method1() : DirectedNetworkConnections.method1())
         : (this.allowsParallelEdges() ? UndirectedMultiNetworkConnections.method1() : UndirectedNetworkConnections.method1()));
   }
}
