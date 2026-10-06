package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteSegment;
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

public class RouteDeleteSubcommand implements RouteSubcommand {
   public RouteDeleteSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      if (items1.length == 1) {
         TextComponent text12 = TextBridge.asAdventure(
            MixinHelper.method1("failedToDeleteRouteNoName", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
         );
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text12);
      } else {
         RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
         DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
         if (holograms3 == null) {
            TextComponent text13 = TextBridge.asAdventure(MixinHelper.method1("failedToDeleteRouteIdUnknown", ChatFormatting.AQUA, ChatFormatting.RED));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text13);
         } else {
            Bridge5Extension_5 bridge5extension_54 = Ref.method7();
            String text5 = items1[1];
            String text6 = bridge5extension_54.bridge$getName() + "-" + items1[1];
            String text7 = holograms3.getBlcID();
            Optional optional8 = holograms3_32.method6().filter(arg2x -> arg2x.method22().equals(text5) || arg2x.method22().equals(text6)).findFirst();
            optional8.ifPresent(
               arg3x -> holograms3_32.method6()
                  .forEach(
                     arg4x -> {
                        if (arg4x.method19().getSwapOnComplete() != null
                           && arg4x.method19()
                              .getSwapOnComplete()
                              .stream()
                              .anyMatch(arg2xxx -> arg2xxx.method3(holograms3_32).map(RouteSection::method7).orElse(null) == arg3x)) {
                           Component component5x = ((TextComponent)((TextComponent)((TextComponent)TextBridge.asAdventure(
                                          MixinHelper.method1("routeDeletionWarning", ChatFormatting.AQUA, ChatFormatting.YELLOW)
                                       )
                                       .append(MixinHelper.method2(arg4x, text7, NamedTextColor.GRAY)))
                                    .append(TextBridge.asAdventure(MixinHelper.method1("routeDeletionWarningInclude", ChatFormatting.YELLOW))))
                                 .append(MixinHelper.method3(arg4x.method19().swapOnComplete, holograms3)))
                              .append(TextBridge.asAdventure(MixinHelper.method1("routeDeletionWarningCloseBracket", ChatFormatting.YELLOW)));
                           Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component5x);
                        }

                        for (RouteSection holograms76x : arg4x.getSections()) {
                           RouteSegment holograms10_27x = holograms76x.method6();
                           if (holograms10_27x.getSwapOnLocked() != null
                              && holograms10_27x.getSwapOnLocked().stream().anyMatch(arg2xxx -> arg2xxx.method3(holograms3_32).map(RouteSection::method7).orElse(null) == arg3x)) {
                              Component component8x = ((TextComponent)((TextComponent)TextBridge.asAdventure(
                                          MixinHelper.method1("routeDeletionWarning", ChatFormatting.AQUA, ChatFormatting.YELLOW)
                                       )
                                       .append(MixinHelper.method2(arg4x, text7, NamedTextColor.GRAY)))
                                    .append(
                                       TextBridge.asAdventure(
                                          MixinHelper.method1("routeDeletionWarningSwapOnLocked", ChatFormatting.YELLOW, holograms76x.getIndex() + 1)
                                       )
                                    ))
                                 .append(TextBridge.asAdventure(MixinHelper.method1("routeDeletionWarningCloseBracket", ChatFormatting.YELLOW)));
                              Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component8x);
                           }
                        }
                     }
                  )
            );

            try {
               boolean flag9 = holograms3_32.method21(text7, text5);
               if (!flag9) {
                  flag9 = holograms3_32.method21(text7, text6);
               }

               if (!flag9) {
                  TextComponent text15 = TextBridge.asAdventure(
                     MixinHelper.method1("routeDeletionWarningNoExists", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                  );
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text15);
                  return;
               }

               holograms3_32.method15();
               TextComponent text14 = TextBridge.asAdventure(
                  MixinHelper.method1("routeDeleteSuccess", ChatFormatting.AQUA, ChatFormatting.GREEN, ChatFormatting.YELLOW, text5, text7)
               );
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text14);
            } catch (IOException exception11) {
               TextComponent text10 = TextBridge.asAdventure(
                  MixinHelper.method1("routeDeleteFailedUnknown", ChatFormatting.AQUA, ChatFormatting.RED, exception11.getMessage())
               );
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text10);
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
