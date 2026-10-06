package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventWorldRender extends Highlight {
   private final Bridge14_3 field1;
   private final float field2;

   @Generated
   public Bridge14_3 method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public EventWorldRender(Bridge14_3 bridge14_3, float value) {
      this.field1 = bridge14_3;
      this.field2 = value;
   }
}
