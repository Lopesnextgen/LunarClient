package com.moonsworth.lunar.client.cosmetics;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.bridge.AutoCloseableImpl;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.OpenGlHelperBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.DepthComparison;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformVec3fBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.render.jit.JitAnimatedResource;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.cosmetics.gecko.AttachedBone;
import com.moonsworth.lunar.client.cosmetics.gecko.FirstPersonArmMode;
import com.moonsworth.lunar.client.cosmetics.gecko.PlayerModelType;
import com.moonsworth.lunar.client.cosmetics.gecko.MolangFunctionRegistry;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Impl2;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationKeyframeParser;
import com.moonsworth.lunar.client.cosmetics.emote.MolangResourceModel;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelQuad;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelVertex;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;
import com.moonsworth.lunar.client.driver.core.holograms.HologramRendererLegacy;
import com.moonsworth.lunar.client.mod.misc.debug.GeckolibDebugMod;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.ItemCustomizer;
import com.moonsworth.lunar.client.mod.render.onesevenvisuals.OneSevenItemsModern;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.render.texture.Util2Handler;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.skin_n_bones.api.animation.AnimationMesh;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJArmature;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJBone;
import org.jetbrains.annotations.Nullable;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import software.bernie.geckolib3.core.processor.IBone;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import com.moonsworth.lunar.client.render.texture.GlintTexture;
import com.moonsworth.lunar.client.cosmetics.gecko.RenderPass;
import com.moonsworth.lunar.client.cosmetics.gecko.Transform;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.emote.CosmeticMeshBuilder;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;

public class PlayerModelPartMap {
   private static final String field1 = "armorHead";
   private static final String field2 = "armorBody";
   private static final String field3 = "armorRightArm";
   private static final String field4 = "armorLeftArm";
   private static final String field5 = "armorRightLeg";
   private static final String field6 = "armorLeftLeg";
   private static final String field7 = "armorRightBoot";
   private static final String field8 = "armorLeftBoot";
   private static final Map<ResourceLocationBridge, ElytraTextureLoaderLegacy> field9 = new ConcurrentHashMap<>();
   private static final float field10 = 0.065F;
   private static final String[] field11 = new String[]{
      "armorHead", "armorBody", "armorRightArm", "armorLeftArm", "armorRightLeg", "armorLeftLeg", "armorRightBoot", "armorLeftBoot"
   };

   public static void method1(ModelRenderConfig var0) {
      var0.method7()
         .method7(
            var1 -> {
               var1.method1(
                  var0.getColor().getRed() / 255.0F,
                  var0.getColor().getGreen() / 255.0F,
                  var0.getColor().getBlue() / 255.0F,
                  var0.getColor().getAlpha() / 255.0F
               );
               OpenGlHelperBridge var2 = Bridge.method22();
               float var3 = var2.method7();
               float var4 = var2.method8();
               if (var0.method14() == RenderPass.EMISSIVE) {
                  var2.method4(var2.method5(), 240.0F, 240.0F);
               }

               ThreadModuleDump63.method4().method82().method2();
               ThreadModuleDump63.method3().bridge$getTextureManager().bridge$bindTexture(var0.getTexture());
               GlintTexture.method1(var0);
               if (var0.method14() == RenderPass.EMISSIVE) {
                  var2.method4(var2.method5(), var3, var4);
               }
            },
            var1 -> {
               ThreadModuleDump63.method4().method82().method2();
               CosmeticMeshBuilder.method2(var0);
            }
         );
      if (var0.method14() != RenderPass.EMISSIVE) {
         method2(var0.getTexture()).ifPresent(var1 -> method1(var0.method20(var1).method25(RenderPass.EMISSIVE)));
      }
   }

   public static Optional<ResourceLocationBridge> method2(ResourceLocationBridge var0) {
      Bridge8Extension3 var1 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTextureMap().get(var0);
      if (var1 instanceof Bridge8Extension34) {
         Bridge3_4 var2 = ((Bridge8Extension34)var1).method3();
         AutoCloseableImpl var3 = null;
         if (var2 instanceof JitAnimatedResource var4) {
            var3 = var4.method17();
         } else if (var2 instanceof Util2Handler var5) {
            var3 = var5.method19();
         }

         if (var3 != null) {
            return Optional.ofNullable(var3.method2());
         }
      }

      return Optional.empty();
   }

   public static Optional<CosmeticMetadata> method3(ItemStackRenderStateBridge var0) {
      if (var0 == null) {
         return Optional.empty();
      }

      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 == null) {
         return Optional.empty();
      }

