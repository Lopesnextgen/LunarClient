package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventRenderWorld extends LunarEvent {
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
   public EventRenderWorld(Bridge14_3 bridge14_31, float value) {
      this.field1 = bridge14_31;
      this.field2 = value;
   }
}
