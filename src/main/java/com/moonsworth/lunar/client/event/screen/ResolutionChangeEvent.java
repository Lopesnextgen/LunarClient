package com.moonsworth.lunar.client.event.screen;

import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

public class ResolutionChangeEvent extends Highlight {
   private final int field1;
   private final int field2;

   @Generated
   public int getScaledWidth() {
      return this.field1;
   }

   @Generated
   public int getScaledHeight() {
      return this.field2;
   }

   @Generated
   public ResolutionChangeEvent(int value, int value2) {
      this.field1 = value;
      this.field2 = value2;
   }
}
