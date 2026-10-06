package com.moonsworth.lunar.client.util.game;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import java.util.Optional;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.Ref;

public class EntityLookup {
   public EntityLookup() {
   }

   @Nullable
   public static BridgeExtension method1(Vec3iBridge horsestats200, int value, Predicate<BridgeExtension> predicate2) {
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (itemcounter6extension3 == null) {
         return null;
      }

      int number4 = Horsestats20Extension.method13(horsestats200.bridge$getX());
      int number5 = Horsestats20Extension.method13(horsestats200.bridge$getY());
      int number6 = Horsestats20Extension.method13(horsestats200.bridge$getZ());

      for (int index7 = 0; index7 < value; index7++) {
         for (byte index8 = 0; index8 <= value; index8 = (byte)(index8 > 0 ? -index8 : 1 - index8)) {
            for (byte index9 = 0; index9 <= index7; index9 = (byte)(index9 > 0 ? -index9 : 1 - index9)) {
               for (int index10 = index9 < index7 && index9 > -index7 ? index7 : 0; index10 <= index7; index10 = index10 > 0 ? -index10 : 1 - index10) {
                  int number11 = number4 + index9 << 4;
                  int number12 = number5 + index8 << 4;
                  int number13 = number6 + index10 << 4;
                  AxisAlignedBBBridge horsestats1214 = Bridge.method8().method45(number11, number12, number13, number11 + 16, number12 + 16, number13 + 16);
                  BridgeExtension bridgeextension15 = itemcounter6extension3.bridge$getFirstEntity(horsestats1214, predicate2);
                  if (bridgeextension15 != null) {
                     return bridgeextension15;
                  }
               }
            }
         }
      }

      return null;
   }

   @Nullable
   public static String method2(int value) {
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      if (itemcounter6extension1 == null) {
         return null;
      }

      Optional optional2 = itemcounter6extension1.bridge$getEntityById(value);
      return optional2.isEmpty() ? null : Bridge.method57().method1(TextBridge.asBridge(((BridgeExtension)optional2.orElseThrow()).bridge$getTypeName())).replace("#", "-");
   }
}
