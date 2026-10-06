package com.moonsworth.lunar.bridge;

import org.joml.Vector3d;
import org.joml.Vector3f;

public class Bridge8_5 {
   public Bridge8_5() {
   }

   public static void method1(Vector3f vector3f0) {
      EntityRenderDispatcherBridge bridge2_431 = Bridge.method9().bridge$getEntityRenderDispatcher();
      vector3f0.x = vector3f0.x - (float)bridge2_431.bridge$renderPosX();
      vector3f0.y = vector3f0.y - (float)bridge2_431.bridge$renderPosY();
      vector3f0.z = vector3f0.z - (float)bridge2_431.bridge$renderPosZ();
   }

   public static void method2(AbstractRenderContext bridgeextension_90, Vector3f vector3f1) {
      EntityRenderDispatcherBridge bridge2_432 = Bridge.method9().bridge$getEntityRenderDispatcher();
      bridgeextension_90.translate(vector3f1.x, vector3f1.y, vector3f1.z);
      bridgeextension_90.method4((float)(-bridge2_432.bridge$playerViewY()), 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method4((float)bridge2_432.bridge$playerViewX(), 1.0F, 0.0F, 0.0F);
   }

   public static void method3(AbstractRenderContext bridgeextension_90, Vector3d vector3d1) {
      EntityRenderDispatcherBridge bridge2_432 = Bridge.method9().bridge$getEntityRenderDispatcher();
      bridgeextension_90.translate(vector3d1.x, vector3d1.y, vector3d1.z);
      bridgeextension_90.method4((float)(-bridge2_432.bridge$playerViewY()), 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method4((float)bridge2_432.bridge$playerViewX(), 1.0F, 0.0F, 0.0F);
   }
}
