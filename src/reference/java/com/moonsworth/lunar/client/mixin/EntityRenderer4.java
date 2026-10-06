package com.moonsworth.lunar.client.mixin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.gson.JsonObject;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.lunarclient.common.v1.InMenus;
import com.lunarclient.common.v1.InboundHostedWorld;
import com.lunarclient.common.v1.InboundInMenus;
import com.lunarclient.common.v1.InboundLocation;
import com.lunarclient.common.v1.InboundMinecraftRealms;
import com.lunarclient.common.v1.InboundReplayWorld;
import com.lunarclient.common.v1.InboundRewindWorld;
import com.lunarclient.common.v1.InboundServer;
import com.lunarclient.common.v1.InboundSinglePlayer;
import com.lunarclient.common.v1.Location;
import com.lunarclient.common.v1.ServerRichStatus;
import com.lunarclient.common.v1.Uuid;
import com.lunarclient.common.v1.InboundRewindWorld.RewindWorldType;
import com.lunarclient.gameipc.location.v1.UpdateLocationRequest;
import com.lunarclient.gameipc.promotion.v1.PromotionType;
import com.lunarclient.websocket.analytics.v1.AnalyticsService;
import com.lunarclient.websocket.analytics.v1.RecordPinnedServerInteractionRequest;
import com.lunarclient.websocket.analytics.v1.RecordPinnedServerInteractionRequest.InteractionType;
import com.lunarclient.websocket.apollo.v1.ApolloService;
import com.lunarclient.websocket.apollo.v1.ApolloService.Interface;
import com.lunarclient.websocket.badge.v1.BadgeService;
import com.lunarclient.websocket.badge.v1.RefreshBadgesPush;
import com.lunarclient.websocket.conversation.v1.ConversationAddParticipantsPush;
import com.lunarclient.websocket.conversation.v1.ConversationAddPinnedMessagePush;
import com.lunarclient.websocket.conversation.v1.ConversationAddedPush;
import com.lunarclient.websocket.conversation.v1.ConversationMessageHistoryDeletedPush;
import com.lunarclient.websocket.conversation.v1.ConversationMessagePush;
import com.lunarclient.websocket.conversation.v1.ConversationOwnerUpdatePush;
import com.lunarclient.websocket.conversation.v1.ConversationPreSendActionPush;
import com.lunarclient.websocket.conversation.v1.ConversationRemoveMessagePush;
import com.lunarclient.websocket.conversation.v1.ConversationRemoveParticipantPush;
import com.lunarclient.websocket.conversation.v1.ConversationRemovePinnedMessagePush;
import com.lunarclient.websocket.conversation.v1.ConversationRemovedPush;
import com.lunarclient.websocket.conversation.v1.ConversationService;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateIconPolicyPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateIconPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateInvitePolicyPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateMessagePinningPolicyPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateNamePolicyPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateNamePush;
import com.lunarclient.websocket.conversation.v1.RefreshConversationsPush;
import com.lunarclient.websocket.cosmetic.v2.CosmeticOwnershipVisibility;
import com.lunarclient.websocket.cosmetic.v2.CosmeticService;
import com.lunarclient.websocket.cosmetic.v2.PlayerCosmeticsPushV2;
import com.lunarclient.websocket.cosmetic.v2.RefreshCosmeticsPush;
import com.lunarclient.websocket.emote.v1.EmoteService;
import com.lunarclient.websocket.emote.v1.EquippedEmote;
import com.lunarclient.websocket.emote.v1.RecommendedJam;
import com.lunarclient.websocket.emote.v1.RefreshEmotesPush;
import com.lunarclient.websocket.emote.v1.StopEmotePush;
import com.lunarclient.websocket.emote.v1.UseEmotePush;
import com.lunarclient.websocket.friend.v1.BroadcastLocationChangeRequest;
import com.lunarclient.websocket.friend.v1.BroadcastServerKickRequest;
import com.lunarclient.websocket.friend.v1.FriendRadioInfoPush;
import com.lunarclient.websocket.friend.v1.FriendRemovedYouPush;
import com.lunarclient.websocket.friend.v1.FriendRequest;
import com.lunarclient.websocket.friend.v1.FriendRequestAcceptedPush;
import com.lunarclient.websocket.friend.v1.FriendRequestCanceledPush;
import com.lunarclient.websocket.friend.v1.FriendRequestDeniedPush;
import com.lunarclient.websocket.friend.v1.FriendRequestReceivedPush;
import com.lunarclient.websocket.friend.v1.FriendService;
import com.lunarclient.websocket.friend.v1.FriendSocials;
import com.lunarclient.websocket.friend.v1.FriendStatusPush;
import com.lunarclient.websocket.friend.v1.LastSeenVisibility;
import com.lunarclient.websocket.friend.v1.OfflineFriend;
import com.lunarclient.websocket.friend.v1.OnlineFriend;
import com.lunarclient.websocket.friend.v1.BroadcastLocationChangeRequest.Trigger;
import com.lunarclient.websocket.friend.v1.FriendStatusPush.FriendCase;
import com.lunarclient.websocket.handshake.v1.Handshake;
import com.lunarclient.websocket.handshake.v1.SessionIdentifyPush;
import com.lunarclient.websocket.heartbeat.v1.HeartbeatService;
import com.lunarclient.websocket.heartbeat.v1.RefreshMetadataPush;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldAvailablePush;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldService;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldWhitelistedPush;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldPush;
import com.lunarclient.websocket.hostedworld.v1.Joinability;
import com.lunarclient.websocket.hostedworld.v1.LoginRequest;
import com.lunarclient.websocket.jam.v1.JamService;
import com.lunarclient.websocket.jam.v1.RefreshJamsPush;
import com.lunarclient.websocket.legacyapi.v1.LegacyApiService;
import com.lunarclient.websocket.liveexperience.v1.LiveExperienceService;
import com.lunarclient.websocket.marker.v1.MarkerService;
import com.lunarclient.websocket.marker.v1.NewMarkerPush;
import com.lunarclient.websocket.notification.v1.DisplayChatMessagePush;
import com.lunarclient.websocket.notification.v1.DisplayNotificationPush;
import com.lunarclient.websocket.notification.v1.NotificationService;
import com.lunarclient.websocket.paynow.v1.OpenPayNowCheckoutPush;
import com.lunarclient.websocket.performance.v1.PerformanceService;
import com.lunarclient.websocket.promotion.v1.PromotionService;
import com.lunarclient.websocket.promotion.v1.LoginResponse.MedalPromotionState;
import com.lunarclient.websocket.protocol.v1.ClientboundWebSocketMessage;
import com.lunarclient.websocket.protocol.v1.WebSocketRichClosePush;
import com.lunarclient.websocket.protocol.v1.ClientboundWebSocketMessage.ContentsCase;
import com.lunarclient.websocket.radio.v1.PlayerRadioPush;
import com.lunarclient.websocket.radio.v1.RadioService;
import com.lunarclient.websocket.screenshot.v1.ScreenshotService;
import com.lunarclient.websocket.server.v1.ServerService;
import com.lunarclient.websocket.serverdiscovery.v1.ServerDiscoveryService;
import com.lunarclient.websocket.serverdiscovery.v1.ServerSectionCardsPush;
import com.lunarclient.websocket.skyblock.v1.DungeonUpdatePush;
import com.lunarclient.websocket.skyblock.v1.SkyblockService;
import com.lunarclient.websocket.socials.v1.RefreshSocialsPush;
import com.lunarclient.websocket.socials.v1.SocialsService;
import com.lunarclient.websocket.spray.v1.RemoveSprayPush;
import com.lunarclient.websocket.spray.v1.SprayService;
import com.lunarclient.websocket.spray.v1.UseSprayPush;
import com.lunarclient.websocket.store.v1.IncomingGiftPrivacy;
import com.lunarclient.websocket.store.v1.StoreService;
import com.lunarclient.websocket.subscription.v1.SubscriptionService;
import com.lunarclient.websocket.tebex.v1.OpenTebexJsCheckoutPush;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.glintcolorizer.Glintcolorizer;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.notification.FriendNotificationLong;
import com.moonsworth.lunar.client.coordinates.Coordinates3;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.highlight.Fishing2Extension;
import com.moonsworth.lunar.client.audio.music.StyngrSong;
import com.moonsworth.lunar.client.audio.music.JamManager;
import com.moonsworth.lunar.client.account.BadgeManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.cosmetics.SprayManager;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGift;
import com.moonsworth.lunar.client.cosmetics.emote.Emote;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework12;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.feature.debug.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectReasonEvent;
import com.moonsworth.lunar.client.event.mixin.holograms.EventAssetServerConnectedLegacy;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftProvider;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteDefinition;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.mod.misc.debug.FpsDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.GeckolibDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.ModsEnabledDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.OptimizationDebugMod;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump13;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump21;
import com.moonsworth.lunar.client.util.ThreadModuleDump42;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import com.moonsworth.lunar.config.Config;
import io.sentry.Breadcrumb;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.Serializable;
import java.net.URI;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.Generated;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class EntityRenderer4 extends WebSocketClient implements EventRegistrar {
   private static UUID field1;
   private static InboundLocation field2 = InboundLocation.newBuilder().setInMenus(InboundInMenus.newBuilder().build()).build();
   private static Location field3 = Location.newBuilder().setInMenus(InMenus.newBuilder().build()).build();
   private static EntityRenderer3 field4;
   private final Handshake field5;
   private final RpcChannelImpl field6 = new RpcChannelImpl(this);
   private final Map<Class<? extends Message>, Consumer<? extends Message>> field7 = new HashMap<>();
   private final Map<Class<? extends Message>, Cache<UUID, ? extends Message>> field8 = new HashMap<>();
   private WebSocketRichClosePush field9 = null;
   private final Interface field10 = ApolloService.newStub(this.field6);
   private final com.lunarclient.websocket.conversation.v1.ConversationService.Interface field11 = ConversationService.newStub(this.field6);
   private final com.lunarclient.websocket.cosmetic.v2.CosmeticService.Interface field12 = CosmeticService.newStub(this.field6);
   private final com.lunarclient.websocket.radio.v1.RadioService.Interface field13 = RadioService.newStub(this.field6);
   private final com.lunarclient.websocket.store.v1.StoreService.Interface field14 = StoreService.newStub(this.field6);
   private final com.lunarclient.websocket.emote.v1.EmoteService.Interface field15 = EmoteService.newStub(this.field6);
   private final com.lunarclient.websocket.spray.v1.SprayService.Interface field16 = SprayService.newStub(this.field6);
   private final com.lunarclient.websocket.badge.v1.BadgeService.Interface field17 = BadgeService.newStub(this.field6);
   private final com.lunarclient.websocket.friend.v1.FriendService.Interface field18 = FriendService.newStub(this.field6);
   private final com.lunarclient.websocket.heartbeat.v1.HeartbeatService.Interface field19 = HeartbeatService.newStub(this.field6);
   private final com.lunarclient.websocket.notification.v1.NotificationService.Interface field20 = NotificationService.newStub(this.field6);
   private final com.lunarclient.websocket.subscription.v1.SubscriptionService.Interface field21 = SubscriptionService.newStub(this.field6);
   private final com.lunarclient.websocket.legacyapi.v1.LegacyApiService.Interface field22 = LegacyApiService.newStub(this.field6);
   private final com.lunarclient.websocket.performance.v1.PerformanceService.Interface field23 = PerformanceService.newStub(this.field6);
   private final com.lunarclient.websocket.hostedworld.v1.HostedWorldService.Interface field24 = HostedWorldService.newStub(this.field6);
   private final com.lunarclient.websocket.analytics.v1.AnalyticsService.Interface field25 = AnalyticsService.newStub(this.field6);
   private final com.lunarclient.websocket.liveexperience.v1.LiveExperienceService.Interface field26 = LiveExperienceService.newStub(this.field6);
   private final com.lunarclient.websocket.screenshot.v1.ScreenshotService.Interface field27 = ScreenshotService.newStub(this.field6);
   private final com.lunarclient.websocket.server.v1.ServerService.Interface field28 = ServerService.newStub(this.field6);
   private final com.lunarclient.websocket.socials.v1.SocialsService.Interface field29 = SocialsService.newStub(this.field6);
   private final com.lunarclient.websocket.jam.v1.JamService.Interface field30 = JamService.newStub(this.field6);
   private final com.lunarclient.websocket.skyblock.v1.SkyblockService.Interface field31 = SkyblockService.newStub(this.field6);
   private final com.lunarclient.websocket.promotion.v1.PromotionService.Interface field32 = PromotionService.newStub(this.field6);
   private final com.lunarclient.websocket.marker.v1.MarkerService.Interface field33 = MarkerService.newStub(this.field6);
   private final com.lunarclient.websocket.serverdiscovery.v1.ServerDiscoveryService.Interface field34 = ServerDiscoveryService.newStub(this.field6);
   private boolean field35 = false;
   private final GuiIterator field36 = new GuiIterator();
   private final Client field37;
   private EntityRendererType2 field38 = EntityRendererType2.READY;
   private boolean field39;
   private LastSeenVisibility field40;
   private IncomingGiftPrivacy field41;
   private CosmeticOwnershipVisibility field42;
   private long field43;
   private final EntityRenderer field44 = new EntityRenderer();

   public EntityRenderer4(Handshake var1) {
      super(
         URI.create(ServiceEndpoints.method6() + "/game"),
         new Draft_6455(),
         Map.of("sentry-trace", ThreadModuleDump80.field3, "User-Agent", "Lunar Client " + Client.method19()),
         30000
      );
      this.field44.method2(var1.getIdentity().getAuthenticatorJwt());
      this.field5 = var1;
      Slayer.method4("Assets", "Instantiate");
      this.field37 = ThreadModuleDump63.method4();
      this.method66(EntityRendererType2.READY);
      this.field36.method3("admin", false);
      this.method70(false);
      this.method71(LastSeenVisibility.LAST_SEEN_VISIBILITY_UNSPECIFIED);
      this.method72(IncomingGiftPrivacy.INCOMING_GIFT_PRIVACY_UNSPECIFIED);
      this.method74(CosmeticOwnershipVisibility.COSMETIC_OWNERSHIP_VISIBILITY_UNSPECIFIED);
      this.method73(0L);
      this.method67();
      if (this.field37.method31() != null) {
         this.field36.method3("onlineStatus", this.field37.method31().method16().getName());
      }

      this.method1(RefreshMetadataPush.class, this::method58);
      this.method1(ConversationMessagePush.class, this::method20);
      this.method1(ConversationPreSendActionPush.class, this::method21);
      this.method1(ConversationOwnerUpdatePush.class, this::method22);
      this.method1(ConversationAddParticipantsPush.class, this::method23);
      this.method1(ConversationRemoveParticipantPush.class, this::method24);
      this.method1(ConversationUpdateNamePush.class, this::method25);
      this.method1(ConversationUpdateIconPush.class, this::method26);
      this.method1(ConversationAddPinnedMessagePush.class, this::method27);
      this.method1(ConversationRemovePinnedMessagePush.class, this::method28);
      this.method1(ConversationUpdateInvitePolicyPush.class, this::method29);
      this.method1(ConversationUpdateNamePolicyPush.class, this::method30);
      this.method1(ConversationUpdateIconPolicyPush.class, this::method31);
      this.method1(ConversationUpdateMessagePinningPolicyPush.class, this::method32);
      this.method1(ConversationAddedPush.class, this::method33);
      this.method1(ConversationRemovedPush.class, this::method34);
      this.method1(ConversationRemoveMessagePush.class, this::method35);
      this.method1(ConversationMessageHistoryDeletedPush.class, this::method36);
      this.method1(RefreshConversationsPush.class, this::method37);
      this.method1(FriendStatusPush.class, this::method45);
      this.method1(FriendRemovedYouPush.class, this::method50);
      this.method1(DisplayChatMessagePush.class, this::method47);
      this.method1(PlayerCosmeticsPushV2.class, this::method51);
      this.method1(PlayerRadioPush.class, this::method52);
      this.method1(UseEmotePush.class, this::method53);
      this.method1(StopEmotePush.class, this::method54);
      this.method1(UseSprayPush.class, this::method56);
      this.method1(RemoveSprayPush.class, this::method57);
      this.method1(FriendRequestReceivedPush.class, this::method49);
      this.method1(DisplayNotificationPush.class, this::method48);
      this.method1(FriendRequestAcceptedPush.class, this::method38);
      this.method1(FriendRequestDeniedPush.class, this::method43);
      this.method1(FriendRequestCanceledPush.class, this::method44);
      this.method1(FriendRadioInfoPush.class, this::method46);
      this.method1(RefreshEmotesPush.class, this::method59);
      this.method1(RefreshCosmeticsPush.class, this::method60);
      this.method1(JoinHostedWorldPush.class, this::method39);
      this.method1(HostedWorldStatusPush.class, this::method40);
      this.method1(HostedWorldWhitelistedPush.class, this::method42);
      this.method1(HostedWorldAvailablePush.class, this::method41);
      this.method1(OpenTebexJsCheckoutPush.class, this::method18);
      this.method1(OpenPayNowCheckoutPush.class, this::method19);
      this.method1(SessionIdentifyPush.class, this::method76);
      this.method1(RefreshSocialsPush.class, this::method61);
      this.method1(RefreshBadgesPush.class, this::method62);
      this.method1(RefreshJamsPush.class, this::method63);
      this.method1(DungeonUpdatePush.class, this::method64);
      this.method1(NewMarkerPush.class, this::method65);
      this.method1(ServerSectionCardsPush.class, this::method69);
   }

   private <T extends Message> void method1(Class<T> var1, Consumer<T> var2) {
      this.field7.put(var1, var2);
      this.field8.put(var1, CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.MINUTES).build());
   }

   public boolean method2(Any var1) {
      for (Entry var3 : this.field7.entrySet()) {
         Class var4 = (Class)var3.getKey();
         if (var1.is(var4)) {
            Message var5;
            try {
               var5 = var1.unpack(var4);
            } catch (InvalidProtocolBufferException var7) {
               Slayer.method8("Assets", "Failed to parse wrapped message for " + var1.getTypeUrl());
               return false;
            }

            ThreadModuleDump13.method1(var5, (Consumer<Message>)var3.getValue());
            return true;
         }
      }

      return false;
   }

   private <T extends Message> void method3(UUID var1, Class<T> var2) {
      Cache var3 = this.field8.get(var2);
      if (var3 != null) {
         Message var4 = (Message)var3.getIfPresent(var1);
         if (var4 != null) {
            var3.invalidate(var1);
            Consumer var5 = this.field7.get(var2);
            ThreadModuleDump13.method1(var4, var5);
         }
      }
   }

   private <T extends Message> void method4(UUID var1, T var2) {
      Class var3 = var2.getClass();
      Cache var4 = this.field8.get(var3);
      if (var4 == null) {
         Slayer.method8("Assets", "Attempted to delay unregistered message type: " + var3.getSimpleName());
      } else {
         var4.put(var1, var2);
      }
   }

   public void onOpen(ServerHandshake var1) {
      this.send(this.field5.toByteArray());
      field4 = null;
      this.method66(EntityRendererType2.READY);
      Slayer.method4("Assets", "Connection established as %s (%s)", this.field37.method31().getName(), this.field37.method31().method16().getName());
      this.field37.method74().method8();
      ThreadModuleDump63.method3().bridge$submit(() -> ClientEventBus.method29().method12(EventAssetServerConnectedLegacy.class, EventAssetServerConnectedLegacy::new));
      this.method77();
      FogIterator var2 = ThreadModuleDump63.method4().method81();
      if (var2 != null) {
         var2.method6();
         this.field24.login(null, LoginRequest.newBuilder().build(), var2::method19);
      }

      this.field18.login(null, com.lunarclient.websocket.friend.v1.LoginRequest.getDefaultInstance(), var1x -> {
         ThreadModuleDump63.method4().method31().method4(EntityRendererType.fromProtobuf(var1x.getCurrentStatus()));
         this.field37.method50().clear();
         this.field37.method51().clear();
         HashSet var2x = new HashSet();

         for (OnlineFriend var4 : var1x.getOnlineFriendsList()) {
            UUID var5 = ThreadModuleDump66.method1(var4.getPlayer().getUuid());
            Memory var6 = new Memory(var5, var4.getPlayer().getUsername());
            var6.method4(EntityRendererType.fromProtobuf(var4.getStatus()));
            var6.method35(System.currentTimeMillis());
            var6.method33(var4.getLocation());
            var6.method38(var4.getHostedWorldJoinability());
            if (var6.getHostedWorldJoinability() != Joinability.JOINABILITY_ALLOWED) {
               var6.method39(false);
            }

            Config.get(var4.getMinecraftVersion().getEnum()).ifPresent(var6::method34);
            var6.method45(var4.getPlusColor().getColor());
            var6.method46(var4.getLogoColor().getColor());
            var6.method47((Gui2Handler2)ThreadModuleDump63.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(var4.getBadgeId()));
            var6.setRank(var4.getRankName());
            if (var4.hasFriendsSince()) {
               var6.method36(ThreadModuleDump66.method5(var4.getFriendsSince()));
            }

            FriendSocials var7 = var4.getSocials();
            if (var4.hasSocials()) {
               var6.method37(var7);
            }

            this.field37.method50().method4(var6);
            var2x.add(var5);
         }

         for (OfflineFriend var12 : var1x.getOfflineFriendsList()) {
            UUID var16 = ThreadModuleDump66.method1(var12.getPlayer().getUuid());
            if (!var2x.contains(var16)) {
               Memory var18 = new Memory(var16, var12.getPlayer().getUsername());
               var18.method33(null);
               var18.method4(EntityRendererType.OFFLINE);
               var18.method47((Gui2Handler2)ThreadModuleDump63.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(var12.getBadgeId()));
               var18.method46(var12.getLogoColor().getColor());
               var18.method45(var12.getPlusColor().getColor());
               var18.setRank(var12.getRankName());
               if (var12.hasLastVisibleOnline()) {
                  var18.method35(var12.getLastVisibleOnline().getSeconds() * 1000L);
               } else {
                  var18.method35(-1L);
               }

               if (var12.hasFriendsSince()) {
                  var18.method36(ThreadModuleDump66.method5(var12.getFriendsSince()));
               }

               FriendSocials var20 = var12.getSocials();
               if (var12.hasSocials()) {
                  var18.method37(var20);
               }

               this.field37.method50().method4(var18);
            }
         }

         for (Uuid var13 : var1x.getPinnedFriendsList()) {
            UUID var17 = ThreadModuleDump66.method1(var13);
            Memory var19 = this.field37.method50().method2(var17);
            if (var19 != null) {
               var19.method51(true);
            }
         }

         this.method70(var1x.getAllowFriendRequests());
         this.method71(var1x.getLastSeenVisibility());

         for (FriendRequest var14 : var1x.getOutboundFriendAddRequestsList()) {
            this.field37.method51().method3(EntityRenderer6.method1(var14));
         }

         for (FriendRequest var15 : var1x.getInboundFriendAddRequestsList()) {
            this.field37.method51().method4(EntityRenderer6.method1(var15));
         }

         if (var1x.hasUserCreatedAt()) {
            ThreadModuleDump63.method4().method43().method33(ThreadModuleDump66.method5(var1x.getUserCreatedAt()));
         }

         this.field37.method50().method9();
      });
      this.method5();
      this.method6();
      this.method9();
      this.method7();
      this.method8();
      this.method10();
      this.method11();
      this.method12();
      this.method13();
      this.method14();
   }

   private void method5() {
      this.field11.login(null, com.lunarclient.websocket.conversation.v1.LoginRequest.newBuilder().setUsingSatellite(true).build(), var1 -> {
         Slayer.method4("Conversation", "Conversation login successful");
         this.field37.method52().method4(var1);
         this.field37.method52().method24();
      });
   }

   private void method6() {
      this.field34.login(null, com.lunarclient.websocket.serverdiscovery.v1.LoginRequest.getDefaultInstance(), var1 -> {
         this.field37.method79().method1(var1);
         this.method68();
      });
   }

   private void method7() {
      this.field15
         .login(
            null,
            com.lunarclient.websocket.emote.v1.LoginRequest.getDefaultInstance(),
            var1 -> {
               ArrayList var2 = new ArrayList();
               var1.getOwnedEmotesList()
                  .forEach(
                     var2x -> {
                        List var3x = var2x.getRecommendedJamsList().isEmpty()
                           ? new ArrayList()
                           : var2x.getRecommendedJamsList().stream().map(RecommendedJam::getJamId).toList();
                        com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo var4 = var2x.hasGiftInfo()
                           ? new com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo(
                              ThreadModuleDump66.method1(var2x.getGiftInfo().getGiftedBy().getUuid()),
                              var2x.getGiftInfo().getGiftedBy().getUsername(),
                              var2x.getGiftInfo().getMessage(),
                              var2x.getGiftInfo().getIsAnonymous()
                           )
                           : null;
                        EmoteGift var5x = new EmoteGift(
                           var2x.getEmoteId(), var2x.getExpiresAt().getSeconds() * 1000L, ThreadModuleDump66.method5(var2x.getGrantedAt()), var3x, var4
                        );
                        EmoteDefinition var6x = (EmoteDefinition)EmoteManager.field3.get(var2x.getEmoteId());
                        if (var6x != null) {
                           var6x.method5(var5x);
                        }

                        if (!var1.getHasAllEmotesFlag()) {
                           var2.add(var5x);
                        }
                     }
                  );
               if (var1.getHasAllEmotesFlag()) {
                  EmoteManager.field3.forEach((var1x, var2x) -> {
                     EmoteGift var3x = new EmoteGift(var1x, -1L, null, new ArrayList<>(), null);
                     var2x.method5(var3x);
                     var2.add(var3x);
                  });
               }

               this.field37.method45().method17(var2);
               HashSet var3 = new HashSet();

               for (EquippedEmote var5 : var1.getEquippedEmotesList()) {
                  EmoteGiftProvider var6 = new EmoteGiftProvider(var5.getEmoteId(), var5.getSlotNumber(), var5.getAttachedJamId());
                  var3.removeIf(var1x -> var1x.getSlotId() == var6.getSlotId());
                  var2.stream().filter(var1x -> var1x.id() == var5.getEmoteId()).findFirst().ifPresent(var6::method3);
                  var3.add(var6);
               }

               this.field37.method45().method18(var3);
               this.field37.method45().method27(var1.getLunarPlusFreeEmoteId());
               this.field37.method45().method28(this.field37.method45().method24().stream().anyMatch(var1x -> var1x.id() == var1.getLunarPlusFreeEmoteId()));
               this.field37.method45().method21();
               this.field37.method45().method22();
            }
         );
   }

   private void method8() {
      this.field12.login(null, com.lunarclient.websocket.cosmetic.v2.LoginRequest.getDefaultInstance(), var1 -> {
         this.field37.method54().method4(var1);
         this.field37.method53().method10(var1);
         this.field37.method54().method7();
         this.field37.method55().method3(var1.getOutfitsList(), var1.getOutfitTree());
         this.method74(var1.getCosmeticOwnershipVisibility());
         if (this.field37.method31() != null) {
            this.field37.method31().setRank(var1.getRankName());
            this.field37.method31().method49(var1.getArtistTools());
            this.field37.method31().method50(var1.getTesterTools());
            if (!LunarBuildData.field4 || var1.getArtistTools()) {
               GeckolibDebugMod var2 = ThreadModuleDump63.method4().method40().method73();
               var2.method15();
               if (!var2.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field10)) {
                  var2.method2(Framework.field10, Framework12.method9());
                  var2.method2(Framework.field15, var2.CCCRRRCHOCOCRIIORCOIHHOIRIORRI());
                  var2.method2(Framework.field1, var2.method13());
                  ModEnabledState var3 = (ModEnabledState)var2.method7(Framework.field6);
                  if (var3 instanceof com.moonsworth.lunar.client.framework.mod.Nameplate var4) {
                     var4.unlock();
                  }
               }

               ShaderDebugMod var8 = ThreadModuleDump63.method4().method40().method74();
               var8.method16();
               if (!var8.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field10)) {
                  var8.method6(Framework.field10, Framework12.method9());
                  var8.method6(Framework.field15, var8.CCCRRRCHOCOCRIIORCOIHHOIRIORRI());
                  ModEnabledState var11 = (ModEnabledState)var8.method7(Framework.field6);
                  if (var11 instanceof com.moonsworth.lunar.client.framework.mod.Nameplate var5) {
                     var5.unlock();
                  }
               }

               ThreadModuleDump63.method4().method53().method1(false);
            }

            if (var1.getTesterTools()) {
               ModsEnabledDebugMod var7 = ThreadModuleDump63.method4().method40().method75();
               if (!var7.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field10)) {
                  var7.method12(Framework.field10, Framework12.method9());
                  var7.method12(Framework.field15, var7.CCCRRRCHOCOCRIIORCOIHHOIRIORRI());
                  var7.method12(Framework.field1, var7.method13());
                  ModEnabledState var9 = (ModEnabledState)var7.method7(Framework.field6);
                  if (var9 instanceof com.moonsworth.lunar.client.framework.mod.Nameplate var12) {
                     var12.unlock();
                  }
               }

               OptimizationDebugMod var10 = ThreadModuleDump63.method4().method40().method76();
               var10.method17();
               if (!var10.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field10)) {
                  var10.method12(Framework.field10, Framework12.method9());
                  var10.method12(Framework.field15, var10.CCCRRRCHOCOCRIIORCOIHHOIRIORRI());
                  var10.method12(Framework.field1, var10.method15());
                  ModEnabledState var13 = (ModEnabledState)var10.method7(Framework.field6);
                  if (var13 instanceof com.moonsworth.lunar.client.framework.mod.Nameplate var15) {
                     var15.unlock();
                  }
               }

               SkyblockDebugMod var14 = ThreadModuleDump63.method4().method40().method77();
               if (!var14.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field10)) {
                  var14.method2(Framework.field10, Framework12.method9());
                  var14.method2(Framework.field15, var14.CCCRRRCHOCOCRIIORCOIHHOIRIORRI());
                  ModEnabledState var16 = (ModEnabledState)var14.method7(Framework.field6);
                  if (var16 instanceof com.moonsworth.lunar.client.framework.mod.Nameplate var6) {
                     var6.unlock();
                  }
               }

               FpsDebugMod var17 = ThreadModuleDump63.method4().method40().method78();
               if (!var17.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field10)) {
                  var17.method6(Framework.field10, Framework12.method9());
                  var17.method6(Framework.field15, var17.CCCRRRCHOCOCRIIORCOIHHOIRIORRI());
               }
            }
         }

         this.field36.method3("cosmeticState", EntityRendererType2.READY.getId());
      });
   }

   private void method9() {
      this.field14.login(null, com.lunarclient.websocket.store.v1.LoginRequest.getDefaultInstance(), var1 -> {
         this.method72(var1.getIncomingGiftPrivacy());
         this.method73(var1.getCoins());
      });
   }

   private void method10() {
      this.field29
         .login(
            null, com.lunarclient.websocket.socials.v1.LoginRequest.getDefaultInstance(), var1 -> this.field37.method101().method1(var1.getLinkedSocialsList())
         );
   }

   private void method11() {
      this.field17
         .login(
            null,
            com.lunarclient.websocket.badge.v1.LoginRequest.getDefaultInstance(),
            var1 -> {
               BadgeManager var2 = this.field37.method95();
               List var3;
               if (var1.getHasAllBadgesFlag()) {
                  var3 = var2.IORHHHROCRRHORHRCHCCHHIHICCRCO().values().stream().map(com.moonsworth.lunar.client.account.OwnedBadge::method3).toList();
               } else {
                  var3 = var1.getOwnedBadgesList()
                     .stream()
                     .map(com.moonsworth.lunar.client.account.OwnedBadge::method2)
                     .filter(Optional::isPresent)
                     .map(Optional::get)
                     .toList();
               }

               var2.method3(var3);
               var2.method4(var1.getEquippedBadgeId());
            }
         );
   }

   private void method12() {
      this.field30
         .login(
            null,
            com.lunarclient.websocket.jam.v1.LoginRequest.getDefaultInstance(),
            var0 -> ThreadModuleDump63.method4().method73().method12(var0.getOwnedJamsList())
         );
   }

   private void method13() {
      this.field16.login(null, com.lunarclient.websocket.spray.v1.LoginRequest.getDefaultInstance(), var0 -> {
         Object2LongOpenHashMap var1 = new Object2LongOpenHashMap();
         SprayManager var2 = ThreadModuleDump63.method4().method46();
         if (!var0.getHasAllSpraysFlag()) {
            var0.getOwnedSpraysList().forEach(var2x -> {
               com.moonsworth.lunar.client.cosmetics.SprayEntry var3x = var2.method5(var2x.getSprayId());
               var1.put(var3x, var2x.getExpiresAt().getSeconds() * 20L);
            });
         } else {
            ObjectIterator var3 = var2.method40().values().iterator();

            while (var3.hasNext()) {
               com.moonsworth.lunar.client.cosmetics.SprayEntry var4 = (com.moonsworth.lunar.client.cosmetics.SprayEntry)var3.next();
               var1.put(var4, -1L);
            }
         }

         var2.method19(var1);
         var2.method20(new LinkedHashSet<>(var0.getEquippedSpraysList()));
         var2.method37(var0.getLunarPlusFreeSprayIdsList());
         var2.method46(var0.getMaxActiveSprays());
      });
   }

   public void method14() {
      this.field32.login(null, com.lunarclient.websocket.promotion.v1.LoginRequest.getDefaultInstance(), var1 -> {
         if (var1.getMedalPromotionState() == MedalPromotionState.MEDAL_PROMOTION_STATE_AVAILABLE) {
            this.field37.method97().method5(PromotionType.PROMOTION_TYPE_MEDAL);
         } else {
            this.field37.method97().method6(PromotionType.PROMOTION_TYPE_MEDAL);
         }

         this.field37.method97().method2(var1.getPendingRewardsList());
      });
   }

   public void onClose(int var1, String var2, boolean var3) {
      this.field6.method2();
      String var4 = field4 == null ? null : field4.method2().orElse(null);
      field4 = new EntityRenderer3(this.field9);
      this.field9 = null;
      if (field4.method2().isEmpty() && var4 != null) {
         field4.method3(var4);
      }

      this.method66(EntityRendererType2.READY);
      Slayer.method4("Assets", "Connection closed (%d, \"%s\")", var1, var2);
      Inventorymod2.method1(Breadcrumb.debug("Disconnected from the AssetServer"));
      this.method70(false);
      this.method71(LastSeenVisibility.LAST_SEEN_VISIBILITY_UNSPECIFIED);
      this.method72(IncomingGiftPrivacy.INCOMING_GIFT_PRIVACY_UNSPECIFIED);
      this.method74(CosmeticOwnershipVisibility.COSMETIC_OWNERSHIP_VISIBILITY_UNSPECIFIED);
      this.method73(0L);
      field3 = Location.newBuilder().setInMenus(InMenus.newBuilder().build()).build();
      this.method77();
      this.field37.method74().method9();
      this.field37.method45().method4();
      this.field37.method101().method4();
      this.field37.method54().method6();
      this.field37.method52().method4();
   }

   public void onMessage(ByteBuffer var1) {
      ClientboundWebSocketMessage var2;
      try {
         var2 = ClientboundWebSocketMessage.parseFrom(var1);
      } catch (InvalidProtocolBufferException var10) {
         Slayer.method8("Assets", "Failed to parse clientbound WebSocket message");
         var10.printStackTrace();
         return;
      }

      if (var2.getContentsCase() == ContentsCase.RPC_RESPONSE) {
         this.field6.method1(var2.getRpcResponse());
      } else if (var2.getContentsCase() == ContentsCase.PUSH_NOTIFICATION) {
         Any var3 = var2.getPushNotification();
         if (var3.is(WebSocketRichClosePush.class)) {
            try {
               this.field9 = (WebSocketRichClosePush)var3.unpack(WebSocketRichClosePush.class);
            } catch (InvalidProtocolBufferException var8) {
            }

            return;
         }

         for (Entry var5 : this.field7.entrySet()) {
            if (var3.is((Class)var5.getKey())) {
               Message var6;
               try {
                  var6 = var3.unpack((Class)var5.getKey());
               } catch (InvalidProtocolBufferException var9) {
                  Slayer.method8("Assets", "Failed to parse wrapped message for " + var3.getTypeUrl());
                  return;
               }

               ThreadModuleDump13.method1(var6, (Consumer<Message>)var5.getValue());
               return;
            }
         }

         Slayer.method6("Assets", "Failed to find handler for push notification " + var3.getTypeUrl());
      } else {
         Slayer.method8("Assets", "Unknown response type to consume");
      }
   }

   public void onError(Exception var1) {
      Slayer.method8("Assets", "WS-Error:" + var1.getMessage());
      if (ThreadModuleDump63.method34(Gui2Extension.ASSET_SERVER)) {
         var1.printStackTrace();
      }
   }

   public void onMessage(String var1) {
   }

   public void method15(InboundLocation var1, Trigger var2, @Nullable Runnable var3) {
      field2 = var1;
      this.field18
         .broadcastLocationChange(
            null,
            BroadcastLocationChangeRequest.newBuilder().setNewLocation(var1).setTrigger(var2).build(),
            var3x -> {
               field3 = var3x.getNewLocation();
               this.method77();
               ThreadModuleDump63.method6()
                  .ifPresent(
                     var2xx -> var2xx.method13()
                        .updateLocation(
                           null, UpdateLocationRequest.newBuilder().setLocation(var3x.getNewLocation()).setInboundLocation(var1).build(), var0x -> {}
                        )
                  );
               if (var3 != null) {
                  var3.run();
               }
            }
         );
   }

   public void method16(ServerRichStatus var1) {
      if (!field2.hasServer()) {
         Slayer.method6("Assets", "Rich server status received while not on a server. (" + field2.getLocationCase() + ")");
      } else {
         InboundServer var2 = field2.getServer();
         if (!var2.getRichStatus().equals(var1)) {
            InboundServer var3 = var2.toBuilder().setRichStatus(var1).build();
            InboundLocation var4 = InboundLocation.newBuilder().setServer(var3).build();
            this.method15(var4, Trigger.TRIGGER_SERVER_RICH_STATUS, null);
         }
      }
   }

   public void method17(@NotNull Bridge3_19 var1, InteractionType var2) {
      this.field25
         .recordPinnedServerInteraction(
            null, RecordPinnedServerInteractionRequest.newBuilder().setServerIp(var1.bridge$serverIP()).setInteractionType(var2).build(), var0 -> {}
         );
   }

   public void method18(OpenTebexJsCheckoutPush var1) {
      ThreadModuleDump42.method1(var1.getBasketIdent(), var1.getLocale());
   }

   public void method19(OpenPayNowCheckoutPush var1) {
      ThreadModuleDump42.method2(var1.getCheckoutToken());
   }

   public void method20(ConversationMessagePush var1) {
      this.field37.method52().method7(var1);
   }

   public void method21(ConversationPreSendActionPush var1) {
      this.field37.method52().method8(var1);
   }

   public void method22(ConversationOwnerUpdatePush var1) {
      this.field37.method52().method9(var1);
   }

   public void method23(ConversationAddParticipantsPush var1) {
      this.field37.method52().method10(var1);
   }

   public void method24(ConversationRemoveParticipantPush var1) {
      this.field37.method52().method11(var1);
   }

   public void method25(ConversationUpdateNamePush var1) {
      this.field37.method52().method12(var1);
   }

   public void method26(ConversationUpdateIconPush var1) {
      this.field37.method52().method13(var1);
   }

   public void method27(ConversationAddPinnedMessagePush var1) {
      this.field37.method52().method14(var1);
   }

   public void method28(ConversationRemovePinnedMessagePush var1) {
      this.field37.method52().method15(var1);
   }

   public void method29(ConversationUpdateInvitePolicyPush var1) {
      this.field37.method52().method16(var1);
   }

   public void method30(ConversationUpdateNamePolicyPush var1) {
      this.field37.method52().method17(var1);
   }

   public void method31(ConversationUpdateIconPolicyPush var1) {
      this.field37.method52().method18(var1);
   }

   public void method32(ConversationUpdateMessagePinningPolicyPush var1) {
      this.field37.method52().method19(var1);
   }

   public void method33(ConversationAddedPush var1) {
      this.field37.method52().method20(var1);
   }

   public void method34(ConversationRemovedPush var1) {
      this.field37.method52().method21(var1);
   }

   public void method35(ConversationRemoveMessagePush var1) {
      this.field37.method52().method22(var1);
   }

   public void method36(ConversationMessageHistoryDeletedPush var1) {
      this.field37.method52().method23(var1);
   }

   public void method37(RefreshConversationsPush var1) {
      this.field37.method52().method24();
   }

   public void method38(FriendRequestAcceptedPush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getNewFriend().getUuid());
      this.field37.method51().method2(var2);
      Memory var3 = this.field37.method50().method2(var2);
      if (var3 == null) {
         var3 = new Memory(var2, var1.getNewFriend().getUsername());
         this.field37.method50().method4(var3);
      }

      this.field37.method69().method11(var2, AdventureChatFormatting.BOLD + var3.getName() + AdventureChatFormatting.RESET + " has accepted your friend request!");
   }

   public void method39(JoinHostedWorldPush var1) {
      Glintcolorizer var2 = ThreadModuleDump63.method3().bridge$getIntegratedServer();
      UUID var3 = ThreadModuleDump66.method1(var1.getJoinerUuid());
      if (var2 != null && var2.bridge$getPublishedPort() != -1) {
         ThreadModuleDump63.method4().method81().method32().add(var1.getRelaySessionId());
         ThreadModuleDump63.method4().method81().method2(var3, var1.getSimpleVoiceChatEndpoint());
         new Coordinates3(var1.getRelaySessionId(), var1.getRelay().getAddress(), var1.getRelay().getPort(), var2.bridge$getPublishedPort());
      }
   }

   public void method40(HostedWorldStatusPush var1) {
      FogIterator var2 = ThreadModuleDump63.method4().method81();
      if (!var2.method20()) {
         var2.method36(var1);
         this.field37.method50().method9();
      }
   }

   public void method41(HostedWorldAvailablePush var1) {
      Memory var2 = ThreadModuleDump63.method4().method50().method2(ThreadModuleDump66.method1(var1.getWorldHost().getUuid()));
      if (var2 == null || var2.method6()) {
         ThreadModuleDump63.method4().method69().method9(NotificationManager.method15("hostedWorldAvailable", var1.getWorldHost().getUsername()));
      }
   }

   public void method42(HostedWorldWhitelistedPush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getWorldHost().getUuid());
      Memory var3 = this.field37.method50().method2(var2);
      if (var3 != null) {
         var3.method39(true);
         ThreadModuleDump63.method4()
            .method69()
            .method10(new FriendNotificationLong(var2, var3, NotificationManager.method15("hostedWorldWhitelisted", var1.getWorldHost().getUsername())));
      }
   }

   public void method43(FriendRequestDeniedPush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getDenierUuid());
      this.field37.method51().method2(var2);
   }

   public void method44(FriendRequestCanceledPush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getSenderUuid());
      this.field37.method51().method2(var2);
   }

   public void method45(FriendStatusPush var1) {
      if (var1.getFriendCase() == FriendCase.ONLINE_FRIEND) {
         OnlineFriend var2 = var1.getOnlineFriend();
         UUID var3 = ThreadModuleDump66.method1(var2.getPlayer().getUuid());
         Memory var4 = this.field37.method50().method2(var3);
         if (var4 == null) {
            var4 = new Memory(var3, var2.getPlayer().getUsername());
         } else {
            var4.setName(var2.getPlayer().getUsername());
         }

         EntityRendererType var5 = EntityRendererType.fromProtobuf(var2.getStatus());
         if (this.field37.method41().method6().method31().get() && var2.getJustCameOnline()) {
            this.field37
               .method69()
               .method11(var4.method10(), AdventureChatFormatting.GREEN.toString() + AdventureChatFormatting.BOLD + var4.getName() + AdventureChatFormatting.WHITE + " is now online");
         }

         var4.method47((Gui2Handler2)ThreadModuleDump63.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(var2.getBadgeId()));
         var4.setRank(var2.getRankName());
         var4.method46(var2.getLogoColor().getColor());
         var4.method45(var2.getPlusColor().getColor());
         var4.method4(var5);
         var4.method35(System.currentTimeMillis());
         var4.method33(var2.getLocation());
         var4.method38(var2.getHostedWorldJoinability());
         if (var4.getHostedWorldJoinability() != Joinability.JOINABILITY_ALLOWED) {
            var4.method39(false);
         }

         Config.get(var2.getMinecraftVersion().getEnum()).ifPresent(var4::method34);
         if (var2.hasFriendsSince()) {
            var4.method36(ThreadModuleDump66.method5(var2.getFriendsSince()));
         }

         FriendSocials var6 = var2.getSocials();
         if (var2.hasSocials()) {
            var4.method37(var6);
         }

         if (!this.field37.method50().method3(var4.method10())) {
            this.field37.method50().method4(var4);
            this.method3(var4.method10(), FriendRadioInfoPush.class);
         }

         this.field37.method50().method9();
      } else if (var1.getFriendCase() == FriendCase.OFFLINE_FRIEND) {
         OfflineFriend var7 = var1.getOfflineFriend();
         UUID var8 = ThreadModuleDump66.method1(var7.getPlayer().getUuid());
         Memory var9 = this.field37.method50().method2(var8);
         if (var9 == null) {
            var9 = new Memory(var8, var7.getPlayer().getUsername());
         } else {
            var9.setName(var7.getPlayer().getUsername());
         }

         var9.method47((Gui2Handler2)ThreadModuleDump63.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(var7.getBadgeId()));
         var9.setRank(var7.getRankName());
         var9.method46(var7.getLogoColor().getColor());
         var9.method45(var7.getPlusColor().getColor());
         var9.method33(null);
         var9.method4(EntityRendererType.OFFLINE);
         if (var7.hasLastVisibleOnline()) {
            var9.method35(var7.getLastVisibleOnline().getSeconds() * 1000L);
         } else {
            var9.method35(-1L);
         }

         if (var7.hasFriendsSince()) {
            var9.method36(ThreadModuleDump66.method5(var7.getFriendsSince()));
         }

         FriendSocials var10 = var7.getSocials();
         if (var7.hasSocials()) {
            var9.method37(var10);
         }

         if (!this.field37.method50().method3(var9.method10())) {
            this.field37.method50().method4(var9);
            this.method3(var9.method10(), FriendRadioInfoPush.class);
         }

         this.field37.method50().method9();
      } else {
         Slayer.method8("Assets", "Unknown friend case " + var1.getFriendCase());
      }
   }

   public void method46(FriendRadioInfoPush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getFriendUuid());
      Memory var3 = this.field37.method50().method2(var2);
      if (var3 == null) {
         this.method4(var2, var1);
      } else {
         var3.method44(var1.getRadioInfo());
         this.field37.method50().method9();
      }
   }

   public void method47(DisplayChatMessagePush var1) {
      Bridge5_12 var2 = ThreadModuleDump63.method3();
      if (var2.bridge$getGuiIngame() != null && var2.bridge$getGuiIngame().bridge$getChatGUI() != null) {
         Bridge5Extension4 var3 = var2.bridge$getGuiIngame().bridge$getChatGUI();
         var3.bridge$addMessage(Bridge.method8().method13(var1.getChatMessage()));
      }
   }

   public void method48(DisplayNotificationPush var1) {
      String var2 = var1.getNotificationTitle();
      String var3 = var1.getNotificationMessage();
      Slayer.method4("Assets", AdventureChatFormatting.getTextWithoutFormattingCodes("[" + var2 + "] " + var3));
      this.field37.method69().method2(var2, var3);
   }

   public void method49(FriendRequestReceivedPush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getSender().getUuid());
      Serializable var3 = var1.getSender().getUsername();
      EntityRenderer6 var4 = new EntityRenderer6(
         ThreadModuleDump66.method1(var1.getSender().getUuid()),
         var1.getSender().getUsername(),
         Instant.now(),
         var1.getSenderLogoColor().getColor(),
         var1.getSenderPlusColor().getColor(),
         var1.getSenderBadgeId() > 0
            ? (Gui2Handler2)ThreadModuleDump63.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(var1.getSenderBadgeId())
            : null,
         var1.getSenderRankName()
      );
      this.field37.method51().method4(var4);
      this.field37.method69().method11(var2, AdventureChatFormatting.BOLD + var3 + AdventureChatFormatting.RESET + " wants to be your friend");
   }

   public void method50(FriendRemovedYouPush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getFriendUuid());
      Memory var3 = this.field37.method50().method2(var2);
      if (var3 != null) {
         this.field37.method50().method5(var2);
      }
   }

   public void method51(PlayerCosmeticsPushV2 var1) {
      if (!ThreadModuleDump63.method4().method40().method85().method19() || this.field35) {
         UUID var2 = ThreadModuleDump66.method1(var1.getPlayerUuid());
         this.field37.method53().method9(var2, var1, var1.getBadgeId());
         this.field37.method55().method2(var1);
         ThreadModuleDump63.method4().method88().method11(var2);
         this.method55(var1);
         this.method3(var2, PlayerRadioPush.class);
      }
   }

   public void method52(PlayerRadioPush var1) {
      if (!ThreadModuleDump63.method4().method40().method85().method19() || this.field35) {
         UUID var2 = ThreadModuleDump66.method1(var1.getPlayerUuid());
         this.field37.method53().method63().compute(var2, (var2x, var3) -> {
            if (var3 == null) {
               this.method4(var2x, var1);
               return null;
            } else {
               return var3.method3(var1.getRadioPlaying());
            }
         });
      }
   }

   public void method53(UseEmotePush var1) {
      if (!ThreadModuleDump63.method4().method40().method85().method19() || this.field35) {
         UUID var2 = ThreadModuleDump66.method1(var1.getPlayerUuid());
         if (!ThreadModuleDump63.method4().method41().method6().method37().get()) {
            Bridge5Extension_5 var5 = ThreadModuleDump63.method3().bridge$getPlayer();
            if (var5 != null && var2.equals(var5.bridge$getUniqueID())) {
               ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, ThreadModuleDump63.method4().method67().method2("popups", "emotesDisabled"));
            }
         } else {
            Emote var3 = this.field37.method45().method13(var1.getEmoteId());
            Itemcounter6Extension var4 = ThreadModuleDump63.method8();
            if (var4 != null) {
               var4.bridge$getPlayerByUniqueId(var2).ifPresent(var3x -> {
                  if (ThreadModuleDump63.method4().method41().method6().method7(var3x)) {
                     StyngrSong var4x = JamManager.method12().get(var1.getEmoteJamId());
                     this.field37.method45().method7(var3x, var3, var1.getEmoteMetadata(), var1.getEmoteSoundtrackUrl(), var4x != null ? var4x.getId() : 0);
                  }
               });
            }

            this.method55(var1);
         }
      }
   }

   public void method54(StopEmotePush var1) {
      if (!ThreadModuleDump63.method4().method40().method85().method19() || this.field35) {
         UUID var2 = ThreadModuleDump66.method1(var1.getPlayerUuid());
         Itemcounter6Extension var3 = ThreadModuleDump63.method8();
         if (var3 != null) {
            var3.bridge$getPlayerByUniqueId(var2).ifPresent(var1x -> this.field37.method45().method7(var1x, null, 0, null, 0));
         }

         this.method55(var1);
      }
   }

   private void method55(Message var1) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      Any var2;
      if (var3.isRecording()) {
         var2 = Any.pack(var1);
         var3.method34().method1(var2);
      } else {
         var2 = null;
      }

      Fishing.method2(Fishing2Extension.class).ifPresent(var2x -> {
         if (Client.method109().method40().method64().isEnabled()) {
            var2x.method8(var2 == null ? Any.pack(var1) : var2);
         }
      });
   }

   public void method56(UseSprayPush var1) {
      if (!ThreadModuleDump63.method4().method40().method85().method19() || this.field35) {
         UUID var2 = ThreadModuleDump66.method1(var1.getPlayerUuid());
         if (!ThreadModuleDump63.method4().method41().method6().method35().get()) {
            Bridge5Extension_5 var4 = ThreadModuleDump63.method3().bridge$getPlayer();
            if (var4 != null && var2.equals(var4.bridge$getUniqueID())) {
               ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, ThreadModuleDump63.method4().method67().method2("popups", "spraysDisabled"));
            }
         } else {
            Itemcounter6Extension var3 = ThreadModuleDump63.method8();
            if (var3 != null) {
               var3.bridge$getPlayerByUniqueId(var2).ifPresent(var3x -> {
                  if (ThreadModuleDump63.method4().method41().method6().method8(var3x)) {
                     Vector3f var4x = ThreadModuleDump66.method13(var1.getPos());
                     SprayManager var5 = this.field37.method46();
                     com.moonsworth.lunar.client.cosmetics.SprayEntry var6 = var5.method5(var1.getSprayId());
                     if (var6 != null) {
                        SprayPlacementTracker var7 = var5.method14(var6, var4x, ThreadModuleDump66.method11(var1.getFacing()), var1.getRotation());
                        if (var7 != null) {
                           double var8 = var3x.bridge$blockInteractionRange() * 1.6;
                           if (var3x.HROHOIOCHIRIHICOORIHOHCIOIRIIH(var4x.x, var4x.y, var4x.z) <= var8) {
                              var5.method2(var2, var7, var1.getMaxActiveSprays(), false);
                           }
                        }
                     }
                  }
               });
            }

            this.method55(var1);
         }
      }
   }

   public void method57(RemoveSprayPush var1) {
      if (!ThreadModuleDump63.method4().method40().method85().method19() || this.field35) {
         this.field37.method46().method3(ThreadModuleDump66.method1(var1.getPlayerUuid()), ThreadModuleDump66.method13(var1.getPos()));
         this.method55(var1);
      }
   }

   public void method58(RefreshMetadataPush var1) {
      ThreadModuleDump63.method4().method72().method7(null);
   }

   public void method59(RefreshEmotesPush var1) {
      this.method7();
   }

   public void method60(RefreshCosmeticsPush var1) {
      this.method8();
   }

   public void method61(RefreshSocialsPush var1) {
      this.method10();
   }

   public void method62(RefreshBadgesPush var1) {
      this.method11();
   }

   public void method63(RefreshJamsPush var1) {
      this.method12();
   }

   public void method64(DungeonUpdatePush var1) {
      DynamicListener.method9(GuiRewindhandlersHandler2_2.class)
         .flatMap(GuiRewindhandlersHandler2_2::method5)
         .ifPresent(var1x -> var1x.method45().method1(var1));
   }

   private void method65(NewMarkerPush var1) {
      UUID var2 = ThreadModuleDump66.method1(var1.getSenderUuid());
      ThreadModuleDump63.method4().method40().method87().method51().method6(var2, var1.getMarker());
   }

   private void method66(EntityRendererType2 var1) {
      this.field38 = var1;
      this.field36.method3("state", var1.getId());
      if (var1 == EntityRendererType2.READY) {
         this.field36.method3("cosmeticState", var1.getId());
         this.method67();
      }
   }

   private void method67() {
      this.field37.method79().method2();
      this.method68();
   }

   private void method68() {
      this.field36.method3("serverDiscovery", this.field37.method79().method3());
   }

   public void method69(ServerSectionCardsPush var1) {
      this.field37.method79().method8(var1);
   }

   public void method70(boolean var1) {
      this.field39 = var1;
      this.field36.method3("allowFriendRequests", var1);
   }

   public void method71(LastSeenVisibility var1) {
      this.field40 = var1;
      this.field36.method3("lastSeenVisibility", var1.name());
   }

   public void method72(IncomingGiftPrivacy var1) {
      this.field41 = var1;
      this.field36.method3("incomingGiftPrivacy", var1.name());
   }

   public void method73(long var1) {
      this.field43 = var1;
      this.field36.method3("coins", var1);
   }

   public void method74(CosmeticOwnershipVisibility var1) {
      this.field42 = var1;
      this.field36.method3("cosmeticOwnershipVisibility", var1.name());
   }

   public static void bootstrap() {
      ClientEventBus.method29().method2(EventEverySecond.class, var0 -> {
         if (Client.method109() != null && !Client.method109().method13()) {
            if (ThreadModuleDump63.method5().isEmpty() || ThreadModuleDump63.method5().get().method112() == EntityRendererType2.READY) {
               if (field4 == null) {
                  Slayer.method6("Assets Reconnect", "There was no closedConnection for a disconnected player.");
                  field4 = new EntityRenderer3(null);
               }

               if (field4.method1()) {
                  ThreadModuleDump63.method4().method7(field4);
                  Slayer.method4("Assets Reconnect", "Reconnecting to the assets server...");
               }
            }
         }
      });
      ClientEventBus.method29()
         .method2(
            DisconnectReasonEvent.class,
            var0 -> ThreadModuleDump63.method5()
               .ifPresent(
                  var1 -> var1.method93()
                     .broadcastServerKick(null, BroadcastServerKickRequest.newBuilder().setKickReason(var0.getReason()).build(), var0xx -> {})
               )
         );
      ClientEventBus.method29()
         .method2(
            DisconnectEvent.class,
            var0 -> ThreadModuleDump63.method5()
               .ifPresent(
                  var0x -> var0x.method15(
                     InboundLocation.newBuilder().setInMenus(InboundInMenus.getDefaultInstance()).build(), Trigger.TRIGGER_DISCONNECT_EVENT, null
                  )
               )
         );
      ClientEventBus.method29().method2(ServerJoinEvent.class, var0 -> method75());
   }

   public static void method75() {
      FogIterator var0 = ThreadModuleDump63.method4().method81();
      if (!var0.method20()) {
         EntityRenderer4 var1 = ThreadModuleDump63.method5().orElse(null);
         if (var1 != null) {
            Runnable var3 = null;
            InboundLocation var2;
            if (var0.method39() != null) {
               var2 = InboundLocation.newBuilder()
                  .setHostedWorld(InboundHostedWorld.newBuilder().setWorldHost(ThreadModuleDump66.method3(var0.method39())).build())
                  .build();
            } else if (ThreadModuleDump63.method4().method40().method85().method19()) {
               RewindHandlers var4 = ThreadModuleDump63.method4().method40().method85().method35();
               var2 = InboundLocation.newBuilder()
                  .setRewindWorld(
                     InboundRewindWorld.newBuilder()
                        .setType(
                           var4.method40() != null && var4.method40().method31()
                              ? RewindWorldType.REWIND_WORLD_TYPE_PREVIEW
                              : RewindWorldType.REWIND_WORLD_TYPE_PROJECT
                        )
                        .build()
                  )
                  .build();
            } else if (ThreadModuleDump63.method4().method40().method64().method13()) {
               var2 = InboundLocation.newBuilder().setReplayWorld(InboundReplayWorld.getDefaultInstance()).build();
            } else if (ThreadModuleDump63.method3().bridge$getCurrentServerData() == null) {
               var2 = InboundLocation.newBuilder().setSinglePlayer(InboundSinglePlayer.getDefaultInstance()).build();
            } else if (ThreadModuleDump63.method3().bridge$isConnectedToRealms()) {
               var2 = InboundLocation.newBuilder().setMinecraftRealms(InboundMinecraftRealms.getDefaultInstance()).build();
            } else {
               Bridge3_19 var5 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
               var2 = InboundLocation.newBuilder().setServer(InboundServer.newBuilder().setServerIp(var5.bridge$serverIP()).build()).build();
               var3 = () -> {
                  if (var5.bridge$wasPinnedClicked()) {
                     var1.method17(var5, InteractionType.INTERACTION_TYPE_PLAY);
                     var5.bridge$setPinnedClicked(false);
                  }
               };
               if (field2.hasServer() && field2.getServer().getServerIp().equals(var2.getServer().getServerIp())) {
                  return;
               }
            }

            var1.method15(var2, Trigger.TRIGGER_JOIN_EVENT, var3);
         }
      }
   }

   public void method76(SessionIdentifyPush var1) {
      field1 = ThreadModuleDump66.method1(var1.getSessionId());
   }

   private void method77() {
      JsonObject var1 = ThreadModuleDump21.method1(
         ThreadModuleDump63.method4().method31().method10(),
         field3,
         ThreadModuleDump63.method4().method31().method16(),
         ThreadModuleDump63.method4().method31().method13()
      );
      this.field36.method3("statusDetails", var1);
   }

   @Generated
   public static UUID method78() {
      return field1;
   }

   @Generated
   public static void method79(UUID var0) {
      field1 = var0;
   }

   @Generated
   public static InboundLocation method80() {
      return field2;
   }

   @Generated
   public static void method81(InboundLocation var0) {
      field2 = var0;
   }

   @Generated
   public static Location method82() {
      return field3;
   }

   @Generated
   public static void method83(Location var0) {
      field3 = var0;
   }

   @Generated
   public static void method84(EntityRenderer3 var0) {
      field4 = var0;
   }

   @Generated
   public Interface method85() {
      return this.field10;
   }

   @Generated
   public com.lunarclient.websocket.conversation.v1.ConversationService.Interface method86() {
      return this.field11;
   }

   @Generated
   public com.lunarclient.websocket.cosmetic.v2.CosmeticService.Interface method87() {
      return this.field12;
   }

   @Generated
   public com.lunarclient.websocket.radio.v1.RadioService.Interface method88() {
      return this.field13;
   }

   @Generated
   public com.lunarclient.websocket.store.v1.StoreService.Interface method89() {
      return this.field14;
   }

   @Generated
   public com.lunarclient.websocket.emote.v1.EmoteService.Interface method90() {
      return this.field15;
   }

   @Generated
   public com.lunarclient.websocket.spray.v1.SprayService.Interface method91() {
      return this.field16;
   }

   @Generated
   public com.lunarclient.websocket.badge.v1.BadgeService.Interface method92() {
      return this.field17;
   }

   @Generated
   public com.lunarclient.websocket.friend.v1.FriendService.Interface method93() {
      return this.field18;
   }

   @Generated
   public com.lunarclient.websocket.heartbeat.v1.HeartbeatService.Interface method94() {
      return this.field19;
   }

   @Generated
   public com.lunarclient.websocket.notification.v1.NotificationService.Interface method95() {
      return this.field20;
   }

   @Generated
   public com.lunarclient.websocket.subscription.v1.SubscriptionService.Interface method96() {
      return this.field21;
   }

   @Generated
   public com.lunarclient.websocket.legacyapi.v1.LegacyApiService.Interface method97() {
      return this.field22;
   }

   @Generated
   public com.lunarclient.websocket.performance.v1.PerformanceService.Interface method98() {
      return this.field23;
   }

   @Generated
   public com.lunarclient.websocket.hostedworld.v1.HostedWorldService.Interface method99() {
      return this.field24;
   }

   @Generated
   public com.lunarclient.websocket.analytics.v1.AnalyticsService.Interface method100() {
      return this.field25;
   }

   @Generated
   public com.lunarclient.websocket.liveexperience.v1.LiveExperienceService.Interface method101() {
      return this.field26;
   }

   @Generated
   public com.lunarclient.websocket.screenshot.v1.ScreenshotService.Interface method102() {
      return this.field27;
   }

   @Generated
   public com.lunarclient.websocket.server.v1.ServerService.Interface method103() {
      return this.field28;
   }

   @Generated
   public com.lunarclient.websocket.socials.v1.SocialsService.Interface method104() {
      return this.field29;
   }

   @Generated
   public com.lunarclient.websocket.jam.v1.JamService.Interface method105() {
      return this.field30;
   }

   @Generated
   public com.lunarclient.websocket.skyblock.v1.SkyblockService.Interface method106() {
      return this.field31;
   }

   @Generated
   public com.lunarclient.websocket.promotion.v1.PromotionService.Interface method107() {
      return this.field32;
   }

   @Generated
   public com.lunarclient.websocket.marker.v1.MarkerService.Interface method108() {
      return this.field33;
   }

   @Generated
   public com.lunarclient.websocket.serverdiscovery.v1.ServerDiscoveryService.Interface method109() {
      return this.field34;
   }

   @Generated
   public void method110(boolean var1) {
      this.field35 = var1;
   }

   @Generated
   public GuiIterator method111() {
      return this.field36;
   }

   @Generated
   public EntityRendererType2 method112() {
      return this.field38;
   }

   @Generated
   public boolean method113() {
      return this.field39;
   }

   @Generated
   public LastSeenVisibility getLastSeenVisibility() {
      return this.field40;
   }

   @Generated
   public IncomingGiftPrivacy getIncomingGiftPrivacy() {
      return this.field41;
   }

   @Generated
   public CosmeticOwnershipVisibility getCosmeticOwnershipVisibility() {
      return this.field42;
   }

   @Generated
   public long method114() {
      return this.field43;
   }

   @Generated
   public EntityRenderer method115() {
      return this.field44;
   }
}
