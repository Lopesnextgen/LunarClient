package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;

public final class JitPaths {
   public static final String field1 = "lunar-jit";

   public static boolean method1(ResourceLocationBridge var0) {
      return var0 != null && var0.bridge$getDomain().equals("lunar-jit");
   }

   public static ResourceLocationBridge method2(ResourceLocationBridge var0) {
      return var0 != null && !method1(var0) && !var0.bridge$getPath().contains("dev_cosmetics")
         ? ResourceLocationBridge.create("lunar-jit", var0.bridge$getPath())
         : var0;
   }

   public static String method3(ResourceLocationBridge var0, boolean flag) {
      String var2 = var0.bridge$getPath();
      if (flag) {
         var2 = var2 + ".mcmeta";
      }

      return var2;
   }

   @Generated
   private JitPaths() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
