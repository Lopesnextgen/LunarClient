package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

abstract class AbstractSetBase2<N> extends AbstractSet<IterableBase<N>> {
   protected final N field1;
   protected final MixinHelper62<N> field2;

   AbstractSetBase2(MixinHelper62<N> var1, N var2) {
      this.field2 = var1;
      this.field1 = (N)var2;
   }

   @Override
   public boolean remove(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int size() {
      return this.field2.isDirected()
         ? this.field2.inDegree(this.field1) + this.field2.outDegree(this.field1) - (this.field2.successors(this.field1).contains(this.field1) ? 1 : 0)
         : this.field2.adjacentNodes(this.field1).size();
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      if (!(var1 instanceof IterableBase)) {
         return false;
      }

      IterableBase var2 = (IterableBase)var1;
      if (this.field2.isDirected()) {
         if (!var2.isOrdered()) {
            return false;
         }

         Object var6 = var2.source();
         Object var7 = var2.target();
         return this.field1.equals(var6) && this.field2.successors(this.field1).contains(var7)
            || this.field1.equals(var7) && this.field2.predecessors(this.field1).contains(var6);
      } else {
         if (var2.isOrdered()) {
            return false;
         }

         Set var3 = this.field2.adjacentNodes(this.field1);
         Object var4 = var2.method5();
         Object var5 = var2.method6();
         return this.field1.equals(var5) && var3.contains(var4) || this.field1.equals(var4) && var3.contains(var5);
      }
   }
}
