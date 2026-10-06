package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.Network;
import com.google.common.graph.AbstractNetwork;

abstract class MixinHelper6332<N, E> extends AbstractNetwork<N, E> {
   protected abstract Network<N, E> method4();

   @Override
   public Set<N> nodes() {
      return this.method4().nodes();
   }

   @Override
   public Set<E> edges() {
      return this.method4().edges();
   }

   @Override
   public boolean isDirected() {
      return this.method4().isDirected();
   }

   @Override
   public boolean allowsParallelEdges() {
      return this.method4().allowsParallelEdges();
   }

   @Override
   public boolean allowsSelfLoops() {
      return this.method4().allowsSelfLoops();
   }

   @Override
   public ElementOrder<N> method2() {
      return this.method4().method2();
   }

   @Override
   public ElementOrder<E> method3() {
      return this.method4().method3();
   }

   @Override
   public Set<N> adjacentNodes(N var1) {
      return this.method4().adjacentNodes((N)var1);
   }

   @Override
   public Set<N> predecessors(N var1) {
      return this.method4().predecessors((N)var1);
   }

   @Override
   public Set<N> successors(N var1) {
      return this.method4().successors((N)var1);
   }

   @Override
   public Set<E> incidentEdges(N var1) {
      return this.method4().incidentEdges((N)var1);
   }

   @Override
   public Set<E> inEdges(N var1) {
      return this.method4().inEdges((N)var1);
   }

   @Override
   public Set<E> outEdges(N var1) {
      return this.method4().outEdges((N)var1);
   }

   @Override
   public IterableBase<N> method4(E var1) {
      return this.method4().method4((E)var1);
   }

   @Override
   public Set<E> adjacentEdges(E var1) {
      return this.method4().adjacentEdges((E)var1);
   }

   @Override
   public int degree(N var1) {
      return this.method4().degree((N)var1);
   }

   @Override
   public int inDegree(N var1) {
      return this.method4().inDegree((N)var1);
   }

   @Override
   public int outDegree(N var1) {
      return this.method4().outDegree((N)var1);
   }

   @Override
   public Set<E> edgesConnecting(N var1, N var2) {
      return this.method4().edgesConnecting((N)var1, (N)var2);
   }

   @Override
   public Set<E> method5(IterableBase<N> var1) {
      return this.method4().method5(var1);
   }

   @Override
   public Optional<E> edgeConnecting(N var1, N var2) {
      return this.method4().edgeConnecting((N)var1, (N)var2);
   }

   @Override
   public Optional<E> method6(IterableBase<N> var1) {
      return this.method4().method6(var1);
   }

   @Override
   public E edgeConnectingOrNull(N var1, N var2) {
      return this.method4().edgeConnectingOrNull((N)var1, (N)var2);
   }

   @Override
   public E method7(IterableBase<N> var1) {
      return this.method4().method7(var1);
   }

   @Override
   public boolean hasEdgeConnecting(N var1, N var2) {
      return this.method4().hasEdgeConnecting((N)var1, (N)var2);
   }

   @Override
   public boolean method8(IterableBase<N> var1) {
      return this.method4().method8(var1);
   }
}
