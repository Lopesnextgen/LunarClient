package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.legacy.wrapper.GuiScreenImpl;
import com.replaymod.core.gui.GuiBackgroundProcesses;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiBackgroundProcesses.class)
public class GuiBackgroundProcessesV1_8Mixin {
   public GuiBackgroundProcessesV1_8Mixin() {
   }

   @Inject(method = "onGuiInit", at = @At("HEAD"), cancellable = true)
   public void ichor$onGuiInit(GuiScreen screen1, CallbackInfo callback2) {
      if (screen1 instanceof GuiScreenImpl
         && ((GuiScreenImpl)screen1).method2() instanceof LcuiScreen
         && !(((GuiScreenImpl)screen1).method2() instanceof com.moonsworth.lunar.client.ui.mainmenu.MainMenuScreen)) {
         callback2.cancel();
      }
   }
}
