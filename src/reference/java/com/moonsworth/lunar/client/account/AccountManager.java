package com.moonsworth.lunar.client.account;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.account.AccountType;
import com.moonsworth.lunar.client.config.JsonFileConfig;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.account.XboxAccountSession;
import com.moonsworth.lunar.client.account.MinecraftProfile;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump36;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.sentry.ISpan;
import io.sentry.ITransaction;
import io.sentry.Sentry;
import io.sentry.protocol.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import lombok.Generated;

public class AccountManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<String, AccountSession> implements JsonFileConfig, Extension, EventRegistrar {
   private String field2;
   private String field3;
   @Nullable
   private ITransaction field4;
   private final HashMap<String, ISpan> field5 = new HashMap<>();
   private final GuiIterator field6 = new GuiIterator();

   public AccountManager() {
      this.handle(EventEverySecond.class, this::method1);
   }

   private void method1(EventEverySecond var1) {
      AccountSession var2 = this.method10();
      if (var2 != null) {
         if (!var2.method3() && !var2.method14()) {
            this.method13();
            var2 = this.method10();
            if (var2 == null) {
               return;
            }

            String var3 = var2.getUsername();
            if (var2.method3()) {
               method24("The launcher refreshed the access token for the current account %s.", var3);
               return;
            }

            if (var2.getAccessToken() == null || var2.method15() == null) {
               var2.method24(true);
               method24("Current account %s is missing token(s), skipping refresh.", var3);
               return;
            }

            method24("Current account's access token has expired, refreshing...");
            var2.method24(true);
            this.method29(var2.method10().getId(), true);
            var2.method2(var2x -> {
               AccountSession var3x = this.method10();
               if (var3x != null && var3x.method10() != null) {
                  this.method29(var3x.method10().getId(), false);
                  if (var2x.isSuccessful()) {
                     this.method30(var3x.method10().getId(), false);
                     method24("Successfully refreshed access token for account %s", var3);
                     this.method13();
                  } else {
                     this.method30(var3x.method10().getId(), true);
                     method24("Failed to refresh access token for account %s", var3);
                  }
               }
            });
         }
      }
   }

   @Nullable
   public AccountSession method10() {
      return this.field3 == null ? null : this.method2().get(this.field3);
   }

   public void method3(@Nullable String var1) {
      this.field3 = var1;
      AccountSession var2 = this.method10();
      if (var2 != null) {
         this.field6.method3("selectedAccount", var2.provide());
      }
   }

   @Override
   protected Map<String, AccountSession> method3() {
      return new ConcurrentHashMap<>();
   }

