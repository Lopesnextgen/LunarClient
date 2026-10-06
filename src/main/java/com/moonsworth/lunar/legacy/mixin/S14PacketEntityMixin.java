package com.moonsworth.lunar.legacy.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(S14PacketEntity.class)
public class S14PacketEntityMixin {
   public S14PacketEntityMixin() {
   }

   @Inject(method = {"func_149065_a$v1_7", "getEntity$v1_8"}, at = @At("HEAD"), cancellable = true)
   private void lunar$fixNullPointer(World world1, CallbackInfoReturnable<Entity> callbackinforeturnable2) {
      if (world1 == null) {
         callbackinforeturnable2.setReturnValue(null);
      }
   }
}
