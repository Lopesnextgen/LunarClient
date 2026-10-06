package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge6_8;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Bridge_52;
import com.moonsworth.lunar.client.render.shader.ShaderInjectedPipeline;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class LunarPostEffect {
   private final String field1;
   private final ShaderInjectedPipeline field2;
   private Bridge3_24 field3 = null;
   private int field4 = 0;
   private final Map<Bridge3_24, RenderLayerBridge> field5 = new HashMap<>();

   public LunarPostEffect(String var1, ShaderInjectedPipeline var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public Bridge3_24 method1(Bridge3_24 var1) {
      if (this.field3 != null && this.field3.method6(var1)) {
         return this.field3;
      }

      if (this.field3 == null) {
         this.field3 = Bridge_52.method2().method1(var1.bridge$framebufferWidth(), var1.bridge$framebufferHeight()).method9(true).method3();
      } else {
         this.field3.bridge$createBindFramebuffer(var1.bridge$framebufferWidth(), var1.bridge$framebufferHeight());
      }

      return this.field3;
   }

   public void method2(AbstractRenderContext var1, Bridge3_24 var2, Consumer<Bridge6_8> var3) {
      Bridge3_24 var4 = this.method1(var2);
      this.method3(var1, var2, var4, var3);
      ThreadModuleDump63.method4().method99().method5(var1, var4, var2);
   }

   public void method3(AbstractRenderContext var1, Bridge3_24 var2, Bridge3_24 var3, Consumer<Bridge6_8> var4) {
      if (this.field2.method10()) {
         throw new IllegalStateException("Tried to process LunarPostEffect but the underlying Shader was destroyed!");
      }

      RenderLayerBridge var5 = this.method4(var3);
      var5.bridge$getShaderUniforms().ifPresent(var2x -> {
         var4.accept(var2x);
         if (var2.bridge$getColorTexture(true) instanceof Bridge8Extension var4x) {
            var2x.bridge$bindSampler("DiffuseSampler", var4x);
         }
      });
      var1.method10(var5)
         .method1()
         .method2(0.0, 0.0, 0.0)
         .method16()
         .method2(1.0, 0.0, 0.0)
         .method16()
         .method2(0.0, 1.0, 0.0)
         .method16()
         .method2(1.0, 1.0, 0.0)
         .method16()
         .method17(BufferBuildMode.BATCHED);
      var1.method33(var5);
   }

   private RenderLayerBridge method4(Bridge3_24 var1) {
      return this.field5.computeIfAbsent(var1, this::method5);
   }

   private RenderLayerBridge method5(Bridge3_24 var1) {
      this.field4++;
      return Bridge.method8()
         .method81()
         .method5(Bridge.method8().method93("Lunar Post Effect (" + this.field1 + " " + this.field4 + ")", () -> var1))
         .method14(this.field2.method6(), "lunar_post_effect", 256, false, false, false);
   }

   public void delete() {
      this.field2.destroy();
      if (this.field3 != null) {
         this.field3.bridge$delete();
         this.field3 = null;
      }

      for (RenderLayerBridge var2 : this.field5.values()) {
         var2.bridge$uncache();
         var2.bridge$getShaderUniforms().ifPresent(Bridge6_8::bridge$close);
      }

      this.field5.clear();
   }
}
