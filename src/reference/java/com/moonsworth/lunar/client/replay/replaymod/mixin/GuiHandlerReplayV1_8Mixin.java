package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.ui.mainmenu.MainMenuScreen;
import com.moonsworth.lunar.legacy.wrapper.GuiScreenImpl;
import com.replaymod.replay.handler.GuiHandler;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiHandler.class)
public class GuiHandlerReplayV1_8Mixin {
   public GuiHandlerReplayV1_8Mixin() {
   }

   @ModifyConstant(method = "injectIntoIngameMenu", constant = @Constant(stringValue = "replaymod.gui.exit"))
   public String ichor$modifyTranslation(String text) {
      return I18n.format(text, new Object[0]);
   }

   @Inject(method = "ensureReplayStopped", at = @At("HEAD"), cancellable = true)
   public void ichor$ensureReplayStopped(GuiScreen screen1, CallbackInfo callback2) {
      if ((!(screen1 instanceof GuiScreenImpl) || !(((GuiScreenImpl)screen1).method2() instanceof MainMenuScreen)) && !(screen1 instanceof GuiMultiplayer)) {
         callback2.cancel();
      }
   }
}
