package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin extends InventoryEffectRenderer {
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
      boolean var5 = !this.mc.player.getActivePotionEffects().isEmpty()
         && (!ThreadModuleDump63.method4().method40().method23().isEnabled() || ThreadModuleDump63.method4().method40().method23().method19().get());
      int var6;
      if (var1.isVisible() && !this.widthTooNarrow) {
         if (!var4) {
            var6 = 177 + (var2 - var3 - 200) / 2;
         } else {
            var6 = (var2 - var3) / 2;
         }
      } else if (!var4 && var5) {
         var6 = 160 + (var2 - var3 - 200) / 2;
      } else {
         var6 = (var2 - var3) / 2;
      }

      return var6;
   }
}
