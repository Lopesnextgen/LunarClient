package com.moonsworth.lunar.client.framework.feature.tps;

import com.moonsworth.lunar.client.framework.Client;
import java.util.UUID;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   NETWORK("levelHeadSourceNetwork", "NETWORK", 25, 50),
   BEDWARS("levelHeadSourceBedWars", "BEDWARS", 25, 50),
   SKYWARS("levelHeadSourceSkyWars", "SKYWARS", 12, 30);

   private final String id;
   private final String queryParam;
   private final int randomLevelMin;
   private final int randomLevelMax;
   private String nametagPrefix;

   Gui2Extension(String var3, String text, int value, int value2) {
      this.id = var3;
      this.queryParam = text;
      this.randomLevelMin = value;
      this.randomLevelMax = value2;
      this.updateNametagPrefixLang();
   }

   public void updateNametagPrefixLang() {
      this.nametagPrefix = Client.method109().method67().method2("settings", this.id + "Prefix", new Object[0]);
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   public int generateRandomLevelForNicked(UUID var1) {
      int var2 = this.randomLevelMax - this.randomLevelMin;
      return this.randomFromUuid(var1, var2) + this.randomLevelMin;
   }

   private int randomFromUuid(UUID var1, int var2) {
      long var3 = var1.getMostSignificantBits() ^ var1.getLeastSignificantBits();
      var3 = var3 < 0L ? -var3 : var3;
      return (int)(var3 % var2);
   }

   @Generated
   public String getQueryParam() {
      return this.queryParam;
   }

   @Generated
   public String getNametagPrefix() {
      return this.nametagPrefix;
   }
}
