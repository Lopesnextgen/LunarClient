package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.client.renderer.entity.RenderXPOrb_v1_7;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin({RenderXPOrb_v1_7.class, RenderXPOrb.class})
public class RenderXPOrb_v1_7Mixin {
   @Annotation2(max = 0)
   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/item/EntityXPOrb;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA_I$v1_7(II)V")
   )
   private void lunar$onRender$v1_7(Tessellator var1, int var2, int var3, Operation<Void> var4) {
      OverlayMod var5 = ThreadModuleDump63.method4().method40().method84();
      if (var5.method25()) {
         GL11.glEnable(3042);
         GL11.glEnable(3008);
         var2 = var5.getXpOrbColor().method14(0.0F);
         var3 = var5.getXpOrbColor().CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F);
      }

      var4.call(new Object[]{var1, var2, var3});
   }

   @Annotation2(min = 1)
   @WrapOperation(
      method = "doRender(Lnet/minecraft/entity/item/EntityXPOrb;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;color$v1_8(IIII)Lnet/minecraft/client/renderer/BufferBuilder;")
   )
   private WorldRenderer lunar$onRender$v1_8(WorldRenderer var1, int var2, int var3, int var4, int var5, Operation<WorldRenderer> var6) {
      OverlayMod var7 = ThreadModuleDump63.method4().method40().method84();
      if (var7.method25()) {
         GlStateManager.enableAlpha();
         GlStateManager.enableBlend();
         ColorOption var8 = var7.getXpOrbColor();
         var2 = var8.IROHICIOOHIRCOCHOOCROHROIIRRIC(0.0F);
         var3 = var8.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(0.0F);
         var4 = var8.IHIRROIOORHHCOOCCOOHHHCHOCCORR(0.0F);
         var5 = var8.CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F);
      }

      return (WorldRenderer)var6.call(new Object[]{var1, var2, var3, var4, var5});
   }
}
