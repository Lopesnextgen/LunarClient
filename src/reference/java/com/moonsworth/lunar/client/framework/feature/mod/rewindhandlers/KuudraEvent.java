package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin.KuudraTierListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;

public abstract class KuudraEvent extends LunarEvent implements DynamicListenerEvent {
   public KuudraEvent() {
   }

   @TriggeredBy(KuudraTierListener.class)
   public static class Data extends KuudraEvent {
      public Data() {
      }
   }

   @TriggeredBy(KuudraTierListener.class)
   public static class KuudraEnterEvent extends KuudraEvent {
      public KuudraEnterEvent() {
      }
   }
}
