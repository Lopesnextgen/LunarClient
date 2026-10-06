package com.moonsworth.lunar.client.network.websocket;

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
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.GuiNewChatBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.server.IntegratedServerBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.account.Badge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.notification.FriendNotificationLong;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldRelay;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink;
import com.moonsworth.lunar.client.audio.music.StyngrSong;
import com.moonsworth.lunar.client.audio.music.JamManager;
import com.moonsworth.lunar.client.account.BadgeManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.cosmetics.SprayManager;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGift;
import com.moonsworth.lunar.client.cosmetics.emote.Emote;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.PanelPosition;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.feature.debug.DebugType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnectReason;
import com.moonsworth.lunar.client.event.mixin.holograms.EventAssetServerConnected;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftProvider;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteDefinition;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.mod.misc.debug.FpsDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.GeckolibDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.ModsEnabledDebugMod;
import com.moonsworth.lunar.client.mod.misc.debug.OptimizationDebugMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.PacketUtil;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.network.friend.FriendStatusUtils;
import com.moonsworth.lunar.client.network.ipc.CheckoutUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import com.moonsworth.lunar.client.framework.LaunchOptions;
import com.moonsworth.lunar.config.Config;
import io.sentry.Breadcrumb;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
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
import com.moonsworth.lunar.client.network.friend.FriendStatus;
import com.moonsworth.lunar.client.mixin.EntityRenderer;
import com.moonsworth.lunar.client.mixin.EntityRenderer6;

public class AssetServerClient extends WebSocketClient implements EventBusAccess {
   private static UUID field1;
   private static InboundLocation field2 = InboundLocation.newBuilder().setInMenus(InboundInMenus.newBuilder().build()).build();
   private static Location field3 = Location.newBuilder().setInMenus(InMenus.newBuilder().build()).build();
   private static ReconnectBackoff field4;
   private final Handshake field5;
   private final WebSocketRpcChannel field6 = new WebSocketRpcChannel(this);
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
   private ConnectionState field38 = ConnectionState.DISCONNECTED;
   private boolean field39;
   private LastSeenVisibility field40;
   private IncomingGiftPrivacy field41;
   private CosmeticOwnershipVisibility field42;
   private long field43;
   private final EntityRenderer field44 = new EntityRenderer();

   public AssetServerClient(Handshake handshake1) {
      super(
         URI.create(ServiceEndpoints.method6() + "/game"),
         new Draft_6455(),
         Map.of("sentry-trace", LaunchOptions.field3, "User-Agent", "Lunar Client " + Client.method19()),
         30000
      );
      this.field44.method2(handshake1.getIdentity().getAuthenticatorJwt());
      this.field5 = handshake1;
      LunarLogger.method4("Assets", "Instantiate", new Object[0]);
      this.field37 = Ref.method4();
      this.method66(ConnectionState.DISCONNECTED);
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

   private <T extends Message> void method1(Class<T> clazz1, Consumer<T> consumer2) {
      this.field7.put(clazz1, consumer2);
      this.field8.put(clazz1, CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.MINUTES).build());
   }

   public boolean method2(Any any1) {
      for (Entry entry3 : this.field7.entrySet()) {
         Class clazz4 = (Class)entry3.getKey();
         if (any1.is(clazz4)) {
            Message message5;
            try {
               message5 = any1.unpack(clazz4);
            } catch (InvalidProtocolBufferException invalidprotocolbufferexception7) {
               LunarLogger.method8("Assets", "Failed to parse wrapped message for " + any1.getTypeUrl(), new Object[0]);
               return false;
            }

            PacketUtil.method1(message5, (Consumer)entry3.getValue());
            return true;
         }
      }

      return false;
   }

   private <T extends Message> void method3(UUID uuid1, Class<T> clazz2) {
      Cache cache3 = this.field8.get(clazz2);
      if (cache3 != null) {
         Message message4 = (Message)cache3.getIfPresent(uuid1);
         if (message4 != null) {
            cache3.invalidate(uuid1);
            Consumer consumer5 = this.field7.get(clazz2);
            PacketUtil.method1(message4, consumer5);
         }
      }
   }

   private <T extends Message> void method4(UUID uuid1, T value2) {
      Class clazz3 = value2.getClass();
      Cache cache4 = this.field8.get(clazz3);
      if (cache4 == null) {
         LunarLogger.method8("Assets", "Attempted to delay unregistered message type: " + clazz3.getSimpleName(), new Object[0]);
      } else {
         cache4.put(uuid1, value2);
      }
   }

