package com.moonsworth.lunar.bridge;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/entity/item/EntityTNTPrimed")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/world/entity/item/PrimedTnt"))
   }
)
public interface EntityTNTPrimedBridge extends BridgeExtension {
   int bridge$getMaximumFuse();

   void bridge$setMaximumFuse(int var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("fuse")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("life")),
            @BridgeVersionMapping(version = 8, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("getFuse()I"))
      }
   )
   int bridge$getFuse();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("fuse")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("life")),
            @BridgeVersionMapping(version = 8, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("setFuse(I)V"))
      }
   )
   void method1(int var1);
}
