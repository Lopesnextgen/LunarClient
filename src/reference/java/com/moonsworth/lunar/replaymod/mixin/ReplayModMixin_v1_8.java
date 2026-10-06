package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.gui.notification.Notification;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.replaymod.core.ReplayMod;
import com.replaymod.core.Setting;
import com.replaymod.core.SettingsRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReplayMod.class)
public abstract class ReplayModMixin_v1_8 implements Calculator2 {
   @Shadow
   public abstract SettingsRegistry getSettingsRegistry();

   @Inject(method = "printToChat", at = @At("HEAD"), cancellable = true)
   public void ichor$printToChat(boolean flag, String text, Object[] items, CallbackInfo callbackInfo) {
      if ((Boolean)this.getSettingsRegistry().get(Setting.NOTIFICATIONS)) {
         Notification var5 = new Notification(
            flag ? NotificationType.WARNING.getIcon() : NotificationType.INFO.getIcon(),
            this.method1("replaymod.title", new Object[0]),
            this.method1(text, items)
         );
         var5.method9(NotificationAnchor.BOTTOM_RIGHT);
         ThreadModuleDump63.method4().method69().method10(var5);
         callbackInfo.cancel();
      }
   }

   public String getLanguagePath() {
      return "replaymod";
   }
}
