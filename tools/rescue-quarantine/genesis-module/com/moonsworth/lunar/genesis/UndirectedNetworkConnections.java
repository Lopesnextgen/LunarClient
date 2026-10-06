package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

final class UndirectedNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {
   protected UndirectedNetworkConnections(Map<E, N> map1) {
      super(map1);
   }

   static <N, E> UndirectedNetworkConnections<N, E> method1() {
      return new UndirectedNetworkConnections<>(HashBiMap.method2(2));
   }

   static <N, E> UndirectedNetworkConnections<N, E> method2(Map<E, N> map0) {
      return new UndirectedNetworkConnections<>(SerializableIterator52.method10(map0));
   }

   public Set<N> adjacentNodes() {
      return Collections.unmodifiableSet(((BiMap)this.HHROHROHOCHIRCCRIIHCHOIOIOOIIC).values());
   }

   public Set<E> edgesConnecting(N value1) {
      return new EdgesConnecting(((BiMap)this.HHROHROHOCHIRCCRIIHCHOIOIOOIIC).method2(), value1);
   }
}
