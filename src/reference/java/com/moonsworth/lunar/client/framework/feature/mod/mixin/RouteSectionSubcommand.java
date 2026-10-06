package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Locale;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class RouteSectionSubcommand implements RouteSubcommand {
   public RouteSectionSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
      if (holograms3 == null) {
         TextComponent text11 = TextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", ChatFormatting.AQUA, ChatFormatting.RED));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text11);
      } else {
         RouteSection holograms74 = holograms3_32.method25().orElse(null);
         if (holograms74 == null) {
            TextComponent text13 = TextBridge.asAdventure(
               MixinHelper.method1("failedNoRouteActive", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text13);
         } else {
            if (items1.length == 2) {
               switch (items1[1].toLowerCase(Locale.ROOT)) {
                  case "prev":
                     if (holograms74.getIndex() == 0) {
                        TextComponent text16 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToGoToPrevSection", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text16);
                     } else {
                        int index17 = holograms74.getIndex();
                        RouteSection holograms722 = holograms74.method7().getSections().get(index17 - 1);
                        holograms3_32.method26(holograms722);
                        holograms74 = holograms3_32.method25().orElse(null);
                        TextComponent text24 = TextBridge.asAdventure(
                           MixinHelper.method1("successfullySwappedToPrevSection", ChatFormatting.AQUA, ChatFormatting.GREEN)
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text24);
                     }
                     break;
                  case "next":
                     if (holograms74.getIndex() == holograms74.method7().getSections().size() - 1) {
                        TextComponent text14 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToGoToNextSection", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text14);
                     } else {
                        int index15 = holograms74.getIndex();
                        RouteSection holograms721 = holograms74.method7().getSections().get(index15 + 1);
                        holograms3_32.method26(holograms721);
                        holograms74 = holograms3_32.method25().orElse(null);
                        TextComponent text23 = TextBridge.asAdventure(MixinHelper.method1("swappedToNextSection", ChatFormatting.AQUA, ChatFormatting.GREEN));
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text23);
                     }
                     break;
                  default:
                     try {
                        int index7 = Integer.parseInt(items1[1]);
                        if (index7 >= holograms74.method7().getSections().size()) {
                           TextComponent text18 = TextBridge.asAdventure(
                              MixinHelper.method1("failedToGoToSectionIndexToBig", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                           );
                           Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text18);
                        } else if (index7 < 0) {
                           TextComponent text19 = TextBridge.asAdventure(
                              MixinHelper.method1("failedToGoToSectionIndexToSmall", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                           );
                           Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text19);
                        } else {
                           RouteSection holograms720 = holograms74.method7().getSections().get(index7 + 1);
                           holograms3_32.method26(holograms720);
                           holograms74 = holograms3_32.method25().orElse(null);
                           TextComponent text9 = TextBridge.asAdventure(MixinHelper.method1("swappedToSection", ChatFormatting.AQUA, ChatFormatting.GREEN));
                           Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text9);
                        }
                     } catch (NumberFormatException numberformatexception10) {
                        TextComponent text8 = TextBridge.asAdventure(
                           MixinHelper.method1("failedGoToSectionInvalidNumber", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text8);
                     }
               }
            }

            Component component12 = ((TextComponent)((TextComponent)((TextComponent)((TextComponent)TextBridge.asAdventure(
                              MixinHelper.method1("currentRouteSection", ChatFormatting.AQUA, ChatFormatting.GREEN)
                           )
                           .append(MixinHelper.method2(holograms74.method7(), holograms3.getBlcID(), NamedTextColor.GRAY)))
                        .append(TextBridge.asAdventure(ChatFormatting.GRAY + ":" + holograms74.getIndex() + " ")))
                     .append(
                        ((TextComponent)Component.text(MixinHelper.method1("prev"), NamedTextColor.GREEN)
                              .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToGoToPrevSection")))))
                           .clickEvent(ClickEvent.runCommand("/route section prev"))
                     ))
                  .appendSpace())
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("next"), NamedTextColor.GREEN)
                        .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToGoToNextSection")))))
                     .clickEvent(ClickEvent.runCommand("/route section next"))
               );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component12);
         }
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return items1.length == 2 ? Stream.of("prev", "next") : Stream.empty();
   }
}
