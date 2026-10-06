package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms10_2;
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

public class MixinHelper103 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      if (var1.length == 1) {
         TextComponent var12 = AdventureTextBridge.asAdventure(
            MixinHelper.method1("failedToDeleteRouteNoName", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
         );
         ThreadModuleDump63.method7().method1(var12);
      } else {
         Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
         Holograms var3 = var2.method18().orElse(null);
         if (var3 == null) {
            TextComponent var13 = AdventureTextBridge.asAdventure(MixinHelper.method1("failedToDeleteRouteIdUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
            ThreadModuleDump63.method7().method1(var13);
         } else {
            Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
            String var5 = var1[1];
            String var6 = var4.bridge$getName() + "-" + var1[1];
            String var7 = var3.getBlcID();
            Optional var8 = var2.method6().filter(var2x -> var2x.method22().equals(var5) || var2x.method22().equals(var6)).findFirst();
            var8.ifPresent(
               var3x -> var2.method6()
                  .forEach(
                     var4x -> {
                        if (var4x.method19().getSwapOnComplete() != null
                           && var4x.method19()
                              .getSwapOnComplete()
                              .stream()
                              .anyMatch(var2xxx -> var2xxx.method3(var2).map(Holograms7::method7).orElse(null) == var3x)) {
                           Component var5x = ((TextComponent)((TextComponent)((TextComponent)AdventureTextBridge.asAdventure(
                                          MixinHelper.method1("routeDeletionWarning", AdventureChatFormatting.AQUA, AdventureChatFormatting.YELLOW)
                                       )
                                       .append(MixinHelper.method2(var4x, var7, NamedTextColor.GRAY)))
                                    .append(AdventureTextBridge.asAdventure(MixinHelper.method1("routeDeletionWarningInclude", AdventureChatFormatting.YELLOW))))
                                 .append(MixinHelper.method3(var4x.method19().swapOnComplete, var3)))
                              .append(AdventureTextBridge.asAdventure(MixinHelper.method1("routeDeletionWarningCloseBracket", AdventureChatFormatting.YELLOW)));
                           ThreadModuleDump63.method7().method1(var5x);
                        }

                        for (Holograms7 var6x : var4x.getSections()) {
                           Holograms10_2 var7x = var6x.method6();
                           if (var7x.getSwapOnLocked() != null
                              && var7x.getSwapOnLocked().stream().anyMatch(var2xxx -> var2xxx.method3(var2).map(Holograms7::method7).orElse(null) == var3x)) {
                              Component var8x = ((TextComponent)((TextComponent)AdventureTextBridge.asAdventure(
                                          MixinHelper.method1("routeDeletionWarning", AdventureChatFormatting.AQUA, AdventureChatFormatting.YELLOW)
                                       )
                                       .append(MixinHelper.method2(var4x, var7, NamedTextColor.GRAY)))
                                    .append(
                                       AdventureTextBridge.asAdventure(
                                          MixinHelper.method1("routeDeletionWarningSwapOnLocked", AdventureChatFormatting.YELLOW, var6x.getIndex() + 1)
                                       )
                                    ))
                                 .append(AdventureTextBridge.asAdventure(MixinHelper.method1("routeDeletionWarningCloseBracket", AdventureChatFormatting.YELLOW)));
                              ThreadModuleDump63.method7().method1(var8x);
                           }
                        }
                     }
                  )
            );

            try {
               boolean var9 = var2.method21(var7, var5);
               if (!var9) {
                  var9 = var2.method21(var7, var6);
               }

               if (!var9) {
                  TextComponent var15 = AdventureTextBridge.asAdventure(
                     MixinHelper.method1("routeDeletionWarningNoExists", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                  );
                  ThreadModuleDump63.method7().method1(var15);
                  return;
               }

               var2.method15();
               TextComponent var14 = AdventureTextBridge.asAdventure(
                  MixinHelper.method1("routeDeleteSuccess", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, AdventureChatFormatting.YELLOW, var5, var7)
               );
               ThreadModuleDump63.method7().method1(var14);
            } catch (IOException var11) {
               TextComponent var10 = AdventureTextBridge.asAdventure(
                  MixinHelper.method1("routeDeleteFailedUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, var11.getMessage())
               );
               ThreadModuleDump63.method7().method1(var10);
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
