package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Nameplate8;
import com.moonsworth.lunar.client.framework.listener.Nameplate;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface ModRestriction {
   static Nameplate8 method1(Framework7Extension framework7extension0, Nameplate nameplate1) {
      return new Nameplate8(framework7extension0, nameplate1);
   }

   static Nameplate8 method2(Framework7Extension framework7extension0, Nameplate nameplate1) {
      return new Nameplate8(framework7extension0, () -> !nameplate1.isEnabled());
   }

   boolean method3();

   @Internal
   void method4();
}
