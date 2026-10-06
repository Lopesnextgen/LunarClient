package com.moonsworth.lunar.client.util.collection;

import java.util.Map;
import java.util.function.Predicate;
import lombok.Generated;

public final class MapRemoval {
   public static <K, V> void method1(Map<K, V> map0, Predicate<K> predicate1) {
      map0.entrySet().removeIf(arg1x -> predicate1.test(arg1x.getKey()));
   }

   public static <K, V> void method2(Map<K, V> map0, Predicate<V> predicate1) {
      map0.entrySet().removeIf(arg1x -> predicate1.test(arg1x.getValue()));
   }

   public static <K, V> void method3(Map<K, V> map0, Predicate<K> predicate1) {
      map0.entrySet().removeIf(arg1x -> !predicate1.test(arg1x.getKey()));
   }

   public static <K, V> void method4(Map<K, V> map0, Predicate<V> predicate1) {
      map0.entrySet().removeIf(arg1x -> !predicate1.test(arg1x.getValue()));
   }

   @Generated
   private MapRemoval() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
