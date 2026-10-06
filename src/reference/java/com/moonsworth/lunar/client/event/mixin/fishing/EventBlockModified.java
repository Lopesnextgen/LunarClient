package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventBlockModified extends Highlight {
   private Vector3iBridge field1;
   private Bridge2_17 field2;
   private Bridge2_17 field3;

   @Generated
   public Vector3iBridge method1() {
      return this.field1;
   }

   @Generated
   public Bridge2_17 method2() {
      return this.field2;
   }

   @Generated
   public Bridge2_17 method3() {
      return this.field3;
   }

   @Generated
   public EventBlockModified(Vector3iBridge vector3iBridge, Bridge2_17 bridge2_17, Bridge2_17 bridge2_172) {
      this.field1 = vector3iBridge;
      this.field2 = bridge2_17;
      this.field3 = bridge2_172;
   }
}
