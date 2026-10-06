package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class ShaderResource {
   private final String field1;
   private final ResourceLocationBridge field2;

   public ShaderResource(String text, ResourceLocationBridge resourceLocationBridge) {
      this.field1 = text;
      this.field2 = resourceLocationBridge;
   }

   public String method1() {
      return this.field1;
   }

   public ResourceLocationBridge method2() {
      return this.field2;
   }
}
