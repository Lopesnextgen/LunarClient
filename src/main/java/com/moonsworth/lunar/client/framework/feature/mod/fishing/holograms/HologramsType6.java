package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public enum HologramsType6 {
   BAT(false),
   CHEST(false),
   ITEM_DROP(false),
   ESSENCE(true),
   FAIRY_SOUL(true);

   private final boolean nonstandardShape;

   @Override
   public String toString() {
      return WordUtils.capitalizeFully(this.name().replace('_', ' '), new char[]{' '});
   }

   @Generated
   public boolean isNonstandardShape() {
      return this.nonstandardShape;
   }

   @Generated
   HologramsType6(boolean flag) {
      this.nonstandardShape = flag;
   }
}
