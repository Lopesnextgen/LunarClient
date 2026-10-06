package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_4;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.bridge.MixinHelper;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.TexturedBoxRenderer;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.render.jit.JitEmoteResource;
import com.moonsworth.lunar.client.cosmetics.gecko.MeshPassRunner;
import com.moonsworth.lunar.client.render.texture.NativeImageBuilder;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import mchorse.emoticons.api.animation.model.AnimatorEmoticonsController;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.skin_n_bones.api.animation.AnimationMesh;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJArmature;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJBone;
import org.joml.Vector3f;
import com.moonsworth.lunar.client.cosmetics.emote.PhysicsPoint;

public class ClothCloakUpdater implements MExtension<Bridge5_11, EntityPlayerBridge> {
   public static final int field1 = 22;
   public static final int field2 = 64;
   public static final ClothCloakUpdater.Data3 field3 = new ClothCloakUpdater.Data3(0.045454547F, 0.5F, 0.0F, 0.05882353F);
   public static final ClothCloakUpdater.Data3 field4 = new ClothCloakUpdater.Data3(0.5F, 0.95454544F, 0.0F, 0.05882353F);
   public static final ClothCloakUpdater.Data3 field5 = new ClothCloakUpdater.Data3(0.0F, 0.045454547F, 0.05882353F, 1.0F);
   public static final ClothCloakUpdater.Data3 field6 = new ClothCloakUpdater.Data3(0.045454547F, 0.5F, 0.05882353F, 1.0F);
   public static final ClothCloakUpdater.Data3 field7 = new ClothCloakUpdater.Data3(0.5F, 0.54545456F, 0.05882353F, 1.0F);
   public static final ClothCloakUpdater.Data3 field8 = new ClothCloakUpdater.Data3(1.0F, 0.54545456F, 0.05882353F, 1.0F);
   private static final ClothCloakUpdater.Data3 field9 = new ClothCloakUpdater.Data3(0.015625F, 0.171875F, 0.0F, 0.03125F);
   private static final ClothCloakUpdater.Data3 field10 = new ClothCloakUpdater.Data3(0.171875F, 0.328125F, 0.0F, 0.03125F);
   private static final ClothCloakUpdater.Data3 field11 = new ClothCloakUpdater.Data3(0.0F, 0.015625F, 0.03125F, 0.53125F);
   private static final ClothCloakUpdater.Data3 field12 = new ClothCloakUpdater.Data3(0.015625F, 0.171875F, 0.03125F, 0.53125F);
   private static final ClothCloakUpdater.Data3 field13 = new ClothCloakUpdater.Data3(0.171875F, 0.1875F, 0.03125F, 0.53125F);
   private static final ClothCloakUpdater.Data3 field14 = new ClothCloakUpdater.Data3(0.34375F, 0.1875F, 0.03125F, 0.53125F);
   private final CosmeticManager field15;

   public ClothCloakUpdater(CosmeticManager var1) {
      this.field15 = var1;
   }

   @Annotation2(max = 5)
   public void method1(
      BridgeExtension3_5 var1, Bridge5_11 var2, BridgeExtension2_7 var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10
   ) {
      CosmeticManager var11 = ThreadModuleDump63.method4().method53();
      if (!var2.bridge$isInvisible() && !var2.bridge$isSpectator() && !var2.bridge$isElytraFlying() && !var2.bridge$isInvisibleToPlayer()) {
         if (this.method3(var1, var2)) {
            List var12 = this.field15.method14(var2.bridge$getUniqueID(), CosmeticCategoryType.CLOAK);
            if (!var12.isEmpty()) {
               var1.push();

               for (CosmeticMetadata var14 : var12) {
                  OwnedCosmetic var15 = var14.method4();
                  if (var15.method10().canShowCosmetic() && var11.method34(var14)) {
                     var1.method5(0.0F, 180.0F, 0.0F);
                     var1.method5(0.0F, 0.0F, 180.0F);
                     var1.translate(0.0, 0.0, -0.15);
                     this.method8(var1, var3, var2, var15.method4(var2), -1);
                     if (var15.method22()) {
                        DriverViewLegacy.method21()
                           .method14()
                           .method3(var1, var15, () -> this.method8(AbstractRenderContext.method32(), var3, var2, CosmeticManager.field2, -1));
                     }
                  }
               }

               var1.pop();
            }
         }
      }
   }

