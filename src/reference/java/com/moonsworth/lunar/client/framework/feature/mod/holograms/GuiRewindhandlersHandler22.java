package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldTimeUpdate;
import lombok.Generated;

public class GuiRewindhandlersHandler22 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private long field7;

   public GuiRewindhandlersHandler22() {
      this.handle(EventWorldTimeUpdate.class, this::method1);
   }

   private void method1(EventWorldTimeUpdate highlightImpl10) {
      this.field7 = Math.abs(highlightImpl10.method1());
   }

   @Generated
   public long method5() {
      return this.field7;
   }
}
