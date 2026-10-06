package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.highlight.PlayerRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.cosmetics.gecko.InactiveType;
import com.moonsworth.lunar.client.cosmetics.gecko.FirstPersonArmMode;
import com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Impl2;
import com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.util.ThreadModuleDump25;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.Type.Data;
import com.moonsworth.lunar.client.util.alert.Alert5;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;

public class PlayerModelPartVisibility {
   public PlayerModelPartVisibility() {
      ClientEventBus.method29().method2(PlayerRenderEvent.class, var0 -> {
         if (var0.method2().bridge$isSelf() && method1(var0.method1(), var0.method2(), var0.method3())) {
            var0.setCancelled(true);
         }
      });
      ClientEventBus.method29().method2(KeybindEvent.class, var0 -> {
         ThreadModuleDump25 var1 = ThreadModuleDump25.method4("shield");
         if (var1 != null && var0.method11() == InputActionLegacy.UP) {
            if (var0.method10() == KeyCode.KEY_LBRACKET) {
               var1.method1(false);
            } else if (var0.method10() == KeyCode.KEY_RBRACKET) {
               var1.method1(true);
            } else if (var0.method10() == KeyCode.KEY_COMMA) {
               var1.method2();
            } else if (var0.method10() == KeyCode.KEY_PERIOD) {
               var1.method3();
            }
         }
      });
   }

   public static boolean method1(AbstractRenderContext var0, EntityPlayerBridge var1, boolean var2) {
      boolean var3 = false;
      boolean var4 = var1.bridge$isEmoting();
      List var5 = ThreadModuleDump63.method4().method53().method18(var1.bridge$getUniqueID());
      int var6 = 0;

      for (int var7 = 0; var7 < var5.size(); var7++) {
         CosmeticMetadata var8 = (CosmeticMetadata)var5.get(var7);
         if (var8.method4().method10().isHeldItemCosmetic()) {
            Collections.swap(var5, var7, var6++);
         }
      }

      MixinHelper_6 var17 = (MixinHelper_6)Bridge.method9()
         .bridge$getEntityRenderDispatcher()
         .bridge$getSkinMap()
         .get(ThreadModuleDump63.method7().bridge$getSkinType());
      if (var17 != null) {
         BridgeExtension2_7 var18 = var17.bridge$getMainModel();

         for (CosmeticMetadata var10 : var5) {
            EmoteModel var11 = (EmoteModel)var10.method4();
            MolangFunctionRegistry var12 = ThreadModuleDump63.method4().method76();
            if (var12.method14(var11) && var11.method10().canShowCosmetic() && !var11.method6().isEmpty()) {
               boolean var13 = var10.method6().method6();
               if (var11.method6().get() instanceof Gui2Impl2 var14 && var14.method20() != FirstPersonArmMode.NONE && var14.method24() == InactiveType.NONE) {
                  var0.push();
                  Consumer var19 = var3x -> {
                     var0.scale(-1.0F, 1.0F, -1.0F);
                     var0.translate(0.12F, 1.52F, -0.03F);
                     var0.method5(-1.67F, -180.0F, -175.0F);
                     PlayerModelPartMap.method38(var14, var2, var0, Type.firstPerson(var2), var3x);
                  };
                  RenderContext var16 = method3(var1, var18);
                  method2(var0, var16, var10, var1, var4, var18, var2, var13, null, Data.method2(var1, var10, Type.firstPerson(var2)), var19);
                  var0.pop();
                  if (var11.method10().isHeldItemCosmetic()) {
                     var3 = true;
                     break;
                  }
               }
            }
         }
      }

      var0.method6(var1x -> ThreadModuleDump63.method3().bridge$getTextureManager().bridge$bindTexture(var1.bridge$getLocationSkin()));
      return var3;
   }

