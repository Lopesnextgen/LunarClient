package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
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
public class GuiMainMenuMixin2 {
   @Shadow
   public String openGLWarningLink;

   @Inject(method = "confirmClicked", at = @At("HEAD"), cancellable = true)
   private void lunar$redirectOpenUrl(boolean var1, int var2, CallbackInfo var3) {
      if (var1 && var2 == 13) {
         try {
            URL var4 = new URL(this.openGLWarningLink);
            if (ThreadModuleDump61.method9(var4, Initiator.INITIATOR_LINK_CONFIRM)) {
               var3.cancel();
            }
         } catch (MalformedURLException var5) {
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
   private void lunar$keepSplashColor(GuiMainMenu var1, FontRenderer var2, String var3, int var4, int var5, int value, Operation<Void> operation) {
      Gui2.method11();

      try {
         operation.call(new Object[]{var1, var2, var3, var4, var5, value});
      } finally {
         Gui2.method12();
      }
   }
}
