package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiHandler9 implements Gui_2 {
   private static final Pattern field1 = Pattern.compile("^Shiny (?<item>Necron's Handle|Wither [A-Za-z]+)$");
   private Matcher matcher;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.matcher = field1.matcher(var1);
      return this.matcher.matches();
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      String var2 = this.matcher.group("item");
      String var3 = Gui2.field5.get(var2);
      if (var3 == null) {
         return Gui.method1();
      }

      GuiRewindhandlersHandler2.Data3 var4 = var1.method4(var3);
      return var4 == null ? Gui.method1() : Gui.method2((int)var4.method1());
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
