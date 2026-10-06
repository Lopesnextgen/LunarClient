package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import lombok.Generated;

public class EventBlockPlacement extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private Bridge3_23 field1;
   private Bridge2_17 field2;
   private Vector3iBridge field3;

   @Generated
   public EventBlockPlacement(Bridge3_23 bridge3_23, Bridge2_17 bridge2_17, Vector3iBridge vector3iBridge) {
      this.field1 = bridge3_23;
      this.field2 = bridge2_17;
      this.field3 = vector3iBridge;
   }

   @Generated
   public Bridge3_23 getBlock() {
      return this.field1;
   }

   @Generated
   public Bridge2_17 method1() {
      return this.field2;
   }

   @Generated
   public Vector3iBridge method2() {
      return this.field3;
   }
}
