package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonClass;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonMapResetEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonFloorDetectedEvent;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventMapUpdate;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public class DungeonMapListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final DungeonFloorListener field7 = (DungeonFloorListener)this.method3(DungeonFloorListener.class);
   private final ScoreboardListener field8 = (ScoreboardListener)this.method3(ScoreboardListener.class);
   private final com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener field9 = (com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener)this.method3(
      com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener.class
   );
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ActionBarStatsListener field10 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ActionBarStatsListener)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ActionBarStatsListener.class
   );
   private static final Pattern field11 = Pattern.compile("([0-9]+)/([0-9]+) Secrets");
   private boolean field12;
   private DungeonStateTracker field13;
   private boolean field14;
   private int field15;
   private int field16;
   private int field17;

   public DungeonMapListener() {
      this.handle(EventTick.class, this::method2);
      this.handle(DungeonFloorDetectedEvent.class, this::method3);
      this.handle(EventMapUpdate.class, this::method4);
      this.handle(TypedChatMessage.class, this::method5);
      this.handle(EventActionBarMessage.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate.class, this::method7);
      this.handle(EventWorldChange.class, this::method11);
   }

   protected boolean isEnabled() {
      return IslandUtils.getIsland() == SkyblockIsland.DUNGEON;
   }

   public Optional<DungeonStateTracker> method5() {
      return Optional.ofNullable(this.field13);
   }

   private void method2(EventTick highlightimpl21) {
      if (this.field13 != null) {
         this.field13.method3();
         this.field17++;
         if (this.field17 >= 5) {
            this.field13.method1();
            this.field17 = 0;
         }
      }
   }

   private void method3(DungeonFloorDetectedEvent rewindhandlers$data101) {
      this.field13 = new DungeonStateTracker(rewindhandlers$data101.method1(), true, this, this.field10, this.field9);
   }

   private void method4(EventMapUpdate highlightimpl61) {
      if (this.field13 != null) {
         this.field13.method2(new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem(highlightimpl61.method1(), highlightimpl61.getId()));
      }
   }

   private void method5(TypedChatMessage data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         String text3 = text2.toLowerCase();
         if (this.field13 != null) {
            if (text2.equals("[BOSS] The Watcher: That will be enough for now.")) {
               this.field13.method32();
            }

            String text4 = text2.trim();
            if (text4.equals("> EXTRA STATS <")) {
               this.field13.method33();
            }

            if (text4.equals("[NPC] Mort: Here, I found this map when I first entered the dungeon.")) {
               this.field13.method37();
            }

            if (text3.contains("blaze done!") || text3.contains("blaze finished!") || text3.contains("blaze puzzle solved!")) {
               this.field13.method44();
            }
         }

         if (!this.field12) {
            if (text2.equals("Starting in 1 second.")) {
               this.field12 = true;
            }
         } else if (this.field13 != null) {
            if (!text2.contains(":")) {
               if (text2.endsWith(" has obtained Wither Key!") || text2.equals("A Wither Key was picked up!")) {
                  this.field13.method40();
               } else if (text2.endsWith(" opened a WITHER door!")) {
                  this.field13.method41();
               } else if (text2.endsWith(" has obtained Blood Key!") || text2.equals("A Blood Key was picked up!")) {
                  this.field13.method40();
               } else if (text2.equals("The BLOOD DOOR has been opened!")) {
                  this.field13.method41();
                  this.field13.method43();
               }
            }
         }
      }
   }

   private void method6(EventActionBarMessage data21) {
      if (this.field13 != null) {
         this.field13.method30();
      }

      String text2 = data21.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
      Matcher matcher3 = field11.matcher(text2);
      if (!matcher3.find()) {
         this.field15 = 0;
         this.field16 = 0;
      } else {
         this.field15 = Integer.parseInt(matcher3.group(1));
         this.field16 = Integer.parseInt(matcher3.group(2));
         this.field14 = false;
      }
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate highlightimpl21) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         for (String text4 : this.field8.method7()) {
            Matcher matcher5 = DungeonPlayerTracker.field1.matcher(text4);
            if (matcher5.matches()) {
               try {
                  this.method8(matcher5);
               } catch (NumberFormatException numberformatexception7) {
                  CrashReporter.method5(numberformatexception7, text4);
               }
            }
         }
      }
   }

   private void method8(Matcher matcher1) {
      String text2 = TextBridge.stripColor(matcher1.group("name"));
      if (!text2.equals(this.field9.method5())) {
         DungeonClass hologramstype2_23 = DungeonClass.fromFirstLetter(matcher1.group("classLetter").charAt(0));
         NamedTextColor namedtextcolor4 = ColorUtils.method37(matcher1.group("healthColor").charAt(0));
         if (namedtextcolor4 != null && hologramstype2_23 != null) {
            String text5 = TextBridge.stripColor(matcher1.group("health"));
            int number6 = 0;
            if (!text5.equals("DEAD")) {
               number6 = NumberUtils.method3(text5.replaceAll("\\D", ""));
            }

            DungeonPlayerTracker holograms4updater7 = this.method9(text2, hologramstype2_23);
            if (holograms4updater7 != null) {
               holograms4updater7.method43(namedtextcolor4);
               holograms4updater7.method41(number6);
            }
         }
      }
   }

   @Nullable
   private DungeonPlayerTracker method9(String text1, DungeonClass hologramstype2_22) {
      DungeonStateTracker holograms2_53 = this.method5().orElse(null);
      if (holograms2_53 == null) {
         return null;
      }

      for (DungeonPlayerTracker holograms4updater5 : holograms2_53.getPlayers()) {
         if (holograms4updater5.method20(false).startsWith(text1) && holograms4updater5.method37() == hologramstype2_22) {
            return holograms4updater5;
         }
      }

      return null;
   }

   public boolean method10() {
      boolean flag1 = !this.field14;
      this.field14 = true;
      return flag1;
   }

   private void method11(EventWorldChange data31) {
      this.clear();
   }

   private void clear() {
      this.field13 = null;
      this.field12 = false;
      this.field15 = 0;
      this.field16 = 0;
      LunarEventBus.method29().method12(DungeonMapResetEvent.class, DungeonMapResetEvent::new);
   }

   protected void onEnable() {
      DungeonFloor highlighttype1 = this.field7.method6();
      if (highlighttype1 != DungeonFloor.NONE) {
         this.method3(new DungeonFloorDetectedEvent(highlighttype1));
      }
   }

   protected void onDisable() {
      this.clear();
   }

   @Generated
   public boolean method12() {
      return this.field12;
   }

   @Generated
   public int method13() {
      return this.field15;
   }

   @Generated
   public int method15() {
      return this.field16;
   }
}
