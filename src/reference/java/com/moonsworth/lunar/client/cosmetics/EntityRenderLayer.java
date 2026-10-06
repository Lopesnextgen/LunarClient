package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.LayerCapeBridge;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.RendererLivingEntityLayerBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticLayerExtension;
import com.moonsworth.lunar.client.cosmetics.CosmeticLayerRendererImpl;
import com.moonsworth.lunar.client.cosmetics.ClothCloakUpdater;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteRenderLayer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.skin_n_bones.api.animation.AnimationMesh;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJArmature;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJBone;

@VersionGate(min = 6)
public abstract class EntityRenderLayer {
   public static final EntityRenderLayer HOLOGRAMS = new EntityRenderLayer() {
      final EmoteRenderLayer field7 = Ref.method4().method53().method75();

      @Override
      public void render(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, float value4, int number5) {
         if (this.field7.method9(bridgeextension2222)) {
            for (int index6 = 0; index6 < this.field7.method3(bridgeextension2222); index6++) {
               Optional optional7 = this.field7.method5(bridgeextension2222, index6);
               if (optional7.isPresent()) {
                  bridgeextension2_111.push();
                  this.field7.method2(bridgeextension2_111, bridgeextension2222, bridgeextension2_73, index6, number5);
                  bridgeextension2_111.pop();
               }
            }
         }
      }
   };
   public static final EntityRenderLayer COSMETICS = new EntityRenderLayer() {
      final CosmeticLayerExtension field7 = Ref.method4().method53().method74();

      @Override
      public void render(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, float value4, int number5) {
         if (this.field7.method9(bridgeextension2222)) {
            for (int index6 = 0; index6 < this.field7.method4(bridgeextension2222); index6++) {
               Optional optional7 = this.field7.method3(bridgeextension2222, index6);
               if (optional7.isPresent()) {
                  bridgeextension2_111.push();
                  this.field7.method2(bridgeextension2_111, bridgeextension2222, bridgeextension2_73, index6, number5);
                  bridgeextension2_111.pop();
               }
            }
         }
      }
   };
   public static final EntityRenderLayer EMOTES = new EntityRenderLayer() {
      @Override
      public void render(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, float value4, int number5) {
         if (Ref.method4().method41().method6().method6(bridgeextension2222)) {
            bridgeextension2_111.push();
            if (bridgeextension2222.bridge$isEmoting()) {
               EmoteController emotecontroller6 = (EmoteController)bridgeextension2222.bridge$getEmoteController();
               BOBJArmature bobjarmature7 = ((AnimationMesh)emotecontroller6.animator.animation.meshes.get(0)).armature;
               emotecontroller6.animator.setupMatrix((BOBJBone)bobjarmature7.bones.get("low_body"), bridgeextension2_111.method51());
            }

            bridgeextension2_111.translate(0.0, 0.375, 0.0);
            bridgeextension2_111.method5(0.0F, 0.0F, 180.0F);
            LayerCapeBridge bridge2_108 = ((RendererLivingEntityLayerBridge)Bridge.method9().bridge$getEntityRenderDispatcher().bridge$defaultPlayerRenderer()).bridge$getLayerCape();
            if (bridge2_108 != null) {
               bridge2_108.bridge$renderModern(bridgeextension2_111, bridgeextension2222, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            }

            bridgeextension2_111.pop();
         }
      }
   };
   public static final EntityRenderLayer CAPES = new EntityRenderLayer() {
      final ClothCloakUpdater field7 = Ref.method4().method53().method77();

      @Override
      public void render(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, float value4, int number5) {
         if (this.field7.method9(bridgeextension2222)) {
            for (int index6 = 0; index6 < this.field7.method5(bridgeextension2222); index6++) {
               Optional optional7 = this.field7.method4(bridgeextension2222, index6);
               if (optional7.isPresent()) {
                  bridgeextension2_111.method51().bridge$pushPose();
                  this.field7.method2(bridgeextension2_111, bridgeextension2222, bridgeextension2_73, index6, number5);
                  bridgeextension2_111.method51().bridge$popPose();
               }
            }
         }
      }
   };
   public static final EntityRenderLayer COMPANIONS = new EntityRenderLayer() {
      final CosmeticLayerRendererImpl field7 = Ref.method4().method53().method78();

      @Override
      public void render(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, float value4, int number5) {
         if (this.field7.method4(bridgeextension2222)) {
            for (int index6 = 0; index6 < this.field7.method16(bridgeextension2222); index6++) {
               bridgeextension2_111.method51().bridge$pushPose();
               this.field7.method9(bridgeextension2222, bridgeextension2_73, index6);
               this.field7.method5(bridgeextension2_111, bridgeextension2222, bridgeextension2_73, index6, number5);
               bridgeextension2_111.method51().bridge$popPose();
            }
         }
      }
   };
   public static final EntityRenderLayer SELF_DUMMY = new EntityRenderLayer() {
      @Override
      public void render(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, float value4, int number5) {
         if (!bridgeextension2222.bridge$isSelf() && !bridgeextension2222.bridge$isDummyMannequin()) {
            bridgeextension2_111.push();
            bridgeextension2_111.method4(value4, 0.0F, 1.0F, 0.0F);
            Bridge.method14().method8(bridgeextension2_111, bridgeextension2222);
            bridgeextension2_111.pop();
         }
      }
   };

   public EntityRenderLayer() {
   }

   public abstract void render(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, float value4, int number5);
}
