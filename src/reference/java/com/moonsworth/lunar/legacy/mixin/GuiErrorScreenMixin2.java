package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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
public abstract class GuiErrorScreenMixin2 extends GuiScreen {
   @Final
   @Shadow
   public String message;
   @Unique
   private int lunar$titleY;
   @Unique
   private int lunar$messageY;

   @Inject(method = "initGui()V", at = @At("RETURN"))
   private void lunar$centerContent(CallbackInfo var1) {
      if (this.lunar$hasChoice()) {
         List var2 = ThreadModuleDump63.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
         int var3 = this.lunar$getLineCount() * 9;
         int var4 = 20 + var3 + 40;
         int var5 = (this.height - var4) / 2;
         this.lunar$titleY = var5;
         this.lunar$messageY = var5 + 20;
         int var6 = this.lunar$messageY + var3 + 20;

         for (GuiButton var8 : var2) {
            var8.yPosition = var6;
         }
      }
   }

   @ModifyConstant(method = "drawScreen", constant = @Constant(intValue = 90))
   private int lunar$replaceTitleY(int var1) {
      return this.lunar$hasChoice() ? this.lunar$titleY : var1;
   }

   @ModifyConstant(method = "drawScreen", constant = @Constant(intValue = 110))
   private int lunar$replaceMessageY(int var1) {
      return this.lunar$hasChoice() ? this.lunar$messageY : var1;
   }

   @Unique
   private boolean lunar$hasChoice() {
      return ThreadModuleDump63.method4().method92().method3() != null;
   }

   @Unique
   private int lunar$getLineCount() {
      if (this.message != null) {
         int var1 = 1;

         for (char var5 : this.message.toCharArray()) {
            if (var5 == '\n') {
               var1++;
            }
         }

         return var1;
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
   private void lunar$wrapDrawCenteredString$v1_7(GuiErrorScreen var1, FontRenderer var2, String var3, int var4, int var5, int var6, Operation<Void> var7) {
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
}
