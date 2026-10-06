package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.replay.modsettings.Highlight;
import com.replaymod.core.SettingsRegistry;
import com.replaymod.core.SettingsRegistry.SettingKey;
import com.replaymod.core.events.SettingsChangedCallback;
import java.util.Map;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SettingsRegistry.class)
public class SettingsRegistryV1_8Mixin {
   @Final
   @Shadow
   private Map<SettingKey<?>, Object> settings;

   public SettingsRegistryV1_8Mixin() {
   }

   @Inject(method = "save", at = @At("HEAD"), cancellable = true)
   public void ichor$save(CallbackInfo callback1) {
      callback1.cancel();
   }

   @Inject(method = "register()V", at = @At("HEAD"), cancellable = true)
   public void ichor$register(CallbackInfo callback1) {
      callback1.cancel();
   }

   @Overwrite
   public void register(SettingKey<?> settingkey1) {
      if (!(settingkey1 instanceof Highlight)) {
         throw new IllegalArgumentException("Lunar Setting " + settingkey1 + " unknown.");
      }

      Object obj2 = ((Highlight)settingkey1).getOption().get();
      this.settings.put(settingkey1, obj2);
   }

   @Overwrite
   public <T> T get(SettingKey<T> settingkey1) {
      if (!(settingkey1 instanceof Highlight)) {
         throw new IllegalArgumentException("Lunar Setting " + settingkey1 + " unknown.");
      } else {
         this.settings.put(settingkey1, ((Highlight)settingkey1).getOption().get());
         if (!this.settings.containsKey(settingkey1)) {
            throw new IllegalArgumentException("Setting " + settingkey1 + " unknown.");
         } else {
            return (T)this.settings.get(settingkey1);
         }
      }
   }

   @Overwrite
   public <T> void set(SettingKey<T> settingkey1, T value2) {
      if (!(settingkey1 instanceof Highlight)) {
         throw new IllegalArgumentException("Lunar Setting " + settingkey1 + " unknown.");
      }

      if (settingkey1.getDefault() instanceof Boolean) {
         ((Highlight)settingkey1).getOption().method10(value2);
      } else if (settingkey1.getDefault() instanceof Integer) {
         ((Highlight)settingkey1).getOption().method10(value2);
      } else if (settingkey1.getDefault() instanceof Double) {
         ((Highlight)settingkey1).getOption().method10(value2);
      } else {
         if (!(settingkey1.getDefault() instanceof String)) {
            throw new IllegalArgumentException("Default type " + settingkey1.getDefault().getClass() + " not supported.");
         }

         ((Highlight)settingkey1).getOption().method10(value2);
      }

      this.settings.put(settingkey1, value2);
      ((SettingsChangedCallback)SettingsChangedCallback.EVENT.invoker()).onSettingsChanged((SettingsRegistry)this, settingkey1);
   }
}
