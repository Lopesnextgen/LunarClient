package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class EffectSegment extends RewindIterator<EffectSegment> {
   public EffectSegment(UndoRedoManager nameplate21) {
      super(nameplate21);
   }

   public EffectSegment method2(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      EffectSegment rewind_24 = new EffectSegment(nameplate21);
      return (EffectSegment)this.method3(rewind_24, nameplate21, range2, range3);
   }

   @Override
   public String type() {
      return "effect";
   }
}
