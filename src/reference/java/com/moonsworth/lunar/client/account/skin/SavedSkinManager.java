package com.moonsworth.lunar.client.account.skin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.ProfileLookupCallback;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.config.JsonFileConfig;
import com.moonsworth.lunar.client.account.skin.SavedSkin;
import com.moonsworth.lunar.client.account.skin.SkinType;
import com.moonsworth.lunar.client.account.GameProfileLookupCallback;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mixin.EntityRenderer2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import lombok.Generated;

public class SavedSkinManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<String, SavedSkin> implements JsonFileConfig, Extension, EventRegistrar {
   private static final GameProfileRepository field2 = EntityRenderer2.method1().createProfileRepository();
   private final GuiIterator field3 = new GuiIterator();
   @Nullable
   private static final Method field4;
   private final List<SavedSkin> field5 = new ArrayList<>();
   private final Set<SavedSkin> field6 = new HashSet<>();
   private SavedSkin field7;
   private String field8 = null;

   @Override
   protected Map<String, SavedSkin> method3() {
      return new ConcurrentHashMap<>();
   }

   @Override
   public String method5() {
      return "saved_skins.json";
   }

   public void method3(SavedSkin var1) {
      if (!this.field5.remove(var1)) {
         this.field5.add(0, var1);
      }

      this.method10();
   }

   private void method10() {
      while (this.field5.size() > 8) {
         this.field5.remove(this.field5.size() - 1);
      }

      this.field6.clear();
      this.field6.addAll(this.field5);
      this.method21();
   }

   public static boolean method5(UUID var0) {
      return (var0.hashCode() & 1) == 1;
   }

   public SavedSkin method6(String var1) {
      GameProfile var2 = method7(var1);
      var2 = method8(var2);
      return this.method13(var2);
   }

   public static GameProfile method7(String var0) {
      GameProfileLookupCallback var1 = new GameProfileLookupCallback();
      if (ThreadModuleDump63.MC_VERSION >= 19) {
         field2.findProfilesByNames(new String[]{var0}, var1);
      } else {
         try {
            Class var2 = Class.forName("com.mojang.authlib.Agent");
            field2.getClass()
               .getDeclaredMethod("findProfilesByNames", String[].class, var2, ProfileLookupCallback.class)
               .invoke(field2, new String[]{var0}, var2.getDeclaredField("MINECRAFT").get(null), var1);
         } catch (Exception var3) {
            throw new RuntimeException(var3);
         }
      }

      return var1.method2();
   }

   public static GameProfile method8(GameProfile var0) {
      MinecraftSessionService var1 = ThreadModuleDump63.method3().bridge$getSessionService();
      if (ThreadModuleDump63.MC_VERSION >= 19) {
         var0 = var1.fetchProfile(var0.getId(), true).profile();
      } else {
         try {
            var0 = (GameProfile)var1.getClass().getDeclaredMethod("fillProfileProperties", GameProfile.class, boolean.class).invoke(var1, var0, true);
         } catch (Exception var3) {
            throw new RuntimeException(var3);
         }
      }

      return var0;
   }

   public SavedSkin method11() {
      return this.method10(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile());
   }

   public SavedSkin method10(GameProfile var1) {
      SkinType var2;
      if (var1.getId() != null && method5(var1.getId())) {
         var2 = SkinType.SLIM;
      } else {
         var2 = SkinType.CLASSIC;
      }

      return this.method18(var2);
   }

   public SavedSkin method12() {
      return this.method12(null);
   }

   public SavedSkin method12(String var1) {
      GameProfile var2 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile();
      if (var2.getProperties().isEmpty()) {
         var2 = Bridge.method69().method1(var2, ThreadModuleDump63.method3().bridge$getProfileProperties());
      }

      return this.method14(var2, var1 != null ? var1 : var2.getName() + "'s skin");
   }

   public SavedSkin method13(GameProfile var1) {
      return this.method14(var1, var1.getName() + "'s skin");
   }

   public SavedSkin method14(GameProfile var1, String var2) {
      MinecraftProfileTexture var3 = method16(var1);
      if (var3 != null) {
         SkinType var4 = getProvider(var3);
         SavedSkin var5 = new SavedSkin(var3.getUrl(), var4, var2);
         return this.method17(var5);
      } else {
         return this.method10(var1);
      }
   }

   public static SkinType getProvider(MinecraftProfileTexture var0) {
      SkinType var1 = SkinType.CLASSIC;
      if ("slim".equals(var0.getMetadata("model"))) {
         var1 = SkinType.SLIM;
      }

      return var1;
   }

