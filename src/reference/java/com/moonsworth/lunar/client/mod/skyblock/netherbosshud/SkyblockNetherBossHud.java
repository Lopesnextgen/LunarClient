package com.moonsworth.lunar.client.mod.skyblock.netherbosshud;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.Nullable;

public class SkyblockNetherBossHud extends AbstractFeature {
   private static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "skyblock/hud/bladesoul.png");
   private static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "skyblock/hud/mage_outlaw.png");
   private static final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "skyblock/hud/barbarian_duke_x.png");
   private static final ResourceLocationBridge field11 = ResourceLocationBridge.create("lunar", "mobs/blaze.png");
   private static final ResourceLocationBridge field12 = ResourceLocationBridge.create("lunar", "mobs/magma_cube.png");
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showBladesoul").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showMageOutlaw").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showBarbDuke").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showAshfang").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showMagmaBoss").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private long field18 = -1L;
   private long field19 = -1L;
   private long field20 = -1L;
   private long field21 = -1L;
   private long field22 = -1L;
   private String field23 = "Unknown";
   private String field24 = "Unknown";
   private String field25 = "Unknown";
   private String field26 = "Unknown";
   private String field27 = "Unknown";

   public SkyblockNetherBossHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockNetherBossHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method3(new SettingsPage[]{SettingsPage.CRIMSON_ISLE, SettingsPage.TIMERS}));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventWorldChange.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.handle(EventSecond.class, this::method3);
      this.method3(this::onDisable);
   }

   private void onDisable() {
      this.field18 = -1L;
      this.field19 = -1L;
      this.field20 = -1L;
      this.field21 = -1L;
      this.field22 = -1L;
   }

   private void method1(EventWorldChange data31) {
      this.onDisable();
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH().trim();
      switch (text2) {
         case "BLADESOUL DOWN!":
            this.field18 = Ref.method3().bridge$getSystemTime();
            break;
         case "MAGE OUTLAW DOWN!":
            this.field19 = Ref.method3().bridge$getSystemTime();
            break;
         case "BARBARIAN DUKE X DOWN!":
            this.field20 = Ref.method3().bridge$getSystemTime();
            break;
         case "ASHFANG DOWN!":
            this.field21 = Ref.method3().bridge$getSystemTime();
            break;
         case "MAGMA BOSS DOWN!":
            this.field22 = Ref.method3().bridge$getSystemTime();
      }
   }

   private void method3(EventSecond highlightimpl41) {
      this.field23 = this.method4(this.field18);
      this.field24 = this.method4(this.field19);
      this.field25 = this.method4(this.field20);
      this.field26 = this.method4(this.field21);
      this.field27 = this.method4(this.field22);
   }

   private String method4(long number1) {
      if (number1 == -1L) {
         return "Unknown";
      }

      long number3 = number1 + TimeUnit.MINUTES.toMillis(2L);
      long number5 = number3 - Ref.method3().bridge$getSystemTime();
      return number5 < 0L ? "Ready!" : TimeFormatting.method1(number5);
   }

   public String getId() {
      return "SKYBLOCK_NETHER_BOSS_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.field13, this.field14, this.field15, this.field16, this.field17})
      );
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 100, 150, 100, 160, 300);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            return this.method3("1m 12s", "Ready!", "48s", "1m 57s", "23s");
         } else {
            return IslandUtils.getIsland() != SkyblockIsland.CRIMSON_ISLES
               ? null
               : this.method3(
                  SkyblockNetherBossHud.this.field23,
                  SkyblockNetherBossHud.this.field24,
                  SkyblockNetherBossHud.this.field25,
                  SkyblockNetherBossHud.this.field26,
                  SkyblockNetherBossHud.this.field27
               );
         }
      }

      private List<HudLine> method3(String text1, String text2, String text3, String text4, String text5) {
         ArrayList list6 = new ArrayList();
         if ((Boolean)SkyblockNetherBossHud.this.field13.get()) {
            list6.add(
               new HudLine(
                  SkyblockNetherBossHud.field8,
                  TextComponentFactory.builder()
                     .method2("Bladesoul")
                     .method4(text1)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockNetherBossHud.this.field14.get()) {
            list6.add(
               new HudLine(
                  SkyblockNetherBossHud.field9,
                  TextComponentFactory.builder()
                     .method2("Mage Outlaw")
                     .method4(text2)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockNetherBossHud.this.field15.get()) {
            list6.add(
               new HudLine(
                  SkyblockNetherBossHud.field10,
                  TextComponentFactory.builder()
                     .method2("Barbarian Duke")
                     .method4(text3)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockNetherBossHud.this.field16.get()) {
            list6.add(
               new HudLine(
                  SkyblockNetherBossHud.field11,
                  TextComponentFactory.builder()
                     .method2("Ashfang")
                     .method4(text4)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockNetherBossHud.this.field17.get()) {
            list6.add(
               new HudLine(
                  SkyblockNetherBossHud.field12,
                  TextComponentFactory.builder()
                     .method2("Magma Boss")
                     .method4(text5)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         return list6;
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
