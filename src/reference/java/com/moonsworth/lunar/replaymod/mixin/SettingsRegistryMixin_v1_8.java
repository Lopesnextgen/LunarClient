package com.moonsworth.lunar.replaymod.mixin;

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
public class SettingsRegistryMixin_v1_8 {
   @Final
   @Shadow
   private Map<SettingKey<?>, Object> settings;

   @Inject(method = "save", at = @At("HEAD"), cancellable = true)
   public void ichor$save(CallbackInfo var1) {
      var1.cancel();
   }

   @Inject(method = "register()V", at = @At("HEAD"), cancellable = true)
   public void ichor$register(CallbackInfo var1) {
      var1.cancel();
   }

   @Overwrite
   public void register(SettingKey<?> var1) {
      if (!(var1 instanceof Highlight)) {
         throw new IllegalArgumentException("Lunar Setting " + var1 + " unknown.");
      }

      Object var2 = ((Highlight)var1).getOption().get();
      this.settings.put(var1, var2);
   }

   @Overwrite
   public <T> T get(SettingKey<T> var1) {
      if (!(var1 instanceof Highlight)) {
         throw new IllegalArgumentException("Lunar Setting " + var1 + " unknown.");
      } else {
         this.settings.put(var1, ((Highlight)var1).getOption().get());
         if (!this.settings.containsKey(var1)) {
            throw new IllegalArgumentException("Setting " + var1 + " unknown.");
         } else {
            return (T)this.settings.get(var1);
         }
      }
   }

   @Overwrite
   public <T> void set(SettingKey<T> var1, T var2) {
      if (!(var1 instanceof Highlight)) {
         throw new IllegalArgumentException("Lunar Setting " + var1 + " unknown.");
      }

      if (var1.getDefault() instanceof Boolean) {
         ((Highlight)var1).getOption().method10(var2);
      } else if (var1.getDefault() instanceof Integer) {
         ((Highlight)var1).getOption().method10(var2);
      } else if (var1.getDefault() instanceof Double) {
         ((Highlight)var1).getOption().method10(var2);
      } else {
         if (!(var1.getDefault() instanceof String)) {
            throw new IllegalArgumentException("Default type " + var1.getDefault().getClass() + " not supported.");
         }

         ((Highlight)var1).getOption().method10(var2);
      }

      this.settings.put(var1, var2);
      ((SettingsChangedCallback)SettingsChangedCallback.EVENT.invoker()).onSettingsChanged((SettingsRegistry)this, var1);
   }
}
