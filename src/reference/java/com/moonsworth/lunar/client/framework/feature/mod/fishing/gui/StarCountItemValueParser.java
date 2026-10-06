package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarCountItemValueParser implements ItemValueParser {
   private static final Pattern field1 = Pattern.compile("^(?<item>.+) (?<stars>✪{1,5})$");
   private Matcher matcher;

   public StarCountItemValueParser() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.matcher = field1.matcher(text);
      return this.matcher.matches();
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      NamedItemValueParser guihandler82 = new NamedItemValueParser();
      String text3 = this.matcher.group("item");
      if (!guihandler82.method1(text3, handler)) {
         guihandler82.cleanup();
         return ItemValueResponse.method1();
      } else {
         ItemValueResponse gui4 = guihandler82.method2(handler);
         guihandler82.cleanup();
         return gui4;
      }
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
