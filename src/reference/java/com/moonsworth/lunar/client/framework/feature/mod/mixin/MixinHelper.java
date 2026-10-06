package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms10$Data;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms12;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class MixinHelper {
   public static String method1(@Annotation(method1 = Annotation.Type.DUNGEON_ROUTES) String var0, Object... var1) {
      return Client.method109().method67().method2("features.SKYBLOCK.dungeonRoutes", var0, var1);
   }

   public static Component method2(Holograms2 var0, String var1, TextColor var2) {
      TextComponent var3 = Component.text(method1("no"), NamedTextColor.RED);
      TextComponent var4 = Component.text(method1("yes"), NamedTextColor.GREEN);
      return Component.text(var0.method22(), var2)
         .hoverEvent(
            HoverEvent.showText(
               ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text(
                                                                  method1("roomIdColon")
                                                               )
                                                               .append(Component.text(var1, NamedTextColor.YELLOW)))
                                                            .appendNewline())
                                                         .append(Component.text(method1("routeNameColon"))))
                                                      .append(Component.text(var0.method22(), NamedTextColor.YELLOW)))
                                                   .appendNewline())
                                                .append(Component.text(method1("aoteNeededColon"))))
                                             .append(var0.method17().needsAote() ? var4 : var3))
                                          .appendNewline())
                                       .append(Component.text(method1("etherwarpNeededColon"))))
                                    .append(var0.method17().needsEtherwarp() ? var4 : var3))
                                 .appendNewline())
                              .append(Component.text(method1("pickaxeNeededColon"))))
                           .append(var0.method17().getPickaxeTier().getFullDisplayComponent()))
                        .appendNewline())
                     .append(Component.text(method1("pearlsNeededColon"))))
                  .append(var0.method17().isPearls() ? var4 : var3)
            )
         );
   }

   public static Component method3(String var0, Holograms var1) {
      Component var2 = Component.text("");
      if (var0 == null) {
         return var2.append(Component.text("null", NamedTextColor.GRAY));
      }

      boolean var3 = true;

      for (String var7 : var0.split(";")) {
         if (!var3) {
            var2 = var2.append(Component.text(";", NamedTextColor.GRAY));
         }

         var3 = false;
         Holograms12 var8 = Holograms12.method2(var7);
         if (var8 == null) {
            var2 = var2.append(Component.text(var7, NamedTextColor.RED));
         } else {
            Holograms3_3 var9 = SkyblockDungeonRoutes.method13().method16();
            Optional var10 = var8.method3(var9);
            if (var10.isPresent()) {
               var2 = var2.append(method2(((Holograms7)var10.get()).method7(), var1.getBlcID(), NamedTextColor.AQUA));
            } else {
               var2 = var2.append(
                  Component.text(var8.method4(), NamedTextColor.RED).hoverEvent(HoverEvent.showText(Component.text(method1("unableToFindRoute"))))
               );
            }

            var2 = var2.append(Component.text(":", NamedTextColor.WHITE)).append(Component.text(String.valueOf(var8.method5()), NamedTextColor.YELLOW));
         }
      }

      return var2;
   }

   public static Component method4(Holograms10$Data var0) {
      Object var1 = Component.text(var0.text);
      if (var0.text.length() > 50) {
         var1 = Component.text(var0.text.substring(0, 47)).append(Component.text("...", NamedTextColor.GRAY));
      }

      return var1.hoverEvent(
         HoverEvent.showText(
            Component.text(
               method1("nameColon", var0.text)
                  + "\n"
                  + method1("xColon", Math.round(var0.pos.x))
                  + "\n"
                  + method1("yColon", Math.round(var0.pos.y))
                  + "\n"
                  + method1("zColon", Math.round(var0.pos.z))
            )
         )
      );
   }

   public static String method5(String var0) {
      Holograms3_3 var1 = SkyblockDungeonRoutes.method13().method16();
      StringBuilder var2 = new StringBuilder();
      boolean var3 = true;

      for (String var7 : var0.split(";")) {
         if (!var3) {
            var2.append(";");
         }

         var3 = false;
         if (!var7.contains(":")) {
            var7 = var7 + ":0";
         }

         Bridge5Extension_5 var8 = ThreadModuleDump63.method7();
         String var9 = var8.bridge$getName() + "-" + var7;
         Holograms12 var10 = Holograms12.method2(var7);
         if (var10 != null && var10.method3(var1).isEmpty()) {
            Holograms12 var11 = Holograms12.method2(var9);
            if (var11 != null && var11.method3(var1).isPresent()) {
               var7 = var9;
            }
         }

         var2.append(var7);
      }

      return var2.toString();
   }
}
