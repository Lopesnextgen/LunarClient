package com.moonsworth.lunar.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.GuiButtonRecipe;
import net.minecraft.client.gui.recipebook.GuiButtonRecipeTab;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({GuiButtonRecipe.class, GuiButtonRecipeTab.class})
public class GuiButtonRecipeMixin {
   @WrapMethod(method = "drawButton")
   private void lunar$tintRecipeSlot(Minecraft var1, int var2, int var3, float var4, Operation<Void> var5) {
      OverlayMod var6 = ThreadModuleDump63.method4().method40().method84();
      if (!var6.isContainerTintEnabled()) {
         var5.call(new Object[]{var1, var2, var3, var4});
      } else {
         Gui2.method1(var6.getContainerTint());

         try {
            var5.call(new Object[]{var1, var2, var3, var4});
         } finally {
            Gui2.method2();
         }
      }
   }
}
