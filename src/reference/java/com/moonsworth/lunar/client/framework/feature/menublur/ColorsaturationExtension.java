package com.moonsworth.lunar.client.framework.feature.menublur;

import com.moonsworth.lunar.bridge.GlslUniformType;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class ColorsaturationExtension implements com.moonsworth.lunar.client.render.shader.BundledShaderLoader {
   @NotNull
   public String method10() {
      return this.method11("menu_blur");
   }

   public void method5(BiConsumer<String, GlslUniformType> var1) {
      var1.accept("BlurDir", GlslUniformType.VEC2);
      var1.accept("Radius", GlslUniformType.FLOAT);
      var1.accept("OneTexel", GlslUniformType.VEC2);
      var1.accept("Progress", GlslUniformType.FLOAT);
   }

   public void method7(Consumer<String> var1) {
   }
}
