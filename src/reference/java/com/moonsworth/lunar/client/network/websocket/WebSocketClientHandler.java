package com.moonsworth.lunar.client.network.websocket;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lunarclient.authenticator.v1.AuthSuccessMessage;
import com.lunarclient.authenticator.v1.ClientboundWebSocketMessage;
import com.lunarclient.authenticator.v1.EncryptionFailMessage;
import com.lunarclient.authenticator.v1.EncryptionRequestMessage;
import com.lunarclient.authenticator.v1.EncryptionResponseMessage;
import com.lunarclient.authenticator.v1.HelloMessage;
import com.lunarclient.authenticator.v1.ServerboundWebSocketMessage;
import com.lunarclient.common.v1.UuidAndUsername;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.moonsworth.lunar.bridge.horsestats.CryptManagerBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.framework.feature.debug.DebugType;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.LaunchOptions;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.util.Map;
import java.util.function.Consumer;
import javax.crypto.SecretKey;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import com.moonsworth.lunar.client.account.YggdrasilAuthServiceFactory;

public final class WebSocketClientHandler extends WebSocketClient {
   private final UuidAndUsername field1;
   private final String field2;
   private final Consumer<String> field3;
   private boolean success = false;

   public WebSocketClientHandler(UuidAndUsername uuidandusername1, String text2, Consumer<String> consumer3) {
      super(
         URI.create(ServiceEndpoints.method5() + "/game"),
         new Draft_6455(),
         Map.of(
            "Accept",
            "application/x-protobuf",
            "sentry-trace",
            LaunchOptions.field3,
            "User-Agent",
            "Lunar Client " + Client.method19(),
            "X-Initiator",
            text2
         ),
         30000
      );
      this.field1 = uuidandusername1;
      this.field2 = text2;
      this.field3 = consumer3;
      LunarLogger.method4("Authentication", "Instantiate", new Object[0]);
   }

   public void onOpen(ServerHandshake serverhandshake1) {
      this.method6(
         ServerboundWebSocketMessage.newBuilder().setHello(HelloMessage.newBuilder().setIdentity(this.field1).setInitiator(this.field2).build()).build()
      );
   }

   public void onMessage(String text1) {
   }

   public void onMessage(ByteBuffer buffer1) {
      ClientboundWebSocketMessage clientboundwebsocketmessage2;
      try {
         clientboundwebsocketmessage2 = ClientboundWebSocketMessage.parseFrom(buffer1);
      } catch (InvalidProtocolBufferException invalidprotocolbufferexception4) {
         throw new RuntimeException(invalidprotocolbufferexception4);
      }

      switch (clientboundwebsocketmessage2.getContentsCase()) {
         case ENCRYPTION_REQUEST:
            this.method3(clientboundwebsocketmessage2.getEncryptionRequest());
            break;
         case AUTH_SUCCESS:
            this.method4(clientboundwebsocketmessage2.getAuthSuccess());
            break;
         case CONTENTS_NOT_SET:
            throw new IllegalStateException("Invalid websocket message from server");
      }
   }

   public void onClose(int number1, String text2, boolean flag3) {
      LunarLogger.method4("Auth", "Connection closed (%d, \"%s\")", new Object[]{number1, text2});
      if (!this.success) {
         this.field3.accept(null);
      }
   }

   public void onError(Exception exception1) {
      LunarLogger.method6("Auth", "Error Connection", new Object[0]);
      exception1.printStackTrace();
   }

   private void method1(Exception exception1, String text2, String text3) {
      if (text3 == null || text3.isEmpty()) {
         text3 = "An unknown error occurred.";
      }

      Ref.method4().method69().method6(NotificationType.ERROR, text2, text3);
      LunarLogger.method6("Auth", text2, new Object[0]);
      exception1.printStackTrace();
      this.method5(text2 + " (" + exception1.getClass().getSimpleName() + ")");
   }

