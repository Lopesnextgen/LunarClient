package com.moonsworth.lunar.client.event.mixin.nameplate;

import com.moonsworth.lunar.bridge.Bridge2$Data;
import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

public class HudRenderLegacyEvent extends Highlight {
   private final AbstractRenderContext field1;
   private final Bridge2_19 field2;
   private float field3;

   public static void method1(AbstractRenderContext var0, Bridge2_19 var1, float var2) {
      ClientEventBus.method29().method12(HudRenderLegacyEvent.class, () -> new HudRenderLegacyEvent(var0, var1, var2));
   }

   @Annotation2(max = 5)
   public static void method2(float var0) {
      Bridge2$Data var1 = new Bridge2$Data(var0, ThreadModuleDump63.method3().bridge$getRenderViewEntity());
      method1(AbstractRenderContext.method9(var0), var1, var0);
   }

   @Generated
   public AbstractRenderContext method3() {
      return this.field1;
   }

   @Generated
   public Bridge2_19 method4() {
      return this.field2;
   }

   @Generated
   public float method5() {
      return this.field3;
   }

   @Generated
   public HudRenderLegacyEvent(AbstractRenderContext var1, Bridge2_19 var2, float value) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = value;
   }
}
