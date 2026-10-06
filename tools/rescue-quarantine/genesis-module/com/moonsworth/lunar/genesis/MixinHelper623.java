package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.graph.ElementOrder;
import com.google.common.collect.Iterators;

abstract class MixinHelper623<N> implements MixinHelper62<N> {
   protected long edgeCount() {
      long var1 = 0L;

      for (Object var4 : this.nodes()) {
         var1 += this.degree((N)var4);
      }

      Preconditions.checkState((var1 & 1L) == 0L);
      return var1 >>> 1;
   }

   @Override
   public Set<IterableBase<N>> edges() {
      return new AbstractSet<IterableBase<N>>() {
         public MixinHelperIterator3<IterableBase<N>> method1() {
            return MixinHelperIterator32.method1(MixinHelper623.this);
         }

         @Override
         public int size() {
            return MixinHelper122.saturatedCast(MixinHelper623.this.edgeCount());
         }

         @Override
         public boolean remove(Object var1) {
            throw new UnsupportedOperationException();
         }

         @Override
         public boolean contains(@Nullable Object var1) {
            if (!(var1 instanceof IterableBase)) {
               return false;
            }

            IterableBase var2 = (IterableBase)var1;
            return MixinHelper623.this.method5(var2)
               && MixinHelper623.this.nodes().contains(var2.method5())
               && MixinHelper623.this.successors(var2.method5()).contains(var2.method6());
         }
      };
   }

   @Override
   public ElementOrder<N> method2() {
      return ElementOrder.method1();
   }

   @Override
   public Set<IterableBase<N>> incidentEdges(N var1) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkArgument(this.nodes().contains(var1), "Node %s is not an element of this graph.", var1);
      return new AbstractSetBase2<N>(this, var1) {
         public MixinHelperIterator3<IterableBase<N>> method1() {
            return this.HRHIHIOCHHRHHIOIRHORHCROHCCCCI.isDirected()
               ? Iterators.method3(
                  Iterators.concat(
                     Iterators.method17(
                        this.HRHIHIOCHHRHHIOIRHORHCROHCCCCI.predecessors(this.RHOIIOHOROHCCHOHCOIIRRROCRHRIC).iterator(),
                        new MixinHelper24_2<N, IterableBase<N>>() {
                           public IterableBase<N> method1(N var1) {
                              return IterableBase.method1((N)var1, (N)RHOIIOHOROHCCHOHCOIIRRROCRHRIC);
                           }
                        }
                     ),
                     Iterators.method17(
                        Sets.method5(
                              this.HRHIHIOCHHRHHIOIRHORHCROHCCCCI.successors(this.RHOIIOHOROHCCHOHCOIIRRROCRHRIC),
                              ImmutableSet.method2(this.RHOIIOHOROHCCHOHCOIIRRROCRHRIC)
                           )
                           .method2(),
                        new MixinHelper24_2<N, IterableBase<N>>() {
                           public IterableBase<N> method1(N var1) {
                              return IterableBase.method1((N)RHOIIOHOROHCCHOHCOIIRRROCRHRIC, (N)var1);
                           }
                        }
                     )
                  )
               )
               : Iterators.method3(
                  Iterators.method17(
                     this.HRHIHIOCHHRHHIOIRHORHCROHCCCCI.adjacentNodes(this.RHOIIOHOROHCCHOHCOIIRRROCRHRIC).iterator(),
                     new MixinHelper24_2<N, IterableBase<N>>() {
                        public IterableBase<N> method1(N var1) {
                           return IterableBase.method2((N)RHOIIOHOROHCCHOHCOIIRRROCRHRIC, (N)var1);
                        }
                     }
                  )
               );
         }
      };
   }

   @Override
   public int degree(N var1) {
      if (this.isDirected()) {
         return MixinHelper7_3.saturatedAdd(this.predecessors((N)var1).size(), this.successors((N)var1).size());
      }

      Set var2 = this.adjacentNodes((N)var1);
      int var3 = this.allowsSelfLoops() && var2.contains(var1) ? 1 : 0;
      return MixinHelper7_3.saturatedAdd(var2.size(), var3);
   }

   @Override
   public int inDegree(N var1) {
      return this.isDirected() ? this.predecessors((N)var1).size() : this.degree((N)var1);
   }

   @Override
   public int outDegree(N var1) {
      return this.isDirected() ? this.successors((N)var1).size() : this.degree((N)var1);
   }

   @Override
   public boolean hasEdgeConnecting(N var1, N var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      return this.nodes().contains(var1) && this.successors((N)var1).contains(var2);
   }

   @Override
   public boolean method3(IterableBase<N> var1) {
      Preconditions.checkNotNull(var1);
      if (!this.method5(var1)) {
         return false;
      }

      Object var2 = var1.method5();
      Object var3 = var1.method6();
      return this.nodes().contains(var2) && this.successors((N)var2).contains(var3);
   }

   protected final void method4(IterableBase<?> var1) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkArgument(this.method5(var1), "Mismatch: unordered endpoints cannot be used with directed graphs");
   }

   protected final boolean method5(IterableBase<?> var1) {
      return var1.isOrdered() || !this.isDirected();
   }
}
