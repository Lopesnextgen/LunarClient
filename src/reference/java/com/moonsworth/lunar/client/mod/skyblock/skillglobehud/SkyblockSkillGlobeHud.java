package com.moonsworth.lunar.client.mod.skyblock.skillglobehud;

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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.SkillXpSource;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkillXpListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkillXpUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;

public class SkyblockSkillGlobeHud extends AbstractFeature {
   private final SkillXpListener skillXpListener = (SkillXpListener)this.method63(SkillXpListener.class);
   private final IntegerOption displayTime = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "displayTime"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 10))
      .method31();
   private final ColorOption skillGlobeCircleColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skillGlobeCircleColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8355712))
      .method31();
   private final ColorOption skillGlobeBorderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skillGlobeBorderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
      .method31();
   private final ColorOption skillGlobeProgressColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skillGlobeProgressColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711933))
      .method31();
   private double skillProgress;
   private long lastUpdateTime;

   public SkyblockSkillGlobeHud(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockSkillGlobeHud.Data()));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.SKILLS));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(SkillXpUpdateEvent.class, this::method1);
   }

   private void method1(SkillXpUpdateEvent highlightimpl41) {
      if (highlightimpl41.method2() == SkillXpSource.ACTION_BAR) {
         this.skillProgress = highlightimpl41.method6() / 100.0;
         this.lastUpdateTime = Ref.method3().bridge$getSystemTime();
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.displayTime}));
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.skillGlobeCircleColor, this.skillGlobeBorderColor, this.skillGlobeProgressColor})
      );
   }

   public String getId() {
      return "SKYBLOCK_SKILL_GLOBE_HUD";
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
         this.method3(50.0F, 50.0F);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (flag4) {
            this.method2(highlightimpl1.method2(), value2, value3, CoordinatesType.MINING, 0.6666666666666666);
         } else {
            CoordinatesType coordinatestype5 = SkyblockSkillGlobeHud.this.skillXpListener.method16();
            if (coordinatestype5 != null) {
               if (SkyblockSkillGlobeHud.this.lastUpdateTime + (Integer)SkyblockSkillGlobeHud.this.displayTime.get() * 1000
                  > Ref.method3().bridge$getSystemTime()) {
                  this.method2(highlightimpl1.method2(), value2, value3, coordinatestype5, SkyblockSkillGlobeHud.this.skillProgress);
               }
            }
         }
      }

      private void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, CoordinatesType coordinatestype4, double value5) {
         mixinhelper_41.push();
         mixinhelper_41.method39(value2, value3);
         mixinhelper_41.method39(this.getWidth() / 2.0F, this.getHeight() / 2.0F);
         LcuiScreen.method78(mixinhelper_41, 0.0, 0.0, 22.0, SkyblockSkillGlobeHud.this.skillGlobeBorderColor.method14(0.0F));
         LcuiScreen.method79(mixinhelper_41, 0.0, 0.0, 22.0, value5, SkyblockSkillGlobeHud.this.skillGlobeProgressColor.method14(0.0F));
         LcuiScreen.method78(mixinhelper_41, 0.0, 0.0, 20.0, SkyblockSkillGlobeHud.this.skillGlobeCircleColor.method14(0.0F));
         mixinhelper_41.scale(1.5F, 1.5F, 0.0F);
         mixinhelper_41.method38(-8.0F, -8.0F, 0.0F);
         mixinhelper_41.method34(coordinatestype4.getIcon(), 0, 0, Ref.method3());
         mixinhelper_41.pop();
      }

      public boolean method4(boolean flag1) {
         return true;
      }
   }
}
