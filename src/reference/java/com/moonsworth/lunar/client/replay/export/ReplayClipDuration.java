package com.moonsworth.lunar.client.replay.export;

import lombok.Generated;

public enum ReplayClipDuration implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SECONDS_30("thirtySixtySeconds", 30),
   MINUTE_1("oneTwoMinute", 60),
   MINUTES_2("twoFourMinutes", 120),
   MINUTES_5("fiveTenMinutes", 300);

   private final String id;
   private final long ms;

   ReplayClipDuration(String text3, int number4) {
      this.id = text3;
      this.ms = number4 * 1000L;
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
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
