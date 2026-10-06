package com.moonsworth.lunar.client.cosmetics;

import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.client.network.apollo.LimbApolloHandler;
import com.moonsworth.lunar.client.feature.ModuleType;
import com.moonsworth.lunar.client.cosmetics.AbstractCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticModelRenderer;
import com.moonsworth.lunar.client.config.PerformanceSettings;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.emote.CosmeticType;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.skin_n_bones.api.animation.AnimationMesh;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJArmature;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJBone;

public class CosmeticLayerExtension implements MExtension<Bridge5_11, EntityPlayerBridge> {
   private final CosmeticManager field1;

   public CosmeticLayerExtension(CosmeticManager var1) {
      this.field1 = var1;
   }

   public void method1(
      BridgeExtension3_5 var1, Bridge5_11 var2, BridgeExtension2_7 var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10
   ) {
      if (!var2.bridge$isInvisible() && !var2.bridge$isInvisibleToPlayer() && !var2.bridge$isSpectator()) {
         List var11 = this.field1.method17(var2.bridge$getUniqueID(), var0 -> var0 == CosmeticType.BODYWEAR || var0 == CosmeticType.HAT);
         if (!var11.isEmpty()) {
            EmoteController var12 = (EmoteController)EmoteController.get(var2);
            boolean var13 = ThreadModuleDump63.method4().method45().method9(var2);

            for (CosmeticMetadata var15 : var11) {
               OwnedCosmetic var16 = var15.method4();
               if (var16 != null && !var16.method19() && var16.method10().canShowCosmetic()) {
                  AbstractCosmetic var17 = this.field1.method7(var16.method2());
                  if (var17 != null) {
                     ModuleType var18 = var17.method5();
                     if (!this.method8(var17, var2)) {
                        var1.push();
                        if (var13) {
                           if (var16.method10().getRenderAs() == CosmeticType.HAT) {
                              if (var12.animator != null) {
                                 BOBJArmature var19 = ((AnimationMesh)var12.animator.animation.meshes.get(0)).armature;
                                 var12.animator.setupMatrix((BOBJBone)var19.bones.get("head"));
                              }
                           } else {
                              if (var18 != ModuleType.BODY) {
                                 var1.pop();
                                 continue;
                              }

                              if (var12.animator != null) {
                                 BOBJArmature var20 = ((AnimationMesh)var12.animator.animation.meshes.get(0)).armature;
                                 var12.animator.setupMatrix((BOBJBone)var20.bones.get("low_body"));
                              }
                           }
                        }

                        var1.method1(1.0F, 1.0F, 1.0F, 1.0F);
                        CosmeticModelRenderer.method1(var1, var2, var3, var15, var17, var10, var7, var6, false, var13, true);
                        var1.pop();
                     }
                  }
               }
            }
         }
      }
   }

   @Annotation2(min = 6)
   public void method2(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, int var4, int var5) {
      boolean var6 = var2.bridge$isEmoting();
      CosmeticMetadata var7 = CosmeticManager.method40(var2.bridge$getWornCosmetics(), var0 -> var0 == CosmeticType.BODYWEAR || var0 == CosmeticType.HAT, var4);
      if (var7 != null) {
         OwnedCosmetic var8 = var7.method4();
         CosmeticCategoryType var9 = var8.method10();
         if (var9.canShowCosmetic()) {
            AbstractCosmetic var10 = this.field1.method7(var8.method2());
            if (var10 != null) {
               if (!this.method8(var10, var2)) {
                  if (var6) {
                     boolean var11 = var10.method5() == ModuleType.BODY;
                     if (!var11 && var9.getRenderAs() != CosmeticType.HAT) {
                        return;
                     }

                     EmoteController var12 = var2.bridge$getEmoteController();
                     if (var12 != null) {
                        if (var12.emote != null && !var12.emote.shouldRenderBodywear()) {
                           return;
                        }

                        if (var12.animator != null) {
                           BOBJArmature var13 = ((AnimationMesh)var12.animator.animation.meshes.get(0)).armature;
                           String var14 = var11 ? "low_body" : "head";
                           var12.animator.setupMatrix((BOBJBone)var13.bones.get(var14), var1.method51());
                           if (var11) {
                              var1.translate(0.0, 0.35F, 0.0);
                           }
                        }
                     }
                  }

                  ResourceLocationBridge var15 = this.method3(var2, var4).orElse(null);
                  if (var15 != null) {
                     RenderLayerBridge var16 = this.method6(var15).get();
                     if (ThreadModuleDump63.MC_VERSION >= 39 && var1.isOutlineBufferSource() && !var16.bridge$isOutline()) {
                        var16 = var16.bridge$getOutline().orElse(null);
                        if (var16 == null) {
                           return;
                        }
                     }

                     CosmeticModelRenderer.method3(var1, var2, var3, var16, var7, var10, 0.0F, false, var6, var5);
                  }
               }
            }
         }
      }
   }

