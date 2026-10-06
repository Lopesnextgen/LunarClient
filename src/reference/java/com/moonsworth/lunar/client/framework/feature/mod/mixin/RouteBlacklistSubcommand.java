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

public class RouteBlacklistSubcommand implements RouteSubcommand {
   public RouteBlacklistSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      SkyblockDungeonRoutes skyblockdungeonroutes2 = SkyblockDungeonRoutes.method13();
      RouteManager holograms3_33 = skyblockdungeonroutes2.method16();
      DungeonRoom holograms4 = holograms3_33.method18().orElse(null);
      if (holograms4 == null) {
         TextComponent text12 = TextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", ChatFormatting.AQUA, ChatFormatting.RED));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text12);
      } else {
         if (items1.length == 1) {
            items1 = new String[]{"blacklist", "list"};
         }

         String text5 = holograms4.getBlcID();
         if (items1.length >= 3) {
            text5 = items1[2];
         }

         Component component20;
         switch (items1[1].toLowerCase(Locale.ROOT)) {
            case "list":
               text5 = holograms4.getBlcID();
               TextComponent text19 = TextBridge.asAdventure(MixinHelper.method1("roomBlackList", ChatFormatting.AQUA, ChatFormatting.GREEN));
               if (!skyblockdungeonroutes2.method19().contains(text5)) {
                  component20 = text19.append(
                     ((TextComponent)Component.text("[ADD THIS ROOM]", NamedTextColor.YELLOW)
                           .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                        .clickEvent(ClickEvent.runCommand("/route blacklist add"))
                  );
               } else {
                  component20 = text19.append(
                     ((TextComponent)Component.text("[REMOVE THIS ROOM]", NamedTextColor.YELLOW)
                           .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                        .clickEvent(ClickEvent.runCommand("/route blacklist remove"))
                  );
               }
               break;
            case "add":
               if (skyblockdungeonroutes2.method19().add(text5)) {
                  TextComponent text17 = TextBridge.asAdventure(
                     MixinHelper.method1("successfullyAddedToBlacklist", ChatFormatting.AQUA, ChatFormatting.GREEN, text5)
                  );
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text17);
               } else {
                  TextComponent text18 = TextBridge.asAdventure(MixinHelper.method1("roomAlreadyBlacklisted", ChatFormatting.AQUA, ChatFormatting.RED));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text18);
               }

               return;
            case "remove":
               if (skyblockdungeonroutes2.method19().remove(text5)) {
                  TextComponent text15 = TextBridge.asAdventure(
                     MixinHelper.method1("successfullyRemovedFromBlacklist", ChatFormatting.AQUA, ChatFormatting.GREEN, text5)
                  );
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text15);
               } else {
                  TextComponent text16 = TextBridge.asAdventure(MixinHelper.method1("failedToRemoveFromBlacklist", ChatFormatting.AQUA, ChatFormatting.RED));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text16);
               }

               return;
            case "clear":
               if (!skyblockdungeonroutes2.method19().isEmpty()) {
                  skyblockdungeonroutes2.method19().clear();
                  TextComponent text13 = TextBridge.asAdventure(MixinHelper.method1("successfullyClearedBlacklist", ChatFormatting.AQUA, ChatFormatting.GREEN));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text13);
               } else {
                  TextComponent text14 = TextBridge.asAdventure(MixinHelper.method1("failedToClearBlacklist", ChatFormatting.AQUA, ChatFormatting.RED));
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text14);
               }

               return;
            default:
               TextComponent text8 = TextBridge.asAdventure(
                  MixinHelper.method1("commandFailedUnknownArgs", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW, items1[1])
               );
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text8);
               return;
         }

         for (String text10 : skyblockdungeonroutes2.method19()) {
            component20 = component20.append(TextBridge.asAdventure("\n" + ChatFormatting.GRAY + " - "))
               .append(
                  ((TextComponent)Component.text("[X]", NamedTextColor.RED)
                        .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                     .clickEvent(ClickEvent.runCommand("/route blacklist remove " + text10))
               )
               .append(TextBridge.asAdventure(" " + text10));
         }

         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component20);
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return items1.length == 2 ? Stream.of("list", "add", "remove", "clear") : Stream.empty();
   }
}
