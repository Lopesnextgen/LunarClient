package com.moonsworth.lunar.client.event.mixin.nameplate;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class EventRenderHudBase extends LunarEvent {
   private final AbstractRenderContext field1;
   private final MixinHelper_4 field2;
   private final MarkerModel<?> field3;
   private final boolean field4;

   public EventRenderHudBase(AbstractRenderContext bridgeextension_91, MixinHelper_4 mixinhelper_42, MarkerModel<?> markers3) {
      this(bridgeextension_91, mixinhelper_42, markers3, true);
   }

   public LegacyGuiGraphicsBridge method1() {
      return new LegacyGuiGraphicsBridge(this.field1);
   }

   @Generated
   public EventRenderHudBase(AbstractRenderContext bridgeextension_91, MixinHelper_4 mixinhelper_42, MarkerModel<?> markers3, boolean flag4) {
      this.field1 = bridgeextension_91;
      this.field2 = mixinhelper_42;
      this.field3 = markers3;
      this.field4 = flag4;
   }

   @Generated
   public MixinHelper_4 method2() {
      return this.field2;
   }

   @Generated
   public MarkerModel<?> method3() {
      return this.field3;
   }

   @Generated
   public boolean method4() {
      return this.field4;
   }

   public static class EventRenderHudFocused extends EventRenderHudBase {
      public EventRenderHudFocused(AbstractRenderContext bridgeextension_91, MixinHelper_4 mixinhelper_42, MarkerModel<?> markers3, boolean flag4) {
         super(bridgeextension_91, mixinhelper_42, markers3, flag4);
      }
   }
}
