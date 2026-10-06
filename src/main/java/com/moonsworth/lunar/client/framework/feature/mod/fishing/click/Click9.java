package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import toxi.geom.Vec2D;

public class Click9 {
   private final Vec2D position;
   private final float weight;
   private final long timestamp;

   public Click9(Vec2D vec2D, float value, long value2) {
      this.position = vec2D;
      this.weight = value;
      this.timestamp = value2;
   }

   public Vec2D getPosition() {
      return this.position;
   }

   public float getWeight() {
      return this.weight;
   }

   public long getTimestamp() {
      return this.timestamp;
   }
}
