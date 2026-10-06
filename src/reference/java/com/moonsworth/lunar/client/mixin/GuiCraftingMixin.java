package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiCrafting.class)
public abstract class GuiCraftingMixin {
   @Shadow
   public boolean widthTooNarrow;

   @Redirect(method = "initGui", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/recipebook/GuiRecipeBook;updateScreenPosition(ZII)I"))
   private int lunar$onUpdateScreenPos(GuiRecipeBook var1, boolean var2, int var3, int var4) {
      return this.lunar$onUpdateScreenPos(var1, var3, var4);
   }

   @Redirect(method = "actionPerformed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/recipebook/GuiRecipeBook;updateScreenPosition(ZII)I"))
   private int lunar$onUpdateScreenPos$actionPerformed(GuiRecipeBook var1, boolean var2, int var3, int var4) {
      return this.lunar$onUpdateScreenPos(var1, var3, var4);
   }

   @Unique
   private int lunar$onUpdateScreenPos(GuiRecipeBook var1, int var2, int var3) {
      boolean var4 = ThreadModuleDump63.method4().method41().method6().method23().get();
      int var5;
      if (!var1.isVisible() || this.widthTooNarrow) {
         var5 = (var2 - var3) / 2;
      } else if (!var4) {
         var5 = 177 + (var2 - var3 - 200) / 2;
      } else {
         var5 = (var2 - var3) / 2;
      }

      return var5;
   }
}
