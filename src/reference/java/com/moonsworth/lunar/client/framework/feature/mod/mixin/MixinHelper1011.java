package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MixinHelper1011 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      Holograms var3 = var2.method18().orElse(null);
      if (var3 == null) {
         TextComponent var6 = AdventureTextBridge.asAdventure(MixinHelper.method1("routeDeselectFailedId", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
         ThreadModuleDump63.method7().method1(var6);
      } else {
         Optional var4 = var2.method25();
         if (var4.isEmpty()) {
            TextComponent var7 = AdventureTextBridge.asAdventure(
               MixinHelper.method1("routeDeselectionFailNoSelected", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
            );
            ThreadModuleDump63.method7().method1(var7);
         } else {
            var2.method26(null);
            Component var5 = AdventureTextBridge.asAdventure(MixinHelper.method1("routeDeselected", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN))
               .append(MixinHelper.method2(((Holograms7)var4.get()).method7(), var3.getBlcID(), NamedTextColor.GRAY));
            ThreadModuleDump63.method7().method1(var5);
         }
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return Stream.of();
   }
}
