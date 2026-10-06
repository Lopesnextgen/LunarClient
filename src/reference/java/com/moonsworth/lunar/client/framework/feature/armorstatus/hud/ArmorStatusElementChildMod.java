package com.moonsworth.lunar.client.framework.feature.armorstatus.hud;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusSlot;
import com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import org.jetbrains.annotations.Nullable;

public class ArmorStatusElementChildMod extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   protected final EnumOption<DurabilityPosition> field8 = (EnumOption<DurabilityPosition>)OptionFactory.method10(
         "durabilityPosition", DurabilityPosition.RIGHT
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("border").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final FloatOption field11 = (FloatOption)((Data)((Data)OptionFactory.method2("borderThickness").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.5F, 3.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   public final ArmorStatusSlot field14;

   private ArmorStatusElementChildMod(Armorstatus armorstatus1, ArmorStatusSlot armorstatustype2) {
      super(true);
      this.field14 = armorstatustype2;
      this.method4(ModTraits.field16, ChildModBinding.method3(armorstatus1));
      this.method4(ModTraits.field17, ModCategories.method2(SettingsPage.HUD));
      this.method4(ModTraits.field1, new ArmorStatusElementHud(this));
   }

   @Override
   public String getId() {
      throw new IllegalStateException("ArmorStatusElementChildMod must be created using ArmorStatusElementChildMod.create()!");
   }

   @Override
   public void method2(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method9(
            new ClientOption[]{((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1)).method9(), this.field8}
         ))
         .method2(() -> !(Boolean)this.method13().field11.get() || (Boolean)this.method13().field27.get());
      ((SettingsSectionImpl)lightingextension231.method7(
            this.field9,
            arg1x -> {
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12});
               arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field10, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11, this.field13})
               );
            }
         ))
         .method2(() -> !(Boolean)this.method13().field11.get() || (Boolean)this.method13().field27.get());
   }

   public Armorstatus method13() {
      return ((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
   }

   @Nullable
   public ArmorStatusElementHud method4(boolean flag1) {
      return (com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusElement)this.method13().method5(flag1).get(this.field14);
   }

   public static ArmorStatusElementChildMod method4(Armorstatus armorstatus0, ArmorStatusSlot armorstatustype1) {
      final String text2 = "ARMORSTATUS_" + armorstatustype1.name() + "_CHILD";
      return new ArmorStatusElementChildMod(armorstatus0, armorstatustype1) {
         @Override
         public String getId() {
            return text2;
         }
      };
   }
}
