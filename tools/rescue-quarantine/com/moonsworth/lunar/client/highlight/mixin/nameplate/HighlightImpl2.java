package com.moonsworth.lunar.client.highlight.mixin.nameplate;

import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.Bridge2.Data;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.highlight.Highlight2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

public class HighlightImpl2 extends Highlight {
   private final BridgeExtension_9 field1;
   private final Bridge2_19 field2;
   private float field3;

   public static void method1(BridgeExtension_9 var0, Bridge2_19 var1, float var2) {
      Highlight2.method29().method12(HighlightImpl2.class, () -> new HighlightImpl2(var0, var1, var2));
   }

   @Annotation2(max = 5)
   public static void method2(float var0) {
      Data var1 = new Data(var0, ThreadModuleDump63.method3().bridge$getRenderViewEntity());
      method1(BridgeExtension_9.method9(var0), var1, var0);
   }

   @Generated
   public BridgeExtension_9 method3() {
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
   public HighlightImpl2(BridgeExtension_9 var1, Bridge2_19 var2, float var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }
}
