package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;

public abstract class LocationEvent extends LunarEvent implements DynamicListenerEvent {
   public LocationEvent() {
   }

   @TriggeredBy(HypixelLocationListener.class)
   public static class Data extends LocationEvent {
      public Data() {
      }
   }

   @TriggeredBy(HypixelLocationListener.class)
   public static class LocationEnterEvent extends LocationEvent {
      public LocationEnterEvent() {
      }
   }
}
