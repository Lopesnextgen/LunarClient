package com.moonsworth.lunar.legacy.mixin;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.GuiControlsBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiKeyBindingList.KeyEntry;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiControls.class)
public abstract class GuiControlsMixin implements GuiControlsBridge, Translatable {
   @Shadow
   public GuiKeyBindingList keyBindingList;

   public GuiControlsMixin() {
   }

   @Inject(method = "initGui", at = @At("HEAD"))
   private void onInit(CallbackInfo callback1) {
      Ref.method28(null);
   }

   @Inject(method = "drawScreen", at = @At("TAIL"))
   private void lunar$onDrawScreen(int number1, int number2, float value3, CallbackInfo callback4) {
      for (IGuiListEntry iguilistentry8 : this.keyBindingList.listEntries) {
         if (iguilistentry8 instanceof KeyEntry keyentry9) {
            KeyBindingBridge mixinhelper_1510 = Ref.MC_VERSION >= 1 ? (KeyBindingBridge)keyentry9.keybinding : (KeyBindingBridge)keyentry9.field_148282_b$v1_7;
            GuiButton guibutton11 = ((KeyEntry)iguilistentry8).btnChangeKeyBinding;
            if (!mixinhelper_1510.bridge$getClashesWith().isEmpty()) {
               boolean flag12 = number1 > guibutton11.xPosition
                  && number1 < guibutton11.xPosition + guibutton11.width
                  && number2 > guibutton11.yPosition
                  && number2 < guibutton11.yPosition + guibutton11.height;
               if (flag12) {
                  LcuiScreen.method85(
                     new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()),
                     ImmutableList.of(
                        this.method1(
                           "clashesWith", new Object[]{ChatFormatting.RED + Ref.method26(mixinhelper_1510.bridge$getClashesWith())}
                        )
                     ),
                     number1,
                     number2
                  );
               }
            }
         }
      }
   }

   public String getLanguagePath() {
      return "gui.components";
   }
}
