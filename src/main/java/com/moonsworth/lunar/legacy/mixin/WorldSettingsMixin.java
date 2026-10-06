package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.world.WorldSettingsBridge;
import net.minecraft.world.WorldSettings;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldSettings.class)
public class WorldSettingsMixin implements WorldSettingsBridge {
   public WorldSettingsMixin() {
   }
}