      List var2 = ThreadModuleDump63.method4().method53().method18(var1.bridge$getUniqueID());
      return var2.stream().filter(var1x -> method4(var1x, var0)).findFirst();
   }

   private static boolean method4(CosmeticMetadata var0, ItemStackRenderStateBridge var1) {
      return var0.method4() instanceof EmoteModel var3
         ? var3.method6()
            .filter(var0x -> var0x instanceof Gui2Impl2)
            .map(var0x -> (Gui2Impl2)var0x)
            .filter(var0x -> var0x.method20() != FirstPersonArmMode.NONE)
            .filter(var2 -> var3.method7(var1) && var3.method10().canShowCosmetic())
            .isPresent()
         : false;
   }

   public static void method5(Bridge6_10 var0, AbstractRenderContext var1) {
      if (var0.bridge$isSneaking()) {
         if (Bridge.getMinecraftVersion().method19()) {
            var1.translate(0.0, -0.125, 0.0);
         } else if (Bridge.getMinecraftVersion().method3(Config.field1)) {
            var1.translate(0.0, -0.2, 0.0);
         }
      }

      var1.translate(var0.bridge$getPosX(), var0.bridge$getPosY(), var0.bridge$getPosZ());
      var1.method4(180.0F - var0.bridge$getBodyRot(), 0.0F, 1.0F, 0.0F);
      var1.scale(-1.0F, -1.0F, 1.0F);
      float var2 = 0.9375F;
      var1.scale(var2, var2, var2);
      var1.translate(0.0, -1.501F, 0.0);
   }

   public static void method6(
      AbstractRenderContext var0,
      CosmeticMetadata var1,
      EmoteModel var2,
      BridgeExtension2_7 var3,
      EntityPlayerBridge var4,
      BoneList var5,
      boolean var6,
      float var7,
      boolean var8,
      boolean var9,
      @Nullable RenderContext var10,
      Consumer<Boolean> var11
   ) {
      if (!var2.method6().isEmpty()) {
         boolean var12 = method8(var1, var2.method10(), var9, var4);
         Gui2Handler var13 = var2.method6().get();
         AttachedBone var14;
         if (var13 instanceof Gui2Impl2 var15) {
            var14 = var15.method22();
         } else {
            var14 = AttachedBone.NONE;
         }

         if (var14 == AttachedBone.NONE) {
            method9(var1, var5, var3, var9, var4);
         }

         if (var8) {
            if (var4 != null) {
               if (var4.bridge$isVisiblyCrouching() && !Bridge.getMinecraftVersion().equals(Config.field1)) {
                  var0.translate(0.0, 0.2F, 0.0);
               }

               if (var3 != null && Bridge.getMinecraftVersion().method21()) {
                  method11(var0, var4, var3, var2);
               }
            }

            if (var6) {
               if (var14 == AttachedBone.SHOULDER) {
                  var0.method4(180.0F, 0.0F, 0.0F, 1.0F);
                  var0.translate(0.0, -0.3F, 0.0);
               } else {
                  var0.method4(180.0F, 0.0F, 0.0F, 1.0F);
                  var0.method4(180.0F, 0.0F, 1.0F, 0.0F);
               }
            }

            var0.method4(180.0F, 0.0F, 0.0F, 1.0F);
            var0.translate(0.0, -1.51F, 0.0);
            method7(var0, var13, var4);

            for (ThreadModuleDump91 var16 : var13.method12()) {
               ThreadModuleDump91.Type var17 = var16.getCondition();
               if (var10 == null ? var17.applies(var4, var1) : var17.applies(var10, var1)) {
                  var16.transform(var0, var4, var7);
               }
            }
         } else {
            method11(var0, var4, var3, var2);
            var0.translate(0.0, 1.51F, 0.0);
            var0.method5(0.0F, 0.0F, 180.0F);
            method7(var0, var13, var4);

            for (ThreadModuleDump91 var20 : var13.method12()) {
               ThreadModuleDump91.Type var21 = var20.getCondition();
               if (var10 == null ? var21.applies(var4, var1) : var21.applies(var10, var1)) {
                  if (Bridge.getMinecraftVersion().method23()) {
                     var20.transform(var0, var4, ((BridgeExtension2_11)var0).method51(), var0.method28());
                  } else {
                     var20.transform(var0, var4, var0.method28());
                  }
               }
            }
         }

         if (var2.method10().isHeldItemCosmetic()) {
            method37(var13, var12, var0, ItemTransformsBridge.Type.thirdPerson(var12), var10);
         }

         if ((var2.method10().isHeldItemCosmetic() || var2.method10() == CosmeticCategoryType.PET) && var12) {
            var0.scale(-1.0F, 1.0F, 1.0F);
            var11.accept(true);
         }
      }
   }

   private static void method7(AbstractRenderContext var0, Gui2Handler var1, EntityPlayerBridge var2) {
      if (var1 instanceof Gui2Impl2 var3 && var3.method26()) {
         if (var2 != null && !var2.method2()) {
            ItemStackRenderStateBridge var4 = var2.bridge$getArmor(EquipmentSlotBridge.CHEST);
            if (var4 != null && var4.bridge$getArmorState() != null) {
               var0.translate(0.0, 0.065F, 0.0);
            }
         }
      }
   }

   private static boolean method8(CosmeticMetadata var0, CosmeticCategoryType var1, boolean var2, EntityPlayerBridge var3) {
      boolean var4 = var2;
      if (var1.isHeldItemCosmetic() && var3 != null && var3.bridge$isMainHandSwapped()) {
         var4 = !var4;
      }

      if (var0.method6().method6()) {
         var4 = !var4;
      }

      return var4;
   }

   private static void method9(CosmeticMetadata var0, BoneList var1, BridgeExtension2_7 var2, boolean var3, EntityPlayerBridge var4) {
      var1.method2("bipedLeftArm").ifPresent(var0x -> var0x.setHidden(false));
      var1.method2("bipedRightArm").ifPresent(var0x -> var0x.setHidden(false));
      var1.method2("bipedHead").ifPresent(var0x -> var0x.setHidden(false));
      var1.method2("bipedBody").ifPresent(var0x -> var0x.setHidden(false));
      var1.method2("bipedLeftLeg").ifPresent(var0x -> var0x.setHidden(false));
      var1.method2("bipedRightLeg").ifPresent(var0x -> var0x.setHidden(false));
      var1.method2("armorHead").ifPresent(var1x -> {
         Bridge2_46 var2x = var2.bridge$bipedHead();
         method14(var2x, var1x);
         var1x.setPositionX(var2x.bridge$getRotatePointX());
         var1x.setPositionY(-var2x.bridge$getRotatePointY());
         var1x.setPositionZ(var2x.bridge$getRotatePointZ());
      });
      var1.method2("armorBody").ifPresent(var1x -> {
         Bridge2_46 var2x = var2.bridge$bipedBody();
         method14(var2x, var1x);
         var1x.setPositionX(var2x.bridge$getRotatePointX());
         var1x.setPositionY(-var2x.bridge$getRotatePointY());
         var1x.setPositionZ(var2x.bridge$getRotatePointZ());
      });
      boolean var5 = false;
      if (var0.method4().method10().isHeldItemCosmetic()) {
         var1.method2("bipedRightArm").ifPresent(var0x -> var0x.setHidden(false));
         var5 = method8(var0, var0.method4().method10(), var3, var4);
      }

      if (var5) {
         Bridge2_46 var6 = var2.bridge$bipedRightArm();
         Bridge2_46 var7 = var2.bridge$bipedLeftArm();
         var1.method2("armorLeftArm").ifPresent(var1x -> {
            var1x.setRotationX(-var6.bridge$getRotateAngleX());
            var1x.setRotationY(var6.bridge$getRotateAngleY());
            var1x.setRotationZ(-var6.bridge$getRotateAngleZ());
            var1x.setPositionX(var6.bridge$getRotatePointX() + 5.0F);
            var1x.setPositionY(2.0F - var6.bridge$getRotatePointY());
            var1x.setPositionZ(var6.bridge$getRotatePointZ());
         });
         var1.method2("armorRightArm").ifPresent(var1x -> {
            var1x.setRotationX(-var7.bridge$getRotateAngleX());
            var1x.setRotationY(var7.bridge$getRotateAngleY());
            var1x.setRotationZ(-var7.bridge$getRotateAngleZ());
            var1x.setPositionX(var7.bridge$getRotatePointX() - 5.0F);
            var1x.setPositionY(2.0F - var7.bridge$getRotatePointY());
            var1x.setPositionZ(var7.bridge$getRotatePointZ());
         });
      } else {
         var1.method2("armorRightArm").ifPresent(var1x -> {
            Bridge2_46 var2x = var2.bridge$bipedRightArm();
            method14(var2x, var1x);
            var1x.setPositionX(var2x.bridge$getRotatePointX() + 5.0F);
            var1x.setPositionY(2.0F - var2x.bridge$getRotatePointY());
            var1x.setPositionZ(var2x.bridge$getRotatePointZ());
         });
         var1.method2("armorLeftArm").ifPresent(var1x -> {
            Bridge2_46 var2x = var2.bridge$bipedLeftArm();
            method14(var2x, var1x);
            var1x.setPositionX(var2x.bridge$getRotatePointX() - 5.0F);
            var1x.setPositionY(2.0F - var2x.bridge$getRotatePointY());
            var1x.setPositionZ(var2x.bridge$getRotatePointZ());
         });
      }

      var1.method2("armorRightLeg").ifPresent(var2x -> {
         Bridge2_46 var3x = var2.bridge$bipedRightLeg();
         method14(var3x, var2x);
         var2x.setPositionX(var3x.bridge$getRotatePointX() + 2.0F);
         var2x.setPositionY(12.0F - var3x.bridge$getRotatePointY());
         var2x.setPositionZ(var3x.bridge$getRotatePointZ());
         var1.method2("armorRightBoot").ifPresent(var1xx -> {
            method14(var3x, var1xx);
            var1xx.setPositionX(var3x.bridge$getRotatePointX() + 2.0F);
            var1xx.setPositionY(12.0F - var3x.bridge$getRotatePointY());
            var1xx.setPositionZ(var3x.bridge$getRotatePointZ());
         });
      });
      var1.method2("armorLeftLeg").ifPresent(var2x -> {
         Bridge2_46 var3x = var2.bridge$bipedLeftLeg();
         method14(var3x, var2x);
         var2x.setPositionX(var3x.bridge$getRotatePointX() - 2.0F);
         var2x.setPositionY(12.0F - var3x.bridge$getRotatePointY());
         var2x.setPositionZ(var3x.bridge$getRotatePointZ());
         var1.method2("armorLeftBoot").ifPresent(var1xx -> {
            method14(var3x, var1xx);
            var1xx.setPositionX(var3x.bridge$getRotatePointX() - 2.0F);
            var1xx.setPositionY(12.0F - var3x.bridge$getRotatePointY());
            var1xx.setPositionZ(var3x.bridge$getRotatePointZ());
         });
      });
   }

   public static void method10(BoneList var0) {
      for (String var4 : field11) {
         var0.method2(var4).ifPresent(var0x -> {
            var0x.setRotationX(0.0F);
            var0x.setRotationY(0.0F);
            var0x.setRotationZ(0.0F);
         });
      }
   }

   private static void method11(AbstractRenderContext var0, EntityPlayerBridge var1, BridgeExtension2_7 var2, EmoteModel var3) {
      var0.method7(var3x -> method12(var1, var2, var3), var3x -> method13(var3x, var1, var2, var3));
   }

   @Annotation2(max = 5)
   private static void method12(EntityPlayerBridge var0, BridgeExtension2_7 var1, EmoteModel var2) {
      if (!var2.method6().isEmpty()) {
         boolean var3 = var0.bridge$isEmoting();
         AttachedBone var4;
         if (var2.method6().get() instanceof Gui2Impl2 var5) {
            var4 = var5.method22();
         } else {
            var4 = AttachedBone.NONE;
         }

         if (var2.method10().isHeldItemCosmetic() && var0 != null && var0.bridge$isMainHandSwapped()) {
            if (var4 == AttachedBone.RIGHT_ARM) {
               var4 = AttachedBone.LEFT_ARM;
            } else if (var4 == AttachedBone.LEFT_ARM) {
               var4 = AttachedBone.RIGHT_ARM;
            }
         }

         if (var3 && var4 != AttachedBone.NONE) {
            EmoteController var7 = (EmoteController)EmoteController.get(var0.bridge$getUniqueID());
            BOBJArmature var8 = ((AnimationMesh)var7.animator.animation.meshes.get(0)).armature;
            var7.animator.setupMatrix((BOBJBone)var8.bones.get(var4.getBoneName()));
         } else if (var4 != AttachedBone.NONE) {
            var4.getBodyPartSupplier().apply(var1).bridge$postRender(0.0625F);
         }
      }
   }

   @Annotation2(min = 6)
   private static void method13(BridgeExtension2_11 var0, EntityPlayerBridge var1, BridgeExtension2_7 var2, EmoteModel var3) {
      if (!var3.method6().isEmpty()) {
         AttachedBone var4;
         if (var3.method6().get() instanceof Gui2Impl2 var5) {
            var4 = var5.method22();
         } else {
            var4 = AttachedBone.NONE;
         }

         if (var3.method10().isHeldItemCosmetic() && var1 != null && var1.bridge$isMainHandSwapped()) {
            if (var4 == AttachedBone.RIGHT_ARM) {
               var4 = AttachedBone.LEFT_ARM;
            } else if (var4 == AttachedBone.LEFT_ARM) {
               var4 = AttachedBone.RIGHT_ARM;
            }
         }

         if (var1.bridge$isEmoting()) {
            EmoteController var7 = (EmoteController)EmoteController.get(var1.bridge$getUniqueID());
            if (var7.animator != null) {
               BOBJArmature var8 = ((AnimationMesh)var7.animator.animation.meshes.get(0)).armature;
               if (var4 != AttachedBone.NONE) {
                  var7.animator.setupMatrix((BOBJBone)var8.bones.get(var4.getBoneName()), var0.method51());
               }

               if (var4 == AttachedBone.SHOULDER) {
                  var0.method5(0.0F, 0.0F, 180.0F);
                  var0.translate(0.0, -0.3F, 0.0);
               } else {
                  var0.method5(0.0F, 180.0F, 180.0F);
               }
            }
         } else if (var4 != AttachedBone.NONE) {
            var4.getBodyPart().translateModern(var2).transform(var0, var1, var0.method28());
         }
      }
   }

   private static void method14(Bridge2_46 var0, IBone var1) {
      var1.setRotationX(-var0.bridge$getRotateAngleX());
      var1.setRotationY(-var0.bridge$getRotateAngleY());
      var1.setRotationZ(var0.bridge$getRotateAngleZ());
   }

   public static void method15(IBoneSerializer var0, Bridge5_16 var1) {
      method31(var0, var1);
      method28(var0, var1);
      method32(var0, var1);
      method30(var0, var1);
      method29(var0, var1);
   }

   public static void method16(IBoneSerializer var0, AnimationKeyframeParser var1) {
      var1.method9(var0);
      var1.method7(var0);
      var1.method11(var0);
      var1.method10(var0);
      var1.method8(var0);
   }

   public static void method17(IBoneSerializer var0, Matrix4f var1) {
      var1.translate(-var0.getPositionX() / 16.0F, var0.getPositionY() / 16.0F, var0.getPositionZ() / 16.0F);
      var1.translate(var0.getPivotX() / 16.0F, var0.getPivotY() / 16.0F, var0.getPivotZ() / 16.0F);
      if (var0.getRotationZ() != 0.0F) {
         var1.rotateZ(var0.getRotationZ());
      }

      if (var0.getRotationY() != 0.0F) {
         var1.rotateY(var0.getRotationY());
      }

      if (var0.getRotationX() != 0.0F) {
         var1.rotateX(var0.getRotationX());
      }

      var1.scale(var0.getScaleX(), var0.getScaleY(), var0.getScaleZ());
      var1.translate(-var0.getPivotX() / 16.0F, -var0.getPivotY() / 16.0F, -var0.getPivotZ() / 16.0F);
   }

   public static void method18(IBoneSerializer var0, Transform var1) {
      var1.method2(
         (-var0.getPositionX() + var0.getPivotX()) * 0.0625F,
         (var0.getPositionY() + var0.getPivotY()) * 0.0625F,
         (var0.getPositionZ() + var0.getPivotZ()) * 0.0625F
      );
      Quaternionf var2 = var1.field2;
      if (var0.getRotationZ() != 0.0F) {
         var2.rotateZ(var0.getRotationZ());
      }

      if (var0.getRotationY() != 0.0F) {
         var2.rotateY(var0.getRotationY());
      }

      if (var0.getRotationX() != 0.0F) {
         var2.rotateX(var0.getRotationX());
      }

      var1.field1.mul(var0.getScaleX(), var0.getScaleY(), var0.getScaleZ());
      var1.method2(-var0.getPivotX() * 0.0625F, -var0.getPivotY() * 0.0625F, -var0.getPivotZ() * 0.0625F);
   }

   public static void method19(CubeMesh var0, Bridge5_16 var1) {
      method26(var0, var1);
      method33(var0, var1);
      method27(var0, var1);
   }

   public static void method20(CubeMesh var0, AnimationKeyframeParser var1) {
      var1.method5(var0);
      var1.method12(var0);
      var1.method6(var0);
   }

   public static void method21(CubeMesh var0, Matrix4f var1) {
      Vector3f var2 = var0.field2;
      var1.translate(var2.x() / 16.0F, var2.y() / 16.0F, var2.z() / 16.0F);
      Vector3f var3 = var0.field3;
      var1.rotateZ(var3.z);
      var1.rotateY(var3.y);
      var1.rotateX(var3.x);
      var2 = var0.field2;
      var1.translate(-var2.x() / 16.0F, -var2.y() / 16.0F, -var2.z() / 16.0F);
   }

   public static void method22(CubeMesh var0, Transform var1) {
      Vector3f var2 = var0.field2;
      var1.method2(var2.x() * 0.0625F, var2.y() * 0.0625F, var2.z() * 0.0625F);
      Vector3f var3 = var0.field3;
      var1.field2.rotateZ(var3.z);
      var1.field2.rotateY(var3.y);
      var1.field2.rotateX(var3.x);
      var2 = var0.field2;
      var1.method2(-var2.x() * 0.0625F, -var2.y() * 0.0625F, -var2.z() * 0.0625F);
   }

   @Nullable
   public static AxisAlignedBBBridge method23(CubeMesh var0, Consumer<Vector3f> var1) {
      AxisAlignedBBBridge var2 = null;

      for (ModelQuad var6 : var0.field1) {
         if (var6 != null) {
            for (ModelVertex var10 : var6.field3) {
               Vector3f var11 = new Vector3f(var10.field1.x, var10.field1.y, var10.field1.z);
               var1.accept(var11);
               if (var2 == null) {
                  var2 = Bridge.method8().method45(var11.x, var11.y, var11.z, var11.x, var11.y, var11.z);
               } else {
                  var2 = var2.method7(var11);
               }
            }
         }
      }

      return var2;
   }

   public static ResourceLocationBridge method24(EmoteModel var0, MolangResourceModel var1, @Nullable Bridge5_11 var2, Evaluator var3) {
      ResourceLocationBridge var4 = var1.method2(var0, var3);
      Optional var5 = var0.method6();
      if (var5.isPresent()) {
         Gui2Handler var6 = (Gui2Handler)var5.get();
         if (var6.method11() == PlayerModelType.MINIME && var2 != null) {
            return method25(var2, var4);
         }
      }

      return var4;
   }

   public static ResourceLocationBridge method25(Bridge5_11 var0, ResourceLocationBridge var1) {
      if (!var0.bridge$isSkinTextureUploaded()) {
         return var1;
      }

      ResourceLocationBridge var2 = var0.bridge$getLocationSkin();
      ElytraTextureLoaderLegacy var3 = field9.get(var2);
      if (var3 != null && var3.isDeleted()) {
         field9.remove(var2, var3);
         var3 = null;
      }

      if (var3 == null) {
         String var4 = "_minime_" + UUID.randomUUID();
         ResourceLocationBridge var5 = ResourceLocationBridge.create("lunar", var1.bridge$getPath() + var4);
         ElytraTextureLoaderLegacy var6 = new ElytraTextureLoaderLegacy(var5, var1, var2, BridgeType2_5.FULL);
         ThreadModuleDump63.method3().bridge$getTextureManager().method3(var5, var6);
         field9.put(var2, var6);
         var3 = var6;
      }

      if (!var3.COOHIRHIRRIHRCHHHHRHOCCIHCHHRR()) {
         var3.RIIIOHCCHRRRORICCHIIHHOORIIOIR(true);
         return var1;
      } else {
         return var3.OHIIOOIORCHHHOOOORIOCOHRHROCOH;
      }
   }

   private static void method26(CubeMesh var0, Bridge5_16 var1) {
      Vector3f var2 = new Vector3f(var0.field2.x, var0.field2.y, var0.field2.z);
      var1.bridge$translate(var2.x() / 16.0F, var2.y() / 16.0F, var2.z() / 16.0F);
   }

   private static void method27(CubeMesh var0, Bridge5_16 var1) {
      Vector3f var2 = new Vector3f(var0.field2.x, var0.field2.y, var0.field2.z);
      var1.bridge$translate(-var2.x() / 16.0F, -var2.y() / 16.0F, -var2.z() / 16.0F);
   }

   private static void method28(IBoneSerializer var0, Bridge5_16 var1) {
      var1.bridge$translate(var0.getPivotX() / 16.0F, var0.getPivotY() / 16.0F, var0.getPivotZ() / 16.0F);
   }

   private static void method29(IBoneSerializer var0, Bridge5_16 var1) {
      var1.bridge$translate(-var0.getPivotX() / 16.0F, -var0.getPivotY() / 16.0F, -var0.getPivotZ() / 16.0F);
   }

   private static void method30(IBoneSerializer var0, Bridge5_16 var1) {
      var1.bridge$scale(var0.getScaleX(), var0.getScaleY(), var0.getScaleZ());
   }

   private static void method31(IBoneSerializer var0, Bridge5_16 var1) {
      var1.bridge$translate(-var0.getPositionX() / 16.0F, var0.getPositionY() / 16.0F, var0.getPositionZ() / 16.0F);
   }

   private static void method32(IBoneSerializer var0, Bridge5_16 var1) {
      if (var0.getRotationZ() != 0.0F) {
         var1.bridge$mulPose(new Quaternionf(new AxisAngle4f(var0.getRotationZ(), HorsestatsType_2.SOUTH.getUnitVector())));
      }

      if (var0.getRotationY() != 0.0F) {
         var1.bridge$mulPose(new Quaternionf(new AxisAngle4f(var0.getRotationY(), HorsestatsType_2.UP.getUnitVector())));
      }

      if (var0.getRotationX() != 0.0F) {
         var1.bridge$mulPose(new Quaternionf(new AxisAngle4f(var0.getRotationX(), HorsestatsType_2.EAST.getUnitVector())));
      }
   }

   private static void method33(CubeMesh var0, Bridge5_16 var1) {
      Vector3f var2 = new Vector3f(var0.field3.x, var0.field3.y, var0.field3.z);
      var1.bridge$mulPose(method34(0.0F, 0.0F, var2.z()));
      var1.bridge$mulPose(method34(0.0F, var2.y(), 0.0F));
      var1.bridge$mulPose(method34(var2.x(), 0.0F, 0.0F));
   }

   private static Quaternionf method34(float var0, float var1, float var2) {
      float var3 = MathHelperBridge.method2(0.5F * var0);
      float var4 = MathHelperBridge.method1(0.5F * var0);
      float var5 = MathHelperBridge.method2(0.5F * var1);
      float var6 = MathHelperBridge.method1(0.5F * var1);
      float var7 = MathHelperBridge.method2(0.5F * var2);
      float var8 = MathHelperBridge.method1(0.5F * var2);
      float var9 = var3 * var6 * var8 + var4 * var5 * var7;
      float var10 = var4 * var5 * var8 - var3 * var6 * var7;
      float var11 = var3 * var5 * var8 + var4 * var6 * var7;
      float var12 = var4 * var6 * var8 - var3 * var5 * var7;
      return new Quaternionf(var9, var10, var11, var12);
   }

   public static void method35(AbstractRenderContext var0, BoneList var1) {
      if (!var1.field2.getIdentifier().contains("aura")) {
         AxisAlignedBBBridge var2 = var1.method1();
         if (var2 != null) {
            double var3 = var2.bridge$getMinX();
            double var5 = var2.bridge$getMinY();
            double var7 = var2.bridge$getMinZ();
            double var9 = var2.bridge$getMaxX();
            double var11 = var2.bridge$getMaxY();
            double var13 = var2.bridge$getMaxZ();
            var0.method13();
            var0.method16();
            var0.method14();
            var0.method3(DepthComparison.GL_GREATER, 0.001F);
            var0.method11();
            var0.method2(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA);
            var0.method6(false);
            Bridge2_32 var15 = var0.method10(LunarRenderTypes.field26);
            var15.method1();
            var15.method2(var3, var5, var7).method9(369098751);
            var15.method2(var9, var5, var7).method9(369098751);
            var15.method2(var9, var5, var13).method9(369098751);
            var15.method2(var3, var5, var13).method9(369098751);
            var15.method2(var3, var11, var7).method9(369041954);
            var15.method2(var3, var11, var13).method9(369041954);
            var15.method2(var9, var11, var13).method9(369041954);
            var15.method2(var9, var11, var7).method9(369041954);
            var15.method2(var3, var5, var13).method9(354615074);
            var15.method2(var9, var5, var13).method9(354615074);
            var15.method2(var9, var11, var13).method9(354615074);
            var15.method2(var3, var11, var13).method9(354615074);
            var15.method2(var3, var5, var7).method9(354558719);
            var15.method2(var3, var11, var7).method9(354558719);
            var15.method2(var9, var11, var7).method9(354558719);
            var15.method2(var9, var5, var7).method9(354558719);
            var15.method2(var3, var5, var7).method9(369098530);
            var15.method2(var3, var5, var13).method9(369098530);
            var15.method2(var3, var11, var13).method9(369098530);
            var15.method2(var3, var11, var7).method9(369098530);
            var15.method2(var9, var5, var7).method9(354615295);
            var15.method2(var9, var11, var7).method9(354615295);
            var15.method2(var9, var11, var13).method9(354615295);
            var15.method2(var9, var5, var13).method9(354615295);
            var15.method17(BufferBuildMode.BATCHED);
            if (HologramRendererLegacy.field1) {
               var0.method33(LunarRenderTypes.field26);
            }

            var0.method6(true);
            var0.method3(DepthComparison.GL_GREATER, 0.1F);
            var0.method10();
            var0.method12();
            var0.method14();
            var0.method2(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA);
         }
      }
   }

   public static Consumer<RenderContext> method36(Gui2Handler var0, boolean var1, AbstractRenderContext var2, ItemTransformsBridge.Type var3) {
      return var4 -> method37(var0, var1, var2, var3, var4);
   }

   public static void method37(Gui2Handler var0, boolean var1, AbstractRenderContext var2, ItemTransformsBridge.Type var3, RenderContext var4) {
      if (var0 instanceof Gui2Impl2 var5) {
         method38(var5, var1, var2, var3, var4);
      }
   }

   public static void method38(Gui2Impl2 var0, boolean var1, AbstractRenderContext var2, ItemTransformsBridge.Type var3, RenderContext var4) {
      if (var3.firstPerson()) {
         var4.method1()
            .filter(var0x -> var0x == ThreadModuleDump63.method7())
            .ifPresent(
               var4x -> {
                  ItemStackBridge var5x = null;
                  if (var4.method19() instanceof ItemStackBridge var6x) {
                     var5x = var6x;
                  } else if (ThreadModuleDump63.MC_VERSION >= 5) {
                     EquipmentSlotBridge var10;
                     if (var1) {
                        var10 = !var4x.bridge$isMainHandSwapped() ? EquipmentSlotBridge.OFFHAND : EquipmentSlotBridge.MAINHAND;
                     } else {
                        var10 = !var4x.bridge$isMainHandSwapped() ? EquipmentSlotBridge.MAINHAND : EquipmentSlotBridge.OFFHAND;
                     }

                     var5x = var4x.bridge$getEquipmentInSlot(var10);
                  }

                  if (ThreadModuleDump63.MC_VERSION == 5
                     && var5x != null
                     && ThreadModuleDump63.method4().method40().method98().method17().method9(var3.thirdPerson())) {
                     ItemTransformVec3fBridge var8 = OneSevenItemsModern.ITEM_TRANSFORM;
                     var2.method7(var2xx -> var8.method2(var1, var2xx), var2xx -> var8.method1(var1, var2xx.method51()));
                  }

                  if (var5x != null && (ThreadModuleDump63.MC_VERSION < 5 || !var5x.bridge$isEmpty())) {
                     ItemCustomizer var9 = ThreadModuleDump63.method4().method40().method83();
                     if (var9.isEnabled() && var9.method13().isEnabled()) {
                        ItemTransformVec3fBridge var11 = var9.method13().method10(var5x, !var1);
                        if (var11 != null) {
                           var2.method7(var2xx -> var11.method2(var1, var2xx), var2xx -> var11.method1(var1, var2xx.method51()));
                        }
                     }
                  }
               }
            );
      }

      GeckolibDebugMod var5 = ThreadModuleDump63.method4().method40().method73();
      if (var5 != null && var5.isValid() && var5.isEnabled()) {
         var2.method7(var2x -> var5.field38.method2(var1, var2x), var2x -> var5.field38.method1(var1, var2x.method51()));
      }

      if (var0.method23() != null) {
         Optional var6 = ThreadModuleDump63.method4().method76().method12(var0.method23().method1(var4.getEvaluator()));
         var6.ifPresent(
            var3x -> var2.method7(
               var3xx -> var3x.method1(var3, true).method2(var1, var3xx), var3xx -> var3x.method1(var3, false).method1(var1, var3xx.method51())
            )
         );
      }
   }

   public static boolean method39(EntityPlayerBridge var0, ItemStackRenderStateBridge var1) {
      MolangFunctionRegistry var2 = ThreadModuleDump63.method4().method76();

      for (CosmeticMetadata var4 : ThreadModuleDump63.method4().method53().method18(var0.bridge$getUniqueID())) {
         if (var4.method4() instanceof EmoteModel var5 && var2.method14(var5) && var5.method8(var1, var0)) {
            return var4.method4().method10().canShowCosmetic();
         }
      }

      return false;
   }

   static {
      ClientEventBus.method29().method2(EventPlayerRemoval.class, var0 -> {
         if (var0.method1() instanceof Bridge5_11 var1) {
            ElytraTextureLoaderLegacy var3 = field9.remove(var1.bridge$getLocationSkin());
            if (var3 != null) {
               var3.method5();
            }
         }
      });
   }

   public class Data {
      private final int field1;
      private final int totalFrames;
      private final int field2;

      public Data(int var1, int var2, int var3) {
         this.field1 = var1;
         this.totalFrames = var2;
         this.field2 = var3;
      }

      public int method1() {
         return this.field1;
      }

      public int method2() {
         return this.totalFrames;
      }

      public int method3() {
         return this.field2;
      }
   }
}
