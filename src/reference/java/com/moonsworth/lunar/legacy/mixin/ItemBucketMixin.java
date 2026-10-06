package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventItemEntity;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBucket.class)
public abstract class ItemBucketMixin {
   @Annotation2(min = 5)
   @Inject(
      method = "onItemRightClick$v1_12",
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/util/math/RayTraceResult;typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;",
         opcode = 180,
         shift = Shift.BEFORE
      ),
      cancellable = true
   )
   private void lunar$onItemRightClick$v1_12(World var1, EntityPlayer var2, EnumHand var3, CallbackInfoReturnable<ActionResult<ItemStack>> var4) {
      if (var1.isRemote) {
         EventItemEntity var5 = ClientEventBus.method29()
            .method12(EventItemEntity.class, () -> new EventItemEntity((ItemStackBridge)var2.getHeldItem(var3), (Bridge6_10)var2));
         if (var5 != null && var5.isCancelled()) {
            var4.setReturnValue(new ActionResult(EnumActionResult.FAIL, (ItemStack)var5.field1));
         }
      }
   }

   @Annotation2(max = 1)
   @Inject(
      method = "onItemRightClick$v1_7",
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/util/math/RayTraceResult;typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;",
         opcode = 180,
         shift = Shift.BEFORE
      ),
      cancellable = true
   )
   private void lunar$onItemRightClick$v1_7(ItemStack var1, World var2, EntityPlayer var3, CallbackInfoReturnable<ItemStack> var4) {
      if (var2.isRemote) {
         EventItemEntity var5 = ClientEventBus.method29().method12(EventItemEntity.class, () -> new EventItemEntity((ItemStackBridge)var1, (Bridge6_10)var3));
         if (var5 != null && var5.isCancelled()) {
            var4.setReturnValue((ItemStack)var5.field1);
         }
      }
   }
}
