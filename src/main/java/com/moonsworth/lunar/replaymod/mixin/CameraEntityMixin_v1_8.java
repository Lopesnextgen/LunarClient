package com.moonsworth.lunar.replaymod.mixin;

import com.replaymod.core.utils.Utils;
import com.replaymod.replay.camera.CameraEntity;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CameraEntity.class)
public class CameraEntityMixin_v1_8 {
   private long lastUpdate;
   @Shadow
   public float roll;

   @Redirect(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"), expect = 2, require = 2)
   public boolean ichor$update(KeyBinding var1) {
      long var2 = System.currentTimeMillis();
      if (var1.isKeyDown()) {
         long var4 = var2 - this.lastUpdate;
         if (var1.getKeyDescription().contains("rollclockwise")) {
            this.roll = (float)(this.roll + (Utils.isCtrlDown() ? 0.2 : 1.0) * var4 * 0.05F);
         } else {
            this.roll = (float)(this.roll - (Utils.isCtrlDown() ? 0.2 : 1.0) * var4 * 0.05F);
         }
      }

      return false;
   }

   @Inject(method = "update", at = @At("TAIL"))
   public void ichor$update$lastUpdate(CallbackInfo var1) {
      this.lastUpdate = System.currentTimeMillis();
   }
}
