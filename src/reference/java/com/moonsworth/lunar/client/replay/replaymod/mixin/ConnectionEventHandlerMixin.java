package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin.EventRegistrationsHandler;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin.ConnectionEventHandlerAccessor;
import com.replaymod.core.ReplayMod;
import com.replaymod.core.SettingsRegistry;
import com.replaymod.core.SettingsRegistry.SettingKey;
import com.replaymod.recording.ServerInfoExt;
import com.replaymod.recording.Setting;
import com.replaymod.recording.gui.GuiRecordingControls;
import com.replaymod.recording.handler.ConnectionEventHandler;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectionEventHandler.class)
public class ConnectionEventHandlerMixin implements ConnectionEventHandlerAccessor {
   @Shadow
   private GuiRecordingControls guiControls;

   public ConnectionEventHandlerMixin() {
   }

   @Redirect(
      method = "onConnectedToServerEvent",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/core/SettingsRegistry;get(Lcom/replaymod/core/SettingsRegistry$SettingKey;)Ljava/lang/Object;")
   )
   public <T> Object ichor$get(SettingsRegistry settingsregistry1, SettingKey<T> settingkey2) {
      if (settingkey2 != Setting.AUTO_START_RECORDING) {
         return settingsregistry1.get(settingkey2);
      } else {
         return !Ref.method4().method40().method64().isEnabled() ? false : settingsregistry1.get(settingkey2);
      }
   }

   @Redirect(
      method = "onConnectedToServerEvent",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/recording/ServerInfoExt;getAutoRecording()Ljava/lang/Boolean;")
   )
   public Boolean ichor$autoRecording(ServerInfoExt serverinfoext1) {
      return !Ref.method4().method40().method64().isEnabled() ? false : serverinfoext1.getAutoRecording();
   }

   @Redirect(method = "onConnectedToServerEvent", at = @At(value = "INVOKE", target = "Lcom/replaymod/core/ReplayMod;isMinimalMode()Z"))
   public boolean ichor$isMinimalMode() {
      return !Ref.method4().method40().method64().isEnabled() ? false : ReplayMod.isMinimalMode();
   }

   @Inject(
      method = "onConnectedToServerEvent",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/core/ReplayMod;printInfoToChat(Ljava/lang/String;[Ljava/lang/Object;)V")
   )
   public void ichor$autoRecord(NetworkManager networkmanager1, CallbackInfo callback2) {
      EventRegistrationsHandler.field4 = true;
   }

   public GuiRecordingControls brige$getGuiControls() {
      return this.guiControls;
   }
}
