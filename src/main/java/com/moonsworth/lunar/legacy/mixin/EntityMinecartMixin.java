package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityMinecartBridge;
import net.minecraft.entity.item.EntityMinecart;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityMinecart.class)
public abstract class EntityMinecartMixin implements EntityMinecartBridge {
   public EntityMinecartMixin() {
   }
}
