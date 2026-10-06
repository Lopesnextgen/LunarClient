package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge12_3;
import net.minecraft.entity.item.EntityXPOrb;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityXPOrb.class)
public class EntityXPOrbMixin implements Bridge12_3 {
   public EntityXPOrbMixin() {
   }
}
