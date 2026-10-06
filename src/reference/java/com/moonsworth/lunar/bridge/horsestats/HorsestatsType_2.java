package com.moonsworth.lunar.bridge.horsestats;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Generated;
import org.joml.Vector3f;
import org.joml.Vector3i;

public enum HorsestatsType_2 {
   DOWN(0, 1, -1, "down", HorsestatsType$Type2.NEGATIVE, HorsestatsType$Type.Y, new Vector3i(0, -1, 0)),
   UP(1, 0, -1, "up", HorsestatsType$Type2.POSITIVE, HorsestatsType$Type.Y, new Vector3i(0, 1, 0)),
   NORTH(2, 3, 2, "north", HorsestatsType$Type2.NEGATIVE, HorsestatsType$Type.Z, new Vector3i(0, 0, -1)),
   SOUTH(3, 2, 0, "south", HorsestatsType$Type2.POSITIVE, HorsestatsType$Type.Z, new Vector3i(0, 0, 1)),
   WEST(4, 5, 1, "west", HorsestatsType$Type2.NEGATIVE, HorsestatsType$Type.X, new Vector3i(-1, 0, 0)),
   EAST(5, 4, 3, "east", HorsestatsType$Type2.POSITIVE, HorsestatsType$Type.X, new Vector3i(1, 0, 0));

   private final int id;
   private final int idOpposite;
   private final int idHorizontal;
   private final String name;
   private final HorsestatsType$Type axis;
   private final HorsestatsType$Type2 direction;
   private final Vector3i vector;
   private static final HorsestatsType_2[] ALL = values();
   private static final Map<String, HorsestatsType_2> NAME_MAP = Arrays.stream(ALL)
      .collect(Collectors.toMap(HorsestatsType_2::getName, var0 -> (HorsestatsType_2)var0));
   private static final HorsestatsType_2[] VALUES = Arrays.stream(ALL).sorted(Comparator.comparingInt(var0 -> var0.id)).toArray(HorsestatsType_2[]::new);
   private static final HorsestatsType_2[] HORIZONTAL = Arrays.stream(ALL)
      .filter(var0 -> var0.getAxis().isHorizontal())
      .sorted(Comparator.comparingInt(var0 -> var0.idHorizontal))
      .toArray(HorsestatsType_2[]::new);

   HorsestatsType_2(int var3, int var4, int var5, String var6, HorsestatsType$Type2 var7, HorsestatsType$Type var8, Vector3i var9) {
      this.id = var3;
      this.idHorizontal = var5;
      this.idOpposite = var4;
      this.name = var6;
      this.axis = var8;
      this.direction = var7;
      this.vector = var9;
   }

   private static HorsestatsType_2[] listClosest(HorsestatsType_2 var0, HorsestatsType_2 var1, HorsestatsType_2 var2) {
      return new HorsestatsType_2[]{var0, var1, var2, var2.getOpposite(), var1.getOpposite(), var0.getOpposite()};
   }

   public int getHorizontal() {
      return this.idHorizontal;
   }

   public HorsestatsType_2 getOpposite() {
      return byId(this.idOpposite);
   }

   public HorsestatsType_2 rotateYClockwise() {
      return switch (this) {
         case NORTH -> EAST;
         case SOUTH -> WEST;
         case WEST -> NORTH;
         case EAST -> SOUTH;
         default -> throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
      };
   }

   public HorsestatsType_2 rotateYCounterclockwise() {
      return switch (this) {
         case NORTH -> WEST;
         case SOUTH -> EAST;
         case WEST -> SOUTH;
         case EAST -> NORTH;
         default -> throw new IllegalStateException("Unable to get CCW facing of " + this);
      };
   }

   public int getOffsetX() {
      return this.vector.x();
   }

   public int getOffsetY() {
      return this.vector.y();
   }

   public int getOffsetZ() {
      return this.vector.z();
   }

   public Vector3f getUnitVector() {
      return new Vector3f(this.getOffsetX(), this.getOffsetY(), this.getOffsetZ());
   }

   public float toYRot() {
      return (this.idHorizontal & 3) * 90;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public int getId() {
      if (this == WEST) {
         return 5;
      } else {
         return this == EAST ? 4 : this.getId();
      }
   }

   public static HorsestatsType_2 byName(String var0) {
      return var0 == null ? null : NAME_MAP.get(var0.toLowerCase(Locale.ROOT));
   }

   public static HorsestatsType_2 byId(int var0) {
      return VALUES[Math.abs(var0 % VALUES.length)];
   }

   public static HorsestatsType_2 fromHorizontal(int var0) {
      return HORIZONTAL[Math.abs(var0 % HORIZONTAL.length)];
   }

   public static HorsestatsType_2 from(HorsestatsType$Type var0, HorsestatsType$Type2 var1) {
      return switch (var0) {
         case X -> var1 == HorsestatsType$Type2.POSITIVE ? EAST : WEST;
         case Y -> var1 == HorsestatsType$Type2.POSITIVE ? UP : DOWN;
         case Z -> var1 == HorsestatsType$Type2.POSITIVE ? SOUTH : NORTH;
      };
   }

   public static HorsestatsType_2 getFacing(double var0, double var2, double var4) {
      return getFacing((float)var0, (float)var2, (float)var4);
   }

   public static HorsestatsType_2 getFacing(float var0, float var1, float var2) {
      HorsestatsType_2 var3 = NORTH;
      float var4 = Float.MIN_VALUE;

      for (HorsestatsType_2 var8 : ALL) {
         float var9 = var0 * var8.vector.x() + var1 * var8.vector.y() + var2 * var8.vector.z();
         if (var9 > var4) {
            var4 = var9;
            var3 = var8;
         }
      }

      return var3;
   }

   public static HorsestatsType_2 get(HorsestatsType$Type2 var0, HorsestatsType$Type var1) {
      for (HorsestatsType_2 var5 : ALL) {
         if (var5.getDirection() == var0 && var5.getAxis() == var1) {
            return var5;
         }
      }

      throw new IllegalArgumentException("No such DirectionBridge: " + var0 + " " + var1);
   }

   public static HorsestatsType_2 getNearest(double var0, double var2, double var4) {
      return getNearest((float)var0, (float)var2, (float)var4);
   }

   public static HorsestatsType_2 getNearest(float var0, float var1, float var2) {
      HorsestatsType_2 var3 = NORTH;
      float var4 = Float.MIN_VALUE;

      for (HorsestatsType_2 var8 : VALUES) {
         float var9 = var0 * var8.vector.x() + var1 * var8.vector.y() + var2 * var8.vector.z();
         if (var9 > var4) {
            var4 = var9;
            var3 = var8;
         }
      }

      return var3;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public HorsestatsType$Type getAxis() {
      return this.axis;
   }

   @Generated
   public HorsestatsType$Type2 getDirection() {
      return this.direction;
   }

   @Generated
   public Vector3i getVector() {
      return this.vector;
   }
}
