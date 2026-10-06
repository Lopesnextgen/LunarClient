package com.moonsworth.lunar.client.cosmetics;

import com.eliotlash.molang.ast.Evaluator;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent.Type;
import com.moonsworth.lunar.client.cosmetics.gecko.InactiveType;
import com.moonsworth.lunar.client.cosmetics.gecko.PlayerModelType;
import com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Impl2;
import com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.ichor.Annotation2;
import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.cosmetics.emote.CosmeticMeshBuilder;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.emote.RenderEntityHandle;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;

public class CosmeticLayerRendererImpl extends CosmeticLayerRenderer {
   private static final Table<UUID, ResourceLocationBridge, ElytraTextureLoaderLegacy> field4 = HashBasedTable.create();
   public static final String field5 = "Could not render cosmetic \"%s\". Check earlier logs for the error that occured while loading.";
   private static Set<String> field6 = new HashSet<>();

   public static void method1(String var0) {
      if (!field6.contains(var0)) {
         Slayer.method7("Could not render cosmetic \"%s\". Check earlier logs for the error that occured while loading.", new Object[]{var0});
         field6.add(var0);
      }
   }

   public CosmeticLayerRendererImpl(CosmeticManager var1) {
      super(var1);
   }

   public void method2(
      BridgeExtension3_5 var1, Bridge5_11 var2, BridgeExtension2_7 var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10
   ) {
      if (this.shouldRender(var2)) {
         boolean var11 = ThreadModuleDump63.method4().method45().method9(var2);
         List var12 = this.method9().method18(var2.bridge$getUniqueID());
         AtomicReference var13 = new AtomicReference(null);

         for (CosmeticMetadata var15 : var12) {
            if (this.method4(var2, var15.method4())) {
               EmoteModel var16 = (EmoteModel)var15.method4();
               CosmeticCategoryType var17 = var16.method10();
               if (var17.canShowCosmetic() && var17 != CosmeticCategoryType.COMPANION) {
                  this.method3(var1, var15, var3, var2, var11, var6, var13);
                  if (var15.method4().method22()) {
                     DriverViewLegacy.method21().method14().method3(var1, var15.method4(), () -> this.method3(var1, var15, var3, var2, var11, var6, var13));
                  }
               }
            }
         }
      }
   }

   private void method3(
      AbstractRenderContext var1, CosmeticMetadata var2, BridgeExtension2_7 var3, EntityPlayerBridge var4, boolean var5, float var6, AtomicReference<CosmeticMetadata> var7
   ) {
      EmoteModel var8 = (EmoteModel)var2.method4();
      MolangFunctionRegistry var9 = ThreadModuleDump63.method4().method76();
      if (var9.method14(var8)) {
         Gui2Handler var10 = var8.method6().get();
         if (!var8.method6().isEmpty()) {
            if (!var5 || var10.method7() && var8.method10() != CosmeticCategoryType.PET) {
               if (!var8.method5().isEmpty()) {
                  if (var8.method10().isHeldItemCosmetic() && var10 instanceof Gui2Impl2 var11) {
                     boolean var12 = var8.method8(var4.bridge$getMainHandItemRenderState(), var4);
                     boolean var13 = false;
                     if (var12) {
                        var7.set(var2);
                     }

                     if (ThreadModuleDump63.MC_VERSION >= 5 && var11.method24() != InactiveType.NONE) {
                        var13 = var8.method8(var4.bridge$getOffHandItemRenderState(), var4);
                     }

                     if (!var12 && !var13) {
                        return;
                     }

                     if (var12) {
                        this.method4(var1, var4, var4.bridge$getMainHandItemRenderState(), var3, var2, var8, var10, var5, false, var6);
                     }

                     if (var13) {
                        this.method4(var1, var4, var4.bridge$getOffHandItemRenderState(), var3, var2, var8, var10, var5, true, var6);
                     }
                  } else {
                     this.method4(var1, var4, var4.bridge$getMainHandItemRenderState(), var3, var2, var8, var10, var5, false, var6);
                  }
               }
            }
         }
      }
   }

