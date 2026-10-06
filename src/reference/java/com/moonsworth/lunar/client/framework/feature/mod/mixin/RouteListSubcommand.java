package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonRoute;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class RouteListSubcommand implements RouteSubcommand {
   public RouteListSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
      if (holograms3 == null) {
         TextComponent text10 = TextBridge.asAdventure(MixinHelper.method1("currentRoomIdUnknown", ChatFormatting.AQUA, ChatFormatting.RED));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text10);
      } else {
         Component component4 = TextBridge.asAdventure(MixinHelper.method1("routesInCurrentRoom", ChatFormatting.AQUA, ChatFormatting.GREEN));

         for (DungeonRoute holograms26 : holograms3_32.method5(true).toList()) {
            component4 = component4.append(TextBridge.asAdventure(MixinHelper.method1("dash", ChatFormatting.GRAY)));
            boolean flag7 = holograms3_32.method6().anyMatch(arg1x -> arg1x == holograms26);
            boolean flag8 = false;
            if (!flag7) {
               component4 = component4.append(
                  Component.text("[B]", NamedTextColor.YELLOW).hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("builtin"))))
               );
               flag8 = true;
            }

            if (holograms26.method19().hidden) {
               component4 = component4.append(
                  Component.text("[H]", NamedTextColor.YELLOW).hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("hidden"))))
               );
               flag8 = true;
            }

            if (flag8) {
               component4 = component4.appendSpace();
            }

            component4 = component4.append(MixinHelper.method2(holograms26, holograms3.getBlcID(), NamedTextColor.GREEN))
               .appendSpace()
               .append(
                  ((TextComponent)Component.text(MixinHelper.method1("select"), NamedTextColor.YELLOW)
                        .hoverEvent(HoverEvent.showText(TextBridge.asAdventure(MixinHelper.method1("clickToSelect")))))
                     .clickEvent(ClickEvent.runCommand("/route select " + holograms26.method22()))
               );
         }

         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component4);
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return Stream.empty();
   }
}
