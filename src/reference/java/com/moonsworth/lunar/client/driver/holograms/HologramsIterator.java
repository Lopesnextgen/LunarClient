package com.moonsworth.lunar.client.driver.holograms;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.AsyncTextureBridge;
import com.moonsworth.lunar.bridge.TextureBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
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
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.GeckolibCosmeticManager;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockGeometry;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.render.texture.AnimatedTexture;
import java.util.Optional;
import java.util.UUID;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.driver.hologram.CosmeticHologram;
import com.moonsworth.lunar.client.driver.hologram.MarkerModelRenderer;

public class HologramsIteratorLegacy implements MarkerModelRenderer<CosmeticHologram> {
   private static final int field1 = 3;
   private static final int field2 = 40;
   private static final double field3 = 0.01;
   public static boolean field4 = false;
   private boolean field5;

   public HologramsIteratorLegacy() {
   }

   public void method1(CosmeticHologram holograms2impl1) {
      holograms2impl1.init();
   }

   public void method2(AbstractRenderContext bridgeextension_91, CosmeticHologram holograms2impl2, MarkerModel.Data5 data53) {
      field4 = true;
      CosmeticMetadata gui2handler34 = holograms2impl2.method8();
      if (gui2handler34 != null) {
         OwnedCosmetic gui2handler5 = gui2handler34.method4();
         float value6 = (float)holograms2impl2.getX();
         float value7 = (float)holograms2impl2.getY();
         float value8 = holograms2impl2.getZoom();
         float value9 = holograms2impl2.method14().getHeight() * value8;
         float value10 = holograms2impl2.method14().getWidth();
         float value11 = holograms2impl2.method14().getHeight();
         bridgeextension_91.push();
         if (Ref.method1()) {
            bridgeextension_91.method35(0.0, value10, value11, 0.0, 21000.0, 1000.0);
         } else {
            bridgeextension_91.method35(0.0, value10, value11, 0.0, 1000.0, Ref.MC_VERSION >= 17 ? 21000.0 : 3000.0);
         }

         if (Ref.MC_VERSION < 8) {
            bridgeextension_91.method27(0, 0, (int)value10, (int)value11);
         }

         bridgeextension_91.method22();
         bridgeextension_91.method18();
         if (Bridge.getMinecraftVersion().method21()) {
            bridgeextension_91.method26();
         }

         bridgeextension_91.method25(1.0F, 1.0F, 1.0F, 1.0F);
         if (gui2handler5 instanceof EmoteModel) {
            this.method3(bridgeextension_91, holograms2impl2, gui2handler34, value6, value7, value10, value11, value9);
         } else if (gui2handler5.method10() == CosmeticCategoryType.CLOAK) {
            this.method4(bridgeextension_91, gui2handler5, holograms2impl2, value6, value7, value10, value11, value9);
         } else if (gui2handler5.method10() == CosmeticCategoryType.WINGS) {
            this.method5(bridgeextension_91, gui2handler5, holograms2impl2, value6, value7, value10, value11, value9);
         } else {
            this.method6(bridgeextension_91, gui2handler34, holograms2impl2, value6, value7, value10, value11, value9);
         }

         bridgeextension_91.method23();
         bridgeextension_91.method6(arg1x -> {
            bridgeextension_91.method28(Bridge.method22().method5());
            bridgeextension_91.method13();
            bridgeextension_91.method28(Bridge.method22().method6());
         });
         bridgeextension_91.method41();
         bridgeextension_91.pop();
      }

      field4 = false;
   }

