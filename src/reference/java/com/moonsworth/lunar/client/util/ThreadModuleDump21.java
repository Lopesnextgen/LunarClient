package com.moonsworth.lunar.client.util;

import com.google.gson.JsonObject;
import com.lunarclient.common.v1.Location;
import com.lunarclient.common.v1.ServerRichStatus;
import com.moonsworth.lunar.client.mixin.EntityRendererType;
import java.util.UUID;

public class ThreadModuleDump21 {
   public static JsonObject method1(UUID var0, Location var1, EntityRendererType var2, long var3) {
      JsonObject var5 = new JsonObject();
      boolean var6 = var1 == null;
      var5.addProperty("onlineFriendStatus", var2.getName());
      if (var6) {
         var5.add("offline", method3(var3));
         return var5;
      }

      var5.addProperty("locationCase", var1.getLocationCase().name());
      JsonObject var7 = method2(var0, var1);
      if (!var7.isEmpty()) {
         var5.add("location", var7);
      }

      return var5;
   }

   private static JsonObject method2(UUID var0, Location var1) {
      JsonObject var2 = new JsonObject();
      switch (var1.getLocationCase()) {
         case PUBLIC_SERVER:
            var2.addProperty("serverName", var1.getPublicServer().getName());
            var2.addProperty("serverMappingsId", var1.getPublicServer().getServerMappingsId());
            if (var1.getPublicServer().hasColors()) {
               JsonObject var6 = new JsonObject();
               var6.addProperty("primary", String.format("#%06X", 16777215 & var1.getPublicServer().getColors().getPrimary().getColor()));
               var6.addProperty("secondary", String.format("#%06X", 16777215 & var1.getPublicServer().getColors().getSecondary().getColor()));
               var2.add("colors", var6);
            }

            if (var1.getPublicServer().hasRichStatus()) {
               ServerRichStatus var7 = var1.getPublicServer().getRichStatus();
               if (!var7.getGameName().isEmpty()) {
                  var2.addProperty("richStatusGameName", var7.getGameName());
               }
            }
            break;
         case IN_GAME:
            if (var1.getInGame().hasMinecraftVersion()) {
               String var5 = var1.getInGame().getMinecraftVersion().getEnum();
               String var4 = var5;
               if (var4.startsWith("v")) {
                  var4 = var4.substring(1);
               }

               var4 = var4.replace("_", ".");
               var2.addProperty("minecraftVersionEnum", var5);
               var2.addProperty("minecraftVersionDisplay", var4);
            }

            var2.addProperty("isBadlion", var1.getInGame().getIsBadlion());
            break;
         case HOSTED_WORLD:
            UUID var3 = ThreadModuleDump66.method1(var1.getHostedWorld().getWorldHost().getUuid());
            var2.addProperty("worldHostUsername", var1.getHostedWorld().getWorldHost().getUsername());
            var2.addProperty("worldHostUuid", var3.toString());
            var2.addProperty("isHostedWorldOwner", var3.equals(var0));
      }

      return var2;
   }

   private static JsonObject method3(long var0) {
      JsonObject var2 = new JsonObject();
      boolean var3 = var0 != -1L;
      var2.addProperty("hasLastVisibleOnline", var3);
      if (var3) {
         var2.addProperty("lastVisibleOnlineMs", var0);
         var2.addProperty("offlineSinceMs", var0);
         var2.addProperty("offlineDurationMs", Math.max(0L, System.currentTimeMillis() - var0));
      }

      return var2;
   }
}
