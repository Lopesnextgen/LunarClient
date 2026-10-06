package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import lombok.Generated;

public class PostProcessEvent extends com.moonsworth.lunar.client.highlight.Highlight {
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
   public PostProcessEvent(AbstractRenderContext abstractRenderContext, Bridge3_24 bridge3_24) {
      this.field1 = abstractRenderContext;
      this.field2 = bridge3_24;
   }
}
