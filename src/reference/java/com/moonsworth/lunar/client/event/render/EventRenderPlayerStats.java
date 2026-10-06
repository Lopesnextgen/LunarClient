package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;

public class EventRenderPlayerStats extends com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase {
   private final boolean field5;

   public EventRenderPlayerStats(AbstractRenderContext bridgeextension_91, MixinHelper_4 mixinhelper_42, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 data, boolean flag) {
      super(bridgeextension_91, mixinhelper_42, data);
      this.field5 = flag;
   }

   @Generated
   public boolean method5() {
      return this.field5;
   }
}
