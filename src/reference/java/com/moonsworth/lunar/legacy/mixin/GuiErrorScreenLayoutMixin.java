package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiErrorScreen.class)
public abstract class GuiErrorScreenLayoutMixin extends GuiScreen {
   @Final
   @Shadow
   public String message;
   @Unique
   private int lunar$titleY;
   @Unique
   private int lunar$messageY;

   public GuiErrorScreenLayoutMixin() {
   }

   @Inject(method = "initGui()V", at = @At("RETURN"))
   private void lunar$centerContent(CallbackInfo callback1) {
      if (this.lunar$hasChoice()) {
         List list2 = Ref.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
         int number3 = this.lunar$getLineCount() * 9;
         int number4 = 20 + number3 + 40;
         int number5 = (this.height - number4) / 2;
         this.lunar$titleY = number5;
         this.lunar$messageY = number5 + 20;
         int number6 = this.lunar$messageY + number3 + 20;

         for (GuiButton guibutton8 : list2) {
            guibutton8.yPosition = number6;
         }
      }
   }

   @ModifyConstant(method = "drawScreen", constant = @Constant(intValue = 90))
   private int lunar$replaceTitleY(int number1) {
      return this.lunar$hasChoice() ? this.lunar$titleY : number1;
   }

   @ModifyConstant(method = "drawScreen", constant = @Constant(intValue = 110))
   private int lunar$replaceMessageY(int number1) {
      return this.lunar$hasChoice() ? this.lunar$messageY : number1;
   }

   @Unique
   private boolean lunar$hasChoice() {
      return Ref.method4().method92().method3() != null;
   }

   @Unique
   private int lunar$getLineCount() {
      if (this.message != null) {
         int index1 = 1;

         for (char character5 : this.message.toCharArray()) {
            if (character5 == '\n') {
               index1++;
            }
         }

         return index1;
      } else {
         return 0;
      }
   }

   @WrapOperation(
      method = "drawScreen",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiErrorScreen;drawCenteredString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V"
      )
   )
   private void lunar$wrapDrawCenteredString$v1_7(GuiErrorScreen guierrorscreen1, FontRenderer font2, String text3, int number4, int number5, int number6, Operation<Void> operation7) {
      if (text3 != null && text3.contains("\n")) {
         String[] items8 = text3.split("\n");
         int number9 = number5;

         for (String text13 : items8) {
            this.drawCenteredString(font2, text13, number4, number9, number6);
            number9 += font2.FONT_HEIGHT;
         }
      } else {
         operation7.call(new Object[]{guierrorscreen1, font2, text3, number4, number5, number6});
      }
   }
}
