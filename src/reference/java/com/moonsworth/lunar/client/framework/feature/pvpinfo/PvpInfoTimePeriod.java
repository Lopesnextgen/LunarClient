package com.moonsworth.lunar.client.framework.feature.pvpinfo;

public enum PvpInfoTimePeriod implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SESSION("session"),
   DAY("day"),
   WEEK("week"),
   MONTH("month"),
   YEAR("year"),
   ALL_TIME("allTime");

   private final String id;

   PvpInfoTimePeriod(String text3) {
      this.id = text3;
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id(), new Object[0]);
   }
}
