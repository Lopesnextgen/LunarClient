package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BipedModelBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.TexturedBoxRenderer;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.render.jit.JitAssetIndex;
import com.moonsworth.lunar.client.render.jit.JitAssetKey;
import com.moonsworth.lunar.client.render.jit.JitModelResource;
import com.moonsworth.lunar.client.render.jit.JitEmoteResource;
import com.moonsworth.lunar.client.render.jit.JitAnimatedResource;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.CosmeticType;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.client.util.colorsaturation.Colorsaturation2;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class CosmeticModelRenderer {
   @Annotation2(max = 7)
   public static void method1(
      AbstractRenderContext var0,
      Bridge5_11 var1,
      BipedModelBridge var2,
      CosmeticMetadata var3,
      AbstractCosmetic var4,
      float var5,
      float var6,
      float var7,
      boolean var8,
      boolean var9,
      boolean var10
   ) {
      OwnedCosmetic var11 = var3.method4();
      boolean var12 = var11.method10().getRenderAs() == CosmeticType.HAT;
      if (var9) {
         var0.method4(180.0F, 0.0F, 0.0F, 1.0F);
         if (var12) {
            var0.method4(90.0F, 0.0F, -1.0F, 0.0F);
         } else {
            var0.translate(0.0, -0.35F, 0.0);
         }
      } else {
         if (var1 != null) {
            var2.bridge$setSneak(var1.bridge$isSneaking());
            if (var1.bridge$isSneaking() && Bridge.getMinecraftVersion() != Config.field1) {
               var0.translate(0.0, 0.2F, 0.0);
            }

            if (var12) {
               var2.bridge$bipedHead().bridge$postRender(var5);
            } else {
               var4.method5().translate(var2).transform(var0, var1, var7);
            }
         }

         if (var12) {
            var0.method4(90.0F, 0.0F, 1.0F, 0.0F);
         }
      }

      if (var12 && var1 != null) {
         float var13 = var3.method6().getHatHeightOffset();
         var0.translate(0.0, var13 * -0.1, 0.0);
      }

      if (var8) {
         float var19 = (var6 + var7) / 20.0F * (180.0F / (float)Math.PI);
         var0.method4(var19, 0.0F, 1.0F, 0.0F);
      }

      for (ThreadModuleDump91 var14 : var4.method6()) {
         if (var14.getCondition().applies(var1, var3)) {
            var14.transform(var0, var1, var7);
         }
      }

      if (var1 != null) {
         var0.scale(var4.getScale(), var4.getScale(), var4.getScale());
      }

      ResourceLocationBridge var21 = var11.method4(var1);
      ResourceLocationBridge var22 = var4.method2();
      JitAssetIndex var15 = ThreadModuleDump63.method4().method96();
      Optional var16;
      ResourceLocationBridge var17;
      if (var4.isDynamic()) {
         var17 = var21.bridge$getPath().lastIndexOf(46) != -1 ? var21 : var4.method3();
         if (ThreadModuleDump63.method4().method53().method20(var21)) {
            var22 = ThreadModuleDump63.method4().method53().method72().get(var21);
         }

         var16 = var15.<Colorsaturation2, JitModelResource>method4(JitAssetKey.method1(var22, var21.bridge$getPath()), JitModelResource::new)
            .OIICRHIHHCCOCOCCOHOCRHOCIOICII();
      } else {
         var17 = var4.method3();
         var16 = var15.<Colorsaturation2, JitModelResource>method2(var22, JitModelResource::new).OIICRHIHHCCOCOCCOHOCRHOCIOICII();
      }

      JitAnimatedResource var18 = var15.method2(var17, var1x -> new JitAnimatedResource(var1x, var11.method10() == CosmeticCategoryType.CLOAK));
      var16.ifPresent(var4x -> {
         if (var18.method3()) {
            var18.restartAnimation();
            var0.method2(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA);
            ThreadModuleDump63.method3().bridge$getTextureManager().bridge$bindTexture(var17);
            ThreadModuleDump63.method4().method82().method3();
            var4x.method1(var0, var17);
            if (var11.method22()) {
               DriverViewLegacy.method21().method14().method3(var0, var11, () -> var4x.method1(var0, var17));
            }
         }
      });
   }

   @Annotation2(max = 7)
   public static void method2(AbstractRenderContext var0, CosmeticMetadata var1, AbstractCosmetic var2, boolean var3) {
      OwnedCosmetic var4 = var1.method4();
      boolean var5 = var4.method10().getRenderAs() == CosmeticType.HAT;
      if (var5) {
         var0.method4(90.0F, 0.0F, 1.0F, 0.0F);
      }

      var0.method4(180.0F, 0.0F, 1.0F, 0.0F);
      RenderContext var6 = RenderContext.method10();

      for (ThreadModuleDump91 var8 : var2.method6()) {
         if (var8.getCondition().applies(var6, var1)) {
            var8.transform(var0, null, 0.0F);
         }
      }

      ResourceLocationBridge var13 = var2.method2();
      JitAssetIndex var14 = ThreadModuleDump63.method4().method96();
      ResourceLocationBridge var9 = var4.method4(ThreadModuleDump63.method7());
      Optional var10;
      ResourceLocationBridge var11;
      if (var2.isDynamic()) {
         var11 = var9.bridge$getPath().lastIndexOf(46) != -1 ? var9 : var2.method3();
         if (ThreadModuleDump63.method4().method53().method20(var9)) {
            var13 = ThreadModuleDump63.method4().method53().method72().get(var9);
         }

         var10 = var14.<Colorsaturation2, JitModelResource>method4(JitAssetKey.method1(var13, var9.bridge$getPath()), JitModelResource::new)
            .OIICRHIHHCCOCOCCOHOCRHOCIOICII();
      } else {
         var11 = var2.method3();
         var10 = var14.<Colorsaturation2, JitModelResource>method2(var13, JitModelResource::new).OIICRHIHHCCOCOCCOHOCRHOCIOICII();
      }

      JitAnimatedResource var12 = var14.method2(var11, var1x -> new JitAnimatedResource(var1x, var4.method10() == CosmeticCategoryType.CLOAK));
      var10.ifPresent(var4x -> {
         if (var12.HRCHCCCICOCHCHCRIRORCCCHHORIOO()) {
            var12.restartAnimation();
            var0.method2(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA);
            ThreadModuleDump63.method3().bridge$getTextureManager().bridge$bindTexture(var11);
            ThreadModuleDump63.method4().method82().method3();
            var4x.method1(var0, var11);
            if (var4.method22()) {
               DriverViewLegacy.method21().method14().method3(var0, var4, () -> var4x.method1(var0, var11));
            }
         }
      });
   }

   @Annotation2(min = 6)
   public static void method3(
      BridgeExtension2_11 var0,
      EntityPlayerBridge var1,
      BridgeExtension2_7 var2,
      RenderLayerBridge var3,
      CosmeticMetadata var4,
      AbstractCosmetic var5,
      float var6,
      boolean var7,
      boolean var8,
      int var9
   ) {
      OwnedCosmetic var10 = var4.method4();
      boolean var11 = var10.method10().getRenderAs() == CosmeticType.HAT;
      if (var8) {
         var0.method51().bridge$rotateDegrees(0.0F, 0.0F, 180.0F);
         if (var11) {
            var0.method51().bridge$rotateDegrees(0.0F, -90.0F, 0.0F);
         }
      } else {
         if (var2 != null) {
            var5.method5().translateModern(var2).transform(var0, var1, var0.method51(), var0.method28());
         }

         if (var11) {
            if (Bridge.getMinecraftVersion().method23()) {
               var0.method5(0.0F, 90.0F, 0.0F);
            } else {
               var0.method51().bridge$rotateDegrees(0.0F, 90.0F, 0.0F);
            }
         }
      }

      if (var11 && var1 != null) {
         float var12 = var4.method6().getHatHeightOffset();
         if (Bridge.getMinecraftVersion().method23()) {
            var12 *= -1.0F;
         } else {
            var12 *= var8 ? -1.0F : 1.0F;
         }

         var0.translate(0.0, var12 * 0.1, 0.0);
      }

      if (var7) {
         float var17 = (var6 + var0.method28()) / 20.0F * (180.0F / (float)Math.PI);
         var0.method51().bridge$rotateDegrees(0.0F, var17, 0.0F);
      }

      for (ThreadModuleDump91 var13 : var5.method6()) {
         if (var13.getCondition().applies(var1, var4)) {
            if (Bridge.getMinecraftVersion().method23()) {
               var13.transform(var0, var1, var0.method51(), var0.method28());
            } else {
               var13.transform(var0, var1, var0.method28());
            }
         }
      }

      if (var1 != null) {
         var0.method51().bridge$scale(var5.getScale(), var5.getScale(), var5.getScale());
      }

      JitAssetIndex var19 = ThreadModuleDump63.method4().method96();
      ResourceLocationBridge var20 = var5.method2();
      ResourceLocationBridge var14 = var10.method4(ThreadModuleDump63.method7());
      Optional var15;
      if (var5.isDynamic() && ThreadModuleDump63.method4().method53().method20(var14)) {
         var20 = ThreadModuleDump63.method4().method53().method72().get(var14);
         var15 = var19.<Colorsaturation2, JitModelResource>method4(JitAssetKey.method1(var20, var14.bridge$getPath()), JitModelResource::new)
            .OIICRHIHHCCOCOCCOHOCRHOCIOICII();
      } else {
         var15 = var19.<Colorsaturation2, JitModelResource>method2(var20, JitModelResource::new).OIICRHIHHCCOCOCCOHOCRHOCIOICII();
      }

      var15.ifPresent(var4x -> {
         ThreadModuleDump63.method4().method82().method3();
         var4x.method2(var3, var0, var9);
         if (var10.method22()) {
            DriverViewLegacy.method21().method14().method3(var0, var10, () -> var4x.method2(var3, var0, var9));
         }
      });
   }

   @Annotation2(min = 6)
   public static void method4(BridgeExtension2_11 var0, RenderLayerBridge var1, CosmeticMetadata var2, AbstractCosmetic var3) {
      OwnedCosmetic var4 = var2.method4();
      boolean var5 = var4.method10().getRenderAs() == CosmeticType.HAT;
      if (var5) {
         if (Bridge.getMinecraftVersion().method23()) {
            var0.method5(0.0F, 90.0F, 0.0F);
         } else {
            var0.method51().bridge$rotateDegrees(0.0F, 90.0F, 0.0F);
         }
      }

      var0.method4(180.0F, 0.0F, 1.0F, 0.0F);
      RenderContext var6 = RenderContext.method10();

      for (ThreadModuleDump91 var8 : var3.method6()) {
         if (var8.getCondition().applies(var6, var2)) {
            if (Bridge.getMinecraftVersion().method23()) {
               var8.transform(var0, null, var0.method51(), var0.method28());
            } else {
               var8.transform(var0, null, var0.method28());
            }
         }
      }

      JitAssetIndex var11 = ThreadModuleDump63.method4().method96();
      ResourceLocationBridge var12 = var3.method2();
      ResourceLocationBridge var9 = var4.method4(ThreadModuleDump63.method7());
      Optional var10;
      if (var3.isDynamic() && ThreadModuleDump63.method4().method53().method20(var9)) {
         var12 = ThreadModuleDump63.method4().method53().method72().get(var9);
         var10 = var11.<Colorsaturation2, JitModelResource>method4(JitAssetKey.method1(var12, var9.bridge$getPath()), JitModelResource::new)
            .OIICRHIHHCCOCOCCOHOCRHOCIOICII();
      } else {
         var10 = var11.<Colorsaturation2, JitModelResource>method2(var12, JitModelResource::new).OIICRHIHHCCOCOCCOHOCRHOCIOICII();
      }

      var10.ifPresent(var2x -> {
         ThreadModuleDump63.method4().method82().method3();
         var2x.method2(var1, var0, -1);
      });
   }

   @Contract("null,null,_,_,_,_->fail")
   public static void method5(
      @Nullable AbstractRenderContext var0,
      @Nullable Consumer<TexturedBoxRenderer> var1,
      @Nullable EntityPlayerBridge var2,
      @Nullable Bridge8Extension3 var3,
      ResourceLocationBridge var4,
      Runnable var5
   ) {
      if (var3 == null) {
         var5.run();
      } else {
         if (var0 == null && var1 == null) {
            throw new IllegalArgumentException("Either renderContext or modelRenderer must not be null!");
         }

         UUID var6 = var2 == null ? null : var2.bridge$getUniqueID();
         if (!ThreadModuleDump63.method4().method53().method71().contains(var4) && var4 != CosmeticManager.field2) {
            Bridge3_4 var7 = var3.method2();
            if (var7 != null) {
               Bridge3Extension_7 var8;
               if (var7 instanceof Bridge3Extension_7 var9) {
                  var9.method3(true);
                  var8 = var9;
                  if (var7 instanceof JitEmoteResource var10) {
                     var10.method9(var2);
                  }
               } else {
                  var8 = null;
               }

               if (var7 instanceof Alert5 var11) {
                  var11.method12();
                  if (var0 == null) {
                     var1.accept(CosmeticManager.field3);
                  } else {
                     CosmeticManager.field3.method1(var0, 0.0315F, var4, var6);
                  }
               } else if (var8 == null || !var8.method13()) {
                  var5.run();
               } else if (var0 == null) {
                  var1.accept(CosmeticManager.field3);
               } else {
                  CosmeticManager.field3.method1(var0, 0.0315F, var4, var6);
               }
            } else if (Client.method109().method53().method70().contains(var4)) {
               if (var0 == null) {
                  var1.accept(CosmeticManager.field3);
               } else {
                  CosmeticManager.field3.method1(var0, 0.0315F, var4, var6);
               }
            } else {
               var5.run();
            }
         } else {
            if (var0 == null) {
               var1.accept(CosmeticManager.field3);
            } else {
               CosmeticManager.field3.method1(var0, 0.0315F, var4, var6);
            }
         }
      }
   }
}
