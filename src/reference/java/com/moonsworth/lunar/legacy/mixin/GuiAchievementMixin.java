package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiAchievementBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.stats.Achievement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(max = 1)
@Mixin(GuiAchievement.class)
public abstract class GuiAchievementMixin implements GuiAchievementBridge {
   public GuiAchievementMixin() {
   }

   @VersionGate(max = 1)
   @Inject(method = "displayAchievement$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$displayAchievement(Achievement achievement1, CallbackInfo callback2) {
      if (!(Boolean)Ref.method4().method41().method6().method45().get()) {
         callback2.cancel();
      }
   }

   @VersionGate(max = 1)
   @Inject(method = "displayUnformattedAchievement$v1_7", at = @At("HEAD"), cancellable = true)
   private void $lunardisplayUnformattedAchievement(Achievement achievement1, CallbackInfo callback2) {
      if (!(Boolean)Ref.method4().method41().method6().method45().get()) {
         callback2.cancel();
      }
   }
}
