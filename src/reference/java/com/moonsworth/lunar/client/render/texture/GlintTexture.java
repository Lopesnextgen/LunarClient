package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2Handler;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationKeyframeParser;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelQuad;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelVertex;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;
import com.moonsworth.lunar.client.mod.render.glintcolorizer.GlintColorizer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import java.awt.Color;
import java.util.function.IntConsumer;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.render.shader.ComputeShaderCache;
import com.moonsworth.lunar.client.render.MatrixStack;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;

@Annotation2(max = 5)
public class GlintTexture {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("textures/misc/enchanted_item_glint.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("textures/atlas/blocks.png");
   public static AnimationKeyframeParser field3 = new AnimationKeyframeParser();

   @Annotation2(max = 5)
   public static void method1(ModelRenderConfig var0) {
      if (ComputeShaderCache.method9()) {
         GL11.glEnable(32826);
         GL11.glEnable(2977);
         ComputeShaderCache.method8().method5(var0);
         GL11.glDisable(32826);
         GL11.glDisable(2977);
      } else if (!Bridge.method5().isEmpty() && ((Slayer2)Bridge.method5().get()).getConfig().hasShaders()) {
         RenderLayerBridge var1 = var0.getRenderType().get(var0.getTexture());
         Bridge2_32 var2 = var0.method7().method10(var1);
         GL11.glShadeModel(7425);
         var2.method1();

         for (IBoneSerializer var4 : var0.method10().field1) {
            method2(var0, var2, var4, null);
         }

         var2.method17(BufferBuildMode.BATCHED);
         if (var0.method14() == RenderPass.NORMAL_GLINT) {
            method12(var0);
         }
      } else {
         GL11.glEnable(32826);
         GL11.glEnable(2977);
         MatrixStack.method1(var0);
         GL11.glDisable(32826);
         GL11.glDisable(2977);
      }
   }

   private static void method2(ModelRenderConfig var0, Bridge2_32 var1, IBoneSerializer var2, Double var3) {
      field3.push();
      PlayerModelPartMap.method16(var2, field3);
      if (!var2.isHidden) {
         for (CubeMesh var5 : var2.field2) {
            field3.push();
            var0.method7().push();
            method3(var0, var1, var5, var3 == null ? var2.field4 : var3);
            var0.method7().pop();
            field3.pop();
         }

         for (IBoneSerializer var7 : var2.field1) {
            method2(var0, var1, var7, var3 == null ? var2.field4 : var3);
         }
      }

      field3.pop();
   }

   private static void method3(ModelRenderConfig var0, Bridge2_32 var1, CubeMesh var2, Double var3) {
      var3 = var3 == null ? 1.0 : var3;
      PlayerModelPartMap.method20(var2, field3);

      for (ModelQuad var7 : var2.field1) {
         if (var7 != null) {
            Vector3f var8;
            if (var0.method14() != RenderPass.EMISSIVE) {
               var8 = new Vector3f(var7.field4.x(), var7.field4.y(), var7.field4.z());
               field3.method2().transform(var8);
               if ((var2.field4.y == 0.0F || var2.field4.z == 0.0F) && var8.x() < 0.0F) {
                  var8.x *= -1.0F;
               }

               if ((var2.field4.x == 0.0F || var2.field4.z == 0.0F) && var8.y() < 0.0F) {
                  var8.y *= -1.0F;
               }

               if ((var2.field4.x == 0.0F || var2.field4.y == 0.0F) && var8.z() < 0.0F) {
                  var8.z *= -1.0F;
               }
            } else {
               var8 = new Vector3f(1.0F, 1.0F, 1.0F);
            }

            for (int var12 : var7.method1(var0)) {
               ModelVertex var13 = var7.field3[var12];
               Vector4f var14 = new Vector4f(var13.field1.x(), var13.field1.y(), var13.field1.z(), 1.0F);
               field3.method1().transform(var14);
               Color var15 = var0.getColor();
               var1.method2(var14.x() * var3, var14.y() * var3, var14.z() * var3)
                  .method10(var13.field2, var13.field3)
                  .method8(var15.getRed() / 255.0F, var15.getGreen() / 255.0F, var15.getBlue() / 255.0F, var15.getAlpha() / 255.0F)
                  .method14(var8.x(), var8.y(), var8.z())
                  .method16();
            }
         }
      }
   }

   public static Color method4() {
      ModsSettings var0 = ThreadModuleDump63.method4().method40();
      GlintColorizer var1 = var0.method26();
      if (var1.isEnabled() && var1.method13()) {
         int var2 = var1.method7(false);
         return new Color(var2);
      } else {
         return new Color(0.38F, 0.19F, 0.608F, 1.0F);
      }
   }

   public static boolean method5() {
      ModsSettings var0 = ThreadModuleDump63.method4().method40();
      GlintColorizer var1 = var0.method26();
      return var1.isEnabled() && var1.method14();
   }

   @Annotation2(max = 5)
   public static void method6(IntConsumer var0) {
      ModsSettings var1 = ThreadModuleDump63.method4().method40();
      GlintColorizer var2 = var1.method26();
      method7();
      int var3 = var2.method7(true);
      float[] var4 = Color.RGBtoHSB(var3 >> 16 & 0xFF, var3 >> 8 & 0xFF, var3 & 0xFF, null);
      int var5 = (int)((1.0F - var4[2] * var4[2]) * 255.0F) * (var3 >> 24 & 0xFF) / 255;
      if (var5 > 10) {
         var0.accept(var5 << 24);
      }

      method8();
      int var6 = ThreadModuleDump63.MC_VERSION == 0 ? (var3 >> 24 & 0xFF) / 2 << 24 | var3 & 16777215 : var3;
      var0.accept(var6);
      method11();
   }

   @Annotation2(max = 5)
   public static void method7() {
      Extension var0 = Bridge.method42().method84();
      Bridge8Handler2 var1 = ThreadModuleDump63.method3().bridge$getTextureManager();
      var0.RHRIOCRHHRORIOOICRIIIOHIROCRRO();
      var0.CORRRROHRCROIOCROHIHOOCCOHCHIR();
      var0.HROHOIOCHIRIHICOORIHOHCIOIRIIH(false);
      var0.RHIRRICCRHHHIIHHIHHOHRCHIOORCC(514);
      var0.IHCCCCRRRRRRRCIOHCORIRIHRCOICC();
      var1.bridge$bindTexture(GlintColorizer.method19());
      var0.method24(0, 771, 0, 771);
      var0.IHRHHRIHICHOOICIRIOOHOICHIRHOI(5890);
      var0.CROORCRRCORRICIOIRIICOOICHOHOO();
      float var2 = 0.25F;
      float var3 = (float)(ThreadModuleDump63.method3().bridge$getSystemTime() % 3000L) / 3000.0F / var2;
      var0.bridge$scale(var2, var2, var2);
      var0.bridge$translate(var3, 0.0F, 0.0F);
      var0.HROHOIOCHIRIHICOORIHOHCIOIRIIH(-50.0F, 0.0F, 0.0F, 1.0F);
   }

   @Annotation2(max = 5)
   public static void method8() {
      Extension var0 = Bridge.method42().method84();
      if (Bridge.getMinecraftVersion() == Config.field1) {
         Bridge8Handler2 var1 = ThreadModuleDump63.method3().bridge$getTextureManager();
         var1.bridge$bindTexture(field1);
         var0.RCIOICOHRIOIIRRRROCRHCIICRROHO(770, 1);
      } else {
         var0.method24(770, 1, 1, 1);
      }
   }

   @Annotation2(max = 5)
   public static void method9() {
      Extension var0 = Bridge.method42().method84();
      Bridge8Handler2 var1 = ThreadModuleDump63.method3().bridge$getTextureManager();
      var0.HROHOIOCHIRIHICOORIHOHCIOIRIIH(false);
      var0.RHIRRICCRHHHIIHHIHHOHRCHIOORCC(514);
      var0.IHCCCCRRRRRRRCIOHCORIRIHRCOICC();
      var1.bridge$bindTexture(field1);
      var0.CORRRROHRCROIOCROHIHOOCCOHCHIR();
      var0.method24(768, 1, 1, 0);
      var0.IHRHHRIHICHOOICIRIOOHOICHIRHOI(5890);
      var0.CROORCRRCORRICIOIRIICOOICHOHOO();
      float var2 = 0.25F;
      float var3 = (float)(ThreadModuleDump63.method3().bridge$getSystemTime() % 3000L) / 3000.0F / var2;
      var0.bridge$scale(var2, var2, var2);
      var0.bridge$translate(var3, 0.0F, 0.0F);
      var0.HROHOIOCHIRIHICOORIHOHCIOIRIIH(-50.0F, 0.0F, 0.0F, 1.0F);
   }

   @Annotation2(max = 5)
   public static void method10() {
      Extension var0 = Bridge.method42().method84();
      float var1 = 0.25F;
      var0.IORRRICHRIHCCIORRIICIIORIRRRRC();
      var0.CROORCRRCORRICIOIRIICOOICHOHOO();
      var0.bridge$scale(var1, var1, var1);
      float var2 = (float)(ThreadModuleDump63.method3().bridge$getSystemTime() % 4873L) / 4873.0F / 0.25F;
      var0.bridge$translate(-var2, 0.0F, 0.0F);
      var0.HROHOIOCHIRIHICOORIHOHCIOIRIIH(10.0F, 0.0F, 0.0F, 1.0F);
   }

   @Annotation2(max = 5)
   public static void method11() {
      Extension var0 = Bridge.method42().method84();
      var0.IORRRICHRIHCCIORRIICIIORIRRRRC();
      var0.IHRHHRIHICHOOICIRIOOHOICHIRHOI(5888);
      var0.RCIOICOHRIOIIRRRROCRHCIICRROHO(770, 771);
      var0.CRICRICOIOHIRRHRHHORICIIHORHII();
      var0.RHIRRICCRHHHIIHHIHHOHRCHIOORCC(515);
      var0.HROHOIOCHIRIHICOORIHOHCIOIRIIH(true);
      var0.IROCRIOCRIICRICCRCHCOOCOIIROCC();
   }

   @Annotation2(max = 5)
   private static void method12(ModelRenderConfig var0) {
      Bridge8Handler2 var1 = ThreadModuleDump63.method3().bridge$getTextureManager();
      BoneList var2 = var0.method10();
      Color var3 = var0.getColor();
      Bridge2Handler var4 = (Bridge2Handler)var0.method7().method10(var0.getRenderType().get(field1));
      if (method5()) {
         IntConsumer var5 = var3x -> {
            var0.method26(new Color(var3x));
            var4.method1();

            for (IBoneSerializer var5x : var2.field1) {
               method2(var0, var4, var5x, null);
            }

            var4.method13();
         };
         method6(var5);
      } else {
         var0.method26(method4());
         method9();
         var4.method1();

         for (IBoneSerializer var6 : var2.field1) {
            method2(var0, var4, var6, null);
         }

         var4.method13();
         method10();
         var4.method1();

         for (IBoneSerializer var9 : var2.field1) {
            method2(var0, var4, var9, null);
         }

         var4.method13();
         method11();
      }

      var0.method26(var3);
      var1.bridge$bindTexture(field2);
   }
}
