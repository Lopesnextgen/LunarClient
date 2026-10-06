package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge6Extension;
import com.moonsworth.lunar.bridge.NetHandlerServerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.entity.EventPlayerDeath;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerMP.class)
public abstract class EntityPlayerMPMixin extends EntityPlayer implements Bridge6Extension {
   @Shadow
   public NetHandlerPlayServer connection;

   public EntityPlayerMPMixin(World world1, GameProfile gameprofile2) {
      super(world1, gameprofile2);
   }

   @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
   private void lunar$livingEntityDeathEvent(DamageSource source1, CallbackInfo callback2) {
      if (this.world.isRemote) {
         EventPlayerDeath highlightimpl_23 = (EventPlayerDeath)LunarEventBus.method29().method12(EventPlayerDeath.class, () -> new EventPlayerDeath(this));
         if (highlightimpl_23 != null && highlightimpl_23.isCancelled()) {
            callback2.cancel();
         }
      }
   }

   public void bridge$kick(String text1) {
      if (Ref.MC_VERSION <= 1) {
         this.connection.kickPlayerFromServer(text1);
      } else {
         this.connection.disconnect$v1_12((IChatComponent)(new ChatComponentText(text1)));
      }
   }

   public NetHandlerServerBridge bridge$getNetHandlerServer() {
      return (NetHandlerServerBridge)this.connection;
   }

   @VersionGate(min = 5)
   public ItemStackRenderStateBridge bridge$getOffHandItemRenderState() {
      return (ItemStackBridge)this.getHeldItemMainhand$v1_12();
   }

   public ItemStackRenderStateBridge bridge$getMainHandItemRenderState() {
      return Ref.MC_VERSION >= 5 ? (ItemStackBridge)this.getHeldItemMainhand$v1_12() : (ItemStackBridge)this.getHeldItem();
   }
}
