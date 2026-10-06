package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(HypixelLocationListener.class)
public class EventLocationChange extends LunarEvent implements DynamicListenerEvent {
   private final HypixelLocation field1;
   private final HypixelLocation field2;

   @Generated
   public HypixelLocation method1() {
      return this.field1;
   }

   @Generated
   public HypixelLocation method2() {
      return this.field2;
   }

   @Generated
   public EventLocationChange(HypixelLocation rewindhandlers21, HypixelLocation rewindhandlers22) {
      this.field1 = rewindhandlers21;
      this.field2 = rewindhandlers22;
   }
}
