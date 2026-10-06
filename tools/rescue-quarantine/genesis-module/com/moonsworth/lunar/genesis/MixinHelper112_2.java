package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Iterables;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.graph.Graphs;

abstract class MixinHelper112_2<N, E> implements MixinHelper11_2<N, E> {
   protected final Map<E, N> field1;
   protected final Map<E, N> field2;
   private int selfLoopCount;

   protected MixinHelper112_2(Map<E, N> var1, Map<E, N> var2, int var3) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
      this.selfLoopCount = Graphs.checkNonNegative(var3);
      Preconditions.checkState(var3 <= var1.size() && var3 <= var2.size());
   }

   @Override
   public Set<N> adjacentNodes() {
      return Sets.method3(this.predecessors(), this.successors());
   }

   @Override
   public Set<E> incidentEdges() {
      return new AbstractSet<E>() {
         public MixinHelperIterator3<E> method1() {
            Iterable var1 = MixinHelper112_2.this.selfLoopCount == 0
               ? Iterables.concat(MixinHelper112_2.this.field1.keySet(), MixinHelper112_2.this.field2.keySet())
               : Sets.method3(MixinHelper112_2.this.field1.keySet(), MixinHelper112_2.this.field2.keySet());
            return Iterators.method3(var1.iterator());
         }

         @Override
         public int size() {
            return MixinHelper7_3.saturatedAdd(MixinHelper112_2.this.field1.size(), MixinHelper112_2.this.field2.size() - MixinHelper112_2.this.selfLoopCount);
         }

         @Override
         public boolean contains(@Nullable Object var1) {
            return MixinHelper112_2.this.field1.containsKey(var1) || MixinHelper112_2.this.field2.containsKey(var1);
         }
      };
   }

   @Override
   public Set<E> inEdges() {
      return Collections.unmodifiableSet(this.field1.keySet());
   }

   @Override
   public Set<E> outEdges() {
      return Collections.unmodifiableSet(this.field2.keySet());
   }

   @Override
   public N adjacentNode(E var1) {
      return Preconditions.checkNotNull(this.field2.get(var1));
   }

   @Override
   public N removeInEdge(E var1, boolean var2) {
      if (var2) {
         Graphs.checkNonNegative(--this.selfLoopCount);
      }

      Object var3 = this.field1.remove(var1);
      return Preconditions.checkNotNull((N)var3);
   }

   @Override
   public N removeOutEdge(E var1) {
      Object var2 = this.field2.remove(var1);
      return Preconditions.checkNotNull((N)var2);
   }

   @Override
   public void addInEdge(E var1, N var2, boolean var3) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      if (var3) {
         Graphs.checkPositive(++this.selfLoopCount);
      }

      Object var4 = this.field1.put((E)var1, (N)var2);
      Preconditions.checkState(var4 == null);
   }

   @Override
   public void addOutEdge(E var1, N var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      Object var3 = this.field2.put((E)var1, (N)var2);
      Preconditions.checkState(var3 == null);
   }
}
