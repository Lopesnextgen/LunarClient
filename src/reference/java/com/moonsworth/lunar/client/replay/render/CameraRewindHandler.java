package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.client.replay.gui.LocalPlayerContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.render.CameraUpdateHandler;
import com.moonsworth.lunar.client.replay.render.CameraMode;
import com.moonsworth.lunar.client.framework.Ref;

public class CameraRewindHandler extends RewindCameraController {
   public CameraRewindHandler(CameraUpdateHandler rewindhandlers3updater1) {
      super(rewindhandlers3updater1);
   }

   @Override
   public void method1(float value1) {
      BridgeExtension bridgeextension2 = this.CCIORHCRHORCHHROHCIHCOIROHROHI.method17();
      BridgeExtension bridgeextension3 = Ref.method3().bridge$getSpectatedEntity();
      if (bridgeextension2 == Ref.method7() && bridgeextension3 != null) {
         bridgeextension2 = bridgeextension3;
      }

      if (Ref.method3().bridge$getRenderViewEntity() != bridgeextension2) {
         Ref.method3().bridge$setRenderViewEntity(bridgeextension2);
      }

      Bridge5Extension_5 bridge5extension_54 = Ref.method7();
      Object obj5 = Ref.method3().bridge$getRenderViewEntity();
      if (obj5 == null) {
         obj5 = bridge5extension_54;
      }

      if (obj5 != null) {
         LocalPlayerContext nameplate36 = ((ReplayContext)this.CCIORHCRHORCHHROHCIHCOIROHROHI.HHHIRRROCCIRICICIOIIIOORRCRHIH().get()).method7();
         if (obj5 == bridge5extension_54) {
            obj5.bridge$setRotationYaw(nameplate36.method2(value1));
            obj5.bridge$setRotationPitch(nameplate36.method3(value1));
            obj5.bridge$setPreviousRotationYaw(obj5.bridge$getRotationYaw());
            obj5.bridge$setPreviousRotationPitch(obj5.bridge$getRotationPitch());
         }

         GameOptionsBridge mixinhelper2_87 = Ref.method3().bridge$getGameSettings();
         if (this.CCIORHCRHORCHHROHCIHCOIROHROHI.method15() == CameraMode.POV && !this.CCIORHCRHORCHHROHCIHCOIROHROHI.method22().isDefault()) {
            mixinhelper2_87.bridge$setThirdPersonView(0);
         } else if (!this.CCIORHCRHORCHHROHCIHCOIROHROHI.method15().forceThirdPersonView()) {
            mixinhelper2_87.bridge$setThirdPersonView(nameplate36.method9());
         } else {
            mixinhelper2_87.bridge$setThirdPersonView(this.CCIORHCRHORCHHROHCIHCOIROHROHI.method15().getThirdPersonView());
         }
      }
   }
}
