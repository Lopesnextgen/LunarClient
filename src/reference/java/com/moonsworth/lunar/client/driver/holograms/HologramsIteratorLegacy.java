package com.moonsworth.lunar.client.driver.holograms;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.AbstractCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticModelRenderer;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.emote.CosmeticMeshBuilder;
import com.moonsworth.lunar.client.render.texture.GlintTexture;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.framework.feature.blockoutline.Blockoutline;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.util.alert.Alert5;
import java.util.Optional;
import java.util.UUID;
import org.jspecify.annotations.Nullable;

public class HologramsIteratorLegacy implements MarkerModelRendererLegacy<CosmeticHologramLegacy> {
   private static final int field1 = 3;
   private static final int field2 = 40;
   private static final double field3 = 0.01;
   public static boolean field4 = false;
   private boolean field5;

   public void method1(CosmeticHologramLegacy var1) {
      var1.init();
   }

   public void method2(AbstractRenderContext var1, CosmeticHologramLegacy var2, MarkerModel.Data5 var3) {
      field4 = true;
      CosmeticMetadata var4 = var2.method8();
      if (var4 != null) {
         OwnedCosmetic var5 = var4.method4();
         float var6 = (float)var2.getX();
         float var7 = (float)var2.getY();
         float var8 = var2.getZoom();
         float var9 = var2.method14().getHeight() * var8;
         float var10 = var2.method14().getWidth();
         float var11 = var2.method14().getHeight();
         var1.push();
         if (ThreadModuleDump63.method1()) {
            var1.method35(0.0, var10, var11, 0.0, 21000.0, 1000.0);
         } else {
            var1.method35(0.0, var10, var11, 0.0, 1000.0, ThreadModuleDump63.MC_VERSION >= 17 ? 21000.0 : 3000.0);
         }

         if (ThreadModuleDump63.MC_VERSION < 8) {
            var1.method27(0, 0, (int)var10, (int)var11);
         }

         var1.method22();
         var1.method18();
         if (Bridge.getMinecraftVersion().method21()) {
            var1.method26();
         }

         var1.method25(1.0F, 1.0F, 1.0F, 1.0F);
         if (var5 instanceof EmoteModel) {
            this.method3(var1, var2, var4, var6, var7, var10, var11, var9);
         } else if (var5.method10() == CosmeticCategoryType.CLOAK) {
            this.method4(var1, var5, var2, var6, var7, var10, var11, var9);
         } else if (var5.method10() == CosmeticCategoryType.WINGS) {
            this.method5(var1, var5, var2, var6, var7, var10, var11, var9);
         } else {
            this.method6(var1, var4, var2, var6, var7, var10, var11, var9);
         }

         var1.method23();
         var1.method6(var1x -> {
            var1.method28(Bridge.method22().method5());
            var1.method13();
            var1.method28(Bridge.method22().method6());
         });
         var1.method41();
         var1.pop();
      }

      field4 = false;
   }

