package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EnumPlayerModelPartsBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.player.EnumPlayerModelParts;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(EnumPlayerModelParts.class)
public abstract class EnumPlayerModelPartsMixin implements EnumPlayerModelPartsBridge {
   @Final
   @Shadow
   public int partMask;
   @Final
   @Shadow
   public String partName;

   public EnumPlayerModelPartsMixin() {
   }

   public int bridge$getMask() {
      return this.partMask;
   }

   public String bridge$getId() {
      return this.partName;
   }
}
