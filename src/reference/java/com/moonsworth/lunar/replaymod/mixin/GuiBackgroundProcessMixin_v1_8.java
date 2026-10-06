package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.legacy.wrapper.GuiScreenImpl;
import com.replaymod.core.gui.GuiBackgroundProcesses;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiBackgroundProcesses.class)
public class GuiBackgroundProcessMixin_v1_8 {
   @Inject(method = "onGuiInit", at = @At("HEAD"), cancellable = true)
   public void ichor$onGuiInit(GuiScreen guiScreen, CallbackInfo callbackInfo) {
      if (guiScreen instanceof GuiScreenImpl
         && ((GuiScreenImpl)guiScreen).method2() instanceof LcuiScreen
         && !(((GuiScreenImpl)guiScreen).method2() instanceof com.moonsworth.lunar.client.ui.mainmenu.MainMenuScreen)) {
         callbackInfo.cancel();
      }
   }
}
