package com.moonsworth.lunar.client.framework.feature.pkg.mixin;

import lombok.Generated;

public enum PkgType {
   DOWN(PkgType.Type.Y, -1),
   UP(PkgType.Type.Y, 1),
   NORTH(PkgType.Type.Z, -1),
   SOUTH(PkgType.Type.Z, 1),
   WEST(PkgType.Type.X, -1),
   EAST(PkgType.Type.X, 1);

   public static final PkgType[] VALUES = values();
   private final PkgType.Type axis;
   private final int axisDirection;

   PkgType(PkgType.Type var3, int value) {
      this.axis = var3;
      this.axisDirection = value;
   }

   public PkgType getOpposite() {
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
   public PkgType.Type getAxis() {
      return this.axis;
   }

   @Generated
   public int getAxisDirection() {
      return this.axisDirection;
   }

   public enum Type {
      X,
      Y,
      Z;

      public static final PkgType.Type[] VALUES = values();

      public int choose(int var1, int value, int var3) {
         return switch (this) {
            case X -> var1;
            case Y -> value;
            case Z -> var3;
         };
      }

      public double choose(double var1, double var3, double value) {
         return switch (this) {
            case X -> var1;
            case Y -> var3;
            case Z -> value;
         };
      }
   }
}
