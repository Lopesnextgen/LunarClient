package com.moonsworth.lunar.genesis;
import com.google.common.graph.ElementOrder;
import com.google.common.base.Optional;

abstract class AbstractGraphBuilder<N> {
   final boolean field1;
   boolean allowsSelfLoops = false;
   ElementOrder<N> field2 = ElementOrder.method3();
   ElementOrder<N> field3 = ElementOrder.method1();
   Optional<Integer> field4 = Optional.method1();

   AbstractGraphBuilder(boolean flag1) {
      this.field1 = flag1;
   }
}
