package com.moonsworth.lunar.client.render.shader;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum ShaderKeyFactory {
   CUSTOM((var0, var1) -> var1 && var0.contains("dev_cosmetics") ? ShaderKey.method4(var0) : ShaderKey.method2(var0)),
   STATIC(ShaderKey::method1);

   private final BiFunction<String, Boolean, ShaderKey> keyFunction;

   ShaderKeyFactory(Function<String, ShaderKey> var3) {
      this((var1x, var2x) -> (ShaderKey)var3.apply(var1x));
   }

   ShaderKeyFactory(BiFunction<String, Boolean, ShaderKey> var3) {
      this.keyFunction = var3;
   }

   public ShaderKey createKey(String var1) {
      return this.createKey(var1, false);
   }

   public ShaderKey createKey(String var1, boolean var2) {
      return this.keyFunction.apply(var1, var2);
   }

   public static ShaderKeyFactory fromId(String var0) {
      try {
         return valueOf(var0.toUpperCase());
      } catch (Exception var2) {
         return null;
      }
   }
}
