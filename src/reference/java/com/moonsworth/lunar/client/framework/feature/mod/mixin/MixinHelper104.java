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

public class MixinHelper104 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      if (var1.length == 2) {
         this.method2(var1[1]);
      } else {
         Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
         boolean var3 = var2.method19();
         TextComponent var4;
         if (var3) {
            var4 = AdventureTextBridge.asAdventure(MixinHelper.method1("showingAllRoutesInCurrentRoom", AdventureChatFormatting.AQUA, AdventureChatFormatting.YELLOW));
         } else {
            var4 = AdventureTextBridge.asAdventure(MixinHelper.method1("noLongerShowingAllRoutes", AdventureChatFormatting.AQUA, AdventureChatFormatting.YELLOW));
         }

         ThreadModuleDump63.method7().method1(var4);
      }
   }

   private void method2(String var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      Holograms var3 = var2.method18().orElse(null);
      if (var3 == null) {
         TextComponent var9 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToFindRouteId", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
         ThreadModuleDump63.method7().method1(var9);
      } else {
         Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
         String var5 = var4.bridge$getName() + "-" + var1;
         Optional var6 = var2.method23(var1);
         if (var6.isEmpty()) {
            var6 = var2.method23(var5);
         }

         if (var6.isPresent()) {
            Holograms2 var7 = (Holograms2)var6.get();
            var2.method30(var7);
            TextComponent var8 = AdventureTextBridge.asAdventure(MixinHelper.method1("routeFound", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
            Component var11 = var8.append(MixinHelper.method2(var7, var3.getBlcID(), NamedTextColor.GRAY));
            ThreadModuleDump63.method7().method1(var11);
         } else {
            TextComponent var10 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToFindRouteUnknownName", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
            ThreadModuleDump63.method7().method1(var10);
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
