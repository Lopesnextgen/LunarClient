package com.moonsworth.lunar.bridge;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ClickableTextRenderer {
   void render(AbstractRenderContext bridgeextension_91, MixinHelper_4 mixinhelper_42);

   static ClickableTextRenderer of(BiConsumer<AbstractRenderContext, MixinHelper_4> biconsumer0) {
      return biconsumer0::accept;
   }
}
