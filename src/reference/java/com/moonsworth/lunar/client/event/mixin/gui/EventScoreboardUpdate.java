package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(ScoreboardListener.class)
public class EventScoreboardUpdate extends LunarEvent implements DynamicListenerEvent {
   private final ScoreboardBridge field1;

   @Generated
   public ScoreboardBridge method1() {
      return this.field1;
   }

   @Generated
   public EventScoreboardUpdate(ScoreboardBridge lighting41) {
      this.field1 = lighting41;
   }
}
