package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface ProtectionEnchantmentsBridge {
   Bridge2_26 method1();

   Bridge2_26 method2();

   Bridge2_26 method3();

   Bridge2_26 method4();

   @VersionGate(min = 2)
   Bridge2_26 method5();
}
