package com.moonsworth.lunar.client.util.chest;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import lombok.Generated;

public class Chest {
   private final Vec3Bridge start;
   private final Vec3Bridge end;

   @Generated
   public Vec3Bridge getStart() {
      return this.start;
   }

   @Generated
   public Vec3Bridge getEnd() {
      return this.end;
   }

   @Generated
   private Chest(Vec3Bridge var1, Vec3Bridge vec3Bridge) {
      this.start = var1;
      this.end = vec3Bridge;
   }

   @Generated
   public static Chest of(Vec3Bridge vec3Bridge, Vec3Bridge var1) {
      return new Chest(vec3Bridge, var1);
   }
}
