package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import java.util.List;

public class F3DebugConditionalWriter implements F3DebugWriter {
   private final F3DebugRenderer field1;

   public F3DebugConditionalWriter(F3DebugRenderer f3display21) {
      this.field1 = f3display21;
   }

   @Override
   public F3DebugWriter method3(boolean flag1, String... items2) {
      for (String text6 : items2) {
         if (text6 == null) {
            return this;
         }
      }

      if (this.field1.method12()) {
         this.field1.method1(items2);
      }

      return this;
   }

   @Override
   public F3DebugWriter method4(boolean flag1, List<ProfilerResultBridge> list) {
      if (this.field1.method12()) {
         if (this.field1.field4) {
            this.field1.method3(F3DebugRenderer.field2);
         } else {
            this.field1.method3(list);
         }
      }

      return this;
   }

   @Override
   public F3DebugWriter method5(com.moonsworth.lunar.client.framework.feature.f3display.chart.F3Chart f3display1) {
      if (this.field1.method12()) {
         this.field1.method6(f3display1);
      }

      return this;
   }

   @Override
   public F3DebugWriter method6(String text, String text2) {
      if (!this.field1.field4) {
         return this;
      }

      if (this.field1.method12()) {
         this.field1.method1(text, F3DebugWriter.method11(text2));
      }

      return this;
   }

   @Override
   public boolean method7() {
      return this.field1.field4;
   }
}
