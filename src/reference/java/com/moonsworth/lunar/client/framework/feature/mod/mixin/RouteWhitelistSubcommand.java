package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
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

public class RouteWhitelistSubcommand implements RouteSubcommand {
   public RouteWhitelistSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
      if (holograms3 == null) {
         TextComponent text11 = TextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", ChatFormatting.AQUA, ChatFormatting.RED));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text11);
      } else {
         if (items1.length == 1) {
            items1 = new String[]{"whitelist", "list"};
         }

         String text4 = holograms3.getBlcID();
         if (items1.length >= 3) {
            text4 = items1[2];
         }

         Component component19;
         switch (items1[1].toLowerCase(Locale.ROOT)) {
            case "list":
               text4 = holograms3.getBlcID();
               TextComponent text18 = TextBridge.asAdventure(MixinHelper.method1("roomWhitelist", ChatFormatting.AQUA, ChatFormatting.GREEN));
               if (!SkyblockDungeonRoutes.method13().method17().contains(text4)) {
                  component19 = text18.append(
                     ((TextComponent)Component.text(MixinHelper.method1("addThisRoom"), NamedTextColor.YELLOW)
                           .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                        .clickEvent(ClickEvent.runCommand("/route whitelist add"))
                  );
               } else {
                  component19 = text18.append(
                     ((TextComponent)Component.text(MixinHelper.method1("removeThisRoom"), NamedTextColor.YELLOW)
                           .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                        .clickEvent(ClickEvent.runCommand("/route whitelist remove"))
                  );
               }
               break;
            case "add":
               if (SkyblockDungeonRoutes.method13().method17().add(text4)) {
                  TextComponent text16 = TextBridge.asAdventure(MixinHelper.method1("addedRouteToWhitelist", ChatFormatting.AQUA, ChatFormatting.GREEN, text4));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text16);
               } else {
                  TextComponent text17 = TextBridge.asAdventure(MixinHelper.method1("failedToAddToWhitelist", ChatFormatting.AQUA, ChatFormatting.RED));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text17);
               }

               return;
            case "remove":
               if (SkyblockDungeonRoutes.method13().method17().remove(text4)) {
                  TextComponent text14 = TextBridge.asAdventure(MixinHelper.method1("removedFromWhitelist", ChatFormatting.AQUA, ChatFormatting.GREEN, text4));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text14);
               } else {
                  TextComponent text15 = TextBridge.asAdventure(MixinHelper.method1("failedToRemoveFromWhitelist", ChatFormatting.AQUA, ChatFormatting.RED));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text15);
               }

               return;
            case "clear":
               if (!SkyblockDungeonRoutes.method13().method17().isEmpty()) {
                  SkyblockDungeonRoutes.method13().method17().clear();
                  TextComponent text12 = TextBridge.asAdventure(MixinHelper.method1("clearedWhitelist", ChatFormatting.AQUA, ChatFormatting.GREEN));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text12);
               } else {
                  TextComponent text13 = TextBridge.asAdventure(MixinHelper.method1("failedToClearWhitelist", ChatFormatting.AQUA, ChatFormatting.RED));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text13);
               }

               return;
            default:
               TextComponent text7 = TextBridge.asAdventure(
                  MixinHelper.method1("commandFailedUnknownArgs", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW, items1[1])
               );
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text7);
               return;
         }

         for (String text9 : SkyblockDungeonRoutes.method13().method17()) {
            component19 = component19.appendNewline()
               .append(Component.text(" - ", NamedTextColor.GRAY))
               .append(
                  ((TextComponent)Component.text("[X]", NamedTextColor.RED)
                        .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                     .clickEvent(ClickEvent.runCommand("/route whitelist remove " + text9))
               )
               .append(TextBridge.asAdventure(" " + text9));
         }

         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component19);
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return items1.length == 2 ? Stream.of("list", "add", "remove", "clear") : Stream.empty();
   }
}
