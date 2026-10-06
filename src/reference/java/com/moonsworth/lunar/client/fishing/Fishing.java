package com.moonsworth.lunar.client.fishing;

import com.moonsworth.lunar.client.util.Slayer;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import lombok.Generated;

public final class Fishing {
   private static final Map<Class<? extends Fishing2>, Fishing2> field1 = new ConcurrentHashMap<>();

   public static void method1(Fishing2 var0) {
      Class var1 = null;

      for (Class var5 : var0.getClass().getInterfaces()) {
         if (Fishing2.class.isAssignableFrom(var5)) {
            var1 = var5;
         }
      }

      if (var1 == null) {
         throw new RuntimeException("Unable to find ExternalLink interface for class: " + var0.getClass().getName());
      }

      Slayer.method2("[Ichor/External Link]", "Registering external class %s as %s.", var0.getClass().getName(), var1.getName());
      field1.put(var1, var0);
   }

   public static <T extends Fishing2> Optional<T> method2(Class<T> var0) {
      return Optional.ofNullable((T)field1.get(var0));
   }

   public static <T> T method3(Class<? extends Fishing2> var0, Supplier<T> var1, Supplier<T> supplier) {
      return (T)(field1.containsKey(var0) ? var1.get() : supplier.get());
   }

   @Generated
   private Fishing() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
