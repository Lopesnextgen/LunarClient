package com.moonsworth.lunar.client.config.profile;

import com.google.gson.JsonObject;

public class HudPosition {
   private final String quadrant;
   private final float x;
   private final float y;
   private final float width;
   private final float height;
   private final int screenWidth;
   private final int screenHeight;
   private final boolean computed;
   private final float scale;

   public HudPosition(String text1, float value2, float value, float value3, float value4, int value5, int value6, boolean flag, float value7) {
      this.quadrant = text1;
      this.x = value2;
      this.y = value;
      this.width = value3;
      this.height = value4;
      this.screenWidth = value5;
      this.screenHeight = value6;
      this.computed = flag;
      this.scale = value7;
   }

   static HudPosition parse(JsonObject json0) {
      return new HudPosition(
         json0.has("quadrant") ? json0.get("quadrant").getAsString() : "TOP_LEFT",
         getFloat(json0, "xPosition", 0.0F),
         getFloat(json0, "yPosition", 0.0F),
         getFloat(json0, "width", 0.0F),
         getFloat(json0, "height", 0.0F),
         (int)getFloat(json0, "screenWidth", 1920.0F),
         (int)getFloat(json0, "screenHeight", 1080.0F),
         json0.has("type") && "computed".equalsIgnoreCase(json0.get("type").getAsString()),
         getFloat(json0, "scale", 2.0F)
      );
   }

   private static float getFloat(JsonObject json0, String text1, float value2) {
      return json0.has(text1) && json0.get(text1).isJsonPrimitive() ? json0.get(text1).getAsFloat() : value2;
   }

   public float pixelX() {
      return this.anchorX() + Math.round(this.thirdWidth() * this.x);
   }

   public float pixelY() {
      return this.anchorY() + Math.round(this.thirdHeight() * this.y);
   }

   public float thirdWidth() {
      return this.screenWidth / 3.0F;
   }

   public float thirdHeight() {
      return this.screenHeight / 3.0F;
   }

   public float anchorX() {
      return this.horizontalQuadrant() * this.thirdWidth();
   }

   public float anchorY() {
      return this.verticalQuadrant() * this.thirdHeight();
   }

   private int horizontalQuadrant() {
      return switch (this.quadrant) {
         case "TOP_CENTER", "MIDDLE_CENTER", "BOTTOM_CENTER" -> 1;
         case "TOP_RIGHT", "MIDDLE_RIGHT", "BOTTOM_RIGHT" -> 2;
         default -> 0;
      };
   }

   private int verticalQuadrant() {
      return switch (this.quadrant) {
         case "MIDDLE_LEFT", "MIDDLE_CENTER", "MIDDLE_RIGHT" -> 1;
         case "BOTTOM_LEFT", "BOTTOM_CENTER", "BOTTOM_RIGHT" -> 2;
         default -> 0;
      };
   }

   public String getQuadrant() {
      return this.quadrant;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getWidth() {
      return this.width;
   }

   public float getHeight() {
      return this.height;
   }

   public int getScreenWidth() {
      return this.screenWidth;
   }

   public int getScreenHeight() {
      return this.screenHeight;
   }

   public boolean isComputed() {
      return this.computed;
   }

   public float scale() {
      return this.scale;
   }
}
