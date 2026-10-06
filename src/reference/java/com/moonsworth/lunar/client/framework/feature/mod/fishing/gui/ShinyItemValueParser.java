package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShinyItemValueParser implements ItemValueParser {
   private static final Pattern field1 = Pattern.compile("^Shiny (?<item>Necron's Handle|Wither [A-Za-z]+)$");
   private Matcher matcher;

   public ShinyItemValueParser() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.matcher = field1.matcher(text);
      return this.matcher.matches();
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      String text2 = this.matcher.group("item");
      String text3 = SkyblockItemRegistry.field5.get(text2);
      if (text3 == null) {
         return ItemValueResponse.method1();
      }

      GuiRewindhandlersHandler2.Data3 data34 = handler.method4(text3);
      return data34 == null ? ItemValueResponse.method1() : ItemValueResponse.method2((int)data34.method1());
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
