package com.moonsworth.lunar.client.event.combat;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class EventPreAttackEntity extends LunarEvent {
   private Bridge6_10 field1 = Ref.method7();
   private final BridgeExtension field2;
   private final double field3;
   private final Vec3Bridge field4;

   @Generated
   public EventPreAttackEntity(BridgeExtension bridge, double value, Vec3Bridge horsestats154) {
      this.field2 = bridge;
      this.field3 = value;
      this.field4 = horsestats154;
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
