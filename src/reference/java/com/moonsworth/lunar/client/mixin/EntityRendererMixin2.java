package com.moonsworth.lunar.client.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.Highlight3Iterator31;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.OutcomeEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.InventoryScreenRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.CrosshairRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
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
public abstract class EntityRendererMixin2 {
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

   @Shadow
   public abstract void setupCameraTransform(float var1, int var2);

   @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/resources/IResourceManager;)V", at = @At("TAIL"))
   private void lunar$initializeLightMap(CallbackInfo var1) {
      Arrays.fill(this.lightmapColors, -1);
      this.lightmapTexture.updateDynamicTexture();
   }

   @Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;renderGameOverlay(FZII)V"))
   private void lunar$renderGui(GuiIngame var1, float var2, boolean var3, int var4, int var5) {
      if (!ThreadModuleDump63.method4().method40().method85().method19() || !ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isHideGui()) {
         Minecraft var6 = this.mc;
         ThreadModuleDump71 var7 = new ThreadModuleDump71((Bridge5_12)Minecraft.getMinecraft());
         LcuiScreen.method150(var7);
         BridgeExtension3_5 var8 = AbstractRenderContext.method32();
         float var9 = LcuiScreen.getScale();
         this.lunar$render3DOverlays(var2);
         var6.entityRenderer.setupOverlayRendering();
         var8.method14();
         if (FramebufferCaptureTask.method1()) {
            var8.method16();
            if (Minecraft.isFancyGraphicsEnabled()) {
               var1.renderVignette(this.mc.thePlayer.getBrightness(var2), var7.getScaledWidth(), var7.getScaledHeight());
            }

            var8.method33();
         }

         var8.push();
         var8.translate(0.0, 0.0, var1.zLevel);
         FramebufferCaptureTask.field8.method9(var8, var7, () -> var1.renderGameOverlay(var2, var3, var4, var5));
         var8.pop();
         if (FramebufferCaptureTask.method1()) {
            var8.method33();
            GL11.glEnable(32826);
            RenderHelper.enableGUIStandardItemLighting();

            for (int var10 = 0; var10 < 9; var10++) {
               int var11 = var7.getScaledWidth() / 2 - 90 + var10 * 20 + 2;
               int var12 = var7.getScaledHeight() - 16 - 3;
               var1.renderInventorySlot(var10, var11, var12, var2);
            }

            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(32826);
            RewindHandlers var14 = ThreadModuleDump63.method4().method40().method85().method35();
            if (var14 == null || var14.method53().method21()) {
               BridgeExtension3_5 var15 = AbstractRenderContext.method9(var2);
               ClientEventBus.method29()
                  .method12(
                     InventoryScreenRenderEvent.HotbarPostEvent.class,
                     () -> new InventoryScreenRenderEvent.HotbarPostEvent(new LegacyGuiGraphicsBridge(var15), var7.getScaledWidth() / 2 - 91, var7.getScaledHeight() - 22)
                  );
               var15.method33();
            }

            if (!this.mc.gameSettings.hideGUI) {
               RewindHandlers var16 = ThreadModuleDump63.method4().method40().method85().method35();
               boolean var17 = var16 == null || var16.method53().method14();
               var6.getTextureManager().bindTexture(Gui.icons);
               var8.method14();
               if (var17) {
                  if (ThreadModuleDump63.method4().method40().method33().isEnabled()) {
                     ThreadModuleDump63.method4()
                        .method40()
                        .method33()
                        .method6(new HudBaseRenderEvent(var8, new LegacyGuiGraphicsBridge(var8), new MarkerModel.Data4(var7.getScaledWidth_double(), var7.getScaledHeight_double())));
                  } else {
                     CrosshairRenderEvent var13 = ClientEventBus.method29().method12(CrosshairRenderEvent.class, CrosshairRenderEvent::new);
                     if (var13 == null || var13.method1() != OutcomeEvent.Type.DENY) {
                        GL11.glEnable(3042);
                        OpenGlHelper.glBlendFunc(775, 769, 1, 0);
                        var1.drawTexturedModalRect(var7.getScaledWidth() / 2 - 7, var7.getScaledHeight() / 2 - 7, 0, 0, 16, 16);
                     }
                  }
               }

               OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            }

            if (FramebufferCaptureTask.method2()) {
               this.mc.mcProfiler.startSection("chat");
               var8.push();
               var8.method14();
               var8.translate(0.0, var7.getScaledHeight() - 48, 0.0);
               var1.persistantChatGUI.drawChat(var1.updateCounter);
               var8.pop();
               this.mc.mcProfiler.endSection();
            }
         }

         var8.method16();
         FramebufferCaptureTask.method5(var7, var8, new LegacyGuiGraphicsBridge(var8), var9);
         var8.method25(1.0F, 1.0F, 1.0F, 1.0F);
         var8.method11();
         var8.method16();
      }
   }

   @Unique
   private void lunar$render3DOverlays(float var1) {
      this.mc.mcProfiler.startSection("overlay3d");
      GL11.glClear(256);
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GameSettings var2 = Minecraft.getMinecraft().gameSettings;
      boolean var3 = var2.anaglyph;
      var2.anaglyph = false;
      this.setupCameraTransform(var1, 2);
      var2.anaglyph = var3;
      HudRenderLegacyEventAlt.method2(var1);
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      this.mc.mcProfiler.endSection();
   }

   @ModifyVariable(method = "getMouseOver(F)V", at = @At(value = "STORE", ordinal = 0), ordinal = 3)
   public double apollo$saveReachVariable(double var1, @Share("reach") LocalDoubleRef var3) {
      var3.set(var1);
      return var1;
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
   private void apollo$reachCheck$v1_7(CallbackInfo var1, @Share("reach") LocalDoubleRef var2) {
      if (this.pointedEntity instanceof AbstractClientPlayer) {
         Client.method109()
            .method84()
            .<ApolloModuleHandler>method3(PacketEnrichmentModule.class)
            .filter(var0 -> (Boolean)var0.getOptions().get(PacketEnrichmentModule.PLAYER_ATTACK_PACKET))
            .ifPresent(var2x -> ((Highlight3Iterator31)var2x).method14().put(this.pointedEntity.getUniqueID(), var2.get()));
      }
   }
}
