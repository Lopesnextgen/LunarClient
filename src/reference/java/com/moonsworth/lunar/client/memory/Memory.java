package com.moonsworth.lunar.client.memory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.common.v1.Location;
import com.lunarclient.common.v1.RadioInfo;
import com.lunarclient.common.v1.RadioTrack;
import com.lunarclient.common.v1.UserSocial;
import com.lunarclient.common.v1.Location.LocationCase;
import com.lunarclient.websocket.friend.v1.FriendSocials;
import com.lunarclient.websocket.hostedworld.v1.Joinability;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.driver.core.gui.Annotation;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.mixin.EntityRendererType;
import com.moonsworth.lunar.client.util.ThreadModuleDump21;
import com.moonsworth.lunar.client.util.ThreadModuleDump34;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.UUID;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public class Memory {
   private final UUID field1;
   private String name;
   private String field2;
   private Location field3;
   private Config field4;
   private long field5;
   @Nullable
   private Instant field6;
   @Nullable
   private FriendSocials field7;
   private EntityRendererType field8 = EntityRendererType.OFFLINE;
   private Joinability field9;
   private boolean field10;
   private long durationMs;
   private int color = -1;
   private long field11;
   private Vec3Bridge field12;
   private Vec3Bridge field13;
   private long field14;
   private long field15;
   @Nullable
   private String field16;
   @Nullable
   private Component field17;
   @Nullable
   private RadioInfo field18;
   private int field19;
   private int field20;
   @Nullable
   private Gui2Handler2 field21;
   @Nullable
   private String rank;
   private boolean field22;
   private boolean field23;
   private boolean field24;
   private String field25 = "steve.png";

   @Deprecated
   public Memory(UUID var1) {
      this.field1 = var1;
      this.method8();
   }

   public Memory(UUID var1, String var2) {
      this(var1);
      this.setName(var2);
   }

   public void method1(Vec3Bridge var1) {
      this.field12 = this.field13 == null ? var1 : this.field13;
      this.field13 = var1;
      long var2 = System.currentTimeMillis();
      if (this.field14 != 0L) {
         this.field15 = var2 - this.field14;
      }

      this.field14 = var2;
   }

   public void method2() {
      this.field12 = null;
      this.field13 = null;
      this.field16 = null;
   }

   public double method3() {
      if (this.field15 <= 0L) {
         return 1.0;
      }

      double var1 = System.currentTimeMillis() - this.field14;
      return Math.min(1.0, var1 / this.field15);
   }

   public void method4(EntityRendererType var1) {
      this.field8 = var1;
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method35() != null && this == ThreadModuleDump63.method4().method31()) {
         ThreadModuleDump63.method4().method35().method111().method3("onlineStatus", var1.getName());
      }
   }

   public int method5() {
      return method7(this.field8);
   }

   public boolean method6() {
      return this.field8 != EntityRendererType.OFFLINE;
   }

   public static int method7(EntityRendererType renderer) {
      if (renderer == null) {
         return -4210753;
      }

      return switch (renderer) {
         case AWAY -> -5991424;
         case BUSY -> -904879;
         case INVISIBLE, OFFLINE -> -4210753;
         case ONLINE -> -12209557;
      };
   }

   public void setName(String var1) {
      this.name = var1;
      this.field2 = AdventureChatFormatting.getTextWithoutFormattingCodes(var1);
   }

   private void method8() {
      String var1 = this.field1.toString() + ".png";
      Path var2 = ThreadModuleDump48.field15;

      try {
         Files.createDirectories(var2);
      } catch (IOException var4) {
         Inventorymod2.method5(var4, "Loading Avatars");
         return;
      }

      File[] var3 = var2.toFile().listFiles();
      if (var3 != null) {
         if (Arrays.stream(var3).noneMatch(var1x -> var1x.getName().equals(var1))) {
            Slayer.method4("WebOSR", "Avatar doesn't exist, download: " + var1);
            new Thread(new MemoryTask(this)).start();
         } else {
            this.method52(var1);
         }
      }
   }

   @Annotation("friends")
   public JsonProviderLegacy method9() {
      return () -> {
         JsonObject var1 = new JsonObject();
         var1.addProperty("uuid", this.field1.toString());
         var1.addProperty("file", this.field25);
         var1.addProperty("formattedName", this.name);
         var1.addProperty("name", this.field2);
         var1.addProperty("online", this.field8.getName());
         var1.addProperty("version", this.field4 == null ? null : this.field4.method45());
         var1.add("statusDetails", ThreadModuleDump21.method1(this.field1, this.field3, this.field8, this.field5));
         var1.addProperty("invitedMeToHostedWorld", this.field10);
         var1.addProperty("hostedWorldJoinability", this.field9 != null ? this.field9.name() : Joinability.JOINABILITY_UNSPECIFIED.name());
         var1.addProperty("canJoinServer", this.getLocation() != null && this.getLocation().getLocationCase() == LocationCase.PUBLIC_SERVER);
         if (this.field18 != null && !this.field18.getTrack().getTitle().isEmpty()) {
            RadioTrack var2 = this.field18.getTrack();
            var1.addProperty("isRadioPlaying", true);
            var1.addProperty("radioTitle", var2.getTitle());
            var1.addProperty("radioArtist", String.join(", ", var2.getArtistNamesList()));
            var1.addProperty("radioCoverArt", var2.getImageUrl());
         } else {
            var1.addProperty("isRadioPlaying", false);
         }

         if (this.field21 != null) {
            var1.add("badge", this.field21.method2());
         }

         if (this.rank != null) {
            var1.addProperty("rank", this.rank);
         }

         JsonObject var8 = new JsonObject();
         var8.addProperty("isLunarPlus", this.field19 != 0);
         var8.addProperty("plusColor", String.format("#%06X", 16777215 & this.method25()));
         var8.addProperty("logoColor", String.format("#%06X", 16777215 & this.method26()));
         var1.add("lunarPlus", var8);
         var1.addProperty("pinned", this.field24);
         if (this.field6 != null && this.field6.toEpochMilli() > 0L) {
            LocalDateTime var3 = LocalDateTime.ofInstant(this.field6, ZoneId.systemDefault());
            var1.addProperty("friendsSince", var3.format(ThreadModuleDump34.field2));
         }

         if (this.field7 != null) {
            JsonObject var9 = new JsonObject();
            var9.addProperty("hidden", this.field7.getHidden());
            JsonArray var4 = new JsonArray();

            for (UserSocial var6 : this.field7.getLinkedList()) {
               JsonObject var7 = new JsonObject();
               var7.addProperty("platform", var6.getPlatform().name());
               var7.addProperty("username", var6.getUsername());
               var7.addProperty("avatar", var6.getAvatar());
               var4.add(var7);
            }

            var9.add("linked", var4);
            var1.add("socials", var9);
         }

         return var1;
      };
   }

   @Generated
   public UUID method10() {
      return this.field1;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String method11() {
      return this.field2;
   }

   @Generated
   public Location getLocation() {
      return this.field3;
   }

   @Generated
   public Config method12() {
      return this.field4;
   }

   @Generated
   public long method13() {
      return this.field5;
   }

   @Nullable
   @Generated
   public Instant method14() {
      return this.field6;
   }

   @Nullable
   @Generated
   public FriendSocials method15() {
      return this.field7;
   }

   @Generated
   public EntityRendererType method16() {
      return this.field8;
   }

   @Generated
   public Joinability getHostedWorldJoinability() {
      return this.field9;
   }

   @Generated
   public boolean method17() {
      return this.field10;
   }

   @Generated
   public long getDurationMs() {
      return this.durationMs;
   }

   @Generated
   public int getColor() {
      return this.color;
   }

   @Generated
   public long method18() {
      return this.field11;
   }

   @Generated
   public Vec3Bridge method19() {
      return this.field12;
   }

   @Generated
   public Vec3Bridge method20() {
      return this.field13;
   }

   @Generated
   public long method21() {
      return this.field14;
   }

   @Generated
   public long method22() {
      return this.field15;
   }

   @Nullable
   @Generated
   public String method23() {
      return this.field16;
   }

   @Nullable
   @Generated
   public Component method24() {
      return this.field17;
   }

   @Nullable
   @Generated
   public RadioInfo getRadioInfo() {
      return this.field18;
   }

   @Generated
   public int method25() {
      return this.field19;
   }

   @Generated
   public int method26() {
      return this.field20;
   }

   @Nullable
   @Generated
   public Gui2Handler2 method27() {
      return this.field21;
   }

   @Nullable
   @Generated
   public String getRank() {
      return this.rank;
   }

   @Generated
   public boolean method29() {
      return this.field22;
   }

   @Generated
   public boolean method30() {
      return this.field23;
   }

   @Generated
   public boolean method31() {
      return this.field24;
   }

   @Generated
   public String method32() {
      return this.field25;
   }

   @Generated
   public void method33(Location var1) {
      this.field3 = var1;
   }

   @Generated
   public void method34(Config var1) {
      this.field4 = var1;
   }

   @Generated
   public void method35(long var1) {
      this.field5 = var1;
   }

   @Generated
   public void method36(@Nullable Instant var1) {
      this.field6 = var1;
   }

   @Generated
   public void method37(@Nullable FriendSocials var1) {
      this.field7 = var1;
   }

   @Generated
   public void method38(Joinability var1) {
      this.field9 = var1;
   }

   @Generated
   public void method39(boolean var1) {
      this.field10 = var1;
   }

   @Generated
   public void setDurationMs(long var1) {
      this.durationMs = var1;
   }

   @Generated
   public void setColor(int var1) {
      this.color = var1;
   }

   @Generated
   public void method41(long var1) {
      this.field11 = var1;
   }

   @Generated
   public void method42(@Nullable String var1) {
      this.field16 = var1;
   }

   @Generated
   public void method43(@Nullable Component var1) {
      this.field17 = var1;
   }

   @Generated
   public void method44(@Nullable RadioInfo var1) {
      this.field18 = var1;
   }

   @Generated
   public void method45(int var1) {
      this.field19 = var1;
   }

   @Generated
   public void method46(int var1) {
      this.field20 = var1;
   }

   @Generated
   public void method47(@Nullable Gui2Handler2 var1) {
      this.field21 = var1;
   }

   @Generated
   public void setRank(@Nullable String var1) {
      this.rank = var1;
   }

   @Generated
   public void method49(boolean var1) {
      this.field22 = var1;
   }

   @Generated
   public void method50(boolean var1) {
      this.field23 = var1;
   }

   @Generated
   public void method51(boolean var1) {
      this.field24 = var1;
   }

   @Generated
   public void method52(String var1) {
      this.field25 = var1;
   }
}
