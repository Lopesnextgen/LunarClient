package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public class Fishing3Impl2 extends Fishing3_3 {
   private static final DateTimeFormatter field1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(Locale.US);

   @Override
   public String getCommand() {
      return "time";
   }

   @Override
   public List<String> getAliases() {
      return List.of("date");
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String var1, String var2, String[] var3, Gui2Extension var4) {
      String var5 = field1.format(ZonedDateTime.now()).replace(' ', ' ');
      this.method6(var4, var5);
   }
}
