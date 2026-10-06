package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms10$Data;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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

public class MixinHelper8 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      Holograms var3 = var2.method18().orElse(null);
      Holograms3 var4 = var2.method17().orElse(null);
      if (var3 != null && var4 != null) {
         Optional var26 = var2.method25();
         if (var26.isEmpty()) {
            TextComponent var30 = AdventureTextBridge.asAdventure(
               MixinHelper.method1("failedNoRouteActive", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
            );
            ThreadModuleDump63.method7().method1(var30);
         } else {
            if (var1.length >= 2) {
               boolean var6 = var2.method6().anyMatch(var1x -> var1x == ((Holograms7)var26.get()).method7());
               if (!var6) {
                  TextComponent var32 = AdventureTextBridge.asAdventure(
                     MixinHelper.method1("failedToMetaRouteIsDefault", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                  );
                  ThreadModuleDump63.method7().method1(var32);
                  return;
               }

               String var7 = var1[1];
               label89:
               switch (var7) {
                  case "swaponlocked":
                     if (var1.length == 2) {
                        TextComponent var39 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToRunUsageSectionmetaSwaponlocked", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                        );
                        ThreadModuleDump63.method7().method1(var39);
                        return;
                     }

                     String var37 = var1[2];
                     if (var37.equals("null")) {
                        var37 = null;
                     } else {
                        var37 = MixinHelper.method5(var37);
                     }

                     try {
                        ((Holograms7)var26.get()).method6().swapOnLocked = var37;
                        var2.method20(var3.getBlcID(), ((Holograms7)var26.get()).method7());
                        var2.method10(((Holograms7)var26.get()).method7(), var3.getBlcID(), ((Holograms7)var26.get()).method7().method22());
                        var2.method26((Holograms7)var26.get());
                        Component var40 = AdventureTextBridge.asAdventure(
                              MixinHelper.method1("setRouteSectionmetaSwaponlocked", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN)
                           )
                           .append(MixinHelper.method3(var37, var3));
                        ThreadModuleDump63.method7().method1(var40);
                        break;
                     } catch (IOException var25) {
                        TextComponent var41 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToUpdateRoute", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, var25.getMessage())
                        );
                        ThreadModuleDump63.method7().method1(var41);
                        return;
                     }
                  case "hologram":
                     if (var1.length == 2) {
                        TextComponent var36 = AdventureTextBridge.asAdventure(
                           MixinHelper.method1("failedToRunUsageSectionmetaHologram", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                        );
                        ThreadModuleDump63.method7().method1(var36);
                        return;
                     }

                     String var35 = var1[2];
                     switch (var35) {
                        case "add":
                           if (var1.length == 3) {
                              TextComponent var45 = AdventureTextBridge.asAdventure(
                                 MixinHelper.method1(
                                    "failedToRunUsageSectionmetaHologramAdd", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW
                                 )
                              );
                              ThreadModuleDump63.method7().method1(var45);
                              return;
                           }

                           Bridge5Extension_5 var44 = ThreadModuleDump63.method7();
                           String var46 = Arrays.stream(var1).skip(3L).collect(Collectors.joining(" "));
                           int var48 = (int)Math.floor(var44.bridge$getPosX());
                           int var49 = (int)Math.floor(var44.bridge$getPosY());
                           int var50 = (int)Math.floor(var44.bridge$getPosZ());
                           List var18 = ((Holograms7)var26.get()).method6().getTextHolograms();
                           int[] var19 = var4.method23(var48, var49, var50);
                           var18.add(
                              new Holograms10$Data(AdventureChatFormatting.getTextWithFormattingCodesFromAmpersand(var46), new Vector3d(var19[0], var19[1], var19[2]))
                           );

                           try {
                              var2.method20(var3.getBlcID(), ((Holograms7)var26.get()).method7());
                              var2.method10(((Holograms7)var26.get()).method7(), var3.getBlcID(), ((Holograms7)var26.get()).method7().method22());
                              var2.method26((Holograms7)var26.get());
                              TextComponent var20 = AdventureTextBridge.asAdventure(MixinHelper.method1("addedNewHologram", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
                              Component var51 = var20.append(MixinHelper.method4((Holograms10$Data)var18.get(var18.size() - 1)));
                              ThreadModuleDump63.method7().method1(var51);
                              break label89;
                           } catch (IOException var24) {
                              TextComponent var21 = AdventureTextBridge.asAdventure(
                                 MixinHelper.method1("failedToUpdateRoute", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, var24.getMessage())
                              );
                              ThreadModuleDump63.method7().method1(var21);
                              return;
                           }
                        case "remove":
                           if (var1.length == 3) {
                              TextComponent var43 = AdventureTextBridge.asAdventure(
                                 MixinHelper.method1(
                                    "failedToRunCommandUsageSectionmetaHologramRemove", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW
                                 )
                              );
                              ThreadModuleDump63.method7().method1(var43);
                              return;
                           }

                           String var42 = var1[3];

                           try {
                              int var14 = Integer.parseInt(var42);
                              Holograms10$Data var47 = ((Holograms7)var26.get()).method6().getTextHolograms().remove(var14);

                              try {
                                 var2.method20(var3.getBlcID(), ((Holograms7)var26.get()).method7());
                                 var2.method10(((Holograms7)var26.get()).method7(), var3.getBlcID(), ((Holograms7)var26.get()).method7().method22());
                                 var2.method26((Holograms7)var26.get());
                                 Component var16 = AdventureTextBridge.asAdventure(MixinHelper.method1("removedHologram", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN))
                                    .append(MixinHelper.method4(var47));
                                 ThreadModuleDump63.method7().method1(var16);
                                 break label89;
                              } catch (IOException var22) {
                                 TextComponent var17 = AdventureTextBridge.asAdventure(
                                    MixinHelper.method1("failedToUpdateRoute", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, var22.getMessage())
                                 );
                                 ThreadModuleDump63.method7().method1(var17);
                                 return;
                              }
                           } catch (NumberFormatException var23) {
                              TextComponent var15 = AdventureTextBridge.asAdventure(
                                 MixinHelper.method1("failedToRunCommandIdMustBeNumber", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED)
                              );
                              ThreadModuleDump63.method7().method1(var15);
                              return;
                           }
                        default:
                           TextComponent var13 = AdventureTextBridge.asAdventure(
                              MixinHelper.method1("failedToRunUsageSectionmetaHologram", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW)
                           );
                           ThreadModuleDump63.method7().method1(var13);
                           return;
                     }
                  default:
                     TextComponent var10 = AdventureTextBridge.asAdventure(
                        MixinHelper.method1("failedUnknownMetaValue", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED, AdventureChatFormatting.YELLOW, var7)
                     );
                     ThreadModuleDump63.method7().method1(var10);
                     return;
               }
            }

            Component var27 = ((TextComponent)((TextComponent)((TextComponent)AdventureTextBridge.asAdventure(
                           MixinHelper.method1("metaForCurrentSection", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN)
                        )
                        .append(MixinHelper.method2(((Holograms7)var26.get()).method7(), var3.getBlcID(), NamedTextColor.WHITE)))
                     .append(Component.text(":", NamedTextColor.GRAY)))
                  .append(Component.text(((Holograms7)var26.get()).getIndex(), NamedTextColor.WHITE)))
               .append(Component.text(")", NamedTextColor.GREEN));
            String var31 = ((Holograms7)var26.get()).method6().swapOnLocked;
            var27 = var27.append(AdventureTextBridge.asAdventure(MixinHelper.method1("swapOnLocked", AdventureChatFormatting.GRAY, AdventureChatFormatting.YELLOW)))
               .append(MixinHelper.method3(var31, var3))
               .appendSpace()
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("edit"), NamedTextColor.GRAY)
                        .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToModify")))))
                     .clickEvent(ClickEvent.suggestCommand("/route sectionmeta swaponlocked "))
               );
            if (var31 != null) {
               var27 = var27.appendSpace()
                  .append(
                     ((TextComponent)Component.text(MixinHelper.method1("remove"), NamedTextColor.GRAY)
                           .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                        .clickEvent(ClickEvent.runCommand("/route sectionmeta swaponlocked null"))
                  );
            }

            List var33 = ((Holograms7)var26.get()).method6().getTextHolograms();
            var27 = var27.append(AdventureTextBridge.asAdventure(MixinHelper.method1("holograms", AdventureChatFormatting.GRAY, AdventureChatFormatting.YELLOW)))
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("add"), NamedTextColor.GRAY)
                        .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToAdd")))))
                     .clickEvent(ClickEvent.suggestCommand("/route sectionmeta hologram add "))
               );

            for (int var34 = 0; var34 < var33.size(); var34++) {
               var27 = var27.append(Component.text(String.format("\n    %d: ", var34), NamedTextColor.GRAY))
                  .append(MixinHelper.method4((Holograms10$Data)var33.get(var34)))
                  .appendSpace()
                  .append(
                     ((TextComponent)Component.text(MixinHelper.method1("remove"), NamedTextColor.GRAY)
                           .hoverEvent(HoverEvent.showText(AdventureTextBridge.asAdventure(MixinHelper.method1("clickToRemove")))))
                        .clickEvent(ClickEvent.runCommand("/route sectionmeta hologram remove " + var34))
                  );
            }

            ThreadModuleDump63.method7().method1(var27);
         }
      } else {
         TextComponent var5 = AdventureTextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", AdventureChatFormatting.AQUA, AdventureChatFormatting.RED));
         ThreadModuleDump63.method7().method1(var5);
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      if (var1.length == 2) {
         return Stream.of("swaponlocked", "hologram");
      } else if (var1.length == 3 && var1[1].equalsIgnoreCase("swaponlocked")) {
         return Stream.of("null");
      } else {
         return var1.length == 3 && var1[1].equalsIgnoreCase("hologram") ? Stream.of("add", "remove") : Stream.empty();
      }
   }
}
