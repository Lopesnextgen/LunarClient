package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.gui.prompt.PromptAction;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
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
public abstract class GuiYesNoMixin2 extends GuiScreen {
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

   @Inject(method = "<init>(Lnet/minecraft/client/gui/GuiYesNoCallback;Ljava/lang/String;Ljava/lang/String;I)V", at = @At("TAIL"))
   private void lunar$insertCheckboxCallback$1(GuiYesNoCallback var1, String var2, String var3, int var4, CallbackInfo var5) {
      this.lunar$insertCheckboxCallback(var1);
   }

   @Inject(
      method = "<init>(Lnet/minecraft/client/gui/GuiYesNoCallback;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V",
      at = @At("TAIL")
   )
   private void lunar$insertCheckboxCallback$2(GuiYesNoCallback var1, String var2, String var3, String var4, String var5, int var6, CallbackInfo var7) {
      this.lunar$insertCheckboxCallback(var1);
   }

   @Inject(method = "actionPerformed(Lnet/minecraft/client/gui/GuiButton;)V", at = @At("HEAD"), cancellable = true)
   private void lunar$preventCheckboxFromClosing(GuiButton var1, CallbackInfo var2) {
      if (var1 == this.lunar$saveChoiceCheckbox || var1 == this.lunar$saveAllChoiceCheckbox) {
         var2.cancel();
      }
   }

   @Inject(method = "initGui()V", at = @At("RETURN"))
   private void lunar$addCheckboxAndCenter(CallbackInfo var1) {
      Component var2 = this.lunar$getCheckboxText();
      if (var2 != null) {
         List var3 = ThreadModuleDump63.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
         String var4 = AdventureTextBridge.asLegacyString(var2);
         int var5 = (int)(ThreadModuleDump63.method10().bridge$getStringWidth(var4) + 24.0F);
         int var6 = this.width / 2 - var5 / 2;
         boolean var7 = this.lunar$saveChoiceCheckbox != null && this.lunar$saveChoiceCheckbox.isSelected();
         this.lunar$saveChoiceCheckbox = new GuiButtonImpl(2, var6, 0, var4, var7);
         var3.add(this.lunar$saveChoiceCheckbox);
         Component var8 = this.lunar$getSecondaryCheckboxText();
         if (var8 != null) {
            String var9 = AdventureTextBridge.asLegacyString(var8);
            int var10 = (int)(ThreadModuleDump63.method10().bridge$getStringWidth(var9) + 24.0F);
            int var11 = this.width / 2 - var10 / 2;
            boolean var12 = this.lunar$saveAllChoiceCheckbox != null && this.lunar$saveAllChoiceCheckbox.isSelected();
            this.lunar$saveAllChoiceCheckbox = new GuiButtonImpl(3, var11, 0, var9, var12);
            var3.add(this.lunar$saveAllChoiceCheckbox);
         }

         this.lunar$layoutPrompt();
      }
   }

   @Unique
   private void lunar$layoutPrompt() {
      List var1 = ThreadModuleDump63.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
      boolean var2 = this.lunar$saveAllChoiceCheckbox != null;
      int var3 = this.lunar$getLineCount() * 9;
      int var4 = 20 + var3 + 40 + 28 + (var2 ? 24 : 0);
      int var5 = Math.max(2, (this.height - var4) / 2);
      this.lunar$titleY = var5;
      this.lunar$listY = var5 + 20;
      int var6 = this.lunar$listY + var3 + 20;

      for (GuiButton var8 : var1) {
         if (var8 != this.lunar$saveChoiceCheckbox && var8 != this.lunar$saveAllChoiceCheckbox) {
            var8.yPosition = var6;
         }
      }

      int var9 = var6 + 28;
      this.lunar$saveChoiceCheckbox.y = var9;
      if (this.lunar$saveAllChoiceCheckbox != null) {
         this.lunar$saveAllChoiceCheckbox.y = var9 + 24;
      }
   }

   @Unique
   private void lunar$insertCheckboxCallback(GuiYesNoCallback var1) {
      PromptAction var2 = ThreadModuleDump63.method4().method92().method3();
      if (var2 != null) {
         this.lunar$choice = var2;
         this.parentScreen = (var3, var4) -> {
            var1.confirmClicked(var3, var4);
            if (this.lunar$saveChoiceCheckbox != null && this.lunar$saveChoiceCheckbox.isSelected()) {
               var2.method1(var3);
            }

            if (this.lunar$saveAllChoiceCheckbox != null && this.lunar$saveAllChoiceCheckbox.isSelected()) {
               var2.method4(var3);
            }
         };
      }
   }

   @Unique
   private Component lunar$getCheckboxText() {
      PromptAction var1 = ThreadModuleDump63.method4().method92().method3();
      return var1 != null ? var1.method2() : null;
   }

   @Unique
   private Component lunar$getSecondaryCheckboxText() {
      PromptAction var1 = ThreadModuleDump63.method4().method92().method3();
      return var1 != null ? var1.method3() : null;
   }

   @Unique
   private boolean lunar$hasChoice() {
      return ThreadModuleDump63.method4().method92().method3() != null;
   }

   @ModifyConstant(method = "drawScreen", constant = @Constant(intValue = 70))
   private int lunar$replaceTitleY(int var1) {
      return this.lunar$hasChoice() ? this.lunar$titleY : var1;
   }

   @ModifyConstant(method = "drawScreen", constant = @Constant(intValue = 90))
   private int lunar$replaceListY(int var1) {
      return this.lunar$hasChoice() ? this.lunar$listY : var1;
   }

   @Unique
   private int lunar$getLineCount() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return this.field_175298_s != null ? this.field_175298_s.size() : 0;
      }

      if (this.messageLine2 != null) {
         int var1 = 1;

         for (char var5 : this.messageLine2.toCharArray()) {
            if (var5 == '\n') {
               var1++;
            }
         }

         return var1;
      } else {
         return 0;
      }
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "drawScreen",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiYesNo;drawCenteredString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V"
      )
   )
   private void lunar$wrapDrawCenteredString$v1_7(GuiYesNo var1, FontRenderer var2, String var3, int var4, int var5, int var6, Operation<Void> var7) {
      if (var3 != null && var3.contains("\n")) {
         String[] var8 = var3.split("\n");
         int var9 = var5;

         for (String var13 : var8) {
            this.drawCenteredString(var2, var13, var4, var9, var6);
            var9 += var2.FONT_HEIGHT;
         }
      } else {
         var7.call(new Object[]{var1, var2, var3, var4, var5, var6});
      }
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      if (this.lunar$choice != null && ThreadModuleDump63.method4().method92().method3() == this.lunar$choice) {
         ThreadModuleDump63.method4().method92().method2();
      }
   }
}
