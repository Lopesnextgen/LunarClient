package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.HashBiMap;

final class MixinHelper1133<N, E> extends MixinHelper113<N, E> {
   protected MixinHelper1133(Map<E, N> var1) {
      super(var1);
   }

   static <N, E> MixinHelper1133<N, E> method1() {
      return new MixinHelper1133<>(HashBiMap.method2(2));
   }

   static <N, E> MixinHelper1133<N, E> method2(Map<E, N> var0) {
      return new MixinHelper1133<>(SerializableIterator52.method10(var0));
   }

   @Override
   public Set<N> adjacentNodes() {
      return Collections.unmodifiableSet(((MapExtension)this.field1).values());
   }

   @Override
   public Set<E> edgesConnecting(N var1) {
      return new AbstractSetImpl<>(((MapExtension)this.field1).method2(), var1);
   }
}
