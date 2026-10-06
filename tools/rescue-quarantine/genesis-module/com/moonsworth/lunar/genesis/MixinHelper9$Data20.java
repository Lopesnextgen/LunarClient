package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.Graphs;

class MixinHelper9$Data20<N, V> extends MixinHelper62332<N, V> {
   private final MixinHelper624<N, V> field1;

   MixinHelper9$Data20(MixinHelper624<N, V> var1) {
      this.field1 = var1;
   }

   @Override
   protected MixinHelper624<N, V> method4() {
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
   public boolean hasEdgeConnecting(N var1, N var2) {
      return this.method4().hasEdgeConnecting((N)var2, (N)var1);
   }

   @Override
   public boolean method3(IterableBase<N> var1) {
      return this.method4().method3(Graphs.method10(var1));
   }

   @Override
   public Optional<V> edgeValue(N var1, N var2) {
      return this.method4().edgeValue((N)var2, (N)var1);
   }

   @Override
   public Optional<V> method6(IterableBase<N> var1) {
      return this.method4().method6(Graphs.method10(var1));
   }

   @Override
   public @Nullable V edgeValueOrDefault(N var1, N var2, @Nullable V var3) {
      return this.method4().edgeValueOrDefault((N)var2, (N)var1, (V)var3);
   }

   @Override
   public @Nullable V method6(IterableBase<N> var1, @Nullable V var2) {
      return this.method4().method6(Graphs.method10(var1), (V)var2);
   }
}
