package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType4;
import lombok.Generated;

public final class Click11 {
   public static final long SKYBLOCK_EPOCH = 1560275700L;
   public static final long YEAR_DURATION_SECONDS = 446400L;
   public static final long DAY_DURATION_SECONDS = 1200L;

   public static long getCurrentTimeSeconds() {
      return System.currentTimeMillis() / 1000L;
   }

   public static int getYear() {
      return (int)((getCurrentTimeSeconds() - 1560275700L) / 446400L) + 1;
   }

   public static long getYearSeconds() {
      return (getCurrentTimeSeconds() - 1560275700L) % 446400L;
   }

   public static long getDaySeconds() {
      return getPeriodSeconds(1);
   }

   public static long getPeriodSeconds(int var0) {
      return (getCurrentTimeSeconds() - 1560275700L) % (1200L * var0);
   }

   public static HighlightType4 getSeason() {
      HighlightType4[] var0 = HighlightType4.values();
      double var1 = getYearSeconds() / 446400.0;
      int var3 = (int)Math.floor(var1 * var0.length);
      return var0[var3];
   }

   public static boolean isSeasonBetween(HighlightType4 var0, HighlightType4 var1) {
      HighlightType4 var2 = getSeason();
      return var2.ordinal() >= var0.ordinal() && var2.ordinal() <= var1.ordinal();
   }

   public static boolean isSpring() {
      return isSeasonBetween(HighlightType4.EARLY_SPRING, HighlightType4.LATE_SPRING);
   }

   public static boolean isAutumn() {
      return isSeasonBetween(HighlightType4.EARLY_AUTUMN, HighlightType4.LATE_AUTUMN);
   }

   @Generated
   private Click11() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
