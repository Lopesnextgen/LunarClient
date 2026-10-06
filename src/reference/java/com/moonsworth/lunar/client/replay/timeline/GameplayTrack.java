package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import com.moonsworth.lunar.client.replay.timeline.HashMapImpl;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;

@SerializedNameOnly
public class GameplayTrack extends Track<GameplaySegment> {
   public GameplayTrack(UndoRedoManager nameplate21, HashMapImpl map) {
      super(nameplate21, map);
   }

   @Override
   public String type() {
      return "gameplay";
   }
}
