package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiSelectWorldBridge;
import net.minecraft.client.gui.GuiSelectWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiSelectWorld.class)
public abstract class GuiSelectWorldMixin implements GuiSelectWorldBridge {
   public GuiSelectWorldMixin() {
   }
}
