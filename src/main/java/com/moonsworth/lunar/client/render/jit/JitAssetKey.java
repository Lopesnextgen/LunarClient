package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class JitAssetKey {
   private final ResourceLocationBridge field1;
   private final String field2;

   public JitAssetKey(ResourceLocationBridge var1, String text) {
      this.field1 = var1;
      this.field2 = text;
   }

   public static JitAssetKey method1(ResourceLocationBridge var0, String var1) {
      return new JitAssetKey(var0, var1);
   }

   public static JitAssetKey method2(ResourceLocationBridge var0) {
      return method1(var0, "");
   }

   public ResourceLocationBridge method3() {
      return this.field1;
   }

   public String method4() {
      return this.field2;
   }
}
