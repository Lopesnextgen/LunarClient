package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.stream.Stream;
import net.kyori.adventure.text.TextComponent;

public class MixinHelper107 implements MixinHelper2 {
   @Override
   public void method1(String[] var1) {
      String var2 = var1.length > 1 ? var1[1] : null;
      if ("meta".equalsIgnoreCase(var2)) {
         String var8 = var1.length > 2 ? var1[2] : null;
         if ("swaponcomplete".equalsIgnoreCase(var8)) {
            TextComponent var11 = AdventureTextBridge.asAdventure(
               MixinHelper.method1(
                  "helpMetaSwaponcomplete",
                  AdventureChatFormatting.AQUA,
                  AdventureChatFormatting.GREEN,
                  AdventureChatFormatting.YELLOW,
                  AdventureChatFormatting.GRAY,
                  ThreadModuleDump63.method7().bridge$getName()
               )
            );
            ThreadModuleDump63.method7().method1(var11);
         } else {
            TextComponent var10 = AdventureTextBridge.asAdventure(
               MixinHelper.method1("helpMeta", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, AdventureChatFormatting.YELLOW, AdventureChatFormatting.GRAY)
            );
            ThreadModuleDump63.method7().method1(var10);
         }
      } else if ("sectionmeta".equalsIgnoreCase(var2)) {
         String var7 = var1.length > 2 ? var1[2] : null;
         if ("swaponlocked".equalsIgnoreCase(var7)) {
            TextComponent var9 = AdventureTextBridge.asAdventure(
               MixinHelper.method1(
                  "helpSectionmetaSwaponlocked",
                  AdventureChatFormatting.AQUA,
                  AdventureChatFormatting.GREEN,
                  AdventureChatFormatting.YELLOW,
                  AdventureChatFormatting.GRAY,
                  ThreadModuleDump63.method7().bridge$getName()
               )
            );
            ThreadModuleDump63.method7().method1(var9);
         } else {
            TextComponent var4 = AdventureTextBridge.asAdventure(
               MixinHelper.method1("helpSectionmeta", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, AdventureChatFormatting.YELLOW, AdventureChatFormatting.GRAY)
            );
            ThreadModuleDump63.method7().method1(var4);
         }
      } else if ("whitelist".equalsIgnoreCase(var2)) {
         TextComponent var6 = AdventureTextBridge.asAdventure(
            MixinHelper.method1("helpWhitelist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, AdventureChatFormatting.YELLOW, AdventureChatFormatting.GRAY)
         );
         ThreadModuleDump63.method7().method1(var6);
      } else if ("blacklist".equalsIgnoreCase(var2)) {
         TextComponent var5 = AdventureTextBridge.asAdventure(
            MixinHelper.method1("helpBlacklist", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, AdventureChatFormatting.YELLOW, AdventureChatFormatting.GRAY)
         );
         ThreadModuleDump63.method7().method1(var5);
      } else {
         TextComponent var3 = AdventureTextBridge.asAdventure(
            MixinHelper.method1("help", AdventureChatFormatting.AQUA, AdventureChatFormatting.GREEN, AdventureChatFormatting.YELLOW, AdventureChatFormatting.GRAY)
         );
         ThreadModuleDump63.method7().method1(var3);
      }
   }

   @Override
   public Stream<String> method2(String[] var1) {
      if (var1.length == 2) {
         return Stream.of("meta", "sectionmeta", "whitelist", "blacklist");
      } else if (var1.length == 3 && var1[1].equalsIgnoreCase("meta")) {
         return Stream.of("swaponcomplete");
      } else {
         return var1.length == 3 && var1[1].equalsIgnoreCase("sectionmeta") ? Stream.of("swaponlocked") : Stream.empty();
      }
   }
}