   private void method2(Exception exception1) {
      if (exception1 instanceof InvocationTargetException) {
         Throwable exception2 = ((InvocationTargetException)exception1).getTargetException();
         if (exception2 instanceof Exception) {
            this.method2((Exception)exception2);
            return;
         }
      }

      TranslationManager foghandler284 = Ref.method4().method67();
      if (exception1 instanceof AuthenticationUnavailableException) {
         this.method1(exception1, "Servers Unavailable", foghandler284.method2("popups.auth", "servers_unavailable", new Object[0]));
      } else if (exception1 instanceof InvalidCredentialsException) {
         this.method1(exception1, "Invalid Session", foghandler284.method2("popups.auth", "invalid_session", new Object[0]));
      } else if (exception1 instanceof AuthenticationException) {
         String text3 = exception1.getClass().getSimpleName();
         if (text3.equals("InsufficientPrivilegesException")) {
            this.method1(exception1, "Insufficient Privileges", foghandler284.method2("popups.auth", "insufficient_privileges", new Object[0]));
            return;
         }

         if (text3.equals("ForcedUsernameChangeException") || text3.equals("UserBannedException")) {
            this.method1(exception1, "User Banned", foghandler284.method2("popups.auth", "user_banned", new Object[0]));
            return;
         }

         this.method1(exception1, "Authentication Error", exception1.getMessage());
      } else {
         this.method1(exception1, "Internal Error", exception1.getMessage());
      }
   }

   public void method3(EncryptionRequestMessage encryptionrequestmessage1) {
      PublicKey publickey2 = CryptManagerBridge.method2(encryptionrequestmessage1.getPublicKey().toByteArray());
      byte[] items3 = encryptionrequestmessage1.getRandomBytes().toByteArray();
      SecretKey secretkey4 = CryptManagerBridge.method3();
      byte[] items5 = CryptManagerBridge.method4("", publickey2, secretkey4);
      if (items5 == null) {
         this.method5("No Hash (NullPointerException)");
      } else {
         String text6 = new BigInteger(items5).toString(16);

         try {
            MinecraftSessionService minecraftsessionservice7 = YggdrasilAuthServiceFactory.method1().createMinecraftSessionService();
            String text8 = Ref.method3().bridge$getSession().bridge$getToken();
            GameProfile gameprofile9 = Ref.method3().bridge$getSession().bridge$getProfile();
            if (Ref.MC_VERSION >= 19) {
               minecraftsessionservice7.joinServer(gameprofile9.getId(), text8, text6);
            } else {
               minecraftsessionservice7.getClass().getDeclaredMethod("joinServer", GameProfile.class, String.class, String.class).invoke(minecraftsessionservice7, gameprofile9, text8, text6);
            }
         } catch (Exception exception10) {
            this.method2(exception10);
            return;
         }

         byte[] items11 = CryptManagerBridge.method1(publickey2, secretkey4.getEncoded());
         byte[] items12 = CryptManagerBridge.method1(publickey2, items3);
         this.method6(
            ServerboundWebSocketMessage.newBuilder()
               .setEncryptionResponse(
                  EncryptionResponseMessage.newBuilder().setPublicKey(ByteString.copyFrom(items12)).setSecretKey(ByteString.copyFrom(items11)).build()
               )
               .build()
         );
      }
   }

   public void method4(AuthSuccessMessage authsuccessmessage1) {
      this.success = true;
      this.field3.accept(authsuccessmessage1.getJwt());
   }

   private void method5(String text1) {
      this.method6(ServerboundWebSocketMessage.newBuilder().setEncryptionFail(EncryptionFailMessage.newBuilder().setReason(text1).build()).build());
   }

   private void method6(ServerboundWebSocketMessage serverboundwebsocketmessage1) {
      this.send(serverboundwebsocketmessage1.toByteArray());
      if (Ref.method34(DebugType.ASSET_SERVER)) {
         LunarLogger.method4("Auth", "Sent: %s", new Object[]{serverboundwebsocketmessage1.getClass().getSimpleName()});
      }
   }
}
