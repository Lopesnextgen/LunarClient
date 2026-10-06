package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import lombok.Generated;

public enum KuudraTier {
   NONE(null),
   T1("Basic"),
   T2("Hot"),
   T3("Burning"),
   T4("Fiery"),
   T5("Infernal");

   private final String tierName;

   @Generated
   public String getTierName() {
      return this.tierName;
   }

   @Generated
   KuudraTier(String text) {
      this.tierName = text;
   }
}
