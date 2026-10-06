package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;

public interface TiersBridge {
   ToolMaterialBridge method1();

   ToolMaterialBridge method2();

   ToolMaterialBridge method3();

   ToolMaterialBridge method4();

   ToolMaterialBridge method5();

   @VersionGate(min = 6)
   ToolMaterialBridge method6();

   List<ToolMaterialBridge> method7();
}
