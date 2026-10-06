package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class ThreadModuleDump11 {
   public static String formatDuration(long var0) {
      if (var0 < 0L) {
         return "now";
      }

      String var2 = DurationFormatUtils.formatDuration(var0, "d'd 'H'h 'm'm 's's'");
      var2 = " " + var2;
      String var3 = StringUtils.replaceOnce(var2, " 0d", "");
      if (var3.length() != var2.length()) {
         var2 = var3;
         var3 = StringUtils.replaceOnce(var2, " 0h", "");
         if (var3.length() != var2.length()) {
            var2 = var3;
            var3 = StringUtils.replaceOnce(var2, " 0m", "");
            if (var3.length() != var2.length()) {
               var2 = StringUtils.replaceOnce(var3, " 0s", "");
            }
         }
      }

      String var4 = var2.trim();
      return !var4.isEmpty() ? var4 : "0s";
   }

   public static String formatClock(long var0) {
      int var2 = (int)(var0 / 1000L);
      int var3 = var2 / 60;
      var2 %= 60;
      int var4 = var3 / 60;
      var3 %= 60;
      int var5 = var4 / 24;
      var4 %= 24;
      StringBuilder var6 = new StringBuilder();
      if (var5 > 0) {
         var6.append(String.format("%02d", var5)).append(":");
      }

      if (var4 > 0) {
         var6.append(String.format("%02d", var4)).append(":");
      }

      var6.append(String.format("%02d", var3)).append(":").append(String.format("%02d", var2));
      return var6.toString();
   }

   public static String formatTimeAgo(long var0) {
      long var2 = 1000L;
      long var4 = 60000L;
      long var6 = 3600000L;
      long var8 = 86400000L;
      long var10 = 604800000L;
      long var12 = 2419200000L;
      long var14 = 29030400000L;
      long var16 = System.currentTimeMillis();
      long var18 = var16 - var0;
      TranslationManager var20 = ThreadModuleDump63.method4().method67();
      if (var18 < 0L) {
         return var20.method2("time.ago", "future");
      } else if (var18 < 1000L) {
         return var20.method2("time.ago", "now");
      } else if (var18 < 60000L) {
         long var28 = var18 / 1000L;
         return var28 == 1L ? var20.method2("time.ago", "second", var28) : var20.method2("time.ago", "seconds", var28);
      } else if (var18 < 3600000L) {
         long var27 = var18 / 60000L;
         return var27 == 1L ? var20.method2("time.ago", "minute", var27) : var20.method2("time.ago", "minutes", var27);
      } else if (var18 < 86400000L) {
         long var26 = var18 / 3600000L;
         return var26 == 1L ? var20.method2("time.ago", "hour", var26) : var20.method2("time.ago", "hours", var26);
      } else if (var18 < 604800000L) {
         long var25 = var18 / 86400000L;
         return var25 == 1L ? var20.method2("time.ago", "day", var25) : var20.method2("time.ago", "days", var25);
      } else if (var18 < 2419200000L) {
         long var24 = var18 / 604800000L;
         return var24 == 1L ? var20.method2("time.ago", "week", var24) : var20.method2("time.ago", "weeks", var24);
      } else if (var18 < 29030400000L) {
         long var23 = var18 / 2419200000L;
         return var23 == 1L ? var20.method2("time.ago", "month", var23) : var20.method2("time.ago", "months", var23);
      } else {
         long var21 = var18 / 29030400000L;
         return var21 == 1L ? var20.method2("time.ago", "year", var21) : var20.method2("time.ago", "years", var21);
      }
   }

   public static String formatDateTime(long var0) {
      Date var2 = new Date(var0);
      SimpleDateFormat var3;
      if (Calendar.getInstance().get(1) == var2.getYear() + 1900) {
         var3 = new SimpleDateFormat("d MMM HH:mm:ss");
      } else {
         var3 = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
      }

      return var3.format(var2);
   }

   public static String formatSeconds(long var0) {
      float var2 = Math.round((float)var0 / 10.0F) / 100.0F;
      return String.format("%.2f", var2);
   }

   private static String formatWithPattern(String var0, long var1, boolean var3, boolean var4, boolean var5) {
      if (var3) {
         long var12 = var1 / 3600000L % 24L;
         long var8 = var1 / 60000L % 60L;
         if (var4) {
            long var10 = var1 / 1000L % 60L;
            return var5 ? String.format(var0, var12, var8, var10, var1 % 1000L) : String.format(var0, var12, var8, var10);
         } else {
            return String.format(var0, var12, var8);
         }
      } else if (var4) {
         long var6 = var1 / 1000L;
         return var5 ? String.format(var0, var6, var1 % 1000L) : String.format(var0, var6);
      } else {
         return String.format(var0, var1);
      }
   }

   private static String formatCompact(long var0) {
      double var2 = var0 / 1000.0;
      if (var2 < 60.0) {
         double var14 = Math.round(var2 * 10.0) / 10.0;
         return var14 + "s";
      } else {
         long var4 = var0 / 1000L;
         long var6 = var4 % 60L;
         long var8 = var4 / 60L % 60L;
         long var10 = var4 / 3600L % 24L;
         long var12 = var4 / 86400L;
         if (var12 > 0L) {
            return var12 + "d" + var10 + "h";
         } else {
            return var10 > 0L ? var10 + "h" + var8 + "m" : var8 + "m" + var6 + "s";
         }
      }
   }

   private static String formatVerbose(long var0) {
      long var2 = var0 / 86400000L;
      long var4 = var0 / 3600000L % 24L;
      long var6 = var0 / 60000L % 60L;
      long var8 = var0 / 1000L % 60L;
      StringBuilder var10 = new StringBuilder();
      boolean var11 = false;
      if (var2 > 0L || var11) {
         if (var11) {
            var10.append(" ");
         }

         var11 = true;
         var10.append(var2).append("d");
      }

      if (var4 > 0L || var11) {
         if (var11) {
            var10.append(" ");
         }

         var11 = true;
         var10.append(var4).append("h");
      }

      if (var6 > 0L || var11) {
         if (var11) {
            var10.append(" ");
         }

         var11 = true;
         var10.append(var6).append("m");
      }

      if (var11) {
         var10.append(" ");
      }

      var11 = true;
      var10.append(var8).append("s");
      return var10.toString();
   }

   public enum Type {
      DEFAULT("12:34:56", "%d:%02d:%02d", false),
      COMPACT_1("12:34", "%d:%02d", false, false),
      COMPACT_2("12:34:56.789", "%d:%02d:%02d.%03d"),
      COMPACT_3("12:34:56:789", "%d:%02d:%02d:%03d"),
      SPREAD_1("12 : 34 : 56", "%d : %02d : %02d", false),
      SPREAD_2("12 : 34", "%d : %02d", false, false),
      SPREAD_3("12 : 34 : 56 . 789", "%d : %02d : %02d . %03d"),
      SPREAD_4("12 : 34 : 56 : 789", "%d : %02d : %02d : %03d"),
      EASY_1("12h 34m", "%2dh %2dm", false, false),
      EASY_2("12h 34m 56s", "%2dh %2dm %02ds", false),
      EASY_3("12h 34m 56s 789ms", "%2dh %2dm %2ds %03dms"),
      STOPWATCH("123.456s", "%d.%03ds", true, true, false),
      MILLISECONDS("12345ms", "%dms", true, false, false),
      EASY_DYNAMIC_1("34m 56s", ThreadModuleDump11::formatVerbose),
      COMPACT_DYNAMIC("1m59s", ThreadModuleDump11::formatCompact);

      private final String display;
      private final Function<Long, String> formatter;

      Type(String var3, String var4) {
         this(var3, var4, true);
      }

      Type(String var3, String var4, boolean var5) {
         this(var3, var4, var5, true);
      }

      Type(String var3, String var4, boolean var5, boolean var6) {
         this(var3, var4, var5, var6, true);
      }

      Type(String var3, String var4, boolean var5, boolean var6, boolean flag) {
         this(var3, var4x -> ThreadModuleDump11.formatWithPattern(var4, var4x, flag, var6, var5));
      }

      Type(String var3, Function<Long, String> var4) {
         this.display = var3;
         this.formatter = var4;
      }

      public String format(long var1) {
         return this.formatter.apply(var1);
      }
   }

   public enum Type2 implements OptionEnumValue {
      DEFAULT(ThreadModuleDump11.Type.DEFAULT),
      COMPACT_1(ThreadModuleDump11.Type.COMPACT_1),
      COMPACT_2(ThreadModuleDump11.Type.COMPACT_2),
      COMPACT_3(ThreadModuleDump11.Type.COMPACT_3),
      SPREAD_1(ThreadModuleDump11.Type.SPREAD_1),
      SPREAD_2(ThreadModuleDump11.Type.SPREAD_2),
      SPREAD_3(ThreadModuleDump11.Type.SPREAD_3),
      SPREAD_4(ThreadModuleDump11.Type.SPREAD_4),
      EASY_1(ThreadModuleDump11.Type.EASY_1),
      EASY_2(ThreadModuleDump11.Type.EASY_2),
      EASY_3(ThreadModuleDump11.Type.EASY_3),
      STOPWATCH(ThreadModuleDump11.Type.STOPWATCH),
      MILLISECONDS(ThreadModuleDump11.Type.MILLISECONDS);

      private final ThreadModuleDump11.Type format;

      Type2(ThreadModuleDump11.Type var3) {
         this.format = var3;
      }

      @Override
      public String id() {
         return this.format.name();
      }

      @Override
      public String toString() {
         return this.format.display;
      }

      public String format(long var1) {
         return this.format.format(var1);
      }
   }
}
