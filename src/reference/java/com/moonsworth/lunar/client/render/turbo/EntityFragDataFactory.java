package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 8)
public class EntityFragDataFactory {
   public static final FragDataFactory<BridgeExtension> field1 = (var0, var1) -> {
      boolean var2 = true;
      AxisAlignedBBBridge var3 = null;

      for (BridgeExtension var5 : var0) {
         var2 &= !var5.bridge$getNoCulling();
         AxisAlignedBBBridge var6 = var5.bridge$getBoundingBoxForCulling().method11(0.5);
         if (var6.bridge$hasNaN() || var6.bridge$getSize() == 0.0) {
            var6 = AxisAlignedBBBridge.method2(
               var5.bridge$getPosX() - 2.0,
               var5.bridge$getPosY() - 2.0,
               var5.bridge$getPosZ() - 2.0,
               var5.bridge$getPosX() + 2.0,
               var5.bridge$getPosY() + 2.0,
               var5.bridge$getPosZ() + 2.0
            );
         }

         if (var3 == null) {
            var3 = var6;
         } else {
            var3 = var3.method6(var6);
         }
      }

      if (var3 == null) {
         var3 = AxisAlignedBBBridge.method18();
      }

      return new SimpleFragData(var0.size(), var3, var2, var1);
   };
   public static final FragDataFactory<Itemcounter_4> field2 = (var0, var1) -> {
      AxisAlignedBBBridge var2 = null;

      for (Itemcounter_4 var4 : var0) {
         AxisAlignedBBBridge var5 = var4.bridge$bounds().method11(0.5);
         if (var2 == null) {
            var2 = var5;
         } else {
            var2 = var2.method6(var5);
         }
      }

      if (var2 == null) {
         var2 = AxisAlignedBBBridge.method18();
      }

      return new SimpleFragData(var0.size(), var2, true, var1);
   };
}
