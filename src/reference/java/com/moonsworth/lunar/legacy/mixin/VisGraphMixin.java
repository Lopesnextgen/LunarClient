package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.mod.render.staffxray.StaffXray;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.chunk.VisGraph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(VisGraph.class)
public class VisGraphMixin {
   public VisGraphMixin() {
   }

   @Inject(method = {"func_178606_a$v1_8", "setOpaqueCube$v1_12"}, at = @At("HEAD"), cancellable = true)
   public void apollo$setOpaqueCube(CallbackInfo callback1) {
      StaffXray staffxray2 = Ref.method4().method44().method10();
      if (staffxray2.method16() && staffxray2.isEnabled()) {
         callback1.cancel();
      }
   }
}