   private void method4(
      AbstractRenderContext var1,
      EntityPlayerBridge var2,
      ItemStackRenderStateBridge var3,
      BridgeExtension2_7 var4,
      CosmeticMetadata var5,
      EmoteModel var6,
      Gui2Handler var7,
      boolean var8,
      boolean var9,
      float var10
   ) {
      MolangResourceModel var11 = var6.method5().get();
      RenderContext var12 = this.method8(var2, var4);
      var12.method24(var5);
      if (var3 != null) {
         var12.method16(var3.bridge$getLunarItemType());
         var12.method18(var3.bridge$getLunarItemMaterial());
      }

      var11.method1(var12);
      this.field3 = var11.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator();
      Optional var13 = var11.method1(var11.method1(var6, this.field3));
      if (!var13.isEmpty()) {
         BoneList var14 = (BoneList)var13.get();
         var11.setLivingAnimations(var6, var6.getName().hashCode());
         this.field3 = var11.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator();
         if (this.field3 == null) {
            var11.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().setEvaluator(Evaluator.getGlobalEvaluator());
            this.field3 = var11.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator();
         }

         var1.push();
         var1.method14();
         var1.method4(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA, BridgeType2_9.GL_ONE, BridgeType2_9.GL_ZERO);
         ResourceLocationBridge var15 = var11.method2(var6, this.field3);
         if (var7.method11() == PlayerModelType.MINIME) {
            var15 = method8(var2, var15);
         }

         Optional var16 = this.method9().method37(var15, var2.bridge$getUniqueID());
         if (!var16.isEmpty()) {
            Bridge8Extension3 var17 = (Bridge8Extension3)var16.get();
            Bridge3_4 var18 = var17.method2();
            if (var18 != null) {
               if (var18 instanceof Bridge3Extension_7 var19) {
                  var19.method3(true);
               }

               if (var18 instanceof Alert5 var22) {
                  var22.method12();
               }
            }

            boolean var23 = var6.RHIHHIHIIICHHCRHRCIRROIHOHRIIH().isHeldItemCosmetic()
               && var3 instanceof ItemStackBridge var20
               && var20.bridge$isItemEnchanted();
            if (var23) {
               ItemGlintRenderEvent var24 = (ItemGlintRenderEvent)ClientEventBus.method29()
                  .method12(ItemGlintRenderEvent.class, () -> new ItemGlintRenderEvent(Type.ITEM, null, null, null, null, var1));
               if (var24 != null && var24.isCancelled()) {
                  var23 = false;
               }
            }

            RenderPass var25 = var23 ? RenderPass.NORMAL_GLINT : RenderPass.NORMAL;
            ModelRenderConfig var21 = ModelRenderConfig.method6()
               .method1(var1)
               .method2(var11.getAnimationProcessor())
               .method3(var15)
               .method4(var14)
               .method8(var25)
               .method5(var7.method8())
               .method12();
            PlayerModelPartMap.method6(var1, var5, var6, var4, var2, var14, var8, var10, true, var9, var12, var21::method23);
            PlayerModelPartMap.method1(var21);
            var1.method15();
            var1.pop();
         }
      }
   }

