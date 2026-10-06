package com.moonsworth.lunar.genesis;

import java.util.Set;
import com.google.common.graph.ElementOrder;

abstract class MixinHelper62322<N> extends MixinHelper6232<N> {
   protected abstract MixinHelper62<N> method3();

   @Override
   public Set<N> nodes() {
      return this.method3().nodes();
   }

   @Override
   protected long edgeCount() {
      return this.method3().edges().size();
   }

   @Override
   public boolean isDirected() {
      return this.method3().isDirected();
   }

   @Override
   public boolean allowsSelfLoops() {
      return this.method3().allowsSelfLoops();
   }

   @Override
   public ElementOrder<N> method1() {
      return this.method3().method1();
   }

   @Override
   public ElementOrder<N> method2() {
      return this.method3().method2();
   }

   @Override
   public Set<N> adjacentNodes(N var1) {
      return this.method3().adjacentNodes((N)var1);
   }

   @Override
   public Set<N> predecessors(N var1) {
      return this.method3().predecessors((N)var1);
   }

   @Override
   public Set<N> successors(N var1) {
      return this.method3().successors((N)var1);
   }

   @Override
   public Set<IterableBase<N>> incidentEdges(N var1) {
      return this.method3().incidentEdges((N)var1);
   }

   @Override
   public int degree(N var1) {
      return this.method3().degree((N)var1);
   }

   @Override
   public int inDegree(N var1) {
      return this.method3().inDegree((N)var1);
   }

   @Override
   public int outDegree(N var1) {
      return this.method3().outDegree((N)var1);
   }

   @Override
   public boolean hasEdgeConnecting(N var1, N var2) {
      return this.method3().hasEdgeConnecting((N)var1, (N)var2);
   }

   @Override
   public boolean method3(IterableBase<N> var1) {
      return this.method3().method3(var1);
   }
}
