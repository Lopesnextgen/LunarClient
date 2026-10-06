package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.TextHologram;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3d;

public class RouteSectionMetaSubcommand implements RouteSubcommand {
   public RouteSectionMetaSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
      RoomInstance holograms34 = holograms3_32.method17().orElse(null);
      if (holograms3 != null && holograms34 != null) {
         Optional optional26 = holograms3_32.method25();
         if (optional26.isEmpty()) {
            TextComponent text30 = TextBridge.asAdventure(
               MixinHelper.method1("failedNoRouteActive", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text30);
         } else {
            if (items1.length >= 2) {
               boolean flag6 = holograms3_32.method6().anyMatch(arg1x -> arg1x == ((RouteSection)optional26.get()).method7());
               if (!flag6) {
                  TextComponent text32 = TextBridge.asAdventure(
                     MixinHelper.method1("failedToMetaRouteIsDefault", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                  );
                  Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text32);
                  return;
               }

               String text7 = items1[1];
               label89:
               switch (text7) {
                  case "swaponlocked":
                     if (items1.length == 2) {
                        TextComponent text39 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToRunUsageSectionmetaSwaponlocked", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text39);
                        return;
                     }

                     String text37 = items1[2];
                     if (text37.equals("null")) {
                        text37 = null;
                     } else {
                        text37 = MixinHelper.method5(text37);
                     }

                     try {
                        ((RouteSection)optional26.get()).method6().swapOnLocked = text37;
                        holograms3_32.method20(holograms3.getBlcID(), ((RouteSection)optional26.get()).method7());
                        holograms3_32.method10(((RouteSection)optional26.get()).method7(), holograms3.getBlcID(), ((RouteSection)optional26.get()).method7().method22());
                        holograms3_32.method26((RouteSection)optional26.get());
                        Component component40 = TextBridge.asAdventure(
                              MixinHelper.method1("setRouteSectionmetaSwaponlocked", ChatFormatting.AQUA, ChatFormatting.GREEN)
                           )
                           .append(MixinHelper.method3(text37, holograms3));
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component40);
                        break;
                     } catch (IOException exception25) {
                        TextComponent text41 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToUpdateRoute", ChatFormatting.AQUA, ChatFormatting.RED, exception25.getMessage())
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text41);
                        return;
                     }
                  case "hologram":
                     if (items1.length == 2) {
                        TextComponent text36 = TextBridge.asAdventure(
                           MixinHelper.method1("failedToRunUsageSectionmetaHologram", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                        );
                        Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text36);
                        return;
                     }

                     String text35 = items1[2];
                     switch (text35) {
                        case "add":
                           if (items1.length == 3) {
                              TextComponent text45 = TextBridge.asAdventure(
                                 MixinHelper.method1(
                                    "failedToRunUsageSectionmetaHologramAdd", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW
                                 )
                              );
                              Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text45);
                              return;
                           }

                           Bridge5Extension_5 bridge5extension_544 = Ref.method7();
                           String text46 = Arrays.stream(items1).skip(3L).collect(Collectors.joining(" "));
                           int number48 = (int)Math.floor(bridge5extension_544.bridge$getPosX());
                           int number49 = (int)Math.floor(bridge5extension_544.bridge$getPosY());
                           int number50 = (int)Math.floor(bridge5extension_544.bridge$getPosZ());
                           List list18 = ((RouteSection)optional26.get()).method6().getTextHolograms();
                           int[] items19 = holograms34.method23(number48, number49, number50);
                           list18.add(
                              new TextHologram(ChatFormatting.getTextWithFormattingCodesFromAmpersand(text46), new Vector3d(items19[0], items19[1], items19[2]))
                           );

                           try {
                              holograms3_32.method20(holograms3.getBlcID(), ((RouteSection)optional26.get()).method7());
                              holograms3_32.method10(((RouteSection)optional26.get()).method7(), holograms3.getBlcID(), ((RouteSection)optional26.get()).method7().method22());
                              holograms3_32.method26((RouteSection)optional26.get());
                              TextComponent text20 = TextBridge.asAdventure(MixinHelper.method1("addedNewHologram", ChatFormatting.AQUA, ChatFormatting.GREEN));
                              Component component51 = text20.append(MixinHelper.method4((TextHologram)list18.get(list18.size() - 1)));
                              Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component51);
                              break label89;
                           } catch (IOException exception24) {
                              TextComponent text21 = TextBridge.asAdventure(
                                 MixinHelper.method1("failedToUpdateRoute", ChatFormatting.AQUA, ChatFormatting.RED, exception24.getMessage())
                              );
                              Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text21);
                              return;
                           }
                        case "remove":
                           if (items1.length == 3) {
                              TextComponent text43 = TextBridge.asAdventure(
                                 MixinHelper.method1(
                                    "failedToRunCommandUsageSectionmetaHologramRemove", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW
                                 )
                              );
                              Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text43);
                              return;
                           }

