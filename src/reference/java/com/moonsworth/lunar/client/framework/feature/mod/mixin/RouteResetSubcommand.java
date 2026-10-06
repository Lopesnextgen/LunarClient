package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.stream.Stream;
import net.kyori.adventure.text.TextComponent;

public class RouteResetSubcommand implements RouteSubcommand {
   public RouteResetSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      RouteManager holograms3_32 = SkyblockDungeonRoutes.method13().method16();
      holograms3_32.method15();
      TextComponent text3 = TextBridge.asAdventure(MixinHelper.method1("resetProgressForCurrent", ChatFormatting.AQUA, ChatFormatting.YELLOW));
      Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text3);
   }

   @Override
   public Stream<String> method2(String[] items1) {
      return Stream.empty();
   }
}
