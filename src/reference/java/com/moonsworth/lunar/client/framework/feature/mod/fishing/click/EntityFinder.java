package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge5Extension2;
import com.moonsworth.lunar.bridge.EntityMobBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import java.util.List;
import java.util.function.Predicate;
import lombok.Generated;

public final class EntityFinder {
   public static BridgeExtension findNearest(BridgeExtension bridgeextension0, List<BridgeExtension> list1) {
      Predicate predicate2 = arg0x -> arg0x instanceof Bridge5Extension2 bridge5extension21x ? NpcUtils.method2(bridge5extension21x, true) : arg0x instanceof EntityMobBridge;
      return findNearest(bridgeextension0, list1, predicate2);
   }

   public static BridgeExtension findNearest(BridgeExtension bridgeextension0, List<BridgeExtension> list1, Predicate<BridgeExtension> predicate2) {
      return findNearest(bridgeextension0, list1, predicate2, Float.MAX_VALUE);
   }

   public static BridgeExtension findNearest(BridgeExtension bridgeextension0, List<BridgeExtension> list1, Predicate<BridgeExtension> predicate2, float value3) {
      BridgeExtension bridgeextension4 = null;
      float value5 = value3;

      for (BridgeExtension bridgeextension7 : list1) {
         if (bridgeextension7 != bridgeextension0 && predicate2.test(bridgeextension7)) {
            if (bridgeextension4 == null) {
               bridgeextension4 = bridgeextension7;
               value5 = distanceSquared(bridgeextension7, bridgeextension0);
            } else {
               float value8 = distanceSquared(bridgeextension7, bridgeextension0);
               if (value8 < value5) {
                  bridgeextension4 = bridgeextension7;
                  value5 = value8;
               }
            }
         }
      }

      return bridgeextension4;
   }

   public static float distanceSquared(BridgeExtension bridgeextension0, BridgeExtension bridge) {
      float value2 = (float)(bridgeextension0.bridge$getPosX() - bridge.bridge$getPosX());
      float value3 = (float)(bridgeextension0.bridge$getPosY() + 2.0 - bridge.bridge$getPosY());
      float value4 = (float)(bridgeextension0.bridge$getPosZ() - bridge.bridge$getPosZ());
      return value2 * value2 + value3 * value3 + value4 * value4;
   }

   @Generated
   private EntityFinder() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
