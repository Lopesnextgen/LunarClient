package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BatchingBufferSourceBridge;
import com.moonsworth.lunar.bridge.Bridge17Extension_2;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelQuad;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelVertex;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;
import com.moonsworth.lunar.client.driver.core.holograms.HologramRendererLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.awt.Color;
import java.util.Optional;
import java.util.function.Supplier;
import org.joml.Matrix3x2fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;
import com.moonsworth.lunar.client.cosmetics.gecko.GeckoRenderMode;

@Annotation2(min = 6)
public class CosmeticMeshBuilder {
   private static boolean method1() {
      Optional var0 = Fishing.method2(Fishing2Extension.class);
      return var0.isPresent()
         && ((Fishing2Extension)var0.get()).lunar$areShadersEnabledInConfig()
         && !"(off)".equals(((Fishing2Extension)var0.get()).lunar$getShaderPack());
   }

   @Annotation2(min = 6)
   public static void method2(ModelRenderConfig var0) {
      RenderLayerBridge var1 = var0.getRenderType().get(var0.getTexture());
      GeckoRenderMode var2 = method3(var0, var1, false, true);
      if (var2 != GeckoRenderMode.RENDERED_GECKO_COMPUTE && var0.method14() == RenderPass.NORMAL_GLINT) {
         method3(var0, LunarRenderTypes.field14, false, false);
      }
   }

   public static GeckoRenderMode method3(ModelRenderConfig var0, RenderLayerBridge var1, boolean var2, boolean var3) {
      Supplier var4 = () -> var0.method7()
         .method30()
         .method2(var1)
         .orElse(
            var3
               ? (Bridge4_6)Bridge.method9()
                  .bridge$getRenderBuffers()
                  .bridge$bufferSource()
                  .bridge$getBuffer(var1)
                  .orElseThrow(() -> new IllegalStateException("Failed to build buffer for render type " + var1))
               : null
         );
      return method4(var0, var4, var2);
   }

   @Annotation2(min = 6)
   public static GeckoRenderMode method4(ModelRenderConfig var0, Supplier<Bridge4_6> var1, boolean var2) {
      Runnable var3 = null;
      if (var2) {
         var3 = method5(var0);
      }

      boolean var4 = ThreadModuleDump63.MC_VERSION >= 39 && var0.method14() == RenderPass.NORMAL_GLINT;
      if (ThreadModuleDump63.MC_VERSION >= 8 && !var4 && GeckoComputePipeline.method13() && !method1() && !var0.method7().method30().isOutlineBufferSource()) {
         if (var3 != null) {
            var3.run();
         }

         GeckoComputePipeline.method12().method10(var0, var0.method1());
         return GeckoRenderMode.RENDERED_GECKO_COMPUTE;
      } else {
         Bridge4_6 var5 = (Bridge4_6)var1.get();
         if (var5 == null) {
            return GeckoRenderMode.RENDERED_NORMAL;
         }

         Bridge5_16 var6 = var0.method7().method30().method51();
         Matrix3x2fStack var7 = null;
         if (HologramRendererLegacy.field1 && ThreadModuleDump63.MC_VERSION >= 30) {
            var7 = var6.bridge$unbindGuiGraphics();
         }

         int var8 = var0.method1();

         for (IBoneSerializer var10 : var0.method10().field1) {
            method6(var0, var10, var8, var5);
         }

         if (HologramRendererLegacy.field1 && ThreadModuleDump63.MC_VERSION >= 30) {
            var6.bridge$bindToGuiGraphics(var7);
         }

         if (var3 != null) {
            var3.run();
         }

         return GeckoRenderMode.RENDERED_NORMAL;
      }
   }

   private static Runnable method5(ModelRenderConfig var0) {
      Optional var1 = var0.method7().method30().method43();
      if (var1.isEmpty()) {
         return null;
      }

      BatchingBufferSourceBridge var2 = (BatchingBufferSourceBridge)var1.get();
      BatchingBufferSourceBridge var3 = Fishing.method2(Fishing2Extension.class).map(var1x -> var1x.lunar$unwrapMultiBufferSource(var2)).orElse(var2);
      return var3 instanceof Bridge17Extension_2 var4 ? () -> {
         var4.bridge$endBatch();
         var4.bridge$endBatch(EmoteModel.method9(var0.getTexture()));
      } : null;
   }

   @Annotation2(min = 6)
   private static void method6(ModelRenderConfig var0, IBoneSerializer var1, int var2, Bridge4_6 var3) {
      if (!var1.isHidden) {
         Bridge5_16 var4 = var0.method7().method30().method51();
         var4.bridge$pushPose();
         PlayerModelPartMap.method15(var1, var4);

         for (CubeMesh var6 : var1.field2) {
            var4.bridge$pushPose();
            method7(var0, var6, var2, var3);
            var4.bridge$popPose();
         }

         for (IBoneSerializer var8 : var1.field1) {
            method6(var0, var8, var2, var3);
         }

         var4.bridge$popPose();
      }
   }

   @Annotation2(min = 6)
   private static void method7(ModelRenderConfig var0, CubeMesh var1, int var2, Bridge4_6 var3) {
      Bridge5_16 var4 = var0.method7().method30().method51();
      Color var5 = var0.getColor();
      PlayerModelPartMap.method19(var1, var4);
      Matrix3fBridge var6 = var4.bridge$last().bridge$normal();
      MixinHelper_21 var7 = var4.bridge$last().bridge$pose();

      for (ModelQuad var11 : var1.field1) {
         if (var11 != null) {
            Vector3f var12;
            if (var0.method14() != RenderPass.EMISSIVE) {
               var12 = var6.method3(var11.field4);
               if (var12.x() < 0.0F) {
                  var12.mul(-1.0F, 1.0F, 1.0F);
               }

               if (var12.y() < 0.0F) {
                  var12.mul(1.0F, -1.0F, 1.0F);
               }

               if (var12.z() < 0.0F) {
                  var12.mul(1.0F, 1.0F, -1.0F);
               }
            } else {
               var12 = new Vector3f(1.0F);
            }

            for (int var16 : var11.method1(var0)) {
               ModelVertex var17 = var11.field3[var16];
               Vector4f var18 = var7.method7(var17.field1);
               var3.bridge$vertex(var18.x(), var18.y(), var18.z())
                  .bridge$color(var5.getRed(), var5.getGreen(), var5.getBlue(), var5.getAlpha())
                  .bridge$uv(var17.field2, var17.field3)
                  .bridge$overlayCoords(0, 10)
                  .bridge$uv2(var2 & 0xFF, var2 >> 16 & 0xFF)
                  .bridge$normal(var12.x(), var12.y(), var12.z())
                  .bridge$endVertex();
            }
         }
      }
   }
}
