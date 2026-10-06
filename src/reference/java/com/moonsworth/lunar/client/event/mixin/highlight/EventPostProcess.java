package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import lombok.Generated;

public class EventPostProcess extends com.moonsworth.lunar.client.event.LunarEvent {
   private final AbstractRenderContext field1;
   private final Bridge3_24 field2;
   private boolean field3;

   public void method1() {
      this.field3 = true;
   }

   @Generated
   public AbstractRenderContext method2() {
      return this.field1;
   }

   @Generated
   public Bridge3_24 method3() {
      return this.field2;
   }

   @Generated
   public boolean isModified() {
      return this.field3;
   }

   @Generated
   public EventPostProcess(AbstractRenderContext bridgeextension_91, Bridge3_24 bridge3_242) {
      this.field1 = bridgeextension_91;
      this.field2 = bridge3_242;
   }
}
