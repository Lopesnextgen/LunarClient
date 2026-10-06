package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import java.util.Optional;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class ThreadModuleDump73 {
   @Nullable
   public static BridgeExtension findFirstEntityInChunks(Vector3iBridge var0, int var1, Predicate<BridgeExtension> var2) {
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 == null) {
         return null;
      }

      int var4 = Horsestats20Extension.method13(var0.bridge$getX());
      int var5 = Horsestats20Extension.method13(var0.bridge$getY());
      int var6 = Horsestats20Extension.method13(var0.bridge$getZ());

      for (int var7 = 0; var7 < var1; var7++) {
         for (byte var8 = 0; var8 <= var1; var8 = (byte)(var8 > 0 ? -var8 : 1 - var8)) {
            for (byte var9 = 0; var9 <= var7; var9 = (byte)(var9 > 0 ? -var9 : 1 - var9)) {
               for (int var10 = var9 < var7 && var9 > -var7 ? var7 : 0; var10 <= var7; var10 = var10 > 0 ? -var10 : 1 - var10) {
                  int var11 = var4 + var9 << 4;
                  int var12 = var5 + var8 << 4;
                  int var13 = var6 + var10 << 4;
                  AxisAlignedBBBridge var14 = Bridge.method8().method45(var11, var12, var13, var11 + 16, var12 + 16, var13 + 16);
                  BridgeExtension var15 = var3.bridge$getFirstEntity(var14, var2);
                  if (var15 != null) {
                     return var15;
                  }
               }
            }
         }
      }

      return null;
   }

   @Nullable
   public static String getEntityTypeName(int var0) {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 == null) {
         return null;
      }

      Optional var2 = var1.bridge$getEntityById(var0);
      return var2.isEmpty() ? null : Bridge.method57().method1(AdventureTextBridge.asBridge(((BridgeExtension)var2.orElseThrow()).bridge$getTypeName())).replace("#", "-");
   }
}
