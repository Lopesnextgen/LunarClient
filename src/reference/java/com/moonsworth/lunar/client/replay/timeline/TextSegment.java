package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.List;
import org.apache.commons.lang3.Range;

public class TextSegment extends EffectSegment {
   public TextSegment(UndoRedoManager nameplate21) {
      super(nameplate21);
   }

   @Override
   public EffectSegment method2(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      TextSegment rewindimpl24 = new TextSegment(nameplate21);
      return (EffectSegment)this.method16(rewindimpl24, nameplate21, range2, range3);
   }

   @Override
   public List<String> method15() {
      return List.of("text");
   }

   @Override
   public String type() {
      return "text";
   }

   @Override
   public JsonElement method16(ReplayTimeline highlight_31, Range<Integer> range2, RewindHandlers rewindhandlers3, Runnable runnable4) {
      JsonObject json5 = super.method16(highlight_31, range2, rewindhandlers3, runnable4).getAsJsonObject();
      json5.addProperty("thumbnail", "rewind/text.png");
      return json5;
   }
}
