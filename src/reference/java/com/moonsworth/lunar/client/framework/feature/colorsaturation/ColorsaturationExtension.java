package com.moonsworth.lunar.client.framework.feature.colorsaturation;

import com.moonsworth.lunar.bridge.GlslUniformType;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class ColorsaturationExtension implements com.moonsworth.lunar.client.render.shader.BundledShaderLoader {
   @NotNull
   public String method10() {
      return this.method11("color_saturation");
   }

   public void method5(BiConsumer<String, GlslUniformType> var1) {
      var1.accept("Hue", GlslUniformType.FLOAT);
      var1.accept("Brightness", GlslUniformType.FLOAT);
      var1.accept("Contrast", GlslUniformType.FLOAT);
      var1.accept("Saturation", GlslUniformType.FLOAT);
   }

   public void method7(Consumer<String> var1) {
   }
}