   public void onOpen(ServerHandshake serverhandshake1) {
      this.send(this.field5.toByteArray());
      field4 = null;
      this.method66(ConnectionState.READY);
      LunarLogger.method4(
         "Assets", "Connection established as %s (%s)", new Object[]{this.field37.method31().getName(), this.field37.method31().method16().getName()}
      );
      this.field37.method74().method8();
      Ref.method3().bridge$submit(() -> LunarEventBus.method29().method12(EventAssetServerConnected.class, EventAssetServerConnected::new));
      this.method77();
      FogIterator fogiterator2 = Ref.method4().method81();
      if (fogiterator2 != null) {
         fogiterator2.method6();
         this.field24.login(null, LoginRequest.newBuilder().build(), fogiterator2::method19);
      }

      this.field18.login(null, com.lunarclient.websocket.friend.v1.LoginRequest.getDefaultInstance(), arg1x -> {
         Ref.method4().method31().method4(FriendStatus.fromProtobuf(arg1x.getCurrentStatus()));
         this.field37.method50().clear();
         this.field37.method51().clear();
         HashSet set2x = new HashSet();

         for (OnlineFriend onlinefriend4 : arg1x.getOnlineFriendsList()) {
            UUID uuid5 = ProtoConverter.method1(onlinefriend4.getPlayer().getUuid());
            Memory memory6 = new Memory(uuid5, onlinefriend4.getPlayer().getUsername());
            memory6.method4(FriendStatus.fromProtobuf(onlinefriend4.getStatus()));
            memory6.method35(System.currentTimeMillis());
            memory6.method33(onlinefriend4.getLocation());
            memory6.method38(onlinefriend4.getHostedWorldJoinability());
            if (memory6.getHostedWorldJoinability() != Joinability.JOINABILITY_ALLOWED) {
               memory6.method39(false);
            }

            Config.get(onlinefriend4.getMinecraftVersion().getEnum()).ifPresent(memory6::method34);
            memory6.method45(onlinefriend4.getPlusColor().getColor());
            memory6.method46(onlinefriend4.getLogoColor().getColor());
            memory6.method47((Badge)Ref.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(onlinefriend4.getBadgeId()));
            memory6.setRank(onlinefriend4.getRankName());
            if (onlinefriend4.hasFriendsSince()) {
               memory6.method36(ProtoConverter.method5(onlinefriend4.getFriendsSince()));
            }

            FriendSocials friendsocials7 = onlinefriend4.getSocials();
            if (onlinefriend4.hasSocials()) {
               memory6.method37(friendsocials7);
            }

            this.field37.method50().method4(memory6);
            set2x.add(uuid5);
         }

         for (OfflineFriend offlinefriend12 : arg1x.getOfflineFriendsList()) {
            UUID uuid16 = ProtoConverter.method1(offlinefriend12.getPlayer().getUuid());
            if (!set2x.contains(uuid16)) {
               Memory memory18 = new Memory(uuid16, offlinefriend12.getPlayer().getUsername());
               memory18.method33(null);
               memory18.method4(FriendStatus.OFFLINE);
               memory18.method47((Badge)Ref.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(offlinefriend12.getBadgeId()));
               memory18.method46(offlinefriend12.getLogoColor().getColor());
               memory18.method45(offlinefriend12.getPlusColor().getColor());
               memory18.setRank(offlinefriend12.getRankName());
               if (offlinefriend12.hasLastVisibleOnline()) {
                  memory18.method35(offlinefriend12.getLastVisibleOnline().getSeconds() * 1000L);
               } else {
                  memory18.method35(-1L);
               }

               if (offlinefriend12.hasFriendsSince()) {
                  memory18.method36(ProtoConverter.method5(offlinefriend12.getFriendsSince()));
               }

               FriendSocials friendsocials20 = offlinefriend12.getSocials();
               if (offlinefriend12.hasSocials()) {
                  memory18.method37(friendsocials20);
               }

               this.field37.method50().method4(memory18);
            }
         }

         for (Uuid uuid13 : arg1x.getPinnedFriendsList()) {
            UUID uuid17 = ProtoConverter.method1(uuid13);
            Memory memory19 = this.field37.method50().method2(uuid17);
            if (memory19 != null) {
               memory19.method51(true);
            }
         }

         this.method70(arg1x.getAllowFriendRequests());
         this.method71(arg1x.getLastSeenVisibility());

         for (FriendRequest friendrequest14 : arg1x.getOutboundFriendAddRequestsList()) {
            this.field37.method51().method3(EntityRenderer6.method1(friendrequest14));
         }

         for (FriendRequest friendrequest15 : arg1x.getInboundFriendAddRequestsList()) {
            this.field37.method51().method4(EntityRenderer6.method1(friendrequest15));
         }

         if (arg1x.hasUserCreatedAt()) {
            Ref.method4().method43().method33(ProtoConverter.method5(arg1x.getUserCreatedAt()));
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
      this.field11.login(null, com.lunarclient.websocket.conversation.v1.LoginRequest.newBuilder().setUsingSatellite(true).build(), arg1 -> {
         LunarLogger.method4("Conversation", "Conversation login successful", new Object[0]);
         this.field37.method52().method4(arg1);
         this.field37.method52().method24();
      });
   }

   private void method6() {
      this.field34.login(null, com.lunarclient.websocket.serverdiscovery.v1.LoginRequest.getDefaultInstance(), arg1 -> {
         this.field37.method79().method1(arg1);
         this.method68();
      });
   }

   private void method7() {
      this.field15
         .login(
            null,
            com.lunarclient.websocket.emote.v1.LoginRequest.getDefaultInstance(),
            arg1 -> {
               ArrayList list2 = new ArrayList();
               arg1.getOwnedEmotesList()
                  .forEach(
                     arg2x -> {
                        List list3x = arg2x.getRecommendedJamsList().isEmpty()
                           ? new ArrayList()
                           : arg2x.getRecommendedJamsList().stream().map(RecommendedJam::getJamId).toList();
                        com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo gui2handler24 = arg2x.hasGiftInfo()
                           ? new com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftInfo(
                              ProtoConverter.method1(arg2x.getGiftInfo().getGiftedBy().getUuid()),
                              arg2x.getGiftInfo().getGiftedBy().getUsername(),
                              arg2x.getGiftInfo().getMessage(),
                              arg2x.getGiftInfo().getIsAnonymous()
                           )
                           : null;
                        EmoteGift fov2_45x = new EmoteGift(
                           arg2x.getEmoteId(), arg2x.getExpiresAt().getSeconds() * 1000L, ProtoConverter.method5(arg2x.getGrantedAt()), list3x, gui2handler24
                        );
                        EmoteDefinition holograms56x = (EmoteDefinition)EmoteManager.field3.get(arg2x.getEmoteId());
                        if (holograms56x != null) {
                           holograms56x.method5(fov2_45x);
                        }

                        if (!arg1.getHasAllEmotesFlag()) {
                           list2.add(fov2_45x);
                        }
                     }
                  );
               if (arg1.getHasAllEmotesFlag()) {
                  EmoteManager.field3.forEach((arg1x, arg2x) -> {
                     EmoteGift fov2_43x = new EmoteGift(arg1x, -1L, null, new ArrayList(), null);
                     arg2x.method5(fov2_43x);
                     list2.add(fov2_43x);
                  });
               }

               this.field37.method45().method17(list2);
               HashSet set3 = new HashSet();

               for (EquippedEmote equippedemote5 : arg1.getEquippedEmotesList()) {
                  EmoteGiftProvider gui2handler6 = new EmoteGiftProvider(equippedemote5.getEmoteId(), equippedemote5.getSlotNumber(), equippedemote5.getAttachedJamId());
                  set3.removeIf(arg1x -> arg1x.getSlotId() == gui2handler6.getSlotId());
                  list2.stream().filter(arg1x -> arg1x.id() == equippedemote5.getEmoteId()).findFirst().ifPresent(gui2handler6::method3);
                  set3.add(gui2handler6);
               }

               this.field37.method45().method18(set3);
               this.field37.method45().method27(arg1.getLunarPlusFreeEmoteId());
               this.field37.method45().method28(this.field37.method45().method24().stream().anyMatch(arg1x -> arg1x.id() == arg1.getLunarPlusFreeEmoteId()));
               this.field37.method45().method21();
               this.field37.method45().method22();
            }
         );
   }

   private void method8() {
      this.field12.login(null, com.lunarclient.websocket.cosmetic.v2.LoginRequest.getDefaultInstance(), arg1 -> {
         this.field37.method54().method4(arg1);
         this.field37.method53().method10(arg1);
         this.field37.method54().method7();
         this.field37.method55().method3(arg1.getOutfitsList(), arg1.getOutfitTree());
         this.method74(arg1.getCosmeticOwnershipVisibility());
         if (this.field37.method31() != null) {
            this.field37.method31().setRank(arg1.getRankName());
            this.field37.method31().method49(arg1.getArtistTools());
            this.field37.method31().method50(arg1.getTesterTools());
            if (!LunarBuildData.field4 || arg1.getArtistTools()) {
               GeckolibDebugMod geckolibdebugmod2 = Ref.method4().method40().method73();
               geckolibdebugmod2.method15();
               if (!geckolibdebugmod2.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field10)) {
                  geckolibdebugmod2.method2(ModTraits.field10, PanelPosition.method9());
                  geckolibdebugmod2.method2(ModTraits.field15, geckolibdebugmod2.method3());
                  geckolibdebugmod2.method2(ModTraits.field1, geckolibdebugmod2.method13());
                  ModEnabledState framework33 = (ModEnabledState)geckolibdebugmod2.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6);
                  if (framework33 instanceof com.moonsworth.lunar.client.framework.mod.UnlockableFeature nameplate4) {
                     nameplate4.unlock();
                  }
               }

               ShaderDebugMod shaderdebugmod8 = Ref.method4().method40().method74();
               shaderdebugmod8.method16();
               if (!shaderdebugmod8.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field10)) {
                  shaderdebugmod8.method6(ModTraits.field10, PanelPosition.method9());
                  shaderdebugmod8.method6(ModTraits.field15, shaderdebugmod8.method3());
                  ModEnabledState framework311 = (ModEnabledState)shaderdebugmod8.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6);
                  if (framework311 instanceof com.moonsworth.lunar.client.framework.mod.UnlockableFeature nameplate5) {
                     nameplate5.unlock();
                  }
               }

               Ref.method4().method53().method1(false);
            }

            if (arg1.getTesterTools()) {
               ModsEnabledDebugMod modsenableddebugmod7 = Ref.method4().method40().method75();
               if (!modsenableddebugmod7.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field10)) {
                  modsenableddebugmod7.method12(ModTraits.field10, PanelPosition.method9());
                  modsenableddebugmod7.method12(ModTraits.field15, modsenableddebugmod7.method3());
                  modsenableddebugmod7.method12(ModTraits.field1, modsenableddebugmod7.method13());
                  ModEnabledState framework39 = (ModEnabledState)modsenableddebugmod7.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6);
                  if (framework39 instanceof com.moonsworth.lunar.client.framework.mod.UnlockableFeature nameplate12) {
                     nameplate12.unlock();
                  }
               }

               OptimizationDebugMod optimizationdebugmod10 = Ref.method4().method40().method76();
               optimizationdebugmod10.method17();
               if (!optimizationdebugmod10.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field10)) {
                  optimizationdebugmod10.method12(ModTraits.field10, PanelPosition.method9());
                  optimizationdebugmod10.method12(ModTraits.field15, optimizationdebugmod10.method3());
                  optimizationdebugmod10.method12(ModTraits.field1, optimizationdebugmod10.method15());
                  ModEnabledState framework313 = (ModEnabledState)optimizationdebugmod10.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6);
                  if (framework313 instanceof com.moonsworth.lunar.client.framework.mod.UnlockableFeature nameplate15) {
                     nameplate15.unlock();
                  }
               }

