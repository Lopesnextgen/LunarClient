package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiClickable;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableColor;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "com.replaymod.lib.de.johni0702.minecraft.gui.element.advanced.AbstractGuiDropdownMenu$DropdownEntry")
public abstract class AbstractGuiDropdownEntryMixin_v1_8 extends AbstractGuiClickable {
   @Redirect(
      method = "draw",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;drawRect(IIIILcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;)V"
      )
   )
   public void ichor$draw$rect(GuiRenderer var1, int var2, int var3, int var4, int var5, ReadableColor var6) {
      BridgeExtension3_5 var7 = BridgeExtension3_5.method32();
      boolean var8 = Client.method109().method40().method64().method14().get();
      if (!var8) {
         var1.drawRect(var2, var3, var4, var5, var6);
      } else {
         if (var6 == ReadableColor.BLACK) {
            LcuiScreen.method28(var7, var1.getOpenGlOffset().getX(), var1.getOpenGlOffset().getY(), var4 + 2, var5 + 1, 4.0F, -553648128);
         }
      }
   }

   @Redirect(
      method = "draw",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;drawString(IILcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;Ljava/lang/String;)I"
      )
   )
   public int ichor$draw$string(GuiRenderer var1, int var2, int var3, ReadableColor var4, String var5) {
      BridgeExtension3_5 var6 = BridgeExtension3_5.method32();
      boolean var7 = Client.method109().method40().method64().method14().get();
      if (!var7) {
         return var1.drawString(var2, var3, var4, var5);
      }

      FontRegistry.method9().method3(var6, var5, var1.getOpenGlOffset().getX() + 3.0F, var1.getOpenGlOffset().getY() + 3.0F, -1);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.blendFunc(770, 771);
      return 0;
   }
}
