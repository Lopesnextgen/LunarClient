package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.google.common.collect.ImmutableList;
import com.lunarclient.generated.SkyblockProfileResponse;
import com.lunarclient.generated.skyblockprofileresponse.Profile;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockProfilesUtil;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler211;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data11;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntityRemoval;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerTickEvent;
import com.moonsworth.lunar.client.config.option.OverrideTriState;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump45;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class GuiRewindhandlersHandler23_2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ Opened Rooms: (?<amount>[0-9]+)$");
   private static final Pattern field8 = Pattern.compile("^ Completed Rooms: (?<amount>[0-9]+)$");
   private static final Pattern field9 = Pattern.compile("^ Secrets Found: (?<amount>[0-9.]+)%$");
   private static final Pattern field10 = Pattern.compile("^ Secrets Found: (?<amount>[0-9.]+)$");
   private static final Pattern field11 = Pattern.compile("^ Crypts: (?<amount>[0-9]+)$");
   private static final Pattern field12 = Pattern.compile("^Cleared: (?<percentage>[0-9]+)% \\((?<score>[0-9]+)\\)$");
   private static final Pattern field13 = Pattern.compile("^Time Elapsed: (?<amount>.+)");
   private static final Pattern field14 = Pattern.compile("^Puzzles: \\(\\d\\)$");
   private final GuiRewindhandlersHandler28 field15 = (GuiRewindhandlersHandler28)this.method3(GuiRewindhandlersHandler28.class);
   private final com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24 field16 = (com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24)this.method3(
      com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler24.class
   );
   private final GuiRewindhandlersHandler22_2 field17 = (GuiRewindhandlersHandler22_2)this.method3(GuiRewindhandlersHandler22_2.class);
   private final GuiRewindhandlersHandler211 field18 = (GuiRewindhandlersHandler211)this.method3(GuiRewindhandlersHandler211.class);
   private final GuiRewindhandlersHandler2_2 field19 = (GuiRewindhandlersHandler2_2)this.method3(GuiRewindhandlersHandler2_2.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22 field20 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler22.class
   );
   private int deaths;
   private boolean field21;
   private boolean field22;
   private boolean field23;
   private boolean field24;
   private boolean field25;
   private boolean field26;
   private boolean field27;
   private ThreadModuleDump45 field28;
   private final TrackedValue<Boolean> field29 = TrackedValue.method1(this, false);
   private int field30;
   private int field31;
   private int field32;
   private int field33;
   private int field34;
   private int field35;
   private int field36;
   private int field37;
   private int field38;

   public GuiRewindhandlersHandler23_2() {
      this.handle(Rewindhandlers$Data10.class, this::method1);
      this.handle(ScoreboardUpdateEvent.class, this::method2);
      this.handle(Data.class, this::method3);
      this.handle(EventEntityRemoval.class, this::method5);
      this.handle(ServerTickEvent.class, this::method11);
   }

   private void method1(Rewindhandlers$Data10 var1) {
      this.deaths = 0;
      this.field22 = false;
      this.field21 = false;
      this.field24 = false;
      this.field25 = false;
      this.field26 = false;
      this.field27 = false;
      this.field28 = null;
      this.field29.set(false);
      this.field23 = false;
      this.field33 = 0;
      this.field34 = 0;
      this.field35 = 0;
      this.field38 = 0;
      this.field37 = 0;
      this.field36 = 0;
      this.field30 = 0;
      this.field31 = 0;
      this.field32 = 0;
   }

   private void method2(ScoreboardUpdateEvent var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         ImmutableList var2 = this.field15.method6();
         ImmutableList var3 = this.field16.method6();
         HighlightType var4 = this.field17.method6();
         float var5 = 0.0F;
         int var6 = 0;
         int var7 = 0;

         for (String var9 : var2) {
            Matcher var10 = field12.matcher(var9);
            if (var10.find()) {
               var5 = Integer.parseInt(var10.group("percentage")) / 100.0F;
               var7 = Integer.parseInt(var10.group("score"));
            }

            var10 = field13.matcher(var9);
            if (var10.find()) {
               var6 = this.method7(var10.group("amount"));
            }
         }

         int var23 = 0;
         int var24 = 0;
         float var26 = 0.0F;
         int var11 = 0;
         this.field33 = 0;
         int var12 = 0;

         for (String var14 : var3) {
            Matcher var15 = field7.matcher(var14);
            if (var15.find()) {
               var23 = Integer.parseInt(var15.group("amount"));
            } else {
               var15 = field8.matcher(var14);
               if (var15.find()) {
                  var24 = Integer.parseInt(var15.group("amount"));
               } else {
                  var15 = field9.matcher(var14);
                  if (var15.find()) {
                     var26 = Float.parseFloat(var15.group("amount")) / 100.0F;
                  } else {
                     var15 = field10.matcher(var14);
                     if (var15.find()) {
                        var11 = Integer.parseInt(var15.group("amount"));
                     } else {
                        var15 = field11.matcher(var14);
                        if (var15.find()) {
                           this.field33 = Integer.parseInt(var15.group("amount"));
                        } else {
                           var15 = field14.matcher(var14);
                           if (var15.find()) {
                              int var16 = var3.indexOf(var14) + 1;

                              while (var16 < var3.size()) {
                                 String var17 = (String)var3.get(var16);
                                 if (!var17.contains("✦") && !var17.contains("✖")) {
                                    if (!var17.contains("✔")) {
                                       break;
                                    }

                                    var16++;
                                 } else if (var17.contains("Higher Or Lower") && this.field23) {
                                    var16++;
                                 } else {
                                    var16++;
                                    var12++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var23 != 0 && var24 != 0) {
            int var27 = Math.round(var24 / var5);
            if (!(Boolean)this.field29.get()) {
               var24++;
            }

            if (!this.field27) {
               var24++;
            }

            float var28 = (float)var24 / var27;
            this.field30 = var11;
            this.field31 = Math.round(var11 / var26);
            int var34 = (int)Math.floor(60.0F * var28);
            int var35 = (int)Math.floor(40.0F * (var26 / var4.getRequiredSecretPercentage()));
            int var36 = Math.min(var34 + var35, 100);
            this.field37 = var36;
            int var18 = 100;
            var18 -= this.method6(var4.getTimeLimit(), var6);
            this.field36 = var18;
            int var19 = 20 + (int)Math.floor(80.0F * var28) - 10 * var12 - this.deaths * 2;
            var19 = Math.min(var19, 100);
            int var20 = this.deaths * 2;
            if (this.field22 && this.deaths > 0) {
               var19++;
               var20--;
            }

            this.field35 = var19;
            int var21 = Math.min(this.field33, 5);
            if (var4.getNumber() >= 6 && this.field24) {
               var21 += 2;
            }

            if (this.field25) {
               var21++;
            }

            if (this.field26) {
               var21++;
            }

            this.field38 = var21;
            this.field34 = var36 + var19 + var18 + var21;
            OverrideTriState var22 = (OverrideTriState)ThreadModuleDump63.method4().method40().method82().method30().get();
            if (var22 == OverrideTriState.DISABLED && this.field18.method2("EZPZ")) {
               this.field34 += 10;
            } else if (var22 == OverrideTriState.FORCE_ON) {
               this.field34 += 10;
            }

            this.field32 = (int)Math.ceil(this.field31 * var4.getRequiredSecretPercentage() * ((40.0 - var21 + var20) / 40.0));
            if ((Boolean)this.field29.get()) {
               this.field34 = var7;
            }

            ClientEventBus.method29().method12(Rewindhandlers$Data11.class, () -> new Rewindhandlers$Data11(this.field34));
         }
      }
   }

   private void method3(Data var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         String var2 = AdventureTextBridge.getTextContent(var1.OHCICHOROROOORHCRICORHRRCRCCHO()).trim();
         String var3 = var2.toLowerCase();
         if (var2.startsWith(String.valueOf('☠')) && var2.endsWith("and became a ghost.")) {
            this.deaths++;
            String var4 = var2.split(" ")[1];
            if (var4.equals("You")) {
               var4 = ThreadModuleDump63.method3().bridge$getPlayer().bridge$getName();
            }

            this.method4(var4);
            if (this.deaths == 1) {
               for (Bridge2_33 var6 : ThreadModuleDump63.method3().bridge$getPlayer().bridge$getSendQueue().bridge$getPlayerInfoMap()) {
                  GameProfile var7 = var6.bridge$getGameProfile();
                  if (var7.getName().equals(var4)) {
                     String var8 = var7.getId().toString().replace("-", "");
                     this.method8(var8);
                  }
               }
            }
         } else if (this.field24 || !var3.contains("mimic dead!") && !var3.contains("mimic killed!")) {
            if (this.field23 || !var3.contains("blaze done!") && !var3.contains("blaze finished!")) {
               if (!this.field27 && var2.equals("[BOSS] The Watcher: You have proven yourself. You may pass.")) {
                  this.field28 = com.moonsworth.lunar.client.util.ThreadModuleDump45.Data.method1().method2().method4().method5(4000L).method7().method2();
               } else if (!this.field25 && var2.equals("A Prince falls. +1 Bonus Score")) {
                  Fishing_3.method1("/pc Prince Dead!");
                  this.field25 = true;
               } else if (this.field25 || !var3.contains("prince dead!") && !var3.contains("prince killed!")) {
                  if (!this.field26 && var2.equals("A Bat has been slain. +1 Bonus Score")) {
                     Fishing_3.method1("/pc Bat Dead!");
                     this.field26 = true;
                  } else if (!this.field26 && (var3.contains("bat dead!") || var3.contains("bat killed!"))) {
                     this.field26 = true;
                  }
               } else {
                  this.field25 = true;
               }
            } else {
               this.field23 = true;
            }
         } else {
            this.field24 = true;
         }

         if (!(Boolean)this.field29.get()) {
            HighlightType var9 = this.field17.method6();
            if (!var9.isBossFloor()) {
               return;
            }

            String var10 = HighlightType.getBossMessage(var9.getNumber());
            if (var2.equals(var10)) {
               this.field29.set(true);
            }
         }
      }
   }

   private void method4(String var1) {
      Holograms2_5 var2 = this.field19.method5().orElse(null);
      if (var2 != null) {
         for (Holograms4Updater var4 : var2.getPlayers()) {
            if (var4.method20(true).equals(var1)) {
               var4.method26();
               break;
            }
         }
      }
   }

   private void method5(EventEntityRemoval var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         BridgeExtension var2 = var1.method1();
         Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
         if (var3 != null) {
            if (!(var2.method13(var3) > 100.0)) {
               Component var4 = var2.bridge$getCustomName();
               if (var4 != null) {
                  String var5 = AdventureTextBridge.getTextContent(var4);
                  if (var5.contains(" Mimic ")) {
                     Fishing_3.method1("/pc Mimic Dead!");
                     this.field24 = true;
                  }
               }
            }
         }
      }
   }

   private int method6(int var1, int var2) {
      if (var1 >= var2) {
         return 0;
      }

      float var3 = 0.0F;
      float var4 = (float)var2 / var1;
      if (var4 > 1.6) {
         var3 += 18.666666F;
         var3 += (var4 - 1.6F) / 0.07F;
      } else if (var4 > 1.5) {
         var3 += 17.0F;
         var3 += (var4 - 1.5F) / 0.06F;
      } else if (var4 > 1.4) {
         var3 += 15.0F;
         var3 += (var4 - 1.4F) / 0.05F;
      } else if (var4 > 1.2) {
         var3 += 10.0F;
         var3 += (var4 - 1.2F) / 0.04F;
      } else {
         var3 += (var4 - 1.0F) / 0.02F;
      }

      return (int)var3;
   }

   private int method7(String var1) {
      String[] var2 = var1.split(" ");
      int var3 = 0;

      try {
         for (String var7 : var2) {
            int var8 = Integer.parseInt(var7.substring(0, var7.length() - 1));
            if (var7.endsWith("s")) {
               var3 += var8;
            } else if (var7.endsWith("m")) {
               var3 += var8 * 60;
            } else if (var7.endsWith("h")) {
               var3 += var8 * 3600;
            }
         }

         return var3;
      } catch (Exception var9) {
         System.err.println("Error parsing time string.");
         return -1;
      }
   }

   private void method8(String var1) {
      this.field21 = true;
      ThreadModuleDump37.method4(
         () -> {
            SkyblockProfileResponse var2 = SkyBlockProfilesUtil.getProfilesSync(var1);
            if (var2 != null) {
               boolean var3 = ((Member)((Profile)var2.profiles().find(var0 -> var0.selected().orElse(false))).members().get(var1))
                  .petsData()
                  .pets()
                  .stream()
                  .anyMatch(var0 -> "SPIRIT".equals(var0.type().orElse("")) && "LEGENDARY".equals(var0.tier().orElse("")));
               ThreadModuleDump37.method11(() -> this.method9(var3));
            }
         }
      );
   }

   private void method9(boolean var1) {
      this.field22 = var1;
      this.field21 = false;
   }

   public boolean method10() {
      return (Boolean)this.field29.get();
   }

   private void method11(ServerTickEvent var1) {
      if (this.field28 != null && this.field28.get() <= 0L) {
         this.field27 = true;
         this.field28.destroy();
         this.field28 = null;
      }
   }

   @Generated
   public int getDeaths() {
      return this.deaths;
   }

   @Generated
   public boolean method13() {
      return this.field21;
   }

   @Generated
   public boolean method15() {
      return this.field22;
   }

   @Generated
   public boolean method16() {
      return this.field23;
   }

   @Generated
   public boolean method17() {
      return this.field24;
   }

   @Generated
   public boolean method18() {
      return this.field25;
   }

   @Generated
   public boolean method21() {
      return this.field26;
   }

   @Generated
   public boolean method22() {
      return this.field27;
   }

   @Generated
   public int method23() {
      return this.field30;
   }

   @Generated
   public int method24() {
      return this.field31;
   }

   @Generated
   public int method25() {
      return this.field32;
   }

   @Generated
   public int method26() {
      return this.field33;
   }

   @Generated
   public int method27() {
      return this.field34;
   }

   @Generated
   public int method28() {
      return this.field35;
   }

   @Generated
   public int method29() {
      return this.field36;
   }

   @Generated
   public int method30() {
      return this.field37;
   }

   @Generated
   public int method31() {
      return this.field38;
   }
}
