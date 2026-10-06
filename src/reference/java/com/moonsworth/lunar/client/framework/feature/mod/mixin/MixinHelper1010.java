package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MixinHelper1010 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      Holograms var3 = var2.method18().orElse(null);
      if (var3 == null) {
         TextComponent var10 = AdventureTextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
         ThreadModuleDump63.method7().method1(var10);
      } else {
         Component var4 = AdventureTextBridge.asAdventure(MixinHelper.method1("routesInCurrentRoom", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));

         for (Holograms2 var6 : var2.method5(true).toList()) {
            var4 = var4.append(AdventureTextBridge.asAdventure(MixinHelper.method1("dash", AdventureChatFormatting.GRAY)));
            boolean var7 = var2.method6().anyMatch(var1x -> var1x == var6);
            boolean var8 = false;
            if (!var7) {
               var4 = var4.append(
                  Component.text("[B]", NamedTextColor.YELLOW).hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("builtin"))))
               );
               var8 = true;
            }

            if (var6.method19().hidden) {
               var4 = var4.append(
                  Component.text("[H]", NamedTextColor.YELLOW).hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("hidden"))))
               );
               var8 = true;
            }

            if (var8) {
               var4 = var4.appendSpace();
            }

            var4 = var4.append(MixinHelper.method2(var6, var3.getBlcID(), NamedTextColor.GREEN))
               .appendSpace()
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("select"), NamedTextColor.YELLOW)
                        .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToSelect")))))
                     .clickEvent(ClickEvent.runCommand("/route select " + var6.method22()))
               );
         }

         ThreadModuleDump63.method7().method1(var4);
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return Stream.empty();
   }
}
