package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.minecraft.ModelRotationBridge;
import com.moonsworth.lunar.bridge.minecraft.EnumFacingBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(ModelRotation.class)
public abstract class ModelRotationMixin implements ModelRotationBridge {
   public ModelRotationMixin() {
   }

   @Shadow
   public abstract EnumFacing rotateFace(EnumFacing facing1);

   public EnumFacingBridge bridge$rotateFace(EnumFacingBridge horsestats251) {
      return (EnumFacingBridge)this.rotateFace((EnumFacing)horsestats251);
   }
}
