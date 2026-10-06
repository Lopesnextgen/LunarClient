package com.moonsworth.lunar.client.driver.core;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.config.SystemConfig;
import com.moonsworth.webosr.config.UltralightConfig;

public class UltralightConfigFactoryLegacy extends DriverViewContextLegacy<UltralightConfig> {
   private final boolean field5;
   private final int sampleSizeGPU;
   private final String resourcePath;

   public UltralightConfigFactoryLegacy(boolean var1, int var2, String var3) {
      super("UL", false);
      this.field5 = var1;
      this.sampleSizeGPU = var2;
      this.resourcePath = var3;
   }

   public UltralightConfig method3() {
      UltralightConfig var1 = new UltralightConfig();
      var1.sampleSizeGPU = this.sampleSizeGPU;
      var1.resourcePath = this.resourcePath;
      SystemConfig var2 = new SystemConfig();
      var2.display = ThreadModuleDump63.method3().bridge$getWindow().bridge$getDisplayHandle();
      var2.window = ThreadModuleDump63.method3().bridge$getWindow().bridge$getWindowHandle();
      var1.system = var2;
      var1.animationTimer = 0.008333333333333333;
      var1.scrollTimer = 0.008333333333333333;
      return var1;
   }
}
