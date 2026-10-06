package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DyeItemValueParser implements ItemValueParser {
   private static final Pattern field1 = Pattern.compile("^(?<dyeName>\\w+) Dye$");
   private Matcher matcher;

   public DyeItemValueParser() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.matcher = field1.matcher(text);
      return this.matcher.matches();
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      String text2 = this.matcher.group("dyeName").replaceAll(" ", "_").toUpperCase();
      GuiRewindhandlersHandler2.Data3 data33 = handler.method4("DYE_" + text2);
      return data33 != null ? ItemValueResponse.method2((int)data33.method2()) : ItemValueResponse.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
