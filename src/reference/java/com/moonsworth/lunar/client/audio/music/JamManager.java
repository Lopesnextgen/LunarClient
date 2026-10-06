package com.moonsworth.lunar.client.audio.music;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lunarclient.websocket.jam.v1.OwnedJam;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.config.JsonFileConfig;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.holograms.EventAssetServerConnectedLegacy;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import lombok.Generated;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.moonsworth.lunar.client.audio.music.StyngrSong;

public class JamManager implements LoadableHandler, JsonFileConfig, GuiIterator.Extension {
   private final GuiIterator field1 = new GuiIterator();
   private static final Map<Integer, StyngrSong> field2 = new HashMap<>();
   private final Map<UUID, JamManager.Data> field3 = new ConcurrentHashMap<>();
   private final Map<String, JamManager.Data> field4 = new ConcurrentHashMap<>();
   private final Map<String, JamManager.Data> field5 = new ConcurrentHashMap<>();
   private final long field6 = 6600000L;
   private List<OwnedJam> field7 = new ArrayList<>();
   private final Logger field8 = LogManager.getLogger(JamManager.class.getName());

   private void method1(Consumer<String> var1) {
      UUID var2 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId();
      JamManager.Data var3 = this.field3.get(var2);
      if (var3 != null && var3.field2 > System.currentTimeMillis()) {
         var1.accept(var3.field1);
      } else if (ThreadModuleDump63.method5().isEmpty()) {
         var1.accept(null);
      } else {
         ThreadModuleDump63.method5()
            .get()
            .method115()
            .method1(
               "GAME_STYNGR_JWT",
               var3x -> {
                  try {
                     this.field8.info("Fetching Styngr JWT...");
                     HttpRequest var4 = HttpRequest.newBuilder()
                        .uri(URI.create(ServiceEndpoints.method7() + "/styngr/jwt"))
                        .header("Content-Type", "application/json")
                        .header("x-installation-id", ThreadModuleDump80.installationId)
                        .header("X-Overwolf-Muid", ThreadModuleDump80.overwolfMuid)
                        .header("Authorization", var3x)
                        .POST(BodyPublishers.ofString("{\"platform\": \"GAME\"}"))
                        .build();
                     HttpResponse var5 = HttpClient.newHttpClient().send(var4, BodyHandlers.ofString());
                     JsonObject var6 = JsonParser.parseString((String)var5.body()).getAsJsonObject();
                     String var7 = var6.get("styngrJwt").getAsString();
                     this.field3.put(var2, new JamManager.Data(var7, System.currentTimeMillis() + 6600000L));
                     var1.accept(var7);
                  } catch (Exception var8) {
                     this.field8.info("An error occurred while attempting to get a Styngr JWT: " + var8.getMessage());
                     var8.printStackTrace();
                  }
               }
            );
      }
   }

   private void method2(String var1, Consumer<String> var2) {
      if (var1 == null) {
         var2.accept(null);
      } else {
         JamManager.Data var3 = this.field4.get(var1);
         if (var3 != null && var3.field2 > System.currentTimeMillis()) {
            var2.accept(var3.field1);
         } else {
            try {
               this.field8.info("Fetching Styngr store token...");
               HttpRequest var4 = HttpRequest.newBuilder()
                  .uri(URI.create(ServiceEndpoints.method2() + "/v2/sdk/tokens/sdkuser"))
                  .header("Content-Type", "application/json")
                  .header("Authorization", "Bearer " + var1)
                  .POST(BodyPublishers.noBody())
                  .build();
               HttpResponse var5 = HttpClient.newHttpClient().send(var4, BodyHandlers.ofString());
               JsonObject var6 = JsonParser.parseString((String)var5.body()).getAsJsonObject();
               String var7 = var6.get("accessToken").getAsString();
               this.field4.put(var1, new JamManager.Data(var7, System.currentTimeMillis() + 6600000L));
               var2.accept(var7);
            } catch (Exception var8) {
               this.field8.info("An error occurred while attempting to get a Styngr Store Token: " + var8.getMessage());
               var8.printStackTrace();
            }
         }
      }
   }

