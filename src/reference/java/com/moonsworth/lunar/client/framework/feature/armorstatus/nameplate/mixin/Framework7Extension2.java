package com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate.mixin;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.ArmorstatusType;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
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

public class Framework7Extension2 extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   protected final EnumOption<Gui2Extension2> field8 = (EnumOption<Gui2Extension2>)OptionFactory.method10(
         "durabilityPosition", Gui2Extension2.RIGHT
      )
      .method31();
   protected final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("border").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final FloatOption field11 = (FloatOption)((Data)((Data)OptionFactory.method2("borderThickness").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   protected final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   protected final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   public final ArmorstatusType field14;

   private Framework7Extension2(Armorstatus var1, ArmorstatusType var2) {
      super(true);
      this.field14 = var2;
      this.method4(Framework.field16, Framework4.method3(var1));
      this.method4(Framework.field17, Framework2.method2(SettingsPage.HUD));
      this.method4(Framework.field1, new Nameplate(this));
   }

   @Override
   public String getId() {
      throw new IllegalStateException("ArmorStatusElementChildMod must be created using ArmorStatusElementChildMod.create()!");
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      ((SettingsSectionImpl)var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1)).method9(), this.field8}
         ))
         .method2(() -> !(Boolean)this.method13().field11.get() || (Boolean)this.method13().field27.get());
      ((SettingsSectionImpl)var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.field9,
            var1x -> {
               var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12});
               var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field10, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11, this.field13})
               );
            }
         ))
         .method2(() -> !(Boolean)this.method13().field11.get() || (Boolean)this.method13().field27.get());
   }

   public Armorstatus method13() {
      return ((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
   }

   @Nullable
   public Nameplate method4(boolean var1) {
      return (com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate.Nameplate)this.method13().method5(var1).get(this.field14);
   }

   public static Framework7Extension2 method4(Armorstatus var0, ArmorstatusType var1) {
      final String var2 = "ARMORSTATUS_" + var1.name() + "_CHILD";
      return new Framework7Extension2(var0, var1) {
         @Override
         public String getId() {
            return var2;
         }
      };
   }
}
