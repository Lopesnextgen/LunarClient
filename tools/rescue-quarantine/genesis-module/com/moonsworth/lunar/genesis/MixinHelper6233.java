package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.ElementOrder;
import com.google.common.collect.Maps;

@Annotation2
public abstract class MixinHelper6233<N, V> extends MixinHelper623<N> implements MixinHelper624<N, V> {
   @Override
   public MixinHelper622<N> method3() {
      return new MixinHelper6232<N>() {
         @Override
         public Set<N> nodes() {
            return MixinHelper6233.this.nodes();
         }

         @Override
         public Set<IterableBase<N>> edges() {
            return MixinHelper6233.this.edges();
         }

         @Override
         public boolean isDirected() {
            return MixinHelper6233.this.isDirected();
         }

         @Override
         public boolean allowsSelfLoops() {
            return MixinHelper6233.this.allowsSelfLoops();
         }

         @Override
         public ElementOrder<N> method1() {
            return MixinHelper6233.this.RCCHIOOCRCOOCHCCHIRICORHRCHCRO();
         }

         @Override
         public ElementOrder<N> method2() {
            return MixinHelper6233.this.method2();
         }

         @Override
         public Set<N> adjacentNodes(N var1) {
            return (Set<N>)MixinHelper6233.this.adjacentNodes(var1);
         }

         @Override
         public Set<N> predecessors(N var1) {
            return (Set<N>)MixinHelper6233.this.predecessors(var1);
         }

         @Override
         public Set<N> successors(N var1) {
            return (Set<N>)MixinHelper6233.this.successors(var1);
         }

         @Override
         public int degree(N var1) {
            return MixinHelper6233.this.degree(var1);
         }

         @Override
         public int inDegree(N var1) {
            return MixinHelper6233.this.inDegree(var1);
         }

         @Override
         public int outDegree(N var1) {
            return MixinHelper6233.this.outDegree(var1);
         }
      };
   }

   @Override
   public Optional<V> edgeValue(N var1, N var2) {
      return Optional.ofNullable(this.edgeValueOrDefault((N)var1, (N)var2, null));
   }

   @Override
   public Optional<V> method6(IterableBase<N> var1) {
      return Optional.ofNullable((V)this.method3(var1, null));
   }

   @Override
   public final boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof MixinHelper624)) {
         return false;
      }

      MixinHelper624 var2 = (MixinHelper624)var1;
      return this.isDirected() == var2.isDirected() && this.nodes().equals(var2.nodes()) && method3(this).equals(method3(var2));
   }

   @Override
   public final int hashCode() {
      return method3(this).hashCode();
   }

   @Override
   public String toString() {
      return "isDirected: " + this.isDirected() + ", allowsSelfLoops: " + this.allowsSelfLoops() + ", nodes: " + this.nodes() + ", edges: " + method3(this);
   }

   private static <N, V> Map<IterableBase<N>, V> method3(final MixinHelper624<N, V> var0) {
      MixinHelper24_2 var1 = new MixinHelper24_2<IterableBase<N>, V>() {
         public V method1(IterableBase<N> var1) {
            return (V)var0.edgeValueOrDefault(var1.method5(), var1.method6(), null);
         }
      };
      return Maps.method8(var0.edges(), var1);
   }
}
