package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.renderer.EntityRenderer.class)
public abstract class EntityRendererMixin3 {
   @Shadow
   public Minecraft mc;
   @Shadow
   public DynamicTexture lightmapTexture;
   @Shadow
   public int[] lightmapColors;

   @Shadow
   public abstract void setupCameraTransform(float var1, int var2);

   @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/resources/IResourceManager;)V", at = @At("TAIL"))
   private void lunar$initializeLightMap(CallbackInfo var1) {
      Arrays.fill(this.lightmapColors, -1);
      this.lightmapTexture.updateDynamicTexture();
   }

   @Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;renderGameOverlay(F)V"))
   private void lunar$renderGui(GuiIngame var1, float var2) {
      if (!ThreadModuleDump63.method4().method40().method85().method19() || !ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isHideGui()) {
         Minecraft var3 = this.mc;
         ThreadModuleDump71 var4 = new ThreadModuleDump71((Bridge5_12)Minecraft.getMinecraft());
         LcuiScreen.method150(var4);
         BridgeExtension3_5 var5 = AbstractRenderContext.method32();
         float var6 = LcuiScreen.getScale();
         this.lunar$render3DOverlays(var2);
         var3.entityRenderer.setupOverlayRendering();
         var5.method14();
         if (FramebufferCaptureTask.method1()) {
            ScaledResolution var7 = new ScaledResolution(var3);
            if (Minecraft.isFancyGraphicsEnabled()) {
               var1.renderVignette(this.mc.thePlayer.getBrightness(var2), var7);
            }

            if (!this.mc.playerController.isSpectator()) {
               var1.renderTooltip(var7, var2);
            }

            var5.method33();
            var5.method16();
         }

         FramebufferCaptureTask.field8.method9(var5, var4, () -> var1.renderGameOverlay(var2));
         if (FramebufferCaptureTask.method1()) {
            var3.entityRenderer.setupOverlayRendering();
            var5.method33();
            if (!this.mc.gameSettings.hideGUI && (var1.showCrosshair() || ThreadModuleDump63.method4().method40().method33().isEnabled())) {
               RewindHandlers var9 = ThreadModuleDump63.method4().method40().method85().method35();
               boolean var8 = var9 == null || var9.method53().method14();
               var3.getTextureManager().bindTexture(Gui.icons);
               var5.method14();
               if (var8) {
                  if (ThreadModuleDump63.method4().method40().method33().isEnabled()) {
                     ThreadModuleDump63.method4()
                        .method40()
                        .method33()
                        .method6(new HudBaseRenderEvent(var5, new LegacyGuiGraphicsBridge(var5), new MarkerModel.Data4(var4.getScaledWidth_double(), var4.getScaledHeight_double())));
                  } else {
                     GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
                     GlStateManager.enableAlpha();
                     var1.drawTexturedModalRect(var4.getScaledWidth() / 2 - 7, var4.getScaledHeight() / 2 - 7, 0, 0, 16, 16);
                  }
               }

               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            }

            if (FramebufferCaptureTask.method2()) {
               this.mc.mcProfiler.startSection("chat");
               var5.push();
               var5.method14();
               var5.translate(0.0, var4.getScaledHeight() - 48, 0.0);
               var1.persistantChatGUI.drawChat(var1.updateCounter);
               var5.pop();
               this.mc.mcProfiler.endSection();
            }
         }

         var5.method16();
         FramebufferCaptureTask.method5(var4, var5, new LegacyGuiGraphicsBridge(var5), var6);
         var5.method25(1.0F, 1.0F, 1.0F, 1.0F);
         var5.method11();
         var5.method16();
      }
   }

   @Unique
   private void lunar$render3DOverlays(float var1) {
      this.mc.mcProfiler.startSection("overlay3d");
      GlStateManager.clear(256);
      GlStateManager.matrixMode(5888);
      GlStateManager.pushMatrix();
      GlStateManager.matrixMode(5889);
      GlStateManager.pushMatrix();
      GameSettings var2 = Minecraft.getMinecraft().gameSettings;
      boolean var3 = var2.anaglyph;
      var2.anaglyph = false;
      this.setupCameraTransform(var1, 2);
      var2.anaglyph = var3;
      HudRenderLegacyEventAlt.method2(var1);
      GlStateManager.matrixMode(5889);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      this.mc.mcProfiler.endSection();
   }
}