   public void method2(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, int var4, int var5) {
      if (ThreadModuleDump63.MC_VERSION >= 26) {
         MixinHelper var10 = var2.bridge$getClothCloakState();
         if (var10 != null) {
            if (this.method3(var1, var2)) {
               Optional var11 = ThreadModuleDump63.method4().method53().method13(var2.bridge$getUniqueID());
               if (!var11.isEmpty() && this.field15.method34((CosmeticMetadata)var11.get())) {
                  if (var2.method2() && ((CosmeticMetadata)var11.get()).method4().method22()) {
                     this.method7(var1, var3, var2, var10.method1(), var5);
                     DriverViewLegacy.method21()
                        .method14()
                        .method3(var1, ((CosmeticMetadata)var11.get()).method4(), () -> this.method7(var1, var3, var2, CosmeticManager.field2, var5));
                  } else {
                     this.method8(var1, var3, var2, var10.method1(), var5);
                  }
               }
            }
         }
      } else {
         ItemStackRenderStateBridge var6 = var2.bridge$getArmor(EquipmentSlotBridge.CHEST);
         boolean var7 = var6 != null && var6.bridge$getLunarItemType() == BridgeType2_4.ELYTRA;
         if (!var7 && !var2.bridge$isInvisible() && !var2.bridge$isSpectator() && !var2.bridge$isInvisibleToPlayer()) {
            if (this.method3(var1, var2)) {
               CosmeticMetadata var8 = CosmeticManager.method42(var2.bridge$getWornCosmetics(), CosmeticCategoryType.CLOAK, var4);
               if (var8 != null && this.field15.method34(var8) && var8.method4().method10().canShowCosmetic()) {
                  ResourceLocationBridge var9 = var8.method4().method5(var2);
                  this.method8(var1, var3, var2, var9, var5);
                  if (var8.method4().method22()) {
                     DriverViewLegacy.method21().method14().method3(var1, var8.method4(), () -> this.method8(var1, var3, var2, CosmeticManager.field2, var5));
                  }
               }
            }
         }
      }
   }

   private boolean method3(AbstractRenderContext var1, EntityPlayerBridge var2) {
      if (var2.bridge$isEmoting()) {
         EmoteController var3 = var2.bridge$getEmoteController();
         if (var3 != null && var3.animator != null) {
            if (!var3.shouldRenderCape()) {
               return false;
            }

            AnimatorEmoticonsController var4 = var3.animator;
            BOBJArmature var5 = ((AnimationMesh)var4.animation.meshes.get(0)).armature;
            var1.method7(
               var2x -> var4.setupMatrix((BOBJBone)var5.bones.get("low_body")),
               var2x -> var4.setupMatrix((BOBJBone)var5.bones.get("low_body"), var2x.method51())
            );
            var1.method5(0.0F, 0.0F, 180.0F);
            var1.translate(0.0, -0.35F, 0.0);
         }
      }

      return true;
   }

   public Optional<ResourceLocationBridge> method4(EntityPlayerBridge var1, int var2) {
      OwnedCosmetic var3 = CosmeticManager.method41(var1.bridge$getWornCosmetics(), CosmeticCategoryType.CLOAK, var2);
      return var3 != null ? Optional.of(var3.method5(var1)) : Optional.empty();
   }

   public int method5(EntityPlayerBridge var1) {
      return CosmeticManager.method47(var1.bridge$getWornCosmetics(), CosmeticCategoryType.CLOAK) ? 1 : 0;
   }

   @Override
   public boolean method9(EntityPlayerBridge var1) {
      return ThreadModuleDump63.method4().method41().method6().method6(var1);
   }

   private void method7(AbstractRenderContext var1, BridgeExtension2_7 var2, EntityPlayerBridge var3, ResourceLocationBridge var4, int var5) {
      if (ThreadModuleDump63.MC_VERSION < 26) {
         this.method8(var1, var2, var3, var4, var5);
      } else {
         BridgeExtension2_11 var6 = var1.method30();
         boolean var7 = var6.isOutlineBufferSource();
         RenderLayerBridge var8 = (var7 ? LunarRenderTypes.field44 : LunarRenderTypes.field43).get(var4);
         Optional var9 = var6.method2(var8);
         if (var9.isPresent()) {
            this.method8(var1, var2, var3, var4, var5);
            var6.method33(var8);
         }
      }
   }

