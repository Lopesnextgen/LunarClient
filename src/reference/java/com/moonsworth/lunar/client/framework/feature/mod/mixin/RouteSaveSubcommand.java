package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonRoute;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class RouteSaveSubcommand implements RouteSubcommand {
   public RouteSaveSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      if (items1.length == 1) {
         TextComponent text11 = TextBridge.asAdventure(MixinHelper.method1("failedToSaveNoName", ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.YELLOW));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text11);
      } else {
         RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
         DungeonRoom holograms3 = holograms3_32.method18().orElse(null);
         if (holograms3 == null) {
            TextComponent text12 = TextBridge.asAdventure(MixinHelper.method1("failedToSaveIdUnknown", ChatFormatting.AQUA, ChatFormatting.RED));
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text12);
         } else {
            Bridge5Extension_5 bridge5extension_54 = Ref.method7();
            String text5 = bridge5extension_54.bridge$getName() + "-" + items1[1];
            String text6 = holograms3.getBlcID();

            Optional optional7;
            try {
               optional7 = holograms3_32.method27().method2(text6, text5);
            } catch (IOException exception10) {
               TextComponent text9 = TextBridge.asAdventure(
                  MixinHelper.method1("failedToSaveUnknown", ChatFormatting.AQUA, ChatFormatting.RED, exception10.getMessage())
               );
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text9);
               return;
            }

            if (optional7.isEmpty()) {
               TextComponent text13 = TextBridge.asAdventure(MixinHelper.method1("failedToSaveNoRecording", ChatFormatting.AQUA, ChatFormatting.RED));
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text13);
            } else {
               holograms3_32.method15();
               DungeonRoute holograms28 = (DungeonRoute)optional7.get();
               Component component14 = TextBridge.asAdventure(MixinHelper.method1("successfullySaved", ChatFormatting.AQUA, ChatFormatting.GREEN))
                  .append(MixinHelper.method2(holograms28, text6, NamedTextColor.GRAY));
               Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(component14);
            }
         }
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return Stream.empty();
   }
}
