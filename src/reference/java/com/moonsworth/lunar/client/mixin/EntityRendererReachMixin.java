package com.moonsworth.lunar.client.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.PacketEnrichmentApolloHandler;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.ResultEvent.Outcome;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderCrosshair;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen.EventRenderHotbarPost;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.renderer.EntityRenderer.class)
public abstract class EntityRendererReachMixin {
   @Shadow
   public Minecraft mc;
   @Shadow
   public Entity pointedEntity;
   @Final
   @Shadow
   public int[] lightmapColors;
   @Final
   @Shadow
   public DynamicTexture lightmapTexture;

   public EntityRendererReachMixin() {
   }

   @Shadow
   public abstract void setupCameraTransform(float value1, int number2);

   @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/resources/IResourceManager;)V", at = @At("TAIL"))
   private void lunar$initializeLightMap(CallbackInfo callback1) {
      Arrays.fill(this.lightmapColors, -1);
      this.lightmapTexture.updateDynamicTexture();
   }

   @Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;renderGameOverlay(FZII)V"))
   private void lunar$renderGui(GuiIngame guiingame1, float value2, boolean flag3, int number4, int number5) {
      if (!Ref.method4().method40().method85().method19() || !Ref.method3().bridge$getGameSettings().bridge$isHideGui()) {
         Minecraft minecraft6 = this.mc;
         GuiResolution threadmoduledump717 = new GuiResolution((MinecraftBridge)Minecraft.getMinecraft());
         LcuiScreen.method150(threadmoduledump717);
         BridgeExtension3_5 bridgeextension3_58 = AbstractRenderContext.method32();
         float value9 = LcuiScreen.getScale();
         this.lunar$render3DOverlays(value2);
         minecraft6.entityRenderer.setupOverlayRendering();
         bridgeextension3_58.method14();
         if (FramebufferCaptureTask.method1()) {
            bridgeextension3_58.method16();
            if (Minecraft.isFancyGraphicsEnabled()) {
               guiingame1.renderVignette(this.mc.thePlayer.getBrightness(value2), threadmoduledump717.getScaledWidth(), threadmoduledump717.getScaledHeight());
            }

            bridgeextension3_58.method33();
         }

         bridgeextension3_58.push();
         bridgeextension3_58.translate(0.0, 0.0, guiingame1.zLevel);
         FramebufferCaptureTask.field8.method9(bridgeextension3_58, threadmoduledump717, () -> guiingame1.renderGameOverlay(value2, flag3, number4, number5));
         bridgeextension3_58.pop();
         if (FramebufferCaptureTask.method1()) {
            bridgeextension3_58.method33();
            GL11.glEnable(32826);
            RenderHelper.enableGUIStandardItemLighting();

            for (int index10 = 0; index10 < 9; index10++) {
               int number11 = threadmoduledump717.getScaledWidth() / 2 - 90 + index10 * 20 + 2;
               int number12 = threadmoduledump717.getScaledHeight() - 16 - 3;
               guiingame1.renderInventorySlot(index10, number11, number12, value2);
            }

            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(32826);
            RewindHandlers rewindhandlers14 = Ref.method4().method40().method85().method35();
            if (rewindhandlers14 == null || rewindhandlers14.method53().method21()) {
               BridgeExtension3_5 bridgeextension3_515 = AbstractRenderContext.method9(value2);
               LunarEventBus.method29()
                  .method12(EventRenderHotbarPost.class, () -> new EventRenderHotbarPost(new LegacyGuiGraphicsBridge(bridgeextension3_515), threadmoduledump717.getScaledWidth() / 2 - 91, threadmoduledump717.getScaledHeight() - 22));
               bridgeextension3_515.method33();
            }

            if (!this.mc.gameSettings.hideGUI) {
               RewindHandlers rewindhandlers16 = Ref.method4().method40().method85().method35();
               boolean flag17 = rewindhandlers16 == null || rewindhandlers16.method53().method14();
               minecraft6.getTextureManager().bindTexture(Gui.icons);
               bridgeextension3_58.method14();
               if (flag17) {
                  if (Ref.method4().method40().method33().isEnabled()) {
                     Ref.method4()
                        .method40()
                        .method33()
                        .method6(new EventRenderHudBase(bridgeextension3_58, new LegacyGuiGraphicsBridge(bridgeextension3_58), new Data4(threadmoduledump717.method1(), threadmoduledump717.method2())));
                  } else {
                     EventRenderCrosshair highlightimpl2213 = (EventRenderCrosshair)LunarEventBus.method29().method12(EventRenderCrosshair.class, EventRenderCrosshair::new);
                     if (highlightimpl2213 == null || highlightimpl2213.method1() != Outcome.DENY) {
                        GL11.glEnable(3042);
                        OpenGlHelper.glBlendFunc(775, 769, 1, 0);
                        guiingame1.drawTexturedModalRect(threadmoduledump717.getScaledWidth() / 2 - 7, threadmoduledump717.getScaledHeight() / 2 - 7, 0, 0, 16, 16);
                     }
                  }
               }

               OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            }

            if (FramebufferCaptureTask.method2()) {
               this.mc.mcProfiler.startSection("chat");
               bridgeextension3_58.push();
               bridgeextension3_58.method14();
               bridgeextension3_58.translate(0.0, threadmoduledump717.getScaledHeight() - 48, 0.0);
               guiingame1.persistantChatGUI.drawChat(guiingame1.updateCounter);
               bridgeextension3_58.pop();
               this.mc.mcProfiler.endSection();
            }
         }

         bridgeextension3_58.method16();
         FramebufferCaptureTask.method5(threadmoduledump717, bridgeextension3_58, new LegacyGuiGraphicsBridge(bridgeextension3_58), value9);
         bridgeextension3_58.method25(1.0F, 1.0F, 1.0F, 1.0F);
         bridgeextension3_58.method11();
         bridgeextension3_58.method16();
      }
   }

   @Unique
   private void lunar$render3DOverlays(float value1) {
      this.mc.mcProfiler.startSection("overlay3d");
      GL11.glClear(256);
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GameSettings gamesettings2 = Minecraft.getMinecraft().gameSettings;
      boolean flag3 = gamesettings2.anaglyph;
      gamesettings2.anaglyph = false;
      this.setupCameraTransform(value1, 2);
      gamesettings2.anaglyph = flag3;
      HudRenderLegacyEventAlt.method2(value1);
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      this.mc.mcProfiler.endSection();
   }

   @ModifyVariable(method = "getMouseOver(F)V", at = @At(value = "STORE", ordinal = 0), ordinal = 3)
   public double apollo$saveReachVariable(double value1, @Share("reach") LocalDoubleRef localdoubleref3) {
      localdoubleref3.set(value1);
      return value1;
   }

   @Inject(
      method = "getMouseOver(F)V",
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/client/Minecraft;objectMouseOver:Lnet/minecraft/util/MovingObjectPosition;",
         shift = Shift.AFTER,
         opcode = 181,
         ordinal = 1
      )
   )
   private void apollo$reachCheck$v1_7(CallbackInfo callback1, @Share("reach") LocalDoubleRef localdoubleref2) {
      if (this.pointedEntity instanceof AbstractClientPlayer) {
         Client.method109()
            .method84()
            .method3(PacketEnrichmentModule.class)
            .filter(arg0 -> (Boolean)arg0.getOptions().get(PacketEnrichmentModule.PLAYER_ATTACK_PACKET))
            .ifPresent(arg2x -> ((PacketEnrichmentApolloHandler)arg2x).method14().put(this.pointedEntity.getUniqueID(), localdoubleref2.get()));
      }
   }
}
