package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;
import net.kyori.adventure.text.Component;

public interface EntityListBridge {
   Bridge8_6 method1();

   Bridge8_6 method2();

   Bridge8_6 method3();

   Component method4(String text1);

   @VersionGate(max = 5)
   int bridge$getEntityId(String text1);

   Map<String, Class<BridgeExtension>> method5();
}
