package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiIterator implements Gui_2 {
   private final Pattern field1 = Pattern.compile("^(?<tier>[A-Za-z]+ )?Kuudra Key$");
   private Matcher matcher;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.matcher = this.field1.matcher(var1);
      return this.matcher.matches();
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      String var2 = this.matcher.group("tier");
      var2 = var2 == null ? "Basic" : var2.trim();
      Skyblock var3 = ThreadModuleDump63.method4().method40().method82();
      Map var4 = var3.method15().method27();
      if (var4 == null) {
         return Gui.method1();
      }

      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui3 var5 = (com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui3)var4.get(
         var2
      );
      if (var5 == null) {
         return Gui.method1();
      }

      int var6 = var5.method1();
      int var7 = this.method3(var1, var5.method4());
      int var8 = this.method3(var1, var5.method2());
      int var9 = this.method3(var1, var5.method3());
      if (var7 != -1 && var8 != -1 && var9 != -1) {
         var6 += var7;

         try {
            Member var10 = var1.method7().method9();
            String var11 = var10.netherIslandPlayerData().selectedFaction().orElse("");
            if (var11.toLowerCase().contains("barb")) {
               return Gui.method2(var6 + var8);
            }

            if (var11.toLowerCase().contains("mage")) {
               return Gui.method2(var6 + var9);
            }
         } catch (Exception var12) {
         }

         return Gui.method2(var6 + Math.min(var9, var8));
      } else {
         return Gui.method1();
      }
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }

   private int method3(GuiRewindhandlersHandler2 var1, Map<String, Integer> var2) {
      int var3 = 0;

      for (Entry var5 : var2.entrySet()) {
         GuiRewindhandlersHandler2.Data2 var6 = var1.method5((String)var5.getKey());
         if (var6 == null) {
            return -1;
         }

         var3 += (int)var6.method4().method6() * var5.getValue();
      }

      return var3;
   }
}
