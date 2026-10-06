package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.util.ThreadModuleDump83;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiHandler10 implements Gui_2 {
   private static final Pattern field1 = Pattern.compile("^(?<potion>[\\w ]+) (?<tier>[IVX]+) Potion$");
   private Matcher matcher;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.matcher = field1.matcher(var1);
      return this.matcher.matches();
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      String var2 = this.matcher.group("potion").replaceAll(" ", "_").toUpperCase();

      int var3;
      try {
         var3 = ThreadModuleDump83.parseRoman(this.matcher.group("tier"));
      } catch (Exception var5) {
         return Gui.method1();
      }

      GuiRewindhandlersHandler2.Data3 var4 = var1.method4("POTION;" + var2 + ";" + var3);
      return var4 != null ? Gui.method2((int)var4.method1()) : Gui.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
