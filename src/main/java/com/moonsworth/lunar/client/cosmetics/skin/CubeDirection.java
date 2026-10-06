package com.moonsworth.lunar.client.cosmetics.skin;

import lombok.Generated;

public enum CubeDirection {
   DOWN(CubeDirection.CubeAxis.Y, -1),
   UP(CubeDirection.CubeAxis.Y, 1),
   NORTH(CubeDirection.CubeAxis.Z, -1),
   SOUTH(CubeDirection.CubeAxis.Z, 1),
   WEST(CubeDirection.CubeAxis.X, -1),
   EAST(CubeDirection.CubeAxis.X, 1);

   public static final CubeDirection[] VALUES = values();
   private final CubeDirection.CubeAxis axis;
   private final int axisDirection;

   CubeDirection(CubeDirection.CubeAxis cubeAxis, int value) {
      this.axis = cubeAxis;
      this.axisDirection = value;
   }

   public CubeDirection getOpposite() {
      return switch (this) {
         case UP -> DOWN;
         case DOWN -> UP;
         case NORTH -> SOUTH;
         case SOUTH -> NORTH;
         case EAST -> WEST;
         case WEST -> EAST;
      };
   }

   public int getStepX() {
      return this.axis.choose(1, 0, 0) * this.axisDirection;
   }

   public int getStepY() {
      return this.axis.choose(0, 1, 0) * this.axisDirection;
   }

   public int getStepZ() {
      return this.axis.choose(0, 0, 1) * this.axisDirection;
   }

   @Generated
   public CubeDirection.CubeAxis getAxis() {
      return this.axis;
   }

   @Generated
   public int getAxisDirection() {
      return this.axisDirection;
   }

   public enum CubeAxis {
      X,
      Y,
      Z;

      public static final CubeDirection.CubeAxis[] VALUES = values();

      CubeAxis() {
      }

      public int choose(int value, int value2, int value3) {
         return switch (this) {
            case X -> value;
            case Y -> value2;
            case Z -> value3;
         };
      }

      public double choose(double value, double value2, double value3) {
         return switch (this) {
            case X -> value;
            case Y -> value2;
            case Z -> value3;
         };
      }
   }
}