   private void method3(AbstractRenderContext bridgeextension_91, CosmeticHologram holograms2impl2, CosmeticMetadata gui2handler33, float value4, float value5, float value6, float value7, float value8) {
      byte number9 = -1;
      if (gui2handler33.method4() instanceof EmoteModel gui2iterator10) {
         GeckolibCosmeticManager fogiterator33 = Ref.method4().method76();
         if (fogiterator33.method14(gui2iterator10)) {
            if (!gui2iterator10.method6().isEmpty() && !gui2iterator10.method5().isEmpty()) {
               com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel holograms12 = (com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel)gui2iterator10.method5()
                  .get();
               RenderContext fov1013 = RenderContext.method11(HologramsIterator2.method14());
               CosmeticManager holograms1214 = Ref.method4().method53();
               holograms1214.method22(gui2iterator10.method9()).ifPresentOrElse(fov1013::method24, () -> fov1013.method24(gui2handler33));
               holograms12.method1(fov1013);
               holograms12.setLivingAnimations(gui2iterator10, gui2iterator10.method4());
               Evaluator evaluator15 = holograms12.method10().getEvaluator();
               Optional optional16 = holograms12.method1(holograms12.method1(gui2iterator10, evaluator15));
               if (!optional16.isEmpty()) {
                  BedrockGeometry rewindhandlers2_217 = (BedrockGeometry)optional16.get();
                  boolean flag18 = Ref.method4().method40().method73().isValid()
                     && (Boolean)Ref.method4().method40().method73().method16().get();
                  if (flag18) {
                     LcuiScreen.method97(bridgeextension_91, value4, value5 + value7 / 2.0F - 0.5F, value6, 1.0F, 553648127);
                     LcuiScreen.method97(bridgeextension_91, value4 + value6 / 2.0F - 0.5F, value5, 1.0F, value7, 553648127);
                  }

                  ResourceLocationBridge horsestats1419 = PlayerModelPartMap.method24(gui2iterator10, holograms12, null, evaluator15);
                  bridgeextension_91.push();
                  bridgeextension_91.translate(value6 / 2.0F, value7 / 2.0F, 0.0);
                  bridgeextension_91.scale(-1.0F, -1.0F, -1.0F);
                  bridgeextension_91.method4(15.5F, -1.0F, 0.0F, 0.0F);
                  bridgeextension_91.method4(35.0F, 0.0F, 1.0F, 0.0F);
                  if (gui2iterator10.method10() == CosmeticCategoryType.SHIELDS) {
                     bridgeextension_91.method4(90.0F, 0.0F, 1.0F, 0.0F);
                     bridgeextension_91.method4(90.0F, 1.0F, 0.0F, 0.0F);
                     value8 *= 0.7F;
                  }

                  rewindhandlers2_217.method2("bipedLeftArm").ifPresent(arg0 -> arg0.setHidden(false));
                  rewindhandlers2_217.method2("bipedRightArm").ifPresent(arg0 -> arg0.setHidden(false));
                  rewindhandlers2_217.method2("bipedHead").ifPresent(arg0 -> arg0.setHidden(false));
                  rewindhandlers2_217.method2("bipedBody").ifPresent(arg0 -> arg0.setHidden(false));
                  rewindhandlers2_217.method2("bipedLeftLeg").ifPresent(arg0 -> arg0.setHidden(false));
                  rewindhandlers2_217.method2("bipedRightLeg").ifPresent(arg0 -> arg0.setHidden(false));
                  AxisAlignedBBBridge horsestats1220 = holograms2impl2.method9();
                  if (horsestats1220 == null) {
                     horsestats1220 = rewindhandlers2_217.method1();
                     int number21 = EventTick.field1;
                     if (horsestats1220 != null && (holograms2impl2.method22() == 0 || number21 != holograms2impl2.method31())) {
                        holograms2impl2.method15(number21);
                        boolean flag22 = method7(horsestats1220, holograms2impl2.method10());
                        holograms2impl2.method12(horsestats1220);
                        holograms2impl2.method13(holograms2impl2.method22() + 1);
                        holograms2impl2.method14(flag22 ? holograms2impl2.method23() + 1 : 0);
                        if (holograms2impl2.method23() >= 3 || holograms2impl2.method22() >= 40) {
                           holograms2impl2.method11(horsestats1220);
                        }
                     }
                  }

                  if (horsestats1220 != null) {
                     float value34 = (float)(horsestats1220.bridge$getMaxX() - horsestats1220.bridge$getMinX());
                     float value37 = (float)(horsestats1220.bridge$getMaxY() - horsestats1220.bridge$getMinY());
                     float value23 = (float)(horsestats1220.bridge$getMaxZ() - horsestats1220.bridge$getMinZ());
                     float value24 = value34 * 0.725F + value23 * 0.725F;
                     float value25 = value37 + value34 * 0.275F + value23 * 0.275F;
                     float value26 = value6 / (value24 * value8);
                     float value27 = value7 / (value25 * value8);
                     float value28 = Math.min(value26, value27);
                     float value32 = Math.min(value8, value8 * value28);
                     value8 = Math.max(value32, 0.1F);
                     float value29 = (float)(horsestats1220.bridge$getMinX() + value34 / 2.0F) * value8;
                     float value30 = (float)(horsestats1220.bridge$getMinY() + value37 / 2.0F) * value8;
                     float value31 = (float)(horsestats1220.bridge$getMinZ() + value23 / 2.0F) * value8;
                     bridgeextension_91.translate(-value29, -value30, -value31);
                  }

                  bridgeextension_91.scale(value8, value8, value8);
                  bridgeextension_91.method16();
                  bridgeextension_91.method14();
                  bridgeextension_91.method18(number9);
                  bridgeextension_91.method22();
                  bridgeextension_91.method26();

                  for (ThreadModuleDump91 threadmoduledump9138 : ((com.moonsworth.lunar.client.inactive.mixin.Gui2Handler)gui2iterator10.method6().get()).method12()) {
                     if (threadmoduledump9138.getCondition().applies(fov1013, gui2handler33)) {
                        threadmoduledump9138.transform(bridgeextension_91, null, 0.0F);
                     }
                  }

                  UUID uuid36 = Ref.method7() == null ? null : Ref.method7().bridge$getUniqueID();
                  Optional optional39 = holograms1214.method37(horsestats1419, uuid36);
                  if (!optional39.isEmpty()) {
                     Bridge8Extension3 bridge8extension340 = (Bridge8Extension3)optional39.get();
                     TextureBridge bridge3_441 = bridge8extension340.method2();
                     if (bridge3_441 != null) {
                        if (bridge3_441 instanceof AsyncTextureBridge bridge3extension_742) {
                           bridge3extension_742.method3(true);
                        }

                        if (bridge3_441 instanceof AnimatedTexture alert543) {
                           alert543.method12();
                        }
                     }

                     ModelRenderConfig fov844 = ModelRenderConfig.method6()
                        .method1(bridgeextension_91)
                        .method2(holograms12.getAnimationProcessor())
                        .method3(horsestats1419)
                        .method4(rewindhandlers2_217)
                        .method5(((com.moonsworth.lunar.client.inactive.mixin.Gui2Handler)gui2iterator10.method6().get()).method8())
                        .method12();
                     bridgeextension_91.method7(arg1x -> GlintTexture.method1(fov844), arg2x -> {
                        RenderTypeBridge bridge203x = EmoteModel.method9(horsestats1419);
                        Bridge5_16 bridge5_164x = arg2x.method51();
                        bridge5_164x.bridge$pushPose();
                        CosmeticMeshBuilder.method3(fov844, bridge203x, false, false);
                        bridge5_164x.bridge$popPose();
                        arg2x.method33(bridge203x);
                     });
                     if (flag18 && horsestats1220 != null) {
                        byte number45 = 12;
                        Blockoutline.method9(bridgeextension_91, null, horsestats1220, 255.0F, 255.0F, 255.0F, number45, 255.0F, 255.0F, 255.0F, number45, LunarRenderTypes.field26);
                        PlayerModelPartMap.method35(bridgeextension_91, rewindhandlers2_217);
                     }

                     bridgeextension_91.method23();
                     bridgeextension_91.method15();
                     bridgeextension_91.method17();
                     bridgeextension_91.method33();
                     bridgeextension_91.pop();
                  }
               }
            }
         }
      }
   }

