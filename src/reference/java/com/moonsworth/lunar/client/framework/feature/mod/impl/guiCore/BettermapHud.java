package com.moonsworth.lunar.client.framework.feature.mod.impl.guiCore;

import com.moonsworth.lunar.bridge.GuiIngameBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapScoreStyle;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonRoomRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonMapOverlay;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsHandler;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapVariant;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapSettings;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.MousePosition;
import lombok.Generated;

public abstract class BettermapHud extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   private final DungeonMapListener field8 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private final DungeonScoreListener field9 = (DungeonScoreListener)this.method63(DungeonScoreListener.class);
   private final EquippedItemListener field10 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final DungeonMapOverlay field11 = new DungeonMapOverlay(new HologramsHandler(this.field8, this.field9));
   private final HudVisibilityWrapper field12;
   private final BettermapSettings field13;
   private DungeonStateTracker field14;

   public BettermapHud(Skyblock skyblock1, BettermapVariant hologramstype2) {
      this(skyblock1, false, hologramstype2);
   }

   public BettermapHud(Skyblock skyblock1, boolean flag2, BettermapVariant hologramstype3) {
      super(flag2);
      this.field13 = new BettermapSettings(hologramstype3, this.field10);
      this.field12 = HudVisibilityWrapper.method4(new BettermapHud.BettermapHudComponent(this.field13));
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, this.field12);
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(EventMarkerInput.class, this::method2);
   }

   @Override
   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      this.field13.method1(lightingextension231);
   }

   private void method2(EventMarkerInput highlightimpl141) {
      if (highlightimpl141.method4() == MouseInputType.CLICK) {
         if (Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            float value2 = this.field12.method2();
            if (this.field13.method28()) {
               value2 += 10.0F;
            }

            this.field11
               .method9(
                  this.field13,
                  this.field12.method1() * this.field12.getScale(),
                  value2 * this.field12.getScale(),
                  this.field12.getScale(),
                  highlightimpl141.method2(),
                  highlightimpl141.method3()
               );
         }
      }
   }

   @Generated
   public BettermapSettings method13() {
      return this.field13;
   }

   private class BettermapHudComponent extends HudElementBase {
      private final BettermapSettings field9;

      public BettermapHudComponent(BettermapSettings holograms_92) {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
         this.field9 = holograms_92;
         this.method5();
         holograms_92.method51(this::method5);
         holograms_92.method52(this::method5);
      }

      public void method5() {
         byte number1 = 100;
         if (this.field9.method28()) {
            number1 += 10;
         }

         if (this.field9.method31() != MapScoreStyle.NONE) {
            number1 += 15;
         }

         this.method58(100.0F, number1);
      }

      @Override
      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         DungeonStateTracker holograms2_55 = BettermapHud.this.field8.method5().orElse(null);
         if (holograms2_55 == null) {
            if (!flag4) {
               return;
            }

            if (BettermapHud.this.field14 == null) {
               BettermapHud.this.field14 = DungeonRoomRegistry.method1(true);
            }

            holograms2_55 = BettermapHud.this.field14;
         }

         if (this.field9.method28()) {
            value3 += 10.0F;
         }

         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data26 = new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2(-9999.0, -9999.0);
         if (!flag4 && Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            data26 = MousePosition.method1().method5();
         }

         float value7 = this.getScale();
         highlightimpl1.method2().push();
         highlightimpl1.method2().scale(1.0F / value7, 1.0F / value7, 1.0F);
         BettermapHud.this.field11.method1(highlightimpl1.method2(), this.field9, holograms2_55, value2 * value7, value3 * value7, value7, data26);
         highlightimpl1.method2().pop();
      }

      @Override
      public boolean method4(boolean flag1) {
         if (flag1) {
            return true;
         } else {
            GuiIngameBridge bridge5extension92 = Ref.method3().bridge$getGuiIngame();
            if (bridge5extension92 == null) {
               return false;
            } else {
               return bridge5extension92.bridge$isTabVisible() && !this.field9.method42() ? false : IslandUtils.getIsland() == SkyblockIsland.DUNGEON;
            }
         }
      }

      @Override
      public boolean method31() {
         return false;
      }
   }
}