   @Annotation2(min = 6)
   public void method5(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, int var4, int var5) {
      Optional var6 = this.method16(var2, var4);
      if (!var6.isEmpty()) {
         EmoteModel var7 = (EmoteModel)((CosmeticMetadata)var6.get()).method4();
         if (var7.RHIHHIHIIICHHCRHRCIRROIHOHRIIH() != CosmeticCategoryType.COMPANION && var7.RHIHHIHIIICHHCRHRCIRROIHOHRIIH().canShowCosmetic()) {
            MolangFunctionRegistry var8 = ThreadModuleDump63.method4().method76();
            if (var8.method14(var7)) {
               Gui2Handler var9 = var7.method6().get();
               if (!var7.method6().isEmpty()) {
                  boolean var10 = var2.bridge$isEmoting();
                  if (!var10 || var9.method7() && var7.RHIHHIHIIICHHCRHRCIRROIHOHRIIH() != CosmeticCategoryType.PET) {
                     if (!var7.method5().isEmpty()) {
                        if (var9 instanceof Gui2Impl2 var11 && var11.method24() != InactiveType.NONE) {
                           RenderEntityHandle var12 = (RenderEntityHandle)this.field1.get(var4);
                           ItemStackRenderStateBridge var13;
                           if (var12.method2()) {
                              var13 = var2.bridge$getOffHandItemRenderState();
                           } else {
                              var13 = var2.bridge$getMainHandItemRenderState();
                           }

                           this.method6(var1, var2, var13, var3, (CosmeticMetadata)var6.get(), var7, var9, var10, var12.method2(), var4, var5);
                        } else {
                           this.method6(
                              var1, var2, var2.bridge$getMainHandItemRenderState(), var3, (CosmeticMetadata)var6.get(), var7, var9, var10, false, var4, var5
                           );
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Annotation2(min = 6)
   private void method6(
      BridgeExtension2_11 var1,
      EntityPlayerBridge var2,
      ItemStackRenderStateBridge var3,
      BridgeExtension2_7 var4,
      CosmeticMetadata var5,
      EmoteModel var6,
      Gui2Handler var7,
      boolean var8,
      boolean var9,
      int var10,
      int var11
   ) {
      var1.push();
      MolangResourceModel var12 = var6.method5().get();
      RenderContext var13 = this.method8(var2, var4);
      var13.method24(var5);
      boolean var14 = var6.RHIHHIHIIICHHCRHRCIRROIHOHRIIH().isHeldItemCosmetic();
      if (var14 && var3 != null) {
         var13.method16(var3.bridge$getLunarItemType());
         var13.method18(var3.bridge$getLunarItemMaterial());
         var13.method20(var3);
      }

      boolean var15 = var14 && var9;
      var12.method1(var13);
      var12.setLivingAnimations(var6, var6.method3(var15));
      this.field3 = var12.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator();
      Optional var16 = var12.method1(var12.method1(var6, var12.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator()));
      if (var16.isEmpty()) {
         var1.pop();
      } else {
         BoneList var17 = (BoneList)var16.get();
         int var18 = var6.getName().hashCode();
         var12.setLivingAnimations(var6, var15 ? ~var18 : var18);
         this.field3 = var12.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator();
         if (this.field3 == null) {
            var12.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().setEvaluator(Evaluator.getGlobalEvaluator());
            this.field3 = var12.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator();
         }

         Color var19;
         if (ThreadModuleDump63.MC_VERSION >= 39 && var1.isOutlineBufferSource()) {
            var19 = new Color(var11 >> 16 & 0xFF, var11 >> 8 & 0xFF, var11 & 0xFF, var11 >> 24 & 0xFF);
         } else {
            var19 = method4(var6, var2);
         }

         Optional var20 = this.method4(var2, var10);
         if (var20.isPresent()) {
            ResourceLocationBridge var21 = (ResourceLocationBridge)var20.get();
            if (var7.method11() == PlayerModelType.MINIME) {
               var21 = method8(var2, var21);
            }

            Optional var22 = this.method9().method37((ResourceLocationBridge)var20.get(), var2.bridge$getUniqueID());
            if (var22.isEmpty()) {
               return;
            }

            Bridge8Extension3 var23 = (Bridge8Extension3)var22.get();
            Bridge3_4 var24 = var23.method2();
            if (var24 != null) {
               if (var24 instanceof Bridge3Extension_7 var25) {
                  var25.method3(true);
               }

               if (var24 instanceof Alert5 var27) {
                  var27.method12();
               }
            }

            boolean var28 = false;
            if (var6.RHIHHIHIIICHHCRHRCIRROIHOHRIIH().isHeldItemCosmetic()) {
               if (var3 instanceof ItemStackBridge var26) {
                  var28 = var26.bridge$isItemEnchanted();
               } else {
                  var28 = Arrays.stream(var3.bridge$layers()).anyMatch(var0 -> var0.bridge$foilType() != com.moonsworth.lunar.bridge.MixinHelper.Type.NONE);
               }
            }

            if (var28) {
               ItemGlintRenderEvent var29 = (ItemGlintRenderEvent)ClientEventBus.method29()
                  .method12(ItemGlintRenderEvent.class, () -> new ItemGlintRenderEvent(Type.ITEM, null, null, null, null, var1));
               if (var29 != null && var29.isCancelled()) {
                  var28 = false;
               }
            }

            if (var28 && ThreadModuleDump63.method3().bridge$getItemRenderer() != null) {
               ThreadModuleDump63.method3()
                  .bridge$getItemRenderer()
                  .bridge$setCurrentTransform(
                     var9
                        ? com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type.THIRD_PERSON_LEFT_HAND
                        : com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type.THIRD_PERSON_RIGHT_HAND
                  );
            }

            ModelRenderConfig var30 = ModelRenderConfig.method6()
               .method1(var1)
               .method2(var12.getAnimationProcessor())
               .method3(var21)
               .method4(var17)
               .method8(var28 ? RenderPass.NORMAL_GLINT : RenderPass.NORMAL)
               .method9(var19)
               .method5(var7.method8())
               .method11(var9 ? 1 : 0)
               .method12();
            PlayerModelPartMap.method6(var1, var5, var6, var4, var2, var17, var8, var1.method28(), false, var9, var13, var30::method23);
            if (!var6.CIIOOHOOICHIIHIICIOROCRCCHIOHO()) {
               PlayerModelPartMap.method1(var30);
            } else {
               this.method7(var30);
               DriverViewLegacy.method21().method14().method3(var1, var6, () -> this.method7(var30));
            }
         }

         var1.pop();
      }
   }

   @Annotation2(min = 6)
   private void method7(ModelRenderConfig var1) {
      RenderLayerBridge var2 = EmoteModel.method9(var1.getTexture());
      BridgeExtension2_11 var3 = var1.method7().method30();
      var3.push();
      CosmeticMeshBuilder.method3(var1, var2, true, false);
      var3.pop();
   }

   public static ResourceLocationBridge method8(EntityPlayerBridge var0, ResourceLocationBridge var1) {
      if (!var0.bridge$isSkinTextureUploaded()) {
         return var1;
      }

      UUID var2 = var0.bridge$getUniqueID();
      ElytraTextureLoaderLegacy var3 = (ElytraTextureLoaderLegacy)field4.get(var2, var1);
      if (var3 != null && var3.isDeleted()) {
         field4.remove(var2, var1);
         var3 = null;
      }

      if (var3 == null) {
         String var4 = "_minime_" + UUID.randomUUID();
         ResourceLocationBridge var5 = ResourceLocationBridge.create("lunar", var1.bridge$getPath() + var4);
         ResourceLocationBridge var6 = var0.bridge$isDummyMannequin() ? var0.bridge$getLocationSkinNoOverride() : var0.bridge$getLocationSkin();
         ElytraTextureLoaderLegacy var7 = new ElytraTextureLoaderLegacy(var5, var1, var6, BridgeType2_5.FULL);
         ThreadModuleDump63.method3().bridge$getTextureManager().method3(var5, var7);
         field4.put(var2, var1, var7);
         var3 = var7;
      }

      if (!var3.COOHIRHIRRIHRCHHHHRHOCCIHCHHRR()) {
         var3.RIIIOHCCHRRRORICCHIIHHOORIIOIR(true);
         return var1;
      } else {
         return var3.OHIIOOIORCHHHOOOORIOCOHRHROCOH;
      }
   }

   public void method9(EntityPlayerBridge var1, BridgeExtension2_7 var2, int var3) {
      Optional var4 = this.method16(var1, var3);
      if (var4.isPresent() && ((CosmeticMetadata)var4.get()).method4().method10().canShowCosmetic()) {
         MolangFunctionRegistry var5 = ThreadModuleDump63.method4().method76();
         EmoteModel var6 = (EmoteModel)((CosmeticMetadata)var4.get()).method4();
         if (!var5.method14(var6)) {
            return;
         }

         if (var6.method6().isEmpty() || var6.method5().isEmpty()) {
            return;
         }

         MolangResourceModel var7 = var6.method5().get();
         RenderContext var8 = this.method8(var1, var2);
         RenderEntityHandle var9 = (RenderEntityHandle)this.field1.get(var3);
         this.method10(var8, var9, var1);
         boolean var10 = var6.RHIHHIHIIICHHCRHRCIRROIHOHRIIH().isHeldItemCosmetic() && var9.method2();
         var7.method1(var8);
         var7.setLivingAnimations(var6, var6.method3(var10));
         this.field3 = var7.IIRCROCIRCCHHIHRIOCROOOCRHIHHO().getEvaluator();
      }
   }

   private void method10(RenderContext var1, RenderEntityHandle var2, EntityPlayerBridge var3) {
      if (var2.method1().method4().method10().isHeldItemCosmetic()) {
         ItemStackRenderStateBridge var4;
         if (ThreadModuleDump63.MC_VERSION >= 5 && var2.method2()) {
            var4 = var3.bridge$getOffHandItemRenderState();
         } else {
            var4 = var3.bridge$getMainHandItemRenderState();
         }

         var1.method16(var4.bridge$getLunarItemType());
         var1.method18(var4.bridge$getLunarItemMaterial());
         var1.method20(var4);
      }
   }
}
