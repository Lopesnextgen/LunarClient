package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.GuiMainMenuBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuBackground;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public abstract class GuiMainMenuMixin implements GuiMainMenuBridge {
   public GuiMainMenuMixin() {
   }

   @WrapOperation(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;renderSkybox(IIF)V"))
   private void lunar$swapVanillaPanorama(GuiMainMenu guimainmenu1, int number2, int number3, float value4, Operation<Void> operation5) {
      if (this.lunar$shouldUseLunarPanorama()) {
         GuiResolution threadmoduledump716 = LcuiScreen.method151();
         int number7 = threadmoduledump716 == null ? Ref.method3().bridge$displayWidth() : threadmoduledump716.getScaledWidth();
         int number8 = threadmoduledump716 == null ? Ref.method3().bridge$displayHeight() : threadmoduledump716.getScaledHeight();
         MainMenuBackground.method3(AbstractRenderContext.method9(value4), number7, number8, value4);
      } else {
         operation5.call(new Object[]{guimainmenu1, number2, number3, value4});
      }
   }

   @Inject(method = "updateScreen", at = @At("HEAD"))
   private void lunar$tickLunarPanorama(CallbackInfo callback1) {
      if (this.lunar$shouldUseLunarPanorama()) {
         MainMenuBackground.method9().method4();
      }
   }

   @Unique
   private boolean lunar$shouldUseLunarPanorama() {
      return Ref.method4() != null
         && Ref.method4().method25()
         && MainMenuThemeManager.method12() != null
         && !MainMenuThemeManager.method12().method5();
   }
}
