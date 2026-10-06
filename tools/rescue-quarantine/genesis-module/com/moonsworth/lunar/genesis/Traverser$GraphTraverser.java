package com.moonsworth.lunar.genesis;
import com.google.common.graph.PredecessorsFunction;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;

final class Traverser$GraphTraverser<N> extends MixinHelper_14<N> {
   private final PredecessorsFunction<N> field1;

   Traverser$GraphTraverser(PredecessorsFunction<N> mixinhelper6_71) {
      super(null);
      this.field1 = (PredecessorsFunction<N>)Preconditions.checkNotNull(mixinhelper6_71);
   }

   public Iterable<N> breadthFirst(N value1) {
      Preconditions.checkNotNull(value1);
      return this.breadthFirst(ImmutableSet.method2(value1));
   }

   public Iterable<N> breadthFirst(Iterable<? extends N> list1) {
      Preconditions.checkNotNull(list1);
      if (Iterables.isEmpty(list1)) {
         return ImmutableSet.method3();
      }

      for (Object obj3 : list1) {
         this.checkThatNodeIsInGraph((N)obj3);
      }

      return new Data53$1(this, list1);
   }

   public Iterable<N> depthFirstPreOrder(N value1) {
      Preconditions.checkNotNull(value1);
      return this.depthFirstPreOrder(ImmutableSet.method2(value1));
   }

   public Iterable<N> depthFirstPreOrder(Iterable<? extends N> list1) {
      Preconditions.checkNotNull(list1);
      if (Iterables.isEmpty(list1)) {
         return ImmutableSet.method3();
      }

      for (Object obj3 : list1) {
         this.checkThatNodeIsInGraph((N)obj3);
      }

      return new Data53$2(this, list1);
   }

   public Iterable<N> depthFirstPostOrder(N value1) {
      Preconditions.checkNotNull(value1);
      return this.depthFirstPostOrder(ImmutableSet.method2(value1));
   }

   public Iterable<N> depthFirstPostOrder(Iterable<? extends N> list1) {
      Preconditions.checkNotNull(list1);
      if (Iterables.isEmpty(list1)) {
         return ImmutableSet.method3();
      }

      for (Object obj3 : list1) {
         this.checkThatNodeIsInGraph((N)obj3);
      }

      return new Data53$3(this, list1);
   }

   private void checkThatNodeIsInGraph(N value1) {
      this.field1.successors(value1);
   }
}
