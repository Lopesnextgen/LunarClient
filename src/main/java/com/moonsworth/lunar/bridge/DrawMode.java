package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum DrawMode {
   POINTS(0, 0, 1, 1, false),
   LINES(4, 1, 2, 2, false),
   LINE_LOOP(6, 2, 2, 1, true),
   LINE_STRIP(5, 3, 2, 1, true),
   TRIANGLES(4, 4, 3, 3, false),
   TRIANGLE_STRIP(5, 5, 3, 1, true),
   TRIANGLE_FAN(6, 6, 3, 1, true),
   QUADS(4, 7, 7, 4, false),
   DEBUG_LINES(1, 1, 2, 2, false),
   DEBUG_LINE_STRIP(3, 3, 2, 1, true);

   private final int mcGLMode;
   private final int realGLMode;
   private final int primitiveLength;
   private final int primitiveStride;
   private final boolean connectedPrimitives;

   public int indexCount(int var1) {
      return switch (this) {
         case LINE_STRIP, DEBUG_LINES, DEBUG_LINE_STRIP, TRIANGLES, TRIANGLE_STRIP, TRIANGLE_FAN -> var1;
         case LINES, QUADS -> var1 / 4 * 6;
         default -> 0;
      };
   }

   public int getGLMode(boolean var1) {
      return var1 ? this.mcGLMode : this.realGLMode;
   }

   public static DrawMode fromGlMode(int value) {
      return switch (value) {
         case 0 -> POINTS;
         case 1 -> LINES;
         case 2 -> LINE_LOOP;
         case 3 -> LINE_STRIP;
         case 4 -> TRIANGLES;
         case 5 -> TRIANGLE_STRIP;
         case 6 -> TRIANGLE_FAN;
         case 7 -> QUADS;
         case 8 -> throw new IllegalStateException("The GL_POLYGON draw mode is not supported on the core profile!");
         default -> throw new IllegalStateException("Unknown GL draw mode: " + value);
      };
   }

   @Generated
   DrawMode(int value, int value2, int value3, int value4, boolean flag) {
      this.mcGLMode = value;
      this.realGLMode = value2;
      this.primitiveLength = value3;
      this.primitiveStride = value4;
      this.connectedPrimitives = flag;
   }

   @Generated
   public int getPrimitiveLength() {
      return this.primitiveLength;
   }

   @Generated
   public int getPrimitiveStride() {
      return this.primitiveStride;
   }

   @Generated
   public boolean isConnectedPrimitives() {
      return this.connectedPrimitives;
   }
}
