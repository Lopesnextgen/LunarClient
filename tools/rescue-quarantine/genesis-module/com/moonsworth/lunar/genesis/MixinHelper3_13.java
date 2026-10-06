package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

interface MixinHelper3_13<N, V> {
   Set<N> adjacentNodes();

   Set<N> predecessors();

   Set<N> successors();

   Iterator<IterableBase<N>> incidentEdgeIterator(N var1);

   @Nullable V value(N var1);

   void removePredecessor(N var1);

   @CanIgnoreReturnValue
   V removeSuccessor(N var1);

   void addPredecessor(N var1, V var2);

   @CanIgnoreReturnValue
   V addSuccessor(N var1, V var2);
}
