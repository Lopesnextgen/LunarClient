package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Locale;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MixinHelper108 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      Holograms var3 = var2.method18().orElse(null);
      if (var3 == null) {
         TextComponent var11 = AdventureTextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
         ThreadModuleDump63.method7().method1(var11);
      } else {
         Holograms7 var4 = var2.method25().orElse(null);
         if (var4 == null) {
            TextComponent var13 = AdventureTextBridge.asAdventure(
               MixinHelper.method1("failedNoRouteActive", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
            );
            ThreadModuleDump63.method7().method1(var13);
         } else {
            if (var1.length == 2) {
               switch (var1[1].toLowerCase(Locale.ROOT)) {
                  case "prev":
                     if (var4.getIndex() == 0) {
                        TextComponent var16 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToGoToPrevSection", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                        );
                        ThreadModuleDump63.method7().method1(var16);
                     } else {
                        int var17 = var4.getIndex();
                        Holograms7 var22 = var4.method7().getSections().get(var17 - 1);
                        var2.method26(var22);
                        var4 = var2.method25().orElse(null);
                        TextComponent var24 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("successfullySwappedToPrevSection", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN)
                        );
                        ThreadModuleDump63.method7().method1(var24);
                     }
                     break;
                  case "next":
                     if (var4.getIndex() == var4.method7().getSections().size() - 1) {
                        TextComponent var14 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToGoToNextSection", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                        );
                        ThreadModuleDump63.method7().method1(var14);
                     } else {
                        int var15 = var4.getIndex();
                        Holograms7 var21 = var4.method7().getSections().get(var15 + 1);
                        var2.method26(var21);
                        var4 = var2.method25().orElse(null);
                        TextComponent var23 = AdventureTextBridge.asAdventure(MixinHelper.method1("swappedToNextSection", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
                        ThreadModuleDump63.method7().method1(var23);
                     }
                     break;
                  default:
                     try {
                        int var7 = Integer.parseInt(var1[1]);
                        if (var7 >= var4.method7().getSections().size()) {
                           TextComponent var18 = AdventureTextBridge.asAdventure(
                              MixinHelper.method1("failedToGoToSectionIndexToBig", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                           );
                           ThreadModuleDump63.method7().method1(var18);
                        } else if (var7 < 0) {
                           TextComponent var19 = AdventureTextBridge.asAdventure(
                              MixinHelper.method1("failedToGoToSectionIndexToSmall", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                           );
                           ThreadModuleDump63.method7().method1(var19);
                        } else {
                           Holograms7 var20 = var4.method7().getSections().get(var7 + 1);
                           var2.method26(var20);
                           var4 = var2.method25().orElse(null);
                           TextComponent var9 = AdventureTextBridge.asAdventure(MixinHelper.method1("swappedToSection", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
                           ThreadModuleDump63.method7().method1(var9);
                        }
                     } catch (NumberFormatException var10) {
                        TextComponent var8 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedGoToSectionInvalidNumber", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                        );
                        ThreadModuleDump63.method7().method1(var8);
                     }
               }
            }

            Component var12 = ((TextComponent)((TextComponent)((TextComponent)((TextComponent)AdventureTextBridge.asAdventure(
                              MixinHelper.method1("currentRouteSection", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN)
                           )
                           .append(MixinHelper.method2(var4.method7(), var3.getBlcID(), NamedTextColor.GRAY)))
                        .append(AdventureTextBridge.asAdventure(AdventureChatFormatting.GRAY + ":" + var4.getIndex() + " ")))
                     .append(
                        ((TextComponent)Component.text(MixinHelper.method1("prev"), NamedTextColor.GREEN)
                              .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToGoToPrevSection")))))
                           .clickEvent(ClickEvent.runCommand("/route section prev"))
                     ))
                  .appendSpace())
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("next"), NamedTextColor.GREEN)
                        .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToGoToNextSection")))))
                     .clickEvent(ClickEvent.runCommand("/route section next"))
               );
            ThreadModuleDump63.method7().method1(var12);
         }
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return var1.length == 2 ? Stream.of("prev", "next") : Stream.empty();
   }
}
