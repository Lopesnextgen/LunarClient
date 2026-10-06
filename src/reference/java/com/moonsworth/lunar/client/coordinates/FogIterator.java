package com.moonsworth.lunar.client.coordinates;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.lunarclient.common.v1.InboundHostedWorld;
import com.lunarclient.common.v1.InboundLocation;
import com.lunarclient.common.v1.InboundSinglePlayer;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.friend.v1.BroadcastLocationChangeRequest.Trigger;
import com.lunarclient.websocket.hostedworld.v1.AddressAndPort;
import com.lunarclient.websocket.hostedworld.v1.Heartbeat;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldHeartbeatRequest;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush;
import com.lunarclient.websocket.hostedworld.v1.JoinSource;
import com.lunarclient.websocket.hostedworld.v1.LoginResponse;
import com.lunarclient.websocket.hostedworld.v1.StartHostingWorldRequest;
import com.lunarclient.websocket.hostedworld.v1.StartHostingWorldResponse;
import com.lunarclient.websocket.hostedworld.v1.StopHostingWorldRequest;
import com.lunarclient.websocket.hostedworld.v1.Heartbeat.OfflinePlayer;
import com.lunarclient.websocket.hostedworld.v1.Heartbeat.OnlinePlayer;
import com.lunarclient.websocket.hostedworld.v1.ListHostedWorldsResponse.HostedWorld;
import com.lunarclient.websocket.hostedworld.v1.StartHostingWorldResponse.Status;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.NetworkConnectionBridge;
import com.moonsworth.lunar.bridge.glintcolorizer.Glintcolorizer;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.gui.notification.HostedWorldNotification;
import com.moonsworth.lunar.client.gui.HostWorldScreen;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.entity.EventEntityWorldJoin;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRunDirectory;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerPingEvent;
import com.moonsworth.lunar.client.driver.core.gui.Annotation;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FogIterator implements LoadableHandler, EventRegistrar {
   private final GuiIterator field1 = new GuiIterator();
   private static final int field2 = 30000;
   private Coordinates2 field3;
   private Thread field4;
   private Coordinates5 field5;
   private File field6;
   private final Set<Gui2Handler> field7 = new HashSet<>();
   private Set<UUID> field8 = new HashSet<>();
   private Set<String> field9 = new HashSet<>();
   private boolean field10 = true;
   private boolean field11 = true;
   private int field12 = 12;
   private HostedWorldStatusPush field13 = null;
   private Coordinates field14 = null;
   private UUID field15 = null;
   private List<HostedWorld> field16 = new ArrayList<>();
   private boolean field17 = false;
   private final Map<UUID, AddressAndPort> field18 = new ConcurrentHashMap<>();
   private int field19 = 5;

   @Override
   public void close() {
   }

   @Override
   public void init() {
      this.handle(DisconnectEvent.class, var1 -> this.method4());
      this.handle(ServerPingEvent.class, var1 -> {
         if (this.method20()) {
            this.method4();
         }
      });
      this.handle(EventRunDirectory.class, this::method15);
      this.handle(EventEntityWorldJoin.class, this::method14);
      this.handle(EventCommandLegacy.class, this::method1);
   }

   private void method1(EventCommandLegacy var1) {
      if (!var1.getCommand().startsWith("/publish") || !this.method26() && !this.method20()) {
         if (var1.getCommand().startsWith("/publish") && ThreadModuleDump63.method3().bridge$getIntegratedServer() != null) {
            ThreadModuleDump63.method3()
               .bridge$submit(() -> ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new HostWorldScreen("hostedWorldSettings"))));
            var1.cancel();
         }
      } else {
         ThreadModuleDump63.method17("Cannot use /publish in a hosted world");
         var1.cancel();
      }
   }

   public void method2(UUID var1, AddressAndPort var2) {
      this.field18.put(var1, var2);
   }

   @Nullable
   public FogIterator.Data6 method3(UUID var1) {
      AddressAndPort var2 = this.field18.get(var1);
      if (this.field3 == null) {
         return null;
      }

      if (var2 == null) {
         return null;
      }

      int var3 = this.field3.method1().getPort();
      new Coordinates4(var1, var2, var3);
      return new FogIterator.Data6(var2.getPort(), var2.getAddress());
   }

   public void method4() {
      this.method11();
      this.method12();
      this.field13 = null;
      this.field14 = null;
      this.field15 = null;
      this.field5 = null;
      this.method22();
   }

   public void method5(Coordinates5 var1) {
      this.field3 = new Coordinates2(var1);
      this.method8();
      this.method7(
         var2 -> {
            Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
            if (var3 == null) {
               ThreadModuleDump63.method4().method81().method4();
            } else {
               ThreadModuleDump63.method5()
                  .ifPresent(
                     var1xx -> var1xx.method15(
                        InboundLocation.newBuilder()
                           .setHostedWorld(InboundHostedWorld.newBuilder().setWorldHost(ThreadModuleDump66.method3(var3.bridge$getUniqueID())).build())
                           .build(),
                        Trigger.TRIGGER_HOSTED_WORLD_START,
                        null
                     )
                  );
               ItemcounterType2 var4 = ItemcounterType2.getByID(var1.method4().ordinal());
               ThreadModuleDump63.method3().bridge$getIntegratedServer().bridge$publishWorldToLan(var4, var1.method2(), var1.getPort());
               ThreadModuleDump63.method4().method69().method10(new HostedWorldNotification(NotificationManager.method15("hostWorldNotification")));
               ClickEvent var5 = null;
               HoverEvent var6 = null;
               if (ThreadModuleDump63.MC_VERSION >= 6) {
                  var5 = ClickEvent.copyToClipboard(var2.getJoinIp());
                  var6 = HoverEvent.showText(
                     Component.text().content(Client.method109().method67().method2("messages", "clickToCopyIp")).color(NamedTextColor.GREEN)
                  );
               }

               Component var7 = ((TextComponent)((TextComponent)Component.text(
                           AdventureChatFormatting.GRAY + "[" + AdventureChatFormatting.AQUA + "LC" + AdventureChatFormatting.GRAY + "] "
                        )
                        .append(
                           ((Builder)((Builder)Component.text()
                                    .content(Client.method109().method67().method2("messages", "startedHostingWorldPrefix"))
                                    .color(NamedTextColor.WHITE))
                                 .clickEvent(var5))
                              .hoverEvent(var6)
                        ))
                     .append(((TextComponent)Component.text(var2.getJoinIp(), NamedTextColor.AQUA).clickEvent(var5)).hoverEvent(var6)))
                  .append(
                     ((Builder)((Builder)Component.text()
                              .content(Client.method109().method67().method2("messages", "startedHostingWorldSuffix"))
                              .color(NamedTextColor.WHITE))
                           .clickEvent(var5))
                        .hoverEvent(var6)
                  );
               var3.method1(var7);
               if (var1.method6() == HostWorldScreen.Type4.InviteOnly) {
                  ThreadModuleDump63.method17(
                     Client.method109()
                        .method67()
                        .method2("messages", "hostedWorldHint", LcuiScreen.method139(Client.method109().method41().method8().method22().get()))
                  );
               }

               this.field4 = new Thread(this::method16);
               this.field4.setDaemon(true);
               this.field4.start();
               this.method11();
               this.method10(var1);
            }
         }
      );
      this.method22();
   }

   public void method6() {
      if (this.method20()) {
         this.method7(var0 -> {});
      } else {
         ThreadModuleDump63.method5().ifPresent(var0 -> var0.method99().stopHostingWorld(null, StopHostingWorldRequest.newBuilder().build(), var0x -> {}));
      }
   }

   public void method7(Consumer<StartHostingWorldResponse> var1) {
      Heartbeat var2 = this.method18();
      ThreadModuleDump63.method5()
         .ifPresent(var2x -> var2x.method99().startHostingWorld(null, StartHostingWorldRequest.newBuilder().setHeartbeat(var2).build(), var1xx -> {
            if (var1xx.getStatus() == Status.STATUS_OK) {
               Slayer.method3("Successfully hosted server.");
               var1.accept(var1xx);
            } else {
               Slayer.method5("Failed to start hosted world: " + var1xx.getStatus());
               ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("failedToHostWorld", var1xx.getStatus()));
               ThreadModuleDump63.method4().method81().method4();
               if (ThreadModuleDump63.method3().bridge$getIntegratedServer() != null) {
                  ThreadModuleDump63.method3().bridge$getIntegratedServer().bridge$haltServer();
               }
            }
         }));
   }

   private void method8() {
      this.field5 = this.field3.method1();
   }

   public void method9(Coordinates5 var1) {
      this.field3 = new Coordinates2(var1);
      this.method8();
      this.method10(var1);
      this.method11();
      Heartbeat var2 = this.method18();
      ThreadModuleDump63.method5()
         .ifPresent(var1x -> var1x.method99().hostedWorldHeartbeat(null, HostedWorldHeartbeatRequest.newBuilder().setHeartbeat(var2).build(), var0x -> {
            if (var0x.getStatus() == com.lunarclient.websocket.hostedworld.v1.HostedWorldHeartbeatResponse.Status.STATUS_OK) {
               Slayer.method3("Successfully updated server.");
            } else {
               Slayer.method5("Failed to update hosted world: " + var0x.getStatus());
            }
         }));
      ItemcounterType2 var3 = ItemcounterType2.getByID(var1.method4().ordinal());
      ThreadModuleDump63.method3().bridge$getIntegratedServer().bridge$updateLanWorld(var3, var1.method2());
      this.method22();
   }

   private void method10(Coordinates5 var1) {
      Glintcolorizer var2 = ThreadModuleDump63.method3().bridge$getIntegratedServer();
      var2.bridge$setAllowCheats(var1.method2());
      var2.bridge$setDifficulty(ItemcounterType.byId(var1.method5().id));
   }

   private void method11() {
      if (this.field6 != null && this.method20()) {
         FogIterator.Data5 var1 = new FogIterator.Data5(this.field3.method1(), this.field7, this.field8);

         try {
            Files.writeString(this.field6.toPath(), ThreadModuleDump48.field22.toJson(var1));
         } catch (IOException var3) {
            throw new RuntimeException("Could not write to lunar hosted world settings file");
         }
      }
   }

   public void method12() {
      if (this.method20()) {
         Glintcolorizer var1 = ThreadModuleDump63.method3().bridge$getIntegratedServer();
         if (var1 != null) {
            var1.bridge$closeLanServer(this.field3.method1().getPort());
         }

         ThreadModuleDump63.method5()
            .ifPresent(
               var0 -> var0.method15(
                  InboundLocation.newBuilder().setSinglePlayer(InboundSinglePlayer.getDefaultInstance()).build(), Trigger.TRIGGER_HOSTED_WORLD_STOP, null
               )
            );
         ThreadModuleDump63.method5().ifPresent(var0 -> var0.method99().stopHostingWorld(null, StopHostingWorldRequest.newBuilder().build(), var0x -> {
            if (var0x.getStatus() == com.lunarclient.websocket.hostedworld.v1.StopHostingWorldResponse.Status.STATUS_OK) {
               Slayer.method3("Successfully stopped hosting server.");
            } else {
               Slayer.method5("Failed to stop hosted world: " + var0x.getStatus());
            }
         }));
         this.field3 = null;
         synchronized (this.field7) {
            this.field7.clear();
         }

         this.field8 = new HashSet<>();
         this.field18.clear();
      }

      this.method22();
   }

   @Nullable
   public FogIterator.Data4 method13(NetworkConnectionBridge var1) {
      String var2 = var1.bridge$getEnteredHostName();
      if (var2 == null) {
         return null;
      } else {
         String[] var3 = var2.split("\u0000");
         if (var3.length == 6 && var3[1].equals("LunarPreAuthV1")) {
            String var4 = var3[2];
            UUID var5 = UUID.fromString(var3[3]);
            FogIterator.Data3[] var6 = (FogIterator.Data3[])ThreadModuleDump48.field22.fromJson(var3[4], FogIterator.Data3[].class);
            String var7 = var3[5];
            return !this.field9.remove(var7) ? null : new FogIterator.Data4(var5, var4, var6);
         } else {
            return null;
         }
      }
   }

   private void method14(EventEntityWorldJoin var1) {
      if (var1.field1 == ThreadModuleDump63.method7() && !this.method26() && this.field5 != null && this.field5.method8() && !this.method20()) {
         if (this.field5.method7() != null) {
            this.field5.method15(this.field5.method7());
         }

         ThreadModuleDump63.method4().method81().method5(this.field5);
      }

      if (this.method20() && var1.field1 instanceof Bridge6_10 var2) {
         synchronized (this.field7) {
            Optional var4 = this.field7.stream().filter(var1x -> var1x.method5().equals(var1.field1.bridge$getUniqueID())).findFirst();
            if (var4.isPresent()) {
               ((Gui2Handler)var4.get()).method9(System.currentTimeMillis());
               ((Gui2Handler)var4.get()).method10(true);
            } else {
               this.field7.add(new Gui2Handler(var2.bridge$getName(), var2.bridge$getUniqueID(), System.currentTimeMillis(), true));
            }
         }

         this.method17();
         ThreadModuleDump63.method4().method50().method9();
      }

      this.method22();
   }

   private void method15(EventRunDirectory var1) {
      File var2 = var1.method2();
      if (var2 != null) {
         this.field6 = new File(var2, "lunar_hosted_world_data.json");
         if (!this.field6.exists()) {
            try {
               this.field6.createNewFile();
            } catch (IOException var8) {
               throw new RuntimeException("Could not create Hosted World JSON.", var8);
            }
         } else {
            try (FileReader var3 = new FileReader(this.field6)) {
               FogIterator.Data5 var4 = (FogIterator.Data5)ThreadModuleDump48.field22.fromJson(var3, FogIterator.Data5.class);
               if (var4 != null) {
                  this.field5 = var4.field1;
                  synchronized (this.field7) {
                     this.field7.clear();
                     if (var4.field2 != null) {
                        this.field7.addAll(var4.field2);
                     }
                  }

                  this.field8 = Objects.requireNonNullElseGet(var4.field3, HashSet::new);
               }
            } catch (IOException var11) {
               throw new RuntimeException("Could not read Hosted World JSON.", var11);
            }
         }
      }

      this.method22();
   }

   private void method16() {
      try {
         while (this.method20()) {
            Thread.sleep(30000L);
            this.method17();
         }
      } catch (Throwable var2) {
         throw var2;
      }
   }

   private void method17() {
      if (this.field3 != null) {
         Heartbeat var1 = this.method18();
         ThreadModuleDump63.method5()
            .ifPresent(var1x -> var1x.method99().hostedWorldHeartbeat(null, HostedWorldHeartbeatRequest.newBuilder().setHeartbeat(var1).build(), var0x -> {
               if (var0x.getStatus() != com.lunarclient.websocket.hostedworld.v1.HostedWorldHeartbeatResponse.Status.STATUS_OK) {
                  Slayer.method5("Failed to heartbeat hosted world: " + var0x.getStatus());
               }
            }));
      }
   }

   @NotNull
   private Heartbeat method18() {
      Coordinates5 var1 = this.field3.method1();
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();

      for (Bridge6_10 var5 : new ArrayList<>(ThreadModuleDump63.method3().bridge$getIntegratedServer().bridge$getPlayers())) {
         synchronized (this.field7) {
            Optional var7 = this.field7.stream().filter(var1x -> var1x.method5().equals(var5.bridge$getUniqueID())).findFirst();
            if (var7.isPresent()) {
               ((Gui2Handler)var7.get()).method9(System.currentTimeMillis());
            } else {
               Slayer.method7("Online player wasn't in the playerlist somehow??");
               Gui2Handler var8 = new Gui2Handler(var5.bridge$getName(), var5.bridge$getUniqueID(), System.currentTimeMillis(), true);
               this.field7.add(var8);
            }
         }

         var2.add(OnlinePlayer.newBuilder().setPlayer(ThreadModuleDump66.method8(var5)).build());
      }

      if (var2.stream().noneMatch(var0 -> var0.getPlayer().getUsername().equals(ThreadModuleDump63.method7().bridge$getName()))) {
         Gui2Handler var15 = new Gui2Handler(
            ThreadModuleDump63.method7().bridge$getName(), ThreadModuleDump63.method7().bridge$getUniqueID(), System.currentTimeMillis(), true
         );
         synchronized (this.field7) {
            this.field7.add(var15);
         }

         var2.add(
            OnlinePlayer.newBuilder()
               .setPlayer(UuidAndUsername.newBuilder().setUuid(ThreadModuleDump66.method3(var15.method5())).setUsername(var15.getUsername()).build())
               .build()
         );
      }

      ArrayList var16;
      synchronized (this.field7) {
         var16 = new ArrayList<>(this.field7);
      }

      for (Gui2Handler var21 : var16) {
         if (!var2.stream().anyMatch(var1x -> ThreadModuleDump66.method1(var1x.getPlayer().getUuid()).equals(var21.method5()))) {
            var21.method10(false);
            var3.add(
               OfflinePlayer.newBuilder()
                  .setPlayer(UuidAndUsername.newBuilder().setUuid(ThreadModuleDump66.method3(var21.method5())).setUsername(var21.getUsername()).build())
                  .setLastOnline(ThreadModuleDump66.method14(var21.method6()))
                  .build()
            );
         }
      }

      com.lunarclient.websocket.hostedworld.v1.Heartbeat.Builder var20 = Heartbeat.newBuilder();
      if (var1.method6() != HostWorldScreen.Type4.Closed) {
         var20.addAllowedJoinSources(switch (this.field5.method6()) {
            case LAN -> JoinSource.JOIN_SOURCE_LOCAL_AREA_NETWORK;
            case InviteOnly -> JoinSource.JOIN_SOURCE_HOSTED_WORLD_WHITELIST;
            case Friends -> JoinSource.JOIN_SOURCE_LUNAR_CLIENT_FRIENDS;
            case Everyone -> JoinSource.JOIN_SOURCE_EVERYONE;
            default -> throw new IllegalStateException("Unknown privacy state");
         });
      }

      return var20.setMaxPlayers(var1.method9())
         .addAllWhitelistedUuids(ThreadModuleDump66.method4(this.field8))
         .addAllOnlinePlayers(var2)
         .addAllOfflinePlayers(var3)
         .build();
   }

   public void method19(LoginResponse var1) {
      this.field12 = var1.getMaxWorldPlayerCount();
      this.field10 = var1.getHostingEnabled();
      this.field11 = var1.getJoiningEnabled();
      this.field19 = var1.getMultiplayerRefreshIntervalSeconds();
   }

   public boolean method20() {
      return this.field3 != null;
   }

   public static HostWorldScreen.Type4 method21(HostedWorldStatusPush var0) {
      if (var0.getAllowedJoinSourcesCount() == 0) {
         return HostWorldScreen.Type4.Closed;
      }

      return switch (var0.getAllowedJoinSources(0)) {
         case JOIN_SOURCE_UNSPECIFIED -> HostWorldScreen.Type4.Closed;
         case JOIN_SOURCE_LOCAL_AREA_NETWORK -> HostWorldScreen.Type4.LAN;
         case JOIN_SOURCE_HOSTED_WORLD_WHITELIST -> HostWorldScreen.Type4.InviteOnly;
         case JOIN_SOURCE_LUNAR_CLIENT_FRIENDS -> HostWorldScreen.Type4.Friends;
         case JOIN_SOURCE_EVERYONE -> HostWorldScreen.Type4.Everyone;
         case UNRECOGNIZED -> null;
         default -> throw new IncompatibleClassChangeError();
      };
   }

   public void method22() {
      this.field1.method3("isHostingWorld", this.method20());
      this.field1.method3("isInSomeonesHostedWorld", this.method26());
      JsonArray var1 = new JsonArray();

      for (UUID var3 : this.field8) {
         var1.add(var3.toString());
      }

      this.field1.method3("whitelistedUUIDs", var1);
      JsonArray var13 = new JsonArray();
      String var14 = "";
      String var4 = "";
      HostedWorldStatusPush var5 = this.method35();
      if (this.method26() && var5 != null) {
         HostWorldScreen.Type4 var15 = method21(var5);
         var14 = Client.method109().method67().method2("gui.components", "hostedWorldNameText", this.method37().username());
         var4 = Client.method109()
            .method67()
            .method2("gui.components", "hostedWorldDescriptionText", var15, var5.getOnlinePlayersCount(), var5.getMaxPlayers());
         long var17 = System.currentTimeMillis();

         for (com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush.OnlinePlayer var10 : var5.getOnlinePlayersList()) {
            var13.add(Gui2Handler.method2(var10, var17).provide());
         }

         for (com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush.OfflinePlayer var21 : var5.getOfflinePlayersList()) {
            var13.add(Gui2Handler.method3(var21).provide());
         }
      } else if (this.method20()) {
         synchronized (this.field7) {
            for (Gui2Handler var8 : this.field7) {
               var13.add(var8.provide());
            }

            int var16 = (int)this.method30().stream().filter(Gui2Handler::method2).count();
            int var18 = this.method30().size();
            Bridge5Extension_5 var9 = ThreadModuleDump63.method7();
            var14 = Client.method109().method67().method2("gui.components", "hostedWorldNameText", var9 == null ? "You" : var9.bridge$getName());
            var4 = Client.method109().method67().method2("gui.components", "hostedWorldDescriptionText", this.method28().method1().method6(), var16, var18);
         }
      }

      this.field1.method3("players", var13);
      this.field1.method3("title", var14);
      this.field1.method3("description", var4);
   }

   public void method23(UUID var1) {
      this.field8.add(var1);
      this.method22();
   }

   public void method24(UUID var1) {
      this.field8.remove(var1);
      this.method22();
   }

   public Gui2Handler method25(UUID var1) {
      HostedWorldStatusPush var2 = this.method35();
      if (this.method26() && var2 != null) {
         long var10 = System.currentTimeMillis();

         for (com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush.OnlinePlayer var6 : var2.getOnlinePlayersList()) {
            Gui2Handler var7 = Gui2Handler.method2(var6, var10);
            if (var7.method5().equals(var1)) {
               return var7;
            }
         }

         for (com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush.OfflinePlayer var13 : var2.getOfflinePlayersList()) {
            Gui2Handler var14 = Gui2Handler.method3(var13);
            if (var14.method5().equals(var1)) {
               return var14;
            }
         }
      } else {
         synchronized (this.field7) {
            for (Gui2Handler var5 : this.field7) {
               if (var5.method5().equals(var1)) {
                  return var5;
               }
            }
         }
      }

      return null;
   }

   public boolean method26() {
      return this.field14 != null && !this.method20();
   }

   @Annotation("hostedWorld")
   @Generated
   public GuiIterator method27() {
      return this.field1;
   }

   @Generated
   public Coordinates2 method28() {
      return this.field3;
   }

   @Generated
   public Coordinates5 method29() {
      return this.field5;
   }

   @Generated
   public Set<Gui2Handler> method30() {
      return this.field7;
   }

   @Generated
   public Set<UUID> method31() {
      return this.field8;
   }

   @Generated
   public Set<String> method32() {
      return this.field9;
   }

   @Generated
   public boolean method33() {
      return this.field10;
   }

   @Generated
   public boolean method34() {
      return this.field11;
   }

   @Generated
   public int getMaxWorldPlayerCount() {
      return this.field12;
   }

   @Generated
   public HostedWorldStatusPush method35() {
      return this.field13;
   }

   @Generated
   public void method36(HostedWorldStatusPush var1) {
      this.field13 = var1;
   }

   @Generated
   public Coordinates method37() {
      return this.field14;
   }

   @Generated
   public void method38(Coordinates var1) {
      this.field14 = var1;
   }

   @Generated
   public UUID method39() {
      return this.field15;
   }

   @Generated
   public void method40(UUID var1) {
      this.field15 = var1;
   }

   @Generated
   public List<HostedWorld> method41() {
      return this.field16;
   }

   @Generated
   public void method42(List<HostedWorld> var1) {
      this.field16 = var1;
   }

   @Generated
   public boolean method43() {
      return this.field17;
   }

   @Generated
   public void method44(boolean var1) {
      this.field17 = var1;
   }

   @Generated
   public int getMultiplayerRefreshIntervalSeconds() {
      return this.field19;
   }

   public class Data3 {
      @SerializedName("name")
      private final String field1;
      @SerializedName("value")
      private final String field2;
      @SerializedName("signature")
      private final String field3;

      public Data3(String var1, String var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @SerializedName("name")
      public String name() {
         return this.field1;
      }

      @SerializedName("value")
      public String value() {
         return this.field2;
      }

      @SerializedName("signature")
      public String signature() {
         return this.field3;
      }
   }

   public class Data4 {
      private final UUID field1;
      private final String field2;
      private final FogIterator.Data3[] field3;

      public Data4(UUID var1, String var2, FogIterator.Data3[] var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public UUID uuid() {
         return this.field1;
      }

      public String username() {
         return this.field2;
      }

      public FogIterator.Data3[] method1() {
         return this.field3;
      }
   }

   public static final class Data5 {
      @SerializedName("worldSettings")
      private final Coordinates5 field1;
      @SerializedName("playerList")
      private final Set<Gui2Handler> field2;
      @SerializedName("whitelistedUUIDs")
      private final Set<UUID> field3;

      public Data5(Coordinates5 var1, Set<Gui2Handler> var2, Set<UUID> var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }

   public class Data6 {
      private final int field1;
      private final String field2;

      public Data6(int var1, String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public int method1() {
         return this.field1;
      }

      public String method2() {
         return this.field2;
      }
   }
}
