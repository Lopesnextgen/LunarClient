package com.moonsworth.lunar.genesis;

final class EndpointPairIterator$Directed<N> extends MixinHelperIterator32<N> {
   private EndpointPairIterator$Directed(BaseGraph<N> mixinhelper621) {
      super(mixinhelper621, null);
   }

   protected IterableBase<N> method4() {
      while (!this.successorIterator.hasNext()) {
         if (!this.method3()) {
            return (IterableBase<N>)this.method1();
         }
      }

      return IterableBase.method1((N)this.node, (N)this.successorIterator.next());
   }
}
