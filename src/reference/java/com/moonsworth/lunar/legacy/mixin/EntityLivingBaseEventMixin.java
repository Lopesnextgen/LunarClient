package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.DamageSourceBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.combat.EventPlayerReceiveDamage;
import com.moonsworth.lunar.client.event.entity.EventEntityHurtAnimation;
import com.moonsworth.lunar.client.event.player.EventItemUseFinish;
import com.moonsworth.lunar.client.event.entity.EventEntityHealthUpdate;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.HeldItemAnimations;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
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
public abstract class EntityLivingBaseEventMixin {
   @Shadow
   public ItemStack activeItemStack$v1_12;

   public EntityLivingBaseEventMixin() {
   }

   @Shadow
   public abstract float getHealth();

   @Shadow
   public abstract boolean isServerWorld();

   @VersionGate(max = 0)
   @ModifyArg(
      method = "setHealth",
      index = 1,
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/datasync/EntityDataManager;updateObject$v1_7(ILjava/lang/Object;)V")
   )
   private Object lunar$setHealth$v1_7(int number1, Object obj2) {
      if (!this.isServerWorld()) {
         LunarEventBus.method29().method12(EventEntityHealthUpdate.class, () -> new EventEntityHealthUpdate((EntityLivingBridge)this, this.getHealth(), (Float)obj2));
      }

      return obj2;
   }

   @VersionGate(min = 1, max = 1)
   @ModifyArg(
      method = "setHealth",
      index = 1,
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/datasync/EntityDataManager;updateObject$v1_8(ILjava/lang/Object;)V")
   )
   private Object lunar$setHealth$v1_8(int number1, Object obj2) {
      if (!this.isServerWorld()) {
         LunarEventBus.method29().method12(EventEntityHealthUpdate.class, () -> new EventEntityHealthUpdate((EntityLivingBridge)this, this.getHealth(), (Float)obj2));
      }

      return obj2;
   }

