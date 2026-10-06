package com.moonsworth.lunar.client.framework.feature.motionblur;

import com.moonsworth.lunar.bridge.GlslUniformType;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class MotionBlurShader implements com.moonsworth.lunar.client.render.shader.BundledShaderLoader {
   public MotionBlurShader() {
   }

   @NotNull
   public String method10() {
      return this.IOOCROHIIHHIIORCCCRICOROOIOOIR("motion_blur");
   }

   public void method7(Consumer<String> consumer1) {
      consumer1.accept("PrevSampler");
   }

   public void method5(BiConsumer<String, GlslUniformType> biconsumer1) {
      biconsumer1.accept("Phosphor", GlslUniformType.VEC3);
   }
}
