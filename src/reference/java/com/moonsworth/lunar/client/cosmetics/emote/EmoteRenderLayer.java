package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.feature.ModuleType;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert5;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.skin_n_bones.api.animation.AnimationMesh;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJArmature;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJBone;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;

public class EmoteRenderLayer implements MExtension<Bridge5_11, EntityPlayerBridge> {
   private final CosmeticManager field1;

   public EmoteRenderLayer(CosmeticManager var1) {
      this.field1 = var1;
   }

   public void method1(
      BridgeExtension3_5 var1, Bridge5_11 var2, BridgeExtension2_7 var3, float var4, float var5, float var6, float var7, float var8, float var9, float value
   ) {
      CosmeticMetadata var11 = this.field1.getProvider(var2.bridge$getUniqueID(), CosmeticCategoryType.WINGS);
      if (var11 != null) {
         OwnedCosmetic var12 = var11.method4();
         if (!var12.method10().canShowCosmetic() || var12.method19()) {
            return;
         }

         if (var2.bridge$isInvisible()) {
            return;
         }

         if (this.field1.method36(var12, var2.bridge$getUniqueID()).isEmpty()) {
            return;
         }

         var1.push();
         if (ThreadModuleDump63.method4().method45().method9(var2)) {
            EmoteController var13 = (EmoteController)EmoteController.get(var2);
            if (var13.emote != null && !var13.emote.shouldRenderWings()) {
               return;
            }

            BOBJArmature var14 = ((AnimationMesh)var13.animator.animation.meshes.get(0)).armature;
            var13.animator.setupMatrix((BOBJBone)var14.bones.get("low_body"));
            var1.method4(180.0F, 0.0F, 0.0F, 1.0F);
            var1.translate(0.0, -0.35, 0.0);
         }

         ModuleType.BODY.translate(var3);
         if (var2.bridge$isVisiblyCrouching() && !Bridge.getMinecraftVersion().equals(Config.field1)) {
            var1.translate(0.0, 0.2F, 0.0);
         }

         ResourceLocationBridge var17 = var12.method3();
         if (var12.method2().method9()) {
            Bridge8Extension3 var18 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var17);
            if (var18.method2() instanceof Alert5 var15) {
               var15.method12();
            }
         }

         CosmeticManager.field5.method1(var1, 0.13F, 0.0625F, var17);
         if (var12.method22()) {
            DriverViewLegacy.method21().method14().method3(var1, var12, () -> CosmeticManager.field5.method1(var1, 0.13F, 0.0625F, var17));
         }

         var1.pop();
      }
   }

   public void method2(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, int var4, int var5) {
      OwnedCosmetic var6 = CosmeticManager.method41(var2.bridge$getWornCosmetics(), CosmeticCategoryType.WINGS, 0);
      if (var6 != null) {
         if (!var6.method10().canShowCosmetic() || var6.method19()) {
            return;
         }

         if (var2.bridge$isInvisible()) {
            return;
         }

         EmoteController var7 = null;
         if (var2.bridge$isEmoting()) {
            var7 = (EmoteController)var2.bridge$getEmoteController();
            if (var7 != null && var7.emote != null && !var7.emote.shouldRenderWings()) {
               return;
            }
         }

         if (this.field1.method36(var6, var2.bridge$getUniqueID()).isEmpty()) {
            return;
         }

         var1.push();
         if (var7 != null && var7.animator != null) {
            BOBJArmature var8 = ((AnimationMesh)var7.animator.animation.meshes.get(0)).armature;
            var7.animator.setupMatrix((BOBJBone)var8.bones.get("low_body"), var1.method51());
            var1.method4(180.0F, 0.0F, 0.0F, 1.0F);
            var1.translate(0.0, -0.35, 0.0);
         }

         if (var2.bridge$isVisiblyCrouching()) {
            var1.translate(0.0, 0.2F, 0.0);
         }

         ResourceLocationBridge var12 = var6.method3();
         if (var6.method2().method9()) {
            Bridge8Extension3 var9 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var12);
            if (var9.method2() instanceof Alert5 var11) {
               var11.method12();
            }
         }

         CosmeticManager.field5.method3(var1, 0.13F, 0.0625F, var12, false, var5);
         if (var6.method22()) {
            DriverViewLegacy.method21().method14().method3(var1, var6, () -> CosmeticManager.field5.method3(var1, 0.13F, 0.0625F, var12, true, var5));
         }

         var1.pop();
      }
   }

   public int method3(EntityPlayerBridge var1) {
      return 1;
   }

   @Annotation2(min = 6)
   public Optional<RenderLayerBridge> method6(ResourceLocationBridge var1) {
      return CosmeticManager.field5.method4(var1);
   }

   public Optional<ResourceLocationBridge> method5(EntityPlayerBridge var1, int var2) {
      OwnedCosmetic var3 = CosmeticManager.method41(var1.bridge$getWornCosmetics(), CosmeticCategoryType.WINGS, 0);
      return var3 != null ? Optional.of(var3.method3()) : Optional.empty();
   }

   public boolean method9(EntityPlayerBridge var1) {
      return ThreadModuleDump63.method4().method41().method6().method6(var1);
   }
}
