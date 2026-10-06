package com.moonsworth.lunar.client.network.ipc;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.lunarclient.apollo.player.v1.EmbeddedCheckoutSupport;
import com.lunarclient.gameipc.auth.v1.AuthService;
import com.lunarclient.gameipc.auth.v1.AuthService.Interface;
import com.lunarclient.gameipc.browser.v1.BrowserService;
import com.lunarclient.gameipc.handshake.v1.Handshake;
import com.lunarclient.gameipc.launch.v1.ExitGamePush;
import com.lunarclient.gameipc.launch.v1.LaunchService;
import com.lunarclient.gameipc.launch.v1.PlayServerPush;
import com.lunarclient.gameipc.location.v1.FocusGamePush;
import com.lunarclient.gameipc.location.v1.LocationService;
import com.lunarclient.gameipc.location.v1.UpdateFocusRequest;
import com.lunarclient.gameipc.paynow.v1.CheckPayNowCapabilitiesRequest;
import com.lunarclient.gameipc.paynow.v1.PayNowService;
import com.lunarclient.gameipc.promotion.v1.CheckPendingPromotionRequest;
import com.lunarclient.gameipc.promotion.v1.PendingPromotionUpdatePush;
import com.lunarclient.gameipc.promotion.v1.PromotionService;
import com.lunarclient.gameipc.protocol.v1.GameboundIPCMessage;
import com.lunarclient.gameipc.protocol.v1.GameboundIPCMessage.ContentsCase;
import com.lunarclient.gameipc.store.v1.StartStorePreviewSessionPush;
import com.lunarclient.gameipc.styngr.v1.StyngrRadioService;
import com.lunarclient.gameipc.tebex.v1.CheckTebexCapabilitiesRequest;
import com.lunarclient.gameipc.tebex.v1.TebexJsCheckoutClosed;
import com.lunarclient.gameipc.tebex.v1.TebexService;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.framework.feature.debug.DebugType;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.network.websocket.ReconnectBackoff;
import com.moonsworth.lunar.client.network.websocket.AssetServerClient;
import com.moonsworth.lunar.client.network.websocket.ConnectionState;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.net.DisconnectUtils;
import com.moonsworth.lunar.client.framework.PacketUtil;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.LaunchOptions;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketClientIterator extends WebSocketClient {
   private static ReconnectBackoff field1;
   private final RpcChannelImpl field2 = new RpcChannelImpl(this);
   private final Map<Class<? extends Message>, Consumer<? extends Message>> field3 = new ConcurrentHashMap<>();
   private final Interface field4 = AuthService.newStub(this.field2);
   private final com.lunarclient.gameipc.launch.v1.LaunchService.Interface field5 = LaunchService.newStub(this.field2);
   private final com.lunarclient.gameipc.location.v1.LocationService.Interface field6 = LocationService.newStub(this.field2);
   private final com.lunarclient.gameipc.tebex.v1.TebexService.Interface field7 = TebexService.newStub(this.field2);
   private final com.lunarclient.gameipc.paynow.v1.PayNowService.Interface field8 = PayNowService.newStub(this.field2);
   private final com.lunarclient.gameipc.browser.v1.BrowserService.Interface field9 = BrowserService.newStub(this.field2);
   private final com.lunarclient.gameipc.promotion.v1.PromotionService.Interface field10 = PromotionService.newStub(this.field2);
   private final com.lunarclient.gameipc.styngr.v1.StyngrRadioService.Interface field11 = StyngrRadioService.newStub(this.field2);
   private ConnectionState field12 = ConnectionState.DISCONNECTED;
   private EmbeddedCheckoutSupport field13 = EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_UNSPECIFIED;
   private boolean field14 = Ref.method3().bridge$isWindowFocused();

   public WebSocketClientIterator(@Nonnull Handshake handshake1) {
      super(
         URI.create("ws://127.0.0.1:" + LaunchOptions.field6),
         new Draft_6455(),
         Map.of("lc-handshake", Base64.getEncoder().encodeToString(handshake1.toByteArray())),
         3000
      );
      this.method1(TebexJsCheckoutClosed.class, this::method9);
   }

   public <T extends Message> void method1(Class<T> clazz1, Consumer<T> consumer2) {
      this.field3.put(clazz1, consumer2);
   }

   public void onOpen(ServerHandshake serverhandshake1) {
      field1 = null;
      this.field12 = ConnectionState.READY;
      LunarLogger.method4("IPC", "Connection established", new Object[0]);
      AccountSession lighting3loader22 = Ref.method4().method43().method10();
      if (lighting3loader22 != null) {
         lighting3loader22.method24(false);
      }

      this.method13().updateFocus(null, UpdateFocusRequest.newBuilder().setFocused(Ref.method3().bridge$isWindowFocused()).build(), arg0 -> {});
      this.method1(PlayServerPush.class, this::method8);
      this.method1(ExitGamePush.class, this::method7);
      this.method1(FocusGamePush.class, this::method3);
      this.method1(PendingPromotionUpdatePush.class, this::method4);
      this.method1(StartStorePreviewSessionPush.class, this::method5);
      this.method2();
      this.method6();
   }

   private void method2() {
      this.method14().checkTebexCapabilities(null, CheckTebexCapabilitiesRequest.getDefaultInstance(), arg1 -> {
         this.field13 = switch (arg1.getEmbeddedCheckout()) {
            case EMBEDDED_CHECKOUT_OVERWOLF_OVERLAY -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_OVERLAY;
            case EMBEDDED_CHECKOUT_ELECTRON_WINDOW -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_WINDOW;
            default -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_UNSPECIFIED;
         };
      });
      this.method15().checkPayNowCapabilities(null, CheckPayNowCapabilitiesRequest.getDefaultInstance(), arg1 -> {
         this.field13 = switch (arg1.getEmbeddedCheckout()) {
            case EMBEDDED_CHECKOUT_OVERWOLF_OVERLAY -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_OVERLAY;
            case EMBEDDED_CHECKOUT_ELECTRON_WINDOW -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_WINDOW;
            default -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_UNSPECIFIED;
         };
      });
   }

   private void method3(FocusGamePush focusgamepush1) {
      LunarLogger.method4("IPC", "Received focus game push, focusing window", new Object[0]);
      if (Ref.MC_VERSION >= 6) {
         Ref.method3().bridge$focusWindow();
      }
   }

   private void method4(PendingPromotionUpdatePush pendingpromotionupdatepush1) {
      LunarLogger.method4("IPC", "Received pending promotion push", new Object[0]);
      Ref.method4().method97().method3(pendingpromotionupdatepush1.getPendingPromotionsList());
   }

   private void method5(StartStorePreviewSessionPush startstorepreviewsessionpush1) {
      LunarLogger.method4("IPC", "Received start store preview session push", new Object[0]);
      Ref.method4().method56().method11(startstorepreviewsessionpush1);
   }

   public void method6() {
      this.method17()
         .checkPendingPromotion(
            null, CheckPendingPromotionRequest.getDefaultInstance(), arg0 -> Ref.method4().method97().method3(arg0.getPendingPromotionsList())
         );
      AssetServerClient entityrenderer41 = Ref.method4().method35();
      if (entityrenderer41 != null) {
         entityrenderer41.method14();
      }
   }

   private void method7(ExitGamePush exitgamepush1) {
      LunarLogger.method4("IPC", "Received exit game push, shutting down", new Object[0]);
      Ref.method3().bridge$shutdownMinecraftApplet();
   }

   private void method8(PlayServerPush playserverpush1) {
      if (Ref.method3().bridge$getSession() == null) {
         LunarLogger.method6("IPC", "Received play server push but no session is available... ignoring", new Object[0]);
      } else {
         LunarLogger.method4("IPC", "Received play server push, connecting to %s", new Object[]{playserverpush1.getAutoJoinServerIp()});
         DisconnectUtils.method1();
         Ref.method3().bridge$displayScreen(null);
         Ref.method3().bridge$connect(Bridge.method8().method50(playserverpush1.getAutoJoinServerIp(), playserverpush1.getAutoJoinServerIp(), false), null);
      }
   }

   public void onMessage(String text1) {
   }

   public void onMessage(ByteBuffer buffer1) {
      GameboundIPCMessage gameboundipcmessage2;
      try {
         gameboundipcmessage2 = GameboundIPCMessage.parseFrom(buffer1);
      } catch (InvalidProtocolBufferException invalidprotocolbufferexception9) {
         LunarLogger.method8("IPC", "Failed to parse clientbound WebSocket message", new Object[0]);
         invalidprotocolbufferexception9.printStackTrace();
         return;
      }

      if (gameboundipcmessage2.getContentsCase() == ContentsCase.RPC_RESPONSE) {
         this.field2.method1(gameboundipcmessage2.getRpcResponse());
      } else if (gameboundipcmessage2.getContentsCase() == ContentsCase.PUSH_NOTIFICATION) {
         Any any3 = gameboundipcmessage2.getPushNotification();

         for (Entry entry5 : this.field3.entrySet()) {
            if (any3.is((Class)entry5.getKey())) {
               Message message6;
               try {
                  message6 = any3.unpack((Class)entry5.getKey());
               } catch (InvalidProtocolBufferException invalidprotocolbufferexception8) {
                  LunarLogger.method8("IPC", "Failed to parse wrapped message for " + any3.getTypeUrl(), new Object[0]);
                  return;
               }

               PacketUtil.method1(message6, (Consumer)entry5.getValue());
               return;
            }
         }

         LunarLogger.method6("IPC", "Failed to find handler for push notification " + any3.getTypeUrl(), new Object[0]);
      } else {
         LunarLogger.method8("IPC", "Unknown response type to consume", new Object[0]);
      }
   }

   public void onClose(int number1, String text2, boolean flag3) {
      this.field2.method2();
      if (this.field12 == null) {
         field1 = new ReconnectBackoff(null, 5000L);
      }

      if (this.field12 == ConnectionState.READY) {
         LunarLogger.method4("IPC", "Connection closed (%d, \"%s\")", new Object[]{number1, text2});
      }

      this.field12 = ConnectionState.DISCONNECTED;
   }

   public void onError(Exception exception1) {
      if (!exception1.getMessage().startsWith("Connection refused:") && (LunarBuildData.field4 || Ref.method34(DebugType.IPC))) {
         LunarLogger.method8("IPC", "WS-Error: " + exception1.getMessage(), new Object[0]);
      }

      if (Ref.method34(DebugType.IPC)) {
         exception1.printStackTrace();
      }
   }

   public void method9(TebexJsCheckoutClosed tebexjscheckoutclosed1) {
      Ref.method3().bridge$displayScreen(null);
      LunarLogger.method4("Tebex", "Embedded checkout closed.", new Object[0]);
   }

   public static void bootstrap() {
      LunarEventBus.method29()
         .method2(
            EventSecond.class,
            arg0 -> {
               if (Client.method109() != null) {
                  if (Ref.method6().isEmpty()
                     || ((WebSocketClientIterator)Ref.method6().get()).method19() == ConnectionState.DISCONNECTED) {
                     if (field1 == null) {
                        field1 = new ReconnectBackoff(null, 5000L);
                     }

                     if (field1.method1()) {
                        Ref.method4().method8();
                     }
                  }

                  Ref.method6()
                     .ifPresent(
                        arg0x -> {
                           if (Ref.method3().bridge$isWindowFocused() != arg0x.method21()) {
                              arg0x.method22(Ref.method3().bridge$isWindowFocused());
                              arg0x.method13()
                                 .updateFocus(
                                    null,
                                    UpdateFocusRequest.newBuilder().setFocused(Ref.method3().bridge$isWindowFocused()).build(),
                                    arg0xx -> {}
                                 );
                           }
                        }
                     );
               }
            }
         );
   }

   @Generated
   public static void method10(ReconnectBackoff entityrenderer30) {
      field1 = entityrenderer30;
   }

   @Generated
   public Interface method11() {
      return this.field4;
   }

   @Generated
   public com.lunarclient.gameipc.launch.v1.LaunchService.Interface method12() {
      return this.field5;
   }

   @Generated
   public com.lunarclient.gameipc.location.v1.LocationService.Interface method13() {
      return this.field6;
   }

   @Generated
   public com.lunarclient.gameipc.tebex.v1.TebexService.Interface method14() {
      return this.field7;
   }

   @Generated
   public com.lunarclient.gameipc.paynow.v1.PayNowService.Interface method15() {
      return this.field8;
   }

   @Generated
   public com.lunarclient.gameipc.browser.v1.BrowserService.Interface method16() {
      return this.field9;
   }

   @Generated
   public com.lunarclient.gameipc.promotion.v1.PromotionService.Interface method17() {
      return this.field10;
   }

   @Generated
   public com.lunarclient.gameipc.styngr.v1.StyngrRadioService.Interface method18() {
      return this.field11;
   }

   @Generated
   public ConnectionState method19() {
      return this.field12;
   }

   @Generated
   public EmbeddedCheckoutSupport method20() {
      return this.field13;
   }

   @Generated
   public boolean method21() {
      return this.field14;
   }

   @Generated
   public void method22(boolean flag1) {
      this.field14 = flag1;
   }
}
