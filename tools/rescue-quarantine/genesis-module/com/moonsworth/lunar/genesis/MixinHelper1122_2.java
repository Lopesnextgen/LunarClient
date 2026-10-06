package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.HashBiMap;

final class MixinHelper1122_2<N, E> extends MixinHelper112_2<N, E> {
   protected MixinHelper1122_2(Map<E, N> var1, Map<E, N> var2, int var3) {
      super(var1, var2, var3);
   }

   static <N, E> MixinHelper1122_2<N, E> method1() {
      return new MixinHelper1122_2<>(HashBiMap.method2(2), HashBiMap.method2(2), 0);
   }

   static <N, E> MixinHelper1122_2<N, E> method2(Map<E, N> var0, Map<E, N> var1, int var2) {
      return new MixinHelper1122_2<>(SerializableIterator52.method10(var0), SerializableIterator52.method10(var1), var2);
   }

   @Override
   public Set<N> predecessors() {
      return Collections.unmodifiableSet(((MapExtension)this.CCCOCCOICIRIOOIIOIHIOOCHOIIOIO).values());
   }

   @Override
   public Set<N> successors() {
      return Collections.unmodifiableSet(((MapExtension)this.IOHRCCOIOOIRCHCOICIHHCCHRORCOH).values());
   }

   @Override
   public Set<E> edgesConnecting(N var1) {
      return new AbstractSetImpl<>(((MapExtension)this.IOHRCCOIOOIRCHCOICIHHCCHRORCOH).method2(), var1);
   }
}
