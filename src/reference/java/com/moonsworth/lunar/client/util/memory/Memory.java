package com.moonsworth.lunar.client.util.memory;

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
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.account.Badge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.driver.bridge.JsonSection;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.network.friend.FriendStatus;
import com.moonsworth.lunar.client.network.friend.FriendStatusUtils;
import com.moonsworth.lunar.client.util.text.DateUtils;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
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
   private FriendStatus field8 = FriendStatus.OFFLINE;
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
   private Badge field21;
   @Nullable
   private String rank;
   private boolean field22;
   private boolean field23;
   private boolean field24;
   private String field25 = "steve.png";

   @Deprecated
   public Memory(UUID uuid1) {
      this.field1 = uuid1;
      this.method8();
   }

   public Memory(UUID uuid1, String text2) {
      this(uuid1);
      this.setName(text2);
   }

   public void method1(Vec3Bridge horsestats151) {
      this.field12 = this.field13 == null ? horsestats151 : this.field13;
      this.field13 = horsestats151;
      long number2 = System.currentTimeMillis();
      if (this.field14 != 0L) {
         this.field15 = number2 - this.field14;
      }

      this.field14 = number2;
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

      double value1 = System.currentTimeMillis() - this.field14;
      return Math.min(1.0, value1 / this.field15);
   }

   public void method4(FriendStatus entityrenderertype1) {
      this.field8 = entityrenderertype1;
      if (Ref.method4() != null && Ref.method4().method35() != null && this == Ref.method4().method31()) {
         Ref.method4().method35().method111().method3("onlineStatus", entityrenderertype1.getName());
      }
   }

   public int method5() {
      return method7(this.field8);
   }

   public boolean method6() {
      return this.field8 != FriendStatus.OFFLINE;
   }

   public static int method7(FriendStatus entityrenderertype0) {
      if (entityrenderertype0 == null) {
         return -4210753;
      }

      return switch (entityrenderertype0) {
         case AWAY -> -5991424;
         case BUSY -> -904879;
         case INVISIBLE, OFFLINE -> -4210753;
         case ONLINE -> -12209557;
         default -> throw new IncompatibleClassChangeError();
      };
   }

   public void setName(String text1) {
      this.name = text1;
      this.field2 = ChatFormatting.getTextWithoutFormattingCodes(text1);
   }

   private void method8() {
      String text1 = this.field1.toString() + ".png";
      Path path2 = LunarConstants.field15;

      try {
         Files.createDirectories(path2);
      } catch (IOException exception4) {
         CrashReporter.method5(exception4, "Loading Avatars");
         return;
      }

      File[] items3 = path2.toFile().listFiles();
      if (items3 != null) {
         if (Arrays.stream(items3).noneMatch(arg1x -> arg1x.getName().equals(text1))) {
            LunarLogger.method4("WebOSR", "Avatar doesn't exist, download: " + text1, new Object[0]);
            new Thread(new MemoryTask(this)).start();
         } else {
            this.method52(text1);
         }
      }
   }

   @JsonSection("friends")
   public JsonProvider method9() {
      return () -> {
         JsonObject json1 = new JsonObject();
         json1.addProperty("uuid", this.field1.toString());
         json1.addProperty("file", this.field25);
         json1.addProperty("formattedName", this.name);
         json1.addProperty("name", this.field2);
         json1.addProperty("online", this.field8.getName());
         json1.addProperty("version", this.field4 == null ? null : this.field4.method45());
         json1.add("statusDetails", FriendStatusUtils.method1(this.field1, this.field3, this.field8, this.field5));
         json1.addProperty("invitedMeToHostedWorld", this.field10);
         json1.addProperty("hostedWorldJoinability", this.field9 != null ? this.field9.name() : Joinability.JOINABILITY_UNSPECIFIED.name());
         json1.addProperty("canJoinServer", this.getLocation() != null && this.getLocation().getLocationCase() == LocationCase.PUBLIC_SERVER);
         if (this.field18 != null && !this.field18.getTrack().getTitle().isEmpty()) {
            RadioTrack radiotrack2 = this.field18.getTrack();
            json1.addProperty("isRadioPlaying", true);
            json1.addProperty("radioTitle", radiotrack2.getTitle());
            json1.addProperty("radioArtist", String.join(", ", radiotrack2.getArtistNamesList()));
            json1.addProperty("radioCoverArt", radiotrack2.getImageUrl());
         } else {
            json1.addProperty("isRadioPlaying", false);
         }

         if (this.field21 != null) {
            json1.add("badge", this.field21.method2());
         }

         if (this.rank != null) {
            json1.addProperty("rank", this.rank);
         }

         JsonObject json8 = new JsonObject();
         json8.addProperty("isLunarPlus", this.field19 != 0);
         json8.addProperty("plusColor", String.format("#%06X", 16777215 & this.method25()));
         json8.addProperty("logoColor", String.format("#%06X", 16777215 & this.method26()));
         json1.add("lunarPlus", json8);
         json1.addProperty("pinned", this.field24);
         if (this.field6 != null && this.field6.toEpochMilli() > 0L) {
            LocalDateTime localdatetime3 = LocalDateTime.ofInstant(this.field6, ZoneId.systemDefault());
            json1.addProperty("friendsSince", localdatetime3.format(DateUtils.field2));
         }

         if (this.field7 != null) {
            JsonObject json9 = new JsonObject();
            json9.addProperty("hidden", this.field7.getHidden());
            JsonArray array4 = new JsonArray();

            for (UserSocial usersocial6 : this.field7.getLinkedList()) {
               JsonObject json7 = new JsonObject();
               json7.addProperty("platform", usersocial6.getPlatform().name());
               json7.addProperty("username", usersocial6.getUsername());
               json7.addProperty("avatar", usersocial6.getAvatar());
               array4.add(json7);
            }

            json9.add("linked", array4);
            json1.add("socials", json9);
         }

         return json1;
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
   public FriendStatus method16() {
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
   public Badge method27() {
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
   public void method33(Location location1) {
      this.field3 = location1;
   }

   @Generated
   public void method34(Config config1) {
      this.field4 = config1;
   }

   @Generated
   public void method35(long number1) {
      this.field5 = number1;
   }

   @Generated
   public void method36(@Nullable Instant instant1) {
      this.field6 = instant1;
   }

   @Generated
   public void method37(@Nullable FriendSocials friendsocials1) {
      this.field7 = friendsocials1;
   }

   @Generated
   public void method38(Joinability joinability1) {
      this.field9 = joinability1;
   }

   @Generated
   public void method39(boolean flag1) {
      this.field10 = flag1;
   }

   @Generated
   public void setDurationMs(long number1) {
      this.durationMs = number1;
   }

   @Generated
   public void setColor(int number1) {
      this.color = number1;
   }

   @Generated
   public void method41(long number1) {
      this.field11 = number1;
   }

   @Generated
   public void method42(@Nullable String text1) {
      this.field16 = text1;
   }

   @Generated
   public void method43(@Nullable Component component1) {
      this.field17 = component1;
   }

   @Generated
   public void method44(@Nullable RadioInfo radioinfo1) {
      this.field18 = radioinfo1;
   }

   @Generated
   public void method45(int number1) {
      this.field19 = number1;
   }

   @Generated
   public void method46(int number1) {
      this.field20 = number1;
   }

   @Generated
   public void method47(@Nullable Badge gui2handler21) {
      this.field21 = gui2handler21;
   }

   @Generated
   public void setRank(@Nullable String text1) {
      this.rank = text1;
   }

   @Generated
   public void method49(boolean flag1) {
      this.field22 = flag1;
   }

   @Generated
   public void method50(boolean flag1) {
      this.field23 = flag1;
   }

   @Generated
   public void method51(boolean flag1) {
      this.field24 = flag1;
   }

   @Generated
   public void method52(String text1) {
      this.field25 = text1;
   }
}
