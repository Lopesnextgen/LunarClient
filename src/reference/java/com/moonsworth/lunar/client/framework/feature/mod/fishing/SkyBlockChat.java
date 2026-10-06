package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiNewChatBridge;
import com.moonsworth.lunar.bridge.GuiIngameBridge;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public final class SkyBlockChat {
   private static final TextComponent field1 = (TextComponent)((TextComponent)Component.text("[", NamedTextColor.WHITE)
         .append(Component.text("LC", NamedTextColor.AQUA)))
      .append(Component.text("] ", NamedTextColor.WHITE));

   public static void method1(String text0) {
      sendMessage(Component.text(text0, NamedTextColor.GOLD));
   }

   public static Component method2(Component component0, String text2) {
      return component0.append(Component.text(" ")).append(field1).append(Component.text(text2, NamedTextColor.GOLD));
   }

   public static void sendMessage(Component component0) {
      method3(field1.append(component0));
   }

   public static void method3(Component component0) {
      GuiIngameBridge bridge5extension91 = Ref.method3().bridge$getGuiIngame();
      if (bridge5extension91 != null) {
         GuiNewChatBridge bridge5extension42 = bridge5extension91.bridge$getChatGUI();
         if (bridge5extension42 != null) {
            bridge5extension42.bridge$addMessage(TextBridge.asBridge(component0));
         }
      }
   }

   public static void method4(String text0, boolean flag) {
      if (flag) {
         Ref.method4().method69().method2("SkyBlock Mod", "Feature " + text0 + " has been toggled [ON]");
      } else {
         Ref.method4().method69().method2("SkyBlock Mod", "Feature " + text0 + " has been toggled [OFF]");
      }
   }

   @Generated
   private SkyBlockChat() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
