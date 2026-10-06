package com.moonsworth.lunar.client.command;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BoundArguments implements CommandArguments {
   private final Map<String, Object> field1 = new HashMap<>();

   public BoundArguments() {
   }

   public void method1(String text1, Object object) {
      this.field1.put(text1, object);
   }

   @Override
   public String getString(String text1) {
      return this.method2(text1, String.class);
   }

   @Override
   public int getInteger(String text1) {
      return this.method2(text1, Integer.class);
   }

   @Override
   public double getDouble(String text1) {
      return this.method2(text1, Double.class);
   }

   @Override
   public Duration getDuration(String text1) {
      return this.method2(text1, Duration.class);
   }

   private <T> T method2(String text1, Class<T> clazz2) {
      Object obj3 = this.field1.get(text1);
      if (obj3 == null) {
         throw new IllegalStateException("No argument bound for name: " + text1);
      } else if (!clazz2.isInstance(obj3)) {
         throw new IllegalStateException("Argument " + text1 + " is not a " + clazz2.getSimpleName() + " (got " + obj3.getClass().getSimpleName() + ")");
      } else {
         return (T)clazz2.cast(obj3);
      }
   }
}
