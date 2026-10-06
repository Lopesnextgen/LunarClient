package com.moonsworth.lunar.client.util.text;

import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import com.moonsworth.lunar.client.framework.Ref;

public class TimeFormatting {
   public TimeFormatting() {
   }

   public static String method1(long number0) {
      if (number0 < 0L) {
         return "now";
      }

      String text2 = DurationFormatUtils.formatDuration(number0, "d'd 'H'h 'm'm 's's'");
      text2 = " " + text2;
      String text3 = StringUtils.replaceOnce(text2, " 0d", "");
      if (text3.length() != text2.length()) {
         text2 = text3;
         text3 = StringUtils.replaceOnce(text2, " 0h", "");
         if (text3.length() != text2.length()) {
            text2 = text3;
            text3 = StringUtils.replaceOnce(text2, " 0m", "");
            if (text3.length() != text2.length()) {
               text2 = StringUtils.replaceOnce(text3, " 0s", "");
            }
         }
      }

      String text4 = text2.trim();
      return !text4.isEmpty() ? text4 : "0s";
   }

   public static String method2(long number0) {
      int number2 = (int)(number0 / 1000L);
      int number3 = number2 / 60;
      number2 %= 60;
      int number4 = number3 / 60;
      number3 %= 60;
      int number5 = number4 / 24;
      number4 %= 24;
      StringBuilder builder6 = new StringBuilder();
      if (number5 > 0) {
         builder6.append(String.format("%02d", number5)).append(":");
      }

      if (number4 > 0) {
         builder6.append(String.format("%02d", number4)).append(":");
      }

      builder6.append(String.format("%02d", number3)).append(":").append(String.format("%02d", number2));
      return builder6.toString();
   }

   public static String method3(long number0) {
      long number2 = 1000L;
      long number4 = 60000L;
      long number6 = 3600000L;
      long number8 = 86400000L;
      long number10 = 604800000L;
      long number12 = 2419200000L;
      long number14 = 29030400000L;
      long number16 = System.currentTimeMillis();
      long number18 = number16 - number0;
      TranslationManager foghandler2820 = Ref.method4().method67();
      if (number18 < 0L) {
         return foghandler2820.method2("time.ago", "future", new Object[0]);
      } else if (number18 < 1000L) {
         return foghandler2820.method2("time.ago", "now", new Object[0]);
      } else if (number18 < 60000L) {
         long number28 = number18 / 1000L;
         return number28 == 1L ? foghandler2820.method2("time.ago", "second", new Object[]{number28}) : foghandler2820.method2("time.ago", "seconds", new Object[]{number28});
      } else if (number18 < 3600000L) {
         long number27 = number18 / 60000L;
         return number27 == 1L ? foghandler2820.method2("time.ago", "minute", new Object[]{number27}) : foghandler2820.method2("time.ago", "minutes", new Object[]{number27});
      } else if (number18 < 86400000L) {
         long number26 = number18 / 3600000L;
         return number26 == 1L ? foghandler2820.method2("time.ago", "hour", new Object[]{number26}) : foghandler2820.method2("time.ago", "hours", new Object[]{number26});
      } else if (number18 < 604800000L) {
         long number25 = number18 / 86400000L;
         return number25 == 1L ? foghandler2820.method2("time.ago", "day", new Object[]{number25}) : foghandler2820.method2("time.ago", "days", new Object[]{number25});
      } else if (number18 < 2419200000L) {
         long number24 = number18 / 604800000L;
         return number24 == 1L ? foghandler2820.method2("time.ago", "week", new Object[]{number24}) : foghandler2820.method2("time.ago", "weeks", new Object[]{number24});
      } else if (number18 < 29030400000L) {
         long number23 = number18 / 2419200000L;
         return number23 == 1L ? foghandler2820.method2("time.ago", "month", new Object[]{number23}) : foghandler2820.method2("time.ago", "months", new Object[]{number23});
      } else {
         long number21 = number18 / 29030400000L;
         return number21 == 1L ? foghandler2820.method2("time.ago", "year", new Object[]{number21}) : foghandler2820.method2("time.ago", "years", new Object[]{number21});
      }
   }

   public static String method4(long number0) {
      Date date2 = new Date(number0);
      SimpleDateFormat simpledateformat3;
      if (Calendar.getInstance().get(1) == date2.getYear() + 1900) {
         simpledateformat3 = new SimpleDateFormat("d MMM HH:mm:ss");
      } else {
         simpledateformat3 = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
      }

      return simpledateformat3.format(date2);
   }

