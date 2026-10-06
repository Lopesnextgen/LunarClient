package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.stream.Stream;
import net.kyori.adventure.text.TextComponent;

public class RouteStartSubcommand implements RouteSubcommand {
   public RouteStartSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      TextComponent text2 = TextBridge.asAdventure(MixinHelper.method1("pleaseDontUseSpiritScepter", ChatFormatting.AQUA, ChatFormatting.YELLOW));
      RouteManager holograms3_33 = SkyblockDungeonRoutes.method13().method16();
      if (holograms3_33.method27().method10()) {
         holograms3_33.method27().method1();
         holograms3_33.method15();
         holograms3_33.method27().startRecording();
         TextComponent text5 = TextBridge.asAdventure(MixinHelper.method1("resetRecording", ChatFormatting.AQUA, ChatFormatting.GREEN));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text5);
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text2);
      } else {
         holograms3_33.method27().startRecording();
         holograms3_33.method15();
         TextComponent text4 = TextBridge.asAdventure(MixinHelper.method1("freeToStartRecording", ChatFormatting.AQUA, ChatFormatting.GREEN));
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text4);
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text2);
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return Stream.empty();
   }
}
