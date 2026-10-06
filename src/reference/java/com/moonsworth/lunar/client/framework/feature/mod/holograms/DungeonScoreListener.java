package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.google.common.collect.ImmutableList;
import com.lunarclient.generated.SkyblockProfileResponse;
import com.lunarclient.generated.skyblockprofileresponse.Profile;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockProfilesUtil;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockElectionListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonFloorDetectedEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonScoreUpdateEvent;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerTick;
import com.moonsworth.lunar.client.config.option.TriState;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class DungeonScoreListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ Opened Rooms: (?<amount>[0-9]+)$");
   private static final Pattern field8 = Pattern.compile("^ Completed Rooms: (?<amount>[0-9]+)$");
   private static final Pattern field9 = Pattern.compile("^ Secrets Found: (?<amount>[0-9.]+)%$");
   private static final Pattern field10 = Pattern.compile("^ Secrets Found: (?<amount>[0-9.]+)$");
   private static final Pattern field11 = Pattern.compile("^ Crypts: (?<amount>[0-9]+)$");
   private static final Pattern field12 = Pattern.compile("^Cleared: (?<percentage>[0-9]+)% \\((?<score>[0-9]+)\\)$");
   private static final Pattern field13 = Pattern.compile("^Time Elapsed: (?<amount>.+)");
   private static final Pattern field14 = Pattern.compile("^Puzzles: \\(\\d\\)$");
   private final ScoreboardListener field15 = (ScoreboardListener)this.method3(ScoreboardListener.class);
   private final com.moonsworth.lunar.client.framework.listener.TabListListener field16 = (com.moonsworth.lunar.client.framework.listener.TabListListener)this.method3(
      com.moonsworth.lunar.client.framework.listener.TabListListener.class
   );
   private final DungeonFloorListener field17 = (DungeonFloorListener)this.method3(DungeonFloorListener.class);
   private final SkyblockElectionListener field18 = (SkyblockElectionListener)this.method3(SkyblockElectionListener.class);
   private final DungeonMapListener field19 = (DungeonMapListener)this.method3(DungeonMapListener.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache field20 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache.class
   );
   private int deaths;
   private boolean field21;
   private boolean field22;
   private boolean field23;
   private boolean field24;
   private boolean field25;
   private boolean field26;
   private boolean field27;
   private HudTimer field28;
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

   public DungeonScoreListener() {
      this.handle(DungeonFloorDetectedEvent.class, this::method1);
      this.handle(EventScoreboardUpdate.class, this::method2);
      this.handle(TypedChatMessage.class, this::method3);
      this.handle(EventEntityRemove.class, this::method5);
      this.handle(EventServerTick.class, this::method11);
   }

   private void method1(DungeonFloorDetectedEvent rewindhandlers$data101) {
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

   private void method2(EventScoreboardUpdate highlightimpl21) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         ImmutableList list2 = this.field15.method6();
         ImmutableList list3 = this.field16.method6();
         DungeonFloor highlighttype4 = this.field17.method6();
         float value5 = 0.0F;
         int number6 = 0;
         int number7 = 0;

         for (String text9 : list2) {
            Matcher matcher10 = field12.matcher(text9);
            if (matcher10.find()) {
               value5 = Integer.parseInt(matcher10.group("percentage")) / 100.0F;
               number7 = Integer.parseInt(matcher10.group("score"));
            }

            matcher10 = field13.matcher(text9);
            if (matcher10.find()) {
               number6 = this.method7(matcher10.group("amount"));
            }
         }

         int number23 = 0;
         int index24 = 0;
         float value26 = 0.0F;
         int number11 = 0;
         this.field33 = 0;
         int index12 = 0;

         for (String text14 : list3) {
            Matcher matcher15 = field7.matcher(text14);
            if (matcher15.find()) {
               number23 = Integer.parseInt(matcher15.group("amount"));
            } else {
               matcher15 = field8.matcher(text14);
               if (matcher15.find()) {
                  index24 = Integer.parseInt(matcher15.group("amount"));
               } else {
                  matcher15 = field9.matcher(text14);
                  if (matcher15.find()) {
                     value26 = Float.parseFloat(matcher15.group("amount")) / 100.0F;
                  } else {
                     matcher15 = field10.matcher(text14);
                     if (matcher15.find()) {
                        number11 = Integer.parseInt(matcher15.group("amount"));
                     } else {
                        matcher15 = field11.matcher(text14);
                        if (matcher15.find()) {
                           this.field33 = Integer.parseInt(matcher15.group("amount"));
                        } else {
                           matcher15 = field14.matcher(text14);
                           if (matcher15.find()) {
                              int index16 = list3.indexOf(text14) + 1;

                              while (index16 < list3.size()) {
                                 String text17 = (String)list3.get(index16);
                                 if (!text17.contains("✦") && !text17.contains("✖")) {
                                    if (!text17.contains("✔")) {
                                       break;
                                    }

                                    index16++;
                                 } else if (text17.contains("Higher Or Lower") && this.field23) {
                                    index16++;
                                 } else {
                                    index16++;
                                    index12++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (number23 != 0 && index24 != 0) {
            int number27 = Math.round(index24 / value5);
            if (!(Boolean)this.field29.get()) {
               index24++;
            }

            if (!this.field27) {
               index24++;
            }

            float value28 = (float)index24 / number27;
            this.field30 = number11;
            this.field31 = Math.round(number11 / value26);
            int number34 = (int)Math.floor(60.0F * value28);
            int number35 = (int)Math.floor(40.0F * (value26 / highlighttype4.getRequiredSecretPercentage()));
            int number36 = Math.min(number34 + number35, 100);
            this.field37 = number36;
            int number18 = 100;
            number18 -= this.method6(highlighttype4.getTimeLimit(), number6);
            this.field36 = number18;
            int index19 = 20 + (int)Math.floor(80.0F * value28) - 10 * index12 - this.deaths * 2;
            index19 = Math.min(index19, 100);
            int index20 = this.deaths * 2;
            if (this.field22 && this.deaths > 0) {
               index19++;
               index20--;
            }

            this.field35 = index19;
            int index21 = Math.min(this.field33, 5);
            if (highlighttype4.getNumber() >= 6 && this.field24) {
               index21 += 2;
            }

            if (this.field25) {
               index21++;
            }

            if (this.field26) {
               index21++;
            }

            this.field38 = index21;
            this.field34 = number36 + index19 + number18 + index21;
            TriState gui2extension222 = (TriState)Ref.method4().method40().method82().method30().get();
            if (gui2extension222 == TriState.DISABLED && this.field18.method2("EZPZ")) {
               this.field34 += 10;
            } else if (gui2extension222 == TriState.FORCE_ON) {
               this.field34 += 10;
            }

            this.field32 = (int)Math.ceil(this.field31 * highlighttype4.getRequiredSecretPercentage() * ((40.0 - index21 + index20) / 40.0));
            if ((Boolean)this.field29.get()) {
               this.field34 = number7;
            }

            LunarEventBus.method29().method12(DungeonScoreUpdateEvent.class, () -> new DungeonScoreUpdateEvent(this.field34));
         }
      }
   }

   private void method3(TypedChatMessage data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         String text2 = TextBridge.getTextContent(data1.OHCICHOROROOORHCRICORHRRCRCCHO()).trim();
         String text3 = text2.toLowerCase();
         if (text2.startsWith(String.valueOf('☠')) && text2.endsWith("and became a ghost.")) {
            this.deaths++;
            String text4 = text2.split(" ")[1];
            if (text4.equals("You")) {
               text4 = Ref.method3().bridge$getPlayer().bridge$getName();
            }

            this.method4(text4);
            if (this.deaths == 1) {
               for (PlayerInfoBridge bridge2_336 : Ref.method3().bridge$getPlayer().bridge$getSendQueue().bridge$getPlayerInfoMap()) {
                  GameProfile gameprofile7 = bridge2_336.bridge$getGameProfile();
                  if (gameprofile7.getName().equals(text4)) {
                     String text8 = gameprofile7.getId().toString().replace("-", "");
                     this.method8(text8);
                  }
               }
            }
         } else if (this.field24 || !text3.contains("mimic dead!") && !text3.contains("mimic killed!")) {
            if (this.field23 || !text3.contains("blaze done!") && !text3.contains("blaze finished!")) {
               if (!this.field27 && text2.equals("[BOSS] The Watcher: You have proven yourself. You may pass.")) {
                  this.field28 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method2().method4().method5(4000L).method7().method2();
               } else if (!this.field25 && text2.equals("A Prince falls. +1 Bonus Score")) {
                  ChatMessageQueue.method1("/pc Prince Dead!");
                  this.field25 = true;
               } else if (this.field25 || !text3.contains("prince dead!") && !text3.contains("prince killed!")) {
                  if (!this.field26 && text2.equals("A Bat has been slain. +1 Bonus Score")) {
                     ChatMessageQueue.method1("/pc Bat Dead!");
                     this.field26 = true;
                  } else if (!this.field26 && (text3.contains("bat dead!") || text3.contains("bat killed!"))) {
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
            DungeonFloor highlighttype9 = this.field17.method6();
            if (!highlighttype9.isBossFloor()) {
               return;
            }

            String text10 = DungeonFloor.getBossMessage(highlighttype9.getNumber());
            if (text2.equals(text10)) {
               this.field29.set(true);
            }
         }
      }
   }

   private void method4(String text1) {
      DungeonStateTracker holograms2_52 = this.field19.method5().orElse(null);
      if (holograms2_52 != null) {
         for (DungeonPlayerTracker holograms4updater4 : holograms2_52.getPlayers()) {
            if (holograms4updater4.method20(true).equals(text1)) {
               holograms4updater4.method26();
               break;
            }
         }
      }
   }

   private void method5(EventEntityRemove highlightimpl121) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         BridgeExtension bridgeextension2 = highlightimpl121.method1();
         Bridge5Extension_5 bridge5extension_53 = Ref.method7();
         if (bridge5extension_53 != null) {
            if (!(bridgeextension2.method13(bridge5extension_53) > 100.0)) {
               Component component4 = bridgeextension2.bridge$getCustomName();
               if (component4 != null) {
                  String text5 = TextBridge.getTextContent(component4);
                  if (text5.contains(" Mimic ")) {
                     ChatMessageQueue.method1("/pc Mimic Dead!");
                     this.field24 = true;
                  }
               }
            }
         }
      }
   }

   private int method6(int number1, int number2) {
      if (number1 >= number2) {
         return 0;
      }

      float value3 = 0.0F;
      float value4 = (float)number2 / number1;
      if (value4 > 1.6) {
         value3 += 18.666666F;
         value3 += (value4 - 1.6F) / 0.07F;
      } else if (value4 > 1.5) {
         value3 += 17.0F;
         value3 += (value4 - 1.5F) / 0.06F;
      } else if (value4 > 1.4) {
         value3 += 15.0F;
         value3 += (value4 - 1.4F) / 0.05F;
      } else if (value4 > 1.2) {
         value3 += 10.0F;
         value3 += (value4 - 1.2F) / 0.04F;
      } else {
         value3 += (value4 - 1.0F) / 0.02F;
      }

      return (int)value3;
   }

   private int method7(String text1) {
      String[] items2 = text1.split(" ");
      int number3 = 0;

      try {
         for (String text7 : items2) {
            int number8 = Integer.parseInt(text7.substring(0, text7.length() - 1));
            if (text7.endsWith("s")) {
               number3 += number8;
            } else if (text7.endsWith("m")) {
               number3 += number8 * 60;
            } else if (text7.endsWith("h")) {
               number3 += number8 * 3600;
            }
         }

         return number3;
      } catch (Exception exception9) {
         System.err.println("Error parsing time string.");
         return -1;
      }
   }

   private void method8(String text1) {
      this.field21 = true;
      BackgroundExecutor.method4(
         () -> {
            SkyblockProfileResponse skyblockprofileresponse2 = SkyBlockProfilesUtil.getProfilesSync(text1);
            if (skyblockprofileresponse2 != null) {
               boolean flag3 = ((Member)((Profile)skyblockprofileresponse2.profiles().find(arg0 -> arg0.selected().orElse(false))).members().get(text1))
                  .petsData()
                  .pets()
                  .stream()
                  .anyMatch(arg0 -> "SPIRIT".equals(arg0.type().orElse("")) && "LEGENDARY".equals(arg0.tier().orElse("")));
               BackgroundExecutor.method11(() -> this.method9(flag3));
            }
         }
      );
   }

   private void method9(boolean flag1) {
      this.field22 = flag1;
      this.field21 = false;
   }

   public boolean method10() {
      return (Boolean)this.field29.get();
   }

   private void method11(EventServerTick highlightimpl91) {
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
