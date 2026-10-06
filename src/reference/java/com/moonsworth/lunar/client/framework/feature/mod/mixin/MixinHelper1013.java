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

public class MixinHelper1013 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      Holograms var3 = var2.method18().orElse(null);
      if (var3 == null) {
         TextComponent var11 = AdventureTextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
         ThreadModuleDump63.method7().method1(var11);
      } else {
         if (var1.length == 1) {
            var1 = new String[]{"whitelist", "list"};
         }

         String var4 = var3.getBlcID();
         if (var1.length >= 3) {
            var4 = var1[2];
         }

         Component var19;
         switch (var1[1].toLowerCase(Locale.ROOT)) {
            case "list":
               var4 = var3.getBlcID();
               TextComponent var18 = AdventureTextBridge.asAdventure(MixinHelper.method1("roomWhitelist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
               if (!SkyblockDungeonRoutes.method13().method17().contains(var4)) {
                  var19 = var18.append(
                     ((TextComponent)Component.text(MixinHelper.method1("addThisRoom"), NamedTextColor.YELLOW)
                           .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                        .clickEvent(ClickEvent.runCommand("/route whitelist add"))
                  );
               } else {
                  var19 = var18.append(
                     ((TextComponent)Component.text(MixinHelper.method1("removeThisRoom"), NamedTextColor.YELLOW)
                           .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                        .clickEvent(ClickEvent.runCommand("/route whitelist remove"))
                  );
               }
               break;
            case "add":
               if (SkyblockDungeonRoutes.method13().method17().add(var4)) {
                  TextComponent var16 = AdventureTextBridge.asAdventure(MixinHelper.method1("addedRouteToWhitelist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, var4));
                  ThreadModuleDump63.method7().method1(var16);
               } else {
                  TextComponent var17 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToAddToWhitelist", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
                  ThreadModuleDump63.method7().method1(var17);
               }

               return;
            case "remove":
               if (SkyblockDungeonRoutes.method13().method17().remove(var4)) {
                  TextComponent var14 = AdventureTextBridge.asAdventure(MixinHelper.method1("removedFromWhitelist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, var4));
                  ThreadModuleDump63.method7().method1(var14);
               } else {
                  TextComponent var15 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToRemoveFromWhitelist", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
                  ThreadModuleDump63.method7().method1(var15);
               }

               return;
            case "clear":
               if (!SkyblockDungeonRoutes.method13().method17().isEmpty()) {
                  SkyblockDungeonRoutes.method13().method17().clear();
                  TextComponent var12 = AdventureTextBridge.asAdventure(MixinHelper.method1("clearedWhitelist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
                  ThreadModuleDump63.method7().method1(var12);
               } else {
                  TextComponent var13 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToClearWhitelist", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
                  ThreadModuleDump63.method7().method1(var13);
               }

               return;
            default:
               TextComponent var7 = AdventureTextBridge.asAdventure(
                  MixinHelper.method1("commandFailedUnknownArgs", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW, var1[1])
               );
               ThreadModuleDump63.method7().method1(var7);
               return;
         }

         for (String var9 : SkyblockDungeonRoutes.method13().method17()) {
            var19 = var19.appendNewline()
               .append(Component.text(" - ", NamedTextColor.GRAY))
               .append(
                  ((TextComponent)Component.text("[X]", NamedTextColor.RED)
                        .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                     .clickEvent(ClickEvent.runCommand("/route whitelist remove " + var9))
               )
               .append(AdventureTextBridge.asAdventure(" " + var9));
         }

         ThreadModuleDump63.method7().method1(var19);
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return var1.length == 2 ? Stream.of("list", "add", "remove", "clear") : Stream.empty();
   }
}
