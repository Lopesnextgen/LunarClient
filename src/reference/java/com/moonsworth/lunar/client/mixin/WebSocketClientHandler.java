package com.moonsworth.lunar.client.mixin;

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
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.framework.feature.debug.Gui2Extension;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
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

public final class WebSocketClientHandler extends WebSocketClient {
   private final UuidAndUsername field1;
   private final String field2;
   private final Consumer<String> field3;
   private boolean success = false;

   public WebSocketClientHandler(UuidAndUsername var1, String var2, Consumer<String> var3) {
      super(
         URI.create(ServiceEndpoints.method5() + "/game"),
         new Draft_6455(),
         Map.of(
            "Accept",
            "application/x-protobuf",
            "sentry-trace",
            ThreadModuleDump80.sentryTraceId,
            "User-Agent",
            "Lunar Client " + Client.method19(),
            "X-Initiator",
            var2
         ),
         30000
      );
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      Slayer.method4("Authentication", "Instantiate");
   }

   public void onOpen(ServerHandshake var1) {
      this.method6(
         ServerboundWebSocketMessage.newBuilder().setHello(HelloMessage.newBuilder().setIdentity(this.field1).setInitiator(this.field2).build()).build()
      );
   }

   public void onMessage(String var1) {
   }

   public void onMessage(ByteBuffer var1) {
      ClientboundWebSocketMessage var2;
      try {
         var2 = ClientboundWebSocketMessage.parseFrom(var1);
      } catch (InvalidProtocolBufferException var4) {
         throw new RuntimeException(var4);
      }

      switch (var2.getContentsCase()) {
         case ENCRYPTION_REQUEST:
            this.method3(var2.getEncryptionRequest());
            break;
         case AUTH_SUCCESS:
            this.method4(var2.getAuthSuccess());
            break;
         case CONTENTS_NOT_SET:
            throw new IllegalStateException("Invalid websocket message from server");
      }
   }

   public void onClose(int var1, String var2, boolean var3) {
      Slayer.method4("Auth", "Connection closed (%d, \"%s\")", var1, var2);
      if (!this.success) {
         this.field3.accept(null);
      }
   }

   public void onError(Exception var1) {
      Slayer.method6("Auth", "Error Connection");
      var1.printStackTrace();
   }

   private void method1(Exception var1, String var2, String var3) {
      if (var3 == null || var3.isEmpty()) {
         var3 = "An unknown error occurred.";
      }

      ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, var2, var3);
      Slayer.method6("Auth", var2);
      var1.printStackTrace();
      this.method5(var2 + " (" + var1.getClass().getSimpleName() + ")");
   }

   private void method2(Exception var1) {
      if (var1 instanceof InvocationTargetException) {
         Throwable var2 = ((InvocationTargetException)var1).getTargetException();
         if (var2 instanceof Exception) {
            this.method2((Exception)var2);
            return;
         }
      }

      TranslationManager var4 = ThreadModuleDump63.method4().method67();
      if (var1 instanceof AuthenticationUnavailableException) {
         this.method1(var1, "Servers Unavailable", var4.method2("popups.auth", "servers_unavailable"));
      } else if (var1 instanceof InvalidCredentialsException) {
         this.method1(var1, "Invalid Session", var4.method2("popups.auth", "invalid_session"));
      } else if (var1 instanceof AuthenticationException) {
         String var3 = var1.getClass().getSimpleName();
         if (var3.equals("InsufficientPrivilegesException")) {
            this.method1(var1, "Insufficient Privileges", var4.method2("popups.auth", "insufficient_privileges"));
            return;
         }

         if (var3.equals("ForcedUsernameChangeException") || var3.equals("UserBannedException")) {
            this.method1(var1, "User Banned", var4.method2("popups.auth", "user_banned"));
            return;
         }

         this.method1(var1, "Authentication Error", var1.getMessage());
      } else {
         this.method1(var1, "Internal Error", var1.getMessage());
      }
   }

   public void method3(EncryptionRequestMessage var1) {
      PublicKey var2 = CryptManagerBridge.method2(var1.getPublicKey().toByteArray());
      byte[] var3 = var1.getRandomBytes().toByteArray();
      SecretKey var4 = CryptManagerBridge.method3();
      byte[] var5 = CryptManagerBridge.method4("", var2, var4);
      if (var5 == null) {
         this.method5("No Hash (NullPointerException)");
      } else {
         String var6 = new BigInteger(var5).toString(16);

         try {
            MinecraftSessionService var7 = EntityRenderer2.method1().createMinecraftSessionService();
            String var8 = ThreadModuleDump63.method3().bridge$getSession().bridge$getToken();
            GameProfile var9 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile();
            if (ThreadModuleDump63.MC_VERSION >= 19) {
               var7.joinServer(var9.getId(), var8, var6);
            } else {
               var7.getClass().getDeclaredMethod("joinServer", GameProfile.class, String.class, String.class).invoke(var7, var9, var8, var6);
            }
         } catch (Exception var10) {
            this.method2(var10);
            return;
         }

         byte[] var11 = CryptManagerBridge.method1(var2, var4.getEncoded());
         byte[] var12 = CryptManagerBridge.method1(var2, var3);
         this.method6(
            ServerboundWebSocketMessage.newBuilder()
               .setEncryptionResponse(
                  EncryptionResponseMessage.newBuilder().setPublicKey(ByteString.copyFrom(var12)).setSecretKey(ByteString.copyFrom(var11)).build()
               )
               .build()
         );
      }
   }

   public void method4(AuthSuccessMessage var1) {
      this.success = true;
      this.field3.accept(var1.getJwt());
   }

   private void method5(String var1) {
      this.method6(ServerboundWebSocketMessage.newBuilder().setEncryptionFail(EncryptionFailMessage.newBuilder().setReason(var1).build()).build());
   }

   private void method6(ServerboundWebSocketMessage var1) {
      this.send(var1.toByteArray());
      if (ThreadModuleDump63.method34(Gui2Extension.ASSET_SERVER)) {
         Slayer.method4("Auth", "Sent: %s", var1.getClass().getSimpleName());
      }
   }
}