   public void method3(String var1, Consumer<String> var2) {
      JamManager.Data var3 = this.field5.get(var1);
      if (var3 != null && var3.field2 > System.currentTimeMillis()) {
         var2.accept(var3.field1);
      } else {
         new Thread(
               () -> this.method1(
                  var3x -> this.method2(
                     var3x,
                     var3xx -> {
                        try {
                           this.field8.info("Fetching styng url for styng_id: " + var1);
                           HttpRequest var4 = HttpRequest.newBuilder()
                              .uri(URI.create(ServiceEndpoints.method2() + "/v1/sdk/styngs/" + var1 + "/play"))
                              .header("Content-Type", "application/json")
                              .header("Authorization", "Bearer " + var3xx)
                              .POST(BodyPublishers.noBody())
                              .build();
                           HttpResponse var5 = HttpClient.newHttpClient().send(var4, BodyHandlers.ofString());
                           JsonObject var6 = JsonParser.parseString((String)var5.body()).getAsJsonObject();
                           String var7 = var6.get("url").getAsString();
                           long var8 = Instant.parse(var6.get("expiresAt").getAsString()).toEpochMilli();
                           long var10 = var8 - System.currentTimeMillis();
                           JamManager.Data var12 = new JamManager.Data(var7, (long)(System.currentTimeMillis() + var10 * 0.9));
                           this.field5.put(var1, var12);
                           var2.accept(var7);
                        } catch (Exception var13) {
                           this.field8.info("An error occurred while attempting to get styng url: " + var13.getMessage());
                           var13.printStackTrace();
                           var2.accept("");
                        }
                     }
                  )
               )
            )
            .start();
      }
   }

   @Override
   public void init() {
      ClientEventBus.method29().method2(EventAssetServerConnectedLegacy.class, var1 -> {
         this.field3.clear();
         this.field4.clear();
         this.field1.clear();
      });
      this.method10();
   }

   @Override
   public void close() {
   }

   private void method4(JsonArray var1) {
      for (StyngrSong var5 : (StyngrSong[])ThreadModuleDump48.field22.fromJson(var1, StyngrSong[].class)) {
         field2.put(var5.getId(), var5);
      }
   }

   @Override
   public void load(JsonObject var1) {
      this.method4(var1.getAsJsonArray("jams"));
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   @Override
   public void method1(JsonObject var1) {
      var1.add("jams", ThreadModuleDump48.field22.toJsonTree(field2.values()));
   }

   @Override
   public String method5() {
      return "jams.json";
   }

   public void method10() {
      try {
         HttpRequest var1 = HttpRequest.newBuilder()
            .uri(URI.create(ServiceEndpoints.method7() + "/styngr/jams"))
            .header("X-Installation-Id", ThreadModuleDump80.installationId)
            .header("X-Overwolf-Muid", ThreadModuleDump80.overwolfMuid)
            .GET()
            .build();
         HttpClient.newHttpClient().sendAsync(var1, BodyHandlers.ofString()).thenAccept(var1x -> {
            JsonElement var2x;
            try {
               var2x = JsonParser.parseString(var1x.body());
            } catch (Exception var6) {
               var6.printStackTrace();
               this.method4();
               return;
            }

            if (!var2x.isJsonArray()) {
               this.method4();
            } else {
               JsonObject var3 = new JsonObject();

               try {
                  var3.add("jams", var2x.getAsJsonArray());
                  this.load(var3);
               } catch (IOException var5) {
                  Inventorymod2.method5(var5, "Jams Processor - Download");
                  this.method4();
               }
            }
         });
      } catch (Exception var2) {
         var2.printStackTrace();
         this.method4();
      }
   }

   public void method11() {
      JsonArray var1 = new JsonArray();

      for (OwnedJam var3 : ThreadModuleDump63.method4().method73().method13()) {
         StyngrSong var4 = method12().get(var3.getJamId());
         if (var4 != null) {
            JsonElement var5 = ThreadModuleDump48.field22.toJsonTree(var4);
            if (var3.hasGiftInfo()) {
               EmoteGiftInfo var6 = new EmoteGiftInfo(
                  ThreadModuleDump66.method1(var3.getGiftInfo().getGiftedBy().getUuid()),
                  var3.getGiftInfo().getGiftedBy().getUsername(),
                  var3.getGiftInfo().getMessage(),
                  var3.getGiftInfo().getIsAnonymous()
               );
               var5.getAsJsonObject().add("gifter", var6.provide());
            }

            var1.add(var5);
         }
      }

      this.field1.method3("items", var1);
   }

   @Generated
   @Override
   public GuiIterator getProvider() {
      return this.field1;
   }

   @Generated
   public static Map<Integer, StyngrSong> method12() {
      return field2;
   }

   @Generated
   public List<OwnedJam> method13() {
      return this.field7;
   }

   @Generated
   public void method12(List<OwnedJam> var1) {
      this.field7 = var1;
   }

   class Data {
      private final String field1;
      private final long field2;

      Data(String var1, long var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String value() {
         return this.field1;
      }

      public long method1() {
         return this.field2;
      }
   }
}
