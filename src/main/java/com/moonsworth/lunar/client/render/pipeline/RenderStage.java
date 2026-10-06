package com.moonsworth.lunar.client.render.pipeline;

public enum RenderStage {
   COLLECTING,
   RENDERING_OPAQUE,
   RENDERING_TRANSLUCENT,
   POST_RENDER;

   RenderStage() {
   }

   public boolean isBefore(RenderStage tpstype1) {
      return this.ordinal() < tpstype1.ordinal();
   }

   public boolean isAfter(RenderStage tpstype1) {
      return this.ordinal() > tpstype1.ordinal();
   }
}
