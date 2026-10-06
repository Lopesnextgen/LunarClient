package com.moonsworth.lunar.client.config.profile;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ProfileColor {
   private final int red;
   private final int green;
   private final int blue;
   private final int alpha;
   private final boolean chroma;

   public ProfileColor(int value, int value2, int value3, int value4, boolean flag5) {
      this.red = value;
      this.green = value2;
      this.blue = value3;
      this.alpha = value4;
      this.chroma = flag5;
   }

   public static ProfileColor parse(JsonElement element0) {
      if (element0 != null && element0.isJsonObject()) {
         JsonObject json1 = element0.getAsJsonObject();
         JsonElement element2 = json1.get("color");
         if (element2 != null && element2.isJsonObject()) {
            JsonObject json3 = element2.getAsJsonObject();
            String text4 = json1.has("mode") ? json1.get("mode").getAsString() : "STATIC";
            boolean flag5 = "RAINBOW".equalsIgnoreCase(text4) || "CHROMA".equalsIgnoreCase(text4);
            return new ProfileColor(readChannel(json3, "red"), readChannel(json3, "green"), readChannel(json3, "blue"), readChannel(json3, "alpha"), flag5);
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static int readChannel(JsonObject json0, String text) {
      return json0.has(text) ? json0.get(text).getAsInt() & 0xFF : 255;
   }

   public int toRgba() {
      return (this.alpha & 0xFF) << 24 | (this.red & 0xFF) << 16 | (this.green & 0xFF) << 8 | this.blue & 0xFF;
   }

   public boolean isChroma() {
      return this.chroma;
   }
}
