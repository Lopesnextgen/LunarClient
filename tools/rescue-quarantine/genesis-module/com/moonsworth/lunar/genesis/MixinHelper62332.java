package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.ElementOrder;

abstract class MixinHelper62332<N, V> extends MixinHelper6233<N, V> {
   protected abstract MixinHelper624<N, V> method4();

   @Override
   public Set<N> nodes() {
      return this.method4().nodes();
   }

   @Override
   protected long edgeCount() {
      return this.method4().edges().size();
   }

   @Override
   public boolean isDirected() {
      return this.method4().isDirected();
   }

   @Override
   public boolean allowsSelfLoops() {
      return this.method4().allowsSelfLoops();
   }

   @Override
   public ElementOrder<N> method1() {
      return this.method4().method1();
   }

   @Override
   public ElementOrder<N> method2() {
      return this.method4().method2();
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
   public boolean hasEdgeConnecting(N var1, N var2) {
      return this.method4().hasEdgeConnecting((N)var1, (N)var2);
   }

   @Override
   public boolean method3(IterableBase<N> var1) {
      return this.method4().method3(var1);
   }

   @Override
   public Optional<V> edgeValue(N var1, N var2) {
      return this.method4().edgeValue((N)var1, (N)var2);
   }

   @Override
   public Optional<V> method6(IterableBase<N> var1) {
      return this.method4().method6(var1);
   }

   @Override
   public @Nullable V edgeValueOrDefault(N var1, N var2, @Nullable V var3) {
      return this.method4().edgeValueOrDefault((N)var1, (N)var2, (V)var3);
   }

   @Override
   public @Nullable V method6(IterableBase<N> var1, @Nullable V var2) {
      return this.method4().method6(var1, (V)var2);
   }
}
