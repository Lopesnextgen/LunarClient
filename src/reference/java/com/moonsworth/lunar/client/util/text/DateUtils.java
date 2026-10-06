package com.moonsworth.lunar.client.util.text;

import com.moonsworth.lunar.client.framework.Client;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateUtils {
   public static final DateTimeFormatter field1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault());
   public static final DateTimeFormatter field2 = DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.getDefault());

   public DateUtils() {
   }

   public static String method1(LocalDate localdate0, ZoneId zoneid1) {
      LocalDate localdate2 = LocalDate.now(zoneid1);
      long number3 = ChronoUnit.DAYS.between(localdate2, localdate0);
      if (number3 == 0L) {
         return method7("today");
      } else if (number3 == 1L) {
         return method7("tomorrow");
      } else if (number3 == -1L) {
         return method7("yesterday");
      } else {
         return number3 > 0L ? method7("inDays", number3) : method7("daysAgo", Math.abs(number3));
      }
   }

   public static String method2(Instant instant0, ZoneId zoneid1) {
      return method1(instant0.atZone(zoneid1).toLocalDate(), zoneid1);
   }

   public static String method3(long number0, ZoneId zoneid2) {
      return method2(Instant.ofEpochMilli(number0), zoneid2);
   }

   public static String method4(long number0) {
      return method3(number0, ZoneId.systemDefault());
   }

   public static String method5(Instant instant0) {
      return method2(instant0, ZoneId.systemDefault());
   }

   public static String method6(LocalDate localdate0) {
      return method1(localdate0, ZoneId.systemDefault());
   }

   private static String method7(String text, Object... items1) {
      return Client.method109().method67().method2("date", text, items1);
   }
}
