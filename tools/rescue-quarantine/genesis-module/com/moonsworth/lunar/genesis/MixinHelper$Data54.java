package com.moonsworth.lunar.genesis;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.Traverser;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;

final class MixinHelper$Data54<N> extends Traverser<N> {
   private final MixinHelper6_7<N> field1;

   MixinHelper$Data54(MixinHelper6_7<N> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public Iterable<N> breadthFirst(N var1) {
      Preconditions.checkNotNull(var1);
      return this.breadthFirst(ImmutableSet.method2((N)var1));
   }

   @Override
   public Iterable<N> breadthFirst(final Iterable<? extends N> var1) {
      Preconditions.checkNotNull(var1);
      if (Iterables.isEmpty(var1)) {
         return ImmutableSet.method3();
      }

      for (Object var3 : var1) {
         this.checkThatNodeIsInTree((N)var3);
      }

      return new Iterable<N>() {
         @Override
         public Iterator<N> iterator() {
            return MixinHelper$Data54.this.new Data2(var1);
         }
      };
   }

   @Override
   public Iterable<N> depthFirstPreOrder(N var1) {
      Preconditions.checkNotNull(var1);
      return this.depthFirstPreOrder(ImmutableSet.method2((N)var1));
   }

   @Override
   public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> var1) {
      Preconditions.checkNotNull(var1);
      if (Iterables.isEmpty(var1)) {
         return ImmutableSet.method3();
      }

      for (Object var3 : var1) {
         this.checkThatNodeIsInTree((N)var3);
      }

      return new Iterable<N>() {
         @Override
         public Iterator<N> iterator() {
            return MixinHelper$Data54.this.new Data3(var1);
         }
      };
   }

   @Override
   public Iterable<N> depthFirstPostOrder(N var1) {
      Preconditions.checkNotNull(var1);
      return this.depthFirstPostOrder(ImmutableSet.method2((N)var1));
   }

   @Override
   public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> var1) {
      Preconditions.checkNotNull(var1);
      if (Iterables.isEmpty(var1)) {
         return ImmutableSet.method3();
      }

      for (Object var3 : var1) {
         this.checkThatNodeIsInTree((N)var3);
      }

      return new Iterable<N>() {
         @Override
         public Iterator<N> iterator() {
            return MixinHelper$Data54.this.new Data(var1);
         }
      };
   }

   private void checkThatNodeIsInTree(N var1) {
      this.field1.successors((N)var1);
   }

   private final class Data extends MixinHelperIterator32_2<N> {
      private final ArrayDeque<MixinHelper$Data54<N>.Data.Data> field2 = new ArrayDeque<>();

      Data(Iterable<? extends N> var2) {
         this.field2.addLast(new MixinHelper$Data54.Data.Data(null, var2));
      }

      @Override
      protected N computeNext() {
         while (!this.field2.isEmpty()) {
            MixinHelper$Data54.Data.Data var1 = this.field2.getLast();
            if (var1.field2.hasNext()) {
               Object var2 = var1.field2.next();
               this.field2.addLast(this.method1((N)var2));
            } else {
               this.field2.removeLast();
               if (var1.field1 != null) {
                  return var1.field1;
               }
            }
         }

         return (N)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
      }

      MixinHelper$Data54<N>.Data.Data method1(N var1) {
         return new MixinHelper$Data54.Data.Data(var1, MixinHelper$Data54.this.field1.successors((N)var1));
      }

      private final class Data {
         final @Nullable N field1;
         final Iterator<? extends N> field2;

         Data(@Nullable N var2, Iterable<? extends N> var3) {
            this.field1 = (N)var2;
            this.field2 = var3.iterator();
         }
      }
   }

   private final class Data2 extends MixinHelperIterator3<N> {
      private final Queue<N> field1 = new ArrayDeque<>();

      Data2(Iterable<? extends N> var2) {
         for (Object var4 : var2) {
            this.field1.add((N)var4);
         }
      }

      @Override
      public boolean hasNext() {
         return !this.field1.isEmpty();
      }

      @Override
      public N next() {
         Object var1 = this.field1.remove();
         Iterables.addAll(this.field1, MixinHelper$Data54.this.field1.successors((N)var1));
         return (N)var1;
      }
   }

   private final class Data3 extends MixinHelperIterator3<N> {
      private final Deque<Iterator<? extends N>> field1 = new ArrayDeque<>();

      Data3(Iterable<? extends N> var2) {
         this.field1.addLast(var2.iterator());
      }

      @Override
      public boolean hasNext() {
         return !this.field1.isEmpty();
      }

      @Override
      public N next() {
         Iterator var1 = this.field1.getLast();
         Object var2 = Preconditions.checkNotNull(var1.next());
         if (!var1.hasNext()) {
            this.field1.removeLast();
         }

         Iterator var3 = MixinHelper$Data54.this.field1.successors((N)var2).iterator();
         if (var3.hasNext()) {
            this.field1.addLast(var3);
         }

         return (N)var2;
      }
   }
}
