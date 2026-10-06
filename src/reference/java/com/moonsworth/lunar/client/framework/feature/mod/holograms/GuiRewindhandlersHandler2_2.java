package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventMapUpdateLegacy;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public class GuiRewindhandlersHandler2_2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler22_2 field7 = (GuiRewindhandlersHandler22_2)this.method3(GuiRewindhandlersHandler22_2.class);
   private final GuiRewindhandlersHandler28 field8 = (GuiRewindhandlersHandler28)this.method3(GuiRewindhandlersHandler28.class);
   private final com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25 field9 = (com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25)this.method3(
      com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25.class
   );
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler24 field10 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler24)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler24.class
   );
   private static final Pattern field11 = Pattern.compile("([0-9]+)/([0-9]+) Secrets");
   private boolean field12;
   private Holograms2_5 field13;
   private boolean field14;
   private int field15;
   private int field16;
   private int field17;

   public GuiRewindhandlersHandler2_2() {
      this.handle(EventClientTick.class, this::method2);
      this.handle(Rewindhandlers$Data10.class, this::method3);
      this.handle(EventMapUpdateLegacy.class, this::method4);
      this.handle(Data.class, this::method5);
      this.handle(Data2.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent.class, this::method7);
      this.handle(EventWorldChanged.class, this::method11);
   }

   protected boolean isEnabled() {
      return Click3.getIsland() == Gui2Extension3.DUNGEON;
   }

   public Optional<Holograms2_5> method5() {
      return Optional.ofNullable(this.field13);
   }

   private void method2(EventClientTick var1) {
      if (this.field13 != null) {
         this.field13.method3();
         this.field17++;
         if (this.field17 >= 5) {
            this.field13.method1();
            this.field17 = 0;
         }
      }
   }

   private void method3(Rewindhandlers$Data10 var1) {
      this.field13 = new Holograms2_5(var1.method1(), true, this, this.field10, this.field9);
   }

   private void method4(EventMapUpdateLegacy var1) {
      if (this.field13 != null) {
         this.field13.method2(new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2(var1.method1(), var1.getId()));
      }
   }

   private void method5(Data var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         String var2 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         String var3 = var2.toLowerCase();
         if (this.field13 != null) {
            if (var2.equals("[BOSS] The Watcher: That will be enough for now.")) {
               this.field13.method32();
            }

            String var4 = var2.trim();
            if (var4.equals("> EXTRA STATS <")) {
               this.field13.method33();
            }

            if (var4.equals("[NPC] Mort: Here, I found this map when I first entered the dungeon.")) {
               this.field13.method37();
            }

            if (var3.contains("blaze done!") || var3.contains("blaze finished!") || var3.contains("blaze puzzle solved!")) {
               this.field13.method44();
            }
         }

         if (!this.field12) {
            if (var2.equals("Starting in 1 second.")) {
               this.field12 = true;
            }
         } else if (this.field13 != null) {
            if (!var2.contains(":")) {
               if (var2.endsWith(" has obtained Wither Key!") || var2.equals("A Wither Key was picked up!")) {
                  this.field13.method40();
               } else if (var2.endsWith(" opened a WITHER door!")) {
                  this.field13.method41();
               } else if (var2.endsWith(" has obtained Blood Key!") || var2.equals("A Blood Key was picked up!")) {
                  this.field13.method40();
               } else if (var2.equals("The BLOOD DOOR has been opened!")) {
                  this.field13.method41();
                  this.field13.method43();
               }
            }
         }
      }
   }

   private void method6(Data2 var1) {
      if (this.field13 != null) {
         this.field13.method30();
      }

      String var2 = var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
      Matcher var3 = field11.matcher(var2);
      if (!var3.find()) {
         this.field15 = 0;
         this.field16 = 0;
      } else {
         this.field15 = Integer.parseInt(var3.group(1));
         this.field16 = Integer.parseInt(var3.group(2));
         this.field14 = false;
      }
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         for (String var4 : this.field8.method7()) {
            Matcher var5 = Holograms4Updater.field1.matcher(var4);
            if (var5.matches()) {
               try {
                  this.method8(var5);
               } catch (NumberFormatException var7) {
                  Inventorymod2.method5(var7, var4);
               }
            }
         }
      }
   }

   private void method8(Matcher var1) {
      String var2 = AdventureTextBridge.stripColor(var1.group("name"));
      if (!var2.equals(this.field9.method5())) {
         HologramsType2_2 var3 = HologramsType2_2.fromFirstLetter(var1.group("classLetter").charAt(0));
         NamedTextColor var4 = ThreadModuleDump23.method37(var1.group("healthColor").charAt(0));
         if (var4 != null && var3 != null) {
            String var5 = AdventureTextBridge.stripColor(var1.group("health"));
            int var6 = 0;
            if (!var5.equals("DEAD")) {
               var6 = ThreadModuleDump40.method3(var5.replaceAll("\\D", ""));
            }

            Holograms4Updater var7 = this.method9(var2, var3);
            if (var7 != null) {
               var7.method43(var4);
               var7.method41(var6);
            }
         }
      }
   }

   @Nullable
   private Holograms4Updater method9(String var1, HologramsType2_2 var2) {
      Holograms2_5 var3 = this.method5().orElse(null);
      if (var3 == null) {
         return null;
      }

      for (Holograms4Updater var5 : var3.getPlayers()) {
         if (var5.method20(false).startsWith(var1) && var5.method37() == var2) {
            return var5;
         }
      }

      return null;
   }

   public boolean method10() {
      boolean var1 = !this.field14;
      this.field14 = true;
      return var1;
   }

   private void method11(EventWorldChanged var1) {
      this.clear();
   }

   private void clear() {
      this.field13 = null;
      this.field12 = false;
      this.field15 = 0;
      this.field16 = 0;
      ClientEventBus.method29().method12(HighlightBase$Data3.class, HighlightBase$Data3::new);
   }

   protected void onEnable() {
      HighlightType var1 = this.field7.method6();
      if (var1 != HighlightType.NONE) {
         this.method3(new Rewindhandlers$Data10(var1));
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
