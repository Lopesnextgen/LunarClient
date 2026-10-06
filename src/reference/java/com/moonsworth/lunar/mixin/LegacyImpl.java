package com.moonsworth.lunar.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.legacy.Legacy;

public class LegacyImpl extends Legacy {
   @Override
   public Config getMinecraftVersion() {
      return Config.field1;
   }
}
