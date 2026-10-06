package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SlayerQuestListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class SlayerQuestEvent extends LunarEvent implements DynamicListenerEvent {
   private final String field1;
   private final long field2;

   @Generated
   public String method1() {
      return this.field1;
   }

   @Generated
   public long getTimestamp() {
      return this.field2;
   }

   @Generated
   public SlayerQuestEvent(String text1, long number2) {
      this.field1 = text1;
      this.field2 = number2;
   }

   @TriggeredBy(SlayerQuestListener.class)
   public static class Data extends SlayerQuestEvent {
      public Data(String text1, long number2) {
         super(text1, number2);
      }
   }

   @TriggeredBy(SlayerQuestListener.class)
   public static class SlayerQuestFailedEvent extends SlayerQuestEvent {
      public SlayerQuestFailedEvent(String text1, long number2) {
         super(text1, number2);
      }
   }

   @TriggeredBy(SlayerQuestListener.class)
   public static class SlayerBossSpawnEvent extends SlayerQuestEvent {
      public SlayerBossSpawnEvent(String text1, long number2) {
         super(text1, number2);
      }
   }
}