   private void method4(AbstractRenderContext bridgeextension_91, OwnedCosmetic gui2handler2, CosmeticHologram holograms2impl3, float value4, float value5, float value6, float value7, float value8) {
      Bridge5Extension_5 bridge5extension_59 = Ref.method7();
      UUID uuid10 = bridge5extension_59 == null ? null : bridge5extension_59.bridge$getUniqueID();
      ResourceLocationBridge horsestats1411 = gui2handler2.method4(bridge5extension_59);
      MinecraftBridge bridge5_1212 = Ref.method3();
      bridgeextension_91.method16();
      bridgeextension_91.method14();
      bridgeextension_91.method22();
      bridgeextension_91.method25(1.0F, 1.0F, 1.0F, 1.0F);
      Bridge8Extension3 bridge8extension313 = bridge5_1212.bridge$getTextureManager().method1(horsestats1411);
      if (bridge8extension313 != null) {
         bridgeextension_91.push();
         bridgeextension_91.translate(value6 / 2.0F, value5 + value7 / 2.0F - value8 / 2.0F - 2.0F, 50.0);
         bridgeextension_91.scale(-value8, value8, value8);
         bridgeextension_91.translate(0.0, 0.1, 0.0);
         bridgeextension_91.method4(180.0F, 0.0F, 1.0F, 0.0F);
         bridgeextension_91.method4(35.0F, 0.0F, 1.0F, 0.0F);
         if (holograms2impl3.method17() && holograms2impl3.method2() != 0.0F) {
            bridgeextension_91.method4(holograms2impl3.method2(), 1.0F, 0.0F, 0.0F);
         }

         if (holograms2impl3.method16() && holograms2impl3.method1() != 0.0F) {
            bridgeextension_91.method4(holograms2impl3.method1(), 0.0F, 1.0F, 0.0F);
         }

         CosmeticModelRenderer.method5(
            bridgeextension_91,
            null,
            null,
            bridge8extension313,
            horsestats1411,
            () -> {
               if (Ref.MC_VERSION >= 6) {
                  CosmeticManager.field4.method1(bridgeextension_91, 0.0315F, horsestats1411, uuid10);
               } else {
                  Ref.method3()
                     .bridge$getEntityRenderDispatcher()
                     .bridge$defaultPlayerRenderer()
                     .bridge$getMainModel()
                     .bridge$cloak()
                     .bridge$render(0.0625F, horsestats1411);
               }
            }
         );
         bridgeextension_91.pop();
      }

      bridgeextension_91.method15();
      bridgeextension_91.method17();
      bridgeextension_91.method23();
      bridgeextension_91.method33();
   }

