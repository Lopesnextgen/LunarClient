package com.moonsworth.lunar.genesis;

import java.util.Set;
import com.google.common.collect.Sets;

final class EndpointPairIterator$Undirected<N> extends MixinHelperIterator32<N> {
   private Set<N> visitedNodes;

   private EndpointPairIterator$Undirected(BaseGraph<N> mixinhelper621) {
      super(mixinhelper621, null);
      this.visitedNodes = Sets.newHashSetWithExpectedSize(mixinhelper621.nodes().size());
   }

   protected IterableBase<N> method4() {
      while (true) {
         if (this.successorIterator.hasNext()) {
            Object obj1 = this.successorIterator.next();
            if (!this.visitedNodes.contains(obj1)) {
               return IterableBase.method2(this.node, obj1);
            }
         } else {
            this.visitedNodes.add((N)this.node);
            if (!this.method3()) {
               this.visitedNodes = null;
               return (IterableBase<N>)this.method1();
            }
         }
      }
   }
}
