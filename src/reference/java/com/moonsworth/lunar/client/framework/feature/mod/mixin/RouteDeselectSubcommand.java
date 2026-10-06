package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class RouteDeselectSubcommand implements RouteSubcommand {
   public RouteDeselectSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
      if (holograms3 == null) {
         TextComponent text6 = TextBridge.asAdventure(MixinHelper.method1("routeDeselectFailedId", ChatFormatting.AQUA, ChatFormatting.RED));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text6);
      } else {
         Optional optional4 = holograms3_32.method25();
         if (optional4.isEmpty()) {
            TextComponent text7 = TextBridge.asAdventure(
               MixinHelper.method1("routeDeselectionFailNoSelected", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text7);
         } else {
            holograms3_32.method26(null);
            Component component5 = TextBridge.asAdventure(MixinHelper.method1("routeDeselected", ChatFormatting.AQUA, ChatFormatting.GREEN))
               .append(MixinHelper.method2(((RouteSection)optional4.get()).method7(), holograms3.getBlcID(), NamedTextColor.GRAY));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component5);
         }
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return Stream.of();
   }
}
