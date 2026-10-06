package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.ElementOrder;
import com.google.common.base.Preconditions;
import com.google.common.graph.Graphs;

class MixinHelper62333<N, V> extends MixinHelper6233<N, V> {
   private final boolean field1;
   private final boolean field2;
   private final ElementOrder<N> field3;
   protected final MixinHelper5_6<N, MixinHelper3_13<N, V>> field4;
   protected long edgeCount;

   MixinHelper62333(MixinHelper8<? super N> var1) {
      this(var1, var1.field2.createMap(var1.field4.or(10)), 0L);
   }

   MixinHelper62333(MixinHelper8<? super N> var1, Map<N, MixinHelper3_13<N, V>> var2, long var3) {
      this.field1 = var1.field1;
      this.field2 = var1.allowsSelfLoops;
      this.field3 = var1.field2.method7();
      this.field4 = var2 instanceof TreeMap ? new MixinHelper52_2<>(var2) : new MixinHelper5_6<>(var2);
      this.edgeCount = Graphs.checkNonNegative(var3);
   }

   @Override
   public Set<N> nodes() {
      return this.field4.method6();
   }

   @Override
   public boolean isDirected() {
      return this.field1;
   }

   @Override
   public boolean allowsSelfLoops() {
      return this.field2;
   }

   @Override
   public ElementOrder<N> method1() {
      return this.field3;
   }

   @Override
   public Set<N> adjacentNodes(N var1) {
      return this.method4((N)var1).adjacentNodes();
   }

   @Override
   public Set<N> predecessors(N var1) {
      return this.method4((N)var1).predecessors();
   }

   @Override
   public Set<N> successors(N var1) {
      return this.method4((N)var1).successors();
   }

   @Override
   public Set<IterableBase<N>> incidentEdges(N var1) {
      final MixinHelper3_13 var2 = this.method4((N)var1);
      return new AbstractSetBase2<N>(this, var1) {
         @Override
         public Iterator<IterableBase<N>> iterator() {
            return (Iterator<IterableBase<N>>)var2.incidentEdgeIterator(this.RHOIIOHOROHCCHOHCOIIRRROCRHRIC);
         }
      };
   }

   @Override
   public boolean hasEdgeConnecting(N var1, N var2) {
      return this.method6(Preconditions.checkNotNull((N)var1), Preconditions.checkNotNull((N)var2));
   }

   @Override
   public boolean method3(IterableBase<N> var1) {
      Preconditions.checkNotNull(var1);
      return this.method3(var1) && this.method6((N)var1.method5(), (N)var1.method6());
   }

   @Override
   public @Nullable V edgeValueOrDefault(N var1, N var2, @Nullable V var3) {
      return this.method7(Preconditions.checkNotNull((N)var1), Preconditions.checkNotNull((N)var2), (V)var3);
   }

   @Override
   public @Nullable V method6(IterableBase<N> var1, @Nullable V var2) {
      this.method4(var1);
      return this.method7((N)var1.method5(), (N)var1.method6(), (V)var2);
   }

   @Override
   protected long edgeCount() {
      return this.edgeCount;
   }

   protected final MixinHelper3_13<N, V> method4(N var1) {
      MixinHelper3_13 var2 = this.field4.get(var1);
      if (var2 == null) {
         Preconditions.checkNotNull(var1);
         throw new IllegalArgumentException("Node " + var1 + " is not an element of this graph.");
      } else {
         return var2;
      }
   }

   protected final boolean method5(@Nullable N var1) {
      return this.field4.method5(var1);
   }

   protected final boolean method6(N var1, N var2) {
      MixinHelper3_13 var3 = this.field4.get(var1);
      return var3 != null && var3.successors().contains(var2);
   }

   protected final V method7(N var1, N var2, V var3) {
      MixinHelper3_13 var4 = this.field4.get(var1);
      Object var5 = var4 == null ? null : var4.value(var2);
      return (V)(var5 == null ? var3 : var5);
   }
}
