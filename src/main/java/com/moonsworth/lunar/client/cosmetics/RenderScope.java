package com.moonsworth.lunar.client.cosmetics;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public enum RenderScope {
   RENDER_COSMETICS("render-cosmetics"),
   RENDER_COSMETICS_ALL("render-cosmetics:all"),
   RENDER_COSMETICS_EQUIPPED("render-cosmetics:equipped"),
   RENDER_EMOTES("render-emotes"),
   RENDER_EMOTES_ALL("render-emotes:all"),
   RENDER_SPRAYS("render-sprays"),
   RENDER_SPRAYS_ALL("render-sprays:all");

   private final String key;
   private static final Map<String, RenderScope> SCOPES_BY_KEY = new HashMap<>();

   @Nullable
   public static RenderScope get(String text) {
      return SCOPES_BY_KEY.get(text);
   }

   @Generated
   RenderScope(String text) {
      this.key = text;
   }

   static {
      for (RenderScope mixinextratype3 : values()) {
         SCOPES_BY_KEY.put(mixinextratype3.key, mixinextratype3);
      }
   }
}
