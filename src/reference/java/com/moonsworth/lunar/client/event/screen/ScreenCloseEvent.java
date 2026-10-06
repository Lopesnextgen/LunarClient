package com.moonsworth.lunar.client.event.screen;

import com.moonsworth.lunar.bridge.Bridge7_8;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public class ScreenCloseEvent extends HighlightImpl {
   private final Bridge7_8 field1;

   @Generated
   public ScreenCloseEvent(Bridge7_8 bridge7_8) {
      this.field1 = bridge7_8;
   }

   @Generated
   public Bridge7_8 method1() {
      return this.field1;
   }
}
