package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import com.google.common.graph.Network;
import com.google.common.graph.Graphs;

class MixinHelper9$Data19<N, E> extends MixinHelper6332<N, E> {
   private final Network<N, E> field1;

   MixinHelper9$Data19(Network<N, E> var1) {
      this.field1 = var1;
   }

   @Override
   protected Network<N, E> method4() {
      return this.field1;
   }

   @Override
   public Set<N> predecessors(N var1) {
      return this.method4().successors((N)var1);
   }

   @Override
   public Set<N> successors(N var1) {
      return this.method4().predecessors((N)var1);
   }

   @Override
   public int inDegree(N var1) {
      return this.method4().outDegree((N)var1);
   }

   @Override
   public int outDegree(N var1) {
      return this.method4().inDegree((N)var1);
   }

   @Override
   public Set<E> inEdges(N var1) {
      return this.method4().outEdges((N)var1);
   }

   @Override
   public Set<E> outEdges(N var1) {
      return this.method4().inEdges((N)var1);
   }

   @Override
   public IterableBase<N> method4(E var1) {
      IterableBase var2 = this.method4().method4((E)var1);
      return IterableBase.method4(this.field1, (N)var2.method6(), (N)var2.method5());
   }

   @Override
   public Set<E> edgesConnecting(N var1, N var2) {
      return this.method4().edgesConnecting((N)var2, (N)var1);
   }

   @Override
   public Set<E> method5(IterableBase<N> var1) {
      return this.method4().method5(Graphs.method10(var1));
   }

   @Override
   public Optional<E> edgeConnecting(N var1, N var2) {
      return this.method4().edgeConnecting((N)var2, (N)var1);
   }

   @Override
   public Optional<E> method6(IterableBase<N> var1) {
      return this.method4().method6(Graphs.method10(var1));
   }

   @Override
   public E edgeConnectingOrNull(N var1, N var2) {
      return this.method4().edgeConnectingOrNull((N)var2, (N)var1);
   }

   @Override
   public E method7(IterableBase<N> var1) {
      return this.method4().method7(Graphs.method10(var1));
   }

   @Override
   public boolean hasEdgeConnecting(N var1, N var2) {
      return this.method4().hasEdgeConnecting((N)var2, (N)var1);
   }

   @Override
   public boolean method8(IterableBase<N> var1) {
      return this.method4().method8(Graphs.method10(var1));
   }
}
