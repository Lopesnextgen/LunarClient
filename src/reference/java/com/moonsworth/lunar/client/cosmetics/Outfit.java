package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.cosmetic.v2.EquippedCosmetic;
import com.lunarclient.websocket.cosmetic.v2.Outfit;
import com.lunarclient.websocket.cosmetic.v2.UpdateOutfitRequest;
import com.lunarclient.websocket.cosmetic.v2.Outfit.Builder;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.account.skin.SavedSkin;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump34;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Outfit implements JsonProviderLegacy {
   private final UUID field1;
   private String name;
   private List<CosmeticMetadata> field2;
   private boolean field3;
   @Nullable
   private final Instant field4;
   @Nullable
   private final Instant field5;
   @Nullable
   private SavedSkin field6;
   @Nullable
   private Gui2Handler2 field7;

   public static Outfit method1(Outfit var0) {
      UUID var1 = ThreadModuleDump66.method1(var0.getId());
      String var2 = var0.getName();
      boolean var3 = var0.getFavorite();
      Instant var4 = var0.hasCreatedAt() ? ThreadModuleDump66.method5(var0.getCreatedAt()) : null;
      Instant var5 = var0.hasUpdatedAt() ? ThreadModuleDump66.method5(var0.getUpdatedAt()) : null;
      List var6 = var0.getCosmeticsList();
      List var7 = var6.stream().map(var0x -> {
         com.moonsworth.lunar.client.cosmetics.OwnedCosmetic var1x = ThreadModuleDump63.method4().method53().method51(var0x.getCosmeticId());
         return var1x == null ? null : new CosmeticMetadata(var1x, ThreadModuleDump66.method22(var0x.getGeckolibMetadata()));
      }).filter(var0x -> var0x != null && var0x.method4() != null && var0x.method4().method10() != null).collect(Collectors.toCollection(ArrayList::new));
      SavedSkin var8 = null;
      if (var0.hasSkinHash()) {
         var8 = (SavedSkin)ThreadModuleDump63.method4().method75().method2().get(var0.getSkinHash());
      }

      Gui2Handler2 var9 = null;
      if (var0.hasBadgeId()) {
         var9 = (Gui2Handler2)ThreadModuleDump63.method4().method95().method2().get(var0.getBadgeId());
      }

      return new Outfit(var1, var2, var7, var3, var4, var5, var8, var9);
   }

   public Outfit method2() {
      Builder var1 = Outfit.newBuilder()
         .setId(ThreadModuleDump66.method3(this.field1))
         .setName(this.name)
         .addAllCosmetics(
            this.field2
               .stream()
               .map(var0 -> EquippedCosmetic.newBuilder().setCosmeticId((int)var0.method4().method9()).setGeckolibMetadata(var0.method2()).build())
               .collect(Collectors.toList())
         )
         .setFavorite(this.field3);
      if (this.field4 != null) {
         var1.setCreatedAt(ThreadModuleDump66.method6(this.field4));
      }

      if (this.field5 != null) {
         var1.setUpdatedAt(ThreadModuleDump66.method6(this.field5));
      }

      if (this.field6 != null) {
         var1.setSkinHash(this.field6.getHash());
      }

      if (this.field7 != null) {
         var1.setBadgeId(this.field7.id());
      }

      return var1.build();
   }

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.field1.toString());
      var1.addProperty("name", this.name);
      var1.addProperty("favorite", this.field3);
      var1.addProperty("createdAt", this.field4 == null ? null : ThreadModuleDump34.method5(this.field4));
      var1.addProperty("updatedAt", this.field5 == null ? null : ThreadModuleDump34.method5(this.field5));
      JsonArray var2 = new JsonArray();

      for (CosmeticMetadata var4 : this.field2) {
         var2.add(var4.provide());
      }

      if (this.field6 != null) {
         var1.add("skin", this.field6.provide());
      } else if (ThreadModuleDump63.method4().method75().method26() != null) {
         var1.add("skin", ThreadModuleDump63.method4().method75().method26().provide());
      }

      if (this.field7 != null) {
         var1.add("badge", this.field7.provide());
      }

      var1.add("cosmetics", var2);
      return var1;
   }

   public void method3() {
      ThreadModuleDump63.method4().method35().method87().updateOutfit(null, UpdateOutfitRequest.newBuilder().setOutfit(this.method2()).build(), var0 -> {});
      ThreadModuleDump63.method4().method55().method6();
   }

   @Generated
   public Outfit(
      UUID var1,
      String var2,
      List<CosmeticMetadata> var3,
      boolean var4,
      @Nullable Instant var5,
      @Nullable Instant var6,
      @Nullable SavedSkin var7,
      @Nullable Gui2Handler2 var8
   ) {
      this.field1 = var1;
      this.name = var2;
      this.field2 = var3;
      this.field3 = var4;
      this.field4 = var5;
      this.field5 = var6;
      this.field6 = var7;
      this.field7 = var8;
   }

   @Generated
   public UUID getId() {
      return this.field1;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public List<CosmeticMetadata> method4() {
      return this.field2;
   }

   @Generated
   public boolean method5() {
      return this.field3;
   }

   @Nullable
   @Generated
   public Instant method6() {
      return this.field4;
   }

   @Nullable
   @Generated
   public Instant method7() {
      return this.field5;
   }

   @Nullable
   @Generated
   public SavedSkin method8() {
      return this.field6;
   }

   @Nullable
   @Generated
   public Gui2Handler2 method9() {
      return this.field7;
   }

   @Generated
   public void setName(String var1) {
      this.name = var1;
   }

   @Generated
   public void method10(List<CosmeticMetadata> var1) {
      this.field2 = var1;
   }

   @Generated
   public void method11(boolean var1) {
      this.field3 = var1;
   }

   @Generated
   public void method12(@Nullable SavedSkin var1) {
      this.field6 = var1;
   }

   @Generated
   public void method13(@Nullable Gui2Handler2 var1) {
      this.field7 = var1;
   }
}