   public static MinecraftProfileTexture method16(GameProfile var0) {
      MinecraftSessionService var1 = ThreadModuleDump63.method3().bridge$getSessionService();
      MinecraftProfileTexture var2;
      if (ThreadModuleDump63.MC_VERSION >= 20) {
         var2 = var1.getTextures(var0).skin();
      } else {
         try {
            Map var3 = (Map)field4.invoke(var1, var0, false);
            var2 = (MinecraftProfileTexture)var3.get(Type.SKIN);
         } catch (Exception var4) {
            throw new IllegalStateException("Failed to get textures for " + var0.getName() + " from MinecraftSessionService", var4);
         }
      }

      return var2;
   }

   public SavedSkin method17(SavedSkin var1) {
      return this.method2().computeIfAbsent(var1.getHash(), var1x -> var1);
   }

   public SavedSkin method18(SkinType var1) {
      String var2 = var1.getUserFriendlyName() + "'s skin";
      return this.method17(new SavedSkin(var1.getDefaultSkinUrl(), SkinType.SLIM, var2));
   }

   public void method19(SavedSkin var1) {
      this.method2().remove(var1.getHash());
   }

   @Override
   public void init() {
      super.init();
      if (!this.method4()) {
         for (SkinType var4 : SkinType.values()) {
            this.method18(var4);
         }
      }
   }

   public void load(JsonObject var1) {
      HashMap var2 = new HashMap();
      this.field5.clear();

      for (Entry var4 : var1.entrySet()) {
         String var5 = (String)var4.getKey();
         SavedSkin var6;
         if (((JsonElement)var4.getValue()).isJsonObject()) {
            JsonObject var7 = ((JsonElement)var4.getValue()).getAsJsonObject();
            var6 = new SavedSkin(var5, SkinType.valueOf(var7.get("type").getAsString().toUpperCase()), var7.get("name").getAsString());
            JsonElement var8 = var7.get("favoriteIndex");
            if (var8 != null) {
               int var9 = var8.getAsJsonPrimitive().getAsInt();
               this.field5.add(var6);
               var2.put(var6, var9);
            }
         } else {
            var6 = new SavedSkin(var5, SkinType.CLASSIC, "unnamed skin");
         }

         this.method17(var6);
      }

      this.field5.sort(Comparator.comparing(var2::get));
      this.method10();
   }

   public void method1(JsonObject var1) {
      this.method21();
      this.method2().values().forEach(var2 -> {
         JsonObject var3 = new JsonObject();
         var3.addProperty("type", var2.method2().toString());
         var3.addProperty("name", var2.getName());
         int var4 = this.field5.indexOf(var2);
         if (var4 != -1) {
            var3.addProperty("favoriteIndex", var4);
         }

         var1.add(var2.getUrl(), var3);
      });
   }

   @Override
   public void close() {
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   public void method21() {
      if (this.field7 == null) {
         this.field7 = this.method12();
      }

      this.field3.method3("actual", this.field7.provide());
      SkinType[] var1 = SkinType.values();
      JsonArray var2 = new JsonArray(var1.length);

      for (SkinType var6 : var1) {
         var2.add(var6.provide());
      }

      this.field3.method3("skinTypes", var2);
      Collection var7 = this.method2().values();
      JsonArray var8 = new JsonArray(var7.size());

      for (SavedSkin var10 : var7) {
         var8.add(var10.provide());
      }

      this.field3.method3("savedSkins", var8);
   }

   public void method22(SavedSkin var1) {
      this.field7 = var1;
      this.field8 = var1.method2().toString();
   }

   @Generated
   public GuiIterator getProvider() {
      return this.field3;
   }

   @Generated
   public List<SavedSkin> method24() {
      return this.field5;
   }

   @Generated
   public Set<SavedSkin> method25() {
      return this.field6;
   }

   @Generated
   public SavedSkin method26() {
      return this.field7;
   }

   @Generated
   public void method27(String var1) {
      this.field8 = var1;
   }

   @Generated
   public String method28() {
      return this.field8;
   }

   static {
      if (ThreadModuleDump63.MC_VERSION >= 20) {
         field4 = null;
      } else {
         try {
            field4 = MinecraftSessionService.class.getDeclaredMethod("getTextures", GameProfile.class, boolean.class);
         } catch (Exception var1) {
            throw new IllegalStateException("Failed to initialized SavedSkinManager", var1);
         }
      }
   }
}
