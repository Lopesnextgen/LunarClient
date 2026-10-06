package com.moonsworth.lunar.client.render.shader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class ShaderProgramDefinition {
   private JsonArray field1 = new JsonArray();
   private JsonArray field2 = new JsonArray();
   private JsonArray field3 = new JsonArray();

   public ShaderProgramDefinition method1(String var1, GlslUniformType var2) {
      JsonObject var3 = new JsonObject();
      String var4 = var2.asMojangType();
      int var5 = var2.getCount();
      JsonArray var6 = new JsonArray();

      for (int var7 = 0; var7 < var5; var7++) {
         var6.add(0);
      }

      var3.addProperty("name", var1);
      var3.addProperty("type", var4);
      var3.addProperty("count", var5);
      var3.add("values", var6);
      this.field2.add(var3);
      return this;
   }

   public ShaderProgramDefinition method2(String var1) {
      JsonObject var2 = new JsonObject();
      var2.addProperty("name", var1);
      this.field1.add(var2);
      return this;
   }

   public ShaderProgramDefinition method3(String var1) {
      this.field3.add(var1);
      return this;
   }

   public JsonObject method4(ResourceLocationBridge var1, ResourceLocationBridge var2) {
      JsonObject var3 = new JsonObject();
      String var4 = ThreadModuleDump63.MC_VERSION >= 26 ? var1.toString() : var1.bridge$getPath();
      String var5 = ThreadModuleDump63.MC_VERSION >= 26 ? var2.toString() : var2.bridge$getPath();
      var3.addProperty("vertex", var4);
      var3.addProperty("fragment", var5);
      var3.add("samplers", this.field1);
      var3.add("uniforms", this.field2);
      var3.add("attributes", this.field3);
      return var3;
   }
}
