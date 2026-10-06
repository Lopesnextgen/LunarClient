package com.moonsworth.lunar.client.event.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.ChatMessageListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.ChatMessageParser.Extension;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

@TriggeredBy(ChatMessageListener.class)
public final class EventNameplateExtension extends LunarEvent implements DynamicListenerEvent {
   private final Extension field1;

   @Generated
   public Extension method1() {
      return this.field1;
   }

   @Generated
   public EventNameplateExtension(Extension extension1) {
      this.field1 = extension1;
   }
}
