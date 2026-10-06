package com.moonsworth.lunar.client.render.particle;

import lombok.Generated;

public enum BedrockMaterial {
   OPAQUE("particles_opaque"),
   ALPHA("particles_alpha"),
   BLEND("particles_blend");

   public final String id;

   public static BedrockMaterial fromString(String text) {
      for (BedrockMaterial glintcolorizertype24 : values()) {
         if (glintcolorizertype24.id.equals(text)) {
            return glintcolorizertype24;
         }
      }

      return OPAQUE;
   }

   @Generated
   BedrockMaterial(String text) {
      this.id = text;
   }
}
