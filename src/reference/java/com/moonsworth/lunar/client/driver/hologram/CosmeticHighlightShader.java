package com.moonsworth.lunar.client.driver.hologram;

import com.moonsworth.lunar.bridge.BlendFunctionBridge;
import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.bridge.RenderPipelineBuilder;
import com.moonsworth.lunar.client.render.jit.JitShaderResource.Type;
import com.moonsworth.lunar.client.render.shader.ShaderPreprocessor;
import com.moonsworth.lunar.client.render.shader.ShaderPreprocessor.Data;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class CosmeticHighlightShader implements com.moonsworth.lunar.client.render.shader.BundledShaderLoader {
   private final boolean field2;

   public CosmeticHighlightShader(boolean flag1) {
      this.field2 = flag1;
   }

   @NotNull
   public String method10() {
      return this.IOOCROHIIHHIIORCCCRICOROOIOOIR("cosmetic_highlight");
   }

   public RenderPipelineBuilder method3() {
      return super.method3().method32(BlendFunctionBridge.field5).method14("ALPHA_CUTOUT", 0.0F).method37(true);
   }

   public String method7(Type type1, String text2) {
      Data data3 = Data.method9(this).method10(type1, this.OCRHIIIRHIOIROHCCROHICROOIIORH()).method11(type1, this.OCRHIIIRHIOIROHCCROHICROOIIORH());
      if (this.field2) {
         data3.method6("REVERSE_Z", "1");
      }

      return ShaderPreprocessor.preprocessWithData(text2, type1, data3);
   }

   public void method5(BiConsumer<String, GlslUniformType> biconsumer1) {
      biconsumer1.accept("HighlightColor", GlslUniformType.VEC3);
      biconsumer1.accept("Resolution", GlslUniformType.VEC2);
   }

   public void method6(Consumer<String> consumer1) {
      this.method7(consumer1);
   }

   public void method7(Consumer<String> consumer1) {
      consumer1.accept("CosmeticDepth");
      consumer1.accept("SceneDepth");
   }
}
