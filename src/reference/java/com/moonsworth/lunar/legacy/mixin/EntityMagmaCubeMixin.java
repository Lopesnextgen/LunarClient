package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityMagmaCubeBridge;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityMagmaCube.class)
public abstract class EntityMagmaCubeMixin extends EntitySlime implements EntityMagmaCubeBridge {
   public EntityMagmaCubeMixin() {
   }

   public int bridge$getSize() {
      return this.getSlimeSize();
   }
}
