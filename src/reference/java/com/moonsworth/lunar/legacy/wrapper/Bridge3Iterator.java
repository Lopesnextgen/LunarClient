package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge3_35;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.mod.render.nametag.Nametag;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class Bridge3Iterator implements Bridge3_35 {
   private GuiMainMenu field1;
   private float field2;

   public void method1() {
      RenderHelper.enableStandardItemLighting();
   }

   public void method6(MixinHelper_4 var1, boolean var2) {
      AbstractRenderContext var3 = var1.method46().method29();
      Minecraft var4 = Minecraft.getMinecraft();
      ThreadModuleDump71 var5 = LcuiScreen.method151();
      int var6 = var5 == null ? var4.displayWidth : var5.getScaledWidth();
      int var7 = var5 == null ? var4.displayHeight : var5.getScaledHeight();
      if (this.field1 == null) {
         this.field1 = new GuiMainMenu();
         this.field1.setWorldAndResolution(var4, var6, var7);
      }

      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.field2 = var3.method28();
      }

      this.field1.width = var6;
      this.field1.height = var7;
      var3.method17();
      this.field1.renderSkybox(0, 0, var3.method28());
      var3.method16();
   }

   public void method7() {
      if (this.field1 != null) {
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            this.field1.panoramaTimer$v1_12 = this.field1.panoramaTimer$v1_12 + this.field2;
         } else {
            this.field1.panoramaTimer++;
         }
      }
   }

   public void method2() {
      RenderHelper.enableGUIStandardItemLighting();
   }

   public void method3() {
      RenderHelper.disableStandardItemLighting();
   }

   public void method5(Bridge10_2 var1, Component var2, double var3, double var5, double var7, float var9, boolean var10, boolean var11, BridgeExtension var12) {
      Nametag var13 = ThreadModuleDump63.method4().method40().method51();
      BridgeExtension3_5 var15 = AbstractRenderContext.method32();
      Extension var16 = Bridge.method42().method84();
      var16.method16(516, 0.1F);
      RenderManager var17 = ThreadModuleDump63.MC_VERSION >= 1 ? Minecraft.getMinecraft().getRenderManager() : RenderManager.theMinecraft;
      if (var10) {
         var16.CROORCRRCORRICIOIRIICOOICHOHOO();
         var16.bridge$translate((float)var3, (float)var5 + var9 + 0.5F, (float)var7);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         var16.HROHOIOCHIRIHICOORIHOHCIOIRIIH(-var17.playerViewY, 0.0F, 1.0F, 0.0F);
         var16.HROHOIOCHIRIHICOORIHOHCIOIRIIH(var17.playerViewX, 1.0F, 0.0F, 0.0F);
         var16.bridge$scale(-0.02666667F, -0.02666667F, 0.02666667F);
         var16.bridge$translate(0.0F, 9.374999F, 0.0F);
         var16.IHCCCCRRRRRRRCIOHCORIRIHRCOICC();
         var16.HROHOIOCHIRIHICOORIHOHCIOIRIIH(false);
         var16.CORRRROHRCROIOCROHIHOOCCOHCHIR();
         var16.HCRCORICRHRICCICIICCHCOHCCIIOI();
         var16.HHRROIIHRRICIIHIIHICRHHRHOHHOO(770, 771, 1, 0);
         int var18 = (int)(var1.bridge$getStringWidth(var2) / 2.0F);
         Tessellator var19 = ThreadModuleDump63.MC_VERSION >= 1 ? Tessellator.getInstance() : Tessellator.theMinecraft;
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            WorldRenderer var20 = ThreadModuleDump63.MC_VERSION >= 5 ? var19.getBuffer$v1_12() : var19.getWorldRenderer();
            var20.begin(7, DefaultVertexFormats.POSITION_COLOR);
            var20.pos(-var18 - 1, -1.0, 0.0).color(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity()).endVertex();
            var20.pos(-var18 - 1, 8.0, 0.0).color(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity()).endVertex();
            var20.pos(var18 + 1, 8.0, 0.0).color(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity()).endVertex();
            var20.pos(var18 + 1, -1.0, 0.0).color(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity()).endVertex();
            var19.draw();
         } else {
            var19.startDrawingQuads$v1_7();
            var19.setColorRGBA_F$v1_7(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity());
            var19.addVertex$v1_7(-var18 - 1, -1.0, 0.0);
            var19.addVertex$v1_7(-var18 - 1, 8.0, 0.0);
            var19.addVertex$v1_7(var18 + 1, 8.0, 0.0);
            var19.addVertex$v1_7(var18 + 1, -1.0, 0.0);
            var19.draw();
         }

         var16.HCHCCORHORIOIHHIRROHCRHRCHHOIR();
         var16.HROHOIOCHIRIHICOORIHOHCIOIRIIH(true);
         float var30 = var1.method18(var2, 0.0F);
         int var21 = 553648127;
         if (var13.isEnabled() && var13.getNametagShadow().get()) {
            var1.bridge$drawShadow(var15, var2, var30, 0.0F, var21);
            Bridge.method42().bridge$translate(0.0F, 0.0F, -0.001F);
         }

         if (var12 == ThreadModuleDump63.method7() && var2 instanceof TextComponent var22) {
            var1.method6(var15, var22, var30, 0.0F, var21, false, true);
         } else {
            var1.bridge$drawString(var15, var2, var30, 0.0F, var21, false);
         }

         var16.CRICRICOIOHIRRHRHHORICIIHORHII();
         this.method9();
         var16.IROCRIOCRIICRICCRCHCOOCOIIROCC();
         var16.CCROIHHHCOCHHOHORCIRHOCRROIOCI(1.0F, 1.0F, 1.0F, 1.0F);
         var16.IORRRICHRIHCCIORRIICIIORIRRRRC();
      } else {
         float var28 = 1.6F;
         float var14 = 0.016666668F * var28;
         var16.CROORCRRCORRICIOIRIICOOICHOHOO();
         var16.bridge$translate((float)var3 + 0.0F, (float)var5 + var9 + 0.5F, (float)var7);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         var16.HROHOIOCHIRIHICOORIHOHCIOIRIIH(-var17.playerViewY, 0.0F, 1.0F, 0.0F);
         var16.HROHOIOCHIRIHICOORIHOHCIOIRIIH(var17.playerViewX, 1.0F, 0.0F, 0.0F);
         var16.bridge$scale(-var14, -var14, var14);
         var16.IHCCCCRRRRRRRCIOHCORIRIHRCOICC();
         var16.HROHOIOCHIRIHICOORIHOHCIOIRIIH(false);
         var16.HOCOHCROOOCHCORRHHICHRIRROIRHR();
         var16.CORRRROHRCROIOCROHIHOOCCOHCHIR();
         var16.HHRROIIHRRICIIHIIHICRHHRHOHHOO(770, 771, 1, 0);
         Tessellator var29 = ThreadModuleDump63.MC_VERSION >= 1 ? Tessellator.getInstance() : Tessellator.theMinecraft;
         byte var31 = 0;
         if (AdventureTextBridge.doesComponentEqual(var2, "deadmau5")) {
            var31 = -10;
         }

         CosmeticManager.Data var32 = var12 == null ? null : ThreadModuleDump63.method4().method53().method63().get(var12.bridge$getUniqueID());
         boolean var33 = false;
         if (var12 instanceof BridgeExtension2_5 var23) {
            var33 = var2.equals(var23.bridge$getDisplayNameComponent())
               && var11
               && var32 != null
               && (var32.method8() || ThreadModuleDump63.method4().method41().method6().method43().get());
         }

         int var34 = (int)var1.bridge$getStringWidth(var2);
         int var24 = var34 / 2;
         var16.HCRCORICRHRICCICIICCHCOHCCIIOI();
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            WorldRenderer var25 = ThreadModuleDump63.MC_VERSION >= 5 ? var29.getBuffer$v1_12() : var29.getWorldRenderer();
            var25.begin(7, DefaultVertexFormats.POSITION_COLOR);
            var25.pos(-var24 - 1 - (var33 ? 6 : 0), -1 + var31, 0.0).color(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity()).endVertex();
            var25.pos(-var24 - 1 - (var33 ? 6 : 0), 8 + var31, 0.0).color(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity()).endVertex();
            var25.pos(var24 + 1 + (var33 ? 6 : 0), 8 + var31, 0.0).color(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity()).endVertex();
            var25.pos(var24 + 1 + (var33 ? 6 : 0), -1 + var31, 0.0).color(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity()).endVertex();
            var29.draw();
         } else {
            var29.startDrawingQuads$v1_7();
            var29.setColorRGBA_F$v1_7(0.0F, 0.0F, 0.0F, var13.getBackgroundOpacity());
            var29.addVertex$v1_7(-var24 - 1 - (var33 ? 6 : 0), -1 + var31, 0.0);
            var29.addVertex$v1_7(-var24 - 1 - (var33 ? 6 : 0), 8 + var31, 0.0);
            var29.addVertex$v1_7(var24 + 1 + (var33 ? 6 : 0), 8 + var31, 0.0);
            var29.addVertex$v1_7(var24 + 1 + (var33 ? 6 : 0), -1 + var31, 0.0);
            var29.draw();
         }

         var16.HCHCCORHORIOIHHIRROHCRHRCHHOIR();
         int var35 = (-var34 + (var33 ? 12 : 0)) / 2;
         int var26 = 553648127;
         if (var13.isEnabled() && var13.getNametagShadow().get()) {
            var1.bridge$drawShadow(var15, var2, var35, 0.0F, var26);
            var16.bridge$translate(0.0F, 0.0F, -0.001F);
         }

         if (var12 == ThreadModuleDump63.method7() && var2 instanceof TextComponent var27) {
            var1.method6(var15, var27, var35, var31, var26, false, true);
         } else {
            var1.bridge$drawString(var15, var2, var35, var31, var26, false);
         }

         var16.RHRIOCRHHRORIOOICRIIIOHIROCRRO();
         var16.HROHOIOCHIRIHICOORIHOHCIOIRIIH(true);
         if (var33) {
            var16.CROORCRRCORRICIOIRIICOOICHOHOO();
            int var36 = ThreadModuleDump23.method11(var32.method5(), var32.method6(), var32.method7(), 1.0F);
            LcuiScreen.method41(
               LunarRenderTypes.field34.get(CosmeticManager.field40),
               var15,
               var35 - 12.0F,
               var31 - 1.5F,
               (float)LcuiScreen.z,
               0.0F,
               0.0F,
               10.0F,
               10.0F,
               10.0F,
               10.0F,
               var36
            );
            if (var32.method1()) {
               LcuiScreen.method41(
                  LunarRenderTypes.field34.get(CosmeticManager.field37),
                  var15,
                  var35 - 12.0F + 7.0F,
                  var31 - 1.5F + 2.0F,
                  (float)LcuiScreen.z,
                  0.0F,
                  0.0F,
                  4.0F,
                  4.0F,
                  4.0F,
                  4.0F,
                  var32.method9()
               );
            }

            var16.IORRRICHRIHCCIORRIICIIORIRRRRC();
         }

         if (var13.isEnabled() && var13.getNametagShadow().get()) {
            var1.bridge$drawShadow(var15, var2, var35, 0.0F, -1);
            Bridge.method42().bridge$translate(0.0F, 0.0F, -0.001F);
         }

         if (var12 == ThreadModuleDump63.method7() && var2 instanceof TextComponent var37) {
            var1.method6(var15, var37, var35, var31, -1, false, true);
         } else {
            var1.bridge$drawString(var15, var2, var35, var31, -1, false);
         }

         var16.CRICRICOIOHIRRHRHHORICIIHORHII();
         this.method9();
         var16.IROCRIOCRIICRICCRCHCOOCOIIROCC();
         var16.IORRRICHRIHCCIORRIICIIORIRRRRC();
      }
   }

   public void method4(
      Bridge10_2 var1, List<Component> var2, double var3, double var5, double var7, float var9, boolean var10, boolean var11, BridgeExtension var12
   ) {
      int var13 = 0;

      for (Component var15 : var2) {
         var5 += var13 / 3.5F;
         this.method5(var1, var15, var3, var5, var7, var9, var10, var11, var12);
         var13++;
      }
   }

   public void method9() {
      GL11.glEnable(2896);
      GL11.glEnable(16384);
      GL11.glEnable(16385);
      GL11.glEnable(2903);
      GL11.glColorMaterial(1032, 5634);
   }

   public void method10() {
      throw new IllegalStateException("Not implemented yet");
   }
}
