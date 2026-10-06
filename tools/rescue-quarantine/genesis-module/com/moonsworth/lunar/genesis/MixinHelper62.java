package com.moonsworth.lunar.genesis;

import java.util.Set;
import com.google.common.graph.ElementOrder;

interface MixinHelper62<N> extends MixinHelper6_7<N>, MixinHelper7_9<N> {
   Set<N> nodes();

   Set<IterableBase<N>> edges();

   boolean isDirected();

   boolean allowsSelfLoops();

   ElementOrder<N> method1();

   ElementOrder<N> method2();

   Set<N> adjacentNodes(N var1);

   Set<N> predecessors(N var1);

   Set<N> successors(N var1);

   Set<IterableBase<N>> incidentEdges(N var1);

   int degree(N var1);

   int inDegree(N var1);

   int outDegree(N var1);

   boolean hasEdgeConnecting(N var1, N var2);

   boolean method3(IterableBase<N> var1);
}
