package com.moonsworth.lunar.client.account;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.conversation.v1.ConversationParticipant;
import com.lunarclient.websocket.conversation.v1.ConversationSender;
import com.lunarclient.websocket.conversation.v1.ConversationSender.SenderCase;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class GuiProfile implements JsonProviderLegacy {
   private final UUID field1;
   private final String field2;
   private int field3;
   private int field4;
   private String field5 = "";
   private boolean field6;
   @Nullable
   private Gui2Handler2 field7 = null;

   public static GuiProfile method1(ConversationParticipant var0) {
      return new GuiProfile(
         ThreadModuleDump66.method1(var0.getPlayer().getUuid()),
         var0.getPlayer().getUsername(),
         var0.getLogoColor().getColor(),
         var0.getPlusColor().getColor(),
         var0.getRankName(),
         var0.getIsRadioPremium(),
         var0.getBadgeId() > 0 ? (Gui2Handler2)ThreadModuleDump63.method4().method95().method2().get(var0.getBadgeId()) : null
      );
   }

   @Nullable
   public static GuiProfile method2(ConversationSender var0) {
      return var0.getSenderCase() != SenderCase.PLAYER
         ? null
         : new GuiProfile(ThreadModuleDump66.method1(var0.getPlayer().getUuid()), var0.getPlayer().getUsername());
   }

   public static GuiProfile method3(UuidAndUsername var0) {
      return new GuiProfile(ThreadModuleDump66.method1(var0.getUuid()), var0.getUsername());
   }

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("uuid", this.field1.toString());
      var1.addProperty("username", this.field2);
      JsonObject var2 = new JsonObject();
      var2.addProperty("isLunarPlus", this.field4 != 0);
      var2.addProperty("plusColor", String.format("#%06X", 16777215 & this.field4));
      var2.addProperty("logoColor", String.format("#%06X", 16777215 & this.field3));
      var1.add("lunarPlus", var2);
      var1.addProperty("rankName", this.field5);
      var1.addProperty("radioPremium", this.field6);
      if (this.field7 != null) {
         var1.add("badge", this.field7.provide());
      }

      return var1;
   }

   @Generated
   public UUID method4() {
      return this.field1;
   }

   @Generated
   public String getUsername() {
      return this.field2;
   }

   @Generated
   public int method5() {
      return this.field3;
   }

   @Generated
   public int method6() {
      return this.field4;
   }

   @Generated
   public String getRankName() {
      return this.field5;
   }

   @Generated
   public boolean method7() {
      return this.field6;
   }

   @Nullable
   @Generated
   public Gui2Handler2 method8() {
      return this.field7;
   }

   @Generated
   public GuiProfile(UUID var1, String var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Generated
   public GuiProfile(UUID var1, String var2, int value, int value2, String text, boolean flag, @Nullable Gui2Handler2 var7) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = value;
      this.field4 = value2;
      this.field5 = text;
      this.field6 = flag;
      this.field7 = var7;
   }
}
