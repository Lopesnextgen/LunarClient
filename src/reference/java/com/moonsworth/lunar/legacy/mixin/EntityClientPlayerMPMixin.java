package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.DamageSourceQuery;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.combat.OtherPlayerDamageEvent;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.RewindFrameEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Annotation2(max = 0)
@Mixin(EntityClientPlayerMP.class)
public abstract class EntityClientPlayerMPMixin {
   @Inject(method = "attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
   private void lunar$entityLivingHurtEvent(DamageSource var1, float var2, CallbackInfoReturnable<Boolean> var3) {
      OtherPlayerDamageEvent var4 = ClientEventBus.method29()
         .method12(OtherPlayerDamageEvent.class, () -> new OtherPlayerDamageEvent((Bridge5Extension_5)this, (DamageSourceQuery)var1, var2));
      if (var4 != null && var4.isCancelled()) {
         var3.setReturnValue(false);
      }

      if (var2 == 0.0F) {
         ThreadModuleDump63.method4().method45().method10((Bridge5Extension_5)this, false);
      }
   }

   @Inject(
      method = "damageEntity(Lnet/minecraft/util/DamageSource;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityClientPlayerMP;setHealth(F)V")
   )
   private void lunar$stopAnimatingOnDamage(DamageSource var1, float var2, CallbackInfo var3) {
      if (var2 > 0.0F) {
         ThreadModuleDump63.method4().method45().method10((Bridge5Extension_5)this, false);
      }
   }

   @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
   private void lunar$chatMessageEvents(String var1, CallbackInfo var2) {
      if (var1.startsWith("/")) {
         EventCommandLegacy var3 = ClientEventBus.method29().method12(EventCommandLegacy.class, () -> new EventCommandLegacy(var1));
         if (var3 != null && var3.isCancelled()) {
            var2.cancel();
         }
      }
   }

   @Inject(method = "dropOneItem$v1_7", at = @At("HEAD"))
   private void lunar$dropItemEvent(boolean var1, CallbackInfoReturnable<Boolean> var2) {
      ClientEventBus.method29().method12(RewindFrameEvent.class, () -> new RewindFrameEvent(var1));
   }
}
