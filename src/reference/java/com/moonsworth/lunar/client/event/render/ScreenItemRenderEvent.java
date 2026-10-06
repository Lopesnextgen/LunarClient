package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.Highlight;

public class ScreenItemRenderEvent extends Highlight {
   private final Bridge5Extension_3 field1;
   private final Bridge3_18 field2;
   private ItemStackBridge field3;

   @Generated
   public Bridge5Extension_3 method1() {
      return this.field1;
   }

   @Generated
   public Bridge3_18 method2() {
      return this.field2;
   }

   @Generated
   public ItemStackBridge method3() {
      return this.field3;
   }

   @Generated
   public ScreenItemRenderEvent(Bridge5Extension_3 var1, Bridge3_18 bridge3_18) {
      this.field1 = var1;
      this.field2 = bridge3_18;
   }

   @Generated
   public void method4(ItemStackBridge var1) {
      this.field3 = var1;
   }
}
