package com.moonsworth.lunar.bridge;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/enchantment/Enchantment")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/world/item/enchantment/Enchantment"))
   }
)
public interface Bridge2_26 {
   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("getWeight")),
            @BridgeVersionMapping(version = 5, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"rarity", "getWeight"})),
            @BridgeVersionMapping(version = 22, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"definition", "weight"}))
      }
   )
   int method1();

   @Annotation("getMinLevel")
   int method2();

   @Annotation("getMaxLevel")
   int method3();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("getMinEnchantability")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("getMinCost"))
      }
   )
   int method4(int var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("getMaxEnchantability")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("getMaxCost"))
      }
   )
   int method5(int var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("canApplyTogether")),
            @BridgeVersionMapping(version = 5, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("isCompatibleWith")),
            @BridgeVersionMapping(version = 24, IHCHIIICCCHCCIROHOHROHOOHRCHRC = {})
      }
   )
   boolean bridge$canApplyTogether(Bridge2_26 var1);

   default boolean bridge$canApply(ItemStackBridge var1) {
      return true;
   }

   boolean bridge$isProtection();

   boolean bridge$isEnchantment(Bridge2_26 var1);
}
