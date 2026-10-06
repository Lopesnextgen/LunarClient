package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.replaymod.core.SettingsRegistry;
import com.replaymod.core.SettingsRegistry.SettingKey;
import com.replaymod.extras.FullBrightness;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FullBrightness.class)
public class FullBrightnessMixin {
   public FullBrightnessMixin() {
   }

   @Redirect(
      method = "getType",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/core/SettingsRegistry;get(Lcom/replaymod/core/SettingsRegistry$SettingKey;)Ljava/lang/Object;")
   )
   public <T> T ichor$getType(SettingsRegistry settingsregistry1, SettingKey<T> settingkey2) {
      return (T)"Gamma";
   }
}
