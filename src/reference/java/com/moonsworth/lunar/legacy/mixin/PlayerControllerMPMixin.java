package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_25;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public abstract class PlayerControllerMPMixin implements Bridge2_25 {
   @Shadow
   public int blockHitDelay;
   @Final
   @Shadow
   public Minecraft mc;
   @Shadow
   public boolean isHittingBlock;
   @Shadow
   public float curBlockDamageMP;
   @Shadow
   public BlockPos currentBlock;
   @Shadow
   public int currentBlockX$v1_7;
   @Shadow
   public int currentBlockY$v1_7;
   @Shadow
   public int currentblockZ$v1_7;

   @Shadow
   public abstract boolean isSpectator();

   @Shadow
   public abstract boolean isSpectatorMode();

   @Shadow
   public abstract EnumActionResult processRightClick$v1_12(EntityPlayer var1, World var2, EnumHand var3);

   @Shadow
   public abstract EnumActionResult processRightClickBlock$v1_12(
      EntityPlayerSP var1, WorldClient var2, BlockPos var3, EnumFacing var4, Vec3 var5, EnumHand var6
   );

   @Shadow
   public abstract boolean onPlayerRightClick(EntityPlayer var1, World var2, ItemStack var3, int var4, int var5, int var6, int var7, Vec3 var8);

   @Shadow
   public abstract boolean onPlayerRightClick(EntityPlayerSP var1, WorldClient var2, ItemStack var3, BlockPos var4, EnumFacing var5, Vec3 var6);

   @Shadow
   public abstract boolean sendUseItem(EntityPlayer var1, World var2, ItemStack var3);

   @Shadow
   public abstract void attackEntity(EntityPlayer var1, Entity var2);

   @Override
   public boolean bridge$isSpectator() {
      return ThreadModuleDump63.MC_VERSION >= 1 && this.isSpectator();
   }

   @Annotation2(min = 5)
   @Inject(method = "windowClick$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$windowClick(int var1, int var2, int var3, ClickType var4, EntityPlayer var5, CallbackInfoReturnable<ItemStack> var6) {
      if (var5.openContainer != null && var2 >= var5.openContainer.inventorySlots.size()) {
         var6.cancel();
      }
   }

   @Annotation2(max = 1)
   @Inject(method = "windowClick$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$windowClick(int var1, int var2, int var3, int var4, EntityPlayer var5, CallbackInfoReturnable<ItemStack> var6) {
      if (var5.openContainer != null
         && var2 >= (ThreadModuleDump63.MC_VERSION >= 1 ? var5.openContainer.inventorySlots.size() : var5.openContainer.inventorySlots$v1_7.size())) {
         var6.cancel();
      }
   }

   @Override
   public int bridge$destroyDelay() {
      return this.blockHitDelay;
   }

   @Override
   public void bridge$attack() {
      Minecraft var1 = Minecraft.getMinecraft();
      if (var1.pointedEntity != null) {
         if (ThreadModuleDump63.MC_VERSION <= 0) {
            this.attackEntity(var1.thePlayer$v1_7, var1.pointedEntity);
         } else {
            this.attackEntity(var1.thePlayer, var1.pointedEntity);
         }
      }
   }

   @Override
   public void bridge$useItemOn(Vector3i var1, int var2, int var3, Vector3d var4, boolean var5, boolean var6) {
      Minecraft var7 = Minecraft.getMinecraft();
      boolean var10 = true;
      ItemStack var8;
      int var9;
      if (ThreadModuleDump63.MC_VERSION == 0) {
         var8 = var7.thePlayer$v1_7.getCurrentEquippedItem();
         var9 = var8 != null ? var8.stackSize : 0;
         this.onPlayerRightClick(var7.thePlayer$v1_7, var7.theWorld, var8, var1.x(), var1.y(), var1.z(), var3, new Vec3(var4.x, var4.y, var4.z));
      } else if (ThreadModuleDump63.MC_VERSION == 1) {
         var8 = var7.thePlayer.getCurrentEquippedItem();
         var9 = var8 != null ? var8.stackSize : 0;
         this.onPlayerRightClick(
            var7.thePlayer, var7.theWorld, var8, new BlockPos(var1.x(), var1.y(), var1.z()), EnumFacing.values()[var3], new Vec3(var4.x, var4.y, var4.z)
         );
      } else {
         var8 = var7.thePlayer.getHeldItem(EnumHand.values()[var2]);
         var9 = var8.getCount$v1_12();
         var10 = this.processRightClickBlock$v1_12(
               var7.thePlayer,
               var7.theWorld,
               new BlockPos(var1.x(), var1.y(), var1.z()),
               EnumFacing.values()[var3],
               new Vec3(var4.x, var4.y, var4.z),
               EnumHand.values()[var2]
            )
            == EnumActionResult.SUCCESS;
      }

      if (var10 && var8 != null && (ThreadModuleDump63.MC_VERSION != 5 || !var8.isEmpty())) {
         if (var8.stackSize == 0 && ThreadModuleDump63.MC_VERSION <= 1) {
            if (ThreadModuleDump63.MC_VERSION <= 0) {
               var7.thePlayer$v1_7.inventory.mainInventory[var7.thePlayer$v1_7.inventory.currentItem] = null;
            } else {
               var7.thePlayer.inventory.mainInventory[var7.thePlayer.inventory.currentItem] = null;
            }
         } else if (var8.stackSize != var9 || var7.playerController.isInCreativeMode()) {
            if (ThreadModuleDump63.MC_VERSION <= 1) {
               var7.entityRenderer.field_177083_e.resetEquippedProgress();
            } else {
               var7.entityRenderer.field_177083_e.resetEquippedProgress(EnumHand.values()[var2]);
            }
         }
      }
   }

   @Override
   public void bridge$useItem(int var1) {
      Minecraft var2 = Minecraft.getMinecraft();
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         Object var3;
         if (ThreadModuleDump63.MC_VERSION == 0) {
            var3 = var2.thePlayer$v1_7;
         } else {
            var3 = var2.thePlayer;
         }

         this.sendUseItem((EntityPlayer)var3, var2.theWorld, var3.getCurrentEquippedItem());
      } else {
         this.processRightClick$v1_12(var2.thePlayer, var2.theWorld, EnumHand.values()[var1]);
      }
   }

   @Override
   public Horsestats20Extension2 bridge$getBlockBeingDestroyed() {
      return ThreadModuleDump63.MC_VERSION <= 0
         ? (Horsestats20Extension2)(new Vector3i(this.currentBlockX$v1_7, this.currentBlockY$v1_7, this.currentblockZ$v1_7))
         : (Horsestats20Extension2)this.currentBlock;
   }

   @Override
   public float bridge$getBlockDestroyProgress() {
      return this.curBlockDamageMP;
   }

   @Override
   public boolean bridge$isHittingBlock() {
      return this.isHittingBlock;
   }
}
