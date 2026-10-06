package com.moonsworth.lunar.genesis;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.Traverser;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;

final class MixinHelper$Data53<N> extends Traverser<N> {
   private final MixinHelper6_7<N> field1;

   MixinHelper$Data53(MixinHelper6_7<N> var1) {
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
         this.checkThatNodeIsInGraph((N)var3);
      }

      return new Iterable<N>() {
         @Override
         public Iterator<N> iterator() {
            return MixinHelper$Data53.this.new Data2(var1);
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
         this.checkThatNodeIsInGraph((N)var3);
      }

      return new Iterable<N>() {
         @Override
         public Iterator<N> iterator() {
            return MixinHelper$Data53.this.new Data(var1, MixinHelper$Type5.PREORDER);
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
         this.checkThatNodeIsInGraph((N)var3);
      }

      return new Iterable<N>() {
         @Override
         public Iterator<N> iterator() {
            return MixinHelper$Data53.this.new Data(var1, MixinHelper$Type5.POSTORDER);
         }
      };
   }

   private void checkThatNodeIsInGraph(N var1) {
      this.field1.successors((N)var1);
   }

   private final class Data extends MixinHelperIterator32_2<N> {
      private final Deque<MixinHelper$Data53<N>.Data.Data> field2 = new ArrayDeque<>();
      private final Set<N> field3 = new HashSet<>();
      private final MixinHelper$Type5 field4;

      Data(Iterable<? extends N> var2, MixinHelper$Type5 var3) {
         this.field2.push(new MixinHelper$Data53.Data.Data(null, var2));
         this.field4 = var3;
      }

      @Override
      protected N computeNext() {
         while (!this.field2.isEmpty()) {
            MixinHelper$Data53.Data.Data var1 = this.field2.getFirst();
            boolean var2 = this.field3.add(var1.field1);
            boolean var3 = !var1.field2.hasNext();
            boolean var4 = var2 && this.field4 == MixinHelper$Type5.PREORDER || var3 && this.field4 == MixinHelper$Type5.POSTORDER;
            if (var3) {
               this.field2.pop();
            } else {
               Object var5 = var1.field2.next();
               if (!this.field3.contains(var5)) {
                  this.field2.push(this.method1((N)var5));
               }
            }

            if (var4 && var1.field1 != null) {
               return var1.field1;
            }
         }

         return (N)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
      }

      MixinHelper$Data53<N>.Data.Data method1(N var1) {
         return new MixinHelper$Data53.Data.Data(var1, MixinHelper$Data53.this.field1.successors((N)var1));
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
      private final Set<N> field2 = new HashSet<>();

      Data2(Iterable<? extends N> var2) {
         for (Object var4 : var2) {
            if (this.field2.add((N)var4)) {
               this.field1.add((N)var4);
            }
         }
      }

      @Override
      public boolean hasNext() {
         return !this.field1.isEmpty();
      }

      @Override
      public N next() {
         Object var1 = this.field1.remove();

         for (Object var3 : MixinHelper$Data53.this.field1.successors((N)var1)) {
            if (this.field2.add((N)var3)) {
               this.field1.add((N)var3);
            }
         }

         return (N)var1;
      }
   }
}
