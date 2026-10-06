package com.moonsworth.lunar.client.render.shader;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge_45;
import com.moonsworth.lunar.bridge.RenderPipelineBuilder;
import com.moonsworth.lunar.bridge.BridgeImplementation.Extension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class ShaderInjectedPipeline {
   private final String field1;
   private final ShaderPipeline field2;
   private Bridge_45 field3;
   private boolean field4 = false;

   private ShaderInjectedPipeline(String var1, ShaderPipeline var2) {
      this.field1 = var1;
      this.field2 = var2;
      this.method1();
   }

   private void method1() {
      RenderPipelineBuilder var1 = this.field2.method3().method3(this.method4()).method9(this.method5(true)).method6(this.method5(false));
      this.field2.method5(var1::method22);
      this.field2.method6(var1::method18);
      if (ThreadModuleDump63.MC_VERSION <= 7) {
         var1.method10(this.method4());
      }

      this.field3 = var1.method44();
   }

   public void method2() {
      if (ThreadModuleDump63.MC_VERSION >= 9 && ThreadModuleDump63.MC_VERSION <= 25) {
         this.field3.method2(this.field3.bridge$location().bridge$getPath());
      }
   }

   public JsonObject method3() {
      ShaderProgramDefinition var1 = this.field2.method4();
      this.field2.method5(var1::method1);
      this.field2.method6(var1::method2);
      return var1.method4(this.method5(true), this.method5(false));
   }

   private ResourceLocationBridge method4() {
      return ResourceLocationBridge.create("lunar-shaders", "lunar/shader-inject/" + this.field1);
   }

   private ResourceLocationBridge method5(boolean var1) {
      return ResourceLocationBridge.create("lunar-shaders", "lunar/shader-inject/" + (var1 ? "vsh/" : "fsh/") + this.field1);
   }

   public void destroy() {
      if (!this.field4) {
         this.field3.bridge$cleanup();
         this.field3.bridge$cleanShaders();
         this.field3 = null;
         this.field4 = true;
         ThreadModuleDump63.method4().method99().method3(this.field1);
      }
   }

   @NotNull
   public Bridge_45 method6() {
      if (!this.field4 && this.field3 != null) {
         return this.field3;
      } else {
         throw new RuntimeException("Tried to access RenderPipeline from a ShaderInjectedPipeline after it has been destroyed! (ID: " + this.field1 + ")");
      }
   }

   public String method7(boolean var1) {
      String var2 = var1 ? this.field2.method1() : this.field2.method2();
      if (var2 == null) {
         throw new IllegalStateException((var1 ? "VSH" : "FSH") + " shader returned null in ShaderInjectedPipeline '" + this.field1 + "'!");
      } else {
         return var2;
      }
   }

   public void method8(Extension var1) {
      if (!this.field4) {
         this.field3.bridge$cleanup();
         this.field3.bridge$cleanShaders();
         this.field3 = null;
         this.method1();
         var1.register(new Bridge_45[]{this.field3});
      }
   }

   public static ShaderInjectedPipeline method9(String text, ShaderPipeline var1) {
      return new ShaderInjectedPipeline(text, var1);
   }

   @Generated
   public boolean method10() {
      return this.field4;
   }
}
