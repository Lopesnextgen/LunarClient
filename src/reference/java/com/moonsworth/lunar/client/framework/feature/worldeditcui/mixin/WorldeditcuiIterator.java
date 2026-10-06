package com.moonsworth.lunar.client.framework.feature.worldeditcui.mixin;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.client.framework.feature.worldeditcui.WorldEditSelectionBase;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.worldeditcui.WorldeditCui;
import com.moonsworth.lunar.client.framework.Ref;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public final class WorldeditcuiIterator implements Worldeditcui {
   public WorldeditcuiIterator() {
   }

   @Override
   public void method1(AbstractRenderContext bridgeextension_91, WorldeditCui worldeditcui2) {
      if (worldeditcui2.method13().method2() instanceof WorldEditSelectionBase worldeditcui2handler3) {
         EntityRenderDispatcherBridge bridge2_4310 = Ref.method13();
         if (bridge2_4310 == null) {
            return;
         }

         bridgeextension_91.push();
         bridgeextension_91.translate(-bridge2_4310.bridge$renderPosX(), -bridge2_4310.bridge$renderPosY(), -bridge2_4310.bridge$renderPosZ());
         BufferBuilderBridge bridge_285;
         if (bridgeextension_91.method38()) {
            bridge_285 = bridgeextension_91.method30().method12(1.0F, true);
         } else {
            bridge_285 = bridgeextension_91.method11(1.0F);
         }

         bridgeextension_91.method25(1.0F, 1.0F, 1.0F, 1.0F);
         int index6 = 0;

         for (Vector3dc vector3dc8 : worldeditcui2handler3.getPoints()) {
            ColorOption lightingextension42229 = index6 % 2 == 0 ? worldeditcui2.method15() : worldeditcui2.method16();
            WorldeditcuiHandler.method2(bridge_285, lightingextension42229, vector3dc8, vector3dc8.add(1.0, 1.0, 1.0, new Vector3d()));
            index6++;
         }

         bridge_285.end();
         bridgeextension_91.pop();
      }
   }
}
