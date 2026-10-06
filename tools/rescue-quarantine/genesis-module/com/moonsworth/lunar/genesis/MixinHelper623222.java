package com.moonsworth.lunar.genesis;

final class MixinHelper623222<N> extends MixinHelper62322<N> implements MixinHelper6222<N> {
   private final MixinHelper6242<N, MixinHelper2$Type6> field1;

   MixinHelper623222(MixinHelper8<? super N> var1) {
      this.field1 = new MixinHelper623333<>(var1);
   }

   @Override
   protected MixinHelper62<N> method3() {
      return this.field1;
   }

   @Override
   public boolean addNode(N var1) {
      return this.field1.addNode((N)var1);
   }

   @Override
   public boolean putEdge(N var1, N var2) {
      return this.field1.putEdgeValue((N)var1, (N)var2, MixinHelper2$Type6.EDGE_EXISTS) == null;
   }

   @Override
   public boolean method1(IterableBase<N> var1) {
      this.method4(var1);
      return this.putEdge((N)var1.method5(), (N)var1.method6());
   }

   @Override
   public boolean removeNode(N var1) {
      return this.field1.removeNode((N)var1);
   }

   @Override
   public boolean removeEdge(N var1, N var2) {
      return this.field1.removeEdge((N)var1, (N)var2) != null;
   }

   @Override
   public boolean method2(IterableBase<N> var1) {
      this.method4(var1);
      return this.removeEdge((N)var1.method5(), (N)var1.method6());
   }
}
