package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BedPartTypeBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.BlockBed.EnumPartType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(EnumPartType.class)
public class EnumPartTypeMixin implements BedPartTypeBridge {
   @Final
   @Shadow
   public static EnumPartType FOOT;

   public EnumPartTypeMixin() {
   }

   public boolean bridge$isFoot() {
      return (EnumPartType)this == FOOT;
   }
}
