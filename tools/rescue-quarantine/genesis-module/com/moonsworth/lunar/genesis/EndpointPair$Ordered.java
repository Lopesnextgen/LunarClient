package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;

final class EndpointPair$Ordered<N> extends IterableBase<N> {
   private EndpointPair$Ordered(N value1, N value2) {
      super(value1, value2, null);
   }

   public N source() {
      return (N)this.method5();
   }

   public N target() {
      return (N)this.method6();
   }

   public boolean isOrdered() {
      return true;
   }

   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      }

      if (!(obj1 instanceof IterableBase)) {
         return false;
      }

      IterableBase iterablebase2 = (IterableBase)obj1;
      return this.isOrdered() != iterablebase2.isOrdered() ? false : this.source().equals(iterablebase2.source()) && this.target().equals(iterablebase2.target());
   }

   public int hashCode() {
      return Objects.hashCode(new Object[]{this.source(), this.target()});
   }

   public String toString() {
      return "<" + this.source() + " -> " + this.target() + ">";
   }
}