               SkyblockDebugMod skyblockdebugmod14 = Ref.method4().method40().method77();
               if (!skyblockdebugmod14.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field10)) {
                  skyblockdebugmod14.method2(ModTraits.field10, PanelPosition.method9());
                  skyblockdebugmod14.method2(ModTraits.field15, skyblockdebugmod14.method3());
                  ModEnabledState framework316 = (ModEnabledState)skyblockdebugmod14.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6);
                  if (framework316 instanceof com.moonsworth.lunar.client.framework.mod.UnlockableFeature nameplate6) {
                     nameplate6.unlock();
                  }
               }

               FpsDebugMod fpsdebugmod17 = Ref.method4().method40().method78();
               if (!fpsdebugmod17.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field10)) {
                  fpsdebugmod17.method6(ModTraits.field10, PanelPosition.method9());
                  fpsdebugmod17.method6(ModTraits.field15, fpsdebugmod17.method3());
               }
            }
         }

         this.field36.method3("cosmeticState", ConnectionState.READY.getId());
      });
   }

   private void method9() {
      this.field14.login(null, com.lunarclient.websocket.store.v1.LoginRequest.getDefaultInstance(), arg1 -> {
         this.method72(arg1.getIncomingGiftPrivacy());
         this.method73(arg1.getCoins());
      });
   }

   private void method10() {
      this.field29
         .login(
            null, com.lunarclient.websocket.socials.v1.LoginRequest.getDefaultInstance(), arg1 -> this.field37.method101().method1(arg1.getLinkedSocialsList())
         );
   }

   private void method11() {
      this.field17
         .login(
            null,
            com.lunarclient.websocket.badge.v1.LoginRequest.getDefaultInstance(),
            arg1 -> {
               BadgeManager holograms132 = this.field37.method95();
               List list3;
               if (arg1.getHasAllBadgesFlag()) {
                  list3 = holograms132.method2().values().stream().map(com.moonsworth.lunar.client.account.OwnedBadge::method3).toList();
               } else {
                  list3 = arg1.getOwnedBadgesList()
                     .stream()
                     .<Optional>map(com.moonsworth.lunar.client.account.OwnedBadge::method2)
                     .filter(Optional::isPresent)
                     .map(Optional::get)
                     .toList();
               }

               holograms132.method3(list3);
               holograms132.method4(arg1.getEquippedBadgeId());
            }
         );
   }

   private void method12() {
      this.field30
         .login(
            null,
            com.lunarclient.websocket.jam.v1.LoginRequest.getDefaultInstance(),
            arg0 -> Ref.method4().method73().method12(arg0.getOwnedJamsList())
         );
   }

   private void method13() {
      this.field16.login(null, com.lunarclient.websocket.spray.v1.LoginRequest.getDefaultInstance(), arg0 -> {
         Object2LongOpenHashMap object2longopenhashmap1 = new Object2LongOpenHashMap();
         SprayManager holograms162 = Ref.method4().method46();
         if (!arg0.getHasAllSpraysFlag()) {
            arg0.getOwnedSpraysList().forEach(arg2x -> {
               com.moonsworth.lunar.client.cosmetics.SprayEntry gui2handler3x = holograms162.method5(arg2x.getSprayId());
               object2longopenhashmap1.put(gui2handler3x, arg2x.getExpiresAt().getSeconds() * 20L);
            });
         } else {
            ObjectIterator objectiterator3 = holograms162.method40().values().iterator();

            while (objectiterator3.hasNext()) {
               com.moonsworth.lunar.client.cosmetics.SprayEntry gui2handler4 = (com.moonsworth.lunar.client.cosmetics.SprayEntry)objectiterator3.next();
               object2longopenhashmap1.put(gui2handler4, -1L);
            }
         }

         holograms162.method19(object2longopenhashmap1);
         holograms162.method20(new LinkedHashSet(arg0.getEquippedSpraysList()));
         holograms162.method37(arg0.getLunarPlusFreeSprayIdsList());
         holograms162.method46(arg0.getMaxActiveSprays());
      });
   }

   public void method14() {
      this.field32.login(null, com.lunarclient.websocket.promotion.v1.LoginRequest.getDefaultInstance(), arg1 -> {
         if (arg1.getMedalPromotionState() == MedalPromotionState.MEDAL_PROMOTION_STATE_AVAILABLE) {
            this.field37.method97().method5(PromotionType.PROMOTION_TYPE_MEDAL);
         } else {
            this.field37.method97().method6(PromotionType.PROMOTION_TYPE_MEDAL);
         }

         this.field37.method97().method2(arg1.getPendingRewardsList());
      });
   }

   public void onClose(int number1, String text2, boolean flag3) {
      this.field6.method2();
      String text4 = field4 == null ? null : field4.method2().orElse(null);
      field4 = new ReconnectBackoff(this.field9);
      this.field9 = null;
      if (field4.method2().isEmpty() && text4 != null) {
         field4.method3(text4);
      }

      this.method66(ConnectionState.DISCONNECTED);
      LunarLogger.method4("Assets", "Connection closed (%d, \"%s\")", new Object[]{number1, text2});
      CrashReporter.method1(Breadcrumb.debug("Disconnected from the AssetServer"));
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

   public void onMessage(ByteBuffer buffer1) {
      ClientboundWebSocketMessage clientboundwebsocketmessage2;
      try {
         clientboundwebsocketmessage2 = ClientboundWebSocketMessage.parseFrom(buffer1);
      } catch (InvalidProtocolBufferException invalidprotocolbufferexception10) {
         LunarLogger.method8("Assets", "Failed to parse clientbound WebSocket message", new Object[0]);
         invalidprotocolbufferexception10.printStackTrace();
         return;
      }

      if (clientboundwebsocketmessage2.getContentsCase() == ContentsCase.RPC_RESPONSE) {
         this.field6.method1(clientboundwebsocketmessage2.getRpcResponse());
      } else if (clientboundwebsocketmessage2.getContentsCase() == ContentsCase.PUSH_NOTIFICATION) {
         Any any3 = clientboundwebsocketmessage2.getPushNotification();
         if (any3.is(WebSocketRichClosePush.class)) {
            try {
               this.field9 = (WebSocketRichClosePush)any3.unpack(WebSocketRichClosePush.class);
            } catch (InvalidProtocolBufferException invalidprotocolbufferexception8) {
            }

            return;
         }

         for (Entry entry5 : this.field7.entrySet()) {
            if (any3.is((Class)entry5.getKey())) {
               Message message6;
               try {
                  message6 = any3.unpack((Class)entry5.getKey());
               } catch (InvalidProtocolBufferException invalidprotocolbufferexception9) {
                  LunarLogger.method8("Assets", "Failed to parse wrapped message for " + any3.getTypeUrl(), new Object[0]);
                  return;
               }

               PacketUtil.method1(message6, (Consumer)entry5.getValue());
               return;
            }
         }

         LunarLogger.method6("Assets", "Failed to find handler for push notification " + any3.getTypeUrl(), new Object[0]);
      } else {
         LunarLogger.method8("Assets", "Unknown response type to consume", new Object[0]);
      }
   }

   public void onError(Exception exception1) {
      LunarLogger.method8("Assets", "WS-Error:" + exception1.getMessage(), new Object[0]);
      if (Ref.method34(DebugType.ASSET_SERVER)) {
         exception1.printStackTrace();
      }
   }

   public void onMessage(String text1) {
   }

   public void method15(InboundLocation inboundlocation1, Trigger trigger2, @Nullable Runnable runnable3) {
      field2 = inboundlocation1;
      this.field18
         .broadcastLocationChange(
            null,
            BroadcastLocationChangeRequest.newBuilder().setNewLocation(inboundlocation1).setTrigger(trigger2).build(),
            arg3x -> {
               field3 = arg3x.getNewLocation();
               this.method77();
               Ref.method6()
                  .ifPresent(
                     arg2xx -> arg2xx.method13()
                        .updateLocation(
                           null, UpdateLocationRequest.newBuilder().setLocation(arg3x.getNewLocation()).setInboundLocation(inboundlocation1).build(), arg0x -> {}
                        )
                  );
               if (runnable3 != null) {
                  runnable3.run();
               }
            }
         );
   }

   public void method16(ServerRichStatus serverrichstatus1) {
      if (!field2.hasServer()) {
         LunarLogger.method6("Assets", "Rich server status received while not on a server. (" + field2.getLocationCase() + ")", new Object[0]);
      } else {
         InboundServer inboundserver2 = field2.getServer();
         if (!inboundserver2.getRichStatus().equals(serverrichstatus1)) {
            InboundServer inboundserver3 = inboundserver2.toBuilder().setRichStatus(serverrichstatus1).build();
            InboundLocation inboundlocation4 = InboundLocation.newBuilder().setServer(inboundserver3).build();
            this.method15(inboundlocation4, Trigger.TRIGGER_SERVER_RICH_STATUS, null);
         }
      }
   }

   public void method17(@NotNull ServerDataBridge bridge3_191, InteractionType interactiontype2) {
      this.field25
         .recordPinnedServerInteraction(
            null, RecordPinnedServerInteractionRequest.newBuilder().setServerIp(bridge3_191.bridge$serverIP()).setInteractionType(interactiontype2).build(), arg0 -> {}
         );
   }

   public void method18(OpenTebexJsCheckoutPush opentebexjscheckoutpush1) {
      CheckoutUtils.method1(opentebexjscheckoutpush1.getBasketIdent(), opentebexjscheckoutpush1.getLocale());
   }

   public void method19(OpenPayNowCheckoutPush openpaynowcheckoutpush1) {
      CheckoutUtils.method2(openpaynowcheckoutpush1.getCheckoutToken());
   }

   public void method20(ConversationMessagePush conversationmessagepush1) {
      this.field37.method52().method7(conversationmessagepush1);
   }

   public void method21(ConversationPreSendActionPush conversationpresendactionpush1) {
      this.field37.method52().method8(conversationpresendactionpush1);
   }

   public void method22(ConversationOwnerUpdatePush conversationownerupdatepush1) {
      this.field37.method52().method9(conversationownerupdatepush1);
   }

   public void method23(ConversationAddParticipantsPush conversationaddparticipantspush1) {
      this.field37.method52().method10(conversationaddparticipantspush1);
   }

   public void method24(ConversationRemoveParticipantPush conversationremoveparticipantpush1) {
      this.field37.method52().method11(conversationremoveparticipantpush1);
   }

   public void method25(ConversationUpdateNamePush conversationupdatenamepush1) {
      this.field37.method52().method12(conversationupdatenamepush1);
   }

   public void method26(ConversationUpdateIconPush conversationupdateiconpush1) {
      this.field37.method52().method13(conversationupdateiconpush1);
   }

   public void method27(ConversationAddPinnedMessagePush conversationaddpinnedmessagepush1) {
      this.field37.method52().method14(conversationaddpinnedmessagepush1);
   }

   public void method28(ConversationRemovePinnedMessagePush conversationremovepinnedmessagepush1) {
      this.field37.method52().method15(conversationremovepinnedmessagepush1);
   }

   public void method29(ConversationUpdateInvitePolicyPush conversationupdateinvitepolicypush1) {
      this.field37.method52().method16(conversationupdateinvitepolicypush1);
   }

   public void method30(ConversationUpdateNamePolicyPush conversationupdatenamepolicypush1) {
      this.field37.method52().method17(conversationupdatenamepolicypush1);
   }

   public void method31(ConversationUpdateIconPolicyPush conversationupdateiconpolicypush1) {
      this.field37.method52().method18(conversationupdateiconpolicypush1);
   }

   public void method32(ConversationUpdateMessagePinningPolicyPush conversationupdatemessagepinningpolicypush1) {
      this.field37.method52().method19(conversationupdatemessagepinningpolicypush1);
   }

   public void method33(ConversationAddedPush conversationaddedpush1) {
      this.field37.method52().method20(conversationaddedpush1);
   }

   public void method34(ConversationRemovedPush conversationremovedpush1) {
      this.field37.method52().method21(conversationremovedpush1);
   }

   public void method35(ConversationRemoveMessagePush conversationremovemessagepush1) {
      this.field37.method52().method22(conversationremovemessagepush1);
   }

   public void method36(ConversationMessageHistoryDeletedPush conversationmessagehistorydeletedpush1) {
      this.field37.method52().method23(conversationmessagehistorydeletedpush1);
   }

   public void method37(RefreshConversationsPush refreshconversationspush1) {
      this.field37.method52().method24();
   }

   public void method38(FriendRequestAcceptedPush friendrequestacceptedpush1) {
      UUID uuid2 = ProtoConverter.method1(friendrequestacceptedpush1.getNewFriend().getUuid());
      this.field37.method51().method2(uuid2);
      Memory memory3 = this.field37.method50().method2(uuid2);
      if (memory3 == null) {
         memory3 = new Memory(uuid2, friendrequestacceptedpush1.getNewFriend().getUsername());
         this.field37.method50().method4(memory3);
      }

      this.field37.method69().method11(uuid2, ChatFormatting.BOLD + memory3.getName() + ChatFormatting.RESET + " has accepted your friend request!");
   }

   public void method39(JoinHostedWorldPush joinhostedworldpush1) {
      IntegratedServerBridge glintcolorizer2 = Ref.method3().bridge$getIntegratedServer();
      UUID uuid3 = ProtoConverter.method1(joinhostedworldpush1.getJoinerUuid());
      if (glintcolorizer2 != null && glintcolorizer2.bridge$getPublishedPort() != -1) {
         Ref.method4().method81().method32().add(joinhostedworldpush1.getRelaySessionId());
         Ref.method4().method81().method2(uuid3, joinhostedworldpush1.getSimpleVoiceChatEndpoint());
         new HostedWorldRelay(joinhostedworldpush1.getRelaySessionId(), joinhostedworldpush1.getRelay().getAddress(), joinhostedworldpush1.getRelay().getPort(), glintcolorizer2.bridge$getPublishedPort());
      }
   }

   public void method40(HostedWorldStatusPush hostedworldstatuspush1) {
      FogIterator fogiterator2 = Ref.method4().method81();
      if (!fogiterator2.method20()) {
         fogiterator2.method36(hostedworldstatuspush1);
         this.field37.method50().method9();
      }
   }

   public void method41(HostedWorldAvailablePush hostedworldavailablepush1) {
      Memory memory2 = Ref.method4().method50().method2(ProtoConverter.method1(hostedworldavailablepush1.getWorldHost().getUuid()));
      if (memory2 == null || memory2.method6()) {
         Ref.method4().method69().method9(NotificationManager.method15("hostedWorldAvailable", new Object[]{hostedworldavailablepush1.getWorldHost().getUsername()}));
      }
   }

   public void method42(HostedWorldWhitelistedPush hostedworldwhitelistedpush1) {
      UUID uuid2 = ProtoConverter.method1(hostedworldwhitelistedpush1.getWorldHost().getUuid());
      Memory memory3 = this.field37.method50().method2(uuid2);
      if (memory3 != null) {
         memory3.method39(true);
         Ref.method4()
            .method69()
            .method10(new FriendNotificationLong(uuid2, memory3, NotificationManager.method15("hostedWorldWhitelisted", new Object[]{hostedworldwhitelistedpush1.getWorldHost().getUsername()})));
      }
   }

   public void method43(FriendRequestDeniedPush friendrequestdeniedpush1) {
      UUID uuid2 = ProtoConverter.method1(friendrequestdeniedpush1.getDenierUuid());
      this.field37.method51().method2(uuid2);
   }

   public void method44(FriendRequestCanceledPush friendrequestcanceledpush1) {
      UUID uuid2 = ProtoConverter.method1(friendrequestcanceledpush1.getSenderUuid());
      this.field37.method51().method2(uuid2);
   }

   public void method45(FriendStatusPush friendstatuspush1) {
      if (friendstatuspush1.getFriendCase() == FriendCase.ONLINE_FRIEND) {
         OnlineFriend onlinefriend2 = friendstatuspush1.getOnlineFriend();
         UUID uuid3 = ProtoConverter.method1(onlinefriend2.getPlayer().getUuid());
         Memory memory4 = this.field37.method50().method2(uuid3);
         if (memory4 == null) {
            memory4 = new Memory(uuid3, onlinefriend2.getPlayer().getUsername());
         } else {
            memory4.setName(onlinefriend2.getPlayer().getUsername());
         }

         FriendStatus entityrenderertype5 = FriendStatus.fromProtobuf(onlinefriend2.getStatus());
         if ((Boolean)this.field37.method41().method6().method31().get() && onlinefriend2.getJustCameOnline()) {
            this.field37
               .method69()
               .method11(memory4.method10(), ChatFormatting.GREEN.toString() + ChatFormatting.BOLD + memory4.getName() + ChatFormatting.WHITE + " is now online");
         }

         memory4.method47((Badge)Ref.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(onlinefriend2.getBadgeId()));
         memory4.setRank(onlinefriend2.getRankName());
         memory4.method46(onlinefriend2.getLogoColor().getColor());
         memory4.method45(onlinefriend2.getPlusColor().getColor());
         memory4.method4(entityrenderertype5);
         memory4.method35(System.currentTimeMillis());
         memory4.method33(onlinefriend2.getLocation());
         memory4.method38(onlinefriend2.getHostedWorldJoinability());
         if (memory4.getHostedWorldJoinability() != Joinability.JOINABILITY_ALLOWED) {
            memory4.method39(false);
         }

         Config.get(onlinefriend2.getMinecraftVersion().getEnum()).ifPresent(memory4::method34);
         if (onlinefriend2.hasFriendsSince()) {
            memory4.method36(ProtoConverter.method5(onlinefriend2.getFriendsSince()));
         }

         FriendSocials friendsocials6 = onlinefriend2.getSocials();
         if (onlinefriend2.hasSocials()) {
            memory4.method37(friendsocials6);
         }

         if (!this.field37.method50().method3(memory4.method10())) {
            this.field37.method50().method4(memory4);
            this.method3(memory4.method10(), FriendRadioInfoPush.class);
         }

         this.field37.method50().method9();
      } else if (friendstatuspush1.getFriendCase() == FriendCase.OFFLINE_FRIEND) {
         OfflineFriend offlinefriend7 = friendstatuspush1.getOfflineFriend();
         UUID uuid8 = ProtoConverter.method1(offlinefriend7.getPlayer().getUuid());
         Memory memory9 = this.field37.method50().method2(uuid8);
         if (memory9 == null) {
            memory9 = new Memory(uuid8, offlinefriend7.getPlayer().getUsername());
         } else {
            memory9.setName(offlinefriend7.getPlayer().getUsername());
         }

         memory9.method47((Badge)Ref.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(offlinefriend7.getBadgeId()));
         memory9.setRank(offlinefriend7.getRankName());
         memory9.method46(offlinefriend7.getLogoColor().getColor());
         memory9.method45(offlinefriend7.getPlusColor().getColor());
         memory9.method33(null);
         memory9.method4(FriendStatus.OFFLINE);
         if (offlinefriend7.hasLastVisibleOnline()) {
            memory9.method35(offlinefriend7.getLastVisibleOnline().getSeconds() * 1000L);
         } else {
            memory9.method35(-1L);
         }

         if (offlinefriend7.hasFriendsSince()) {
            memory9.method36(ProtoConverter.method5(offlinefriend7.getFriendsSince()));
         }

         FriendSocials friendsocials10 = offlinefriend7.getSocials();
         if (offlinefriend7.hasSocials()) {
            memory9.method37(friendsocials10);
         }

         if (!this.field37.method50().method3(memory9.method10())) {
            this.field37.method50().method4(memory9);
            this.method3(memory9.method10(), FriendRadioInfoPush.class);
         }

         this.field37.method50().method9();
      } else {
         LunarLogger.method8("Assets", "Unknown friend case " + friendstatuspush1.getFriendCase(), new Object[0]);
      }
   }

   public void method46(FriendRadioInfoPush friendradioinfopush1) {
      UUID uuid2 = ProtoConverter.method1(friendradioinfopush1.getFriendUuid());
      Memory memory3 = this.field37.method50().method2(uuid2);
      if (memory3 == null) {
         this.method4(uuid2, friendradioinfopush1);
      } else {
         memory3.method44(friendradioinfopush1.getRadioInfo());
         this.field37.method50().method9();
      }
   }

   public void method47(DisplayChatMessagePush displaychatmessagepush1) {
      MinecraftBridge bridge5_122 = Ref.method3();
      if (bridge5_122.bridge$getGuiIngame() != null && bridge5_122.bridge$getGuiIngame().bridge$getChatGUI() != null) {
         GuiNewChatBridge bridge5extension43 = bridge5_122.bridge$getGuiIngame().bridge$getChatGUI();
         bridge5extension43.bridge$addMessage(Bridge.method8().method13(displaychatmessagepush1.getChatMessage()));
      }
   }

   public void method48(DisplayNotificationPush displaynotificationpush1) {
      String text2 = displaynotificationpush1.getNotificationTitle();
      String text3 = displaynotificationpush1.getNotificationMessage();
      LunarLogger.method4("Assets", ChatFormatting.getTextWithoutFormattingCodes("[" + text2 + "] " + text3), new Object[0]);
      this.field37.method69().method2(text2, text3);
   }

   public void method49(FriendRequestReceivedPush friendrequestreceivedpush1) {
      UUID uuid2 = ProtoConverter.method1(friendrequestreceivedpush1.getSender().getUuid());
      Object obj3 = friendrequestreceivedpush1.getSender().getUsername();
      EntityRenderer6 entityrenderer64 = new EntityRenderer6(
         ProtoConverter.method1(friendrequestreceivedpush1.getSender().getUuid()),
         friendrequestreceivedpush1.getSender().getUsername(),
         Instant.now(),
         friendrequestreceivedpush1.getSenderLogoColor().getColor(),
         friendrequestreceivedpush1.getSenderPlusColor().getColor(),
         friendrequestreceivedpush1.getSenderBadgeId() > 0
            ? (Badge)Ref.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(friendrequestreceivedpush1.getSenderBadgeId())
            : null,
         friendrequestreceivedpush1.getSenderRankName()
      );
      this.field37.method51().method4(entityrenderer64);
      this.field37.method69().method11(uuid2, ChatFormatting.BOLD + obj3 + ChatFormatting.RESET + " wants to be your friend");
   }

   public void method50(FriendRemovedYouPush friendremovedyoupush1) {
      UUID uuid2 = ProtoConverter.method1(friendremovedyoupush1.getFriendUuid());
      Memory memory3 = this.field37.method50().method2(uuid2);
      if (memory3 != null) {
         this.field37.method50().method5(uuid2);
      }
   }

   public void method51(PlayerCosmeticsPushV2 playercosmeticspushv21) {
      if (!Ref.method4().method40().method85().method19() || this.field35) {
         UUID uuid2 = ProtoConverter.method1(playercosmeticspushv21.getPlayerUuid());
         this.field37.method53().method9(uuid2, playercosmeticspushv21, playercosmeticspushv21.getBadgeId());
         this.field37.method55().method2(playercosmeticspushv21);
         Ref.method4().method88().method11(uuid2);
         this.method55(playercosmeticspushv21);
         this.method3(uuid2, PlayerRadioPush.class);
      }
   }

   public void method52(PlayerRadioPush playerradiopush1) {
      if (!Ref.method4().method40().method85().method19() || this.field35) {
         UUID uuid2 = ProtoConverter.method1(playerradiopush1.getPlayerUuid());
         this.field37.method53().method63().compute(uuid2, (arg2x, arg3) -> {
            if (arg3 == null) {
               this.method4(arg2x, playerradiopush1);
               return null;
            } else {
               return arg3.method3(playerradiopush1.getRadioPlaying());
            }
         });
      }
   }

   public void method53(UseEmotePush useemotepush1) {
      if (!Ref.method4().method40().method85().method19() || this.field35) {
         UUID uuid2 = ProtoConverter.method1(useemotepush1.getPlayerUuid());
         if (!(Boolean)Ref.method4().method41().method6().method37().get()) {
            Bridge5Extension_5 bridge5extension_55 = Ref.method3().bridge$getPlayer();
            if (bridge5extension_55 != null && uuid2.equals(bridge5extension_55.bridge$getUniqueID())) {
               Ref.method4()
                  .method69()
                  .method7(NotificationType.ERROR, Ref.method4().method67().method2("popups", "emotesDisabled", new Object[0]));
            }
         } else {
            Emote fovhandler3 = this.field37.method45().method13(useemotepush1.getEmoteId());
            WorldBridgeExtension itemcounter6extension4 = Ref.method8();
            if (itemcounter6extension4 != null) {
               itemcounter6extension4.bridge$getPlayerByUniqueId(uuid2).ifPresent(arg3x -> {
                  if (Ref.method4().method41().method6().method7(arg3x)) {
                     StyngrSong chest4x = (StyngrSong)JamManager.method12().get(useemotepush1.getEmoteJamId());
                     this.field37.method45().method7(arg3x, fovhandler3, useemotepush1.getEmoteMetadata(), useemotepush1.getEmoteSoundtrackUrl(), chest4x != null ? chest4x.getId() : 0);
                  }
               });
            }

            this.method55(useemotepush1);
         }
      }
   }

   public void method54(StopEmotePush stopemotepush1) {
      if (!Ref.method4().method40().method85().method19() || this.field35) {
         UUID uuid2 = ProtoConverter.method1(stopemotepush1.getPlayerUuid());
         WorldBridgeExtension itemcounter6extension3 = Ref.method8();
         if (itemcounter6extension3 != null) {
            itemcounter6extension3.bridge$getPlayerByUniqueId(uuid2).ifPresent(arg1x -> this.field37.method45().method7(arg1x, null, 0, null, 0));
         }

         this.method55(stopemotepush1);
      }
   }

   private void method55(Message message1) {
      RewindMod rewind3 = Ref.method4().method40().method85();
      Any any2;
      if (rewind3.isRecording()) {
         any2 = Any.pack(message1);
         rewind3.method34().method1(any2);
      } else {
         any2 = null;
      }

      ExternalLinkRegistry.method2(RecordingExternalLink.class).ifPresent(arg2x -> {
         if (Client.method109().method40().method64().isEnabled()) {
            arg2x.method8(any2 == null ? Any.pack(message1) : any2);
         }
      });
   }

   public void method56(UseSprayPush usespraypush1) {
      if (!Ref.method4().method40().method85().method19() || this.field35) {
         UUID uuid2 = ProtoConverter.method1(usespraypush1.getPlayerUuid());
         if (!(Boolean)Ref.method4().method41().method6().method35().get()) {
            Bridge5Extension_5 bridge5extension_54 = Ref.method3().bridge$getPlayer();
            if (bridge5extension_54 != null && uuid2.equals(bridge5extension_54.bridge$getUniqueID())) {
               Ref.method4()
                  .method69()
                  .method7(NotificationType.ERROR, Ref.method4().method67().method2("popups", "spraysDisabled", new Object[0]));
            }
         } else {
            WorldBridgeExtension itemcounter6extension3 = Ref.method8();
            if (itemcounter6extension3 != null) {
               itemcounter6extension3.bridge$getPlayerByUniqueId(uuid2).ifPresent(arg3x -> {
                  if (Ref.method4().method41().method6().method8(arg3x)) {
                     Vector3f vector3f4x = ProtoConverter.method13(usespraypush1.getPos());
                     SprayManager holograms165 = this.field37.method46();
                     com.moonsworth.lunar.client.cosmetics.SprayEntry gui2handler6 = holograms165.method5(usespraypush1.getSprayId());
                     if (gui2handler6 != null) {
                        SprayPlacementTracker clickhandler27 = holograms165.method14(gui2handler6, vector3f4x, ProtoConverter.method11(usespraypush1.getFacing()), usespraypush1.getRotation());
                        if (clickhandler27 != null) {
                           double value8 = arg3x.bridge$blockInteractionRange() * 1.6;
                           if (arg3x.HROHOIOCHIRIHICOORIHOHCIOIRIIH(vector3f4x.x, vector3f4x.y, vector3f4x.z) <= value8) {
                              holograms165.method2(uuid2, clickhandler27, usespraypush1.getMaxActiveSprays(), false);
                           }
                        }
                     }
                  }
               });
            }

            this.method55(usespraypush1);
         }
      }
   }

   public void method57(RemoveSprayPush removespraypush1) {
      if (!Ref.method4().method40().method85().method19() || this.field35) {
         this.field37.method46().method3(ProtoConverter.method1(removespraypush1.getPlayerUuid()), ProtoConverter.method13(removespraypush1.getPos()));
         this.method55(removespraypush1);
      }
   }

   public void method58(RefreshMetadataPush refreshmetadatapush1) {
      Ref.method4().method72().method7(null);
   }

   public void method59(RefreshEmotesPush refreshemotespush1) {
      this.method7();
   }

   public void method60(RefreshCosmeticsPush refreshcosmeticspush1) {
      this.method8();
   }

   public void method61(RefreshSocialsPush refreshsocialspush1) {
      this.method10();
   }

   public void method62(RefreshBadgesPush refreshbadgespush1) {
      this.method11();
   }

   public void method63(RefreshJamsPush refreshjamspush1) {
      this.method12();
   }

   public void method64(DungeonUpdatePush dungeonupdatepush1) {
      DynamicListener.method9(DungeonMapListener.class)
         .<DungeonStateTracker>flatMap(DungeonMapListener::method5)
         .ifPresent(arg1x -> arg1x.method45().method1(dungeonupdatepush1));
   }

   private void method65(NewMarkerPush newmarkerpush1) {
      UUID uuid2 = ProtoConverter.method1(newmarkerpush1.getSenderUuid());
      Ref.method4().method40().method87().method51().method6(uuid2, newmarkerpush1.getMarker());
   }

   private void method66(ConnectionState entityrenderertype21) {
      this.field38 = entityrenderertype21;
      this.field36.method3("state", entityrenderertype21.getId());
      if (entityrenderertype21 == ConnectionState.DISCONNECTED) {
         this.field36.method3("cosmeticState", entityrenderertype21.getId());
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

   public void method69(ServerSectionCardsPush serversectioncardspush1) {
      this.field37.method79().method8(serversectioncardspush1);
   }

   public void method70(boolean flag1) {
      this.field39 = flag1;
      this.field36.method3("allowFriendRequests", flag1);
   }

   public void method71(LastSeenVisibility lastseenvisibility1) {
      this.field40 = lastseenvisibility1;
      this.field36.method3("lastSeenVisibility", lastseenvisibility1.name());
   }

   public void method72(IncomingGiftPrivacy incominggiftprivacy1) {
      this.field41 = incominggiftprivacy1;
      this.field36.method3("incomingGiftPrivacy", incominggiftprivacy1.name());
   }

   public void method73(long number1) {
      this.field43 = number1;
      this.field36.method3("coins", number1);
   }

   public void method74(CosmeticOwnershipVisibility cosmeticownershipvisibility1) {
      this.field42 = cosmeticownershipvisibility1;
      this.field36.method3("cosmeticOwnershipVisibility", cosmeticownershipvisibility1.name());
   }

   public static void bootstrap() {
      LunarEventBus.method29()
         .method2(
            EventSecond.class,
            arg0 -> {
               if (Client.method109() != null && !Client.method109().method13()) {
                  if (Ref.method5().isEmpty()
                     || ((AssetServerClient)Ref.method5().get()).method112() == ConnectionState.DISCONNECTED) {
                     if (field4 == null) {
                        LunarLogger.method6("Assets Reconnect", "There was no closedConnection for a disconnected player.", new Object[0]);
                        field4 = new ReconnectBackoff(null);
                     }

                     if (field4.method1()) {
                        Ref.method4().method7(field4);
                        LunarLogger.method4("Assets Reconnect", "Reconnecting to the assets server...", new Object[0]);
                     }
                  }
               }
            }
         );
      LunarEventBus.method29()
         .method2(
            EventDisconnectReason.class,
            arg0 -> Ref.method5()
               .ifPresent(
                  arg1 -> arg1.method93()
                     .broadcastServerKick(null, BroadcastServerKickRequest.newBuilder().setKickReason(arg0.getReason()).build(), arg0xx -> {})
               )
         );
      LunarEventBus.method29()
         .method2(
            EventDisconnect.class,
            arg0 -> Ref.method5()
               .ifPresent(
                  arg0x -> arg0x.method15(
                     InboundLocation.newBuilder().setInMenus(InboundInMenus.getDefaultInstance()).build(), Trigger.TRIGGER_DISCONNECT_EVENT, null
                  )
               )
         );
      LunarEventBus.method29().method2(EventServerJoin.class, arg0 -> method75());
   }

   public static void method75() {
      FogIterator fogiterator0 = Ref.method4().method81();
      if (!fogiterator0.method20()) {
         AssetServerClient entityrenderer41 = (AssetServerClient)Ref.method5().orElse(null);
         if (entityrenderer41 != null) {
            Runnable runnable3 = null;
            InboundLocation inboundlocation2;
            if (fogiterator0.method39() != null) {
               inboundlocation2 = InboundLocation.newBuilder()
                  .setHostedWorld(InboundHostedWorld.newBuilder().setWorldHost(ProtoConverter.method3(fogiterator0.method39())).build())
                  .build();
            } else if (Ref.method4().method40().method85().method19()) {
               RewindHandlers rewindhandlers4 = Ref.method4().method40().method85().method35();
               inboundlocation2 = InboundLocation.newBuilder()
                  .setRewindWorld(
                     InboundRewindWorld.newBuilder()
                        .setType(
                           rewindhandlers4.method40() != null && rewindhandlers4.method40().method31()
                              ? RewindWorldType.REWIND_WORLD_TYPE_PREVIEW
                              : RewindWorldType.REWIND_WORLD_TYPE_PROJECT
                        )
                        .build()
                  )
                  .build();
            } else if (Ref.method4().method40().method64().method13()) {
               inboundlocation2 = InboundLocation.newBuilder().setReplayWorld(InboundReplayWorld.getDefaultInstance()).build();
            } else if (Ref.method3().bridge$getCurrentServerData() == null) {
               inboundlocation2 = InboundLocation.newBuilder().setSinglePlayer(InboundSinglePlayer.getDefaultInstance()).build();
            } else if (Ref.method3().bridge$isConnectedToRealms()) {
               inboundlocation2 = InboundLocation.newBuilder().setMinecraftRealms(InboundMinecraftRealms.getDefaultInstance()).build();
            } else {
               ServerDataBridge bridge3_195 = Ref.method3().bridge$getCurrentServerData();
               inboundlocation2 = InboundLocation.newBuilder().setServer(InboundServer.newBuilder().setServerIp(bridge3_195.bridge$serverIP()).build()).build();
               runnable3 = () -> {
                  if (bridge3_195.bridge$wasPinnedClicked()) {
                     entityrenderer41.method17(bridge3_195, InteractionType.INTERACTION_TYPE_PLAY);
                     bridge3_195.bridge$setPinnedClicked(false);
                  }
               };
               if (field2.hasServer() && field2.getServer().getServerIp().equals(inboundlocation2.getServer().getServerIp())) {
                  return;
               }
            }

            entityrenderer41.method15(inboundlocation2, Trigger.TRIGGER_JOIN_EVENT, runnable3);
         }
      }
   }

   public void method76(SessionIdentifyPush sessionidentifypush1) {
      field1 = ProtoConverter.method1(sessionidentifypush1.getSessionId());
   }

   private void method77() {
      JsonObject json1 = FriendStatusUtils.method1(
         Ref.method4().method31().method10(),
         field3,
         Ref.method4().method31().method16(),
         Ref.method4().method31().method13()
      );
      this.field36.method3("statusDetails", json1);
   }

   @Generated
   public static UUID method78() {
      return field1;
   }

   @Generated
   public static void method79(UUID uuid0) {
      field1 = uuid0;
   }

   @Generated
   public static InboundLocation method80() {
      return field2;
   }

   @Generated
   public static void method81(InboundLocation inboundlocation0) {
      field2 = inboundlocation0;
   }

   @Generated
   public static Location method82() {
      return field3;
   }

   @Generated
   public static void method83(Location location0) {
      field3 = location0;
   }

   @Generated
   public static void method84(ReconnectBackoff entityrenderer30) {
      field4 = entityrenderer30;
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
   public void method110(boolean flag1) {
      this.field35 = flag1;
   }

   @Generated
   public GuiIterator method111() {
      return this.field36;
   }

   @Generated
   public ConnectionState method112() {
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
