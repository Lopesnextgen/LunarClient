package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Locale;
import lombok.Generated;

public enum CosmeticCategory {
   HATS,
   BODYWEAR;

   private final ResourceLocationBridge location = ResourceLocationBridge.create("lunar", "cosmetics/indexes/" + this.name().toLowerCase(Locale.ROOT) + ".json");

   CosmeticCategory() {
   }

   @Generated
   public ResourceLocationBridge getLocation() {
      return this.location;
   }
}
