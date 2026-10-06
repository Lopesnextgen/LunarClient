package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_15;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin implements Bridge4_15 {
   @Annotation2(min = 5)
   @Shadow
   public GuiRecipeBook recipeBookGui$v1_12;
   @Annotation2(min = 5)
   @Shadow
   public boolean widthTooNarrow$v1_12;

   @Override
   public boolean bridge$isRecipeBookVisible() {
      return ThreadModuleDump63.MC_VERSION >= 5 && this.recipeBookGui$v1_12.isVisible();
   }

   @Override
   public boolean bridge$isWidthTooNarrow() {
      return ThreadModuleDump63.MC_VERSION >= 5 && this.widthTooNarrow$v1_12;
   }
}
