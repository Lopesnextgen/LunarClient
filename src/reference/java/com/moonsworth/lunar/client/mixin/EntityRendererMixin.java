package com.moonsworth.lunar.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
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
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayTotemAnimation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.renderer.EntityRenderer.class)
public abstract class EntityRendererMixin {
   @Final
   @Shadow
   public Minecraft mc;
   @Final
   @Shadow
   public int[] lightmapColors;
   @Final
   @Shadow
   public DynamicTexture lightmapTexture;
   @Shadow
   public ItemStack itemActivationItem;
   @Shadow
   public int itemActivationTicks;
   @Shadow
   public float itemActivationOffX;
   @Shadow
   public float itemActivationOffY;

   @Shadow
   public abstract void setupOverlayRendering();

   @Shadow
   public abstract void setupCameraTransform(float var1, int var2);

   @Inject(method = "displayItemActivation", at = @At("RETURN"))
   private void lunar$onDisplayItemActivation(ItemStack var1, CallbackInfo var2) {
      if (this.lunar$isTotemAnimationOverriden()) {
         this.itemActivationTicks = ThreadModuleDump63.method4().method40().method84().method70().method13();
      }
   }

   @WrapMethod(method = "renderItemActivation")
   private void lunar$onRenderItemActivation(int var1, int var2, float var3, Operation<Void> var4) {
      if (!this.lunar$isTotemAnimationOverriden()) {
         this.itemActivationTicks = Math.min(40, this.itemActivationTicks);
         var4.call(new Object[]{var1, var2, var3});
      } else {
         OverlayTotemAnimation var5 = ThreadModuleDump63.method4().method40().method84().method70();
         if (this.itemActivationItem != null && this.itemActivationTicks > 0 && !var5.method14()) {
            int var6 = var5.method13();
            int var7 = var6 - this.itemActivationTicks;
            float var8 = (var7 + var3) / var6;
            var8 = MathHelper.clamp(var8, 0.0F, 1.0F);
            float var9 = var8 * var8;
            float var10 = var8 * var9;
            float var11 = 10.25F * var10 * var9 + -24.95F * var9 * var9 + 25.5F * var10 + -13.8F * var9 + 4.0F * var8;
            var11 *= (float) Math.PI;
            int var12 = var5.method9(var1);
            int var13 = var5.method10(var2);
            boolean var14 = var5.method17();
            float var15 = var14 ? this.itemActivationOffX * (var12 / 4) : 0.0F;
            float var16 = var14 ? this.itemActivationOffY * (var13 / 4) : 0.0F;
            GlStateManager.enableAlpha();
            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();
            GlStateManager.enableDepth();
            GlStateManager.disableCull();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.translate(
               var5.method7(var12 / 2) + var15 * MathHelper.abs(MathHelper.sin(var11 * 2.0F)),
               var5.method8(var13 / 2) + var16 * MathHelper.abs(MathHelper.sin(var11 * 2.0F)),
               -50.0F
            );
            float var17 = 50.0F + 175.0F * var5.method2(true) * MathHelper.sin(var11);
            GlStateManager.scale(var17, -var17, var17);
            if (var5.method16()) {
               float var18 = var5.method15();
               GlStateManager.rotate(900.0F * MathHelper.abs(MathHelper.sin(var11 * var18)), 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(6.0F * MathHelper.cos(var8 * 8.0F * var18), 1.0F, 0.0F, 0.0F);
               GlStateManager.rotate(6.0F * MathHelper.cos(var8 * 8.0F * var18), 0.0F, 0.0F, 1.0F);
            }

            this.mc.getRenderItem().renderItem(this.itemActivationItem, TransformType.FIXED);
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableCull();
            GlStateManager.disableDepth();
         }
      }
   }

   @Unique
   private boolean lunar$isTotemAnimationOverriden() {
      if (this.itemActivationItem != null && this.itemActivationItem.getItem() == Items.TOTEM_OF_UNDYING) {
         OverlayMod var1 = ThreadModuleDump63.method4().method40().method84();
         return var1.isEnabled() && var1.getTotemAnimationOverlay().isEnabled();
      } else {
         return false;
      }
   }

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
         var5.method14();
         ScaledResolution var7 = new ScaledResolution(var3);
         if (FramebufferCaptureTask.method1()) {
            if (Minecraft.isFancyGraphicsEnabled()) {
               var1.renderVignette(var3.player.getBrightness(), new ScaledResolution(var3));
            }

            if (!var3.playerController.isSpectator()) {
               var1.renderHotbar(var7, var2);
            }

            var5.method33();
         }

         FramebufferCaptureTask.field8.method9(var5, var4, () -> var1.renderGameOverlay(var2));
         if (FramebufferCaptureTask.method1()) {
            this.setupOverlayRendering();
            var5.method33();
            if (!var3.gameSettings.hideGUI) {
               RewindHandlers var8 = ThreadModuleDump63.method4().method40().method85().method35();
               boolean var9 = var8 == null || var8.method53().method14();
               var3.getTextureManager().bindTexture(Gui.icons);
               var5.method14();
               if (var9) {
                  if (ThreadModuleDump63.method4().method40().method33().isEnabled()) {
                     this.lunar$renderAttackIndicator(var1, var4);
                     ThreadModuleDump63.method4()
                        .method40()
                        .method33()
                        .method6(new HudBaseRenderEvent(var5, new LegacyGuiGraphicsBridge(var5), new MarkerModel.Data4(var4.getScaledWidth_double(), var4.getScaledHeight_double())));
                  } else {
                     var1.renderAttackIndicator(var2, var7);
                  }
               }

               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            }

            if (FramebufferCaptureTask.method2()) {
               var3.mcProfiler.startSection("chat");
               var5.push();
               var5.method14();
               var5.translate(0.0, var4.getScaledHeight() - 48, 0.0);
               var1.persistantChatGUI.drawChat(var1.updateCounter);
               var5.pop();
               var3.mcProfiler.endSection();
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

   @Unique
   private void lunar$renderAttackIndicator(Gui var1, ThreadModuleDump71 var2) {
      int var3 = var2.getScaledWidth();
      int var4 = var2.getScaledHeight();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.ONE_MINUS_DST_COLOR, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.enableAlpha();
      if (this.mc.gameSettings.attackIndicator == 1 && !ThreadModuleDump63.method4().method40().method34().method13()) {
         float var5 = this.mc.player.getCooledAttackStrength(0.0F);
         boolean var6 = false;
         if (this.mc.pointedEntity != null && this.mc.pointedEntity instanceof EntityLivingBase && var5 >= 1.0F) {
            var6 = this.mc.player.getCooldownPeriod() > 5.0F;
            var6 = var6 && this.mc.pointedEntity.isEntityAlive();
         }

         int var7 = var4 / 2 - 7 + 16;
         int var8 = var3 / 2 - 8;
         if (var6) {
            var1.drawTexturedModalRect(var8, var7, 68, 94, 16, 16);
         } else if (var5 < 1.0F) {
            int var9 = (int)(var5 * 17.0F);
            var1.drawTexturedModalRect(var8, var7, 36, 94, 16, 4);
            var1.drawTexturedModalRect(var8, var7, 52, 94, var9, 4);
         }
      }

      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
   }
}
