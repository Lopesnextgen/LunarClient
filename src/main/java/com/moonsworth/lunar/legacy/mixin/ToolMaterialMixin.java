package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ToolMaterialBridge;
import net.minecraft.item.Item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ToolMaterial.class)
public abstract class ToolMaterialMixin implements ToolMaterialBridge {
   public ToolMaterialMixin() {
   }
}
