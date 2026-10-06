package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ModelHumanoidHeadBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(ModelHumanoidHead.class)
public class ModelHumanoidHeadMixin implements ModelHumanoidHeadBridge {
   @Final
   @Shadow
   public ModelRenderer head;

   public ModelHumanoidHeadMixin() {
   }

   public void bridge$showHat(boolean flag) {
      this.head.showModel = flag;
   }
}
