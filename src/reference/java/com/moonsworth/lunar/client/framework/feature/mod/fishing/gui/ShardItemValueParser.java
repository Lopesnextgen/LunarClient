package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShardItemValueParser implements ItemValueParser {
   private static final Pattern field1 = Pattern.compile("^(?<shard>.+) Shard$");
   private Matcher matcher;

   public ShardItemValueParser() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.matcher = field1.matcher(text);
      return this.matcher.matches();
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      String text2 = this.matcher.group("shard").replaceAll(" ", "_").toUpperCase();
      GuiRewindhandlersHandler2.Data2 data23 = handler.method5("SHARD_" + text2);
      return data23 != null ? ItemValueResponse.method3().method1(true).method2((int)data23.method4().method2()).method4() : ItemValueResponse.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
