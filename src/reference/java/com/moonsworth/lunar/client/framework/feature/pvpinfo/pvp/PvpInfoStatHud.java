package com.moonsworth.lunar.client.framework.feature.pvpinfo.pvp;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudRowElement;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.ui.hud.HudRowLayout;
import com.moonsworth.lunar.client.ui.hud.HudRow;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.MousePosition;
import java.util.List;

public abstract class PvpInfoStatHud extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   private static final int field8 = 18;
   private final ResourceLocationBridge field9;
   private boolean field10 = true;
   protected final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("minimizeStats").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("displayIcon").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field15 = (ToggleOption)OptionFactory.method7("border").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("autoAlign").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("boldTitle").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTimePeriod").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final FloatOption field19 = (FloatOption)((Data)((Data)OptionFactory.method2("borderThickness").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.5F, 3.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final EnumOption<HudRowAlignment> field20 = (EnumOption<HudRowAlignment>)OptionFactory.method10("alignment", HudRowAlignment.LEFT)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field21 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "headingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field22 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "statColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field23 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "numberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field24 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field25 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public PvpInfoStatHud() {
      super(false);
      this.field9 = this.method28("18x18");
   }

   @Override
   public final void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(this.field11, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12}));
      lightingextension231.method1(
         "displayOptions",
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field13});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field14,
               arg1xx -> arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field15, arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field19})
               )
            );
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16, this.field17, this.field18});
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field20}).method3(this.field16::get);
         }
      );
      this.method3(lightingextension231);
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field21, this.field22, this.field23});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field24}).method3(() -> !(Boolean)this.field14.get());
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field25}).method3(() -> !(Boolean)this.field15.get());
      });
   }

   protected abstract void method3(RootSettingsBuilder lightingextension231);

   protected abstract class PvpInfoStatComponent extends HudRowElement {
      public PvpInfoStatComponent() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      @Override
      public void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4, List<HudRow> list5) {
         float value6 = 6.0F;
         int number7 = 6;

         for (HudRow hitbox29 : list5) {
            number7 += (int)hitbox29.method3();
            float value10 = 6.0F + hitbox29.method2();
            if (value10 > value6) {
               value6 = value10;
            }
         }

         this.method16(value6, number7);
         HudRowAlignment gui2extension11 = HudRowLayout.method3(
            (Boolean)PvpInfoStatHud.this.field16.get(), this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(), (HudRowAlignment)PvpInfoStatHud.this.field20.get()
         );
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data212 = MousePosition.method1().method5();
         float value13 = gui2extension11 == HudRowAlignment.RIGHT ? value6 : 0.0F;
         if (PvpInfoStatHud.this.field10) {
            PvpInfoStatHud.this.field10 = !this.method5(data212, value6);
         } else if (!this.method5(data212, value6)) {
            if ((gui2extension11 == HudRowAlignment.LEFT || gui2extension11 == HudRowAlignment.CENTER)
               && data212.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.CHOIIHHOIHHIIORICIRHOCIOOCHIOI() + this.method15()) {
               PvpInfoStatHud.this.field10 = true;
            } else if (gui2extension11 == HudRowAlignment.RIGHT && data212.HHHCHORHIHRCOHIOICICICHCRRICCI() > this.CHOIIHHOIHHIIORICIRHOCIOOCHIOI() + value13 * this.getScale()
               )
             {
               PvpInfoStatHud.this.field10 = true;
            } else {
               PvpInfoStatHud.this.field10 = !this.method14(data212);
            }
         }

         if (!(Boolean)PvpInfoStatHud.this.field11.get()) {
            PvpInfoStatHud.this.field10 = false;
         }

         if ((Boolean)PvpInfoStatHud.this.field11.get() || (Boolean)PvpInfoStatHud.this.field12.get()) {
            if ((Boolean)PvpInfoStatHud.this.field14.get()) {
               PvpInfoStatHud.this.field24.method11(mixinhelper_41, value2 + value13, value3, 18.0F, 18.0F);
            }

            mixinhelper_41.method24(PvpInfoStatHud.this.field9, (int)(value2 + value13), (int)value3, 18, 18, -1);
         }

         if (!PvpInfoStatHud.this.field10) {
            HudRowLayout.method2(
               mixinhelper_41,
               this,
               value2 + this.method5(),
               value3,
               gui2extension11,
               (Boolean)PvpInfoStatHud.this.field14.get(),
               PvpInfoStatHud.this.field24,
               (Boolean)PvpInfoStatHud.this.field15.get(),
               (Float)PvpInfoStatHud.this.field19.get(),
               PvpInfoStatHud.this.field25,
               list5
            );
         }
      }

      @Override
      public void method16(float value1, float value2) {
         if (!(Boolean)PvpInfoStatHud.this.field11.get() && !(Boolean)PvpInfoStatHud.this.field12.get()) {
            super.method8(value1, value2);
         } else {
            super.method8(value1 + 18.0F, value2);
         }
      }

      private int method5() {
         if (!(Boolean)PvpInfoStatHud.this.field11.get() && !(Boolean)PvpInfoStatHud.this.field12.get()) {
            return 0;
         }

         HudRowAlignment gui2extension1 = HudRowLayout.method3(
            (Boolean)PvpInfoStatHud.this.field16.get(), this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(), (HudRowAlignment)PvpInfoStatHud.this.field20.get()
         );
         return gui2extension1 != HudRowAlignment.LEFT && gui2extension1 != HudRowAlignment.CENTER ? 0 : 18;
      }

      private float method15() {
         return 18.0F * this.getScale();
      }

      private boolean method5(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data21, float value2) {
         HudRowAlignment gui2extension3 = HudRowLayout.method3(
            (Boolean)PvpInfoStatHud.this.field16.get(), this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(), (HudRowAlignment)PvpInfoStatHud.this.field20.get()
         );
         float value4 = gui2extension3 == HudRowAlignment.RIGHT ? value2 : 0.0F;
         return Ref.method11() != null
            && data21.HHHCHORHIHRCOHIOICICICHCRRICCI() > this.CHOIIHHOIHHIIORICIRHOCIOOCHIOI() + value4 * this.getScale()
            && data21.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.CHOIIHHOIHHIIORICIRHOCIOOCHIOI() + this.method15() + value4 * this.getScale()
            && data21.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > this.IRICRICICCOIICCCHRRHCCIRCRHICH()
            && data21.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.IRICRICICCOIICCCHRRHCCIRCRHICH() + this.method15();
      }
   }
}