   private void method8(AbstractRenderContext var1, BridgeExtension2_7 var2, EntityPlayerBridge var3, ResourceLocationBridge var4, int var5) {
      try {
         ClothCloakSolver var6 = CosmeticManager.method5(var3);
         var6.method17(System.nanoTime());
         double var7;
         double var9;
         double var11;
         if (ThreadModuleDump63.MC_VERSION >= 26) {
            MixinHelper var13 = var3.bridge$getClothCloakState();
            var7 = var13.method2();
            var9 = var13.method3();
            var11 = var13.method4();
         } else {
            Bridge5_11 var32 = (Bridge5_11)var3;
            var7 = (var32.bridge$getPosX() - var32.bridge$lastTickX()) * 20.0;
            var9 = (var32.bridge$getPosY() - var32.bridge$lastTickY()) * 20.0;
            var11 = (var32.bridge$getPosZ() - var32.bridge$lastTickZ()) * 20.0;
         }

         Vector3f var33 = new Vector3f((float)var7, (float)var9, (float)var11);
         float var14 = var3.bridge$getBodyRot();
         float var15 = (float)Math.toRadians(-var14);
         float var16 = (float)Math.sin(var15);
         float var17 = (float)Math.cos(var15);
         Vector3f var18 = new Vector3f(var33.x * var17 - var33.z * var16, var33.y, var33.x * var16 + var33.z * var17);
         float var19 = 0.06F;
         var6.method8().set(var18.x * var19);
         var6.method9().set(var18.y * var19);
         var6.method10().set(var18.z * var19);
         var6.method11().set(var3.bridge$isVisiblyCrouching());
         Optional var20 = this.field15.method37(var4, var3.bridge$getUniqueID());
         if (!var20.isEmpty()) {
            Bridge8Extension3 var21 = (Bridge8Extension3)var20.get();
            boolean var22 = false;
            Bridge3_4 var23 = var21.method2();
            if (var23 != null) {
               if (var23 instanceof Bridge3Extension_7 var24) {
                  var24.method3(true);
                  var22 = var24.method13();
                  if (var23 instanceof JitEmoteResource var25) {
                     var25.method9(var3);
                  }
               }

               if (var23 instanceof Alert5 var37) {
                  var37.method12();
               }
            } else {
               ShaderDebugMod var38 = ThreadModuleDump63.method4().method40().method74();
               if (var38.isValid() && var38.method24() != null && var38.method24().method39() == var21) {
                  var22 = true;
               }
            }

            var22 |= Client.method109().method53().method70().contains(var4);
            var22 |= this.field15.method70().contains(var4);
            var22 |= this.field15.method71().contains(var4);
            int var39 = (int)Bridge.method22().method7();
            int var40 = (int)Bridge.method22().method8();
            if (var1.method38()) {
               BridgeExtension2_11 var26 = var1.method30();
               Optional var27 = var26.method46();
               if (var27.isPresent()) {
                  var39 = (Integer)var27.get() & 0xFF;
                  var40 = (Integer)var27.get() >> 16 & 0xFF;
               }
            }

            if (CosmeticManager.field4 instanceof TexturedBoxRenderer.Extension var41) {
               var41.method1();
            }

            var1.push();
            if (var2 != null && Bridge.getMinecraftVersion().method21()) {
               var2.bridge$cloak().bridge$postRender(0.0625F);
               var1.method5(0.0F, 180.0F, 180.0F);
               var1.translate(0.0, -0.03, -0.15);
            } else if (Bridge.getMinecraftVersion().method19()) {
               var1.translate(0.0, -0.03F, 0.0);
            }

            if (var3.bridge$getChestItem() != null) {
               var1.translate(0.0, 0.0, 0.08);
            }

            var1.translate(0.08, 0.635, 0.14);
            var1.method4(2.76F, 1.0F, 0.0F, 0.0F);
            if (var3.bridge$isVisiblyCrouching()) {
               var1.method4(30.0F, 1.0F, 0.0F, 0.0F);
               if (Bridge.getMinecraftVersion() == Config.field1) {
                  var1.translate(0.0, 0.02F, 0.31F);
               } else {
                  var1.translate(0.0, 0.19F, 0.21);
               }
            }

            var1.scale(1.5F, 1.5F, 1.5F);
            com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer var42 = null;
            if (com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer.method34()) {
               var42 = ThreadModuleDump63.method4().method102().method13(var3.bridge$getUniqueID(), var4);
            }

            if (var42 != null && var42.method36()) {
               boolean var44 = var42.method26();
               if (var44) {
                  var42.method14(var1x -> MeshPassRunner.method1(var6, var1x));
               }

               if (!var44) {
                  var1.push();
                  var1.method36();
               }

               var42.method17(NativeImageBuilder.method8(var1));
               if (!var44) {
                  var1.pop();
               }
            }

            ClothCloakUpdater.Data var45;
            if (var1.method38() && var1.method30().isOutlineBufferSource()) {
               Bridge2_32 var46 = var1.method10(LunarRenderTypes.field44.get(var4));
               var46.method1();
               var45 = new ClothCloakUpdater.Data2(var46, var39, var40, var5);
            } else {
               Bridge2_32 var28 = var1.method10(LunarRenderTypes.field43.get(var4));
               var28.method1();
               var45 = new ClothCloakUpdater.Data(var28, var39, var40, var5);
            }

            Boolean var47 = null;
            if (Bridge.getMinecraftVersion().method21()) {
               var47 = var1.method7();
               var1.method20();
            }

            Runnable var29 = var45::method1;
            ClothCloakUpdater.Extension var30 = var45::vertex;
            method11(var6, var22, var30, var29);
            method10(var6, var22, var30, var29);
            method12(var6, var22, var30, var29);
            method13(var6, var22, var30, var29);
            method14(var6, var22, var30, var29);
            method15(var6, var22, var30, var29);
            var45.method2(BufferBuildMode.BATCHED);
            var1.pop();
            if (var47 != null && !var47) {
               var1.method21();
            }
         }
      } catch (Throwable var31) {
         throw var31;
      }
   }

