package com.moonsworth.lunar.client.framework.feature.tps;

import com.moonsworth.lunar.client.framework.Client;
import java.util.UUID;
import lombok.Generated;

public enum LevelHeadSource implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   NETWORK("levelHeadSourceNetwork", "NETWORK", 25, 50),
   BEDWARS("levelHeadSourceBedWars", "BEDWARS", 25, 50),
   SKYWARS("levelHeadSourceSkyWars", "SKYWARS", 12, 30);

   private final String id;
   private final String queryParam;
   private final int randomLevelMin;
   private final int randomLevelMax;
   private String nametagPrefix;

   LevelHeadSource(String text3, String text4, int number5, int number6) {
      this.id = text3;
      this.queryParam = text4;
      this.randomLevelMin = number5;
      this.randomLevelMax = number6;
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
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   public int generateRandomLevelForNicked(UUID uuid1) {
      int number2 = this.randomLevelMax - this.randomLevelMin;
      return this.randomFromUuid(uuid1, number2) + this.randomLevelMin;
   }

   private int randomFromUuid(UUID uuid1, int number2) {
      long number3 = uuid1.getMostSignificantBits() ^ uuid1.getLeastSignificantBits();
      number3 = number3 < 0L ? -number3 : number3;
      return (int)(number3 % number2);
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
