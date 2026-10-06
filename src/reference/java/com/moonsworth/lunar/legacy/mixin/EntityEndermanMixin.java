package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityEndermanMarkerBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.combat.EventEndermanTeleport;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityEnderman.class)
public abstract class EntityEndermanMixin extends EntityMob implements EntityEndermanMarkerBridge {
   public EntityEndermanMixin() {
   }

   @Inject(method = "teleportTo", at = @At("HEAD"), cancellable = true)
   private void lunar$eventEnderTeleport(double value1, double value3, double value5, CallbackInfoReturnable<Boolean> callbackinforeturnable7) {
      if (this.world.isRemote) {
         EventEndermanTeleport highlightimpl10_28 = (EventEndermanTeleport)LunarEventBus.method29()
            .method12(EventEndermanTeleport.class, () -> new EventEndermanTeleport(value1, value3, value5, 0.0F));
         if (highlightimpl10_28 != null) {
            if (highlightimpl10_28.isCancelled()) {
               callbackinforeturnable7.setReturnValue(false);
               return;
            }

            this.posX = highlightimpl10_28.method1();
            this.posY = highlightimpl10_28.method2();
            this.posZ = highlightimpl10_28.method3();
         }
      }
   }
}
