package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEvent.Action;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ {2}(?<amount>[+-][\\d,]+) (?<item>[\\w ]+) \\((?<sack>[\\w ]+ Sack)\\)$");
   private static final Pattern field8 = Pattern.compile("^\\[Sacks] (?<amount>[+-][0-9,]+) items?\\. \\(Last \\d+s\\.\\)$");
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22 field9 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22.class
   );
   private final Object2IntOpenHashMap<String> field10 = new Object2IntOpenHashMap();

   public GuiRewindhandlersHandler2() {
      this.handle(Rewindhandlers.Data15.class, this::method2);
      this.handle(Rewindhandlers.Data14.class, this::method3);
      this.handle(Data.class, this::method4);
   }

   public int method1(String var1) {
      return this.field10.getOrDefault(var1, 0);
   }

   private void method2(Rewindhandlers.Data15 var1) {
      this.field10.clear();
   }

   private void method3(Rewindhandlers.Data14 var1) {
      this.method5();
   }

   public void onEnable() {
      this.method5();
   }

   private void method4(Data var1) {
      String var2 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if (field8.matcher(var2).matches()) {
         Component var3 = var1.OHCICHOROROOORHCRICORHRRCRCCHO();

         for (Component var5 : var3.children()) {
            HoverEvent var6 = var5.hoverEvent();
            if (var6 != null && var6.action() == Action.SHOW_TEXT && var6.value() instanceof Component var7) {
               String var17 = AdventureTextBridge.getTextContent(var7);

               for (String var12 : var17.split("\n")) {
                  Matcher var13 = field7.matcher(var12);
                  if (var13.matches()) {
                     int var14 = ThreadModuleDump40.method3(var13.group("amount").replace("+", "").replace(",", ""));
                     String var15 = Gui2.field5.get(var13.group("item"));
                     if (var15 != null) {
                        int var16 = Math.max(this.field10.getOrDefault(var15, 0) + var14, 0);
                        this.field10.put(var15, var16);
                     }
                  }
               }

               return;
            }
         }
      }
   }

   private void method5() {
      Member var1 = this.field9.method9();
      if (var1 != null) {
         Map var2 = var1.inventory().sacksCounts().entrySet().stream().collect(Collectors.toMap(Entry::getKey, var0 -> ((Double)var0.getValue()).intValue()));
         this.field10.putAll(var2);
      }
   }

   @Generated
   public Object2IntOpenHashMap<String> method6() {
      return this.field10;
   }
}
