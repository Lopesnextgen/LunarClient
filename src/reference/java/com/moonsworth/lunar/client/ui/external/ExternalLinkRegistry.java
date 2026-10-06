package com.moonsworth.lunar.client.ui.external;

import com.moonsworth.lunar.client.util.LunarLogger;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import lombok.Generated;

public final class ExternalLinkRegistry {
   private static final Map<Class<? extends ExternalLink>, ExternalLink> field1 = new ConcurrentHashMap<>();

   public static void method1(ExternalLink fishing20) {
      Class clazz1 = null;

      for (Class clazz5 : fishing20.getClass().getInterfaces()) {
         if (ExternalLink.class.isAssignableFrom(clazz5)) {
            clazz1 = clazz5;
         }
      }

      if (clazz1 == null) {
         throw new RuntimeException("Unable to find ExternalLink interface for class: " + fishing20.getClass().getName());
      }

      LunarLogger.method2("[Ichor/External Link]", "Registering external class %s as %s.", new Object[]{fishing20.getClass().getName(), clazz1.getName()});
      field1.put(clazz1, fishing20);
   }

   public static <T extends ExternalLink> Optional<T> method2(Class<T> clazz0) {
      return Optional.ofNullable((T)field1.get(clazz0));
   }

   public static <T> T method3(Class<? extends ExternalLink> clazz0, Supplier<T> supplier1, Supplier<T> supplier2) {
      return (T)(field1.containsKey(clazz0) ? supplier1.get() : supplier2.get());
   }

   @Generated
   private ExternalLinkRegistry() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
