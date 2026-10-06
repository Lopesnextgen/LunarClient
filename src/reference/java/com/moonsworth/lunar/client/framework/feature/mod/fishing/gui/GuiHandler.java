package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiHandler implements ItemValueParser {
   private static final Pattern field1 = Pattern.compile("^(?:◆ )?(?<type>.*) Rune (?<tier>I{1,3})$");
   private Matcher matcher;

   public GuiHandler() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.matcher = field1.matcher(text);
      return this.matcher.matches();
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      String text2 = this.matcher.group("type").replace(' ', '_').toUpperCase(Locale.ROOT);
      int number3 = RomanNumeralParser.romanToInt(this.matcher.group("tier"));
      String text4 = "RUNE;" + text2 + ";" + number3;
      GuiRewindhandlersHandler2.Data3 data35 = handler.method4(text4);
      if (data35 != null) {
         return ItemValueResponse.method2((int)data35.method1());
      }

      data35 = handler.method4("UNIQUE_" + text4);
      return data35 != null ? ItemValueResponse.method2((int)data35.method1()) : ItemValueResponse.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
