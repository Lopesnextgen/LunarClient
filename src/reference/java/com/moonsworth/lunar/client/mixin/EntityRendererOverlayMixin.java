package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
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
public abstract class EntityRendererOverlayMixin {
   @Shadow
   public Minecraft mc;
   @Shadow
   public DynamicTexture lightmapTexture;
   @Shadow
   public int[] lightmapColors;

   public EntityRendererOverlayMixin() {
   }

   @Shadow
   public abstract void setupCameraTransform(float value1, int number2);

   @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/resources/IResourceManager;)V", at = @At("TAIL"))
   private void lunar$initializeLightMap(CallbackInfo callback1) {
      Arrays.fill(this.lightmapColors, -1);
      this.lightmapTexture.updateDynamicTexture();
   }

   @Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;renderGameOverlay(F)V"))
   private void lunar$renderGui(GuiIngame guiingame1, float value2) {
      if (!Ref.method4().method40().method85().method19() || !Ref.method3().bridge$getGameSettings().bridge$isHideGui()) {
         Minecraft minecraft3 = this.mc;
         GuiResolution threadmoduledump714 = new GuiResolution((MinecraftBridge)Minecraft.getMinecraft());
         LcuiScreen.method150(threadmoduledump714);
         BridgeExtension3_5 bridgeextension3_55 = AbstractRenderContext.method32();
         float value6 = LcuiScreen.getScale();
         this.lunar$render3DOverlays(value2);
         minecraft3.entityRenderer.setupOverlayRendering();
         bridgeextension3_55.method14();
         if (FramebufferCaptureTask.method1()) {
            ScaledResolution scaledresolution7 = new ScaledResolution(minecraft3);
            if (Minecraft.isFancyGraphicsEnabled()) {
               guiingame1.renderVignette(this.mc.thePlayer.getBrightness(value2), scaledresolution7);
            }

            if (!this.mc.playerController.isSpectator()) {
               guiingame1.renderTooltip(scaledresolution7, value2);
            }

            bridgeextension3_55.method33();
            bridgeextension3_55.method16();
         }

         FramebufferCaptureTask.field8.method9(bridgeextension3_55, threadmoduledump714, () -> guiingame1.renderGameOverlay(value2));
         if (FramebufferCaptureTask.method1()) {
            minecraft3.entityRenderer.setupOverlayRendering();
            bridgeextension3_55.method33();
            if (!this.mc.gameSettings.hideGUI && (guiingame1.showCrosshair() || Ref.method4().method40().method33().isEnabled())) {
               RewindHandlers rewindhandlers9 = Ref.method4().method40().method85().method35();
               boolean flag8 = rewindhandlers9 == null || rewindhandlers9.method53().method14();
               minecraft3.getTextureManager().bindTexture(Gui.icons);
               bridgeextension3_55.method14();
               if (flag8) {
                  if (Ref.method4().method40().method33().isEnabled()) {
                     Ref.method4()
                        .method40()
                        .method33()
                        .method6(new EventRenderHudBase(bridgeextension3_55, new LegacyGuiGraphicsBridge(bridgeextension3_55), new Data4(threadmoduledump714.method1(), threadmoduledump714.method2())));
                  } else {
                     GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
                     GlStateManager.enableAlpha();
                     guiingame1.drawTexturedModalRect(threadmoduledump714.getScaledWidth() / 2 - 7, threadmoduledump714.getScaledHeight() / 2 - 7, 0, 0, 16, 16);
                  }
               }

               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            }

            if (FramebufferCaptureTask.method2()) {
               this.mc.mcProfiler.startSection("chat");
               bridgeextension3_55.push();
               bridgeextension3_55.method14();
               bridgeextension3_55.translate(0.0, threadmoduledump714.getScaledHeight() - 48, 0.0);
               guiingame1.persistantChatGUI.drawChat(guiingame1.updateCounter);
               bridgeextension3_55.pop();
               this.mc.mcProfiler.endSection();
            }
         }

         bridgeextension3_55.method16();
         FramebufferCaptureTask.method5(threadmoduledump714, bridgeextension3_55, new LegacyGuiGraphicsBridge(bridgeextension3_55), value6);
         bridgeextension3_55.method25(1.0F, 1.0F, 1.0F, 1.0F);
         bridgeextension3_55.method11();
         bridgeextension3_55.method16();
      }
   }

   @Unique
   private void lunar$render3DOverlays(float value1) {
      this.mc.mcProfiler.startSection("overlay3d");
      GlStateManager.clear(256);
      GlStateManager.matrixMode(5888);
      GlStateManager.pushMatrix();
      GlStateManager.matrixMode(5889);
      GlStateManager.pushMatrix();
      GameSettings gamesettings2 = Minecraft.getMinecraft().gameSettings;
      boolean flag3 = gamesettings2.anaglyph;
      gamesettings2.anaglyph = false;
      this.setupCameraTransform(value1, 2);
      gamesettings2.anaglyph = flag3;
      HudRenderLegacyEventAlt.method2(value1);
      GlStateManager.matrixMode(5889);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      this.mc.mcProfiler.endSection();
   }
}
