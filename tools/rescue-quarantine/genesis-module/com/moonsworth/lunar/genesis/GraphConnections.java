package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

interface GraphConnections<N, V> {
   Set<N> adjacentNodes();

   Set<N> predecessors();

   Set<N> successors();

   Iterator<IterableBase<N>> incidentEdgeIterator(N value1);

   @Nullable V value(N value1);

   void removePredecessor(N value1);

   @CanIgnoreReturnValue
   V removeSuccessor(N value1);

   void addPredecessor(N value1, V value2);

   @CanIgnoreReturnValue
   V addSuccessor(N value1, V value2);
}
