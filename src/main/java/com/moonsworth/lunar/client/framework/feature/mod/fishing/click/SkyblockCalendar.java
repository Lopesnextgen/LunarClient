package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockSeason;
import lombok.Generated;

public final class SkyblockCalendar {
   public static final long FIRST_YEAR_START = 1560275700L;
   public static final long YEAR_SECONDS = 446400L;
   public static final long DAY_SECONDS = 1200L;

   public static long now() {
      return System.currentTimeMillis() / 1000L;
   }

   public static int getYear() {
      return (int)((now() - 1560275700L) / 446400L) + 1;
   }

   public static long getElapsedYearSeconds() {
      return (now() - 1560275700L) % 446400L;
   }

   public static long getElapsedDaySeconds() {
      return getElapsedSeconds(1);
   }

   public static long getElapsedSeconds(int value) {
      return (now() - 1560275700L) % (1200L * value);
   }

   public static SkyblockSeason getSeason() {
      SkyblockSeason[] items0 = SkyblockSeason.values();
      double value1 = getElapsedYearSeconds() / 446400.0;
      int index3 = (int)Math.floor(value1 * items0.length);
      return items0[index3];
   }

   public static boolean isSeasonBetween(SkyblockSeason skyblockSeason, SkyblockSeason skyblockSeason2) {
      SkyblockSeason highlighttype42 = getSeason();
      return highlighttype42.ordinal() >= skyblockSeason.ordinal() && highlighttype42.ordinal() <= skyblockSeason2.ordinal();
   }

   public static boolean isSpring() {
      return isSeasonBetween(SkyblockSeason.EARLY_SPRING, SkyblockSeason.LATE_SPRING);
   }

   public static boolean isAutumn() {
      return isSeasonBetween(SkyblockSeason.EARLY_AUTUMN, SkyblockSeason.LATE_AUTUMN);
   }

   @Generated
   private SkyblockCalendar() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
