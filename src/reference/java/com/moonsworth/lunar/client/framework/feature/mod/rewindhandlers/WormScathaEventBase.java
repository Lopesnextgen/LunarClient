package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.gui.WormScathaSpawnListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public abstract class WormScathaEventBase extends LunarEvent implements DynamicListenerEvent {
   public WormScathaEventBase() {
   }

   @TriggeredBy(WormScathaSpawnListener.class)
   public static class Data extends WormScathaEventBase {
      public Data() {
      }
   }

   @TriggeredBy(WormScathaSpawnListener.class)
   public static class WormScathaSpawnEvent extends WormScathaEventBase {
      private final boolean field1;

      @Generated
      public WormScathaSpawnEvent(boolean flag) {
         this.field1 = flag;
      }

      @Generated
      public boolean method1() {
         return this.field1;
      }
   }
}