   private void method5(AbstractRenderContext bridgeextension_91, OwnedCosmetic gui2handler2, CosmeticHologram holograms2impl3, float value4, float value5, float value6, float value7, float value8) {
      CosmeticManager holograms129 = Ref.method4().method53();
      UUID uuid10 = Ref.method7() == null ? null : Ref.method7().bridge$getUniqueID();
      if (!holograms129.method36(gui2handler2, uuid10).isEmpty()) {
         bridgeextension_91.method16();
         bridgeextension_91.method14();
         if (Bridge.getMinecraftVersion().method21()) {
            bridgeextension_91.method26();
         }

         bridgeextension_91.method25(1.0F, 1.0F, 1.0F, 1.0F);
         bridgeextension_91.push();
         bridgeextension_91.translate(value6 / 2.0F, value5 + value7 / 2.0F - value8 / 2.0F, 50.0);
         bridgeextension_91.scale(value8, value8, value8);
         bridgeextension_91.translate(0.2, 0.5, 0.0);
         bridgeextension_91.method4(180.0F, 0.0F, 1.0F, 0.0F);
         bridgeextension_91.method4(35.0F, 0.0F, 1.0F, 0.0F);
         if (holograms2impl3.method17() && holograms2impl3.method2() != 0.0F) {
            bridgeextension_91.method4(holograms2impl3.method2(), 1.0F, 0.0F, 0.0F);
         }

         if (holograms2impl3.method16() && holograms2impl3.method1() != 0.0F) {
            bridgeextension_91.method4(holograms2impl3.method1(), 0.0F, 1.0F, 0.0F);
         }

         ResourceLocationBridge horsestats1411 = gui2handler2.method4(Ref.method7());
         if (gui2handler2.method2().method9()) {
            Bridge8Extension3 bridge8extension312 = Ref.method3().bridge$getTextureManager().bridge$getTexture(horsestats1411);
            if (bridge8extension312.method2() instanceof AnimatedTexture alert514) {
               alert514.method12();
            }
         }

         if (bridgeextension_91.method38()) {
            CosmeticManager.field5.method3(bridgeextension_91.method30(), 0.13F, 0.0625F, horsestats1411, true, -1);
         } else {
            CosmeticManager.field5.method1(bridgeextension_91.method31(), 0.13F, 0.0625F, horsestats1411);
         }

         bridgeextension_91.pop();
         bridgeextension_91.method15();
         bridgeextension_91.method17();
         bridgeextension_91.method23();
         bridgeextension_91.method33();
         bridgeextension_91.method21();
      }
   }

