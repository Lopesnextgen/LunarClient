package com.moonsworth.lunar.client.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiRecipeBook.class)
public class GuiRecipeBookMixin extends Gui {
   @Shadow
   public int xOffset;

   @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/recipebook/GuiRecipeBook;drawTexturedModalRect(IIIIII)V"))
   private void lunar$tintRecipeBook(GuiRecipeBook var1, int var2, int var3, int var4, int var5, int var6, int var7, Operation<Void> var8) {
      OverlayMod var9 = ThreadModuleDump63.method4().method40().method84();
      if (!var9.isContainerTintEnabled()) {
         var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
      } else {
         Gui2.method1(var9.getContainerTint());

         try {
            var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
         } finally {
            Gui2.method2();
         }
      }
   }

   @Inject(
      method = "initVisuals",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/recipebook/GuiRecipeBook;xOffset:I", shift = Shift.AFTER, ordinal = 0)
   )
   private void lunar$keepInventoryCentered(CallbackInfo var1) {
      boolean var2 = ThreadModuleDump63.method4().method41().method6().method23().get();
      if (var2) {
         this.xOffset = 163;
      }
   }

   @Inject(method = "isOffsetNextToMainGUI", at = @At("HEAD"), cancellable = true)
   private void lunar$keepInventoryOpen(CallbackInfoReturnable<Boolean> var1) {
      if (this.xOffset == 163) {
         var1.setReturnValue(true);
      }
   }
}
