package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Nameplate8;
import com.moonsworth.lunar.client.framework.listener.Nameplate;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface Framework7 {
   static Nameplate8 method1(Framework7Extension var0, Nameplate var1) {
      return new Nameplate8(var0, var1);
   }

   static Nameplate8 method2(Framework7Extension var0, Nameplate var1) {
      return new Nameplate8(var0, () -> !var1.isEnabled());
   }

   boolean method3();

   @Internal
   void method4();
}
