package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class RewindFrameEvent extends Highlight {
   private boolean field1;

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Generated
   public RewindFrameEvent(boolean flag) {
      this.field1 = flag;
   }
}