                           String text42 = items1[3];

                           try {
                              int index14 = Integer.parseInt(text42);
                              TextHologram holograms10$data47 = ((RouteSection)optional26.get()).method6().getTextHolograms().remove(index14);

                              try {
                                 holograms3_32.method20(holograms3.getBlcID(), ((RouteSection)optional26.get()).method7());
                                 holograms3_32.method10(((RouteSection)optional26.get()).method7(), holograms3.getBlcID(), ((RouteSection)optional26.get()).method7().method22());
                                 holograms3_32.method26((RouteSection)optional26.get());
                                 Component component16 = TextBridge.asAdventure(MixinHelper.method1("removedHologram", ChatFormatting.AQUA, ChatFormatting.GREEN))
                                    .append(MixinHelper.method4(holograms10$data47));
                                 Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component16);
                                 break label89;
                              } catch (IOException exception22) {
                                 TextComponent text17 = TextBridge.asAdventure(
                                    MixinHelper.method1("failedToUpdateRoute", ChatFormatting.AQUA, ChatFormatting.RED, exception22.getMessage())
                                 );
                                 Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text17);
                                 return;
                              }
                           } catch (NumberFormatException numberformatexception23) {
                              TextComponent text15 = TextBridge.asAdventure(
                                 MixinHelper.method1("failedToRunCommandIdMustBeNumber", ChatFormatting.AQUA, ChatFormatting.RED)
                              );
                              Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text15);
                              return;
                           }
                        default:
                           TextComponent text13 = TextBridge.asAdventure(
                              MixinHelper.method1("failedToRunUsageSectionmetaHologram", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW)
                           );
                           Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text13);
                           return;
                     }
                  default:
                     TextComponent text10 = TextBridge.asAdventure(
                        MixinHelper.method1("failedUnknownMetaValue", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW, text7)
                     );
                     Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text10);
                     return;
               }
            }

            Component component27 = ((TextComponent)((TextComponent)((TextComponent)TextBridge.asAdventure(
                           MixinHelper.method1("metaForCurrentSection", ChatFormatting.AQUA, ChatFormatting.GREEN)
                        )
                        .append(MixinHelper.method2(((RouteSection)optional26.get()).method7(), holograms3.getBlcID(), NamedTextColor.WHITE)))
                     .append(Component.text(":", NamedTextColor.GRAY)))
                  .append(Component.text(((RouteSection)optional26.get()).getIndex(), NamedTextColor.WHITE)))
               .append(Component.text(")", NamedTextColor.GREEN));
            String text31 = ((RouteSection)optional26.get()).method6().swapOnLocked;
            component27 = component27.append(TextBridge.asAdventure(MixinHelper.method1("swapOnLocked", ChatFormatting.GRAY, ChatFormatting.YELLOW)))
               .append(MixinHelper.method3(text31, holograms3))
               .appendSpace()
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("edit"), NamedTextColor.GRAY)
                        .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToModify")))))
                     .clickEvent(ClickEvent.suggestCommand("/route sectionmeta swaponlocked "))
               );
            if (text31 != null) {
               component27 = component27.appendSpace()
                  .append(
                     ((TextComponent)Component.text(MixinHelper.method1("remove"), NamedTextColor.GRAY)
                           .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                        .clickEvent(ClickEvent.runCommand("/route sectionmeta swaponlocked null"))
                  );
            }

            List list33 = ((RouteSection)optional26.get()).method6().getTextHolograms();
            component27 = component27.append(TextBridge.asAdventure(MixinHelper.method1("holograms", ChatFormatting.GRAY, ChatFormatting.YELLOW)))
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("add"), NamedTextColor.GRAY)
                        .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                     .clickEvent(ClickEvent.suggestCommand("/route sectionmeta hologram add "))
               );

            for (int index34 = 0; index34 < list33.size(); index34++) {
               component27 = component27.append(Component.text(String.format("\n    %d: ", index34), NamedTextColor.GRAY))
                  .append(MixinHelper.method4((TextHologram)list33.get(index34)))
                  .appendSpace()
                  .append(
                     ((TextComponent)Component.text(MixinHelper.method1("remove"), NamedTextColor.GRAY)
                           .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                        .clickEvent(ClickEvent.runCommand("/route sectionmeta hologram remove " + index34))
                  );
            }

            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component27);
         }
      } else {
         TextComponent text5 = TextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", ChatFormatting.AQUA, ChatFormatting.RED));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text5);
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      if (items1.length == 2) {
         return Stream.of("swaponlocked", "hologram");
      } else if (items1.length == 3 && items1[1].equalsIgnoreCase("swaponlocked")) {
         return Stream.of("null");
      } else {
         return items1.length == 3 && items1[1].equalsIgnoreCase("hologram") ? Stream.of("add", "remove") : Stream.empty();
      }
   }
}
