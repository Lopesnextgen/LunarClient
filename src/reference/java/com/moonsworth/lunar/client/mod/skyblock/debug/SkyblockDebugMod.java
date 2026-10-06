package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.framework.mod.UnlockableFeature;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.skyblock.debug.DebugTimerHud;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugHideHud;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugHud;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugHudInteractable;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugHudScrollable;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.List;
import lombok.Generated;

public class SkyblockDebugMod extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("metalDetectorDataGen").method31();
   private final SkyblockMetalDetectorDataGen field9 = new SkyblockMetalDetectorDataGen(this, this.field8);
   private final SkyblockDebugGraphs field10 = new SkyblockDebugGraphs(this);
   private final WaterRoomSolutionGenerator field11 = new WaterRoomSolutionGenerator(this);
   private final DebugTimerHud field12 = new DebugTimerHud(this);
   private final SkyblockChildEnabledDebug field13 = new SkyblockChildEnabledDebug(this);
   private final SkyblockPreviewIsolationDebug field14 = new SkyblockPreviewIsolationDebug(this);
   private final SkyblockSpiritLeapDebug field15 = new SkyblockSpiritLeapDebug(this);
   private final SkyblockRenderDebugLavaWater field16 = new SkyblockRenderDebugLavaWater(this);
   private final SkyblockRenderDebugBlockColors field17 = new SkyblockRenderDebugBlockColors(this);
   private final SkyblockRenderDebugUtilities field18 = new SkyblockRenderDebugUtilities(this);
   private final SkyblockDebugHud field19 = new SkyblockDebugHud(this);
   private final SkyblockDebugHideHud field20 = new SkyblockDebugHideHud(this);
   private final SkyblockDebugRarityBackground field21 = new SkyblockDebugRarityBackground(this);
   private final SkyblockDebugHotbarStackSize field22 = new SkyblockDebugHotbarStackSize(this);
   private final SkyblockDebugDisplayAlert field23 = new SkyblockDebugDisplayAlert(this);
   private final SkyblockDebugRandomLore field24 = new SkyblockDebugRandomLore(this);
   private final SkyblockDebugChime field25 = new SkyblockDebugChime(this);
   private final SkyblockDebugHudScrollable field26 = new SkyblockDebugHudScrollable(this);
   private final SkyblockDebugHudInteractable field27 = new SkyblockDebugHudInteractable(this);

   public SkyblockDebugMod() {
      super(false);
   }

   protected void method1(boolean flag1) {
      this.method2(ModTraits.field6, new UnlockableFeature(ModEnabledState.method6(flag1)));
      this.method2(ModTraits.field9, ModSearchIndex.method7());
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_MOD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   protected List<Framework7Extension> method9() {
      return List.of(
         this.field9,
         this.field10,
         this.field11,
         this.field12,
         this.field13,
         this.field14,
         this.field15,
         this.field16,
         this.field17,
         this.field18,
         this.field19,
         this.field20,
         this.field21,
         this.field22,
         this.field23,
         this.field24,
         this.field25,
         this.field26,
         this.field27
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   @Generated
   public SkyblockRenderDebugLavaWater method13() {
      return this.field16;
   }

   @Generated
   public SkyblockRenderDebugBlockColors method14() {
      return this.field17;
   }

   @Generated
   public SkyblockDebugHideHud method15() {
      return this.field20;
   }

   @Generated
   public SkyblockDebugRarityBackground method16() {
      return this.field21;
   }
}
