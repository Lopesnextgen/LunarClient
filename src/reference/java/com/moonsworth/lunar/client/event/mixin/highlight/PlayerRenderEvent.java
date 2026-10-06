package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import lombok.Generated;

public class PlayerRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final AbstractRenderContext field1;
   private final EntityPlayerBridge field2;
   private final boolean field3;

   @Generated
   public AbstractRenderContext method1() {
      return this.field1;
   }

   @Generated
   public EntityPlayerBridge method2() {
      return this.field2;
   }

   @Generated
   public boolean method3() {
      return this.field3;
   }

   @Generated
   public PlayerRenderEvent(AbstractRenderContext abstractRenderContext, EntityPlayerBridge entity, boolean flag) {
      this.field1 = abstractRenderContext;
      this.field2 = entity;
      this.field3 = flag;
   }
}