   @Annotation2(min = 26)
   public static MixinHelper method9(Bridge5_11 var0, EntityPlayerBridge var1) {
      ItemStackRenderStateBridge var2 = var0.bridge$getArmor(EquipmentSlotBridge.CHEST);
      boolean var3 = var2 != null && var2.bridge$getLunarItemType() == BridgeType2_4.ELYTRA;
      if (!var3 && !var1.bridge$isInvisible() && !var1.bridge$isSpectator() && !var1.bridge$isInvisibleToPlayer()) {
         if (var1.bridge$isEmoting()) {
            EmoteController var4 = var1.bridge$getEmoteController();
            if (var4 != null && !var4.shouldRenderCape()) {
               return null;
            }
         }

         CosmeticMetadata var5 = CosmeticManager.method42(var1.bridge$getWornCosmetics(), CosmeticCategoryType.CLOAK, 0);
         return var5 != null && ThreadModuleDump63.method4().method53().method34(var5) && var5.method4().method10().canShowCosmetic()
            ? new MixinHelper(
               var5.method4().method5(var1),
               (var0.bridge$getPosX() - var0.bridge$lastTickX()) * 20.0,
               (var0.bridge$getPosY() - var0.bridge$lastTickY()) * 20.0,
               (var0.bridge$getPosZ() - var0.bridge$lastTickZ()) * 20.0
            )
            : null;
      } else {
         return null;
      }
   }

   public static void method10(ClothCloakSolver var0, boolean var1, ClothCloakUpdater.Extension var2, Runnable var3) {
      ClothCloakUpdater.Data3 var4 = var1 ? field8 : field14;

      for (int var5 = 0; var5 < 7; var5++) {
         var3.run();

         for (int var6 = 0; var6 < 5; var6++) {
            PhysicsPoint var7 = var0.method16()[5 - var6 - 1][var5];
            PhysicsPoint var8 = var0.method16()[5 - var6 - 1][var5 + 1];
            float var9 = ThreadModuleDump67.method16(var6, 0.0F, 4.0F, var4.field1, var4.field2);
            float var10 = ThreadModuleDump67.method16(var5, 0.0F, 7.0F, var4.field3, var4.field4);
            float var11 = ThreadModuleDump67.method16(var5 + 1, 0.0F, 7.0F, var4.field3, var4.field4);
            Vector3f var12 = new Vector3f(var7.method9()).mul(-1.0F);
            Vector3f var13 = new Vector3f(var8.method9()).mul(-1.0F);
            var2.vertex(var7.method7(), var9, var10, var12);
            var2.vertex(var8.method7(), var9, var11, var13);
         }

         var3.run();
      }
   }

