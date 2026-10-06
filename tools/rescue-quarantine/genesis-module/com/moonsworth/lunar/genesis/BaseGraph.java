package com.moonsworth.lunar.genesis;

import java.util.Set;
import com.google.common.graph.PredecessorsFunction;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.SuccessorsFunction;

interface BaseGraph<N> extends PredecessorsFunction<N>, SuccessorsFunction<N> {
   Set<N> nodes();

   Set<IterableBase<N>> edges();

   boolean isDirected();

   boolean allowsSelfLoops();

   ElementOrder<N> method1();

   ElementOrder<N> method2();

   Set<N> adjacentNodes(N value1);

   Set<N> predecessors(N value1);

   Set<N> successors(N value1);

   Set<IterableBase<N>> incidentEdges(N value1);

   int degree(N value1);

   int inDegree(N value1);

   int outDegree(N value1);

   boolean hasEdgeConnecting(N value1, N value2);

   boolean method3(IterableBase<N> iterablebase1);
}
