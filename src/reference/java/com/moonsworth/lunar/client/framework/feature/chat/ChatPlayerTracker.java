package com.moonsworth.lunar.client.framework.feature.chat;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jspecify.annotations.Nullable;

public class ChatPlayerTracker {
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
   private final Int2ObjectLinkedOpenHashMap<PlayerInfoBridge> field7 = new Int2ObjectLinkedOpenHashMap();

   public ChatPlayerTracker() {
   }

   public void method1(TypedChatMessage data1) {
      PlayerInfoBridge bridge2_332 = this.method2(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
      if (bridge2_332 != null) {
         this.field7.put(data1.method1(), bridge2_332);

         while (this.field7.size() > 1200) {
            this.field7.remove(this.field7.firstIntKey());
         }
      }
   }

   public @Nullable PlayerInfoBridge method2(String text1) {
      NetHandlerPlayClientBridge bridgeextension_72 = Ref.method3().bridge$getClientPacketListener();
      if (bridgeextension_72 == null) {
         return null;
      }

      List list3 = bridgeextension_72.bridge$getPlayerInfoMap();
      if (list3 != null && !list3.isEmpty()) {
         HashMap map4 = new HashMap(list3.size());

         for (PlayerInfoBridge bridge2_336 : list3) {
            GameProfile gameprofile7 = bridge2_336.bridge$getGameProfile();
            if (gameprofile7 != null && gameprofile7.getName() != null) {
               map4.put(gameprofile7.getName().toLowerCase(Locale.ROOT), bridge2_336);
            }
         }

         String text8 = this.method3(text1, map4.keySet());
         return text8 == null ? null : (PlayerInfoBridge)map4.get(text8.toLowerCase(Locale.ROOT));
      } else {
         return null;
      }
   }

   public @Nullable String method3(String text1, Set<String> set2) {
      Matcher matcher3 = field1.matcher(text1);

      while (matcher3.find()) {
         if (matcher3.start() > 40) {
            return null;
         }

         if (method6(text1, matcher3) && set2.contains(matcher3.group().toLowerCase(Locale.ROOT))) {
            return matcher3.group();
         }
      }

      return null;
   }

   public @Nullable String method4(String text1) {
      Matcher matcher2 = field1.matcher(text1);

      while (matcher2.find()) {
         if (matcher2.start() > 40) {
            return null;
         }

         if (method8(text1, matcher2)) {
            return matcher2.group();
         }

         char character3 = method7(text1, matcher2);
         if (character3 != 0 && method9(text1, matcher2, character3)) {
            return matcher2.group();
         }
      }

      return null;
   }

   public @Nullable ResourceLocationBridge method5(int index1) {
      PlayerInfoBridge bridge2_332 = (PlayerInfoBridge)this.field7.get(index1);
      if (bridge2_332 == null) {
         return null;
      }

      if (Ref.MC_VERSION >= 1) {
         return bridge2_332.bridge$getLocationSkin();
      }

      GameProfile gameprofile3 = bridge2_332.bridge$getGameProfile();
      return gameprofile3 == null ? null : (ResourceLocationBridge)Ref.method3().bridge$getSkinManager().bridge$getSkinLocation(gameprofile3, Type.SKIN).orElse(null);
   }

   private static boolean method6(String text0, Matcher matcher1) {
      return method8(text0, matcher1) || method7(text0, matcher1) != 0;
   }

   private static char method7(String text0, Matcher matcher1) {
      int index2 = method13(text0, matcher1.end());
      if (index2 < text0.length() && text0.charAt(index2) == '[') {
         int number3 = text0.indexOf(93, index2);
         if (number3 == -1) {
            return '\u0000';
         }

         index2 = method13(text0, number3 + 1);
      }

      return index2 < text0.length() && "»›:>".indexOf(text0.charAt(index2)) != -1 ? text0.charAt(index2) : '\u0000';
   }

   private static boolean method8(String text0, Matcher matcher1) {
      return matcher1.start() > 0 && text0.charAt(matcher1.start() - 1) == '<' && matcher1.end() < text0.length() && text0.charAt(matcher1.end()) == '>';
   }

   private static boolean method9(String text0, Matcher matcher1, char character2) {
      int index3 = matcher1.start() - 1;

      while (index3 >= 0) {
         char character4 = text0.charAt(index3);
         if ("»›>".indexOf(character4) != -1) {
            return true;
         }

         if (!method12(character4)) {
            if (character4 == ']') {
               int number6 = text0.lastIndexOf(91, index3);
               if (number6 == -1) {
                  return false;
               }

               index3 = number6 - 1;
            } else {
               index3--;
            }
         } else {
            if (method11(text0, index3, "From") || method11(text0, index3, "To")) {
               return true;
            }

            int index5 = index3;

            while (index5 > 0 && method12(text0.charAt(index5 - 1))) {
               index5--;
            }

            if (!method10(text0.substring(index5, index3 + 1))) {
               return false;
            }

            index3 = index5 - 1;
         }
      }

      return character2 != '>' && !method10(matcher1.group()) && !field6.contains(matcher1.group().toLowerCase(Locale.ROOT));
   }

   private static boolean method10(String text0) {
      boolean flag1 = false;

      for (int index2 = 0; index2 < text0.length(); index2++) {
         char character3 = text0.charAt(index2);
         if (!Character.isUpperCase(character3)) {
            return false;
         }

         flag1 = true;
      }

      return flag1;
   }

   private static boolean method11(String text0, int number1, String text2) {
      int index3 = number1 - text2.length() + 1;
      return index3 >= 0 && text0.regionMatches(index3, text2, 0, text2.length()) && (index3 == 0 || text0.charAt(index3 - 1) == ' ');
   }

   private static boolean method12(char character0) {
      return Character.isLetterOrDigit(character0) || character0 == '_';
   }

   private static int method13(String text0, int index1) {
      while (index1 < text0.length() && text0.charAt(index1) == ' ') {
         index1++;
      }

      return index1;
   }
}
