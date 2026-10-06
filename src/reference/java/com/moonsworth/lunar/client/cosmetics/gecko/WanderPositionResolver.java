package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Random;
import javax.annotation.Nullable;

public class WanderPositionResolver {
   @Nullable
   public static Horsestats20Extension2 method1(EmoteDefinition var0, PathFilter var1, int var2, int var3, int var4) {
      Horsestats20Extension2 var5 = method5(var0.method9(), var2, var3, var4);
      return method4(var0, var1, var5);
   }

   @Nullable
   public static Horsestats20Extension2 method2(EmoteDefinition var0, PathFilter var1, Horsestats20Extension2 var2, int var3, int var4, int var5) {
      Horsestats20Extension2 var6 = method5(var0.method9(), var3, var4, var5);
      return method3(var2, var1, var6);
   }

   @Nullable
   public static Horsestats20Extension2 method3(Horsestats20Extension2 var0, PathFilter var1, Horsestats20Extension2 var2) {
      Horsestats20Extension2 var3 = Bridge.method8()
         .method4(var0.bridge$getX() + var2.bridge$getX(), var0.bridge$getY() + var2.bridge$getY(), var0.bridge$getZ() + var2.bridge$getZ());
      Itemcounter6Extension var4 = ThreadModuleDump63.method8();
      if (var4 == null) {
         return null;
      } else {
         return var3.bridge$getY() - 1 > var4.bridge$getMinBuildHeight()
               && var3.bridge$getY() < var4.bridge$getMaxBuildHeight()
               && var1.method5().method22(var3)
            ? var3
            : null;
      }
   }

   @Nullable
   public static Horsestats20Extension2 method4(EmoteDefinition var0, PathFilter var1, Horsestats20Extension2 var2) {
      Horsestats20Extension2 var3 = Bridge.method8()
         .method6(var0.bridge$getPosX() + var2.bridge$getX(), var0.bridge$getPosY() + var2.bridge$getY(), var0.bridge$getPosZ() + var2.bridge$getZ());
      Itemcounter6Extension var4 = ThreadModuleDump63.method8();
      if (var4 == null) {
         return null;
      } else {
         return var3.bridge$getY() - 1 > var4.bridge$getMinBuildHeight()
               && var3.bridge$getY() < var4.bridge$getMaxBuildHeight()
               && var1.method5().method22(var3)
            ? var3
            : null;
      }
   }

   public static Horsestats20Extension2 method5(Random var0, int var1, int var2, int var3) {
      int var4 = var0.nextInt(var1, var2 + 1);
      int var5 = var0.nextInt(2 * var3 + 1) - var3;
      int var6 = var0.nextInt(var1, var2 + 1);
      if (var0.nextBoolean()) {
         var4 = -var4;
      }

      if (var0.nextBoolean()) {
         var6 = -var6;
      }

      return Bridge.method8().method4(var4, var5, var6);
   }
}
