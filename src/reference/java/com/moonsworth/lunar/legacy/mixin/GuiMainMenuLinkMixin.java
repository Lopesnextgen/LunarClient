package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.client.framework.feature.overlay.HudColorOverride;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import java.net.MalformedURLException;
import java.net.URL;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuLinkMixin {
   @Shadow
   public String openGLWarningLink;

   public GuiMainMenuLinkMixin() {
   }

   @Inject(method = "confirmClicked", at = @At("HEAD"), cancellable = true)
   private void lunar$redirectOpenUrl(boolean flag, int value, CallbackInfo callback3) {
      if (flag && value == 13) {
         try {
            URL url4 = new URL(this.openGLWarningLink);
            if (BrowserUtils.method9(url4, Initiator.INITIATOR_LINK_CONFIRM)) {
               callback3.cancel();
            }
         } catch (MalformedURLException malformedurlexception5) {
         }
      }
   }

   @WrapOperation(
      method = "drawScreen",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiMainMenu;drawCenteredString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V"
      )
   )
   private void lunar$keepSplashColor(GuiMainMenu guimainmenu1, FontRenderer font2, String text, int value, int value2, int value3, Operation<Void> operation7) {
      HudColorOverride.method11();

      try {
         operation7.call(new Object[]{guimainmenu1, font2, text, value, value2, value3});
      } finally {
         HudColorOverride.method12();
      }
   }
}
