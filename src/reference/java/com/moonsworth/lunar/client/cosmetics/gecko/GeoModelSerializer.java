package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper27;
import com.moonsworth.lunar.MixinHelper28;
import com.moonsworth.lunar.MixinHelper312;
import com.moonsworth.lunar.MixinHelper3122;
import com.moonsworth.lunar.MixinHelper314;
import com.moonsworth.lunar.MixinHelper44;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class GeoModelSerializer {
   private static final DateTimeFormatter field1 = new DateTimeFormatterBuilder()
      .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
      .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
      .appendOptional(DateTimeFormatter.ISO_INSTANT)
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      .toFormatter()
      .withZone(ZoneOffset.UTC);
   private static final DateTimeFormatter field2 = new DateTimeFormatterBuilder()
      .appendOptional(DateTimeFormatter.ISO_TIME)
      .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
      .parseDefaulting(ChronoField.YEAR, 2020L)
      .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1L)
      .parseDefaulting(ChronoField.DAY_OF_MONTH, 1L)
      .toFormatter()
      .withZone(ZoneOffset.UTC);
   private static MixinHelper28 field3;
   private static MixinHelper314 field4;

   public GeoModelSerializer() {
   }

   public static OffsetDateTime method1(String text0) {
      return ZonedDateTime.from(field1.parse(text0)).toOffsetDateTime();
   }

   public static OffsetTime method2(String text0) {
      return ZonedDateTime.from(field2.parse(text0)).toOffsetDateTime().toOffsetTime();
   }

   public static BedrockGeometryFile method3(String text0) {
      return (BedrockGeometryFile)method6().method98(text0);
   }

   public static String method4(BedrockGeometryFile rewindhandlers3_20) {
      return method7().method83(rewindhandlers3_20);
   }

   private static void method5() {
      MixinHelper27 mixinhelper270 = new MixinHelper27();
      mixinhelper270.method10(new MixinHelper3122());
      mixinhelper270.method117(MixinHelper44.WRITE_DATES_AS_TIMESTAMPS, false);
      MixinHelper312 mixinhelper3121 = new MixinHelper312();
      mixinhelper3121.method13(OffsetDateTime.class, new MixinHelper102_4<OffsetDateTime>() {
         public OffsetDateTime method1(MixinHelper53 mixinhelper531, MixinHelper73_3 mixinhelper73_32) {
            String text3 = mixinhelper531.getText();
            return GeoModelSerializer.method1(text3);
         }
      });
      mixinhelper270.method10(mixinhelper3121);
      field3 = mixinhelper270.method223(BedrockGeometryFile.class);
      field4 = mixinhelper270.method205(BedrockGeometryFile.class);
   }

   private static MixinHelper28 method6() {
      if (field3 == null) {
         method5();
      }

      return field3;
   }

   private static MixinHelper314 method7() {
      if (field4 == null) {
         method5();
      }

      return field4;
   }
}
