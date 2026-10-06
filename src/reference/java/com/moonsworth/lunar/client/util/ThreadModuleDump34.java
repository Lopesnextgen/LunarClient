package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.framework.Client;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class ThreadModuleDump34 {
   public static final DateTimeFormatter field1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault());
   public static final DateTimeFormatter field2 = DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.getDefault());

   public static String method1(LocalDate var0, ZoneId var1) {
      LocalDate var2 = LocalDate.now(var1);
      long var3 = ChronoUnit.DAYS.between(var2, var0);
      if (var3 == 0L) {
         return method7("today");
      } else if (var3 == 1L) {
         return method7("tomorrow");
      } else if (var3 == -1L) {
         return method7("yesterday");
      } else {
         return var3 > 0L ? method7("inDays", var3) : method7("daysAgo", Math.abs(var3));
      }
   }

   public static String method2(Instant var0, ZoneId var1) {
      return method1(var0.atZone(var1).toLocalDate(), var1);
   }

   public static String method3(long var0, ZoneId var2) {
      return method2(Instant.ofEpochMilli(var0), var2);
   }

   public static String method4(long var0) {
      return method3(var0, ZoneId.systemDefault());
   }

   public static String method5(Instant var0) {
      return method2(var0, ZoneId.systemDefault());
   }

   public static String method6(LocalDate var0) {
      return method1(var0, ZoneId.systemDefault());
   }

   private static String method7(String var0, Object... var1) {
      return Client.method109().method67().method2("date", var0, var1);
   }
}
