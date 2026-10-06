package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiOptionsBridge;
import net.minecraft.client.gui.GuiOptions;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiOptions.class)
public abstract class GuiOptionsMixin implements GuiOptionsBridge {
   public GuiOptionsMixin() {
   }
}
