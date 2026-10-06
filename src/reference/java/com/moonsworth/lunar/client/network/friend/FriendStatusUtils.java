package com.moonsworth.lunar.client.network.friend;

import com.google.gson.JsonObject;
import com.lunarclient.common.v1.Location;
import com.lunarclient.common.v1.ServerRichStatus;
import com.moonsworth.lunar.client.network.friend.FriendStatus;
import java.util.UUID;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;

public class FriendStatusUtils {
   public FriendStatusUtils() {
   }

   public static JsonObject method1(UUID uuid0, Location location1, FriendStatus entityrenderertype2, long number3) {
      JsonObject json5 = new JsonObject();
      boolean flag6 = location1 == null;
      json5.addProperty("onlineFriendStatus", entityrenderertype2.getName());
      if (flag6) {
         json5.add("offline", method3(number3));
         return json5;
      }

      json5.addProperty("locationCase", location1.getLocationCase().name());
      JsonObject json7 = method2(uuid0, location1);
      if (!json7.isEmpty()) {
         json5.add("location", json7);
      }

      return json5;
   }

   private static JsonObject method2(UUID uuid0, Location location1) {
      JsonObject json2 = new JsonObject();
      switch (location1.getLocationCase()) {
         case PUBLIC_SERVER:
            json2.addProperty("serverName", location1.getPublicServer().getName());
            json2.addProperty("serverMappingsId", location1.getPublicServer().getServerMappingsId());
            if (location1.getPublicServer().hasColors()) {
               JsonObject json6 = new JsonObject();
               json6.addProperty("primary", String.format("#%06X", 16777215 & location1.getPublicServer().getColors().getPrimary().getColor()));
               json6.addProperty("secondary", String.format("#%06X", 16777215 & location1.getPublicServer().getColors().getSecondary().getColor()));
               json2.add("colors", json6);
            }

            if (location1.getPublicServer().hasRichStatus()) {
               ServerRichStatus serverrichstatus7 = location1.getPublicServer().getRichStatus();
               if (!serverrichstatus7.getGameName().isEmpty()) {
                  json2.addProperty("richStatusGameName", serverrichstatus7.getGameName());
               }
            }
            break;
         case IN_GAME:
            if (location1.getInGame().hasMinecraftVersion()) {
               String text5 = location1.getInGame().getMinecraftVersion().getEnum();
               String text4 = text5;
               if (text4.startsWith("v")) {
                  text4 = text4.substring(1);
               }

               text4 = text4.replace("_", ".");
               json2.addProperty("minecraftVersionEnum", text5);
               json2.addProperty("minecraftVersionDisplay", text4);
            }

            json2.addProperty("isBadlion", location1.getInGame().getIsBadlion());
            break;
         case HOSTED_WORLD:
            UUID uuid3 = ProtoConverter.method1(location1.getHostedWorld().getWorldHost().getUuid());
            json2.addProperty("worldHostUsername", location1.getHostedWorld().getWorldHost().getUsername());
            json2.addProperty("worldHostUuid", uuid3.toString());
            json2.addProperty("isHostedWorldOwner", uuid3.equals(uuid0));
      }

      return json2;
   }

   private static JsonObject method3(long number0) {
      JsonObject json2 = new JsonObject();
      boolean flag3 = number0 != -1L;
      json2.addProperty("hasLastVisibleOnline", flag3);
      if (flag3) {
         json2.addProperty("lastVisibleOnlineMs", number0);
         json2.addProperty("offlineSinceMs", number0);
         json2.addProperty("offlineDurationMs", Math.max(0L, System.currentTimeMillis() - number0));
      }

      return json2;
   }
}
