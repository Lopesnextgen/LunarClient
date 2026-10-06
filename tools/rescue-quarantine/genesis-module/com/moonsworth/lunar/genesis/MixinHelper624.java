package com.moonsworth.lunar.genesis;

import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.graph.ElementOrder;

@Annotation2
public interface MixinHelper624<N, V> extends MixinHelper62<N> {
   @Override
   Set<N> nodes();

   @Override
   Set<IterableBase<N>> edges();

   MixinHelper622<N> method3();

   @Override
   boolean isDirected();

   @Override
   boolean allowsSelfLoops();

   @Override
   ElementOrder<N> method1();

   @Override
   ElementOrder<N> method2();

   @Override
   Set<N> adjacentNodes(N var1);

   @Override
   Set<N> predecessors(N var1);

   @Override
   Set<N> successors(N var1);

   @Override
   Set<IterableBase<N>> incidentEdges(N var1);

   @Override
   int degree(N var1);

   @Override
   int inDegree(N var1);

   @Override
   int outDegree(N var1);

   @Override
   boolean hasEdgeConnecting(N var1, N var2);

   @Override
   boolean method3(IterableBase<N> var1);

   Optional<V> edgeValue(N var1, N var2);

   Optional<V> method6(IterableBase<N> var1);

   @Nullable V edgeValueOrDefault(N var1, N var2, @Nullable V var3);

   @Nullable V method6(IterableBase<N> var1, @Nullable V var2);

   @Override
   boolean equals(@Nullable Object var1);

   @Override
   int hashCode();
}