   @VersionGate(min = 5)
   @ModifyArg(
      method = "setHealth",
      index = 1,
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/datasync/EntityDataManager;set$v1_12(Lnet/minecraft/network/datasync/DataParameter;Ljava/lang/Object;)V"
      )
   )
   private Object lunar$setHealth(Object obj1) {
      if (!this.isServerWorld()) {
         LunarEventBus.method29().method12(EventEntityHealthUpdate.class, () -> new EventEntityHealthUpdate((EntityLivingBridge)this, this.getHealth(), (Float)obj1));
      }

      return obj1;
   }

   @VersionGate(min = 5)
   @Inject(method = "onItemUseFinish$v1_12", at = @At("HEAD"))
   private void lunar$finishUsingItemEvent(CallbackInfo callback1) {
      if (!this.isServerWorld() && this instanceof Bridge6_10 bridge6_102) {
         LunarEventBus.method29().method12(EventItemUseFinish.class, () -> new EventItemUseFinish(bridge6_102, (ItemStackBridge)this.activeItemStack$v1_12));
      }
   }

   @VersionGate(min = 5)
   @Inject(
      method = "updatePotionEffects",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/datasync/EntityDataManager;get(Lnet/minecraft/network/datasync/DataParameter;)Ljava/lang/Object;",
         ordinal = 0
      ),
      cancellable = true
   )
   private void lunar$cancelLocalPlayerParticles$v1_12(CallbackInfo callback1) {
      this.lunar$cancelLocalPlayerParticles(callback1);
   }

   @VersionGate(max = 1)
   @Inject(
      method = "updatePotionEffects",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DataWatcher;getWatchableObjectInt(I)I", ordinal = 0),
      cancellable = true
   )
   private void lunar$cancelLocalPlayerParticles$v1_7(CallbackInfo callback1) {
      this.lunar$cancelLocalPlayerParticles(callback1);
   }

   @Unique
   private void lunar$cancelLocalPlayerParticles(CallbackInfo callback1) {
      if (this instanceof Bridge5Extension_5
         && Ref.method3().bridge$getGameSettings().bridge$getThirdPersonView() == 0
         && Ref.method4().method40().method22().method13()) {
         callback1.cancel();
      }
   }

   @WrapOperation(
      method = {"handleHealthUpdate$v1_7", "handleStatusUpdate$v1_8"},
      at = @At(value = "INVOKE", target = "net/minecraft/entity/EntityLivingBase.attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z")
   )
   private boolean lunar$postReceiveDamage(EntityLivingBase entity1, DamageSource source2, float value3, Operation<Boolean> operation4) {
      boolean flag5 = (Boolean)operation4.call(new Object[]{entity1, source2, value3});
      if (this instanceof AbstractClientPlayer) {
         LunarEventBus.method29().method12(EventPlayerReceiveDamage.class, () -> new EventPlayerReceiveDamage((Bridge5_11)entity1, (DamageSourceBridge)source2));
      }

      LunarEventBus.method29().method12(EventEntityHurtAnimation.class, () -> new EventEntityHurtAnimation((EntityLivingBridge)this, 0.0F));
      return flag5;
   }

   @Inject(method = "performHurtAnimation", at = @At("RETURN"))
   private void lunar$onHurtAnimation(CallbackInfo callback1) {
      if (!this.isServerWorld()) {
         LunarEventBus.method29().method12(EventEntityHurtAnimation.class, () -> new EventEntityHurtAnimation((EntityLivingBridge)this, 0.0F));
      }
   }

   @Inject(
      method = {"moveEntityWithHeading$v1_7", "travel$v1_12"},
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;prevLimbSwingAmount:F"),
      cancellable = true
   )
   private void lunar$rewindDisableLimbCalculation(CallbackInfo callback1) {
      if (this == Ref.method7() && Ref.method4().method40().method85().method19()) {
         callback1.cancel();
      }
   }

   @WrapOperation(method = "onEntityUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isEntityInsideOpaqueBlock()Z"))
   private boolean lunar$rewindSkipUpdate$opaque(EntityLivingBase entity1, Operation<Boolean> operation2) {
      return Ref.method4().method40().method85().method17(arg0 -> arg0.method41().method18() / 50L > 20L)
         ? false
         : (Boolean)operation2.call(new Object[]{entity1});
   }

   @WrapOperation(
      method = "onEntityUpdate",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isInsideOfMaterial(Lnet/minecraft/block/material/Material;)Z")
   )
   private boolean lunar$rewindSkipUpdate$material(EntityLivingBase entity1, Material material2, Operation<Boolean> operation3) {
      return Ref.method4().method40().method85().method17(arg0 -> arg0.method41().method18() / 50L > 20L)
         ? false
         : (Boolean)operation3.call(new Object[]{entity1, material2});
   }

   @WrapOperation(method = "onEntityUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;updatePotionEffects()V"))
   private void lunar$rewindSkipUpdate$potions(EntityLivingBase entity1, Operation<Void> operation2) {
      if (!Ref.method4().method40().method85().method17(arg0 -> arg0.method41().method18() / 50L > 20L)) {
         operation2.call(new Object[]{entity1});
      }
   }

   @Inject(method = "getArmSwingAnimationEnd", at = @At("RETURN"), cancellable = true)
   private void lunar$swingSpeedHook(CallbackInfoReturnable<Integer> callbackinforeturnable1) {
      HeldItemAnimations helditemanimations2 = Ref.method4().method40().method83().method14();
      if (helditemanimations2.isEnabled()) {
         callbackinforeturnable1.setReturnValue((int)(callbackinforeturnable1.getReturnValueI() * helditemanimations2.method19()));
      }
   }

   @WrapOperation(
      method = "getArmSwingAnimationEnd",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isPotionActive(Lnet/minecraft/potion/Potion;)Z", ordinal = 0)
   )
   private boolean lunar$ignoreHasteHook(EntityLivingBase entity1, Potion potion2, Operation<Boolean> operation3) {
      return Ref.method4().method40().method83().method14().method14() ? false : (Boolean)operation3.call(new Object[]{entity1, potion2});
   }

   @WrapOperation(
      method = "getArmSwingAnimationEnd",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isPotionActive(Lnet/minecraft/potion/Potion;)Z", ordinal = 1)
   )
   private boolean lunar$ignoreMiningFatigueHook(EntityLivingBase entity1, Potion potion2, Operation<Boolean> operation3) {
      return Ref.method4().method40().method83().method14().method15() ? false : (Boolean)operation3.call(new Object[]{entity1, potion2});
   }
}
