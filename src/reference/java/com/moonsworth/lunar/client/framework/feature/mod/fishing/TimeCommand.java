package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public class TimeCommand extends SkyBlockChatCommand {
   private static final DateTimeFormatter field1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(Locale.US);

   public TimeCommand() {
   }

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
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      String text5 = field1.format(ZonedDateTime.now()).replace(' ', ' ');
      this.method6(gui2extension4, text5);
   }
}
