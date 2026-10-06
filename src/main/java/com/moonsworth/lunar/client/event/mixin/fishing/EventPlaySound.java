package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;
import org.joml.Vector3d;

public class EventPlaySound extends com.moonsworth.lunar.client.event.CancellableEvent {
   private ResourceLocationBridge field1;
   private String category;
   private boolean field2;
   float volume;
   float pitch;
   float field3;
   float field4;
   float field5;

   public Vector3d method1() {
      return new Vector3d(this.field3, this.field4, this.field5);
   }

   public String getPath() {
      return this.field1.bridge$getPath();
   }

   public boolean method2(String text) {
      return this.getPath().equals(text);
   }

   @Generated
   public EventPlaySound(ResourceLocationBridge horsestats141, String text, boolean flag, float value, float value2, float value3, float value4, float value5) {
      this.field1 = horsestats141;
      this.category = text;
      this.field2 = flag;
      this.volume = value;
      this.pitch = value2;
      this.field3 = value3;
      this.field4 = value4;
      this.field5 = value5;
   }

   @Generated
   public ResourceLocationBridge method3() {
      return this.field1;
   }

   @Generated
   public String getCategory() {
      return this.category;
   }

   @Generated
   public boolean method4() {
      return this.field2;
   }

   @Generated
   public float getVolume() {
      return this.volume;
   }

   @Generated
   public float getPitch() {
      return this.pitch;
   }

   @Generated
   public float method5() {
      return this.field3;
   }

   @Generated
   public float method6() {
      return this.field4;
   }

   @Generated
   public float method7() {
      return this.field5;
   }
}
