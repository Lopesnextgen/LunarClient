package com.moonsworth.lunar.client.account;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.badge.v1.OwnedBadge;
import com.lunarclient.websocket.badge.v1.OwnedBadge.ExpirationReason;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump34;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.framework.Client;

public class OwnedBadge implements JsonProviderLegacy {
   @NotNull
   private final Gui2Handler2 field1;
   private final Instant field2;
   private final Instant field3;
   private final ExpirationReason field4;
   private final boolean field5;

   public OwnedBadge(@NotNull Gui2Handler2 var1, Instant var2, Instant var3, ExpirationReason var4, boolean flag) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = flag;
   }

   private String method2() {
      TranslationManager var1 = Client.method109().method67();
      String var2;
      if (this.field3.isBefore(Instant.now())) {
         int var3 = (int)((Instant.now().toEpochMilli() - this.field3.toEpochMilli()) / 86400000L);
         var2 = var1.method2("gui.emotes", var3 > 1 ? "expired_plural" : "expired_singular", var3);
      } else {
         int var4 = (int)((this.field3.toEpochMilli() - Instant.now().toEpochMilli()) / 86400000L);
         var2 = var1.method2("gui.emotes", var4 > 1 ? "expired_plural" : "expires_in_singular", var4);
      }

      return var2;
   }

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.add("badge", this.field1.provide());
      if (this.field3 != null && this.field3.toEpochMilli() > 0L) {
         var1.addProperty("expiresAt", this.field3.toEpochMilli());
         var1.addProperty("expiresIn", this.method2());
      }

      if (this.field2 != null) {
         LocalDateTime var2 = LocalDateTime.ofInstant(this.field2, ZoneId.systemDefault());
         var1.addProperty("grantedAt", this.field2.getEpochSecond());
         var1.addProperty("grantedAtReadable", var2.format(ThreadModuleDump34.field1));
      } else {
         var1.addProperty("grantedAt", 0);
         var1.addProperty("grantedAtReadable", "Unknown");
      }

      if (this.field4 != null) {
         var1.addProperty("expirationReason", this.field4.name());
      }

      var1.addProperty("isLunarPlus", this.field5);
      return var1;
   }

   public static Optional<OwnedBadge> method2(OwnedBadge var0) {
      Gui2Handler2 var1 = (Gui2Handler2)ThreadModuleDump63.method4().method95().method2().get(var0.getBadgeId());
      return var1 == null
         ? Optional.empty()
         : Optional.of(
            new OwnedBadge(
               var1,
               ThreadModuleDump66.method5(var0.getGrantedAt()),
               ThreadModuleDump66.method5(var0.getExpiresAt()),
               var0.getExpirationReason(),
               var0.getIsLunarPlus()
            )
         );
   }

   public static OwnedBadge method3(Gui2Handler2 var0) {
      return new OwnedBadge(var0, null, null, null, false);
   }

   @NotNull
   public Gui2Handler2 method4() {
      return this.field1;
   }

   public Instant method5() {
      return this.field2;
   }

   public Instant method6() {
      return this.field3;
   }

   public ExpirationReason method7() {
      return this.field4;
   }

   public boolean method8() {
      return this.field5;
   }
}
