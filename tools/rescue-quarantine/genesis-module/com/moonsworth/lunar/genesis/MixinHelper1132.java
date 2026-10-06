package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.HashMultiset;

final class MixinHelper1132<N, E> extends MixinHelper113<N, E> {
   @LazyInit
   private transient Reference<Multiset<N>> adjacentNodesReference;

   private MixinHelper1132(Map<E, N> var1) {
      super(var1);
   }

   static <N, E> MixinHelper1132<N, E> method1() {
      return new MixinHelper1132<>(new HashMap<>(2, 1.0F));
   }

   static <N, E> MixinHelper1132<N, E> method2(Map<E, N> var0) {
      return new MixinHelper1132<>(ImmutableMap.method9(var0));
   }

   @Override
   public Set<N> adjacentNodes() {
      return Collections.unmodifiableSet(this.method3().elementSet());
   }

   private Multiset<N> method3() {
      Multiset var1 = getReference(this.adjacentNodesReference);
      if (var1 == null) {
         var1 = HashMultiset.method3(this.field1.values());
         this.adjacentNodesReference = new SoftReference<>(var1);
      }

      return var1;
   }

   @Override
   public Set<E> edgesConnecting(final N var1) {
      return new AbstractSetBase<E>(this.field1, var1) {
         @Override
         public int size() {
            return MixinHelper1132.this.method3().count(var1);
         }
      };
   }

   @Override
   public N removeInEdge(E var1, boolean var2) {
      return !var2 ? this.removeOutEdge((E)var1) : null;
   }

   @Override
   public N removeOutEdge(E var1) {
      Object var2 = super.removeOutEdge((E)var1);
      Multiset var3 = getReference(this.adjacentNodesReference);
      if (var3 != null) {
         Preconditions.checkState(var3.remove(var2));
      }

      return (N)var2;
   }

   @Override
   public void addInEdge(E var1, N var2, boolean var3) {
      if (!var3) {
         this.addOutEdge((E)var1, (N)var2);
      }
   }

   @Override
   public void addOutEdge(E var1, N var2) {
      super.addOutEdge((E)var1, (N)var2);
      Multiset var3 = getReference(this.adjacentNodesReference);
      if (var3 != null) {
         Preconditions.checkState(var3.add(var2));
      }
   }

   private static <T> @Nullable T getReference(@Nullable Reference<T> var0) {
      return (T)(var0 == null ? null : var0.get());
   }
}
