package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms12;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MixinHelper1012 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      if (var1.length == 1) {
         TextComponent var8 = AdventureTextBridge.asAdventure(
            MixinHelper.method1("failedToSelectRouteNoName", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
         );
         ThreadModuleDump63.method7().method1(var8);
      } else {
         Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
         Holograms var3 = var2.method18().orElse(null);
         if (var3 == null) {
            TextComponent var9 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToSelectRouteNoId", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
            ThreadModuleDump63.method7().method1(var9);
         } else {
            Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
            String var5 = var1[1];
            Optional var6;
            if (var5.contains(":")) {
               List var7 = Holograms12.method1(var5);
               var6 = var2.method8(var7);
            } else {
               String var10 = var4.bridge$getName() + "-" + var1[1];
               var6 = var2.method23(var5).map(Holograms2::method3);
               if (var6.isEmpty()) {
                  var6 = var2.method23(var10).map(Holograms2::method3);
               }
            }

            if (var6.isEmpty()) {
               TextComponent var12 = AdventureTextBridge.asAdventure(
                  MixinHelper.method1("failedToSelectRouteCantFindName", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
               );
               ThreadModuleDump63.method7().method1(var12);
            } else {
               var2.method26((Holograms7)var6.get());
               Component var11 = AdventureTextBridge.asAdventure(MixinHelper.method1("selectedRoute", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN))
                  .append(MixinHelper.method2(((Holograms7)var6.get()).method7(), var3.getBlcID(), NamedTextColor.GRAY));
               ThreadModuleDump63.method7().method1(var11);
            }
         }
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      if (var1.length == 2) {
         Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
         return var2.method5(true).map(Holograms2::method22);
      } else {
         return Stream.empty();
      }
   }
}
