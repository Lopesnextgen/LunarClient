package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge2_20;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public class ButtonRenderEvent extends HighlightImpl {
   private final Bridge5Extension6 field1;
   private final Bridge2_20 field2;

   @Generated
   public Bridge5Extension6 method1() {
      return this.field1;
   }

   @Generated
   public Bridge2_20 method2() {
      return this.field2;
   }

   @Generated
   public ButtonRenderEvent(Bridge5Extension6 bridge5Extension6, Bridge2_20 bridge2_20) {
      this.field1 = bridge5Extension6;
      this.field2 = bridge2_20;
   }
}
