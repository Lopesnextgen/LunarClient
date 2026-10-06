package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventCameraEyeHeight extends Highlight {
   private float eyeHeight;
   private final float field1;
   private boolean field2;

   public EventCameraEyeHeight(float var1, float value) {
      this.eyeHeight = var1;
      this.field1 = value;
   }

   public void method1(float var1) {
      this.eyeHeight = var1;
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
