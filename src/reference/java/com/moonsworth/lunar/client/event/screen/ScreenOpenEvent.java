package com.moonsworth.lunar.client.event.screen;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

public class ScreenOpenEvent extends Highlight {
   private Bridge5Extension6 field1;
   private Bridge5Extension6 field2;

   @Generated
   public Bridge5Extension6 method1() {
      return this.field1;
   }

   @Generated
   public Bridge5Extension6 method2() {
      return this.field2;
   }

   @Generated
   public void method3(Bridge5Extension6 var1) {
      this.field1 = var1;
   }

   @Generated
   public void method4(Bridge5Extension6 var1) {
      this.field2 = var1;
   }

   @Generated
   public ScreenOpenEvent(Bridge5Extension6 var1, Bridge5Extension6 bridge5Extension6) {
      this.field1 = var1;
      this.field2 = bridge5Extension6;
   }
}
