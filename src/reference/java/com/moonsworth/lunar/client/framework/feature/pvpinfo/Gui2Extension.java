package com.moonsworth.lunar.client.framework.feature.pvpinfo;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SESSION("session"),
   DAY("day"),
   WEEK("week"),
   MONTH("month"),
   YEAR("year"),
   ALL_TIME("allTime");

   private final String id;

   Gui2Extension(String text) {
      this.id = text;
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id(), new Object[0]);
   }
}