   @Override
   public File method6() {
      File var1 = new File(ThreadModuleDump48.field25 + File.separator + "accounts.json");
      if (var1.exists()) {
         return var1;
      }

      try {
         var1.createNewFile();
      } catch (IOException var3) {
         var3.printStackTrace();
      }

      return new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), this.method5());
   }

   @Override
   public String method5() {
      return "launcher_accounts.json";
   }

   @Override
   public void init() {
      super.init();
      this.method13(true);
   }

   private boolean method11() {
      File var1 = this.method6();
      if (!var1.exists()) {
         return false;
      } else {
         boolean var2 = var1.setWritable(true);
         if (!var2) {
            method24("Could not set accounts file to writable!");
            this.method12();
            return true;
         } else {
            return !Files.isWritable(var1.toPath());
         }
      }
   }

   private void method12() {
      ThreadModuleDump63.method3()
         .bridge$schedule(
            () -> ThreadModuleDump63.method4()
               .method69()
               .method7(NotificationType.ERROR, com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("accounts_file_not_writable", new Object[0]))
         );
   }

   @Override
   public boolean method8() {
      return this.method11() ? false : JsonFileConfig.super.method8();
   }

   @Override
   public void method7(boolean var1) {
      if (!this.method11()) {
         JsonFileConfig.super.method7(var1);
      }
   }

   @Override
   public void method8(Exception var1, File var2) {
      if (var1 instanceof FileNotFoundException && var2.isFile()) {
         method24("Could not write to file: %s", var2.getAbsolutePath());
         this.method12();
      } else {
         JsonFileConfig.super.method8(var1, null);
      }
   }

   public void method13() {
      this.method13(false);
   }

   public void method13(boolean var1) {
      this.method2().clear();
      this.method8();
      ArrayList var2 = new ArrayList();

      for (AccountSession var4 : this.method2().values()) {
         MinecraftProfile var5 = var4.method10();
         if (var4.method12() != AccountType.XBOX) {
            method24("Removing %s because it is not an XBOX account.", var4.getUsername());
            var2.add(var4);
         } else if (!var2.contains(var4) && var5 != null) {
            for (AccountSession var7 : this.method2().values()) {
               MinecraftProfile var8 = var7.method10();
               if (!var2.contains(var7) && var8 != null && !var5.equals(var8) && var5.getId().equals(var8.getId())) {
                  method24("Removing duplicate account because it's xbox");
                  var2.add(var7);
                  break;
               }
            }
         }
      }

      method24("Removed all accounts [%s count]", var2.size());
      this.method2().values().removeAll(var2);
      this.method14();
      if (var1) {
         AccountSession var9 = this.method10();
         if (var9 != null && !var9.method3()) {
            var9.method2(var2x -> {
               if (var2x.isSuccessful()) {
                  method24("Successfully refreshed access token for account %s", var9.getUsername());
                  this.method13();
               } else {
                  method24("Failed to refresh access token for account %s", var9.getUsername());
               }
            });
         }
      }
   }

   private void method14() {
      if (FeatureFlag.SENTRY_ACCOUNT_LOGIN_TRACING.isEnabled()) {
         this.field4 = Sentry.startTransaction("Auth", "auth3");
      } else {
         this.field4 = null;
      }

      this.method26();
   }

   private void method16() {
      method24("Current account is null?! Signing into the first account we find.");
      this.method2()
         .entrySet()
         .stream()
         .filter(var0 -> var0.getValue().method3())
         .findFirst()
         .ifPresentOrElse(var1 -> this.method16(var1.getValue()), () -> this.method16(null));
   }

   public void method16(AccountSession var1) {
      this.method17(var1, false);
   }

   public void method17(AccountSession var1, boolean var2) {
      if (var1 == null) {
         this.method3(null);
         Sentry.setUser(null);
      } else {
         this.method3(var1.getUsername());
         ISpan var3 = null;
         if (this.field4 != null) {
            var3 = this.field4.startChild("setCurrentAccount " + var1.getUsername());
         }

         var1.method7();
         if (var3 != null) {
            var3.finish();
         }

         User var4 = new User();
         var4.setUsername(var1.getUsername());
         String var5 = ThreadModuleDump36.method1(var1.method10().getId());
         var4.setId(var5);
         Sentry.setUser(var4);
         if (var2) {
            this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         }
      }
   }

   public void load(JsonObject var1) {
      if (var1.has("mojangClientToken")) {
         this.field2 = var1.get("mojangClientToken").getAsString();
      }

      String var2 = "";
      if (var1.has("activeAccountLocalId")) {
         var2 = var1.get("activeAccountLocalId").getAsString();
      }

      if (var1.has("accounts")) {
         JsonObject var3 = var1.get("accounts").getAsJsonObject();

         for (Entry var5 : var3.entrySet()) {
            method24("Attempting to load account [%s]", var5.getKey());
            JsonObject var6 = ((JsonElement)var5.getValue()).getAsJsonObject();
            AccountType var7 = AccountType.valueOf(var6.get("type").getAsString().toUpperCase());
            if (var7 == AccountType.XBOX) {
               XboxAccountSession var8 = new XboxAccountSession((String)var5.getKey());
               var8.load(var6);
               Instant var9 = var8.method8();
               if (var9 != null && var9.isAfter(Instant.now().plus(14L, ChronoUnit.DAYS))) {
                  method24("Account [%s] was invalid (2)", var8.getUsername());
                  this.method2().remove(var8.getUsername());
               } else {
                  method24("Loaded content for [%s] Token IAT", var8.getUsername(), var8.method5());
                  AccountSession var10 = this.method2().get(var8.getUsername());
                  if (var10 != null) {
                     if (!var8.method3()) {
                        method24("Account was skipped due to double account which is invalid.");
                        continue;
                     }

                     if (var10.method5() < var8.method5()) {
                        method24("Double Account, getting the newer one so we're skipping this one.");
                        continue;
                     }

                     var2 = this.method18(var2, var5, var8);
                     method24("Removing old account.");
                     method24("Duplicate account found!");
                  }

                  this.method2().put(var8.getUsername(), var8);
                  if (var2.equals(var5.getKey()) || var10 != null && var10.method9().equals(var5.getKey())) {
                     if (var8.getAccessToken() != null && var8.method15() != null) {
                        method24("Setting currentAccount to what was on disk: %s", var8.getUsername());
                        this.method16(var8);
                     } else {
                        this.method30(var8.method10().getId(), true);
                     }
                  }
               }
            } else if (var7 != AccountType.MOJANG) {
               throw new IllegalArgumentException("Unknown type for " + var7.name());
            }
         }
      }

      this.method26();
   }

   private String method18(String var1, Entry<String, JsonElement> var2, AccountSession var3) {
      this.method2().remove(var3.getUsername());
      if (this.field3 != null && this.field3.equals(var3.getUsername())) {
         this.field3 = var3.getUsername();
         var1 = (String)var2.getKey();
      }

      this.method26();
      return var1;
   }

   @Override
   public boolean method4() {
      return true;
   }

   @Override
   public void close() {
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   public void method1(JsonObject var1) {
      this.method26();
      var1.addProperty("mojangClientToken", this.field2);
      AccountSession var2 = this.method10();
      if (var2 != null) {
         var1.addProperty("activeAccountLocalId", var2.method9());
      }

      JsonObject var3 = new JsonObject();
      var1.add("accounts", var3);

      for (AccountSession var5 : this.method2().values()) {
         var5.method1(var3);
      }
   }

   public boolean method21(AccountSession var1) {
      this.method2().remove(var1.getUsername());
      if (var1.getUsername().equals(this.field3)) {
         this.field3 = null;
         this.method16();
      }

      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      return true;
   }

   public boolean method22() {
      AccountSession var1 = this.method10();
      return var1 != null && var1.getAccessToken() != null && var1.method3();
   }

   public boolean method23() {
      return !LunarBuildData.field4
         ? true
         : this.method22()
            && ThreadModuleDump63.method3().bridge$getSession() != null
            && ThreadModuleDump63.method3().bridge$getSession().bridge$isValidSession();
   }

   public static void method24(Object var0, Object... var1) {
      Slayer.method4("Accounts", var0, var1);
   }

   @Override
   public Map<String, AccountSession> method2() {
      return super.method2();
   }

   private void method26() {
      this.field6.method2("accounts", this.method2().values());
   }

   public void method27(boolean var1) {
      this.field6.method3("loggingIn", var1);
   }

   public void method28(boolean var1) {
      this.field6.method3("addingAccount", var1);
   }

   public void method29(String var1, boolean var2) {
      AccountSession var3 = this.method31(var1);
      if (var3 != null) {
         var3.method27(var2);
         if (var2) {
            var3.setInvalid(false);
         }

         this.method26();
         this.method32(var3);
      }
   }

   public void method30(String var1, boolean var2) {
      AccountSession var3 = this.method31(var1);
      if (var3 != null) {
         var3.method27(false);
         var3.setInvalid(var2);
         this.method26();
         this.method32(var3);
      }
   }

   private AccountSession method31(String var1) {
      return this.method2().values().stream().filter(var1x -> var1x.method10() != null && var1x.method10().getId().equals(var1)).findFirst().orElse(null);
   }

   private void method32(AccountSession var1) {
      AccountSession var2 = this.method10();
      if (var2 != null && var2 == var1) {
         this.field6.method3("selectedAccount", var1.provide());
      }
   }

   public void method33(Instant var1) {
      AccountSession var2 = this.method10();
      if (var2 != null) {
         var2.method28(var1);
         this.field6.method3("selectedAccount", var2.provide());
         this.method26();
      }
   }

   @Generated
   public void method34(String var1) {
      this.field2 = var1;
   }

   @Generated
   public String method35() {
      return this.field2;
   }

   @Generated
   public HashMap<String, ISpan> method36() {
      return this.field5;
   }

   @Generated
   public GuiIterator getProvider() {
      return this.field6;
   }
}