   public static void method2(
      AbstractRenderContext var0,
      RenderContext var1,
      CosmeticMetadata var2,
      EntityPlayerBridge var3,
      boolean var4,
      BridgeExtension2_7 var5,
      boolean var6,
      boolean var7,
      @Nullable ItemStackRenderStateBridge var8,
      Data var9,
      Consumer<RenderContext> var10
   ) {
      MolangFunctionRegistry var11 = ThreadModuleDump63.method4().method76();
      if (var2.method4() instanceof EmoteModel var12) {
         if (var11.method14(var12) && !var12.method6().isEmpty() && !var12.method5().isEmpty()) {
            if (var12.method6().get() instanceof Gui2Impl2 var27) {
               if (!var4 || var27.method7() && var12.RHIHHIHIIICHHCRHRCIRROIHOHRIIH() != CosmeticCategoryType.PET) {
                  MolangResourceModel var28 = var12.method5().get();
                  if (var8 != null) {
                     var1.method16(var8.bridge$getLunarItemType());
                     var1.method18(var8.bridge$getLunarItemMaterial());
                  }

                  var28.method1(var1);
                  var28.setLivingAnimations(var12, var12.method3(var6));

                  for (ThreadModuleDump91 var16 : var27.method12()) {
                     if (var16.getCondition().applies(var9)) {
                        var0.method5(var3x -> var16.transform(var0, var3, var3x.method51(), var0.method28()));
                        var0.method6(var3x -> var16.transform(var0, var3, var0.method28()));
                     }
                  }

                  Color var29 = CosmeticLayerRenderer.method4(var12, var3);
                  ResourceLocationBridge var30 = var12.method5().get().method2(var12, var28.method10().getEvaluator());
                  if (var30 != null) {
                     CosmeticManager var17 = ThreadModuleDump63.method4().method53();
                     Optional var18 = var17.method37(var30, var3.bridge$getUniqueID());
                     if (var18.isEmpty()) {
                        return;
                     }

                     Bridge8Extension3 var19 = (Bridge8Extension3)var18.get();
                     Bridge3_4 var20 = var19.method2();
                     if (var20 != null) {
                        if (var20 instanceof Bridge3Extension_7 var21) {
                           var21.method3(true);
                        }

                        if (var20 instanceof Alert5 var31) {
                           var31.method12();
                        }
                     }

                     var0.push();
                     Optional var32 = var28.method1(
                        var12.method5().get().method1(var12, var28.method10().getEvaluator())
                     );
                     if (var32.isEmpty()) {
                        return;
                     }

                     BoneList var22 = (BoneList)var32.get();
                     var22.method2("bipedLeftArm").ifPresent(var0x -> var0x.setHidden(false));
                     var22.method2("bipedRightArm").ifPresent(var0x -> var0x.setHidden(false));
                     var22.method2("bipedHead").ifPresent(var0x -> var0x.setHidden(true));
                     var22.method2("bipedBody").ifPresent(var0x -> var0x.setHidden(true));
                     var22.method2("bipedLeftLeg").ifPresent(var0x -> var0x.setHidden(true));
                     var22.method2("bipedRightLeg").ifPresent(var0x -> var0x.setHidden(true));
                     PlayerModelPartMap.method10(var22);
                     boolean var23 = var7;
                     if (var27.method24() == InactiveType.NONE) {
                        if (var27.method20() == FirstPersonArmMode.SINGLE_ARM) {
                           if (var6 != var23) {
                              return;
                           }
                        } else {
                           var22.method2("bipedLeftArm").ifPresent(var0x -> var0x.setHidden(true));
                        }
                     }

                     boolean var24 = false;
                     if (var12.RHIHHIHIIICHHCRHRCIRROIHOHRIIH().isHeldItemCosmetic()) {
                        if (var8 instanceof ItemStackBridge var25) {
                           var24 = var25.bridge$isItemEnchanted();
                        } else if (var8 != null) {
                           var24 = Arrays.stream(var8.bridge$layers())
                              .anyMatch(var0x -> var0x.bridge$foilType() != com.moonsworth.lunar.bridge.MixinHelper.Type.NONE);
                        }
                     }

                     if (var24) {
                        ItemGlintRenderEvent var33 = (ItemGlintRenderEvent)ClientEventBus.method29()
                           .method12(
                              ItemGlintRenderEvent.class,
                              () -> new ItemGlintRenderEvent(
                                 com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent.Type.ITEM, null, null, null, null, var0
                              )
                           );
                        if (var33 != null && var33.isCancelled()) {
                           var24 = false;
                        }
                     }

                     RenderPass var34 = var8 != null && var24 ? RenderPass.NORMAL_GLINT : RenderPass.NORMAL;
                     ModelRenderConfig var26 = ModelRenderConfig.method6()
                        .method1(var0)
                        .method2(var28.getAnimationProcessor())
                        .method3(var30)
                        .method4(var22)
                        .method8(var34)
                        .method9(var29)
                        .method5(var27.method8())
                        .method11(var6 ? 1 : 0)
                        .method12();
                     if (var6 && var27.method24() == InactiveType.NONE) {
                        var0.scale(-1.0F, 1.0F, 1.0F);
                        var26.method23(true);
                     }

                     var10.accept(var28.method10());
                     PlayerModelPartMap.method1(var26);
                     var0.pop();
                  }
               }
            }
         }
      }
   }

   protected static RenderContext method3(EntityPlayerBridge var0, BridgeExtension2_7 var1) {
      if (ThreadModuleDump63.MC_VERSION < 26) {
         return var0.method2() ? RenderContext.method8((Bridge5_11)var0, var1) : RenderContext.method7((Bridge5_11)var0, var1);
      }

      if (var0.method2()) {
         return RenderContext.method8(null, var1);
      }

      Bridge6_10 var2 = (Bridge6_10)ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(var0.bridge$getUniqueID()).orElse(null);
      return RenderContext.method7((Bridge5_11)var2, var1);
   }
}
