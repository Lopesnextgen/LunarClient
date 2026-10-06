package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityDragonBridge;
import net.minecraft.entity.boss.EntityDragon;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityDragon.class)
public class EntityDragonMixin implements EntityDragonBridge {
   public EntityDragonMixin() {
   }
}
