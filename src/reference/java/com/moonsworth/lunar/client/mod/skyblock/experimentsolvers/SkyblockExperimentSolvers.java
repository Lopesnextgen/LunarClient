package com.moonsworth.lunar.client.mod.skyblock.experimentsolvers;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.List;
import lombok.Generated;

public class SkyblockExperimentSolvers extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("blockIncorrectClicks").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field9 = (ModifierKeybindOption)OptionFactory.method18("blockClicksOverride")
      .method5(KeyCode.KEY_LCONTROL)
      .method31();
   private final ColorOption field10 = (ColorOption)((Data)OptionFactory.method8("superpairsUncoveredColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1431655936))
      .method31();
   private final ColorOption field11 = (ColorOption)((Data)OptionFactory.method8("superpairsUnclaimedPairColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1431699286))
      .method31();
   private final ColorOption field12 = (ColorOption)((Data)OptionFactory.method8("superpairsClaimedColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1442797056))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("chronomatron").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("superpairs").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("ultrasequencer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final SkyblockChronomatron field16 = new SkyblockChronomatron(this, this.field13);
   private final SkyblockSuperpairs field17 = new SkyblockSuperpairs(this, this.field14);
   private final SkyblockUltrasequencer field18 = new SkyblockUltrasequencer(this, this.field15);

   public SkyblockExperimentSolvers(Framework7Extension framework7extension1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(framework7extension1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
   }

   public List<Framework7Extension> method9() {
      return List.of(this.field16, this.field17, this.field18);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_EXPERIMENT_SOLVERS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field9, this.field13, this.field14, this.field15});
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10, this.field11, this.field12})
      );
   }

   @Generated
   public ToggleOption method13() {
      return this.field8;
   }

   @Generated
   public ModifierKeybindOption method14() {
      return this.field9;
   }

   @Generated
   public ColorOption method15() {
      return this.field10;
   }

   @Generated
   public ColorOption method16() {
      return this.field11;
   }

   @Generated
   public ColorOption method17() {
      return this.field12;
   }

   @Generated
   public ToggleOption method19() {
      return this.field13;
   }

   @Generated
   public ToggleOption method21() {
      return this.field14;
   }

   @Generated
   public ToggleOption method22() {
      return this.field15;
   }

   @Generated
   public SkyblockChronomatron method23() {
      return this.field16;
   }

   @Generated
   public SkyblockSuperpairs method24() {
      return this.field17;
   }

   @Generated
   public SkyblockUltrasequencer method25() {
      return this.field18;
   }
}
