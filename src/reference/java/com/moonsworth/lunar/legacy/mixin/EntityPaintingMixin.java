package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityPaintingBridge;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityPainting;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPainting.class)
public abstract class EntityPaintingMixin extends EntityHanging implements EntityPaintingBridge {
   public EntityPaintingMixin() {
   }
}
