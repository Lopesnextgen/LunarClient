package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TextureManagerBridge extends Bridge13_3 {
   boolean bridge$loadTexture(ResourceLocationBridge horsestats141, Bridge8Extension3 bridge8extension32);

   void bridge$bindTexture(ResourceLocationBridge horsestats141);

   void bridge$deleteTexture(ResourceLocationBridge horsestats141);

   @NotNull
   Bridge8Extension3 bridge$getTexture(ResourceLocationBridge horsestats141);

   @Nullable
   default Bridge8Extension3 method1(ResourceLocationBridge horsestats141) {
      return this.bridge$getTextureMap().get(horsestats141);
   }

   default Optional<AsyncTextureBridge> method2(ResourceLocationBridge horsestats141) {
      Bridge8Extension3 bridge8extension32 = this.bridge$getTexture(horsestats141);
      return bridge8extension32.method2() instanceof AsyncTextureBridge bridge3extension_74 ? Optional.of(bridge3extension_74) : Optional.empty();
   }

   default Bridge8Extension34 method3(ResourceLocationBridge horsestats141, TextureBridge bridge3_42) {
      Bridge8Extension34 bridge8extension343 = Bridge.method8().method47(bridge3_42);
      this.bridge$loadTexture(horsestats141, bridge8extension343);
      return bridge8extension343;
   }

   ResourceLocationBridge bridge$getDynamicTextureLocation(String text1, Bridge8Extension33 bridge8extension332);

   Map<ResourceLocationBridge, Bridge8Extension3> bridge$getTextureMap();
}
