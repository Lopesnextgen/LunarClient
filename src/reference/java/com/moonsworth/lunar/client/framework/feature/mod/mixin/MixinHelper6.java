package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.stream.Stream;
import net.kyori.adventure.text.TextComponent;

public class MixinHelper6 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      Holograms3_3 var2 = SkyblockDungeonRoutes.method13().method16();
      var2.method15();
      TextComponent var3 = AdventureTextBridge.asAdventure(MixinHelper.method1("resetProgressForCurrent", AdventureChatFormatting.AQUA, AdventureChatFormatting.YELLOW));
      ThreadModuleDump63.method7().method1(var3);
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return Stream.empty();
   }
}
