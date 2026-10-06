package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_3;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.stream.Stream;
import net.kyori.adventure.text.TextComponent;

public class MixinHelper105 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      TextComponent var2 = AdventureTextBridge.asAdventure(MixinHelper.method1("pleaseDontUseSpiritScepter", AdventureChatFormatting.AQUA, AdventureChatFormatting.YELLOW));
      Holograms3_3 var3 = SkyblockDungeonRoutes.method13().method16();
      if (var3.method27().method10()) {
         var3.method27().method1();
         var3.method15();
         var3.method27().startRecording();
         TextComponent var5 = AdventureTextBridge.asAdventure(MixinHelper.method1("resetRecording", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
         ThreadModuleDump63.method7().method1(var5);
         ThreadModuleDump63.method7().method1(var2);
      } else {
         var3.method27().startRecording();
         var3.method15();
         TextComponent var4 = AdventureTextBridge.asAdventure(MixinHelper.method1("freeToStartRecording", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN));
         ThreadModuleDump63.method7().method1(var4);
         ThreadModuleDump63.method7().method1(var2);
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      return Stream.empty();
   }
}
