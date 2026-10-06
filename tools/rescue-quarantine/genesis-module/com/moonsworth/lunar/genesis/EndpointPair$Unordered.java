package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

final class EndpointPair$Unordered<N> extends IterableBase<N> {
   private EndpointPair$Unordered(N value1, N value2) {
      super(value1, value2, null);
   }

   public N source() {
      throw new UnsupportedOperationException(
         "Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't."
      );
   }

   public N target() {
      throw new UnsupportedOperationException(
         "Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't."
      );
   }

   public boolean isOrdered() {
      return false;
   }

   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof IterableBase)) {
         return false;
      } else {
         IterableBase iterablebase2 = (IterableBase)obj1;
         if (this.isOrdered() != iterablebase2.isOrdered()) {
            return false;
         } else {
            return this.method5().equals(iterablebase2.method5())
               ? this.method6().equals(iterablebase2.method6())
               : this.method5().equals(iterablebase2.method6()) && this.method6().equals(iterablebase2.method5());
         }
      }
   }

   public int hashCode() {
      return this.method5().hashCode() + this.method6().hashCode();
   }

   public String toString() {
      return "[" + this.method5() + ", " + this.method6() + "]";
   }
}
