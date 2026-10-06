package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.GlslUniformType;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class BlitPostEffect implements BundledShaderLoader {
   @NotNull
   @Override
   public String method10() {
      return this.method11("blit");
   }

   @Override
   public void method7(Consumer<String> var1) {
   }

   @Override
   public void method5(BiConsumer<String, GlslUniformType> var1) {
   }
}