   public Optional<ResourceLocationBridge> method3(EntityPlayerBridge var1, int var2) {
      CosmeticMetadata var3 = CosmeticManager.method40(var1.bridge$getWornCosmetics(), var0 -> var0 == CosmeticType.BODYWEAR || var0 == CosmeticType.HAT, var2);
      if (var3 == null) {
         return Optional.empty();
      }

      OwnedCosmetic var4 = var3.method4();
      AbstractCosmetic var5 = this.field1.method7(var4.method2());
      if (var5 == null) {
         return Optional.empty();
      }

      ResourceLocationBridge var6;
      if (var5.isDynamic()) {
         var6 = var4.method5(var1).bridge$getPath().lastIndexOf(46) != -1 ? var4.method5(var1) : var5.method3();
      } else {
         var6 = var5.method3();
      }

      Optional var7 = this.field1.method37(var6, var1.bridge$getUniqueID());
      if (var7.isEmpty()) {
         return Optional.empty();
      }

      var7.map(Bridge8Extension3::method2).filter(Alert5.class::isInstance).map(Alert5.class::cast).ifPresent(Alert5::restartAnimation);
      return Optional.of(var6);
   }

   public int method4(EntityPlayerBridge var1) {
      if (!var1.bridge$isInvisible() && !var1.bridge$isSpectator()) {
         Rewind var2 = ThreadModuleDump63.method4().method40().method85();
         boolean var3 = var2.method17(var0 -> var0.method45().method19() || !var0.method45().method15().isFixedToPlayer());
         return (
                  var1.bridge$isInvisibleToPlayer()
                     || var1.bridge$isSelf() && ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getThirdPersonView() == 0
               )
               && !var3
            ? 0
            : CosmeticManager.method44(var1.bridge$getWornCosmetics(), var0 -> var0 == CosmeticType.BODYWEAR || var0 == CosmeticType.HAT);
      } else {
         return 0;
      }
   }

   @Override
   public Optional<RenderLayerBridge> method6(ResourceLocationBridge var1) {
      return Optional.of(LunarRenderTypes.field45.get(var1));
   }

   @Override
   public boolean method8() {
      return ThreadModuleDump63.MC_VERSION >= 8;
   }

   @Override
   public boolean method9(EntityPlayerBridge var1) {
      return ThreadModuleDump63.method4().method41().method6().method6(var1);
   }

   private boolean method8(AbstractCosmetic var1, EntityPlayerBridge var2) {
      if (!var1.method5().hasArmorSlot()) {
         return false;
      }

      ItemStackRenderStateBridge var3 = var2.bridge$getArmor(var1.method5().getArmorSlot());
      if (var3 != null && !var3.bridge$isEmpty()) {
         EquipmentSlotBridge var4 = var1.method5().getArmorSlot();
         PerformanceSettings var5 = ThreadModuleDump63.method4().method41().method7();
         Optional var6 = ThreadModuleDump63.method4().method84().method3(LimbModule.class);
         if (var6.isPresent()) {
            LimbApolloHandler var7 = (LimbApolloHandler)var6.get();
            Set var8 = var7.method6().get(var2.bridge$getUniqueID());
            if (var8 != null) {
               if (var4 == EquipmentSlotBridge.HEAD && var8.contains(ArmorPiece.HELMET)) {
                  return false;
               }

               if (var4 == EquipmentSlotBridge.CHEST && var8.contains(ArmorPiece.CHESTPLATE)) {
                  return false;
               }

               if (var4 == EquipmentSlotBridge.LEGS && var8.contains(ArmorPiece.LEGGINGS)) {
                  return false;
               }

               if (var4 == EquipmentSlotBridge.FEET && var8.contains(ArmorPiece.BOOTS)) {
                  return false;
               }
            }
         }

         OverlayMod var9 = ThreadModuleDump63.method4().method40().method84();
         if (var4 == EquipmentSlotBridge.HEAD && var9.method41(var2)) {
            return false;
         }

         if (var4 == EquipmentSlotBridge.CHEST && var9.isHideChestEnabled(var2)) {
            return false;
         }

         if (var4 == EquipmentSlotBridge.LEGS && var9.isHideLeggingsEnabled(var2)) {
            return false;
         }

         if (var4 == EquipmentSlotBridge.FEET && var9.isHideBootsEnabled(var2)) {
            return false;
         }

         if (ThreadModuleDump63.MC_VERSION >= 6) {
            Optional var10 = Bridge.method5();
            if (var10.isPresent() && ((Slayer2)var10.get()).getConfig().hasFastRender()) {
               return true;
            }
         }

         return !var1.method1();
      } else {
         return false;
      }
   }
}
