package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.util.math.NumberUtils;
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

public class SackCountListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ {2}(?<amount>[+-][\\d,]+) (?<item>[\\w ]+) \\((?<sack>[\\w ]+ Sack)\\)$");
   private static final Pattern field8 = Pattern.compile("^\\[Sacks] (?<amount>[+-][0-9,]+) items?\\. \\(Last \\d+s\\.\\)$");
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache field9 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache.class
   );
   private final Object2IntOpenHashMap<String> field10 = new Object2IntOpenHashMap();

   public SackCountListener() {
      this.handle(SkyblockProfileEvents.SkyblockProfileChangeEvent.class, this::method2);
      this.handle(SkyblockProfileEvents.SkyblockProfileLoadEvent.class, this::method3);
      this.handle(TypedChatMessage.class, this::method4);
   }

   public int method1(String text1) {
      return this.field10.getOrDefault(text1, 0);
   }

   private void method2(SkyblockProfileEvents.SkyblockProfileChangeEvent data151) {
      this.field10.clear();
   }

   private void method3(SkyblockProfileEvents.SkyblockProfileLoadEvent data141) {
      this.method5();
   }

   public void onEnable() {
      this.method5();
   }

   private void method4(TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if (field8.matcher(text2).matches()) {
         Component component3 = data1.OHCICHOROROOORHCRICORHRRCRCCHO();

         for (Component component5 : component3.children()) {
            HoverEvent hoverevent6 = component5.hoverEvent();
            if (hoverevent6 != null && hoverevent6.action() == Action.SHOW_TEXT && hoverevent6.value() instanceof Component component7) {
               String text17 = TextBridge.getTextContent(component7);

               for (String text12 : text17.split("\n")) {
                  Matcher matcher13 = field7.matcher(text12);
                  if (matcher13.matches()) {
                     int number14 = NumberUtils.method3(matcher13.group("amount").replace("+", "").replace(",", ""));
                     String text15 = SkyblockItemRegistry.field5.get(matcher13.group("item"));
                     if (text15 != null) {
                        int number16 = Math.max(this.field10.getOrDefault(text15, 0) + number14, 0);
                        this.field10.put(text15, number16);
                     }
                  }
               }

               return;
            }
         }
      }
   }

   private void method5() {
      Member member1 = this.field9.method9();
      if (member1 != null) {
         Map map2 = member1.inventory().sacksCounts().entrySet().stream().collect(Collectors.toMap(Entry::getKey, arg0 -> ((Double)arg0.getValue()).intValue()));
         this.field10.putAll(map2);
      }
   }

   @Generated
   public Object2IntOpenHashMap<String> method6() {
      return this.field10;
   }
}
