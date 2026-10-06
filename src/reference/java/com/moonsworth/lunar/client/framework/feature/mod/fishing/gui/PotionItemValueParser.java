package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PotionItemValueParser implements ItemValueParser {
   private static final Pattern field1 = Pattern.compile("^(?<potion>[\\w ]+) (?<tier>[IVX]+) Potion$");
   private Matcher matcher;

   public PotionItemValueParser() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.matcher = field1.matcher(text);
      return this.matcher.matches();
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      String text2 = this.matcher.group("potion").replaceAll(" ", "_").toUpperCase();

      int number3;
      try {
         number3 = RomanNumeralParser.romanToInt(this.matcher.group("tier"));
      } catch (Exception exception5) {
         return ItemValueResponse.method1();
      }

      GuiRewindhandlersHandler2.Data3 data34 = handler.method4("POTION;" + text2 + ";" + number3);
      return data34 != null ? ItemValueResponse.method2((int)data34.method1()) : ItemValueResponse.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
