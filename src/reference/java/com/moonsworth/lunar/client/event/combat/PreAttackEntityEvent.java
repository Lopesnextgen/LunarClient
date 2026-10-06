package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class PreAttackEntityEvent extends Highlight {
   private Bridge6_10 field1 = ThreadModuleDump63.method7();
   private final BridgeExtension field2;
   private final double field3;
   private final Vec3Bridge field4;

   @Generated
   public PreAttackEntityEvent(BridgeExtension bridge, double value, Vec3Bridge vec3Bridge) {
      this.field2 = bridge;
      this.field3 = value;
      this.field4 = vec3Bridge;
   }

   @Generated
   @Override
   public String toString() {
      return "EventPreAttackEntity(player="
         + this.method1()
         + ", target="
         + this.method2()
         + ", distance="
         + this.getDistance()
         + ", hitPos="
         + this.method3()
         + ")";
   }

   @Generated
   public Bridge6_10 method1() {
      return this.field1;
   }

   @Generated
   public BridgeExtension method2() {
      return this.field2;
   }

   @Generated
   public double getDistance() {
      return this.field3;
   }

   @Generated
   public Vec3Bridge method3() {
      return this.field4;
   }
}
