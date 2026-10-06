package com.moonsworth.lunar.client.event.screen;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

public class ScreenUpdateEvent extends Highlight {
   private Bridge5Extension6 field1;

   @Generated
   public ScreenUpdateEvent(Bridge5Extension6 var1) {
      this.field1 = var1;
   }

   @Generated
   public Bridge5Extension6 method1() {
      return this.field1;
   }

   @Generated
   public void method2(Bridge5Extension6 var1) {
      this.field1 = var1;
   }
}
