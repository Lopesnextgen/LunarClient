package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moonsworth.lunar.client.framework.feature.overlay.HudColorOverride;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiButton.class)
public class GuiButtonOverlayMixin {
   public GuiButtonOverlayMixin() {
   }

   @VersionGate(max = 1)
   @WrapMethod(method = "drawButton$v1_7")
   private void lunar$tintButton$v1_7(Minecraft minecraft1, int number2, int number3, Operation<Void> operation4) {
      HudColorOverride.method17(() -> operation4.call(new Object[]{minecraft1, number2, number3}));
   }

   @VersionGate(min = 5)
   @WrapMethod(method = "drawButton$v1_12")
   private void lunar$tintButton$v1_12(Minecraft minecraft1, int number2, int number3, float value, Operation<Void> operation5) {
      HudColorOverride.method17(() -> operation5.call(new Object[]{minecraft1, number2, number3, value}));
   }
}
