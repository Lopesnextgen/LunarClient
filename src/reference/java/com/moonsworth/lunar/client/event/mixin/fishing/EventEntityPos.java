package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

@Annotation2(min = 8)
public class EventEntityPos extends Highlight {
   private Horsestats20Extension field1;

   @Generated
   public Horsestats20Extension method1() {
      return this.field1;
   }

   @Generated
   public EventEntityPos(Horsestats20Extension horsestats20) {
      this.field1 = horsestats20;
   }
}
