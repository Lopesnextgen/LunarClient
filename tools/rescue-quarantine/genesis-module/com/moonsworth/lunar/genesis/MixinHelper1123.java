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

final class MixinHelper1123<N, E> extends MixinHelper112_2<N, E> {
   @LazyInit
   private transient Reference<Multiset<N>> predecessorsReference;
   @LazyInit
   private transient Reference<Multiset<N>> successorsReference;

   private MixinHelper1123(Map<E, N> var1, Map<E, N> var2, int var3) {
      super(var1, var2, var3);
   }

   static <N, E> MixinHelper1123<N, E> method1() {
      return new MixinHelper1123<>(new HashMap<>(2, 1.0F), new HashMap<>(2, 1.0F), 0);
   }

   static <N, E> MixinHelper1123<N, E> method2(Map<E, N> var0, Map<E, N> var1, int var2) {
      return new MixinHelper1123<>(ImmutableMap.method9(var0), ImmutableMap.method9(var1), var2);
   }

   @Override
   public Set<N> predecessors() {
      return Collections.unmodifiableSet(this.method3().elementSet());
   }

   private Multiset<N> method3() {
      Multiset var1 = getReference(this.predecessorsReference);
      if (var1 == null) {
         var1 = HashMultiset.method3(this.CCCOCCOICIRIOOIIOIHIOOCHOIIOIO.values());
         this.predecessorsReference = new SoftReference<>(var1);
      }

      return var1;
   }

   @Override
   public Set<N> successors() {
      return Collections.unmodifiableSet(this.method4().elementSet());
   }

   private Multiset<N> method4() {
      Multiset var1 = getReference(this.successorsReference);
      if (var1 == null) {
         var1 = HashMultiset.method3(this.IOHRCCOIOOIRCHCOICIHHCCHRORCOH.values());
         this.successorsReference = new SoftReference<>(var1);
      }

      return var1;
   }

   @Override
   public Set<E> edgesConnecting(final N var1) {
      return new AbstractSetBase<E>(this.IOHRCCOIOOIRCHCOICIHHCCHRORCOH, var1) {
         @Override
         public int size() {
            return MixinHelper1123.this.method4().count(var1);
         }
      };
   }

   @Override
   public N removeInEdge(E var1, boolean var2) {
      Object var3 = super.removeInEdge((E)var1, var2);
      Multiset var4 = getReference(this.predecessorsReference);
      if (var4 != null) {
         Preconditions.checkState(var4.remove(var3));
      }

      return (N)var3;
   }

   @Override
   public N removeOutEdge(E var1) {
      Object var2 = super.removeOutEdge((E)var1);
      Multiset var3 = getReference(this.successorsReference);
      if (var3 != null) {
         Preconditions.checkState(var3.remove(var2));
      }

      return (N)var2;
   }

   @Override
   public void addInEdge(E var1, N var2, boolean var3) {
      super.addInEdge((E)var1, (N)var2, var3);
      Multiset var4 = getReference(this.predecessorsReference);
      if (var4 != null) {
         Preconditions.checkState(var4.add(var2));
      }
   }

   @Override
   public void addOutEdge(E var1, N var2) {
      super.addOutEdge((E)var1, (N)var2);
      Multiset var3 = getReference(this.successorsReference);
      if (var3 != null) {
         Preconditions.checkState(var3.add(var2));
      }
   }

   private static <T> @Nullable T getReference(@Nullable Reference<T> var0) {
      return (T)(var0 == null ? null : var0.get());
   }
}
