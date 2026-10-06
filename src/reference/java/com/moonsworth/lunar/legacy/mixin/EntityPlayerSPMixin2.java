package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.DamageSourceQuery;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.combat.OtherPlayerDamageEvent;
import com.moonsworth.lunar.client.event.combat.EventEnchantCriticalHit;
import com.moonsworth.lunar.client.event.player.EventLocalDeath;
import com.moonsworth.lunar.client.event.combat.EventMeleeCriticalHit;
import com.moonsworth.lunar.client.event.network.PluginChannelRegisterEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.RewindFrameEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
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

@Annotation2(min = 1)
@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin2 extends AbstractClientPlayer {
   @Final
   @Shadow
   public net.minecraft.stats.StatFileWriter statWriter;

   public EntityPlayerSPMixin2(World var1, GameProfile var2) {
      super(var1, var2);
   }

   @Inject(method = "attackEntityFrom$v1_8(Lnet/minecraft/util/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
   private void lunar$entityLivingHurtEvent(DamageSource var1, float var2, CallbackInfoReturnable<Boolean> var3) {
      OtherPlayerDamageEvent var4 = (OtherPlayerDamageEvent)ClientEventBus.method29()
         .method12(OtherPlayerDamageEvent.class, () -> new OtherPlayerDamageEvent((Bridge5Extension_5)this, (DamageSourceQuery)var1, var2));
      if (var4 != null && var4.isCancelled()) {
         var3.setReturnValue(false);
      }

      if (var2 == 0.0F) {
         ThreadModuleDump63.method4().method45().method10((Bridge5Extension_5)this, false);
      }
   }

   @Inject(
      method = "damageEntity$v1_8(Lnet/minecraft/util/DamageSource;F)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setHealth(F)V")
   )
   private void lunar$stopAnimatingOnDamage(DamageSource var1, float var2, CallbackInfo var3) {
      if (var2 > 0.0F) {
         ThreadModuleDump63.method4().method45().method10((Bridge5Extension_5)this, false);
      }
   }

   @Inject(method = "onCriticalHit(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$criticalStrikeEvent(Entity var1, CallbackInfo var2) {
      EventMeleeCriticalHit var3 = (EventMeleeCriticalHit)ClientEventBus.method29().method12(EventMeleeCriticalHit.class, () -> new EventMeleeCriticalHit((BridgeExtension)var1));
      if (var3 != null && var3.isCancelled()) {
         var2.cancel();
      }
   }

   @Inject(method = "onEnchantmentCritical(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$enchantCriticalStrikeEvent(Entity var1, CallbackInfo var2) {
      EventEnchantCriticalHit var3 = (EventEnchantCriticalHit)ClientEventBus.method29().method12(EventEnchantCriticalHit.class, () -> new EventEnchantCriticalHit((BridgeExtension)var1));
      if (var3 != null && var3.isCancelled()) {
         var2.cancel();
      }
   }

   @Inject(method = "onLivingUpdate()V", at = @At("HEAD"))
   private void lunar$livingUpdateEvent(CallbackInfo var1) {
      ClientEventBus.method29().method12(PluginChannelRegisterEvent.class, PluginChannelRegisterEvent::new);
      if (this.isDead && this.getUniqueID().equals(ThreadModuleDump63.method7().bridge$getUniqueID())) {
         ClientEventBus.method29().method12(EventLocalDeath.class, EventLocalDeath::new);
      }
   }

   @Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setSprinting(Z)V", ordinal = 0))
   private void lunar$onSprinting(EntityPlayerSP var1, boolean var2) {
      if (!var2 || !ThreadModuleDump63.method4().method40().method28().isEnabled() || ThreadModuleDump63.method4().method40().method28().method34().get()) {
         var1.setSprinting(var2);
      }
   }

   @Inject(method = "sendChatMessage$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$chatMessageEvents(String var1, CallbackInfo var2) {
      if (var1.startsWith("/")) {
         com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy var3 = (com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy)ClientEventBus.method29()
            .method12(
               com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy.class, () -> new com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy(var1)
            );
         if (var3 != null && var3.isCancelled()) {
            var2.cancel();
         }
      }
   }

   @WrapWithCondition(
      method = "onLivingUpdate()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V")
   )
   private boolean apollo$handleAntiPortalTraps(Minecraft var1, GuiScreen var2) {
      return ThreadModuleDump63.method4()
         .method84()
         .method3(ServerRuleModule.class)
         .filter(var0 -> (Boolean)var0.getOptions().get(ServerRuleModule.ANTI_PORTAL_TRAPS))
         .filter(var0 -> ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension612)
         .isEmpty();
   }

   @Annotation2(1)
   public Vec3 getLook(float var1) {
      return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
   }

   public void addStat(StatBase var1, int var2) {
      if (var1 != null) {
         this.statWriter.increaseStat(this, var1, var2);
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "dropItem", at = @At("HEAD"))
   private void lunar$dropItem$v1_12(boolean var1, CallbackInfoReturnable<EntityItem> var2) {
      ItemStack var3 = this.inventory.getCurrentItem();
      if (!var3.isEmpty()) {
         Item var4 = var3.getItem();
         if (var4 != Items.AIR$v1_12) {
            this.statWriter.increaseStat(this, StatList.getDroppedObjectStats$v1_12(var4), var1 ? var3.getCount$v1_12() : 1);
         }
      }

      this.statWriter.increaseStat(this, StatList.dropStat, 1);
   }

   @Inject(method = "isCurrentViewEntity$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindFixPlayerAnimations(CallbackInfoReturnable<Boolean> var1) {
      if (ThreadModuleDump63.method4().method40().method85().method19()) {
         var1.setReturnValue(true);
      }
   }

   @WrapOperation(method = "onLivingUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding_v1_8;isKeyDown()Z"))
   private boolean lunar$rewindIgnoreSprintKey(KeyBinding var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? false : (Boolean)var2.call(new Object[]{var1});
   }

   @Inject(method = {"dropOneItem$v1_8", "dropItem$v1_12(Z)Lnet/minecraft/entity/item/EntityItem;"}, at = @At("HEAD"))
   private void lunar$dropItemEvent(boolean var1, CallbackInfoReturnable<Boolean> var2) {
      ClientEventBus.method29().method12(RewindFrameEvent.class, () -> new RewindFrameEvent(var1));
   }
}
