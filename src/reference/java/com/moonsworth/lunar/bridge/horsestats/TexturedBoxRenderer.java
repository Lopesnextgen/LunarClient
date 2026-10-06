package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import java.util.UUID;

public interface TexturedBoxRenderer {
   void method1(AbstractRenderContext var1, float var2, ResourceLocationBridge var3, UUID var4);

   interface Extension extends TexturedBoxRenderer {
      void method1();
   }
}
