package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import java.util.List;

public interface F3display_3 {
   default F3display_3 method1(String var1) {
      return this.method3(false, var1);
   }

   default F3display_3 method2(String var1, String var2) {
      return this.method3(true, var1, var2);
   }

   F3display_3 method3(boolean var1, String... var2);

   F3display_3 method4(boolean var1, List<ProfilerResultBridge> var2);

   F3display_3 method5(com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3display var1);

   F3display_3 method6(String var1, String var2);

   boolean method7();

   static F3display$Data2 method8(F3display2 var0) {
      return new F3display$Data2(var0);
   }

   static F3display$Data4 method9(boolean var0) {
      return new F3display$Data4(var0);
   }

   static F3display$Data3 method10(F3display2 var0) {
      return new F3display$Data3(var0);
   }

   static String method11(String var0) {
      return "Not Available < " + var0;
   }
}
