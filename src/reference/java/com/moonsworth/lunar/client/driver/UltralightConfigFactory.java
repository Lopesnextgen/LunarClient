package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.config.SystemConfig;
import com.moonsworth.webosr.config.UltralightConfig;
import com.moonsworth.lunar.client.driver.core.DriverViewContextLegacy;

public class UltralightConfigFactory extends DriverViewContextLegacy<UltralightConfig> {
   private final boolean field5;
   private final int sampleSizeGPU;
   private final String resourcePath;

   public UltralightConfigFactory(boolean flag1, int number2, String text3) {
      super("UL", false);
      this.field5 = flag1;
      this.sampleSizeGPU = number2;
      this.resourcePath = text3;
   }

   public UltralightConfig method3() {
      UltralightConfig ultralightconfig1 = new UltralightConfig();
      ultralightconfig1.sampleSizeGPU = this.sampleSizeGPU;
      ultralightconfig1.resourcePath = this.resourcePath;
      SystemConfig systemconfig2 = new SystemConfig();
      systemconfig2.display = Ref.method3().bridge$getWindow().bridge$getDisplayHandle();
      systemconfig2.window = Ref.method3().bridge$getWindow().bridge$getWindowHandle();
      ultralightconfig1.system = systemconfig2;
      ultralightconfig1.animationTimer = 0.008333333333333333;
      ultralightconfig1.scrollTimer = 0.008333333333333333;
      return ultralightconfig1;
   }
}
