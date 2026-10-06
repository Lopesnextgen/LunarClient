package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.framework.listener.TabListListener;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;

@TriggeredBy(TabListListener.class)
public class EventTabListUpdate extends LunarEvent implements DynamicListenerEvent {
   public EventTabListUpdate() {
   }
}
