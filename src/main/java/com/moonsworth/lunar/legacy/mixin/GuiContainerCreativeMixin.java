package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiContainerCreativeBridge;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiContainerCreative.class)
public class GuiContainerCreativeMixin implements GuiContainerCreativeBridge {
   @Shadow
   public static int selectedTabIndex;

   public GuiContainerCreativeMixin() {
   }

   public boolean bridge$isInventory() {
      return selectedTabIndex == 11;
   }
}
