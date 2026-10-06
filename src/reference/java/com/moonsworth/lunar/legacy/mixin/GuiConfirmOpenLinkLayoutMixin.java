package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(max = 0)
@Mixin(GuiConfirmOpenLink.class)
public abstract class GuiConfirmOpenLinkLayoutMixin extends GuiScreen {
   public GuiConfirmOpenLinkLayoutMixin() {
   }

   @VersionGate(max = 0)
   @Inject(method = "initGui", at = @At("RETURN"))
   private void lunar$centerButtons(CallbackInfo callback1) {
      List list2 = this.buttonList$v1_7;
      int number3 = this.width / 2 - 50;
      int number4 = this.height / 6 + 96;
      ((GuiButton)list2.get(0)).xPosition = number3 - 105;
      ((GuiButton)list2.get(0)).yPosition = number4;
      ((GuiButton)list2.get(1)).xPosition = number3;
      ((GuiButton)list2.get(1)).yPosition = number4;
      ((GuiButton)list2.get(2)).xPosition = number3 + 105;
      ((GuiButton)list2.get(2)).yPosition = number4;
   }
}
