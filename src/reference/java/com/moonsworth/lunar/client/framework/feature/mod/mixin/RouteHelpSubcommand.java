package com.moonsworth.lunar.client.framework.feature.mod.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.stream.Stream;
import net.kyori.adventure.text.TextComponent;

public class RouteHelpSubcommand implements RouteSubcommand {
   public RouteHelpSubcommand() {
   }

   @Override
   public void method1(String[] items1) {
      String text2 = items1.length > 1 ? items1[1] : null;
      if ("meta".equalsIgnoreCase(text2)) {
         String text8 = items1.length > 2 ? items1[2] : null;
         if ("swaponcomplete".equalsIgnoreCase(text8)) {
            TextComponent text11 = TextBridge.asAdventure(
               MixinHelper.method1(
                  "helpMetaSwaponcomplete",
                  ChatFormatting.AQUA,
                  ChatFormatting.GREEN,
                  ChatFormatting.YELLOW,
                  ChatFormatting.GRAY,
                  Ref.method7().bridge$getName()
               )
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text11);
         } else {
            TextComponent text10 = TextBridge.asAdventure(
               MixinHelper.method1("helpMeta", ChatFormatting.AQUA, ChatFormatting.GREEN, ChatFormatting.YELLOW, ChatFormatting.GRAY)
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text10);
         }
      } else if ("sectionmeta".equalsIgnoreCase(text2)) {
         String text7 = items1.length > 2 ? items1[2] : null;
         if ("swaponlocked".equalsIgnoreCase(text7)) {
            TextComponent text9 = TextBridge.asAdventure(
               MixinHelper.method1(
                  "helpSectionmetaSwaponlocked",
                  ChatFormatting.AQUA,
                  ChatFormatting.GREEN,
                  ChatFormatting.YELLOW,
                  ChatFormatting.GRAY,
                  Ref.method7().bridge$getName()
               )
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text9);
         } else {
            TextComponent text4 = TextBridge.asAdventure(
               MixinHelper.method1("helpSectionmeta", ChatFormatting.AQUA, ChatFormatting.GREEN, ChatFormatting.YELLOW, ChatFormatting.GRAY)
            );
            Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text4);
         }
      } else if ("whitelist".equalsIgnoreCase(text2)) {
         TextComponent text6 = TextBridge.asAdventure(
            MixinHelper.method1("helpWhitelist", ChatFormatting.AQUA, ChatFormatting.GREEN, ChatFormatting.YELLOW, ChatFormatting.GRAY)
         );
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text6);
      } else if ("blacklist".equalsIgnoreCase(text2)) {
         TextComponent text5 = TextBridge.asAdventure(
            MixinHelper.method1("helpBlacklist", ChatFormatting.AQUA, ChatFormatting.GREEN, ChatFormatting.YELLOW, ChatFormatting.GRAY)
         );
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text5);
      } else {
         TextComponent text3 = TextBridge.asAdventure(
            MixinHelper.method1("help", ChatFormatting.AQUA, ChatFormatting.GREEN, ChatFormatting.YELLOW, ChatFormatting.GRAY)
         );
         Ref.method7().HRICOROOOCCOCOROCRHHCRRIRCOICO(text3);
      }
   }

   @Override
   public Stream<String> method2(String[] items1) {
      if (items1.length == 2) {
         return Stream.of("meta", "sectionmeta", "whitelist", "blacklist");
      } else if (items1.length == 3 && items1[1].equalsIgnoreCase("meta")) {
         return Stream.of("swaponcomplete");
      } else {
         return items1.length == 3 && items1[1].equalsIgnoreCase("sectionmeta") ? Stream.of("swaponlocked") : Stream.empty();
      }
   }
}
