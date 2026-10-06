package com.moonsworth.lunar.mixin.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.combat.EventEnchantmentCriticalHit;
import com.moonsworth.lunar.client.event.player.EventLocalPlayerDeath;
import com.moonsworth.lunar.client.event.combat.EventCriticalHit;
import com.moonsworth.lunar.client.event.player.EventPlayerLivingUpdate;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin extends AbstractClientPlayer {
   public EntityPlayerSPMixin(World world1, GameProfile gameprofile2) {
      super(world1, gameprofile2);
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

   @Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setSprinting(Z)V", ordinal = 1))
   private void lunar$onSprinting(EntityPlayerSP player1, boolean flag) {
      if (!flag
         || !Ref.method4().method40().method28().isEnabled()
         || (Boolean)Ref.method4().method40().method28().method34().get()) {
         player1.setSprinting(flag);
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
}
