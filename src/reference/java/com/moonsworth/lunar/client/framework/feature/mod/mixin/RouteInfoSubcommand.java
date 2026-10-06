package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonRoute;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class RouteInfoSubcommand implements RouteSubcommand {
   public RouteInfoSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      if (items1.length == 1) {
         TextComponent text10 = TextBridge.asAdventure(
            MixinHelper.method1("failedToFindRouteNoName", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
         );
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text10);
      } else {
         RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
         DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
         if (holograms3 == null) {
            TextComponent text11 = TextBridge.asAdventure(MixinHelper.method1("failedToFindRouteId", ChatFormatting.AQUA, ChatFormatting.RED));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text11);
         } else {
            Bridge5Extension_5 bridge5extension_54 = Ref.method7();
            String text5 = items1[1];
            String text6 = bridge5extension_54.bridge$getName() + "-" + items1[1];
            Optional optional7 = holograms3_32.method23(text5);
            if (optional7.isEmpty()) {
               optional7 = holograms3_32.method23(text6);
            }

            if (optional7.isPresent()) {
               DungeonRoute holograms28 = (DungeonRoute)optional7.get();
               Component component9 = TextBridge.asAdventure(MixinHelper.method1("foundRoute", ChatFormatting.AQUA, ChatFormatting.GREEN))
                  .append(MixinHelper.method2(holograms28, holograms3.getBlcID(), NamedTextColor.GRAY));
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component9);
            } else {
               TextComponent text12 = TextBridge.asAdventure(MixinHelper.method1("failedToFindRouteUnknownName", ChatFormatting.AQUA, ChatFormatting.RED));
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text12);
            }
         }
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return Stream.empty();
   }
}
