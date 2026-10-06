package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.AbstractNetwork;

class MixinHelper6333<N, E> extends AbstractNetwork<N, E> {
   private final boolean field1;
   private final boolean field2;
   private final boolean field3;
   private final ElementOrder<N> field4;
   private final ElementOrder<E> field5;
   protected final MixinHelper5_6<N, MixinHelper11_2<N, E>> field6;
   protected final MixinHelper5_6<E, N> field7;

   MixinHelper6333(MixinHelper83<? super N, ? super E> var1) {
      this(var1, var1.field5.createMap(var1.field6.or(10)), var1.field5.createMap(var1.field6.or(20)));
   }

   MixinHelper6333(MixinHelper83<? super N, ? super E> var1, Map<N, MixinHelper11_2<N, E>> var2, Map<E, N> var3) {
      this.field1 = var1.allowsParallelEdges;
      this.field2 = var1.allowsParallelEdges;
      this.field3 = var1.allowsSelfLoops;
      this.field4 = var1.field5.method7();
      this.field5 = var1.field5.method7();
      this.field6 = var2 instanceof TreeMap ? new MixinHelper52_2<>(var2) : new MixinHelper5_6<>(var2);
      this.field7 = new MixinHelper5_6<>(var3);
   }

   @Override
   public Set<N> nodes() {
      return this.field6.method6();
   }

   @Override
   public Set<E> edges() {
      return this.field7.method6();
   }

   @Override
   public boolean isDirected() {
      return this.field1;
   }

   @Override
   public boolean allowsParallelEdges() {
      return this.field2;
   }

   @Override
   public boolean allowsSelfLoops() {
      return this.field3;
   }

   @Override
   public ElementOrder<N> method2() {
      return this.field4;
   }

   @Override
   public ElementOrder<E> method3() {
      return this.field5;
   }

   @Override
   public Set<E> incidentEdges(N var1) {
      return this.method5((N)var1).incidentEdges();
   }

   @Override
   public IterableBase<N> method4(E var1) {
      Object var2 = this.method6((E)var1);
      Object var3 = this.field6.get(var2).adjacentNode((E)var1);
      return IterableBase.method4(this, (N)var2, (N)var3);
   }

   @Override
   public Set<N> adjacentNodes(N var1) {
      return this.method5((N)var1).adjacentNodes();
   }

   @Override
   public Set<E> edgesConnecting(N var1, N var2) {
      MixinHelper11_2 var3 = this.method5((N)var1);
      if (!this.field3 && var1 == var2) {
         return ImmutableSet.method3();
      }

      Preconditions.checkArgument(this.method7((N)var2), "Node %s is not an element of this graph.", var2);
      return var3.edgesConnecting(var2);
   }

   @Override
   public Set<E> inEdges(N var1) {
      return this.method5((N)var1).inEdges();
   }

   @Override
   public Set<E> outEdges(N var1) {
      return this.method5((N)var1).outEdges();
   }

   @Override
   public Set<N> predecessors(N var1) {
      return this.method5((N)var1).predecessors();
   }

   @Override
   public Set<N> successors(N var1) {
      return this.method5((N)var1).successors();
   }

   protected final MixinHelper11_2<N, E> method5(N var1) {
      MixinHelper11_2 var2 = this.field6.get(var1);
      if (var2 == null) {
         Preconditions.checkNotNull(var1);
         throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", var1));
      } else {
         return var2;
      }
   }

   protected final N method6(E var1) {
      Object var2 = this.field7.get(var1);
      if (var2 == null) {
         Preconditions.checkNotNull(var1);
         throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", var1));
      } else {
         return (N)var2;
      }
   }

   protected final boolean method7(@Nullable N var1) {
      return this.field6.method5(var1);
   }

   protected final boolean method8(@Nullable E var1) {
      return this.field7.method5(var1);
   }
}
