package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.player.ItemPickupEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin implements ItemEntityBridge {
   @Shadow
   public float hoverStart;

   @Shadow
   public abstract ItemStack getItem();

   @Shadow
   public abstract ItemStack getEntityItem();

   public ItemStackBridge bridge$getItemState() {
      return (ItemStackBridge)(ThreadModuleDump63.MC_VERSION >= 5 ? this.getItem() : this.getEntityItem());
   }

   @Override
   public int bridge$renderCount() {
      int var1 = this.bridge$getItemStack().bridge$getStackSize();
      if (var1 <= 1) {
         return 1;
      } else if (var1 <= 16) {
         return 2;
      } else if (var1 <= 32) {
         return 3;
      } else {
         return var1 <= 48 ? 4 : 5;
      }
   }

   @Annotation2(min = 5)
   @Override
   public int bridge$renderSeed() {
      return Bridge.method28().method23((Bridge6_4)this.getItem().item) + this.bridge$getItemState().bridge$getItemDamage();
   }

   @Override
   public ItemStackBridge bridge$getItemStack() {
      return this.bridge$getItemState();
   }

   @Override
   public BakedModelExtension bridge$getBakedModel() {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         throw new AbstractMethodErrorImpl("Getting Baked Model not supported on 1.7");
      }

      Bridge5_19 var1 = Bridge.method9().bridge$getRenderItem();
      return var1.bridge$getItemModelShaper().bridge$getItemModel(this.bridge$getItemState());
   }

   @Override
   public float bridge$getBobOffset() {
      return this.hoverStart;
   }

   @Inject(
      method = "onCollideWithPlayer",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;onItemPickup(Lnet/minecraft/entity/Entity;I)V")
   )
   private void lunar$itemPickupEvent(EntityPlayer var1, CallbackInfo var2) {
      if (this.bridge$getWorld().bridge$isRemote()) {
         ClientEventBus.method29().method12(ItemPickupEvent.class, () -> new ItemPickupEvent((Bridge6_10)var1, this));
      }
   }
}
