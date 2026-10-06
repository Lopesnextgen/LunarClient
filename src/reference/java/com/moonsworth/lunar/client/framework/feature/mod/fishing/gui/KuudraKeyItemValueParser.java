package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KuudraKeyItemValueParser implements ItemValueParser {
   private final Pattern field1 = Pattern.compile("^(?<tier>[A-Za-z]+ )?Kuudra Key$");
   private Matcher matcher;

   public KuudraKeyItemValueParser() {
   }

   @Override
   public boolean method1(String text1, GuiRewindhandlersHandler2 guirewindhandlershandler22) {
      this.matcher = this.field1.matcher(text1);
      return this.matcher.matches();
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 guirewindhandlershandler21) {
      String text2 = this.matcher.group("tier");
      text2 = text2 == null ? "Basic" : text2.trim();
      Skyblock skyblock3 = Ref.method4().method40().method82();
      Map map4 = skyblock3.method15().method27();
      if (map4 == null) {
         return ItemValueResponse.method1();
      }

      com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui3 gui35 = (com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui3)map4.get(
         text2
      );
      if (gui35 == null) {
         return ItemValueResponse.method1();
      }

      int number6 = gui35.method1();
      int number7 = this.method3(guirewindhandlershandler21, gui35.method4());
      int number8 = this.method3(guirewindhandlershandler21, gui35.method2());
      int number9 = this.method3(guirewindhandlershandler21, gui35.method3());
      if (number7 != -1 && number8 != -1 && number9 != -1) {
         number6 += number7;

         try {
            Member member10 = guirewindhandlershandler21.method7().method9();
            String text11 = member10.netherIslandPlayerData().selectedFaction().orElse("");
            if (text11.toLowerCase().contains("barb")) {
               return ItemValueResponse.method2(number6 + number8);
            }

            if (text11.toLowerCase().contains("mage")) {
               return ItemValueResponse.method2(number6 + number9);
            }
         } catch (Exception exception12) {
         }

         return ItemValueResponse.method2(number6 + Math.min(number9, number8));
      } else {
         return ItemValueResponse.method1();
      }
   }

   @Override
   public void cleanup() {
      this.matcher = null;
   }

   private int method3(GuiRewindhandlersHandler2 guirewindhandlershandler21, Map<String, Integer> map2) {
      int number3 = 0;

      for (Entry entry5 : map2.entrySet()) {
         GuiRewindhandlersHandler2.Data2 data26 = guirewindhandlershandler21.method5((String)entry5.getKey());
         if (data26 == null) {
            return -1;
         }

         number3 += (int)data26.method4().method6() * entry5.getValue();
      }

      return number3;
   }
}
