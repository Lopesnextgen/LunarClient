package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityLightningBoltBridge;
import net.minecraft.entity.effect.EntityLightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLightningBolt.class)
public class EntityLightningBoltMixin implements EntityLightningBoltBridge {
   @Unique
   public boolean lunar$weatherChangerLightning = false;
   @Shadow
   public int lightningState;
   @Unique
   private int lunar$oldLightningState = -1;

   public EntityLightningBoltMixin() {
   }

   @Inject(method = "onUpdate", at = @At("HEAD"))
   private void lunar$onUpdate(CallbackInfo callback1) {
      if (this.lunar$weatherChangerLightning) {
         this.lunar$oldLightningState = this.lightningState;
         this.lightningState = 3;
      }
   }

   @Inject(
      method = "onUpdate",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/effect/EntityLightningBolt;lightningState:I", ordinal = 0, shift = Shift.AFTER)
   )
   private void lunar$onUpdate$post(CallbackInfo callback1) {
      if (this.lunar$weatherChangerLightning) {
         this.lightningState = this.lunar$oldLightningState - 1;
      }
   }

   public void bridge$setAddedByWeatherChanger(boolean flag) {
      this.lunar$weatherChangerLightning = flag;
   }
}
