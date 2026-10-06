package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.hud.ping.Ping;

public class PingMigration implements ConfigMigration {
   public PingMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof Ping) {
         JsonObject json4;
         if (this.method2("options", json3)) {
            json4 = json3.getAsJsonObject("options");
            JsonObject json5;
            JsonObject json6;
            if (this.method2("PING_NAMETAG", json3)) {
               json5 = json3.getAsJsonObject("PING_NAMETAG");
               json6 = this.method2("options", json5) ? json5.getAsJsonObject("options") : new JsonObject();
            } else {
               json5 = new JsonObject();
               json6 = new JsonObject();
               json5.add("options", json6);
               json3.add("PING_NAMETAG", json5);
            }

            if (this.method2("enablePingNametag", json4)) {
               boolean flag7 = json4.remove("enablePingNametag").getAsBoolean();
               if (flag7) {
                  json5.remove("enabled");
                  json5.addProperty("enabled", true);
               }
            }

            if (this.method2("pingAbove", json4)) {
               boolean flag10 = json4.remove("pingAbove").getAsBoolean();
               json6.addProperty("pingAbove", flag10);
            }
         } else {
            json4 = new JsonObject();
            json3.add("options", json4);
         }

         if (this.method2("PING_HUD", json3)) {
            JsonObject json8 = json3.getAsJsonObject("PING_HUD");
            if (this.method2("options", json8)) {
               JsonObject json9 = json8.getAsJsonObject("options");
               if (this.method2("pingShowMs", json9)) {
                  boolean flag11 = json9.remove("pingShowMs").getAsBoolean();
                  json4.addProperty("pingShowMs", flag11);
               }
            }
         }
      }
   }

   private boolean method2(String text, JsonObject json2) {
      return json2.has(text) && !json2.get(text).isJsonNull();
   }
}
