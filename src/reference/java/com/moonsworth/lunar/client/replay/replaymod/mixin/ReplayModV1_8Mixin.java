package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.ui.notification.Notification;
import com.moonsworth.lunar.client.framework.Ref;
import com.replaymod.core.ReplayMod;
import com.replaymod.core.Setting;
import com.replaymod.core.SettingsRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReplayMod.class)
public abstract class ReplayModV1_8Mixin implements Translatable {
   public ReplayModV1_8Mixin() {
   }

   @Shadow
   public abstract SettingsRegistry getSettingsRegistry();

   @Inject(method = "printToChat", at = @At("HEAD"), cancellable = true)
   public void ichor$printToChat(boolean flag1, String text2, Object[] items3, CallbackInfo callback4) {
      if ((Boolean)this.getSettingsRegistry().get(Setting.NOTIFICATIONS)) {
         Notification gui2iterator5 = new Notification(
            flag1 ? NotificationType.WARNING.getIcon() : NotificationType.INFO.getIcon(),
            this.OHROCHICOIOICHOCRROORRCIIICIHO("replaymod.title", new Object[0]),
            this.OHROCHICOIOICHOCRROORRCIIICIHO(text2, items3)
         );
         gui2iterator5.method9(NotificationAnchor.BOTTOM_RIGHT);
         Ref.method4().method69().method10(gui2iterator5);
         callback4.cancel();
      }
   }

   public String getLanguagePath() {
      return "replaymod";
   }
}
