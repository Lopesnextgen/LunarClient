package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteLink;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonRoute;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class RouteSelectSubcommand implements RouteSubcommand {
   public RouteSelectSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      if (items1.length == 1) {
         TextComponent text8 = TextBridge.asAdventure(
            MixinHelper.method1("failedToSelectRouteNoName", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
         );
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text8);
      } else {
         RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
         DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
         if (holograms3 == null) {
            TextComponent text9 = TextBridge.asAdventure(MixinHelper.method1("failedToSelectRouteNoId", ChatFormatting.AQUA, ChatFormatting.RED));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text9);
         } else {
            Bridge5Extension_5 bridge5extension_54 = Ref.method7();
            String text5 = items1[1];
            Optional optional6;
            if (text5.contains(":")) {
               List list7 = RouteLink.method1(text5);
               optional6 = holograms3_32.method8(list7);
            } else {
               String text10 = bridge5extension_54.bridge$getName() + "-" + items1[1];
               optional6 = holograms3_32.method23(text5).map(DungeonRoute::method3);
               if (optional6.isEmpty()) {
                  optional6 = holograms3_32.method23(text10).map(DungeonRoute::method3);
               }
            }

            if (optional6.isEmpty()) {
               TextComponent text12 = TextBridge.asAdventure(
                  MixinHelper.method1("failedToSelectRouteCantFindName", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
               );
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text12);
            } else {
               holograms3_32.method26((RouteSection)optional6.get());
               Component component11 = TextBridge.asAdventure(MixinHelper.method1("selectedRoute", ChatFormatting.AQUA, ChatFormatting.GREEN))
                  .append(MixinHelper.method2(((RouteSection)optional6.get()).method7(), holograms3.getBlcID(), NamedTextColor.GRAY));
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component11);
            }
         }
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      if (items1.length == 2) {
         RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
         return holograms3_32.method5(true).map(DungeonRoute::method22);
      } else {
         return Stream.empty();
      }
   }
}
