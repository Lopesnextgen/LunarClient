package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation2
public abstract class MixinHelper6232<N> extends MixinHelper623<N> implements MixinHelper622<N> {
   @Override
   public final boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof MixinHelper622)) {
         return false;
      }

      MixinHelper622 var2 = (MixinHelper622)var1;
      return this.isDirected() == var2.isDirected() && this.nodes().equals(var2.nodes()) && this.edges().equals(var2.edges());
   }

   @Override
   public final int hashCode() {
      return this.edges().hashCode();
   }

   @Override
   public String toString() {
      return "isDirected: " + this.isDirected() + ", allowsSelfLoops: " + this.allowsSelfLoops() + ", nodes: " + this.nodes() + ", edges: " + this.edges();
   }
}
