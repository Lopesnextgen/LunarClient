package com.moonsworth.lunar.client.driver.holograms;

import com.moonsworth.lunar.bridge.Bridge2_15;
import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.bridge.RenderPipelineBuilder;
import com.moonsworth.lunar.client.render.jit.JitShaderResource.Type;
import com.moonsworth.lunar.client.util.ThreadModuleDump94;
import com.moonsworth.lunar.client.util.ThreadModuleDump94.Data;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class ColorsaturationExtension implements com.moonsworth.lunar.client.render.shader.BundledShaderLoader {
   private final boolean field2;

   public ColorsaturationExtension(boolean var1) {
      this.field2 = var1;
   }

   @NotNull
   public String method10() {
      return this.method11("cosmetic_highlight");
   }

   public RenderPipelineBuilder method3() {
      return super.method3().method32(Bridge2_15.field5).method14("ALPHA_CUTOUT", 0.0F).method37(true);
   }

   public String method7(Type var1, String var2) {
      Data var3 = Data.method9(this).method10(var1, this.method8()).method11(var1, this.method8());
      if (this.field2) {
         var3.method6("REVERSE_Z", "1");
      }

      return ThreadModuleDump94.method5(var2, var1, var3);
   }

   public void method5(BiConsumer<String, GlslUniformType> var1) {
      var1.accept("HighlightColor", GlslUniformType.VEC3);
      var1.accept("Resolution", GlslUniformType.VEC2);
   }

   public void method6(Consumer<String> var1) {
      this.method7(var1);
   }

   public void method7(Consumer<String> var1) {
      var1.accept("CosmeticDepth");
      var1.accept("SceneDepth");
   }
}
