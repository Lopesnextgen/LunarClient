package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonRoute;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class RouteCloneSubcommand implements RouteSubcommand {
   public RouteCloneSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      Optional optional3 = holograms3_32.method25();
      if (optional3.isEmpty()) {
         TextComponent text11 = TextBridge.asAdventure(
            MixinHelper.method1(
               "failedToClonePleaseSelect", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW, Ref.method7().bridge$getName()
            )
         );
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text11);
      } else {
         DungeonRoom holograms4 = holograms3_32.method18().orElse(null);
         if (holograms4 == null) {
            TextComponent text13 = TextBridge.asAdventure(MixinHelper.method1("failedToCloneUnknownId", ChatFormatting.AQUA, ChatFormatting.RED));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text13);
         } else if (items1.length == 1) {
            TextComponent text12 = TextBridge.asAdventure(
               MixinHelper.method1("failedToCloneRouteGiveName", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text12);
         } else {
            Bridge5Extension_5 bridge5extension_55 = Ref.method7();
            String text6 = bridge5extension_55.bridge$getName() + "-" + items1[1];
            DungeonRoute holograms27 = new DungeonRoute(((RouteSection)optional3.get()).method7());

            try {
               holograms3_32.method10(holograms27, holograms4.getBlcID(), text6);
               holograms3_32.method26(holograms27.getSections().get(((RouteSection)optional3.get()).getIndex()));
            } catch (IOException exception10) {
               TextComponent text9 = TextBridge.asAdventure(
                  MixinHelper.method1("failedToCloneRouteUnknown", ChatFormatting.AQUA, ChatFormatting.RED, exception10.getMessage())
               );
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text9);
               return;
            }

            TextComponent text8 = TextBridge.asAdventure(MixinHelper.method1("successfullyClonedRoute", ChatFormatting.AQUA, ChatFormatting.GREEN));
            Component component14 = text8.append(MixinHelper.method2(holograms27, text6, NamedTextColor.GRAY));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component14);
            holograms3_32.method26(holograms27.getSections().get(((RouteSection)optional3.get()).getIndex()));
         }
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return Stream.empty();
   }
}
