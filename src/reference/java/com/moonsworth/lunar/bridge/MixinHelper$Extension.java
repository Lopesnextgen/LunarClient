package com.moonsworth.lunar.bridge;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface MixinHelper$Extension {
   void render(AbstractRenderContext var1, MixinHelper_4 var2);

   static MixinHelper$Extension of(BiConsumer<AbstractRenderContext, MixinHelper_4> consumer) {
      return consumer::accept;
   }
}
