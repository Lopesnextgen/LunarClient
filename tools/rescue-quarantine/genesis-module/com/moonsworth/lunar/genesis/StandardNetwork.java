package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.AbstractNetwork;

class StandardNetwork<N, E> extends AbstractNetwork<N, E> {
   private final boolean field1;
   private final boolean field2;
   private final boolean field3;
   private final ElementOrder<N> field4;
   private final ElementOrder<E> field5;
   protected final MixinHelper5_6<N, NetworkConnections<N, E>> field6;
   protected final MixinHelper5_6<E, N> field7;

   StandardNetwork(MixinHelper83<? super N, ? super E> mixinhelper831) {
      this(
         mixinhelper831,
         mixinhelper831.IICOIRRCCHRHCHCOCOIHRIIORCOHCO.createMap((Integer)mixinhelper831.OHIOOORCICROCIOIRCCROOOOOCCOCI.or(10)),
         mixinhelper831.field5.createMap((Integer)mixinhelper831.field6.or(20))
      );
   }

   StandardNetwork(MixinHelper83<? super N, ? super E> mixinhelper831, Map<N, NetworkConnections<N, E>> map2, Map<E, N> map3) {
      this.field1 = mixinhelper831.COHIIRHIICOICRHIIHRHRCCCIIROHI;
      this.field2 = mixinhelper831.allowsParallelEdges;
      this.field3 = mixinhelper831.allowsSelfLoops;
      this.field4 = mixinhelper831.IICOIRRCCHRHCHCOCOIHRIIORCOHCO.method7();
      this.field5 = mixinhelper831.field5.method7();
      this.field6 = (MixinHelper5_6<N, NetworkConnections<N, E>>)(map2 instanceof TreeMap ? new MixinHelper52_2(map2) : new MixinHelper5_6(map2));
      this.field7 = new MixinHelper5_6(map3);
   }

   public Set<N> nodes() {
      return this.field6.method6();
   }

   public Set<E> edges() {
      return this.field7.method6();
   }

   public boolean isDirected() {
      return this.field1;
   }

   public boolean allowsParallelEdges() {
      return this.field2;
   }

   public boolean allowsSelfLoops() {
      return this.field3;
   }

   public ElementOrder<N> method2() {
      return this.field4;
   }

   public ElementOrder<E> method3() {
      return this.field5;
   }

   public Set<E> incidentEdges(N value1) {
      return this.method5((N)value1).incidentEdges();
   }

   public IterableBase<N> method4(E value1) {
      Object obj2 = this.method6((E)value1);
      Object obj3 = ((NetworkConnections)this.field6.get(obj2)).adjacentNode(value1);
      return IterableBase.method4(this, obj2, obj3);
   }

   public Set<N> adjacentNodes(N value1) {
      return this.method5((N)value1).adjacentNodes();
   }

   public Set<E> edgesConnecting(N value1, N value2) {
      NetworkConnections mixinhelper11_23 = this.method5((N)value1);
      if (!this.field3 && value1 == value2) {
         return ImmutableSet.method3();
      }

      Preconditions.checkArgument(this.method7((N)value2), "Node %s is not an element of this graph.", value2);
      return mixinhelper11_23.edgesConnecting(value2);
   }

   public Set<E> inEdges(N value1) {
      return this.method5((N)value1).inEdges();
   }

   public Set<E> outEdges(N value1) {
      return this.method5((N)value1).outEdges();
   }

   public Set<N> predecessors(N value1) {
      return this.method5((N)value1).predecessors();
   }

   public Set<N> successors(N value1) {
      return this.method5((N)value1).successors();
   }

   protected final NetworkConnections<N, E> method5(N value1) {
      NetworkConnections mixinhelper11_22 = (NetworkConnections)this.field6.get(value1);
      if (mixinhelper11_22 == null) {
         Preconditions.checkNotNull(value1);
         throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", value1));
      } else {
         return mixinhelper11_22;
      }
   }

   protected final N method6(E value1) {
      Object obj2 = this.field7.get(value1);
      if (obj2 == null) {
         Preconditions.checkNotNull(value1);
         throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", value1));
      } else {
         return (N)obj2;
      }
   }

   protected final boolean method7(@Nullable N value1) {
      return this.field6.method5(value1);
   }

   protected final boolean method8(@Nullable E value1) {
      return this.field7.method5(value1);
   }
}