   private void method6(AbstractRenderContext bridgeextension_91, CosmeticMetadata gui2handler32, CosmeticHologram holograms2impl3, float value4, float value5, float value6, float value7, float value8) {
      OwnedCosmetic gui2handler9 = gui2handler32.method4();
      AbstractCosmetic moduletype310 = Ref.method4().method53().method7(gui2handler9.method2());
      if (moduletype310 == null) {
         if (!this.field5) {
            this.field5 = true;
            String text11 = String.format("No corresponding index type \"%s\" found for cosmetic: %s", gui2handler9.method2().method5(), gui2handler9.method2().getName());
            CrashReporter.method5(new RuntimeException(text11), "CosmeticEntry");
         }
      } else {
         if (Bridge.getMinecraftVersion().method21()) {
            bridgeextension_91.method26();
         }

         bridgeextension_91.translate(value6 / 2.0F, value7 / 2.0F + value8 / 2.0F, 50.0);
         bridgeextension_91.scale(-value8, value8, 50.0F);
         bridgeextension_91.method4(-35.0F, 0.0F, 1.0F, 0.0F);
         bridgeextension_91.method4(-5.0F, 1.0F, 0.0F, 0.0F);
         if (holograms2impl3.method17() && holograms2impl3.method2() != 0.0F) {
            bridgeextension_91.method4(holograms2impl3.method2(), 1.0F, 0.0F, 0.0F);
         }

         if (holograms2impl3.method16() && holograms2impl3.method1() != 0.0F) {
            bridgeextension_91.method4(holograms2impl3.method1(), 0.0F, 1.0F, 0.0F);
         }

         ResourceLocationBridge horsestats1415 = moduletype310.isDynamic() ? gui2handler9.method4(Ref.method7()) : moduletype310.method3();
         CosmeticManager holograms1212 = Ref.method4().method53();
         UUID uuid13 = Ref.method7() == null ? null : Ref.method7().bridge$getUniqueID();
         Optional optional14 = holograms1212.method37(horsestats1415, uuid13);
         optional14.<TextureBridge>map(Bridge8Extension3::method2).filter(AnimatedTexture.class::isInstance).map(AnimatedTexture.class::cast).ifPresent(AnimatedTexture::method12);
         bridgeextension_91.method7(arg2x -> {
            arg2x.push();
            CosmeticModelRenderer.method2(arg2x, gui2handler32, moduletype310, false);
            arg2x.pop();
         }, arg3x -> {
            RenderTypeBridge bridge204x = LunarRenderTypes.field45.get(horsestats1415);
            bridge204x.bridge$setupRenderState();
            CosmeticModelRenderer.method4(arg3x, bridge204x, gui2handler32, moduletype310);
            bridge204x.bridge$clearRenderState();
         });
      }
   }

   private static boolean method7(AxisAlignedBBBridge horsestats120, @Nullable AxisAlignedBBBridge horsestats121) {
      if (horsestats121 == null) {
         return false;
      }

      double value2 = Math.max(
         horsestats120.bridge$getMaxX() - horsestats120.bridge$getMinX(), Math.max(horsestats120.bridge$getMaxY() - horsestats120.bridge$getMinY(), horsestats120.bridge$getMaxZ() - horsestats120.bridge$getMinZ())
      );
      double value4 = Math.max(0.001, value2 * 0.01);
      return Math.abs(horsestats120.bridge$getMinX() - horsestats121.bridge$getMinX()) < value4
         && Math.abs(horsestats120.bridge$getMinY() - horsestats121.bridge$getMinY()) < value4
         && Math.abs(horsestats120.bridge$getMinZ() - horsestats121.bridge$getMinZ()) < value4
         && Math.abs(horsestats120.bridge$getMaxX() - horsestats121.bridge$getMaxX()) < value4
         && Math.abs(horsestats120.bridge$getMaxY() - horsestats121.bridge$getMaxY()) < value4
         && Math.abs(horsestats120.bridge$getMaxZ() - horsestats121.bridge$getMaxZ()) < value4;
   }
}
