package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension2;
import com.moonsworth.lunar.bridge.minecraft.DamageSourceBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.combat.EventOtherPlayerDamage;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityOtherPlayerMP.class)
public abstract class EntityOtherPlayerMPMixin extends AbstractClientPlayer implements Bridge5Extension2 {
   public EntityOtherPlayerMPMixin() {
   }

   @Inject(method = "attackEntityFrom", at = @At("HEAD"))
   private void lunar$attackEntityFrom(DamageSource source1, float value2, CallbackInfoReturnable<Boolean> callbackinforeturnable3) {
      EventOtherPlayerDamage highlightimpl15_24 = (EventOtherPlayerDamage)LunarEventBus.method29()
         .method12(EventOtherPlayerDamage.class, () -> new EventOtherPlayerDamage(this, (DamageSourceBridge)source1, value2));
      if (highlightimpl15_24 != null && highlightimpl15_24.isCancelled()) {
         callbackinforeturnable3.setReturnValue(false);
      }
   }

   @VersionGate(max = 1)
   @Inject(
      method = "onUpdate",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   private void bridge$fixNullItems(CallbackInfo callback1, double value2, double value4, float value6, ItemStack stack7) {
      if (stack7.getItem() == null) {
         callback1.cancel();
      }
   }
}
