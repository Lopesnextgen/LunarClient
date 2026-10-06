package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiChestBridge;
import net.minecraft.client.gui.inventory.GuiChest;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiChest.class)
public abstract class GuiChestMixin implements GuiChestBridge {
   public GuiChestMixin() {
   }
}
