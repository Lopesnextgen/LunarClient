package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

abstract class IncidentEdgeSet<N> extends AbstractSet<IterableBase<N>> {
   protected final N field1;
   protected final BaseGraph<N> field2;

   IncidentEdgeSet(BaseGraph<N> mixinhelper621, N value2) {
      this.field2 = mixinhelper621;
      this.field1 = (N)value2;
   }

   @Override
   public boolean remove(Object obj1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int size() {
      return this.field2.isDirected()
         ? this.field2.inDegree(this.field1) + this.field2.outDegree(this.field1) - (this.field2.successors(this.field1).contains(this.field1) ? 1 : 0)
         : this.field2.adjacentNodes(this.field1).size();
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      if (!(obj1 instanceof IterableBase)) {
         return false;
      }

      IterableBase iterablebase2 = (IterableBase)obj1;
      if (this.field2.isDirected()) {
         if (!iterablebase2.isOrdered()) {
            return false;
         }

         Object obj6 = iterablebase2.source();
         Object obj7 = iterablebase2.target();
         return this.field1.equals(obj6) && this.field2.successors(this.field1).contains(obj7)
            || this.field1.equals(obj7) && this.field2.predecessors(this.field1).contains(obj6);
      } else {
         if (iterablebase2.isOrdered()) {
            return false;
         }

         Set set3 = this.field2.adjacentNodes(this.field1);
         Object obj4 = iterablebase2.method5();
         Object obj5 = iterablebase2.method6();
         return this.field1.equals(obj5) && set3.contains(obj4) || this.field1.equals(obj4) && set3.contains(obj5);
      }
   }
}
