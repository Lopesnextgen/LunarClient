package com.moonsworth.lunar.client.event.mixin.nameplate;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class EventRenderHudLegacy extends HudBaseRenderEvent {
   public EventRenderHudLegacy(AbstractRenderContext var1, MixinHelper_4 var2, MarkerModel.Data2 var3) {
      super(var1, var2, var3, true);
   }

   public static class Data extends HudBaseRenderEvent.Data4 {
      public Data(AbstractRenderContext var1, MixinHelper_4 var2, MarkerModel.Data2 var3) {
         super(var1, var2, var3, false);
      }

      public Data(AbstractRenderContext var1, MixinHelper_4 var2, MarkerModel.Data2 var3, boolean flag) {
         super(var1, var2, var3, flag);
      }
   }
}
