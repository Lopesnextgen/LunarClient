package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public final class Fishing2_2 {
   private static final TextComponent field1 = (TextComponent)((TextComponent)Component.text("[", NamedTextColor.WHITE)
         .append(Component.text("LC", NamedTextColor.AQUA)))
      .append(Component.text("] ", NamedTextColor.WHITE));

   public static void method1(String var0) {
      sendMessage(Component.text(var0, NamedTextColor.GOLD));
   }

   public static Component method2(Component var0, String var1) {
      return var0.append(Component.text(" ")).append(field1).append(Component.text(var1, NamedTextColor.GOLD));
   }

   public static void sendMessage(Component var0) {
      method3(field1.append(var0));
   }

   public static void method3(Component var0) {
      Bridge5Extension9 var1 = ThreadModuleDump63.method3().bridge$getGuiIngame();
      if (var1 != null) {
         Bridge5Extension4 var2 = var1.bridge$getChatGUI();
         if (var2 != null) {
            var2.bridge$addMessage(AdventureTextBridge.asBridge(var0));
         }
      }
   }

   public static void method4(String var0, boolean var1) {
      if (var1) {
         ThreadModuleDump63.method4().method69().method2("SkyBlock Mod", "Feature " + var0 + " has been toggled [ON]");
      } else {
         ThreadModuleDump63.method4().method69().method2("SkyBlock Mod", "Feature " + var0 + " has been toggled [OFF]");
      }
   }

   @Generated
   private Fishing2_2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
