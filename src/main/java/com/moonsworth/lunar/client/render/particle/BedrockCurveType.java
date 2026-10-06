package com.moonsworth.lunar.client.render.particle;

import lombok.Generated;

public enum BedrockCurveType {
   LINEAR("linear"),
   HERMITE("catmull_rom");

   public final String id;

   public static BedrockCurveType fromString(String text) {
      for (BedrockCurveType glintcolorizertype_44 : values()) {
         if (glintcolorizertype_44.id.equals(text)) {
            return glintcolorizertype_44;
         }
      }

      return LINEAR;
   }

   @Generated
   BedrockCurveType(String text) {
      this.id = text;
   }
}
