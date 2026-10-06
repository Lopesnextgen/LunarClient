package com.moonsworth.lunar.client.framework.feature.heightlimit;

import com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimit.Type;
import lombok.Generated;

public enum HeightlimitType {
   BEDWARS("HEIGHT_LIMIT_BEDWARS", Type.BEDWARS),
   BRIDGE("HEIGHT_LIMIT_BRIDGE", Type.BRIDGE),
   VANILLA("HEIGHT_LIMIT_VANILLA", Type.ALL),
   SERVER("HEIGHT_LIMIT_SERVER", Type.ALL);

   private final String featureId;
   private final Type filter;

   @Generated
   public String getFeatureId() {
      return this.featureId;
   }

   @Generated
   public Type getFilter() {
      return this.filter;
   }

   @Generated
   HeightlimitType(String text, Type type) {
      this.featureId = text;
      this.filter = type;
   }
}
