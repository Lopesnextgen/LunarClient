package com.moonsworth.lunar.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.legacy.MultiBridge;

public class MultiBridgeV1_7 extends MultiBridge {
   public MultiBridgeV1_7() {
   }

   public Config getMinecraftVersion() {
      return Config.field1;
   }
}
