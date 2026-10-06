package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;
import com.moonsworth.lunar.client.event.DynamicListenerEvent;
import com.moonsworth.lunar.client.event.LunarEvent;

@TriggeredBy(DungeonFloorListener.class)
public class DungeonExitEvent extends LunarEvent implements DynamicListenerEvent {
   public DungeonExitEvent() {
   }
}
