package com.moonsworth.lunar.client.framework.hud;

public enum Corner {
   TOP_LEFT,
   TOP_RIGHT,
   BOTTOM_LEFT,
   BOTTOM_RIGHT;

   Corner() {
   }

   public Corner getOpposite() {
      return switch (this) {
         case TOP_LEFT -> BOTTOM_RIGHT;
         case BOTTOM_RIGHT -> TOP_LEFT;
         case TOP_RIGHT -> BOTTOM_LEFT;
         case BOTTOM_LEFT -> TOP_RIGHT;
      };
   }

   public Corner rotate90CW() {
      return switch (this) {
         case TOP_LEFT -> TOP_RIGHT;
         case BOTTOM_RIGHT -> BOTTOM_LEFT;
         case TOP_RIGHT -> BOTTOM_RIGHT;
         case BOTTOM_LEFT -> TOP_LEFT;
      };
   }

   public Corner rotate90CCW() {
      return switch (this) {
         case TOP_LEFT -> BOTTOM_LEFT;
         case BOTTOM_RIGHT -> TOP_RIGHT;
         case TOP_RIGHT -> TOP_LEFT;
         case BOTTOM_LEFT -> BOTTOM_RIGHT;
      };
   }

   public int offsetX() {
      return switch (this) {
         case TOP_LEFT, TOP_RIGHT -> 1;
         case BOTTOM_RIGHT, BOTTOM_LEFT -> -1;
      };
   }

   public int offsetY() {
      return switch (this) {
         case TOP_LEFT, BOTTOM_LEFT -> -1;
         case BOTTOM_RIGHT, TOP_RIGHT -> 1;
      };
   }
}
