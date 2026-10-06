package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import com.google.common.base.Preconditions;

abstract class MixinHelper113<N, E> implements MixinHelper11_2<N, E> {
   protected final Map<E, N> field1;

   protected MixinHelper113(Map<E, N> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public Set<N> predecessors() {
      return this.adjacentNodes();
   }

   @Override
   public Set<N> successors() {
      return this.adjacentNodes();
   }

   @Override
   public Set<E> incidentEdges() {
      return Collections.unmodifiableSet(this.field1.keySet());
   }

   @Override
   public Set<E> inEdges() {
      return this.incidentEdges();
   }

   @Override
   public Set<E> outEdges() {
      return this.incidentEdges();
   }

   @Override
   public N adjacentNode(E var1) {
      return Preconditions.checkNotNull(this.field1.get(var1));
   }

   @Override
   public N removeInEdge(E var1, boolean var2) {
      return !var2 ? this.removeOutEdge((E)var1) : null;
   }

   @Override
   public N removeOutEdge(E var1) {
      Object var2 = this.field1.remove(var1);
      return Preconditions.checkNotNull((N)var2);
   }

   @Override
   public void addInEdge(E var1, N var2, boolean var3) {
      if (!var3) {
         this.addOutEdge((E)var1, (N)var2);
      }
   }

   @Override
   public void addOutEdge(E var1, N var2) {
      Object var3 = this.field1.put((E)var1, (N)var2);
      Preconditions.checkState(var3 == null);
   }
}
