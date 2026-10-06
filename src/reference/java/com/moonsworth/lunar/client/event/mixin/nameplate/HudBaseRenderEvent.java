package com.moonsworth.lunar.client.event.mixin.nameplate;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class HudBaseRenderEvent extends Highlight {
   private final AbstractRenderContext field1;
   private final MixinHelper_4 field2;
   private final MarkerModel<?> field3;
   private final boolean field4;

   public HudBaseRenderEvent(AbstractRenderContext var1, MixinHelper_4 var2, MarkerModel<?> var3) {
      this(var1, var2, var3, true);
   }

   public LegacyGuiGraphicsBridge method1() {
      return new LegacyGuiGraphicsBridge(this.field1);
   }

   @Generated
   public HudBaseRenderEvent(AbstractRenderContext var1, MixinHelper_4 var2, MarkerModel<?> var3, boolean var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
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

   public static class Data4 extends HudBaseRenderEvent {
      public Data4(AbstractRenderContext var1, MixinHelper_4 var2, MarkerModel<?> var3, boolean var4) {
         super(var1, var2, var3, var4);
      }
   }
}
