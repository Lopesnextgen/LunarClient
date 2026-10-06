package com.moonsworth.lunar.bridge;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/profiler/Profiler$Result")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/util/profiling/ResultField"))
   }
)
public interface ProfilerResultBridge {
   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("field_76332_a")),
            @BridgeVersionMapping(version = 5, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("usePercentage")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("percentage"))
      }
   )
   double method1();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("field_76330_b")),
            @BridgeVersionMapping(version = 5, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("totalUsePercentage")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("globalPercentage"))
      }
   )
   double method2();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("field_76331_c")),
            @BridgeVersionMapping(version = 5, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("profilerName")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("name"))
      }
   )
   String bridge$getName();
}
