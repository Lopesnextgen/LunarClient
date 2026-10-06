package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import lombok.Generated;

public interface Bridge6Extension3 extends Bridge6_4 {
   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   int bridge$getArmorIndex();

   EquipmentSlotBridge bridge$getSlot();

   boolean bridge$hasColor(ItemStackBridge var1);

   int bridge$getColor(ItemStackBridge var1);

   Bridge6Extension3.Type bridge$getArmorMaterial();

   String bridge$getResourcePath();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   float bridge$getArmorToughness(ItemStackBridge var1);

   int bridge$getArmorValue(ItemStackBridge var1);

   enum Type {
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

      public static Bridge6Extension3.Type fromMaterial(String text) {
         for (Bridge6Extension3.Type var4 : values()) {
            if (var4.getMaterial().equals(text)) {
               return var4;
            }
         }

         return UNKNOWN;
      }

      @Generated
      public String getMaterial() {
         return this.material;
      }

      @Generated
      Type(String text) {
         this.material = text;
      }
   }
}
