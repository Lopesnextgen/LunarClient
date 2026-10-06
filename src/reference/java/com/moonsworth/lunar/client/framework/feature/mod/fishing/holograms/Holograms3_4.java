package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class Holograms3_4 {
   public static double[] method1(Vector3iBridge var0, Dungeonwaypoints4 dungeonwaypoints4, @Nullable HologramsType_3 var2) {
      double var3 = var0.bridge$getX();
      double var5 = var0.bridge$getY();
      double var7 = var0.bridge$getZ();
      double var9 = Math.max(dungeonwaypoints4.method17(), 0.01F);
      double var11 = Math.max(dungeonwaypoints4.method18(), 0.01F);
      double var13 = Math.max(dungeonwaypoints4.method19(), 0.01F);
      double[] var15 = method2(dungeonwaypoints4.method14(), var9, dungeonwaypoints4.method16(), var13, var2);
      return new double[]{var3 + var15[0], var5 + dungeonwaypoints4.method15(), var7 + var15[2], var3 + var15[1], var5 + dungeonwaypoints4.method15() + var11, var7 + var15[3]};
   }

   public static double[] method2(double var0, double var2, double var4, double var6, @Nullable HologramsType_3 var8) {
      HologramsType_3 var9 = var8 == null ? HologramsType_3.EAST : var8;

      return switch (var9) {
         case EAST -> new double[]{var0, var0 + var2, var4, var4 + var6};
         case SOUTH -> new double[]{1.0 - var4 - var6, 1.0 - var4, var0, var0 + var2};
         case WEST -> new double[]{1.0 - var0 - var2, 1.0 - var0, 1.0 - var4 - var6, 1.0 - var4};
         case NORTH -> new double[]{var4, var4 + var6, 1.0 - var0 - var2, 1.0 - var0};
      };
   }

   public static float[] method3(double var0, double var2, double var4, double var6, @Nullable HologramsType_3 var8) {
      float var9 = (float)(var2 - var0);
      float var10 = (float)(var6 - var4);
      HologramsType_3 var11 = var8 == null ? HologramsType_3.EAST : var8;

      return switch (var11) {
         case EAST -> new float[]{(float)var0, var9, (float)var4, var10};
         case SOUTH -> new float[]{(float)var4, var10, (float)(1.0 - var2), var9};
         case WEST -> new float[]{(float)(1.0 - var2), var9, (float)(1.0 - var6), var10};
         case NORTH -> new float[]{(float)(1.0 - var6), var10, (float)var0, var9};
      };
   }

   @Generated
   private Holograms3_4() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
