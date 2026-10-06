package com.moonsworth.lunar.client.render.pipeline;

import net.kyori.adventure.text.Component;

public class RenderCache {
   private Object value;
   public int cachedWidth = -1;
   public boolean cachedState = false;
   public Component cachedText;

   public RenderCache() {
   }

   public <T> T getValue() {
      return (T)this.value;
   }

   public <T> T setValue(T t) {
      this.value = t;
      return (T)t;
   }
}
