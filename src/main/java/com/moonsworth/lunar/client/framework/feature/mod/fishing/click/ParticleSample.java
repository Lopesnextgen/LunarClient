package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import toxi.geom.Vec2D;

public class ParticleSample {
   private final Vec2D position;
   private final float y;
   private final long timestamp;

   public ParticleSample(Vec2D vec2d1, float value, long value2) {
      this.position = vec2d1;
      this.y = value;
      this.timestamp = value2;
   }

   public Vec2D getPosition() {
      return this.position;
   }

   public float getY() {
      return this.y;
   }

   public long getTimestamp() {
      return this.timestamp;
   }
}
