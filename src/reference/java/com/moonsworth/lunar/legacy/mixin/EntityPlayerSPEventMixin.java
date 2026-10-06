package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.minecraft.DamageSourceBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.combat.EventOtherPlayerDamage;
import com.moonsworth.lunar.client.event.combat.EventEnchantmentCriticalHit;
import com.moonsworth.lunar.client.event.player.EventLocalPlayerDeath;
import com.moonsworth.lunar.client.event.combat.EventCriticalHit;
import com.moonsworth.lunar.client.event.player.EventPlayerLivingUpdate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventDropItem;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(min = 1)
@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPEventMixin extends AbstractClientPlayer {
   @Final
   @Shadow
   public net.minecraft.stats.StatFileWriter statWriter;

   public EntityPlayerSPEventMixin(World world1, GameProfile gameprofile2) {
      super(world1, gameprofile2);
   }

   @Inject(method = "attackEntityFrom$v1_8(Lnet/minecraft/util/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
   private void lunar$entityLivingHurtEvent(DamageSource source1, float value2, CallbackInfoReturnable<Boolean> callbackinforeturnable3) {
      EventOtherPlayerDamage highlightimpl15_24 = (EventOtherPlayerDamage)LunarEventBus.method29()
         .method12(EventOtherPlayerDamage.class, () -> new EventOtherPlayerDamage((Bridge5Extension_5)this, (DamageSourceBridge)source1, value2));
      if (highlightimpl15_24 != null && highlightimpl15_24.isCancelled()) {
         callbackinforeturnable3.setReturnValue(false);
      }

      if (value2 == 0.0F) {
         Ref.method4().method45().method10((Bridge5Extension_5)this, false);
      }
   }

   @Inject(
      method = "damageEntity$v1_8(Lnet/minecraft/util/DamageSource;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setHealth(F)V")
   )
   private void lunar$stopAnimatingOnDamage(DamageSource source1, float value2, CallbackInfo callback3) {
      if (value2 > 0.0F) {
         Ref.method4().method45().method10((Bridge5Extension_5)this, false);
      }
   }

   @Inject(method = "onCriticalHit(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$criticalStrikeEvent(Entity entity1, CallbackInfo callback2) {
      EventCriticalHit highlightimpl83 = (EventCriticalHit)LunarEventBus.method29().method12(EventCriticalHit.class, () -> new EventCriticalHit((BridgeExtension)entity1));
      if (highlightimpl83 != null && highlightimpl83.isCancelled()) {
         callback2.cancel();
      }
   }

   @Inject(method = "onEnchantmentCritical(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$enchantCriticalStrikeEvent(Entity entity1, CallbackInfo callback2) {
      EventEnchantmentCriticalHit highlightimpl43 = (EventEnchantmentCriticalHit)LunarEventBus.method29().method12(EventEnchantmentCriticalHit.class, () -> new EventEnchantmentCriticalHit((BridgeExtension)entity1));
      if (highlightimpl43 != null && highlightimpl43.isCancelled()) {
         callback2.cancel();
      }
   }

   @Inject(method = "onLivingUpdate()V", at = @At("HEAD"))
   private void lunar$livingUpdateEvent(CallbackInfo callback1) {
      LunarEventBus.method29().method12(EventPlayerLivingUpdate.class, EventPlayerLivingUpdate::new);
      if (this.isDead && this.getUniqueID().equals(Ref.method7().bridge$getUniqueID())) {
         LunarEventBus.method29().method12(EventLocalPlayerDeath.class, EventLocalPlayerDeath::new);
      }
   }

   @Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setSprinting(Z)V", ordinal = 0))
   private void lunar$onSprinting(EntityPlayerSP player1, boolean flag2) {
      if (!flag2
         || !Ref.method4().method40().method28().isEnabled()
         || (Boolean)Ref.method4().method40().method28().method34().get()) {
         player1.setSprinting(flag2);
      }
   }

   @Inject(method = "sendChatMessage$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$chatMessageEvents(String text1, CallbackInfo callback2) {
      if (text1.startsWith("/")) {
         com.moonsworth.lunar.client.event.mixin.EventCommand highlightimpl43 = (com.moonsworth.lunar.client.event.mixin.EventCommand)LunarEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.mixin.EventCommand.class, () -> new com.moonsworth.lunar.client.event.mixin.EventCommand(text1)
            );
         if (highlightimpl43 != null && highlightimpl43.isCancelled()) {
            callback2.cancel();
         }
      }
   }

   @WrapWithCondition(
      method = "onLivingUpdate()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V")
   )
   private boolean apollo$handleAntiPortalTraps(Minecraft minecraft1, GuiScreen screen2) {
      return Ref.method4()
         .method84()
         .method3(ServerRuleModule.class)
         .filter(arg0 -> (Boolean)arg0.getOptions().get(ServerRuleModule.ANTI_PORTAL_TRAPS))
         .filter(arg0 -> Ref.method3().bridge$getCurrentScreen() instanceof Bridge5Extension612)
         .isEmpty();
   }

   @VersionGate(1)
   public Vec3 getLook(float value1) {
      return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
   }

   public void addStat(StatBase statbase1, int number2) {
      if (statbase1 != null) {
         this.statWriter.increaseStat(this, statbase1, number2);
      }
   }

   @VersionGate(min = 5)
   @Inject(method = "dropItem", at = @At("HEAD"))
   private void lunar$dropItem$v1_12(boolean flag1, CallbackInfoReturnable<EntityItem> callbackinforeturnable2) {
      ItemStack stack3 = this.inventory.getCurrentItem();
      if (!stack3.isEmpty()) {
         Item item4 = stack3.getItem();
         if (item4 != Items.AIR$v1_12) {
            this.statWriter.increaseStat(this, StatList.getDroppedObjectStats$v1_12(item4), flag1 ? stack3.getCount$v1_12() : 1);
         }
      }

      this.statWriter.increaseStat(this, StatList.dropStat, 1);
   }

   @Inject(method = "isCurrentViewEntity$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindFixPlayerAnimations(CallbackInfoReturnable<Boolean> callbackinforeturnable1) {
      if (Ref.method4().method40().method85().method19()) {
         callbackinforeturnable1.setReturnValue(true);
      }
   }

   @WrapOperation(method = "onLivingUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_8;isKeyDown()Z"))
   private boolean lunar$rewindIgnoreSprintKey(KeyBinding keybinding1, Operation<Boolean> operation2) {
      return Ref.method4().method40().method85().method19() ? false : (Boolean)operation2.call(new Object[]{keybinding1});
   }

   @Inject(method = {"dropOneItem$v1_8", "dropItem$v1_12(Z)Lnet/minecraft/entity/item/EntityItem;"}, at = @At("HEAD"))
   private void lunar$dropItemEvent(boolean flag1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      LunarEventBus.method29().method12(EventDropItem.class, () -> new EventDropItem(flag1));
   }
}
