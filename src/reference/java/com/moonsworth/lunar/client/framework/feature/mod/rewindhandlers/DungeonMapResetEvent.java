package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.listener.TriggeredBy;

@TriggeredBy(DungeonMapListener.class)
public class DungeonMapResetEvent extends DungeonRoomEvent {
   public DungeonMapResetEvent() {
   }
}
