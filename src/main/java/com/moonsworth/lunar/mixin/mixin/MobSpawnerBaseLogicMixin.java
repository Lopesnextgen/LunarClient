package com.moonsworth.lunar.mixin.mixin;

import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MobSpawnerBaseLogic.class)
public abstract class MobSpawnerBaseLogicMixin {
   public MobSpawnerBaseLogicMixin() {
   }

   @Shadow
   public abstract World getSpawnerWorld();

   @ModifyArg(
      method = "getEntityToRender",
      at = @At(
         value = "INVOKE",
         target = "net/minecraft/entity/EntityList.createEntityByName (Ljava/lang/String;Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;"
      )
   )
   private World impl$fillInWorldValue(World world1) {
      return this.getSpawnerWorld();
   }
}
