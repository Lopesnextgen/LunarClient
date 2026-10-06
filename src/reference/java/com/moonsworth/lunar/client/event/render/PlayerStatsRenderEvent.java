package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class PlayerStatsRenderEvent extends com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent {
   private final boolean field5;

   public PlayerStatsRenderEvent(AbstractRenderContext abstractRenderContext, MixinHelper_4 mixinHelper_4, MarkerModel.Data4 data, boolean flag) {
      super(abstractRenderContext, mixinHelper_4, data);
      this.field5 = flag;
   }

   @Generated
   public boolean method5() {
      return this.field5;
   }
}
