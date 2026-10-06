package com.moonsworth.lunar.client.mod.skyblock.skillprogressbarhud;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkillXpListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkillXpUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSkillProgressBarHud extends AbstractFeature {
   private static final float BAR_WIDTH = 100.0F;
   private static final float BAR_HEIGHT = 8.0F;
   private static final float BAR_BORDER_OFFSET = 1.5F;
   private static final float INNER_BAR_WIDTH = 97.0F;
   private static final float INNER_BAR_HEIGHT = 5.0F;
   private final SkillXpListener skillXpListener = (SkillXpListener)this.method63(SkillXpListener.class);
   private final ColorOption skillBarColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skillBarColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141121))
      .method16()
      .method31();
   private double skillProgress;

   public SkyblockSkillProgressBarHud(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockSkillProgressBarHud.Data()));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.SKILLS));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(SkillXpUpdateEvent.class, this::method1);
   }

   private void method1(SkillXpUpdateEvent highlightimpl41) {
      this.skillProgress = highlightimpl41.method6() / 100.0;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.skillBarColor}));
   }

   public String getId() {
      return "SKYBLOCK_SKILL_PROGRESS_BAR_HUD";
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.BOTTOM_LEFT);
         this.method58(100.0F, 8.0F);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (flag4) {
            this.method2(highlightimpl1.method2(), value2, value3, 0.6666667F);
         } else {
            CoordinatesType coordinatestype5 = SkyblockSkillProgressBarHud.this.skillXpListener.method16();
            if (coordinatestype5 != null) {
               this.method2(highlightimpl1.method2(), value2, value3, (float)SkyblockSkillProgressBarHud.this.skillProgress);
            }
         }
      }

      private void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4) {
         LcuiScreen.method117(mixinhelper_41, value2, value3, 100.0F, 8.0F, 7.0F, 267386880);
         float value5 = value2 + 1.5F;
         float value6 = value3 + 1.5F;
         LcuiScreen.method117(mixinhelper_41, value5, value6, 97.0F, 5.0F, 5.0F, -13421773);
         LcuiScreen.method117(mixinhelper_41, value5, value6, 97.0F * value4, 5.0F, 5.0F, SkyblockSkillProgressBarHud.this.skillBarColor.method14(0.0F));
      }

      public boolean method4(boolean flag1) {
         return true;
      }
   }
}
