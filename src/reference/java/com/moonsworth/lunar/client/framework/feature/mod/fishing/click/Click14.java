package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge5Extension2;
import com.moonsworth.lunar.bridge.Bridge5Extension4_2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump22;
import java.util.List;
import java.util.function.Predicate;
import lombok.Generated;

public final class Click14 {
   public static BridgeExtension findNearest(BridgeExtension var0, List<BridgeExtension> var1) {
      Predicate var2 = var0x -> var0x instanceof Bridge5Extension2 var1x ? ThreadModuleDump22.method2(var1x, true) : var0x instanceof Bridge5Extension4_2;
      return findNearest(var0, var1, var2);
   }

   public static BridgeExtension findNearest(BridgeExtension var0, List<BridgeExtension> var1, Predicate<BridgeExtension> var2) {
      return findNearest(var0, var1, var2, Float.MAX_VALUE);
   }

   public static BridgeExtension findNearest(BridgeExtension var0, List<BridgeExtension> var1, Predicate<BridgeExtension> var2, float var3) {
      BridgeExtension var4 = null;
      float var5 = var3;

      for (BridgeExtension var7 : var1) {
         if (var7 != var0 && var2.test(var7)) {
            if (var4 == null) {
               var4 = var7;
               var5 = distanceSquared(var7, var0);
            } else {
               float var8 = distanceSquared(var7, var0);
               if (var8 < var5) {
                  var4 = var7;
                  var5 = var8;
               }
            }
         }
      }

      return var4;
   }

   public static float distanceSquared(BridgeExtension var0, BridgeExtension var1) {
      float var2 = (float)(var0.bridge$getPosX() - var1.bridge$getPosX());
      float var3 = (float)(var0.bridge$getPosY() + 2.0 - var1.bridge$getPosY());
      float var4 = (float)(var0.bridge$getPosZ() - var1.bridge$getPosZ());
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   @Generated
   private Click14() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