   public static void method11(ClothCloakSolver var0, boolean var1, ClothCloakUpdater.Extension var2, Runnable var3) {
      ClothCloakUpdater.Data3 var4 = var1 ? field6 : field12;

      for (int var5 = 0; var5 < 7; var5++) {
         var3.run();

         for (int var6 = 0; var6 < 5; var6++) {
            PhysicsPoint var7 = var0.method16()[5 - var6 - 1][var5];
            PhysicsPoint var8 = var0.method16()[5 - var6 - 1][var5 + 1];
            float var9 = ThreadModuleDump67.method16(var6, 0.0F, 4.0F, var4.field1, var4.field2);
            float var10 = ThreadModuleDump67.method16(var5, 0.0F, 7.0F, var4.field3, var4.field4);
            float var11 = ThreadModuleDump67.method16(var5 + 1, 0.0F, 7.0F, var4.field3, var4.field4);
            Vector3f var12 = new Vector3f(var7.method9()).mul(0.03125F);
            Vector3f var13 = new Vector3f(var7.method7()).add(var12);
            Vector3f var14 = new Vector3f(var8.method9()).mul(0.03125F);
            Vector3f var15 = new Vector3f(var8.method7()).add(var14);
            var2.vertex(var15, var9, var11, var8.method9());
            var2.vertex(var13, var9, var10, var7.method9());
         }

         var3.run();
      }
   }

   public static void method12(ClothCloakSolver var0, boolean var1, ClothCloakUpdater.Extension var2, Runnable var3) {
      ClothCloakUpdater.Data3 var4 = var1 ? field5 : field11;
      var3.run();
      byte var5 = 4;

      for (int var6 = 0; var6 < 8; var6++) {
         PhysicsPoint var7 = var0.method16()[var5][var6];
         float var8 = ThreadModuleDump67.method16(var6, 0.0F, 7.0F, var4.field3, var4.field4);
         Vector3f var9 = new Vector3f();
         var7.method7().sub(var0.method16()[var5 - 1][var6].method7(), var9);
         var9.normalize();
         Vector3f var10 = new Vector3f(var7.method9()).mul(0.03125F);
         Vector3f var11 = new Vector3f(var7.method7()).add(var10);
         var2.vertex(var7.method7(), var4.field1, var8, var9);
         var2.vertex(var11, var4.field2, var8, var9);
      }

      var3.run();
   }

   public static void method13(ClothCloakSolver var0, boolean var1, ClothCloakUpdater.Extension var2, Runnable var3) {
      ClothCloakUpdater.Data3 var4 = var1 ? field7 : field13;
      var3.run();
      byte var5 = 0;

      for (int var6 = 0; var6 < 8; var6++) {
         PhysicsPoint var7 = var0.method16()[var5][var6];
         float var8 = ThreadModuleDump67.method16(var6, 0.0F, 7.0F, var4.field3, var4.field4);
         Vector3f var9 = new Vector3f();
         var7.method7().sub(var0.method16()[var5 + 1][var6].method7(), var9);
         var9.normalize();
         Vector3f var10 = new Vector3f(var7.method9()).mul(0.03125F);
         Vector3f var11 = new Vector3f(var7.method7()).add(var10);
         var2.vertex(var11, var4.field1, var8, var9);
         var2.vertex(var7.method7(), var4.field2, var8, var9);
      }

      var3.run();
   }

   public static void method14(ClothCloakSolver var0, boolean var1, ClothCloakUpdater.Extension var2, Runnable var3) {
      ClothCloakUpdater.Data3 var4 = var1 ? field4 : field10;
      var3.run();
      byte var5 = 7;

      for (int var6 = 0; var6 < 5; var6++) {
         PhysicsPoint var7 = var0.method16()[var6][var5];
         float var8 = ThreadModuleDump67.method16(var6, 0.0F, 4.0F, var4.field2, var4.field1);
         float var9 = var4.field4;
         float var10 = var4.field3;
         Vector3f var11 = new Vector3f();
         var7.method7().sub(var0.method16()[var6][var5 - 1].method7(), var11);
         var11.normalize();
         Vector3f var12 = new Vector3f(var7.method9()).mul(0.03125F);
         Vector3f var13 = new Vector3f(var7.method7()).add(var12);
         var2.vertex(var13, var8, var10, var11);
         var2.vertex(var7.method7(), var8, var9, var11);
      }

      var3.run();
   }

   public static void method15(ClothCloakSolver var0, boolean var1, ClothCloakUpdater.Extension var2, Runnable var3) {
      ClothCloakUpdater.Data3 var4 = var1 ? field3 : field9;
      var3.run();
      byte var5 = 0;

      for (int var6 = 0; var6 < 5; var6++) {
         PhysicsPoint var7 = var0.method16()[var6][var5];
         float var8 = ThreadModuleDump67.method16(var6, 0.0F, 4.0F, var4.field2, var4.field1);
         float var9 = var4.field4;
         float var10 = var4.field3;
         Vector3f var11 = new Vector3f();
         var7.method7().sub(var0.method16()[var6][var5 + 1].method7(), var11);
         var11.normalize();
         Vector3f var12 = new Vector3f(var7.method9()).mul(0.03125F);
         Vector3f var13 = new Vector3f(var7.method7()).add(var12);
         var2.vertex(var7.method7(), var8, var10, var11);
         var2.vertex(var13, var8, var9, var11);
      }

      var3.run();
   }

