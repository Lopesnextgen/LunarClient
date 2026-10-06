package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DepthTestFunction;
import com.moonsworth.lunar.bridge.GlslUniformType;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.VertexFormats;
import com.moonsworth.lunar.bridge.VertexFormatBridge;
import com.moonsworth.lunar.bridge.RenderPipelineBuilder;
import com.moonsworth.lunar.client.render.shader.ShaderProgramDefinition;
import com.moonsworth.lunar.client.render.shader.ShaderUniform;
import com.moonsworth.lunar.client.render.shader.ShaderResource;
import com.moonsworth.lunar.client.render.shader.GlslBuiltin;
import com.moonsworth.lunar.client.render.shader.ShaderPipeline;
import com.moonsworth.lunar.client.render.shader.ShaderDefinition;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;

public class ShaderPass implements ShaderPipeline {
   private final ShaderDefinition field1;

   public ShaderPass(ShaderCloakRenderer fov31) {
      this.field1 = fov31.method35();
   }

   @NotNull
   public String method1() {
      return this.field1.method12();
   }

   @NotNull
   public String method2() {
      return this.field1.method13();
   }

   public RenderPipelineBuilder method3() {
      ShaderUniform colorsaturation41 = this.field1.method2();
      VertexFormatBridge bridge_632 = colorsaturation41.method3() ? VertexFormats.field5 : VertexFormats.field1;
      return Bridge.method8()
         .method80()
         .method11(bridge_632, DrawMode.TRIANGLE_STRIP)
         .method32(null)
         .method29(false)
         .method33(true)
         .method37(false)
         .method27(DepthTestFunction.NO_DEPTH_TEST);
   }

   public ShaderProgramDefinition method4() {
      ShaderProgramDefinition colorsaturation2_21 = new ShaderProgramDefinition();
      this.field1.method2().method1((arg1x, arg2) -> colorsaturation2_21.method3(arg2));
      return colorsaturation2_21;
   }

   public void method5(BiConsumer<String, GlslUniformType> biconsumer1) {
      for (GlslBuiltin colorsaturationtype23 : this.field1.method7()) {
         biconsumer1.accept(colorsaturationtype23.getVarName(), colorsaturationtype23.getType());
      }

      ShaderDefinition.method3(this.field1.method2(), biconsumer1);
   }

   public void method6(Consumer<String> consumer1) {
      for (ShaderResource colorsaturation53 : this.field1.method8()) {
         consumer1.accept(colorsaturation53.method1());
      }
   }
}
