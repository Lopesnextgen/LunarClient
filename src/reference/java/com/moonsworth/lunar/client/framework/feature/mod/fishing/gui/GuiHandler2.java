package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.util.ThreadModuleDump83;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiHandler2 implements Gui_2 {
   private static final Pattern field1 = Pattern.compile("^Enchanted Book \\((?<book>[\\w- ]+) (?<tier>[IVX]+)\\)$");
   private Matcher matcher;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.matcher = field1.matcher(var1);
      return this.matcher.matches();
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      String var2 = this.matcher.group("book");
      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui var3 = com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui.method1(
         var2
      );
      String var4 = var3 == null ? var2.replaceAll(" ", "_").toUpperCase() : var3.name();

      int var5;
      try {
         var5 = ThreadModuleDump83.parseRoman(this.matcher.group("tier"));
      } catch (Exception var7) {
         return Gui.method1();
      }

      GuiRewindhandlersHandler2.Data2 var6 = var1.method5("ENCHANTMENT_" + var4 + "_" + var5);
      return var6 != null ? Gui.method2((int)var6.method4().method2()) : Gui.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
