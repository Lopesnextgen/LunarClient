package com.moonsworth.lunar.genesis;
import com.google.common.base.Preconditions;

abstract class DirectedGraphConnections$NodeConnection<N> {
   final N field1;

   DirectedGraphConnections$NodeConnection(N value1) {
      this.field1 = (N)Preconditions.checkNotNull(value1);
   }
}
