package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiButtonBridge;
import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiButton.class)
public abstract class GuiButtonMixin implements GuiButtonBridge {
   public GuiButtonMixin() {
   }
}
