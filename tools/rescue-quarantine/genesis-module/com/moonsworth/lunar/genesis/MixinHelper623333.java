package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.Graphs;
import com.google.common.base.Preconditions;

final class MixinHelper623333<N, V> extends MixinHelper62333<N, V> implements MixinHelper6242<N, V> {
   private final ElementOrder<N> field5;

   MixinHelper623333(MixinHelper8<? super N> var1) {
      super(var1);
      this.field5 = var1.field3.method7();
   }

   @Override
   public ElementOrder<N> method2() {
      return this.field5;
   }

   @CanIgnoreReturnValue
   @Override
   public boolean addNode(N var1) {
      Preconditions.checkNotNull(var1, "node");
      if (this.ORCCIIORRCICHIORRRHICHHCRIRHRO(var1)) {
         return false;
      }

      this.method2((N)var1);
      return true;
   }

   @CanIgnoreReturnValue
   private MixinHelper3_13<N, V> method2(N var1) {
      MixinHelper3_13 var2 = this.method5();
      Preconditions.checkState(this.field4.method1(var1, var2) == null);
      return var2;
   }

   @CanIgnoreReturnValue
   @Override
   public V putEdgeValue(N var1, N var2, V var3) {
      Preconditions.checkNotNull(var1, "nodeU");
      Preconditions.checkNotNull(var2, "nodeV");
      Preconditions.checkNotNull(var3, "value");
      if (!this.allowsSelfLoops()) {
         Preconditions.checkArgument(
            !var1.equals(var2),
            "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.",
            var1
         );
      }

      MixinHelper3_13 var4 = (MixinHelper3_13)this.field4.get(var1);
      if (var4 == null) {
         var4 = this.method2((N)var1);
      }

      Object var5 = var4.addSuccessor(var2, var3);
      MixinHelper3_13 var6 = (MixinHelper3_13)this.field4.get(var2);
      if (var6 == null) {
         var6 = this.method2((N)var2);
      }

      var6.addPredecessor(var1, var3);
      if (var5 == null) {
         Graphs.checkPositive(++this.edgeCount);
      }

      return (V)var5;
   }

   @CanIgnoreReturnValue
   @Override
   public V method1(IterableBase<N> var1, V var2) {
      this.method4(var1);
      return this.putEdgeValue((N)var1.method5(), (N)var1.method6(), (V)var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean removeNode(N var1) {
      Preconditions.checkNotNull(var1, "node");
      MixinHelper3_13 var2 = (MixinHelper3_13)this.field4.get(var1);
      if (var2 == null) {
         return false;
      }

      if (this.allowsSelfLoops() && var2.removeSuccessor(var1) != null) {
         var2.removePredecessor(var1);
         this.edgeCount--;
      }

      for (Object var4 : var2.successors()) {
         ((MixinHelper3_13)this.field4.method4(var4)).removePredecessor(var1);
         this.edgeCount--;
      }

      if (this.isDirected()) {
         for (Object var6 : var2.predecessors()) {
            Preconditions.checkState(((MixinHelper3_13)this.field4.method4(var6)).removeSuccessor(var1) != null);
            this.edgeCount--;
         }
      }

      this.field4.method2(var1);
      Graphs.checkNonNegative(this.edgeCount);
      return true;
   }

   @CanIgnoreReturnValue
   @Override
   public V removeEdge(N var1, N var2) {
      Preconditions.checkNotNull(var1, "nodeU");
      Preconditions.checkNotNull(var2, "nodeV");
      MixinHelper3_13 var3 = (MixinHelper3_13)this.field4.get(var1);
      MixinHelper3_13 var4 = (MixinHelper3_13)this.field4.get(var2);
      if (var3 != null && var4 != null) {
         Object var5 = var3.removeSuccessor(var2);
         if (var5 != null) {
            var4.removePredecessor(var1);
            Graphs.checkNonNegative(--this.edgeCount);
         }

         return (V)var5;
      } else {
         return null;
      }
   }

   @CanIgnoreReturnValue
   @Override
   public V method2(IterableBase<N> var1) {
      this.method4(var1);
      return this.removeEdge((N)var1.method5(), (N)var1.method6());
   }

   private MixinHelper3_13<N, V> method5() {
      return this.isDirected() ? MixinHelper32_2.method1(this.field5) : MixinHelper33_2.method1(this.field5);
   }
}
