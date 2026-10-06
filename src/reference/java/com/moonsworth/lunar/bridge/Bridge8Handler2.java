package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Bridge8Handler2 extends Bridge13_3 {
   boolean bridge$loadTexture(ResourceLocationBridge var1, Bridge8Extension3 var2);

   void bridge$bindTexture(ResourceLocationBridge var1);

   void bridge$deleteTexture(ResourceLocationBridge var1);

   @NotNull
   Bridge8Extension3 bridge$getTexture(ResourceLocationBridge var1);

   @Nullable
   default Bridge8Extension3 method1(ResourceLocationBridge var1) {
      return this.bridge$getTextureMap().get(var1);
   }

   default Optional<Bridge3Extension_7> method2(ResourceLocationBridge var1) {
      Bridge8Extension3 var2 = this.bridge$getTexture(var1);
      return var2.method2() instanceof Bridge3Extension_7 var4 ? Optional.of(var4) : Optional.empty();
   }

   default Bridge8Extension34 method3(ResourceLocationBridge var1, Bridge3_4 var2) {
      Bridge8Extension34 var3 = Bridge.method8().method47(var2);
      this.bridge$loadTexture(var1, var3);
      return var3;
   }

   ResourceLocationBridge bridge$getDynamicTextureLocation(String var1, Bridge8Extension33 var2);

   Map<ResourceLocationBridge, Bridge8Extension3> bridge$getTextureMap();
}
