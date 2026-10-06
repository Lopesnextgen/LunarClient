package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MixinHelper109 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      if (var1.length == 1) {
         TextComponent var10 = AdventureTextBridge.asAdventure(
            MixinHelper.method1("failedToFindRouteNoName", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
         );
         ThreadModuleDump63.method7().method1(var10);
      } else {
         Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
         Holograms var3 = var2.method18().orElse(null);
         if (var3 == null) {
            TextComponent var11 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToFindRouteId", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
            ThreadModuleDump63.method7().method1(var11);
         } else {
            Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
            String var5 = var1[1];
            String var6 = var4.bridge$getName() + "-" + var1[1];
            Optional var7 = var2.method23(var5);
            if (var7.isEmpty()) {
               var7 = var2.method23(var6);
            }

            if (var7.isPresent()) {
               Holograms2 var8 = (Holograms2)var7.get();
               Component var9 = AdventureTextBridge.asAdventure(MixinHelper.method1("foundRoute", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN))
                  .append(MixinHelper.method2(var8, var3.getBlcID(), NamedTextColor.GRAY));
               ThreadModuleDump63.method7().method1(var9);
            } else {
               TextComponent var12 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToFindRouteUnknownName", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
               ThreadModuleDump63.method7().method1(var12);
            }
         }
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return Stream.empty();
   }
}
