package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import java.util.List;

public class F3display$Data2 implements F3display_3 {
   private final F3display2 field1;

   public F3display$Data2(F3display2 var1) {
      this.field1 = var1;
   }

   public F3display$Data2 method1(boolean var1, String... var2) {
      for (String var6 : var2) {
         if (var6 == null) {
            return this;
         }
      }

      this.field1.method2(var1, var2);
      return this;
   }

   @Override
   public F3display_3 method4(boolean var1, List<ProfilerResultBridge> var2) {
      if (this.field1.field4) {
         this.field1.method4(var1, F3display2.field2);
      } else {
         this.field1.method4(var1, var2);
      }

      return this;
   }

   @Override
   public F3display_3 method5(com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3display var1) {
      this.field1.method7(var1);
      return this;
   }

   @Override
   public F3display_3 method6(String var1, String var2) {
      return !this.field1.field4 ? this : this.method6(var1, F3display_3.method11(var2));
   }

   @Override
   public boolean method7() {
      return this.field1.field4;
   }
}
