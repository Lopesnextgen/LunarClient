package com.moonsworth.lunar.client.network.server;

import com.google.gson.JsonObject;
import com.lunarclient.websocket.serverdiscovery.v1.DiscoverServersRequest;
import com.lunarclient.websocket.serverdiscovery.v1.LoadAutocompleteSuggestionsRequest;
import com.lunarclient.websocket.serverdiscovery.v1.LoadServerModalRequest;
import com.lunarclient.websocket.serverdiscovery.v1.LoginResponse;
import com.lunarclient.websocket.serverdiscovery.v1.SearchServersRequest;
import com.lunarclient.websocket.serverdiscovery.v1.ServerSectionCardsPush;
import com.lunarclient.websocket.serverdiscovery.v1.TrackSectionNotInterestedRequest;
import com.lunarclient.websocket.serverdiscovery.v1.TrackServerJoinRequest;
import com.lunarclient.websocket.serverdiscovery.v1.ServerDiscoveryService.Interface;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge4_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.network.server.ServerMapping;
import com.moonsworth.lunar.client.network.server.ServerRecommendation;
import com.moonsworth.lunar.client.network.server.RecommendedServer;
import com.moonsworth.lunar.client.network.server.SocialLinks;
import com.moonsworth.lunar.client.network.server.DiscoverySection;
import com.moonsworth.lunar.client.network.server.DiscoveryCard;
import com.moonsworth.lunar.client.keystrokes.Keystrokes2;
import com.moonsworth.lunar.client.mixin.EntityRendererType2;
import com.moonsworth.lunar.client.util.ThreadModuleDump10;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ServerDiscoveryManager implements LoadableHandler {
   private static final String field1 = "serverDiscovery:sectionCards";
   private volatile int field2;
   @Nullable
   private volatile ServerRecommendation field3;
   private volatile List<DiscoveryCard> field4 = List.of();
   private volatile EntityRendererType2 field5 = EntityRendererType2.READY;

   @Override
   public void close() {
      this.field2 = 0;
      this.field3 = null;
      this.field4 = List.of();
      this.field5 = EntityRendererType2.READY;
   }

   @Override
   public void init() {
   }

   public void method1(LoginResponse var1) {
      this.field2 = var1.getTotalServers();
      this.field3 = var1.hasAnnouncement() ? ServerRecommendation.method1(var1.getAnnouncement()) : null;
      this.field4 = method15(var1.getGameTypesList(), DiscoveryCard::method1);
      this.field5 = EntityRendererType2.READY;
   }

   public void method2() {
      this.field5 = EntityRendererType2.READY;
   }

   public JsonObject method3() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("state", this.field5.getId());
      var1.addProperty("totalServers", this.field2);
      var1.add("announcement", ThreadModuleDump48.field22.toJsonTree(this.field3));
      var1.add("gameTypes", ThreadModuleDump48.field22.toJsonTree(this.field4));
      return var1;
   }

   public void method4(Consumer<List<DiscoverySection>> var1) {
      if (this.method14() == null) {
         var1.accept(List.of());
      } else {
         this.method14()
            .discoverServers(null, DiscoverServersRequest.getDefaultInstance(), var1x -> var1.accept(method15(var1x.getSectionsList(), DiscoverySection::method1)));
      }
   }

   public void method5(String var1, Consumer<List<RecommendedServer>> var2) {
      if (this.method14() == null) {
         var2.accept(List.of());
      } else {
         this.method14()
            .searchServers(
               null,
               SearchServersRequest.newBuilder().setQuery(var1 == null ? "" : var1).build(),
               var1x -> var2.accept(method15(var1x.getServersList(), RecommendedServer::method1))
            );
      }
   }

   public void method6(Consumer<List<ServerMapping>> var1) {
      if (this.method14() == null) {
         var1.accept(List.of());
      } else {
         this.method14()
            .loadAutocompleteSuggestions(
               null, LoadAutocompleteSuggestionsRequest.getDefaultInstance(), var1x -> var1.accept(method15(var1x.getSuggestionsList(), ServerMapping::method1))
            );
      }
   }

   public void method7(String var1, Consumer<SocialLinks> var2, Runnable var3) {
      if (var1 != null && !var1.isEmpty() && this.method14() != null) {
         this.method14().loadServerModal(null, LoadServerModalRequest.newBuilder().setServerMappingsId(var1).build(), var2x -> {
            if (var2x != null && var2x.hasDetails()) {
               var2.accept(SocialLinks.method1(var2x.getDetails()));
            } else {
               var3.run();
            }
         });
      } else {
         var3.run();
      }
   }

   public void method8(ServerSectionCardsPush var1) {
      com.moonsworth.lunar.client.driver.core.DriverViewportLegacy var2 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50();
      if (var2 != null) {
         JsonObject var3 = new JsonObject();
         var3.addProperty("sectionId", var1.getSectionId());
         var3.add("cards", ThreadModuleDump48.field22.toJsonTree(method15(var1.getCardsList(), RecommendedServer::method1)));
         var2.method23(var2.method55().method13(), "serverDiscovery:sectionCards", var3);
      }
   }

   public void method9(String var1, String var2, String var3, String var4, String var5) {
      if (var4 != null && !var4.isEmpty()) {
         this.method12(var1, var2, var5);
         this.method13(var3 != null && !var3.isEmpty() ? var3 : var4, var4);
      }
   }

   public void method10(String var1, String var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isEmpty()) {
         ThreadModuleDump63.method3().bridge$submit(() -> {
            Bridge4_4 var2x = Bridge.method8().method51();
            var2x.bridge$load();
            if (var2x.bridge$containsUnpinnedAddress(var2)) {
               Client.method109().method69().method3("Server is already saved");
            } else {
               var2x.bridge$add(Bridge.method8().method50(var1, var2, false));
               var2x.bridge$save();
               Client.method109().method69().method3("Saved " + var1 + " to your servers!");
            }
         });
      }
   }

   public void method11(String var1, Runnable var2) {
      if (var1 != null && !var1.isEmpty() && this.method14() != null) {
         this.method14().trackSectionNotInterested(null, TrackSectionNotInterestedRequest.newBuilder().setId(var1).build(), var1x -> var2.run());
      } else {
         var2.run();
      }
   }

   private void method12(String var1, String var2, String var3) {
      if (this.method14() != null) {
         this.method14()
            .trackServerJoin(
               null,
               TrackServerJoinRequest.newBuilder()
                  .setSectionId(var3 == null ? "" : var3)
                  .setCardServerMappingsId(var2 == null ? "" : var2)
                  .setCardRecommendationId(var1 == null ? "" : var1)
                  .build(),
               var0 -> {}
            );
      }
   }

   private void method13(String var1, String var2) {
      ThreadModuleDump63.method3().bridge$submit(() -> {
         ThreadModuleDump10.disconnect();
         ThreadModuleDump63.method3().bridge$displayScreen(null);
         Bridge3_19 var2x = Bridge.method8().method50(var1, var2, false);
         Keystrokes2 var3 = Client.method109().method59().method10().get(var2);
         if (var3 != null && var3.name().equals(var1)) {
            var2x.bridge$setIsPinned(true);
            Client.method109().method59().method12(var2x);
         }

         ThreadModuleDump63.method3().bridge$connect(var2x, null);
      });
   }

   private Interface method14() {
      return Client.method109().method35() == null ? null : Client.method109().method35().method109();
   }

   private static <P, M> List<M> method15(List<P> var0, Function<P, M> var1) {
      ArrayList var2 = new ArrayList(var0.size());

      for (Object var4 : var0) {
         var2.add(var1.apply(var4));
      }

      return var2;
   }

   @Generated
   public int getTotalServers() {
      return this.field2;
   }

   @Nullable
   @Generated
   public ServerRecommendation method16() {
      return this.field3;
   }

   @Generated
   public List<DiscoveryCard> method17() {
      return this.field4;
   }

   @Generated
   public EntityRendererType2 method18() {
      return this.field5;
   }
}
