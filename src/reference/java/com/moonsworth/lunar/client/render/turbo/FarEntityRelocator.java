package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import org.joml.Math;

public class FarEntityRelocator implements TurboEntityFilter<BridgeExtension> {
   public boolean method1(TurboEntityManager manager, Itemcounter6Extension itemcounter6, Bridge5Extension_5 bridge5Extension_5, BridgeExtension bridge) {
      int var5 = bridge.method2();
      bridge.method2(var5 + 1);
      com.moonsworth.lunar.client.render.turbo.PathMovementHelper.method1(bridge, bridge5Extension_5.bridge$getEyePosition());
      double var6 = bridge.method13(bridge5Extension_5);
      if (var6 > 144.0) {
         com.moonsworth.lunar.client.render.turbo.PathMovementHelper.method4(itemcounter6, bridge5Extension_5, bridge);
      }

      if (var5 % 4 == 1) {
         int var8 = (int)Math.floor(bridge.bridge$getPosX());
         int var9 = (int)Math.floor(bridge.bridge$getPosY());
         int var10 = (int)Math.floor(bridge.bridge$getPosZ());
         if (!com.moonsworth.lunar.client.render.turbo.PathMovementHelper.method3(itemcounter6, var8, var9, var10, bridge)
            && !com.moonsworth.lunar.client.render.turbo.PathMovementHelper.method4(itemcounter6, bridge5Extension_5, bridge)) {
            double var11 = bridge5Extension_5.bridge$getPosX() + 2000.0;
            double var13 = bridge5Extension_5.bridge$getPosY() - 2000.0;
            double var15 = bridge5Extension_5.bridge$getPosZ() + 2000.0;
            bridge.bridge$setPosX(var11);
            bridge.bridge$setPosY(var13);
            bridge.bridge$setPosZ(var15);
            bridge.bridge$setPreviousPosX(var11);
            bridge.bridge$setPreviousPosY(var13);
            bridge.bridge$setPreviousPosZ(var15);
         }
      }

      return false;
   }
}
