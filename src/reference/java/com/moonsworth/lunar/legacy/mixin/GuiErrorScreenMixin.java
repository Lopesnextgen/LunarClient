package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiErrorScreenBridge;
import net.minecraft.client.gui.GuiErrorScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiErrorScreen.class)
public abstract class GuiErrorScreenMixin implements GuiErrorScreenBridge {
   public GuiErrorScreenMixin() {
   }
}
