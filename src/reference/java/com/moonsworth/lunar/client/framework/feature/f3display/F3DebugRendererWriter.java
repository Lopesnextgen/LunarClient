package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import java.util.List;

public class F3DebugRendererWriter implements F3DebugWriter {
   private final F3DebugRenderer field1;

   public F3DebugRendererWriter(F3DebugRenderer f3display21) {
      this.field1 = f3display21;
   }

   public F3DebugRendererWriter method1(boolean flag1, String... items2) {
      for (String text6 : items2) {
         if (text6 == null) {
            return this;
         }
      }

      this.field1.method2(flag1, items2);
      return this;
   }

   @Override
   public F3DebugWriter method4(boolean flag1, List<ProfilerResultBridge> list2) {
      if (this.field1.field4) {
         this.field1.method4(flag1, F3DebugRenderer.field2);
      } else {
         this.field1.method4(flag1, list2);
      }

      return this;
   }

   @Override
   public F3DebugWriter method5(com.moonsworth.lunar.client.framework.feature.f3display.chart.F3Chart f3display1) {
      this.field1.method7(f3display1);
      return this;
   }

   @Override
   public F3DebugWriter method6(String text1, String text2) {
      return !this.field1.field4 ? this : this.HCHHRHHCRIIORRRICOOCCOCHIRRRRR(text1, F3DebugWriter.method11(text2));
   }

   @Override
   public boolean method7() {
      return this.field1.field4;
   }
}