   private void method3(AbstractRenderContext var1, CosmeticHologramLegacy var2, CosmeticMetadata var3, float var4, float var5, float var6, float var7, float var8) {
      byte var9 = -1;
      if (var3.method4() instanceof EmoteModel var10) {
         MolangFunctionRegistry var33 = ThreadModuleDump63.method4().method76();
         if (var33.method14(var10)) {
            if (!var10.method6().isEmpty() && !var10.method5().isEmpty()) {
               com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel var12 = (com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel)var10.method5()
                  .get();
               RenderContext var13 = RenderContext.method11(HologramsIterator2.method14());
               CosmeticManager var14 = ThreadModuleDump63.method4().method53();
               var14.method22(var10.HHRHHCIOIOIHOHRCCHRHOOHRHRIHRO()).ifPresentOrElse(var13::method24, () -> var13.method24(var3));
               var12.method1(var13);
               var12.setLivingAnimations(var10, var10.method4());
               Evaluator var15 = var12.method10().getEvaluator();
               Optional var16 = var12.method1(var12.method1(var10, var15));
               if (!var16.isEmpty()) {
                  BoneList var17 = (BoneList)var16.get();
                  boolean var18 = ThreadModuleDump63.method4().method40().method73().isValid()
                     && (Boolean)ThreadModuleDump63.method4().method40().method73().method16().get();
                  if (var18) {
                     LcuiScreen.method97(var1, var4, var5 + var7 / 2.0F - 0.5F, var6, 1.0F, 553648127);
                     LcuiScreen.method97(var1, var4 + var6 / 2.0F - 0.5F, var5, 1.0F, var7, 553648127);
                  }

                  ResourceLocationBridge var19 = PlayerModelPartMap.method24(var10, var12, null, var15);
                  var1.push();
                  var1.translate(var6 / 2.0F, var7 / 2.0F, 0.0);
                  var1.scale(-1.0F, -1.0F, -1.0F);
                  var1.method4(15.5F, -1.0F, 0.0F, 0.0F);
                  var1.method4(35.0F, 0.0F, 1.0F, 0.0F);
                  if (var10.RHIHHIHIIICHHCRHRCIRROIHOHRIIH() == CosmeticCategoryType.SHIELDS) {
                     var1.method4(90.0F, 0.0F, 1.0F, 0.0F);
                     var1.method4(90.0F, 1.0F, 0.0F, 0.0F);
                     var8 *= 0.7F;
                  }

                  var17.method2("bipedLeftArm").ifPresent(var0 -> var0.setHidden(false));
                  var17.method2("bipedRightArm").ifPresent(var0 -> var0.setHidden(false));
                  var17.method2("bipedHead").ifPresent(var0 -> var0.setHidden(false));
                  var17.method2("bipedBody").ifPresent(var0 -> var0.setHidden(false));
                  var17.method2("bipedLeftLeg").ifPresent(var0 -> var0.setHidden(false));
                  var17.method2("bipedRightLeg").ifPresent(var0 -> var0.setHidden(false));
                  AxisAlignedBBBridge var20 = var2.method9();
                  if (var20 == null) {
                     var20 = var17.method1();
                     int var21 = EventClientTick.field1;
                     if (var20 != null && (var2.method22() == 0 || var21 != var2.method31())) {
                        var2.method15(var21);
                        boolean var22 = method7(var20, var2.method10());
                        var2.method12(var20);
                        var2.method13(var2.method22() + 1);
                        var2.method14(var22 ? var2.method23() + 1 : 0);
                        if (var2.method23() >= 3 || var2.method22() >= 40) {
                           var2.method11(var20);
                        }
                     }
                  }

                  if (var20 != null) {
                     float var34 = (float)(var20.bridge$getMaxX() - var20.bridge$getMinX());
                     float var37 = (float)(var20.bridge$getMaxY() - var20.bridge$getMinY());
                     float var23 = (float)(var20.bridge$getMaxZ() - var20.bridge$getMinZ());
                     float var24 = var34 * 0.725F + var23 * 0.725F;
                     float var25 = var37 + var34 * 0.275F + var23 * 0.275F;
                     float var26 = var6 / (var24 * var8);
                     float var27 = var7 / (var25 * var8);
                     float var28 = Math.min(var26, var27);
                     float var32 = Math.min(var8, var8 * var28);
                     var8 = Math.max(var32, 0.1F);
                     float var29 = (float)(var20.bridge$getMinX() + var34 / 2.0F) * var8;
                     float var30 = (float)(var20.bridge$getMinY() + var37 / 2.0F) * var8;
                     float var31 = (float)(var20.bridge$getMinZ() + var23 / 2.0F) * var8;
                     var1.translate(-var29, -var30, -var31);
                  }

                  var1.scale(var8, var8, var8);
                  var1.method16();
                  var1.method14();
                  var1.method18(var9);
                  var1.method22();
                  var1.method26();

                  for (ThreadModuleDump91 var38 : ((com.moonsworth.lunar.client.inactive.mixin.Gui2Handler)var10.method6().get()).method12()) {
                     if (var38.getCondition().applies(var13, var3)) {
                        var38.transform(var1, null, 0.0F);
                     }
                  }

                  UUID var36 = ThreadModuleDump63.method7() == null ? null : ThreadModuleDump63.method7().bridge$getUniqueID();
                  Optional var39 = var14.method37(var19, var36);
                  if (!var39.isEmpty()) {
                     Bridge8Extension3 var40 = (Bridge8Extension3)var39.get();
                     Bridge3_4 var41 = var40.method2();
                     if (var41 != null) {
                        if (var41 instanceof Bridge3Extension_7 var42) {
                           var42.method3(true);
                        }

                        if (var41 instanceof Alert5 var43) {
                           var43.method12();
                        }
                     }

                     ModelRenderConfig var44 = ModelRenderConfig.method6()
                        .method1(var1)
                        .method2(var12.getAnimationProcessor())
                        .method3(var19)
                        .method4(var17)
                        .method5(((com.moonsworth.lunar.client.inactive.mixin.Gui2Handler)var10.method6().get()).method8())
                        .method12();
                     var1.method7(var1x -> GlintTexture.method1(var44), var2x -> {
                        RenderLayerBridge var3x = EmoteModel.method9(var19);
                        Bridge5_16 var4x = var2x.method51();
                        var4x.bridge$pushPose();
                        CosmeticMeshBuilder.method3(var44, var3x, false, false);
                        var4x.bridge$popPose();
                        var2x.method33(var3x);
                     });
                     if (var18 && var20 != null) {
                        byte var45 = 12;
                        Blockoutline.method9(var1, null, var20, 255.0F, 255.0F, 255.0F, var45, 255.0F, 255.0F, 255.0F, var45, LunarRenderTypes.field26);
                        PlayerModelPartMap.method35(var1, var17);
                     }

                     var1.method23();
                     var1.method15();
                     var1.method17();
                     var1.method33();
                     var1.pop();
                  }
               }
            }
         }
      }
   }

