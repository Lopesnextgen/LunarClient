package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MixinHelper102 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      Holograms var3 = var2.method18().orElse(null);
      if (var3 == null) {
         TextComponent var14 = AdventureTextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
         ThreadModuleDump63.method7().method1(var14);
      } else {
         Optional var4 = var2.method25();
         if (var4.isEmpty()) {
            TextComponent var18 = AdventureTextBridge.asAdventure(
               MixinHelper.method1("failedNoRouteActive", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
            );
            ThreadModuleDump63.method7().method1(var18);
         } else {
            if (var1.length >= 2) {
               boolean var5 = var2.method6().anyMatch(var1x -> var1x == ((Holograms7)var4.get()).method7());
               if (!var5) {
                  TextComponent var20 = AdventureTextBridge.asAdventure(
                     MixinHelper.method1("failedToMetaRouteIsDefault", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                  );
                  ThreadModuleDump63.method7().method1(var20);
                  return;
               }

               String var6 = var1[1];
               switch (var6) {
                  case "hidden":
                     Boolean var26 = null;
                     if (var1.length > 2) {
                        var26 = switch (var1[2].toLowerCase(Locale.ROOT)) {
                           case "yes", "enable", "true" -> true;
                           case "no", "disable", "false" -> false;
                           default -> var26;
                        };
                     }

                     if (var26 == null) {
                        TextComponent var30 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToRunUsageRouteMetaHidden", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                        );
                        ThreadModuleDump63.method7().method1(var30);
                        return;
                     }

                     try {
                        ((Holograms7)var4.get()).method7().method19().hidden = var26;
                        var2.method20(var3.getBlcID(), ((Holograms7)var4.get()).method7());
                        var2.method10(((Holograms7)var4.get()).method7(), var3.getBlcID(), ((Holograms7)var4.get()).method7().method22());
                        var2.method26((Holograms7)var4.get());
                        TextComponent var29 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("setRouteMetaHiddenTo", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, var26 + "")
                        );
                        ThreadModuleDump63.method7().method1(var29);
                        break;
                     } catch (IOException var13) {
                        TextComponent var32 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToUpdateRoute", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, var13.getMessage())
                        );
                        ThreadModuleDump63.method7().method1(var32);
                        return;
                     }
                  case "swaponcomplete":
                     if (var1.length == 2) {
                        TextComponent var25 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToRunMetaSwaponcomplete", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                        );
                        ThreadModuleDump63.method7().method1(var25);
                        return;
                     }

                     String var23 = var1[2];
                     if (var23.equals("null")) {
                        var23 = null;
                     } else {
                        var23 = MixinHelper.method5(var23);
                     }

                     try {
                        ((Holograms7)var4.get()).method7().method19().swapOnComplete = var23;
                        var2.method20(var3.getBlcID(), ((Holograms7)var4.get()).method7());
                        var2.method10(((Holograms7)var4.get()).method7(), var3.getBlcID(), ((Holograms7)var4.get()).method7().method22());
                        var2.method26((Holograms7)var4.get());
                        Component var10 = AdventureTextBridge.asAdventure(MixinHelper.method1("setSwaponcomplete", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN))
                           .append(MixinHelper.method3(var23, var3));
                        ThreadModuleDump63.method7().method1(var10);
                        break;
                     } catch (IOException var12) {
                        TextComponent var11 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToUpdateRoute", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, var12.getMessage())
                        );
                        ThreadModuleDump63.method7().method1(var11);
                        return;
                     }
                  default:
                     TextComponent var9 = AdventureTextBridge.asAdventure(
                        MixinHelper.method1("failedUnknownMetaValue", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW, var6)
                     );
                     ThreadModuleDump63.method7().method1(var9);
                     return;
               }
            }

            Component var15 = ((TextComponent)AdventureTextBridge.asAdventure(MixinHelper.method1("metaForCurrent", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN))
                  .append(MixinHelper.method2(((Holograms7)var4.get()).method7(), var3.getBlcID(), NamedTextColor.WHITE)))
               .append(Component.text(MixinHelper.method1("closeBracket2"), NamedTextColor.GREEN));
            boolean var19 = ((Holograms7)var4.get()).method7().method19().hidden;
            String var21 = MixinHelper.method1("enabled");
            String var22 = MixinHelper.method1("disabled");
            var15 = var15.append(
                  AdventureTextBridge.asAdventure(
                     MixinHelper.method1(
                        "hiddenColon", AdventureChatFormatting.GRAY, AdventureChatFormatting.YELLOW, var19 ? AdventureChatFormatting.GREEN : AdventureChatFormatting.RED, var19 ? var21 : var22
                     )
                  )
               )
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("toggle"), NamedTextColor.GRAY)
                        .hoverEvent(
                           HoverEvent.showText(
                              AdventureTextBridge.asAdventure(MixinHelper.method1("clickTo", var19 ? MixinHelper.method1("disable") : MixinHelper.method1("enable")))
                           )
                        ))
                     .clickEvent(ClickEvent.runCommand("/route meta hidden " + (var19 ? "disable" : "enable")))
               );
            String var27 = ((Holograms7)var4.get()).method7().method19().swapOnComplete;
            var15 = var15.append(AdventureTextBridge.asAdventure(MixinHelper.method1("swapOnCompleteColon", AdventureChatFormatting.GRAY, AdventureChatFormatting.YELLOW)))
               .append(MixinHelper.method3(var27, var3))
               .appendSpace()
               .append(
                  ((TextComponent)Component.text("[EDIT]", NamedTextColor.GRAY)
                        .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToModify")))))
                     .clickEvent(ClickEvent.suggestCommand("/route meta swaponcomplete "))
               );
            if (var27 != null) {
               var15 = var15.appendSpace()
                  .append(
                     ((TextComponent)Component.text("[REMOVE]", NamedTextColor.GRAY)
                           .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                        .clickEvent(ClickEvent.runCommand("/route meta swaponcomplete null"))
                  );
            }

            ThreadModuleDump63.method7().method1(var15);
         }
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      if (var1.length == 2) {
         return Stream.of("hidden", "swaponcomplete");
      } else if (var1.length == 3 && var1[1].equalsIgnoreCase("hidden")) {
         return Stream.of("true", "false");
      } else {
         return var1.length == 3 && var1[1].equalsIgnoreCase("swaponcomplete") ? Stream.of("null") : Stream.empty();
      }
   }
}
