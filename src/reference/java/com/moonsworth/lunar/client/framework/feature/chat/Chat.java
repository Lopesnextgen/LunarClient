package com.moonsworth.lunar.client.framework.feature.chat;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jspecify.annotations.Nullable;

public class Chat {
   private static final Pattern field1 = Pattern.compile("[A-Za-z0-9_]{3,16}");
   private static final String field2 = "»›:>";
   private static final String field3 = "»›>";
   private static final int field4 = 40;
   private static final int field5 = 1200;
   private static final Set<String> field6 = Set.of(
      "reminder",
      "warning",
      "notice",
      "alert",
      "info",
      "note",
      "tip",
      "error",
      "score",
      "winner",
      "winners",
      "loser",
      "losers",
      "vote",
      "store",
      "shop",
      "discord",
      "website",
      "server",
      "status",
      "online",
      "offline",
      "total",
      "members",
      "team",
      "guild",
      "party",
      "level",
      "rank"
   );
   private final Int2ObjectLinkedOpenHashMap<Bridge2_33> field7 = new Int2ObjectLinkedOpenHashMap();

   public void method1(Data var1) {
      Bridge2_33 var2 = this.method2(var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
      if (var2 != null) {
         this.field7.put(var1.method1(), var2);

         while (this.field7.size() > 1200) {
            this.field7.remove(this.field7.firstIntKey());
         }
      }
   }

   public @Nullable Bridge2_33 method2(String var1) {
      ClientPacketListenerBridge var2 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
      if (var2 == null) {
         return null;
      }

      List var3 = var2.bridge$getPlayerInfoMap();
      if (var3 != null && !var3.isEmpty()) {
         HashMap var4 = new HashMap(var3.size());

         for (Bridge2_33 var6 : var3) {
            GameProfile var7 = var6.bridge$getGameProfile();
            if (var7 != null && var7.getName() != null) {
               var4.put(var7.getName().toLowerCase(Locale.ROOT), var6);
            }
         }

         String var8 = this.method3(var1, var4.keySet());
         return var8 == null ? null : (Bridge2_33)var4.get(var8.toLowerCase(Locale.ROOT));
      } else {
         return null;
      }
   }

   public @Nullable String method3(String var1, Set<String> var2) {
      Matcher var3 = field1.matcher(var1);

      while (var3.find()) {
         if (var3.start() > 40) {
            return null;
         }

         if (method6(var1, var3) && var2.contains(var3.group().toLowerCase(Locale.ROOT))) {
            return var3.group();
         }
      }

      return null;
   }

   public @Nullable String method4(String var1) {
      Matcher var2 = field1.matcher(var1);

      while (var2.find()) {
         if (var2.start() > 40) {
            return null;
         }

         if (method8(var1, var2)) {
            return var2.group();
         }

         char var3 = method7(var1, var2);
         if (var3 != 0 && method9(var1, var2, var3)) {
            return var2.group();
         }
      }

      return null;
   }

   public @Nullable ResourceLocationBridge method5(int var1) {
      Bridge2_33 var2 = (Bridge2_33)this.field7.get(var1);
      if (var2 == null) {
         return null;
      }

      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return var2.bridge$getLocationSkin();
      }

      GameProfile var3 = var2.bridge$getGameProfile();
      return var3 == null ? null : (ResourceLocationBridge)ThreadModuleDump63.method3().bridge$getSkinManager().bridge$getSkinLocation(var3, Type.SKIN).orElse(null);
   }

   private static boolean method6(String var0, Matcher var1) {
      return method8(var0, var1) || method7(var0, var1) != 0;
   }

   private static char method7(String var0, Matcher var1) {
      int var2 = method13(var0, var1.end());
      if (var2 < var0.length() && var0.charAt(var2) == '[') {
         int var3 = var0.indexOf(93, var2);
         if (var3 == -1) {
            return '\u0000';
         }

         var2 = method13(var0, var3 + 1);
      }

      return var2 < var0.length() && "»›:>".indexOf(var0.charAt(var2)) != -1 ? var0.charAt(var2) : '\u0000';
   }

   private static boolean method8(String var0, Matcher var1) {
      return var1.start() > 0 && var0.charAt(var1.start() - 1) == '<' && var1.end() < var0.length() && var0.charAt(var1.end()) == '>';
   }

   private static boolean method9(String var0, Matcher var1, char var2) {
      int var3 = var1.start() - 1;

      while (var3 >= 0) {
         char var4 = var0.charAt(var3);
         if ("»›>".indexOf(var4) != -1) {
            return true;
         }

         if (!method12(var4)) {
            if (var4 == ']') {
               int var6 = var0.lastIndexOf(91, var3);
               if (var6 == -1) {
                  return false;
               }

               var3 = var6 - 1;
            } else {
               var3--;
            }
         } else {
            if (method11(var0, var3, "From") || method11(var0, var3, "To")) {
               return true;
            }

            int var5 = var3;

            while (var5 > 0 && method12(var0.charAt(var5 - 1))) {
               var5--;
            }

            if (!method10(var0.substring(var5, var3 + 1))) {
               return false;
            }

            var3 = var5 - 1;
         }
      }

      return var2 != '>' && !method10(var1.group()) && !field6.contains(var1.group().toLowerCase(Locale.ROOT));
   }

   private static boolean method10(String var0) {
      boolean var1 = false;

      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);
         if (!Character.isUpperCase(var3)) {
            return false;
         }

         var1 = true;
      }

      return var1;
   }

   private static boolean method11(String var0, int var1, String var2) {
      int var3 = var1 - var2.length() + 1;
      return var3 >= 0 && var0.regionMatches(var3, var2, 0, var2.length()) && (var3 == 0 || var0.charAt(var3 - 1) == ' ');
   }

   private static boolean method12(char var0) {
      return Character.isLetterOrDigit(var0) || var0 == '_';
   }

   private static int method13(String var0, int var1) {
      while (var1 < var0.length() && var0.charAt(var1) == ' ') {
         var1++;
      }

      return var1;
   }
}