   public static String method5(long number0) {
      float value2 = Math.round((float)number0 / 10.0F) / 100.0F;
      return String.format("%.2f", value2);
   }

   private static String method6(String text, long number1, boolean flag, boolean flag2, boolean flag5) {
      if (flag) {
         long number12 = number1 / 3600000L % 24L;
         long number8 = number1 / 60000L % 60L;
         if (flag2) {
            long number10 = number1 / 1000L % 60L;
            return flag5 ? String.format(text, number12, number8, number10, number1 % 1000L) : String.format(text, number12, number8, number10);
         } else {
            return String.format(text, number12, number8);
         }
      } else if (flag2) {
         long number6 = number1 / 1000L;
         return flag5 ? String.format(text, number6, number1 % 1000L) : String.format(text, number6);
      } else {
         return String.format(text, number1);
      }
   }

   private static String method7(long number0) {
      double value2 = number0 / 1000.0;
      if (value2 < 60.0) {
         double value14 = Math.round(value2 * 10.0) / 10.0;
         return value14 + "s";
      } else {
         long number4 = number0 / 1000L;
         long number6 = number4 % 60L;
         long number8 = number4 / 60L % 60L;
         long number10 = number4 / 3600L % 24L;
         long number12 = number4 / 86400L;
         if (number12 > 0L) {
            return number12 + "d" + number10 + "h";
         } else {
            return number10 > 0L ? number10 + "h" + number8 + "m" : number8 + "m" + number6 + "s";
         }
      }
   }

   private static String method8(long number0) {
      long number2 = number0 / 86400000L;
      long number4 = number0 / 3600000L % 24L;
      long number6 = number0 / 60000L % 60L;
      long number8 = number0 / 1000L % 60L;
      StringBuilder builder10 = new StringBuilder();
      boolean flag11 = false;
      if (number2 > 0L || flag11) {
         if (flag11) {
            builder10.append(" ");
         }

         flag11 = true;
         builder10.append(number2).append("d");
      }

      if (number4 > 0L || flag11) {
         if (flag11) {
            builder10.append(" ");
         }

         flag11 = true;
         builder10.append(number4).append("h");
      }

      if (number6 > 0L || flag11) {
         if (flag11) {
            builder10.append(" ");
         }

         flag11 = true;
         builder10.append(number6).append("m");
      }

      if (flag11) {
         builder10.append(" ");
      }

      flag11 = true;
      builder10.append(number8).append("s");
      return builder10.toString();
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
      EASY_DYNAMIC_1("34m 56s", TimeFormatting::method8),
      COMPACT_DYNAMIC("1m59s", TimeFormatting::method7);

      private final String display;
      private final Function<Long, String> formatter;

      Type(String text3, String text4) {
         this(text3, text4, true);
      }

      Type(String text3, String text4, boolean flag5) {
         this(text3, text4, flag5, true);
      }

      Type(String text3, String text4, boolean flag5, boolean flag6) {
         this(text3, text4, flag5, flag6, true);
      }

      Type(String text3, String text4, boolean flag5, boolean flag6, boolean flag) {
         this(text3, arg4x -> TimeFormatting.method6(text4, arg4x, flag, flag6, flag5));
      }

      Type(String text3, Function<Long, String> function4) {
         this.display = text3;
         this.formatter = function4;
      }

      public String format(long number1) {
         return this.formatter.apply(number1);
      }
   }

   public enum TimeFormat implements OptionEnumValue {
      DEFAULT(TimeFormatting.Type.DEFAULT),
      COMPACT_1(TimeFormatting.Type.COMPACT_1),
      COMPACT_2(TimeFormatting.Type.COMPACT_2),
      COMPACT_3(TimeFormatting.Type.COMPACT_3),
      SPREAD_1(TimeFormatting.Type.SPREAD_1),
      SPREAD_2(TimeFormatting.Type.SPREAD_2),
      SPREAD_3(TimeFormatting.Type.SPREAD_3),
      SPREAD_4(TimeFormatting.Type.SPREAD_4),
      EASY_1(TimeFormatting.Type.EASY_1),
      EASY_2(TimeFormatting.Type.EASY_2),
      EASY_3(TimeFormatting.Type.EASY_3),
      STOPWATCH(TimeFormatting.Type.STOPWATCH),
      MILLISECONDS(TimeFormatting.Type.MILLISECONDS);

      private final TimeFormatting.Type format;

      TimeFormat(TimeFormatting.Type type) {
         this.format = type;
      }

      public String id() {
         return this.format.name();
      }

      @Override
      public String toString() {
         return this.format.display;
      }

      public String format(long number1) {
         return this.format.format(number1);
      }
   }
}
