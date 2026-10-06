package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonTerminalListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class TerminalEvents {
   public TerminalEvents() {
   }

   @TriggeredBy(DungeonTerminalListener.class)
   public static class Data extends LunarEvent implements DynamicListenerEvent {
      private final SkyblockMenuType field1;

      @Generated
      public SkyblockMenuType method1() {
         return this.field1;
      }

      @Generated
      public Data(SkyblockMenuType skyblockMenuType) {
         this.field1 = skyblockMenuType;
      }
   }

   @TriggeredBy(DungeonTerminalListener.class)
   public static class TerminalPhaseEvent extends LunarEvent implements DynamicListenerEvent {
      private final long field1;
      private final int field2;

      @Generated
      public long getTimestamp() {
         return this.field1;
      }

      @Generated
      public int method1() {
         return this.field2;
      }

      @Generated
      public TerminalPhaseEvent(long value, int value2) {
         this.field1 = value;
         this.field2 = value2;
      }
   }

   @TriggeredBy(DungeonTerminalListener.class)
   public static class TerminalActivateEvent extends com.moonsworth.lunar.client.event.CancellableEvent implements DynamicListenerEvent {
      private final TerminalEvents.TerminalActivateEvent.Type field1;
      private final String field2;
      private final boolean field3;

      @Generated
      public TerminalEvents.TerminalActivateEvent.Type method1() {
         return this.field1;
      }

      @Generated
      public String method2() {
         return this.field2;
      }

      @Generated
      public boolean method3() {
         return this.field3;
      }

      @Generated
      public TerminalActivateEvent(TerminalEvents.TerminalActivateEvent.Type type, String text, boolean flag) {
         this.field1 = type;
         this.field2 = text;
         this.field3 = flag;
      }

      public enum Type {
         TERMINAL,
         DEVICE,
         LEVER,
         GATE;

         Type() {
         }
      }
   }
}
