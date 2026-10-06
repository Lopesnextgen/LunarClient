package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Set;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

abstract class MixinHelperIterator32<N> extends MixinHelperIterator32_2<IterableBase<N>> {
   private final MixinHelper62<N> field2;
   private final Iterator<N> field3;
   protected N node = (N)null;
   protected Iterator<N> successorIterator = ImmutableSet.<N>method3().method1();

   static <N> MixinHelperIterator32<N> method1(MixinHelper62<N> var0) {
      return var0.isDirected() ? new MixinHelperIterator32.Data2<>(var0) : new MixinHelperIterator32.Data<>(var0);
   }

   private MixinHelperIterator32(MixinHelper62<N> var1) {
      this.field2 = var1;
      this.field3 = var1.nodes().iterator();
   }

   protected final boolean method3() {
      Preconditions.checkState(!this.successorIterator.hasNext());
      if (!this.field3.hasNext()) {
         return false;
      }

      this.node = this.field3.next();
      this.successorIterator = this.field2.successors(this.node).iterator();
      return true;
   }

   private static final class Data<N> extends MixinHelperIterator32<N> {
      private Set<N> visitedNodes;

      private Data(MixinHelper62<N> var1) {
         super(var1);
         this.visitedNodes = Sets.newHashSetWithExpectedSize(var1.nodes().size());
      }

      protected IterableBase<N> method4() {
         while (true) {
            if (this.successorIterator.hasNext()) {
               Object var1 = this.successorIterator.next();
               if (!this.visitedNodes.contains(var1)) {
                  return IterableBase.method2(this.node, (N)var1);
               }
            } else {
               this.visitedNodes.add(this.node);
               if (!this.method3()) {
                  this.visitedNodes = null;
                  return (IterableBase<N>)this.method1();
               }
            }
         }
      }
   }

   private static final class Data2<N> extends MixinHelperIterator32<N> {
      private Data2(MixinHelper62<N> var1) {
         super(var1);
      }

      protected IterableBase<N> method4() {
         while (!this.successorIterator.hasNext()) {
            if (!this.method3()) {
               return (IterableBase<N>)this.method1();
            }
         }

         return IterableBase.method1(this.node, this.successorIterator.next());
      }
   }
}
