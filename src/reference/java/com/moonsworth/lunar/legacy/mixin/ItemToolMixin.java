package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ToolMaterialBridge;
import com.moonsworth.lunar.legacy.ToolMaterialHolder;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemTool.class)
abstract class ItemToolMixin implements ToolMaterialHolder {
   @Shadow
   public ToolMaterial toolMaterial;

   ItemToolMixin() {
   }

   @Override
   public ToolMaterialBridge lunar$getMaterial() {
      return (ToolMaterialBridge)this.toolMaterial;
   }
}
