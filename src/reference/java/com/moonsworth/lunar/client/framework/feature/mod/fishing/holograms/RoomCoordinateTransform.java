package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class RoomCoordinateTransform {
   public static double[] method1(Vec3iBridge horsestats200, WaypointStyle dungeonwaypoints41, @Nullable RoomRotation hologramstype_32) {
      double value3 = horsestats200.bridge$getX();
      double value5 = horsestats200.bridge$getY();
      double value7 = horsestats200.bridge$getZ();
      double value9 = Math.max(dungeonwaypoints41.method17(), 0.01F);
      double value11 = Math.max(dungeonwaypoints41.method18(), 0.01F);
      double value13 = Math.max(dungeonwaypoints41.method19(), 0.01F);
      double[] items15 = method2(dungeonwaypoints41.method14(), value9, dungeonwaypoints41.method16(), value13, hologramstype_32);
      return new double[]{value3 + items15[0], value5 + dungeonwaypoints41.method15(), value7 + items15[2], value3 + items15[1], value5 + dungeonwaypoints41.method15() + value11, value7 + items15[3]};
   }

   public static double[] method2(double value0, double value2, double value4, double value6, @Nullable RoomRotation hologramstype_38) {
      RoomRotation hologramstype_39 = hologramstype_38 == null ? RoomRotation.EAST : hologramstype_38;

      return switch (hologramstype_39) {
         case EAST -> new double[]{value0, value0 + value2, value4, value4 + value6};
         case SOUTH -> new double[]{1.0 - value4 - value6, 1.0 - value4, value0, value0 + value2};
         case WEST -> new double[]{1.0 - value0 - value2, 1.0 - value0, 1.0 - value4 - value6, 1.0 - value4};
         case NORTH -> new double[]{value4, value4 + value6, 1.0 - value0 - value2, 1.0 - value0};
      };
   }

   public static float[] method3(double value0, double value2, double value4, double value6, @Nullable RoomRotation hologramstype_38) {
      float value9 = (float)(value2 - value0);
      float value10 = (float)(value6 - value4);
      RoomRotation hologramstype_311 = hologramstype_38 == null ? RoomRotation.EAST : hologramstype_38;

      return switch (hologramstype_311) {
         case EAST -> new float[]{(float)value0, value9, (float)value4, value10};
         case SOUTH -> new float[]{(float)value4, value10, (float)(1.0 - value2), value9};
         case WEST -> new float[]{(float)(1.0 - value2), value9, (float)(1.0 - value6), value10};
         case NORTH -> new float[]{(float)(1.0 - value6), value10, (float)value0, value9};
      };
   }

   @Generated
   private RoomCoordinateTransform() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
