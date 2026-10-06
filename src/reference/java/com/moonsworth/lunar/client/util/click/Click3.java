package com.moonsworth.lunar.client.util.click;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_10;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.SExtension;
import com.moonsworth.lunar.client.cosmetics.CosmeticLayerExtension;
import com.moonsworth.lunar.client.cosmetics.CosmeticLayerRendererImpl;
import com.moonsworth.lunar.client.cosmetics.ClothCloakUpdater;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteRenderLayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.skin_n_bones.api.animation.AnimationMesh;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJArmature;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJBone;

@Annotation2(min = 6)
public abstract class Click3 {
   public static final Click3 field1 = new Click3() {
      final EmoteRenderLayer field7 = ThreadModuleDump63.method4().method53().method75();

      @Override
      public void method1(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, float var4, int var5) {
         if (this.field7.method9(var2)) {
            for (int var6 = 0; var6 < this.field7.method3(var2); var6++) {
               Optional var7 = this.field7.method5(var2, var6);
               if (var7.isPresent()) {
                  var1.push();
                  this.field7.method2(var1, var2, var3, var6, var5);
                  var1.pop();
               }
            }
         }
      }
   };
   public static final Click3 field2 = new Click3() {
      final CosmeticLayerExtension field7 = ThreadModuleDump63.method4().method53().method74();

      @Override
      public void method1(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, float var4, int var5) {
         if (this.field7.method9(var2)) {
            for (int var6 = 0; var6 < this.field7.method4(var2); var6++) {
               Optional var7 = this.field7.method3(var2, var6);
               if (var7.isPresent()) {
                  var1.push();
                  this.field7.method2(var1, var2, var3, var6, var5);
                  var1.pop();
               }
            }
         }
      }
   };
   public static final Click3 field3 = new Click3() {
      @Override
      public void method1(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, float var4, int var5) {
         if (ThreadModuleDump63.method4().method41().method6().method6(var2)) {
            var1.push();
            if (var2.bridge$isEmoting()) {
               EmoteController var6 = (EmoteController)var2.bridge$getEmoteController();
               BOBJArmature var7 = ((AnimationMesh)var6.animator.animation.meshes.get(0)).armature;
               var6.animator.setupMatrix((BOBJBone)var7.bones.get("low_body"), var1.method51());
            }

            var1.translate(0.0, 0.375, 0.0);
            var1.method5(0.0F, 0.0F, 180.0F);
            Bridge2_10 var8 = ((SExtension)Bridge.method9().bridge$getEntityRenderDispatcher().bridge$defaultPlayerRenderer()).bridge$getLayerCape();
            if (var8 != null) {
               var8.bridge$renderModern(var1, var2, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            }

            var1.pop();
         }
      }
   };
   public static final Click3 field4 = new Click3() {
      final ClothCloakUpdater field7 = ThreadModuleDump63.method4().method53().method77();

      @Override
      public void method1(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, float var4, int var5) {
         if (this.field7.method9(var2)) {
            for (int var6 = 0; var6 < this.field7.method5(var2); var6++) {
               Optional var7 = this.field7.method4(var2, var6);
               if (var7.isPresent()) {
                  var1.method51().bridge$pushPose();
                  this.field7.method2(var1, var2, var3, var6, var5);
                  var1.method51().bridge$popPose();
               }
            }
         }
      }
   };
   public static final Click3 field5 = new Click3() {
      final CosmeticLayerRendererImpl field7 = ThreadModuleDump63.method4().method53().method78();

      @Override
      public void method1(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, float var4, int var5) {
         if (this.field7.method4(var2)) {
            for (int var6 = 0; var6 < this.field7.method16(var2); var6++) {
               var1.method51().bridge$pushPose();
               this.field7.method9(var2, var3, var6);
               this.field7.method5(var1, var2, var3, var6, var5);
               var1.method51().bridge$popPose();
            }
         }
      }
   };
   public static final Click3 field6 = new Click3() {
      @Override
      public void method1(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, float var4, int var5) {
         if (!var2.bridge$isSelf() && !var2.bridge$isDummyMannequin()) {
            var1.push();
            var1.method4(var4, 0.0F, 1.0F, 0.0F);
            Bridge.method14().method8(var1, var2);
            var1.pop();
         }
      }
   };

   public abstract void method1(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, float var4, int var5);
}
