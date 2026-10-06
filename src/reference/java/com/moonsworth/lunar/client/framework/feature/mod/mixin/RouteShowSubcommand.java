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

public class RouteShowSubcommand implements RouteSubcommand {
   public RouteShowSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      if (items1.length == 2) {
         this.method2(items1[1]);
      } else {
         RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
         boolean flag3 = holograms3_32.method19();
         TextComponent text4;
         if (flag3) {
            text4 = TextBridge.asAdventure(MixinHelper.method1("showingAllRoutesInCurrentRoom", ChatFormatting.AQUA, ChatFormatting.YELLOW));
         } else {
            text4 = TextBridge.asAdventure(MixinHelper.method1("noLongerShowingAllRoutes", ChatFormatting.AQUA, ChatFormatting.YELLOW));
         }

         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text4);
      }
   }

   private void method2(String text1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
      if (holograms3 == null) {
         TextComponent text9 = TextBridge.asAdventure(MixinHelper.method1("failedToFindRouteId", ChatFormatting.AQUA, ChatFormatting.RED));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text9);
      } else {
         Bridge5Extension_5 bridge5extension_54 = Ref.method7();
         String text5 = bridge5extension_54.bridge$getName() + "-" + text1;
         Optional optional6 = holograms3_32.method23(text1);
         if (optional6.isEmpty()) {
            optional6 = holograms3_32.method23(text5);
         }

         if (optional6.isPresent()) {
            DungeonRoute holograms27 = (DungeonRoute)optional6.get();
            holograms3_32.method30(holograms27);
            TextComponent text8 = TextBridge.asAdventure(MixinHelper.method1("routeFound", ChatFormatting.AQUA, ChatFormatting.GREEN));
            Component component11 = text8.append(MixinHelper.method2(holograms27, holograms3.getBlcID(), NamedTextColor.GRAY));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component11);
         } else {
            TextComponent text10 = TextBridge.asAdventure(MixinHelper.method1("failedToFindRouteUnknownName", ChatFormatting.AQUA, ChatFormatting.RED));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text10);
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
