package com.moonsworth.lunar.client.event.mixin.nameplate;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;

public class EventRenderHud extends EventRenderHudBase {
   public EventRenderHud(AbstractRenderContext bridgeextension_91, MixinHelper_4 mixinhelper_42, Data2 data23) {
      super(bridgeextension_91, mixinhelper_42, data23, true);
   }

   public static class Focused extends EventRenderHudBase.EventRenderHudFocused {
      public Focused(AbstractRenderContext bridgeextension_91, MixinHelper_4 mixinhelper_42, Data2 data23) {
         super(bridgeextension_91, mixinhelper_42, data23, false);
      }

      public Focused(AbstractRenderContext bridgeextension_91, MixinHelper_4 mixinhelper_42, Data2 data23, boolean flag) {
         super(bridgeextension_91, mixinhelper_42, data23, flag);
      }
   }
}
