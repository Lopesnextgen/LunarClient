package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.common.graph.Network;

@Annotation2
public interface MixinHelper632<N, E> extends Network<N, E> {
   @CanIgnoreReturnValue
   boolean addNode(N var1);

   @CanIgnoreReturnValue
   boolean addEdge(N var1, N var2, E var3);

   @CanIgnoreReturnValue
   boolean method1(IterableBase<N> var1, E var2);

   @CanIgnoreReturnValue
   boolean removeNode(N var1);

   @CanIgnoreReturnValue
   boolean removeEdge(E var1);
}
