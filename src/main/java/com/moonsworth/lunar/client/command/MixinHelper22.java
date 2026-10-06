package com.moonsworth.lunar.client.command;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MixinHelper22 implements MixinHelper2 {
   private final Map<String, Object> field1 = new HashMap<>();

   public void method1(String var1, Object var2) {
      this.field1.put(var1, var2);
   }

   @Override
   public String getString(String var1) {
      return this.method2(var1, String.class);
   }

   @Override
   public int getInteger(String var1) {
      return this.method2(var1, Integer.class);
   }

   @Override
   public double getDouble(String var1) {
      return this.method2(var1, Double.class);
   }

   @Override
   public Duration getDuration(String var1) {
      return this.method2(var1, Duration.class);
   }

   private <T> T method2(String var1, Class<T> var2) {
      Object var3 = this.field1.get(var1);
      if (var3 == null) {
         throw new IllegalStateException("No argument bound for name: " + var1);
      } else if (!var2.isInstance(var3)) {
         throw new IllegalStateException("Argument " + var1 + " is not a " + var2.getSimpleName() + " (got " + var3.getClass().getSimpleName() + ")");
      } else {
         return (T)var2.cast(var3);
      }
   }
}
