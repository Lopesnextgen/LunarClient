package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge6_2;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_34;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import org.lwjgl.opengl.GL11;

public class Bridge6Handler implements Bridge6_2 {
   @Annotation2(max = 0)
   private static RenderBlocks field1;

   public Bridge6Handler() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.enableTexture2D();
         Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         GlStateManager.enableColorMaterial();
         GlStateManager.enableLighting();
         RenderHelper.enableStandardItemLighting();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableAlpha();
         GlStateManager.alphaFunc(516, 0.1F);
      } else {
         GL11.glEnable(3553);
         Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationItemsTexture$v1_7);
         GL11.glEnable(2903);
         GL11.glEnable(2896);
         RenderHelper.enableStandardItemLighting();
         GL11.glEnable(32826);
         GL11.glEnable(3008);
         GL11.glAlphaFunc(516, 0.1F);
         if (field1 == null) {
            field1 = new RenderBlocks();
         }
      }
   }

   @Override
   public void method1(AbstractRenderContext var1, Bridge3_23 var2, int var3, int var4) {
      int var5 = Bridge_34.method2(var4);
      int var6 = Bridge_34.method1(var4);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var5, var6);
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.pushMatrix();
         IBlockState var7 = ((Block)var2).getStateFromMeta(var3);
         Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(var7, 1.0F);
         GlStateManager.popMatrix();
      } else {
         GL11.glPushMatrix();
         field1.renderBlockAsItem((Block)var2, var3, 1.0F);
         GL11.glPopMatrix();
      }
   }

   @Override
   public void method2() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableRescaleNormal();
         GlStateManager.disableLighting();
      } else {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(32826);
         GL11.glDisable(2896);
      }

      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
   }
}
