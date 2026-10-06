package com.moonsworth.lunar.client.waypoints;

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
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.framework.feature.debug.Gui2Extension;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.mixin.EntityRenderer3;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mixin.EntityRendererType2;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump10;
import com.moonsworth.lunar.client.util.ThreadModuleDump13;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
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
   private static EntityRenderer3 field1;
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
   private EntityRendererType2 field12 = EntityRendererType2.READY;
   private EmbeddedCheckoutSupport field13 = EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_UNSPECIFIED;
   private boolean field14 = ThreadModuleDump63.method3().bridge$isWindowFocused();

   public WebSocketClientIterator(@Nonnull Handshake var1) {
      super(
         URI.create("ws://127.0.0.1:" + ThreadModuleDump80.ipcPort),
         new Draft_6455(),
         Map.of("lc-handshake", Base64.getEncoder().encodeToString(var1.toByteArray())),
         3000
      );
      this.method1(TebexJsCheckoutClosed.class, this::method9);
   }

   public <T extends Message> void method1(Class<T> var1, Consumer<T> var2) {
      this.field3.put(var1, var2);
   }

   public void onOpen(ServerHandshake var1) {
      field1 = null;
      this.field12 = EntityRendererType2.READY;
      Slayer.method4("IPC", "Connection established");
      AccountSession var2 = ThreadModuleDump63.method4().method43().method10();
      if (var2 != null) {
         var2.method24(false);
      }

      this.method13().updateFocus(null, UpdateFocusRequest.newBuilder().setFocused(ThreadModuleDump63.method3().bridge$isWindowFocused()).build(), var0 -> {});
      this.method1(PlayServerPush.class, this::method8);
      this.method1(ExitGamePush.class, this::method7);
      this.method1(FocusGamePush.class, this::method3);
      this.method1(PendingPromotionUpdatePush.class, this::method4);
      this.method1(StartStorePreviewSessionPush.class, this::method5);
      this.method2();
      this.method6();
   }

   private void method2() {
      this.method14().checkTebexCapabilities(null, CheckTebexCapabilitiesRequest.getDefaultInstance(), var1 -> {
         this.field13 = switch (var1.getEmbeddedCheckout()) {
            case EMBEDDED_CHECKOUT_OVERWOLF_OVERLAY -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_OVERLAY;
            case EMBEDDED_CHECKOUT_ELECTRON_WINDOW -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_WINDOW;
            default -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_UNSPECIFIED;
         };
      });
      this.method15().checkPayNowCapabilities(null, CheckPayNowCapabilitiesRequest.getDefaultInstance(), var1 -> {
         this.field13 = switch (var1.getEmbeddedCheckout()) {
            case EMBEDDED_CHECKOUT_OVERWOLF_OVERLAY -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_OVERLAY;
            case EMBEDDED_CHECKOUT_ELECTRON_WINDOW -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_WINDOW;
            default -> EmbeddedCheckoutSupport.EMBEDDED_CHECKOUT_SUPPORT_UNSPECIFIED;
         };
      });
   }

   private void method3(FocusGamePush var1) {
      Slayer.method4("IPC", "Received focus game push, focusing window");
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         ThreadModuleDump63.method3().bridge$focusWindow();
      }
   }

   private void method4(PendingPromotionUpdatePush var1) {
      Slayer.method4("IPC", "Received pending promotion push");
      ThreadModuleDump63.method4().method97().method3(var1.getPendingPromotionsList());
   }

   private void method5(StartStorePreviewSessionPush var1) {
      Slayer.method4("IPC", "Received start store preview session push");
      ThreadModuleDump63.method4().method56().method11(var1);
   }

   public void method6() {
      this.method17()
         .checkPendingPromotion(
            null, CheckPendingPromotionRequest.getDefaultInstance(), var0 -> ThreadModuleDump63.method4().method97().method3(var0.getPendingPromotionsList())
         );
      EntityRenderer4 var1 = ThreadModuleDump63.method4().method35();
      if (var1 != null) {
         var1.method14();
      }
   }

   private void method7(ExitGamePush var1) {
      Slayer.method4("IPC", "Received exit game push, shutting down");
      ThreadModuleDump63.method3().bridge$shutdownMinecraftApplet();
   }

   private void method8(PlayServerPush var1) {
      if (ThreadModuleDump63.method3().bridge$getSession() == null) {
         Slayer.method6("IPC", "Received play server push but no session is available... ignoring");
      } else {
         Slayer.method4("IPC", "Received play server push, connecting to %s", var1.getAutoJoinServerIp());
         ThreadModuleDump10.disconnect();
         ThreadModuleDump63.method3().bridge$displayScreen(null);
         ThreadModuleDump63.method3().bridge$connect(Bridge.method8().method50(var1.getAutoJoinServerIp(), var1.getAutoJoinServerIp(), false), null);
      }
   }

   public void onMessage(String var1) {
   }

   public void onMessage(ByteBuffer var1) {
      GameboundIPCMessage var2;
      try {
         var2 = GameboundIPCMessage.parseFrom(var1);
      } catch (InvalidProtocolBufferException var9) {
         Slayer.method8("IPC", "Failed to parse clientbound WebSocket message");
         var9.printStackTrace();
         return;
      }

      if (var2.getContentsCase() == ContentsCase.RPC_RESPONSE) {
         this.field2.method1(var2.getRpcResponse());
      } else if (var2.getContentsCase() == ContentsCase.PUSH_NOTIFICATION) {
         Any var3 = var2.getPushNotification();

         for (Entry var5 : this.field3.entrySet()) {
            if (var3.is((Class)var5.getKey())) {
               Message var6;
               try {
                  var6 = var3.unpack((Class)var5.getKey());
               } catch (InvalidProtocolBufferException var8) {
                  Slayer.method8("IPC", "Failed to parse wrapped message for " + var3.getTypeUrl());
                  return;
               }

               ThreadModuleDump13.runOnMainThread(var6, (Consumer<Message>)var5.getValue());
               return;
            }
         }

         Slayer.method6("IPC", "Failed to find handler for push notification " + var3.getTypeUrl());
      } else {
         Slayer.method8("IPC", "Unknown response type to consume");
      }
   }

   public void onClose(int var1, String var2, boolean var3) {
      this.field2.method2();
      if (this.field12 == null) {
         field1 = new EntityRenderer3(null, 5000L);
      }

      if (this.field12 == EntityRendererType2.READY) {
         Slayer.method4("IPC", "Connection closed (%d, \"%s\")", var1, var2);
      }

      this.field12 = EntityRendererType2.READY;
   }

   public void onError(Exception var1) {
      if (!var1.getMessage().startsWith("Connection refused:") && (LunarBuildData.field4 || ThreadModuleDump63.method34(Gui2Extension.IPC))) {
         Slayer.method8("IPC", "WS-Error: " + var1.getMessage());
      }

      if (ThreadModuleDump63.method34(Gui2Extension.IPC)) {
         var1.printStackTrace();
      }
   }

   public void method9(TebexJsCheckoutClosed var1) {
      ThreadModuleDump63.method3().bridge$displayScreen(null);
      Slayer.method4("Tebex", "Embedded checkout closed.");
   }

   public static void bootstrap() {
      ClientEventBus.method29()
         .method2(
            EventEverySecond.class,
            var0 -> {
               if (Client.method109() != null) {
                  if (ThreadModuleDump63.method6().isEmpty() || ThreadModuleDump63.method6().get().method19() == EntityRendererType2.READY) {
                     if (field1 == null) {
                        field1 = new EntityRenderer3(null, 5000L);
                     }

                     if (field1.method1()) {
                        ThreadModuleDump63.method4().method8();
                     }
                  }

                  ThreadModuleDump63.method6()
                     .ifPresent(
                        var0x -> {
                           if (ThreadModuleDump63.method3().bridge$isWindowFocused() != var0x.method21()) {
                              var0x.method22(ThreadModuleDump63.method3().bridge$isWindowFocused());
                              var0x.method13()
                                 .updateFocus(
                                    null,
                                    UpdateFocusRequest.newBuilder().setFocused(ThreadModuleDump63.method3().bridge$isWindowFocused()).build(),
                                    var0xx -> {}
                                 );
                           }
                        }
                     );
               }
            }
         );
   }

   @Generated
   public static void method10(EntityRenderer3 var0) {
      field1 = var0;
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
   public EntityRendererType2 method19() {
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
   public void method22(boolean var1) {
      this.field14 = var1;
   }
}
