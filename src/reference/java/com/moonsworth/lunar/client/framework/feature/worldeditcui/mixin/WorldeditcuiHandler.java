package com.moonsworth.lunar.client.framework.feature.worldeditcui.mixin;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.client.framework.feature.worldeditcui.CuboidSelection;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.worldeditcui.WorldeditCui;
import com.moonsworth.lunar.client.framework.Ref;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public final class WorldeditcuiHandler implements Worldeditcui {
   public WorldeditcuiHandler() {
   }

   @Override
   public void method1(AbstractRenderContext bridgeextension_91, WorldeditCui worldeditcui2) {
      if (worldeditcui2.method13().method2() instanceof CuboidSelection worldeditcui2base224) {
         int number5 = worldeditcui2base224.getPoints().size();
         if (number5 >= 2) {
            EntityRenderDispatcherBridge bridge2_436 = Ref.method13();
            if (bridge2_436 != null) {
               bridgeextension_91.push();
               bridgeextension_91.translate(-bridge2_436.bridge$renderPosX(), -bridge2_436.bridge$renderPosY(), -bridge2_436.bridge$renderPosZ());
               BufferBuilderBridge bridge_287;
               if (bridgeextension_91.method38()) {
                  bridge_287 = bridgeextension_91.method30().method12(1.0F, true);
               } else {
                  bridge_287 = bridgeextension_91.method11(1.0F);
               }

               bridgeextension_91.method25(1.0F, 1.0F, 1.0F, 1.0F);
               Vector3dc vector3dc8 = worldeditcui2base224.getPoints().get(0);
               Vector3dc vector3dc9 = worldeditcui2base224.getPoints().get(1);
               Vector3d vector3d10 = vector3dc8.min(vector3dc9, new Vector3d());
               Vector3d vector3d11 = vector3dc8.max(vector3dc9, new Vector3d()).add(1.0, 1.0, 1.0);
               method2(bridge_287, worldeditcui2.method17(), vector3d10, vector3d11);
               bridge_287.end();
               bridgeextension_91.pop();
            }
         }
      }
   }

   public static void method2(BufferBuilderBridge bridge_280, ColorOption lightingextension42221, Vector3dc vector3dc2, Vector3dc vector3dc3) {
      bridge_280.method1(lightingextension42221.method1(0.0F));
      bridge_280.method3(vector3dc2.x(), vector3dc2.y(), vector3dc2.z(), vector3dc3.x(), vector3dc2.y(), vector3dc2.z());
      bridge_280.method3(vector3dc3.x(), vector3dc2.y(), vector3dc2.z(), vector3dc3.x(), vector3dc2.y(), vector3dc3.z());
      bridge_280.method3(vector3dc2.x(), vector3dc2.y(), vector3dc3.z(), vector3dc2.x(), vector3dc2.y(), vector3dc2.z());
      bridge_280.method3(vector3dc3.x(), vector3dc2.y(), vector3dc3.z(), vector3dc2.x(), vector3dc2.y(), vector3dc3.z());
      bridge_280.method3(vector3dc2.x(), vector3dc3.y(), vector3dc2.z(), vector3dc3.x(), vector3dc3.y(), vector3dc2.z());
      bridge_280.method3(vector3dc3.x(), vector3dc3.y(), vector3dc2.z(), vector3dc3.x(), vector3dc3.y(), vector3dc3.z());
      bridge_280.method3(vector3dc2.x(), vector3dc3.y(), vector3dc3.z(), vector3dc2.x(), vector3dc3.y(), vector3dc2.z());
      bridge_280.method3(vector3dc3.x(), vector3dc3.y(), vector3dc3.z(), vector3dc2.x(), vector3dc3.y(), vector3dc3.z());
      bridge_280.method3(vector3dc2.x(), vector3dc2.y(), vector3dc2.z(), vector3dc2.x(), vector3dc3.y(), vector3dc2.z());
      bridge_280.method3(vector3dc3.x(), vector3dc2.y(), vector3dc2.z(), vector3dc3.x(), vector3dc3.y(), vector3dc2.z());
      bridge_280.method3(vector3dc3.x(), vector3dc2.y(), vector3dc3.z(), vector3dc3.x(), vector3dc3.y(), vector3dc3.z());
      bridge_280.method3(vector3dc2.x(), vector3dc2.y(), vector3dc3.z(), vector3dc2.x(), vector3dc3.y(), vector3dc3.z());
   }
}
