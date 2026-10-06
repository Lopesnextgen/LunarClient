package com.moonsworth.lunar.legacy.mixin;

import org.apache.logging.log4j.core.net.JndiManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(JndiManager.class)
public class JndiManagerMixin {
   @Overwrite
   public <T> T lookup(String var1) {
      return null;
   }
}
