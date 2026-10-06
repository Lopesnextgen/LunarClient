package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.moonsworth.lunar.replaymod.forge.v1_8.mixin.MixinHelper2;
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
public class ConnectionEventHandlerMixin_v1_8 implements MixinHelper2 {
   @Shadow
   private GuiRecordingControls guiControls;

   @Redirect(
      method = "onConnectedToServerEvent",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/core/SettingsRegistry;get(Lcom/replaymod/core/SettingsRegistry$SettingKey;)Ljava/lang/Object;")
   )
   public <T> Object ichor$get(SettingsRegistry var1, SettingKey<T> var2) {
      if (var2 != Setting.AUTO_START_RECORDING) {
         return var1.get(var2);
      } else {
         return !ThreadModuleDump63.method4().method40().method64().isEnabled() ? false : var1.get(var2);
      }
   }

   @Redirect(
      method = "onConnectedToServerEvent",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/recording/ServerInfoExt;getAutoRecording()Ljava/lang/Boolean;")
   )
   public Boolean ichor$autoRecording(ServerInfoExt var1) {
      return !ThreadModuleDump63.method4().method40().method64().isEnabled() ? false : var1.getAutoRecording();
   }

   @Redirect(method = "onConnectedToServerEvent", at = @At(value = "INVOKE", target = "Lcom/replaymod/core/ReplayMod;isMinimalMode()Z"))
   public boolean ichor$isMinimalMode() {
      return !ThreadModuleDump63.method4().method40().method64().isEnabled() ? false : ReplayMod.isMinimalMode();
   }

   @Inject(
      method = "onConnectedToServerEvent",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/core/ReplayMod;printInfoToChat(Ljava/lang/String;[Ljava/lang/Object;)V")
   )
   public void ichor$autoRecord(NetworkManager var1, CallbackInfo var2) {
      EventRegistrationsHandler.field4 = true;
   }

   @Override
   public GuiRecordingControls brige$getGuiControls() {
      return this.guiControls;
   }
}
