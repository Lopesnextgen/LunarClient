package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.ui.prompt.PromptAction;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.GuiButtonImpl;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiYesNo.class)
public abstract class GuiYesNoChoiceMixin extends GuiScreen {
   @Shadow
   public GuiYesNoCallback parentScreen;
   @Final
   @Shadow
   public List<String> field_175298_s;
   @Final
   @Shadow
   public String messageLine2;
   @Unique
   private GuiButtonImpl lunar$saveChoiceCheckbox;
   @Unique
   private GuiButtonImpl lunar$saveAllChoiceCheckbox;
   @Unique
   private PromptAction lunar$choice;
   @Unique
   private int lunar$titleY;
   @Unique
   private int lunar$listY;

   public GuiYesNoChoiceMixin() {
   }

   @Inject(method = "<init>(Lnet/minecraft/client/gui/GuiYesNoCallback;Ljava/lang/String;Ljava/lang/String;I)V", at = @At("TAIL"))
   private void lunar$insertCheckboxCallback$1(GuiYesNoCallback guiyesnocallback1, String text2, String text3, int number4, CallbackInfo callback5) {
      this.lunar$insertCheckboxCallback(guiyesnocallback1);
   }

   @Inject(
      method = "<init>(Lnet/minecraft/client/gui/GuiYesNoCallback;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V",
      at = @At("TAIL")
   )
   private void lunar$insertCheckboxCallback$2(GuiYesNoCallback guiyesnocallback1, String text2, String text3, String text4, String text5, int number6, CallbackInfo callback7) {
      this.lunar$insertCheckboxCallback(guiyesnocallback1);
   }

   @Inject(method = "actionPerformed(Lnet/minecraft/client/gui/GuiButton;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$preventCheckboxFromClosing(GuiButton guibutton1, CallbackInfo callback2) {
      if (guibutton1 == this.lunar$saveChoiceCheckbox || guibutton1 == this.lunar$saveAllChoiceCheckbox) {
         callback2.cancel();
      }
   }

   @Inject(method = "initGui()V", at = @At("RETURN"))
   private void lunar$addCheckboxAndCenter(CallbackInfo callback1) {
      Component component2 = this.lunar$getCheckboxText();
      if (component2 != null) {
         List list3 = Ref.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
         String text4 = TextBridge.asLegacyString(component2);
         int number5 = (int)(Ref.method10().bridge$getStringWidth(text4) + 24.0F);
         int number6 = this.width / 2 - number5 / 2;
         boolean flag7 = this.lunar$saveChoiceCheckbox != null && this.lunar$saveChoiceCheckbox.isSelected();
         this.lunar$saveChoiceCheckbox = new GuiButtonImpl(2, number6, 0, text4, flag7);
         list3.add(this.lunar$saveChoiceCheckbox);
         Component component8 = this.lunar$getSecondaryCheckboxText();
         if (component8 != null) {
            String text9 = TextBridge.asLegacyString(component8);
            int number10 = (int)(Ref.method10().bridge$getStringWidth(text9) + 24.0F);
            int number11 = this.width / 2 - number10 / 2;
            boolean flag12 = this.lunar$saveAllChoiceCheckbox != null && this.lunar$saveAllChoiceCheckbox.isSelected();
            this.lunar$saveAllChoiceCheckbox = new GuiButtonImpl(3, number11, 0, text9, flag12);
            list3.add(this.lunar$saveAllChoiceCheckbox);
         }

         this.lunar$layoutPrompt();
      }
   }

   @Unique
   private void lunar$layoutPrompt() {
      List list1 = Ref.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
      boolean flag2 = this.lunar$saveAllChoiceCheckbox != null;
      int number3 = this.lunar$getLineCount() * 9;
      int number4 = 20 + number3 + 40 + 28 + (flag2 ? 24 : 0);
      int number5 = Math.max(2, (this.height - number4) / 2);
      this.lunar$titleY = number5;
      this.lunar$listY = number5 + 20;
      int number6 = this.lunar$listY + number3 + 20;

      for (GuiButton guibutton8 : list1) {
         if (guibutton8 != this.lunar$saveChoiceCheckbox && guibutton8 != this.lunar$saveAllChoiceCheckbox) {
            guibutton8.yPosition = number6;
         }
      }

      int number9 = number6 + 28;
      this.lunar$saveChoiceCheckbox.y = number9;
      if (this.lunar$saveAllChoiceCheckbox != null) {
         this.lunar$saveAllChoiceCheckbox.y = number9 + 24;
      }
   }

   @Unique
   private void lunar$insertCheckboxCallback(GuiYesNoCallback guiyesnocallback1) {
      PromptAction animations2 = Ref.method4().method92().method3();
      if (animations2 != null) {
         this.lunar$choice = animations2;
         this.parentScreen = (arg3, arg4) -> {
            guiyesnocallback1.confirmClicked(arg3, arg4);
            if (this.lunar$saveChoiceCheckbox != null && this.lunar$saveChoiceCheckbox.isSelected()) {
               animations2.method1(arg3);
            }

            if (this.lunar$saveAllChoiceCheckbox != null && this.lunar$saveAllChoiceCheckbox.isSelected()) {
               animations2.method4(arg3);
            }
         };
      }
   }

   @Unique
   private Component lunar$getCheckboxText() {
      PromptAction animations1 = Ref.method4().method92().method3();
      return animations1 != null ? animations1.method2() : null;
   }

   @Unique
   private Component lunar$getSecondaryCheckboxText() {
      PromptAction animations1 = Ref.method4().method92().method3();
      return animations1 != null ? animations1.method3() : null;
   }

   @Unique
   private boolean lunar$hasChoice() {
      return Ref.method4().method92().method3() != null;
   }

   @ModifyConstant(method = "drawScreen", constant = @Constant(intValue = 70))
   private int lunar$replaceTitleY(int number1) {
      return this.lunar$hasChoice() ? this.lunar$titleY : number1;
   }

   @ModifyConstant(method = "drawScreen", constant = @Constant(intValue = 90))
   private int lunar$replaceListY(int number1) {
      return this.lunar$hasChoice() ? this.lunar$listY : number1;
   }

   @Unique
   private int lunar$getLineCount() {
      if (Ref.MC_VERSION >= 1) {
         return this.field_175298_s != null ? this.field_175298_s.size() : 0;
      }

      if (this.messageLine2 != null) {
         int index1 = 1;

         for (char character5 : this.messageLine2.toCharArray()) {
            if (character5 == '\n') {
               index1++;
            }
         }

         return index1;
      } else {
         return 0;
      }
   }

   @VersionGate(max = 0)
   @WrapOperation(
      method = "drawScreen",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiYesNo;drawCenteredString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V"
      )
   )
   private void lunar$wrapDrawCenteredString$v1_7(GuiYesNo guiyesno1, FontRenderer font2, String text3, int number4, int number5, int number6, Operation<Void> operation7) {
      if (text3 != null && text3.contains("\n")) {
         String[] items8 = text3.split("\n");
         int number9 = number5;

         for (String text13 : items8) {
            this.drawCenteredString(font2, text13, number4, number9, number6);
            number9 += font2.FONT_HEIGHT;
         }
      } else {
         operation7.call(new Object[]{guiyesno1, font2, text3, number4, number5, number6});
      }
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      if (this.lunar$choice != null && Ref.method4().method92().method3() == this.lunar$choice) {
         Ref.method4().method92().method2();
      }
   }
}
