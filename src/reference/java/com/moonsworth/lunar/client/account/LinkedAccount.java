package com.moonsworth.lunar.client.account;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.common.v1.UserSocialPlatform;
import com.lunarclient.websocket.socials.v1.LinkedSocial;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump34;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Generated;

public class LinkedAccount implements JsonProviderLegacy {
   private final String field1;
   private final String field2;
   private final UserSocialPlatform field3;
   private final PhosphorIconLegacy field4;
   @Nullable
   private LinkedSocial field5;
   private final List<Integer> field6;

   public LinkedAccount(String var1, String var2, UserSocialPlatform var3, PhosphorIconLegacy var4) {
      this(var1, var2, var3, var4, null);
   }

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("platform", this.field3.name());
      var1.addProperty("name", this.field2);
      var1.addProperty("icon", this.field4.ordinal());
      if (this.field5 != null) {
         JsonObject var2 = new JsonObject();
         String var3 = ThreadModuleDump34.method5(ThreadModuleDump66.method5(this.field5.getLinkedAt()));
         var2.addProperty("linkedAt", var3);
         JsonObject var4 = new JsonObject();
         var4.addProperty("username", this.field5.getSocial().getUsername());
         var4.addProperty("avatar", this.field5.getSocial().getAvatar());
         var2.add("userSocial", var4);
         JsonObject var5 = new JsonObject();
         var5.addProperty("isMember", this.field5.getCommunity().getIsMember());
         if (this.field5.getCommunity().hasJoinedAt()) {
            String var6 = ThreadModuleDump34.method5(ThreadModuleDump66.method5(this.field5.getCommunity().getJoinedAt()));
            var5.addProperty("joinedAt", var6);
         }

         if (this.field5.getCommunity().hasLeftAt()) {
            String var8 = ThreadModuleDump34.method5(ThreadModuleDump66.method5(this.field5.getCommunity().getLeftAt()));
            var5.addProperty("leftAt", var8);
         }

         var5.addProperty("hasFlair", this.field5.getCommunity().getHasFlair());
         var2.add("community", var5);
         var1.add("linkedSocial", var2);
      }

      if (this.field6 != null) {
         JsonArray var7 = new JsonArray();
         this.field6.forEach(var7::add);
         var1.add("cosmeticRewards", var7);
      }

      return var1;
   }

   @Generated
   public LinkedAccount(String var1, String var2, UserSocialPlatform var3, PhosphorIconLegacy var4, List<Integer> var5) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field6 = var5;
   }

   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public String getName() {
      return this.field2;
   }

   @Generated
   public UserSocialPlatform getPlatform() {
      return this.field3;
   }

   @Generated
   public void method1(@Nullable LinkedSocial var1) {
      this.field5 = var1;
   }
}
