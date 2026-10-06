package com.moonsworth.lunar.client.framework.feature.motionblur;

import com.moonsworth.lunar.bridge.GlslUniformType;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class ColorsaturationExtension implements com.moonsworth.lunar.client.render.shader.BundledShaderLoader {
   @NotNull
   public String method10() {
      return this.method11("motion_blur");
   }

   public void method7(Consumer<String> var1) {
      var1.accept("PrevSampler");
   }

   public void method5(BiConsumer<String, GlslUniformType> var1) {
      var1.accept("Phosphor", GlslUniformType.VEC3);
   }
}
