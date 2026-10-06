package com.moonsworth.lunar.client.util.net;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScorePlayerTeamBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.config.Config;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import com.moonsworth.lunar.client.framework.Ref;

public final class ServerUtils {
   private static final Pattern BRAND_PATTERN = Pattern.compile("(.+) (?:<- .+)?");
   private static String server;

   public static void updateServer() {
      if (Ref.method7() == null || Ref.method3().bridge$getCurrentServerData() == null) {
         server = null;
      } else if (!ServerBrandWatcher.method8(KeystrokesType.HYPIXEL)) {
         server = null;
      } else {
         String text0 = getScoreboardServer();
         if (text0 == null) {
            server = null;
         } else {
            server = WordUtils.capitalize(normalizeServerName(text0).toLowerCase(), null);
         }
      }
   }

   public static String normalizeServerName(String text0) {
      text0 = TextBridge.stripColor(text0);
      return text0.equalsIgnoreCase("skyblock co-op") ? "SKYBLOCK" : text0;
   }

   private static String getScoreboardServer() {
      ScoreboardBridge lighting40 = Ref.method3().bridge$getWorld().bridge$getScoreboard();
      ScoreboardObjectiveBridge lighting1 = null;
      ScorePlayerTeamBridge lighting32 = lighting40.bridge$getPlayersTeam(Ref.method3().bridge$getPlayer().bridge$getName());
      if (lighting32 != null) {
         int number3 = lighting32.bridge$getChatFormat().getColorIndex();
         if (Bridge.getMinecraftVersion() == Config.field1) {
            lighting1 = lighting40.bridge$getObjectiveInDisplaySlot(1);
         } else if (number3 >= 0) {
            try {
               lighting1 = lighting40.bridge$getObjectiveInDisplaySlot(3 + number3);
            } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception5) {
            }
         }
      }

      ScoreboardObjectiveBridge lighting6 = lighting1 != null ? lighting1 : lighting40.bridge$getObjectiveInDisplaySlot(1);
      return lighting6 == null ? null : TextBridge.getTextContent(TextBridge.asAdventure(lighting6.bridge$getDisplayName()));
   }

   public static boolean isServer(String text0) {
      return server != null && server.equalsIgnoreCase(text0);
   }

   public static boolean isClientBrand(String text0) {
      if (Ref.method3().bridge$getPlayer() != null && Ref.method3().bridge$getPlayer().bridge$getClientBrand().isPresent()) {
         String text1 = (String)Ref.method3().bridge$getPlayer().bridge$getClientBrand().get();
         if (Bridge.getMinecraftVersion().method19()) {
            return text1.startsWith(text0);
         }

         Matcher matcher2 = field1.matcher(text1);
         if (matcher2.find()) {
            return matcher2.group(1).startsWith(text0);
         }
      }

      return false;
   }

   @Generated
   private ServerUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static String getServer() {
      return server;
   }
}
