package com.moonsworth.lunar.client.framework.feature.colorsaturation;

import com.moonsworth.lunar.bridge.GlslUniformType;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class ColorSaturationShader implements com.moonsworth.lunar.client.render.shader.BundledShaderLoader {
   public ColorSaturationShader() {
   }

   @NotNull
   public String method10() {
      return this.IOOCROHIIHHIIORCCCRICOROOIOOIR("color_saturation");
   }

   public void method5(BiConsumer<String, GlslUniformType> biconsumer1) {
      biconsumer1.accept("Hue", GlslUniformType.FLOAT);
      biconsumer1.accept("Brightness", GlslUniformType.FLOAT);
      biconsumer1.accept("Contrast", GlslUniformType.FLOAT);
      biconsumer1.accept("Saturation", GlslUniformType.FLOAT);
   }

   public void method7(Consumer<String> consumer1) {
   }
}
