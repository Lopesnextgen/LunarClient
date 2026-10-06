package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class SkyblockProfileEvents {
   public SkyblockProfileEvents() {
   }

   @TriggeredBy(ProfileIdListener.class)
   public static class SkyblockProfileIdEvent extends LunarEvent implements DynamicListenerEvent {
      private final String field1;

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public SkyblockProfileIdEvent(String text1) {
         this.field1 = text1;
      }
   }

   @TriggeredBy(SkyblockProfileCache.class)
   public static class SkyblockProfileLoadEvent extends LunarEvent implements DynamicListenerEvent {
      public SkyblockProfileLoadEvent() {
      }
   }

   @TriggeredBy(ProfileIdListener.class)
   public static class SkyblockProfileChangeEvent extends LunarEvent implements DynamicListenerEvent {
      private final String field1;
      private final String field2;

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public String method2() {
         return this.field2;
      }

      @Generated
      public SkyblockProfileChangeEvent(String text1, String text) {
         this.field1 = text1;
         this.field2 = text;
      }
   }
}
