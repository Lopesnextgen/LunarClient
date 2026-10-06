package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnchantedBookItemValueParser implements ItemValueParser {
   private static final Pattern field1 = Pattern.compile("^Enchanted Book \\((?<book>[\\w- ]+) (?<tier>[IVX]+)\\)$");
   private Matcher matcher;

   public EnchantedBookItemValueParser() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.matcher = field1.matcher(text);
      return this.matcher.matches();
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      String text2 = this.matcher.group("book");
      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui gui3 = com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui.method1(
         text2
      );
      String text4 = gui3 == null ? text2.replaceAll(" ", "_").toUpperCase() : gui3.name();

      int number5;
      try {
         number5 = RomanNumeralParser.romanToInt(this.matcher.group("tier"));
      } catch (Exception exception7) {
         return ItemValueResponse.method1();
      }

      GuiRewindhandlersHandler2.Data2 data26 = handler.method5("ENCHANTMENT_" + text4 + "_" + number5);
      return data26 != null ? ItemValueResponse.method2((int)data26.method4().method2()) : ItemValueResponse.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
