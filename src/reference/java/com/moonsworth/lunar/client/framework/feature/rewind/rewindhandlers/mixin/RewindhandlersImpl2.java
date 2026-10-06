package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Updater;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class RewindhandlersImpl2 extends Rewindhandlers {
   public RewindhandlersImpl2(RewindHandlers3Updater var1) {
      super(var1);
   }

   @Override
   public void method1(float var1) {
      BridgeExtension var2 = this.field1.method17();
      BridgeExtension var3 = ThreadModuleDump63.method3().bridge$getSpectatedEntity();
      if (var2 == ThreadModuleDump63.method7() && var3 != null) {
         var2 = var3;
      }

      if (ThreadModuleDump63.method3().bridge$getRenderViewEntity() != var2) {
         ThreadModuleDump63.method3().bridge$setRenderViewEntity(var2);
      }

      Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
      Object var5 = ThreadModuleDump63.method3().bridge$getRenderViewEntity();
      if (var5 == null) {
         var5 = var4;
      }

      if (var5 != null) {
         Nameplate3 var6 = ((Nameplate4)this.field1.method13().get()).method7();
         if (var5 == var4) {
            var5.bridge$setRotationYaw(var6.method2(var1));
            var5.bridge$setRotationPitch(var6.method3(var1));
            var5.bridge$setPreviousRotationYaw(var5.bridge$getRotationYaw());
            var5.bridge$setPreviousRotationPitch(var5.bridge$getRotationPitch());
         }

         GameOptionsBridge var7 = ThreadModuleDump63.method3().bridge$getGameSettings();
         if (this.field1.method15() == Gui2Extension2.POV && !this.field1.method22().isDefault()) {
            var7.bridge$setThirdPersonView(0);
         } else if (!this.field1.method15().forceThirdPersonView()) {
            var7.bridge$setThirdPersonView(var6.method9());
         } else {
            var7.bridge$setThirdPersonView(this.field1.method15().getThirdPersonView());
         }
      }
   }
}
