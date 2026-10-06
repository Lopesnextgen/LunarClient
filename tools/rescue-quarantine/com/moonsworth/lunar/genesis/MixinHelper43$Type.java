package com.moonsworth.lunar.genesis;

import java.util.LinkedList;
import java.util.List;

enum MixinHelper43$Type implements Supplier<List<Object>> {
   INSTANCE;

   MixinHelper43$Type() {
   }

   public static <V> Supplier<List<V>> instance() {
      return INSTANCE;
   }

   public List<Object> get() {
      return new LinkedList<>();
   }
}
