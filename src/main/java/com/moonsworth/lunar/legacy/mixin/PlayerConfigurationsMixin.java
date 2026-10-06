package com.moonsworth.lunar.legacy.mixin;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.optifine.player.PlayerConfiguration;
import net.optifine.player.PlayerConfigurations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerConfigurations.class)
public abstract class PlayerConfigurationsMixin {
   public PlayerConfigurationsMixin() {
   }

   @Overwrite
   public static synchronized PlayerConfiguration getPlayerConfiguration(AbstractClientPlayer player0) {
      return null;
   }
}
