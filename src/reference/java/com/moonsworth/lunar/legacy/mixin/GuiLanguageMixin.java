package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiLanguageBridge;
import net.minecraft.client.gui.GuiLanguage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiLanguage.class)
public abstract class GuiLanguageMixin implements GuiLanguageBridge {
   public GuiLanguageMixin() {
   }
}
