package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiConfirmOpenLinkBridge;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiConfirmOpenLink.class)
public abstract class GuiConfirmOpenLinkMixin implements GuiConfirmOpenLinkBridge {
   public GuiConfirmOpenLinkMixin() {
   }
}
