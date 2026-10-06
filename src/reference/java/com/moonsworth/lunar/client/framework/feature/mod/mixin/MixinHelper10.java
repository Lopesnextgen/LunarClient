package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MixinHelper10 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      Optional var3 = var2.method25();
      if (var3.isEmpty()) {
         TextComponent var11 = AdventureTextBridge.asAdventure(
            MixinHelper.method1(
               "failedToClonePleaseSelect", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW, ThreadModuleDump63.method7().bridge$getName()
            )
         );
         ThreadModuleDump63.method7().method1(var11);
      } else {
         Holograms var4 = var2.method18().orElse(null);
         if (var4 == null) {
            TextComponent var13 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToCloneUnknownId", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
            ThreadModuleDump63.method7().method1(var13);
         } else if (var1.length == 1) {
            TextComponent var12 = AdventureTextBridge.asAdventure(
               MixinHelper.method1("failedToCloneRouteGiveName", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
            );
            ThreadModuleDump63.method7().method1(var12);
         } else {
            Bridge5Extension_5 var5 = ThreadModuleDump63.method7();
            String var6 = var5.bridge$getName() + "-" + var1[1];
            Holograms2 var7 = new Holograms2(((Holograms7)var3.get()).method7());

            try {
               var2.method10(var7, var4.getBlcID(), var6);
               var2.method26(var7.getSections().get(((Holograms7)var3.get()).getIndex()));
            } catch (IOException var10) {
               TextComponent var9 = AdventureTextBridge.asAdventure(
                  MixinHelper.method1("failedToCloneRouteUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, var10.getMessage())
               );
               ThreadModuleDump63.method7().method1(var9);
               return;
            }

            TextComponent var8 = AdventureTextBridge.asAdventure(MixinHelper.method1("successfullyClonedRoute", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
            Component var14 = var8.append(MixinHelper.method2(var7, var6, NamedTextColor.GRAY));
            ThreadModuleDump63.method7().method1(var14);
            var2.method26(var7.getSections().get(((Holograms7)var3.get()).getIndex()));
         }
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return Stream.empty();
   }
}
