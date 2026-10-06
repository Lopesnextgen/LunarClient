package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventEyeHeight extends LunarEvent {
   private float eyeHeight;
   private final float field1;
   private boolean field2;

   public EventEyeHeight(float value1, float value) {
      this.eyeHeight = value1;
      this.field1 = value;
   }

   public void method1(float value1) {
      this.eyeHeight = value1;
      this.field2 = true;
   }

   @Generated
   public float getEyeHeight() {
      return this.eyeHeight;
   }

   @Generated
   public float method2() {
      return this.field1;
   }

   @Generated
   public boolean isModified() {
      return this.field2;
   }
}
