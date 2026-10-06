package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge11_5;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.monster.EntityGuardian;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(min = 1)
@Mixin(EntityGuardian.class)
public class EntityGuardianMixin implements Bridge11_5 {
   public EntityGuardianMixin() {
   }
}
