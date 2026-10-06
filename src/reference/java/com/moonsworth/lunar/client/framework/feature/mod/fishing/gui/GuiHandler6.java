package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiHandler6 implements Gui_2 {
   private static final Pattern field1 = Pattern.compile("^(?<essenceType>\\w+) Essence$");
   private Matcher matcher;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.matcher = field1.matcher(var1);
      return this.matcher.matches();
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      String var2 = this.matcher.group("essenceType").replaceAll(" ", "_").toUpperCase();
      GuiRewindhandlersHandler2.Data2 var3 = var1.method5("ESSENCE_" + var2);
      return var3 != null ? Gui.method2((int)var3.method4().method2()) : Gui.method1();
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
