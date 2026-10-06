package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiHandler4 implements Gui_2 {
   private static final Pattern field1 = Pattern.compile("^(?<dyeName>\\w+) Dye$");
   private Matcher matcher;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.matcher = field1.matcher(var1);
      return this.matcher.matches();
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      String var2 = this.matcher.group("dyeName").replaceAll(" ", "_").toUpperCase();
      GuiRewindhandlersHandler2.Data3 var3 = var1.method4("DYE_" + var2);
      return var3 != null ? Gui.method2((int)var3.method2()) : Gui.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
