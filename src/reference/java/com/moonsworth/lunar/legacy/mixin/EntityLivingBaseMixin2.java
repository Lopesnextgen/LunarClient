package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.DamageSourceQuery;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.combat.EventPlayerDamaged;
import com.moonsworth.lunar.client.event.entity.EventHurtAnimation;
import com.moonsworth.lunar.client.event.player.EventUseItemFinish;
import com.moonsworth.lunar.client.event.entity.EventEntityHealthChange;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.HeldItemAnimations;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin2 {
   @Shadow
   public ItemStack activeItemStack$v1_12;

   @Shadow
   public abstract float getHealth();

   @Shadow
   public abstract boolean isServerWorld();

   @Annotation2(max = 0)
   @ModifyArg(
      method = "setHealth",
      index = 1,
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/datasync/EntityDataManager;updateObject$v1_7(ILjava/lang/Object;)V")
   )
   private Object lunar$setHealth$v1_7(int var1, Object var2) {
      if (!this.isServerWorld()) {
         ClientEventBus.method29().method12(EventEntityHealthChange.class, () -> new EventEntityHealthChange((BridgeExtension2_5)this, this.getHealth(), (Float)var2));
      }

      return var2;
   }

   @Annotation2(min = 1, max = 1)
   @ModifyArg(
      method = "setHealth",
      index = 1,
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/datasync/EntityDataManager;updateObject$v1_8(ILjava/lang/Object;)V")
   )
   private Object lunar$setHealth$v1_8(int var1, Object var2) {
      if (!this.isServerWorld()) {
         ClientEventBus.method29().method12(EventEntityHealthChange.class, () -> new EventEntityHealthChange((BridgeExtension2_5)this, this.getHealth(), (Float)var2));
      }

      return var2;
   }

   @Annotation2(min = 5)
   @ModifyArg(
      method = "setHealth",
      index = 1,
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/datasync/EntityDataManager;set$v1_12(Lnet/minecraft/network/datasync/DataParameter;Ljava/lang/Object;)V"
      )
   )
   private Object lunar$setHealth(Object var1) {
      if (!this.isServerWorld()) {
         ClientEventBus.method29().method12(EventEntityHealthChange.class, () -> new EventEntityHealthChange((BridgeExtension2_5)this, this.getHealth(), (Float)var1));
      }

      return var1;
   }

   @Annotation2(min = 5)
   @Inject(method = "onItemUseFinish$v1_12", at = @At("HEAD"))
   private void lunar$finishUsingItemEvent(CallbackInfo var1) {
      if (!this.isServerWorld() && this instanceof Bridge6_10 var2) {
         ClientEventBus.method29().method12(EventUseItemFinish.class, () -> new EventUseItemFinish(var2, (ItemStackBridge)this.activeItemStack$v1_12));
      }
   }

   @Annotation2(min = 5)
   @Inject(
      method = "updatePotionEffects",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/datasync/EntityDataManager;get(Lnet/minecraft/network/datasync/DataParameter;)Ljava/lang/Object;",
         ordinal = 0
      ),
      cancellable = true
   )
   private void lunar$cancelLocalPlayerParticles$v1_12(CallbackInfo var1) {
      this.lunar$cancelLocalPlayerParticles(var1);
   }

   @Annotation2(max = 1)
   @Inject(
      method = "updatePotionEffects",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DataWatcher;getWatchableObjectInt(I)I", ordinal = 0),
      cancellable = true
   )
   private void lunar$cancelLocalPlayerParticles$v1_7(CallbackInfo var1) {
      this.lunar$cancelLocalPlayerParticles(var1);
   }

   @Unique
   private void lunar$cancelLocalPlayerParticles(CallbackInfo var1) {
      if (this instanceof Bridge5Extension_5
         && ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getThirdPersonView() == 0
         && ThreadModuleDump63.method4().method40().method22().method13()) {
         var1.cancel();
      }
   }

   @WrapOperation(
      method = {"handleHealthUpdate$v1_7", "handleStatusUpdate$v1_8"},
      at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z")
   )
   private boolean lunar$postReceiveDamage(EntityLivingBase var1, DamageSource var2, float var3, Operation<Boolean> var4) {
      boolean var5 = (Boolean)var4.call(new Object[]{var1, var2, var3});
      if (this instanceof AbstractClientPlayer) {
         ClientEventBus.method29().method12(EventPlayerDamaged.class, () -> new EventPlayerDamaged((Bridge5_11)var1, (DamageSourceQuery)var2));
      }

      ClientEventBus.method29().method12(EventHurtAnimation.class, () -> new EventHurtAnimation((BridgeExtension2_5)this, 0.0F));
      return var5;
   }

   @Inject(method = "performHurtAnimation", at = @At("RETURN"))
   private void lunar$onHurtAnimation(CallbackInfo var1) {
      if (!this.isServerWorld()) {
         ClientEventBus.method29().method12(EventHurtAnimation.class, () -> new EventHurtAnimation((BridgeExtension2_5)this, 0.0F));
      }
   }

   @Inject(
      method = {"moveEntityWithHeading$v1_7", "travel$v1_12"},
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;prevLimbSwingAmount:F"),
      cancellable = true
   )
   private void lunar$rewindDisableLimbCalculation(CallbackInfo var1) {
      if (this == ThreadModuleDump63.method7() && ThreadModuleDump63.method4().method40().method85().method19()) {
         var1.cancel();
      }
   }

   @WrapOperation(method = "onEntityUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isEntityInsideOpaqueBlock()Z"))
   private boolean lunar$rewindSkipUpdate$opaque(EntityLivingBase var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L)
         ? false
         : (Boolean)var2.call(new Object[]{var1});
   }

   @WrapOperation(
      method = "onEntityUpdate",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isInsideOfMaterial(Lnet/minecraft/block/material/Material;)Z")
   )
   private boolean lunar$rewindSkipUpdate$material(EntityLivingBase var1, Material var2, Operation<Boolean> var3) {
      return ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L)
         ? false
         : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @WrapOperation(method = "onEntityUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;updatePotionEffects()V"))
   private void lunar$rewindSkipUpdate$potions(EntityLivingBase var1, Operation<Void> var2) {
      if (!ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L)) {
         var2.call(new Object[]{var1});
      }
   }

   @Inject(method = "getArmSwingAnimationEnd", at = @At("RETURN"), cancellable = true)
   private void lunar$swingSpeedHook(CallbackInfoReturnable<Integer> var1) {
      HeldItemAnimations var2 = ThreadModuleDump63.method4().method40().method83().method14();
      if (var2.isEnabled()) {
         var1.setReturnValue((int)(var1.getReturnValueI() * var2.method19()));
      }
   }

   @WrapOperation(
      method = "getArmSwingAnimationEnd",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isPotionActive(Lnet/minecraft/potion/Potion;)Z", ordinal = 0)
   )
   private boolean lunar$ignoreHasteHook(EntityLivingBase var1, Potion var2, Operation<Boolean> var3) {
      return ThreadModuleDump63.method4().method40().method83().method14().method14() ? false : (Boolean)var3.call(new Object[]{var1, var2});
   }

   @WrapOperation(
      method = "getArmSwingAnimationEnd",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isPotionActive(Lnet/minecraft/potion/Potion;)Z", ordinal = 1)
   )
   private boolean lunar$ignoreMiningFatigueHook(EntityLivingBase var1, Potion var2, Operation<Boolean> var3) {
      return ThreadModuleDump63.method4().method40().method83().method14().method15() ? false : (Boolean)var3.call(new Object[]{var1, var2});
   }
}
