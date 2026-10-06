package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import java.util.List;

public interface F3DebugWriter {
   default F3DebugWriter method1(String text1) {
      return this.method3(false, text1);
   }

   default F3DebugWriter method2(String text1, String text2) {
      return this.method3(true, text1, text2);
   }

   F3DebugWriter method3(boolean flag1, String... items2);

   F3DebugWriter method4(boolean flag1, List<ProfilerResultBridge> list2);

   F3DebugWriter method5(com.moonsworth.lunar.client.framework.feature.f3display.chart.F3Chart f3display1);

   F3DebugWriter method6(String text1, String text2);

   boolean method7();

   static F3DebugRendererWriter method8(F3DebugRenderer f3display20) {
      return new F3DebugRendererWriter(f3display20);
   }

   static F3DebugMeasurer method9(boolean flag) {
      return new F3DebugMeasurer(flag);
   }

   static F3DebugConditionalWriter method10(F3DebugRenderer f3display20) {
      return new F3DebugConditionalWriter(f3display20);
   }

   static String method11(String text) {
      return "Not Available < " + text;
   }
}
