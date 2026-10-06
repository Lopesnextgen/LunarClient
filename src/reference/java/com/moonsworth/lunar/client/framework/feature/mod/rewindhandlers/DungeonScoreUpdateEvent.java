package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(DungeonScoreListener.class)
public class DungeonScoreUpdateEvent extends LunarEvent implements DynamicListenerEvent {
   private final int field1;

   @Generated
   public int method1() {
      return this.field1;
   }

   @Generated
   public DungeonScoreUpdateEvent(int value) {
      this.field1 = value;
   }
}
