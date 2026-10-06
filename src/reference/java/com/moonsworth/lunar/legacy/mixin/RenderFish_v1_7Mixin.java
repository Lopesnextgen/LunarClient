package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.client.renderer.entity.RenderFish_v1_7;
import net.minecraft.entity.projectile.EntityFishHook;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin({RenderFish_v1_7.class, RenderFish.class})
public class RenderFish_v1_7Mixin {
   @Unique
   private static boolean lunar$deferring;
   @Unique
   private static final float lunar$LINE_SEGMENTS = 16.0F;
   @Unique
   private int lunar$lineVertexIndex;

   @Annotation2(min = 1)
   @Inject(
      method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/BufferBuilder;begin$v1_8(ILnet/minecraft/client/renderer/vertex/VertexFormat;)V",
         ordinal = 1,
         shift = Shift.AFTER
      )
   )
   private void lunar$modifyFishingLineThickness$v1_8(EntityFishHook var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      this.lunar$lineVertexIndex = 0;
      OverlayMod var11 = ThreadModuleDump63.method4().method40().method84();
      if (var11.isCustomFishingLineEnabled()) {
         GL11.glLineWidth(var11.getFishingLineThickness());
      }
   }

   @Annotation2(min = 1)
   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;color$v1_8(IIII)Lnet/minecraft/client/renderer/BufferBuilder;")
   )
   private WorldRenderer lunar$modifyFishingLineColor$v1_8(WorldRenderer var1, int var2, int var3, int var4, int var5, Operation<WorldRenderer> var6) {
      OverlayMod var7 = ThreadModuleDump63.method4().method40().method84();
      if (var7.isCustomFishingLineEnabled()) {
         int var8 = var7.getFishingLineColor(this.lunar$lineVertexIndex++ / 16.0F);
         var2 = ThreadModuleDump23.method1(var8);
         var3 = ThreadModuleDump23.method2(var8);
         var4 = ThreadModuleDump23.method3(var8);
         var5 = ThreadModuleDump23.method4(var8);
      }

      return (WorldRenderer)var6.call(new Object[]{var1, var2, var3, var4, var5});
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorOpaque_I$v1_7(I)V")
   )
   private void lunar$modifyFishingLine$v1_7(Tessellator var1, int var2, Operation<Void> var3) {
      this.lunar$lineVertexIndex = 0;
      OverlayMod var4 = ThreadModuleDump63.method4().method40().method84();
      if (var4.isCustomFishingLineEnabled()) {
         GL11.glLineWidth(var4.getFishingLineThickness());
         ColorOption var5 = var4.getFishingLineColorOption();
         var1.setColorRGBA_F$v1_7(
            var5.IROHICIOOHIRCOCHOOCROHROIIRRIC(0.0F) / 255.0F,
            var5.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(0.0F) / 255.0F,
            var5.IHIRROIOORHHCOOCCOOHHHCHOCCORR(0.0F) / 255.0F,
            var5.CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F) / 255.0F
         );
      } else {
         var3.call(new Object[]{var1, var2});
      }
   }

   @Annotation2(max = 0)
   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;addVertex$v1_7(DDD)V")
   )
   private void lunar$modifyFishingLineVertexColor$v1_7(Tessellator var1, double var2, double var4, double var6, Operation<Void> var8) {
      OverlayMod var9 = ThreadModuleDump63.method4().method40().method84();
      if (var9.isCustomFishingLineEnabled() && var9.getFishingLineColorOption().method14()) {
         int var10 = var9.getFishingLineColor(this.lunar$lineVertexIndex++ / 16.0F);
         var1.setColorRGBA_F$v1_7(
            ThreadModuleDump23.method5(var10), ThreadModuleDump23.greenFloat(var10), ThreadModuleDump23.method7(var10), ThreadModuleDump23.method8(var10)
         );
      }

      var8.call(new Object[]{var1, var2, var4, var6});
   }

   @Inject(method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V", at = @At("RETURN"))
   private void lunar$resetLineWidth(EntityFishHook var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      GL11.glLineWidth(1.0F);
   }

   @Annotation2(min = 5)
   @Inject(method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V", at = @At("HEAD"), cancellable = true)
   private void lunar$deferBobber(EntityFishHook var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      if (!lunar$deferring && !(this.lunar$getBobberOpacity(var1) >= 1.0F) && ThreadModuleDump63.method4().method103().method6()) {
         RenderFish var11 = (RenderFish)this;
         int var12 = var1.getBrightnessForRender();
         float var13 = var12 % 65536;
         float var14 = var12 / 65536;
         ThreadModuleDump63.method4().method103().method2(() -> {
            lunar$deferring = true;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var13, var14);
            var11.doRender(var1, var2, var4, var6, var8, var9);
            lunar$deferring = false;
         });
         var10.cancel();
      }
   }

   @Annotation2(min = 5)
   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_8()V", ordinal = 0)
   )
   private void lunar$modifyBobberOpacity(Tessellator var1, Operation<Void> var2, @Local(argsOnly = true) EntityFishHook var3) {
      float var4 = this.lunar$getBobberOpacity(var3);
      if (var4 >= 1.0F) {
         var2.call(new Object[]{var1});
      } else {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.depthMask(false);
         GlStateManager.color(1.0F, 1.0F, 1.0F, var4);
         var2.call(new Object[]{var1});
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.depthMask(true);
         GlStateManager.disableBlend();
      }
   }

   @Annotation2(min = 5)
   @Unique
   private float lunar$getBobberOpacity(EntityFishHook var1) {
      return var1.caughtEntity != null && var1.caughtEntity == Minecraft.getMinecraft().thePlayer
         ? ThreadModuleDump63.method4().method40().method84().method30()
         : 1.0F;
   }
}
