package com.moonsworth.lunar.client.framework.feature.rewind;

import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SECONDS_30("thirtySixtySeconds", 30),
   MINUTE_1("oneTwoMinute", 60),
   MINUTES_2("twoFourMinutes", 120),
   MINUTES_5("fiveTenMinutes", 300);

   private final String id;
   private final long ms;

   Gui2Extension(String text, int value) {
      this.id = text;
      this.ms = value * 1000L;
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public long getMs() {
      return this.ms;
   }
}
