package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge11_5;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderGuardian;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.entity.RenderGuardian;
import net.minecraft.entity.monster.EntityGuardian;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(RenderGuardian.class)
public class RenderGuardianMixin {
   public RenderGuardianMixin() {
   }

   @Inject(
      method = "doRender(Lnet/minecraft/entity/monster/EntityGuardian;DDDFF)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderLiving;doRender(Lnet/minecraft/entity/EntityLiving;DDDFF)V",
         shift = Shift.AFTER
      ),
      cancellable = true
   )
   private void lunar$fireRenderBeamEvent(EntityGuardian entityguardian1, double value, double value2, double value3, float value4, float value5, CallbackInfo callback10) {
      EventRenderGuardian highlightimpl1911 = (EventRenderGuardian)LunarEventBus.method29().method12(EventRenderGuardian.class, () -> new EventRenderGuardian((Bridge11_5)entityguardian1));
      if (highlightimpl1911 != null && highlightimpl1911.isCancelled()) {
         callback10.cancel();
      }
   }
}
