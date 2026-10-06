package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;

interface NetworkConnections<N, E> {
   Set<N> adjacentNodes();

   Set<N> predecessors();

   Set<N> successors();

   Set<E> incidentEdges();

   Set<E> inEdges();

   Set<E> outEdges();

   Set<E> edgesConnecting(N value1);

   N adjacentNode(E value1);

   @CanIgnoreReturnValue
   N removeInEdge(E value1, boolean flag2);

   @CanIgnoreReturnValue
   N removeOutEdge(E value1);

   void addInEdge(E value1, N value2, boolean flag3);

   void addOutEdge(E value1, N value2);
}
