package com.moonsworth.lunar.client.feature;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Locale;
import lombok.Generated;

public enum ModuleType2 {
   HATS,
   BODYWEAR;

   private final ResourceLocationBridge location = ResourceLocationBridge.create("lunar", "cosmetics/indexes/" + this.name().toLowerCase(Locale.ROOT) + ".json");

   @Generated
   public ResourceLocationBridge getLocation() {
      return this.location;
   }
}
