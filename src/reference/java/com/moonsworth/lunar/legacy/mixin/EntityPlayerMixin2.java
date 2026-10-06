package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.player.EventUseItemFinish;
import com.moonsworth.lunar.ichor.Annotation2;
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
public abstract class EntityPlayerMixin2 extends Entity {
   @Annotation2(max = 1)
   @Shadow
   public ItemStack itemInUse;
   @Annotation2(max = 1)
   @Shadow
   public InventoryPlayer inventory;

   public EntityPlayerMixin2(World var1) {
      super(var1);
   }

   @Annotation2(max = 1)
   @Inject(method = "onItemUseFinish$v1_7", at = @At("HEAD"))
   private void lunar$finishUsingItemEvent(CallbackInfo var1) {
      if (this.world.isRemote && this instanceof Bridge6_10 var2) {
         ClientEventBus.method29().method12(EventUseItemFinish.class, () -> new EventUseItemFinish(var2, (ItemStackBridge)this.itemInUse));
      }
   }

   @Annotation2(min = 1)
   @Inject(
      method = "handleStatusUpdate$v1_8",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;hasReducedDebug$v1_8:Z", ordinal = 1, opcode = 181)
   )
   private void lunar$captureDebugStatusChange(byte var1, CallbackInfo var2) {
      ReducedDebugInfoNotifier.method2();
   }

   @Annotation2(min = 1)
   @Inject(method = "setReducedDebug$v1_8", at = @At("TAIL"))
   private void lunar$injectDebugInfoPopup$v1_8(boolean var1, CallbackInfo var2) {
      ReducedDebugInfoNotifier.method2();
   }
}
