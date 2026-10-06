package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Set;
import com.google.common.collect.Iterators;
import com.google.common.graph.Graphs;

class MixinHelper9$Data18<N> extends MixinHelper62322<N> {
   private final MixinHelper622<N> field1;

   MixinHelper9$Data18(MixinHelper622<N> var1) {
      this.field1 = var1;
   }

   protected MixinHelper622<N> method4() {
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
   public Set<IterableBase<N>> incidentEdges(N var1) {
      return new AbstractSetBase2<N>(this, var1) {
         @Override
         public Iterator<IterableBase<N>> iterator() {
            return Iterators.method17(
               MixinHelper9$Data18.this.method4().incidentEdges(this.RHOIIOHOROHCCHOHCOIIRRROCRHRIC).iterator(),
               new MixinHelper24_2<IterableBase<N>, IterableBase<N>>() {
                  public IterableBase<N> method1(IterableBase<N> var1) {
                     return IterableBase.method3(MixinHelper9$Data18.this.method4(), (N)var1.method6(), (N)var1.method5());
                  }
               }
            );
         }
      };
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
}
