package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.client.replay.render.CameraUpdateHandler;
import com.moonsworth.lunar.client.event.input.EventMouseMove;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindFrame;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScroll;
import lombok.Generated;

public abstract class RewindCameraController {
   protected final CameraUpdateHandler field1;

   public abstract void method1(float value1);

   public void method2(EventMouseScroll event) {
   }

   public void method3(EventMouseMove event) {
   }

   public void method4(EventRewindFrame event) {
   }

   @Generated
   public RewindCameraController(CameraUpdateHandler rewindhandlers3updater1) {
      this.field1 = rewindhandlers3updater1;
   }
}
