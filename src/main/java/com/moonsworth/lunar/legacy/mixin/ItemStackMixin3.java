package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public class ItemStackMixin3 {
   @WrapWithCondition(
      method = "updateAnimation",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/item/Item;onUpdate(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;IZ)V"
      )
   )
   private boolean lunar$checkIfItemIsNull(Item item, ItemStack itemStack, World world, Entity entity, int value, boolean flag) {
      return item != null;
   }
}
