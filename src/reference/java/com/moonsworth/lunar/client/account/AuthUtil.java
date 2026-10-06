package com.moonsworth.lunar.client.account;

import com.google.common.primitives.Ints;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lunarclient.gameipc.auth.v1.AddAccountRequest;
import com.lunarclient.gameipc.auth.v1.RefreshAccountRequest;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.account.AccountManager;
import com.moonsworth.lunar.client.mixin.EntityRendererType2;
import com.moonsworth.lunar.client.waypoints.WebSocketClientIterator;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.Generated;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import com.moonsworth.lunar.client.util.ThreadModuleDump36;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;

public final class AuthUtil {
   public static void method1(Consumer<AuthUtil.Data> var0) {
      Integer var1 = Ints.tryParse(ThreadModuleDump80.launcherVersion.replace(".", ""));
      Slayer.method4("Microsoft Auth", "Launcher version (as int): " + var1);
      Optional var2 = ThreadModuleDump63.method6();
      if (!var2.isEmpty() && ((WebSocketClientIterator)var2.get()).method19() == EntityRendererType2.READY) {
         ((WebSocketClientIterator)var2.get()).method11().addAccount(null, AddAccountRequest.newBuilder().build(), var1x -> {
            if (var1x.getSuccess()) {
               var0.accept(new AuthUtil.Data(true));
            } else {
               AuthUtil.Type var2x = switch (var1x.getError()) {
                  case ACCOUNT_ERROR_NOT_PURCHASED_MINECRAFT -> AuthUtil.Type.NOT_PURCHASED_MINECRAFT;
                  case ACCOUNT_ERROR_UNSPECIFIED, UNRECOGNIZED -> AuthUtil.Type.UNKNOWN;
                  default -> throw new IncompatibleClassChangeError();
               };
               var0.accept(new AuthUtil.Data(false, var2x));
            }
         });
      } else {
         var0.accept(new AuthUtil.Data(false, AuthUtil.Type.LAUNCHER_NOT_OPEN));
         Slayer.method4("Microsoft Auth", "No launcher open");
      }
   }

   public static void method2(AccountSession var0, Consumer<AuthUtil.RefreshAccountResult> var1) {
      AccountManager.method24("Refreshing account: %s", var0.getUsername());
      Optional var2 = ThreadModuleDump63.method6();
      if (!var2.isEmpty() && ((WebSocketClientIterator)var2.get()).method19() == EntityRendererType2.READY) {
         ((WebSocketClientIterator)var2.get())
            .method11()
            .refreshAccount(
               null,
               RefreshAccountRequest.newBuilder()
                  .setUuid(ThreadModuleDump66.method3(UUID.fromString(ThreadModuleDump36.method1(var0.method10().getId()))))
                  .build(),
               var1x -> {
                  if (var1x.getSuccess()) {
                     var1.accept(new AuthUtil.RefreshAccountResult(true));
                  } else {
                     AuthUtil.Type2 var2x = switch (var1x.getError()) {
                        case ACCOUNT_ERROR_MISSING_REFRESH_TOKEN -> AuthUtil.Type2.MISSING_REFRESH_TOKEN;
                        case ACCOUNT_ERROR_NOT_PURCHASED_MINECRAFT -> AuthUtil.Type2.NOT_PURCHASED_MINECRAFT;
                        case ACCOUNT_ERROR_ACCOUNTS_FILE_EMPTY -> AuthUtil.Type2.ACCOUNTS_FILE_EMPTY;
                        default -> AuthUtil.Type2.UNKNOWN;
                     };
                     var1.accept(new AuthUtil.RefreshAccountResult(false, var2x));
                  }
               }
            );
      } else {
         var1.accept(new AuthUtil.RefreshAccountResult(false, AuthUtil.Type2.LAUNCHER_NOT_OPEN));
         Slayer.method4("Microsoft Auth", "No launcher open");
      }
   }

   public static JsonObject method3(String var0) {
      JsonObject var1 = new JsonObject();
      if (var0 == null) {
         Slayer.method6("Auth", "Invalid JWT in AuthUtil...");
         return var1;
      }

      if (var0.contains(".") && var0.split("\\.").length >= 2) {
         String var2 = new String(Base64.getDecoder().decode(var0.split("\\.")[1]), StandardCharsets.UTF_8);
         JsonParser var3 = new JsonParser();

         try {
            JsonElement var4 = var3.parse(var2);
            if (var4.isJsonObject()) {
               var1 = (JsonObject)var4;
            }
         } catch (Exception var5) {
            return var1;
         }
      }

      return var1;
   }

   @Generated
   private AuthUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static class Data {
      private final boolean field1;
      private AuthUtil.Type field2;

      @Generated
      public Data(boolean var1) {
         this.field1 = var1;
      }

      @Generated
      public Data(boolean var1, AuthUtil.Type var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      @Generated
      public boolean isSuccessful() {
         return this.field1;
      }

      @Generated
      public AuthUtil.Type method1() {
         return this.field2;
      }
   }

   public static class RefreshAccountResult {
      private final boolean field1;
      private AuthUtil.Type2 field2;

      @Generated
      public RefreshAccountResult(boolean var1) {
         this.field1 = var1;
      }

      @Generated
      public RefreshAccountResult(boolean var1, AuthUtil.Type2 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      @Generated
      public boolean isSuccessful() {
         return this.field1;
      }

      @Generated
      public AuthUtil.Type2 method1() {
         return this.field2;
      }
   }

   public enum Type implements Calculator2 {
      UNKNOWN("unknown"),
      LAUNCHER_NOT_OPEN("launcher_not_open"),
      NOT_PURCHASED_MINECRAFT("not_purchased_minecraft");

      private final String id;

      public static Optional<AuthUtil.Type> getFromId(String var0) {
         for (AuthUtil.Type var4 : values()) {
            if (var4.getId().equals(var0)) {
               return Optional.of(var4);
            }
         }

         return Optional.empty();
      }

      @Override
      public String getLanguagePath() {
         return "popups.add_account";
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type(String var3) {
         this.id = var3;
      }

      @Generated
      public String getId() {
         return this.id;
      }
   }

   public enum Type2 implements Calculator2 {
      UNKNOWN("unknown"),
      ACCOUNTS_FILE_EMPTY("accounts_file_empty"),
      LAUNCHER_NOT_OPEN("launcher_not_open"),
      NOT_PURCHASED_MINECRAFT("not_purchased_minecraft"),
      MISSING_REFRESH_TOKEN("missing_refresh_token");

      private final String id;

      public static Optional<AuthUtil.Type2> getFromId(String var0) {
         for (AuthUtil.Type2 var4 : values()) {
            if (var4.getId().equals(var0)) {
               return Optional.of(var4);
            }
         }

         return Optional.empty();
      }

      @Override
      public String getLanguagePath() {
         return "popups.refresh_auth";
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type2(String var3) {
         this.id = var3;
      }

      @Generated
      public String getId() {
         return this.id;
      }
   }
}
