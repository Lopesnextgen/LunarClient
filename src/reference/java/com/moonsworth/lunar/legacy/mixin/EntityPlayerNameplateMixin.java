package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.player.EventItemUseFinish;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerNameplateMixin extends Entity {
   @VersionGate(max = 1)
   @Shadow
   public ItemStack itemInUse;
   @VersionGate(max = 1)
   @Shadow
   public InventoryPlayer inventory;

   public EntityPlayerNameplateMixin(World world1) {
      super(world1);
   }

   @VersionGate(max = 1)
   @Inject(method = "onItemUseFinish$v1_7", at = @At("HEAD"))
   private void lunar$finishUsingItemEvent(CallbackInfo callback1) {
      if (this.world.isRemote && this instanceof Bridge6_10 bridge6_102) {
         LunarEventBus.method29().method12(EventItemUseFinish.class, () -> new EventItemUseFinish(bridge6_102, (ItemStackBridge)this.itemInUse));
      }
   }

   @VersionGate(min = 1)
   @Inject(
      method = "handleStatusUpdate$v1_8",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;hasReducedDebug$v1_8:Z", ordinal = 1, opcode = 181)
   )
   private void lunar$captureDebugStatusChange(byte number1, CallbackInfo callback2) {
      ReducedDebugInfoNotifier.method2();
   }

   @VersionGate(min = 1)
   @Inject(method = "setReducedDebug$v1_8", at = @At("TAIL"))
   private void lunar$injectDebugInfoPopup$v1_8(boolean flag1, CallbackInfo callback2) {
      ReducedDebugInfoNotifier.method2();
   }
}
