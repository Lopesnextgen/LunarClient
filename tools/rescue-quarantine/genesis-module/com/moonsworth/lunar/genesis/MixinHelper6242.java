package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Annotation2
public interface MixinHelper6242<N, V> extends MixinHelper624<N, V> {
   @CanIgnoreReturnValue
   boolean addNode(N var1);

   @CanIgnoreReturnValue
   V putEdgeValue(N var1, N var2, V var3);

   @CanIgnoreReturnValue
   V method1(IterableBase<N> var1, V var2);

   @CanIgnoreReturnValue
   boolean removeNode(N var1);

   @CanIgnoreReturnValue
   V removeEdge(N var1, N var2);

   @CanIgnoreReturnValue
   V method2(IterableBase<N> var1);
}
