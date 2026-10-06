package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

final class MixinHelper63332<N, E> extends MixinHelper6333<N, E> implements MixinHelper632<N, E> {
   MixinHelper63332(MixinHelper83<? super N, ? super E> var1) {
      super(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean addNode(N var1) {
      Preconditions.checkNotNull(var1, "node");
      if (this.CIIIOIOHCCIOIIIRRHCRROIOIICCOO(var1)) {
         return false;
      }

      this.method1((N)var1);
      return true;
   }

   @CanIgnoreReturnValue
   private MixinHelper11_2<N, E> method1(N var1) {
      MixinHelper11_2 var2 = this.method4();
      Preconditions.checkState(this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.method1(var1, var2) == null);
      return var2;
   }

   @CanIgnoreReturnValue
   @Override
   public boolean addEdge(N var1, N var2, E var3) {
      Preconditions.checkNotNull(var1, "nodeU");
      Preconditions.checkNotNull(var2, "nodeV");
      Preconditions.checkNotNull(var3, "edge");
      if (this.CICIOOHRIHORIICCRRCOROCROOHORH(var3)) {
         IterableBase var7 = this.method4(var3);
         IterableBase var8 = IterableBase.method4(this, var1, var2);
         Preconditions.checkArgument(
            var7.equals(var8),
            "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.",
            var3,
            var7,
            var8
         );
         return false;
      }

      MixinHelper11_2 var4 = (MixinHelper11_2)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(var1);
      if (!this.allowsParallelEdges()) {
         Preconditions.checkArgument(
            var4 == null || !var4.successors().contains(var2),
            "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.",
            var1,
            var2
         );
      }

      boolean var5 = var1.equals(var2);
      if (!this.allowsSelfLoops()) {
         Preconditions.checkArgument(
            !var5,
            "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.",
            var1
         );
      }

      if (var4 == null) {
         var4 = this.method1((N)var1);
      }

      var4.addOutEdge(var3, var2);
      MixinHelper11_2 var6 = (MixinHelper11_2)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(var2);
      if (var6 == null) {
         var6 = this.method1((N)var2);
      }

      var6.addInEdge(var3, var1, var5);
      this.RROCCOOOCIIHIOORHHHHRCRIHRCCCC.method1(var3, var1);
      return true;
   }

   @CanIgnoreReturnValue
   @Override
   public boolean method1(IterableBase<N> var1, E var2) {
      this.ORCHOHHCOHCORRICRIHCHHRORHHCHH(var1);
      return this.addEdge((N)var1.method5(), (N)var1.method6(), (E)var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean removeNode(N var1) {
      Preconditions.checkNotNull(var1, "node");
      MixinHelper11_2 var2 = (MixinHelper11_2)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(var1);
      if (var2 == null) {
         return false;
      }

      MixinHelperIterator3 var3 = ImmutableList.method15(var2.incidentEdges()).method1();

      while (var3.hasNext()) {
         Object var4 = var3.next();
         this.removeEdge((E)var4);
      }

      this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.method2(var1);
      return true;
   }

   @CanIgnoreReturnValue
   @Override
   public boolean removeEdge(E var1) {
      Preconditions.checkNotNull(var1, "edge");
      Object var2 = this.RROCCOOOCIIHIOORHHHHRCRIHRCCCC.get(var1);
      if (var2 == null) {
         return false;
      }

      MixinHelper11_2 var3 = (MixinHelper11_2)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(var2);
      Object var4 = var3.adjacentNode(var1);
      MixinHelper11_2 var5 = (MixinHelper11_2)this.ICCRRIIOIHROCRCHIIOIRCHCOCCOIH.get(var4);
      var3.removeOutEdge(var1);
      var5.removeInEdge(var1, this.allowsSelfLoops() && var2.equals(var4));
      this.RROCCOOOCIIHIOORHHHHRCRIHRCCCC.method2(var1);
      return true;
   }

   private MixinHelper11_2<N, E> method4() {
      return this.isDirected()
         ? (this.allowsParallelEdges() ? MixinHelper1123.method1() : MixinHelper1122_2.method1())
         : (this.allowsParallelEdges() ? MixinHelper1132.method1() : MixinHelper1133.method1());
   }
}
