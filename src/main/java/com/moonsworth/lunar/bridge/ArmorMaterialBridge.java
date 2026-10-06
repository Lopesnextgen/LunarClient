package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum ArmorMaterialBridge {
   LEATHER("leather"),
   CHAIN("chainmail"),
   IRON("iron"),
   GOLD("gold"),
   DIAMOND("diamond"),
   TURTLE("turtle"),
   NETHERITE("netherite"),
   ARMADILLO("armadillo"),
   TURTLE_SCUTE("turtle_scute"),
   COPPER("copper"),
   UNKNOWN("unknown");

   private final String material;

   public static ArmorMaterialBridge fromMaterial(String text) {
      for (ArmorMaterialBridge bridge6extension3$type4 : values()) {
         if (bridge6extension3$type4.getMaterial().equals(text)) {
            return bridge6extension3$type4;
         }
      }

      return UNKNOWN;
   }

   @Generated
   public String getMaterial() {
      return this.material;
   }

   @Generated
   ArmorMaterialBridge(String text) {
      this.material = text;
   }
}
