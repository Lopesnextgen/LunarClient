package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiHandler3 implements Gui_2 {
   private static final Pattern field1 = Pattern.compile("^(?<item>.+) (?<stars>✪{1,5})$");
   private Matcher matcher;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.matcher = field1.matcher(var1);
      return this.matcher.matches();
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      GuiHandler8 var2 = new GuiHandler8();
      String var3 = this.matcher.group("item");
      if (!var2.method1(var3, var1)) {
         var2.cleanup();
         return Gui.method1();
      } else {
         Gui var4 = var2.method2(var1);
         var2.cleanup();
         return var4;
      }
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }
}
