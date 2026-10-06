package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.lighting.Lighting;
import com.moonsworth.lunar.bridge.lighting.Lighting3;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.config.Config;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public final class ThreadModuleDump3 {
   private static final Pattern field1 = Pattern.compile("(.+) (?:<- .+)?");
   private static String server;

   public static void method1() {
      if (ThreadModuleDump63.method7() == null || ThreadModuleDump63.method3().bridge$getCurrentServerData() == null) {
         server = null;
      } else if (!Highlight3Iterator.method8(KeystrokesType.HYPIXEL)) {
         server = null;
      } else {
         String var0 = method3();
         if (var0 == null) {
            server = null;
         } else {
            server = WordUtils.capitalize(method2(var0).toLowerCase(), null);
         }
      }
   }

   public static String method2(String var0) {
      var0 = AdventureTextBridge.stripColor(var0);
      return var0.equalsIgnoreCase("skyblock co-op") ? "SKYBLOCK" : var0;
   }

   private static String method3() {
      Lighting4 var0 = ThreadModuleDump63.method3().bridge$getWorld().bridge$getScoreboard();
      Lighting var1 = null;
      Lighting3 var2 = var0.bridge$getPlayersTeam(ThreadModuleDump63.method3().bridge$getPlayer().bridge$getName());
      if (var2 != null) {
         int var3 = var2.bridge$getChatFormat().getColorIndex();
         if (Bridge.getMinecraftVersion() == Config.field1) {
            var1 = var0.bridge$getObjectiveInDisplaySlot(1);
         } else if (var3 >= 0) {
            try {
               var1 = var0.bridge$getObjectiveInDisplaySlot(3 + var3);
            } catch (ArrayIndexOutOfBoundsException var5) {
            }
         }
      }

      Lighting var6 = var1 != null ? var1 : var0.bridge$getObjectiveInDisplaySlot(1);
      return var6 == null ? null : AdventureTextBridge.getTextContent(AdventureTextBridge.asAdventure(var6.bridge$getDisplayName()));
   }

   public static boolean method4(String var0) {
      return server != null && server.equalsIgnoreCase(var0);
   }

   public static boolean method5(String var0) {
      if (ThreadModuleDump63.method3().bridge$getPlayer() != null && ThreadModuleDump63.method3().bridge$getPlayer().bridge$getClientBrand().isPresent()) {
         String var1 = (String)ThreadModuleDump63.method3().bridge$getPlayer().bridge$getClientBrand().get();
         if (Bridge.getMinecraftVersion().method19()) {
            return var1.startsWith(var0);
         }

         Matcher var2 = field1.matcher(var1);
         if (var2.find()) {
            return var2.group(1).startsWith(var0);
         }
      }

      return false;
   }

   @Generated
   private ThreadModuleDump3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static String getServer() {
      return server;
   }
}