   private void method4(AbstractRenderContext var1, OwnedCosmetic var2, CosmeticHologramLegacy var3, float var4, float var5, float var6, float var7, float var8) {
      Bridge5Extension_5 var9 = ThreadModuleDump63.method7();
      UUID var10 = var9 == null ? null : var9.bridge$getUniqueID();
      ResourceLocationBridge var11 = var2.method4(var9);
      Bridge5_12 var12 = ThreadModuleDump63.method3();
      var1.method16();
      var1.method14();
      var1.method22();
      var1.method25(1.0F, 1.0F, 1.0F, 1.0F);
      Bridge8Extension3 var13 = var12.bridge$getTextureManager().method1(var11);
      if (var13 != null) {
         var1.push();
         var1.translate(var6 / 2.0F, var5 + var7 / 2.0F - var8 / 2.0F - 2.0F, 50.0);
         var1.scale(-var8, var8, var8);
         var1.translate(0.0, 0.1, 0.0);
         var1.method4(180.0F, 0.0F, 1.0F, 0.0F);
         var1.method4(35.0F, 0.0F, 1.0F, 0.0F);
         if (var3.RCCHOIOHHOICRRCRHIOCOORRIRIOIR() && var3.HRHIRHROCHOROHHROHOHROIOIORIIR() != 0.0F) {
            var1.method4(var3.HRHIRHROCHOROHHROHOHROIOIORIIR(), 1.0F, 0.0F, 0.0F);
         }

         if (var3.RHCHOROROOORIRCOHHCOOCICCIICHI() && var3.HOOCCOICOORRIIRHIOOOIIIRIRCROH() != 0.0F) {
            var1.method4(var3.HOOCCOICOORRIIRHIOOOIIIRIRCROH(), 0.0F, 1.0F, 0.0F);
         }

         CosmeticModelRenderer.method5(
            var1,
            null,
            null,
            var13,
            var11,
            () -> {
               if (ThreadModuleDump63.MC_VERSION >= 6) {
                  CosmeticManager.field4.method1(var1, 0.0315F, var11, var10);
               } else {
                  ThreadModuleDump63.method3()
                     .bridge$getEntityRenderDispatcher()
                     .bridge$defaultPlayerRenderer()
                     .bridge$getMainModel()
                     .bridge$cloak()
                     .bridge$render(0.0625F, var11);
               }
            }
         );
         var1.pop();
      }

      var1.method15();
      var1.method17();
      var1.method23();
      var1.method33();
   }