   private static class Data {
      private final Bridge2_32 field1;
      private final int field2;
      private final int field3;
      private final int field4;
      private boolean field5;
      private boolean field6;
      private Vector3f field7;
      private float field8;
      private float field9;
      private Vector3f field10;

      public Data(Bridge2_32 var1, int var2, int var3, int var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = true;
         this.field6 = true;
      }

      public void vertex(Vector3f var1, float var2, float var3, Vector3f var4) {
         this.field6 = false;
         this.method3(var1, var2, var3, var4);
         this.method4();
         if (this.field5) {
            this.method4();
            this.field5 = false;
         }
      }

      public void method1() {
         if (!this.field6) {
            this.method4();
            this.field6 = true;
            this.field5 = true;
         }
      }

      public void method2(BufferBuildMode var1) {
         this.method1();
         this.field1.method17(var1);
      }

      protected void method3(Vector3f var1, float var2, float var3, Vector3f var4) {
         this.field7 = var1;
         this.field8 = var2;
         this.field9 = var3;
         this.field10 = var4;
      }

      protected void method4() {
         this.field1.method20(this.field7.x, this.field7.y, this.field7.z);
         if (Bridge.getMinecraftVersion().method19()) {
            this.field1.method9(this.field4);
            this.field1.method10(this.field8, this.field9);
            this.field1.method13(0, 10);
            this.field1.method12(this.field2, this.field3);
         } else {
            this.field1.method10(this.field8, this.field9);
            this.field1.method9(this.field4);
         }

         this.field1.method21(this.field10.x, this.field10.y, this.field10.z);
         this.field1.method16();
      }
   }

   public static class Data2 extends ClothCloakUpdater.Data {
      private ClothCloakUpdater.Data2.Data field11;
      private ClothCloakUpdater.Data2.Data field12;
      private ClothCloakUpdater.Data2.Data field13;
      private ClothCloakUpdater.Data2.Data field14;
      private int index = 0;

      public Data2(Bridge2_32 var1, int var2, int var3, int var4) {
         super(var1, var2, var3, var4);
      }

      @Override
      public void vertex(Vector3f var1, float var2, float var3, Vector3f var4) {
         this.field11 = this.field12;
         this.field12 = this.field13;
         this.field13 = this.field14;
         this.field14 = new ClothCloakUpdater.Data2.Data(var1, var2, var3, var4);
         if (this.index >= 3 && this.index % 2 == 1) {
            this.method2(this.field11.field1, this.field11.field2, this.field11.field3, this.field11.field4);
            this.CRHHHIHCOICHROCIOOHOIIORCOORHH();
            this.method2(this.field13.field1, this.field13.field2, this.field13.field3, this.field13.field4);
            this.CRHHHIHCOICHROCIOOHOIIORCOORHH();
            this.method2(this.field14.field1, this.field14.field2, this.field14.field3, this.field14.field4);
            this.CRHHHIHCOICHROCIOOHOIIORCOORHH();
            this.method2(this.field12.field1, this.field12.field2, this.field12.field3, this.field12.field4);
            this.CRHHHIHCOICHROCIOOHOIIORCOORHH();
         }

         this.index++;
      }

      @Override
      public void method1() {
         this.index = 0;
      }

      private static class Data {
         protected Vector3f field1;
         protected float field2;
         protected float field3;
         protected Vector3f field4;

         @Generated
         public Data(Vector3f var1, float var2, float var3, Vector3f var4) {
            this.field1 = var1;
            this.field2 = var2;
            this.field3 = var3;
            this.field4 = var4;
         }
      }
   }

   public class Data3 {
      private final float field1;
      private final float field2;
      private final float field3;
      private final float field4;

      public Data3(float var1, float var2, float var3, float var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public float method1() {
         return this.field1;
      }

      public float method2() {
         return this.field2;
      }

      public float method3() {
         return this.field3;
      }

      public float method4() {
         return this.field4;
      }
   }

   @FunctionalInterface
   public interface Extension {
      void vertex(Vector3f var1, float var2, float var3, Vector3f var4);
   }
}
