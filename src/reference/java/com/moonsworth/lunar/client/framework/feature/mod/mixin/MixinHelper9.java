package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
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

public class MixinHelper9 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      SkyblockDungeonRoutes var2 = SkyblockDungeonRoutes.method13();
      Holograms3_3 var3 = var2.method16();
      Holograms var4 = var3.method18().orElse(null);
      if (var4 == null) {
         TextComponent var12 = AdventureTextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
         ThreadModuleDump63.method7().method1(var12);
      } else {
         if (var1.length == 1) {
            var1 = new String[]{"blacklist", "list"};
         }

         String var5 = var4.getBlcID();
         if (var1.length >= 3) {
            var5 = var1[2];
         }

         Component var20;
         switch (var1[1].toLowerCase(Locale.ROOT)) {
            case "list":
               var5 = var4.getBlcID();
               TextComponent var19 = AdventureTextBridge.asAdventure(MixinHelper.method1("roomBlackList", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
               if (!var2.method19().contains(var5)) {
                  var20 = var19.append(
                     ((TextComponent)Component.text("[ADD THIS ROOM]", NamedTextColor.YELLOW)
                           .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                        .clickEvent(ClickEvent.runCommand("/route blacklist add"))
                  );
               } else {
                  var20 = var19.append(
                     ((TextComponent)Component.text("[REMOVE THIS ROOM]", NamedTextColor.YELLOW)
                           .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                        .clickEvent(ClickEvent.runCommand("/route blacklist remove"))
                  );
               }
               break;
            case "add":
               if (var2.method19().add(var5)) {
                  TextComponent var17 = AdventureTextBridge.asAdventure(
                     MixinHelper.method1("successfullyAddedToBlacklist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, var5)
                  );
                  ThreadModuleDump63.method7().method1(var17);
               } else {
                  TextComponent var18 = AdventureTextBridge.asAdventure(MixinHelper.method1("roomAlreadyBlacklisted", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
                  ThreadModuleDump63.method7().method1(var18);
               }

               return;
            case "remove":
               if (var2.method19().remove(var5)) {
                  TextComponent var15 = AdventureTextBridge.asAdventure(
                     MixinHelper.method1("successfullyRemovedFromBlacklist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, var5)
                  );
                  ThreadModuleDump63.method7().method1(var15);
               } else {
                  TextComponent var16 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToRemoveFromBlacklist", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
                  ThreadModuleDump63.method7().method1(var16);
               }

               return;
            case "clear":
               if (!var2.method19().isEmpty()) {
                  var2.method19().clear();
                  TextComponent var13 = AdventureTextBridge.asAdventure(MixinHelper.method1("successfullyClearedBlacklist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
                  ThreadModuleDump63.method7().method1(var13);
               } else {
                  TextComponent var14 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToClearBlacklist", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
                  ThreadModuleDump63.method7().method1(var14);
               }

               return;
            default:
               TextComponent var8 = AdventureTextBridge.asAdventure(
                  MixinHelper.method1("commandFailedUnknownArgs", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW, var1[1])
               );
               ThreadModuleDump63.method7().method1(var8);
               return;
         }

         for (String var10 : var2.method19()) {
            var20 = var20.append(AdventureTextBridge.asAdventure("\n" + AdventureChatFormatting.GRAY + " - "))
               .append(
                  ((TextComponent)Component.text("[X]", NamedTextColor.RED)
                        .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                     .clickEvent(ClickEvent.runCommand("/route blacklist remove " + var10))
               )
               .append(AdventureTextBridge.asAdventure(" " + var10));
         }

         ThreadModuleDump63.method7().method1(var20);
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return var1.length == 2 ? Stream.of("list", "add", "remove", "clear") : Stream.empty();
   }
}
