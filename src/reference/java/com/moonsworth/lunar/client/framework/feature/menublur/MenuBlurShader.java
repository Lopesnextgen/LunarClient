package com.moonsworth.lunar.client.framework.feature.menublur;

import com.moonsworth.lunar.bridge.GlslUniformType;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class MenuBlurShader implements com.moonsworth.lunar.client.render.shader.BundledShaderLoader {
   public MenuBlurShader() {
   }

   @NotNull
   public String method10() {
      return this.IOOCROHIIHHIIORCCCRICOROOIOOIR("menu_blur");
   }

   public void method5(BiConsumer<String, GlslUniformType> biconsumer1) {
      biconsumer1.accept("BlurDir", GlslUniformType.VEC2);
      biconsumer1.accept("Radius", GlslUniformType.FLOAT);
      biconsumer1.accept("OneTexel", GlslUniformType.VEC2);
      biconsumer1.accept("Progress", GlslUniformType.FLOAT);
   }

   public void method7(Consumer<String> consumer1) {
   }
}
