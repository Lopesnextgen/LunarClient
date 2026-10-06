package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.CropType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.nameplate.CropTracker;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(CropTracker.class)
public class EventCropPlaced extends LunarEvent implements DynamicListenerEvent {
   private final CropType field1;

   @Generated
   public EventCropPlaced(CropType cropType) {
      this.field1 = cropType;
   }

   @Generated
   public CropType getCrop() {
      return this.field1;
   }
}
