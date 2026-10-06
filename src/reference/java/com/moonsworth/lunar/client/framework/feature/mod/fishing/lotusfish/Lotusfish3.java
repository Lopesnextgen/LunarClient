package com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;

public class Lotusfish3 {
   private final List<Component> field1;
   private final List<String> field2;
   private static final Pattern field3 = Pattern.compile("^ [^ ].+");

   public Lotusfish3(List<Component> var1, List<String> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public static Lotusfish3 method1(GuiRewindhandlersHandler24 handler, int var1) {
      ImmutableList var2 = handler.method5();
      ImmutableList var3 = handler.method6();
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      var4.add((Component)var2.get(var1));
      var5.add((String)var3.get(var1));

      for (int var6 = var1 + 1; var6 < var3.size() && var6 < var2.size(); var6++) {
         Component var7 = (Component)var2.get(var6);
         String var8 = (String)var3.get(var6);
         if (!var8.equals("               Info")) {
            if (!field3.matcher(var8).matches()) {
               break;
            }

            var4.add(var7);
            var5.add(var8);
         }
      }

      return new Lotusfish3(var4, var5);
   }

   public List<Component> method2() {
      return this.field1;
   }

   public List<String> method3() {
      return this.field2;
   }
}
