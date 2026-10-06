package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityItemFrameBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityItemFrame.class)
public abstract class EntityItemFrameMixin extends EntityHanging implements EntityItemFrameBridge {
   public EntityItemFrameMixin() {
   }

   @Shadow
   public abstract ItemStack getDisplayedItem();

   @Shadow
   public abstract int getRotation();

   @Override
   public ItemStackBridge bridge$getItemStack() {
      return (ItemStackBridge)this.getDisplayedItem();
   }

   @Override
   public int bridge$getRotation() {
      return this.getRotation();
   }

   @Override
   public PacketBridge bridge$getMapPacket(Itemcounter6 itemcounter61, Bridge6_10 bridge6_102) {
      ItemStack stack3 = this.getDisplayedItem();
      if (stack3 != null && stack3.getItem() instanceof ItemMap itemmap4) {
         MapData mapdata6 = itemmap4.getMapData(stack3, (World)itemcounter61);
         return mapdata6 == null ? null : ((MapDataBridge)mapdata6).bridge$getMapPacket(stack3.getMetadata());
      } else {
         return null;
      }
   }

   @Override
   public Vec3iBridge bridge$getHangingPosition() {
      if (Ref.MC_VERSION >= 1) {
         return (Vec3iBridge)this.hangingPosition;
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }
}
