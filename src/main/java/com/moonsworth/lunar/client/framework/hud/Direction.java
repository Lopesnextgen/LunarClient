package com.moonsworth.lunar.client.framework.hud;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
   TOP,
   BOTTOM,
   RIGHT,
   LEFT;

   Direction() {
   }

   public Direction getOpposite() {
      return switch (this) {
         case TOP -> BOTTOM;
         case BOTTOM -> TOP;
         case RIGHT -> LEFT;
         case LEFT -> RIGHT;
      };
   }

   public Direction rotate90CW() {
      return switch (this) {
         case TOP -> RIGHT;
         case BOTTOM -> LEFT;
         case RIGHT -> BOTTOM;
         case LEFT -> TOP;
      };
   }

   public Direction rotate90CCW() {
      return switch (this) {
         case TOP -> LEFT;
         case BOTTOM -> RIGHT;
         case RIGHT -> TOP;
         case LEFT -> BOTTOM;
      };
   }

   public boolean isVertical() {
      return this.ordinal() < 2;
   }

   public int offsetX() {
      return this == RIGHT ? 1 : (this == LEFT ? -1 : 0);
   }

   public int offsetY() {
      return this == TOP ? 1 : (this == BOTTOM ? -1 : 0);
   }

   public static Direction getRandom() {
      Direction[] items0 = values();
      return items0[ThreadLocalRandom.current().nextInt(items0.length)];
   }

   public static Direction[] getAxis(boolean flag) {
      return flag ? new Direction[]{TOP, BOTTOM} : new Direction[]{RIGHT, LEFT};
   }
}
