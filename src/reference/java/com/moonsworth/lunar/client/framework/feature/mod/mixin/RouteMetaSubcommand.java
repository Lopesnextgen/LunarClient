package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class RouteMetaSubcommand implements RouteSubcommand {
   public RouteMetaSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
      if (holograms3 == null) {
         TextComponent text14 = TextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", ChatFormatting.AQUA, ChatFormatting.RED));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text14);
      } else {
         Optional optional4 = holograms3_32.method25();
         if (optional4.isEmpty()) {
            TextComponent text18 = TextBridge.asAdventure(
               MixinHelper.method1("failedNoRouteActive", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text18);
         } else {
            if (items1.length >= 2) {
               boolean flag5 = holograms3_32.method6().anyMatch(arg1x -> arg1x == ((RouteSection)optional4.get()).method7());
               if (!flag5) {
                  TextComponent text20 = TextBridge.asAdventure(
                     MixinHelper.method1("failedToMetaRouteIsDefault", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                  );
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text20);
                  return;
               }

               String text6 = items1[1];
               switch (text6) {
                  case "hidden":
                     Boolean flag26 = null;
                     if (items1.length > 2) {
                        flag26 = switch (items1[2].toLowerCase(Locale.ROOT)) {
                           case "yes", "enable", "true" -> true;
                           case "no", "disable", "false" -> false;
                           default -> flag26;
                        };
                     }

                     if (flag26 == null) {
                        TextComponent text30 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToRunUsageRouteMetaHidden", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text30);
                        return;
                     }

                     try {
                        ((RouteSection)optional4.get()).method7().method19().hidden = flag26;
                        holograms3_32.method20(holograms3.getBlcID(), ((RouteSection)optional4.get()).method7());
                        holograms3_32.method10(((RouteSection)optional4.get()).method7(), holograms3.getBlcID(), ((RouteSection)optional4.get()).method7().method22());
                        holograms3_32.method26((RouteSection)optional4.get());
                        TextComponent text29 = TextBridge.asAdventure(
                           MixinHelper.method1("setRouteMetaHiddenTo", ChatFormatting.AQUA, ChatFormatting.GREEN, flag26 + "")
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text29);
                        break;
                     } catch (IOException exception13) {
                        TextComponent text32 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToUpdateRoute", ChatFormatting.AQUA, ChatFormatting.RED, exception13.getMessage())
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text32);
                        return;
                     }
                  case "swaponcomplete":
                     if (items1.length == 2) {
                        TextComponent text25 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToRunMetaSwaponcomplete", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text25);
                        return;
                     }

                     String text23 = items1[2];
                     if (text23.equals("null")) {
                        text23 = null;
                     } else {
                        text23 = MixinHelper.method5(text23);
                     }

                     try {
                        ((RouteSection)optional4.get()).method7().method19().swapOnComplete = text23;
                        holograms3_32.method20(holograms3.getBlcID(), ((RouteSection)optional4.get()).method7());
                        holograms3_32.method10(((RouteSection)optional4.get()).method7(), holograms3.getBlcID(), ((RouteSection)optional4.get()).method7().method22());
                        holograms3_32.method26((RouteSection)optional4.get());
                        Component component10 = TextBridge.asAdventure(MixinHelper.method1("setSwaponcomplete", ChatFormatting.AQUA, ChatFormatting.GREEN))
                           .append(MixinHelper.method3(text23, holograms3));
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component10);
                        break;
                     } catch (IOException exception12) {
                        TextComponent text11 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToUpdateRoute", ChatFormatting.AQUA, ChatFormatting.RED, exception12.getMessage())
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text11);
                        return;
                     }
                  default:
                     TextComponent text9 = TextBridge.asAdventure(
                        MixinHelper.method1("failedUnknownMetaValue", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW, text6)
                     );
                     Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text9);
                     return;
               }
            }

            Component component15 = ((TextComponent)TextBridge.asAdventure(MixinHelper.method1("metaForCurrent", ChatFormatting.AQUA, ChatFormatting.GREEN))
                  .append(MixinHelper.method2(((RouteSection)optional4.get()).method7(), holograms3.getBlcID(), NamedTextColor.WHITE)))
               .append(Component.text(MixinHelper.method1("closeBracket2"), NamedTextColor.GREEN));
            boolean flag19 = ((RouteSection)optional4.get()).method7().method19().hidden;
            String text21 = MixinHelper.method1("enabled");
            String text22 = MixinHelper.method1("disabled");
            component15 = component15.append(
                  TextBridge.asAdventure(
                     MixinHelper.method1(
                        "hiddenColon", ChatFormatting.GRAY, ChatFormatting.YELLOW, flag19 ? ChatFormatting.GREEN : ChatFormatting.RED, flag19 ? text21 : text22
                     )
                  )
               )
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("toggle"), NamedTextColor.GRAY)
                        .hoverEvent(
                           HoverEvent.showText(
                              TextBridge.asAdventure(MixinHelper.method1("clickTo", flag19 ? MixinHelper.method1("disable") : MixinHelper.method1("enable")))
                           )
                        ))
                     .clickEvent(ClickEvent.runCommand("/route meta hidden " + (flag19 ? "disable" : "enable")))
               );
            String text27 = ((RouteSection)optional4.get()).method7().method19().swapOnComplete;
            component15 = component15.append(TextBridge.asAdventure(MixinHelper.method1("swapOnCompleteColon", ChatFormatting.GRAY, ChatFormatting.YELLOW)))
               .append(MixinHelper.method3(text27, holograms3))
               .appendSpace()
               .append(
                  ((TextComponent)Component.text("[EDIT]", NamedTextColor.GRAY)
                        .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToModify")))))
                     .clickEvent(ClickEvent.suggestCommand("/route meta swaponcomplete "))
               );
            if (text27 != null) {
               component15 = component15.appendSpace()
                  .append(
                     ((TextComponent)Component.text("[REMOVE]", NamedTextColor.GRAY)
                           .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                        .clickEvent(ClickEvent.runCommand("/route meta swaponcomplete null"))
                  );
            }

            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component15);
         }
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      if (items1.length == 2) {
         return Stream.of("hidden", "swaponcomplete");
      } else if (items1.length == 3 && items1[1].equalsIgnoreCase("hidden")) {
         return Stream.of("true", "false");
      } else {
         return items1.length == 3 && items1[1].equalsIgnoreCase("swaponcomplete") ? Stream.of("null") : Stream.empty();
      }
   }
}