   private void method5(AbstractRenderContext var1, OwnedCosmetic var2, CosmeticHologramLegacy var3, float var4, float var5, float var6, float var7, float var8) {
      CosmeticManager var9 = ThreadModuleDump63.method4().method53();
      UUID var10 = ThreadModuleDump63.method7() == null ? null : ThreadModuleDump63.method7().bridge$getUniqueID();
      if (!var9.method36(var2, var10).isEmpty()) {
         var1.method16();
         var1.method14();
         if (Bridge.getMinecraftVersion().method21()) {
            var1.method26();
         }

         var1.method25(1.0F, 1.0F, 1.0F, 1.0F);
         var1.push();
         var1.translate(var6 / 2.0F, var5 + var7 / 2.0F - var8 / 2.0F, 50.0);
         var1.scale(var8, var8, var8);
         var1.translate(0.2, 0.5, 0.0);
         var1.method4(180.0F, 0.0F, 1.0F, 0.0F);
         var1.method4(35.0F, 0.0F, 1.0F, 0.0F);
         if (var3.RCCHOIOHHOICRRCRHIOCOORRIRIOIR() && var3.HRHIRHROCHOROHHROHOHROIOIORIIR() != 0.0F) {
            var1.method4(var3.HRHIRHROCHOROHHROHOHROIOIORIIR(), 1.0F, 0.0F, 0.0F);
         }

         if (var3.RHCHOROROOORIRCOHHCOOCICCIICHI() && var3.HOOCCOICOORRIIRHIOOOIIIRIRCROH() != 0.0F) {
            var1.method4(var3.HOOCCOICOORRIIRHIOOOIIIRIRCROH(), 0.0F, 1.0F, 0.0F);
         }

         ResourceLocationBridge var11 = var2.method4(ThreadModuleDump63.method7());
         if (var2.method2().method9()) {
            Bridge8Extension3 var12 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var11);
            if (var12.method2() instanceof Alert5 var14) {
               var14.method12();
            }
         }

         if (var1.method38()) {
            CosmeticManager.field5.method3(var1.method30(), 0.13F, 0.0625F, var11, true, -1);
         } else {
            CosmeticManager.field5.method1(var1.method31(), 0.13F, 0.0625F, var11);
         }

         var1.pop();
         var1.method15();
         var1.method17();
         var1.method23();
         var1.method33();
         var1.method21();
      }
   }

   private void method6(AbstractRenderContext var1, CosmeticMetadata var2, CosmeticHologramLegacy var3, float var4, float var5, float var6, float var7, float var8) {
      OwnedCosmetic var9 = var2.method4();
      AbstractCosmetic var10 = ThreadModuleDump63.method4().method53().method7(var9.method2());
      if (var10 == null) {
         if (!this.field5) {
            this.field5 = true;
            String var11 = String.format("No corresponding index type \"%s\" found for cosmetic: %s", var9.method2().method5(), var9.method2().getName());
            Inventorymod2.method5(new RuntimeException(var11), "CosmeticEntry");
         }
      } else {
         if (Bridge.getMinecraftVersion().method21()) {
            var1.method26();
         }

         var1.translate(var6 / 2.0F, var7 / 2.0F + var8 / 2.0F, 50.0);
         var1.scale(-var8, var8, 50.0F);
         var1.method4(-35.0F, 0.0F, 1.0F, 0.0F);
         var1.method4(-5.0F, 1.0F, 0.0F, 0.0F);
         if (var3.RCCHOIOHHOICRRCRHIOCOORRIRIOIR() && var3.HRHIRHROCHOROHHROHOHROIOIORIIR() != 0.0F) {
            var1.method4(var3.HRHIRHROCHOROHHROHOHROIOIORIIR(), 1.0F, 0.0F, 0.0F);
         }

         if (var3.RHCHOROROOORIRCOHHCOOCICCIICHI() && var3.HOOCCOICOORRIIRHIOOOIIIRIRCROH() != 0.0F) {
            var1.method4(var3.HOOCCOICOORRIIRHIOOOIIIRIRCROH(), 0.0F, 1.0F, 0.0F);
         }

         ResourceLocationBridge var15 = var10.isDynamic() ? var9.method4(ThreadModuleDump63.method7()) : var10.method3();
         CosmeticManager var12 = ThreadModuleDump63.method4().method53();
         UUID var13 = ThreadModuleDump63.method7() == null ? null : ThreadModuleDump63.method7().bridge$getUniqueID();
         Optional var14 = var12.method37(var15, var13);
         var14.<Bridge3_4>map(Bridge8Extension3::method2).filter(Alert5.class::isInstance).map(Alert5.class::cast).ifPresent(Alert5::restartAnimation);
         var1.method7(var2x -> {
            var2x.push();
            CosmeticModelRenderer.method2(var2x, var2, var10, false);
            var2x.pop();
         }, var3x -> {
            RenderLayerBridge var4x = LunarRenderTypes.field45.get(var15);
            var4x.bridge$setupRenderState();
            CosmeticModelRenderer.method4(var3x, var4x, var2, var10);
            var4x.bridge$clearRenderState();
         });
      }
   }

   private static boolean method7(AxisAlignedBBBridge var0, @Nullable AxisAlignedBBBridge var1) {
      if (var1 == null) {
         return false;
      }

      double var2 = Math.max(
         var0.bridge$getMaxX() - var0.bridge$getMinX(), Math.max(var0.bridge$getMaxY() - var0.bridge$getMinY(), var0.bridge$getMaxZ() - var0.bridge$getMinZ())
      );
      double var4 = Math.max(0.001, var2 * 0.01);
      return Math.abs(var0.bridge$getMinX() - var1.bridge$getMinX()) < var4
         && Math.abs(var0.bridge$getMinY() - var1.bridge$getMinY()) < var4
         && Math.abs(var0.bridge$getMinZ() - var1.bridge$getMinZ()) < var4
         && Math.abs(var0.bridge$getMaxX() - var1.bridge$getMaxX()) < var4
         && Math.abs(var0.bridge$getMaxY() - var1.bridge$getMaxY()) < var4
         && Math.abs(var0.bridge$getMaxZ() - var1.bridge$getMaxZ()) < var4;
   }
}
