package com.moonsworth.lunar.client.account;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.account.skin.SavedSkin;
import com.moonsworth.lunar.client.account.AccountManager;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.driver.bridge.JsonSection;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.util.text.DateUtils;
import com.moonsworth.lunar.client.util.text.UuidUtils;
import com.moonsworth.lunar.client.cosmetics.DummyPlayer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.texture.HeadTextureCache;
import com.moonsworth.lunar.client.account.AuthUtil.RefreshAccountResult;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.Generated;

public abstract class AccountSession implements JsonConfigurable {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "steve.png");
   private String field2;
   private Instant field3;
   private String field4;
   private MinecraftProfile field5;
   private String field6;
   private AccountType field7;
   private String username;
   private AccountProfile field8;
   private boolean field9 = false;
   private transient String field10;
   private transient ResourceLocationBridge field11;
   private transient boolean field12;
   private transient boolean invalid;
   private Instant field13;

   public AccountSession(String text1) {
      this.field4 = text1;
   }

   public void load(JsonObject json1) {
      if (json1.has("accessTokenExpiresAt") && !json1.get("accessTokenExpiresAt").isJsonNull()) {
         this.field3 = Instant.parse(json1.get("accessTokenExpiresAt").getAsString());
      } else {
         this.field3 = null;
      }

      this.field4 = json1.get("localId").getAsString();
      this.field5 = new MinecraftProfile();
      this.field5.load(json1.get("minecraftProfile").getAsJsonObject());
      this.field6 = json1.get("remoteId").getAsString();
      this.field7 = AccountType.valueOf(json1.get("type").getAsString().toUpperCase());
      if (json1.has("username")) {
         this.username = json1.get("username").getAsString();
      }

      if (json1.has("accessToken") && !json1.get("accessToken").getAsString().isEmpty()) {
         this.field2 = json1.get("accessToken").getAsString();
      } else {
         AccountManager.method24("No access token found for [%s]", this.username);
      }

      if (json1.has("refreshToken")) {
         this.field10 = json1.get("refreshToken").getAsString();
      }

      this.field8 = new AccountProfile();
      this.field8.load(json1);
   }

   public void method1(JsonObject json1) {
      JsonObject json2 = new JsonObject();
      json1.add(this.field4, json2);
      json2.addProperty("accessToken", this.field2);
      if (this.field3 != null) {
         json2.addProperty("accessTokenExpiresAt", this.field3.toString());
      }

      if (this.field8 == null) {
         this.field8 = new AccountProfile();
      }

      this.field8.method1(json2);
      json2.addProperty("localId", this.field4);
      json2.addProperty("refreshToken", this.field10);
      this.field5.method1(json2);
      json2.addProperty("remoteId", this.field6);
      json2.addProperty("type", this.field7.getFormatted());
      json2.addProperty("username", this.username);
   }

   public abstract void method2(Consumer<RefreshAccountResult> consumer1);

   public abstract boolean method3();

   public abstract long method4();

   public abstract int method5();

   public ResourceLocationBridge method6() {
      if (this.field11 == null) {
         if (this.field5 == null) {
            return field1;
         }

         String text1 = UuidUtils.method1(this.field5.getId());
         this.field11 = HeadTextureCache.method1(UUID.fromString(text1));
      }

      return this.field11;
   }

   public void method7() {
      AccountManager.method24("Setting account '%s' as the current session.", this.username);
      Ref.method3()
         .bridge$setSession(Bridge.method8().method17(this.method10().getName(), this.method10().getId(), this.getAccessToken(), "msa"));
      Horsestats horsestats1 = Ref.method3().bridge$getSession();
      if (horsestats1 != null && horsestats1.bridge$getProfile() != null && horsestats1.bridge$getProfile().getId() != null) {
         com.moonsworth.lunar.client.fog.fishing.Gui2Handler savedSkin = Ref.method4().method75().method12();
         if (savedSkin != null) {
            Ref.method4().method75().method22(savedSkin);
            if (HologramsIterator2.method14() != null) {
               ResourceLocationBridge horsestats143 = HologramsIterator2.method18(savedSkin.getHash(), savedSkin.getUrl());
               String text4 = savedSkin.method2().toString().equals("classic") ? "default" : savedSkin.method2().toString();
               HologramsIterator2.method14().bridge$setSkinLocation(horsestats143, text4);
               HologramsIterator2.method21(horsestats143);
               NickHider.realSkinLocation = text4;
               GameProfile gameprofile5 = HologramsIterator2.method14().bridge$getGameProfile();
               ((DummyPlayer)HologramsIterator2.method14()).setGameProfile(new GameProfile(gameprofile5.getId(), horsestats1.bridge$getProfile().getName()));
            }
         }
      }

      Ref.method4().method75().method21();
   }

   @JsonSection("accounts")
   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("uuid", UuidUtils.method1(this.field5.getId()));
      json1.addProperty("username", this.username);
      json1.addProperty("accountType", this.field7.getFormatted());
      boolean flag2 = this.method3();
      json1.addProperty("validSession", flag2);
      json1.addProperty("loading", this.field12);
      json1.addProperty("invalid", this.invalid);
      json1.addProperty("localId", this.field4);
      Instant instant3 = this.field3 != null
         ? this.field3.minus(1L, ChronoUnit.DAYS)
         : (this.method4() > 0L ? Instant.ofEpochMilli(this.method4()).minus(1L, ChronoUnit.DAYS) : Instant.now());
      json1.addProperty("lastUsedAt", instant3.getEpochSecond());
      json1.addProperty("lastUsedAtReadable", "Last used " + DateUtils.method5(instant3));
      if (this.field13 != null) {
         LocalDateTime localdatetime4 = LocalDateTime.ofInstant(this.field13, ZoneId.systemDefault());
         json1.addProperty("createdAt", localdatetime4.format(DateUtils.field2));
      }

      return json1;
   }

   @Generated
   public String getAccessToken() {
      return this.field2;
   }

   @Generated
   public Instant method8() {
      return this.field3;
   }

   @Generated
   public String method9() {
      return this.field4;
   }

   @Generated
   public MinecraftProfile method10() {
      return this.field5;
   }

   @Generated
   public String method11() {
      return this.field6;
   }

   @Generated
   public AccountType method12() {
      return this.field7;
   }

   @Generated
   public String getUsername() {
      return this.username;
   }

   @Generated
   public AccountProfile method13() {
      return this.field8;
   }

   @Generated
   public boolean method14() {
      return this.field9;
   }

   @Generated
   public String method15() {
      return this.field10;
   }

   @Generated
   public boolean isLoading() {
      return this.field12;
   }

   @Generated
   public boolean isInvalid() {
      return this.invalid;
   }

   @Generated
   public Instant method16() {
      return this.field13;
   }

   @Generated
   public void method17(String text1) {
      this.field2 = text1;
   }

   @Generated
   public void method18(Instant instant1) {
      this.field3 = instant1;
   }

   @Generated
   public void method19(String text1) {
      this.field4 = text1;
   }

   @Generated
   public void method20(MinecraftProfile minecraftProfile) {
      this.field5 = minecraftProfile;
   }

   @Generated
   public void method21(String text1) {
      this.field6 = text1;
   }

   @Generated
   public void method22(AccountType accountType) {
      this.field7 = accountType;
   }

   @Generated
   public void setUsername(String text1) {
      this.username = text1;
   }

   @Generated
   public void method23(AccountProfile accountProfile) {
      this.field8 = accountProfile;
   }

   @Generated
   public void method24(boolean flag1) {
      this.field9 = flag1;
   }

   @Generated
   public void method25(String text1) {
      this.field10 = text1;
   }

   @Generated
   public void method26(ResourceLocationBridge horsestats141) {
      this.field11 = horsestats141;
   }

   @Generated
   public void method27(boolean flag1) {
      this.field12 = flag1;
   }

   @Generated
   public void setInvalid(boolean flag1) {
      this.invalid = flag1;
   }

   @Generated
   public void method28(Instant instant1) {
      this.field13 = instant1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof AccountSession lighting3loader22)) {
         return false;
      } else {
         if (!lighting3loader22.canEqual(this)) {
            return false;
         }

         if (this.method14() != lighting3loader22.method14()) {
            return false;
         }

         String text3 = this.getAccessToken();
         String text4 = lighting3loader22.getAccessToken();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            Instant instant5 = this.method8();
            Instant instant6 = lighting3loader22.method8();
            if (instant5 == null ? instant6 == null : instant5.equals(instant6)) {
               String text7 = this.method9();
               String text8 = lighting3loader22.method9();
               if (text7 == null ? text8 == null : text7.equals(text8)) {
                  MinecraftProfile lighting3loader39 = this.method10();
                  MinecraftProfile lighting3loader310 = lighting3loader22.method10();
                  if (lighting3loader39 == null ? lighting3loader310 == null : lighting3loader39.equals(lighting3loader310)) {
                     String text11 = this.method11();
                     String text12 = lighting3loader22.method11();
                     if (text11 == null ? text12 == null : text11.equals(text12)) {
                        AccountType fogtype13 = this.method12();
                        AccountType fogtype14 = lighting3loader22.method12();
                        if (fogtype13 == null ? fogtype14 == null : fogtype13.equals(fogtype14)) {
                           String text15 = this.getUsername();
                           String text16 = lighting3loader22.getUsername();
                           if (text15 == null ? text16 == null : text15.equals(text16)) {
                              AccountProfile lighting3loader17 = this.method13();
                              AccountProfile lighting3loader18 = lighting3loader22.method13();
                              if (lighting3loader17 == null ? lighting3loader18 == null : lighting3loader17.equals(lighting3loader18)) {
                                 Instant instant19 = this.method16();
                                 Instant instant20 = lighting3loader22.method16();
                                 return instant19 == null ? instant20 == null : instant19.equals(instant20);
                              } else {
                                 return false;
                              }
                           } else {
                              return false;
                           }
                        } else {
                           return false;
                        }
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof AccountSession;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.method14() ? 79 : 97);
      String text3 = this.getAccessToken();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      Instant instant4 = this.method8();
      number2 = number2 * 59 + (instant4 == null ? 43 : instant4.hashCode());
      String text5 = this.method9();
      number2 = number2 * 59 + (text5 == null ? 43 : text5.hashCode());
      MinecraftProfile lighting3loader36 = this.method10();
      number2 = number2 * 59 + (lighting3loader36 == null ? 43 : lighting3loader36.hashCode());
      String text7 = this.method11();
      number2 = number2 * 59 + (text7 == null ? 43 : text7.hashCode());
      AccountType fogtype8 = this.method12();
      number2 = number2 * 59 + (fogtype8 == null ? 43 : fogtype8.hashCode());
      String text9 = this.getUsername();
      number2 = number2 * 59 + (text9 == null ? 43 : text9.hashCode());
      AccountProfile lighting3loader10 = this.method13();
      number2 = number2 * 59 + (lighting3loader10 == null ? 43 : lighting3loader10.hashCode());
      Instant instant11 = this.method16();
      return number2 * 59 + (instant11 == null ? 43 : instant11.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "Account(accessToken="
         + this.getAccessToken()
         + ", accessTokenExpiresAt="
         + this.method8()
         + ", localId="
         + this.method9()
         + ", profile="
         + this.method10()
         + ", remoteId="
         + this.method11()
         + ", type="
         + this.method12()
         + ", username="
         + this.getUsername()
         + ", accountComplianceData="
         + this.method13()
         + ", triedRefreshing="
         + this.method14()
         + ", refreshToken="
         + this.method15()
         + ", head="
         + this.method6()
         + ", loading="
         + this.isLoading()
         + ", invalid="
         + this.isInvalid()
         + ", createdAt="
         + this.method16()
         + ")";
   }
}
