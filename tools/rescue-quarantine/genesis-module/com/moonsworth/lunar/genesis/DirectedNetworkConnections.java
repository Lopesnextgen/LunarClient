package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

final class DirectedNetworkConnections<N, E> extends AbstractDirectedNetworkConnections<N, E> {
   protected DirectedNetworkConnections(Map<E, N> map1, Map<E, N> map2, int number3) {
      super(map1, map2, number3);
   }

   static <N, E> DirectedNetworkConnections<N, E> method1() {
      return new DirectedNetworkConnections<>(HashBiMap.method2(2), HashBiMap.method2(2), 0);
   }

   static <N, E> DirectedNetworkConnections<N, E> method2(Map<E, N> map0, Map<E, N> map1, int number2) {
      return new DirectedNetworkConnections<>(SerializableIterator52.method10(map0), SerializableIterator52.method10(map1), number2);
   }

   public Set<N> predecessors() {
      return Collections.unmodifiableSet(((BiMap)this.CCCOCCOICIRIOOIIOIHIOOCHOIIOIO).values());
   }

   public Set<N> successors() {
      return Collections.unmodifiableSet(((BiMap)this.IOHRCCOIOOIRCHCOICIHHCCHRORCOH).values());
   }

   public Set<E> edgesConnecting(N value1) {
      return new EdgesConnecting(((BiMap)this.IOHRCCOIOOIRCHCOICIHHCCHRORCOH).method2(), value1);
   }
}
