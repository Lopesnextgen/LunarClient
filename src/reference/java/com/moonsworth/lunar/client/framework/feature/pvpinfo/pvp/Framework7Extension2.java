package com.moonsworth.lunar.client.framework.feature.pvpinfo.pvp;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudRowElement;
import com.moonsworth.lunar.client.ui.hud.row.Gui2Extension;
import com.moonsworth.lunar.client.ui.hud.row.Hitbox;
import com.moonsworth.lunar.client.ui.hud.row.Hitbox2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump85;
import java.util.List;

public abstract class Framework7Extension2 extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   private static final int field8 = 18;
   private final ResourceLocationBridge field9;
   private boolean field10 = true;
   protected final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("minimizeStats").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("displayIcon").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field15 = (ToggleOption)OptionFactory.method7("border").method31();
   protected final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("autoAlign").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("boldTitle").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTimePeriod").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final FloatOption field19 = (FloatOption)((Data)((Data)OptionFactory.method2("borderThickness").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   protected final EnumOption<Gui2Extension> field20 = (EnumOption<Gui2Extension>)OptionFactory.method10("alignment", Gui2Extension.LEFT)
      .method31();
   protected final ColorOption field21 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "headingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   protected final ColorOption field22 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "statColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final ColorOption field23 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "numberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   protected final ColorOption field24 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   protected final ColorOption field25 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();

   public Framework7Extension2() {
      super(false);
      this.field9 = this.method28("18x18");
   }

   @Override
   public final void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, var1x -> var1x.method9(new ClientOption[]{this.field12}));
      var1.method1(
         "displayOptions",
         var1x -> {
            var1x.method9(new ClientOption[]{this.field13});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field14,
               var1xx -> var1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field15, var1xxx -> var1xxx.method9(new ClientOption[]{this.field19})
               )
            );
            var1x.method9(new ClientOption[]{this.field16, this.field17, this.field18});
            var1x.method9(new ClientOption[]{this.field20}).method3(this.field16::get);
         }
      );
      this.method3(var1);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(SettingsPage.COLOR, var1x -> {
         var1x.method9(new ClientOption[]{this.field21, this.field22, this.field23});
         var1x.method9(new ClientOption[]{this.field24}).method3(() -> !(Boolean)this.field14.get());
         var1x.method9(new ClientOption[]{this.field25}).method3(() -> !(Boolean)this.field15.get());
      });
   }

   protected abstract void method3(RootSettingsAssembler var1);

   protected abstract class Data3 extends HudRowElement {
      public Data3() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      @Override
      public void method2(MixinHelper_4 var1, float var2, float var3, boolean var4, List<Hitbox2> var5) {
         float var6 = 6.0F;
         int var7 = 6;

         for (Hitbox2 var9 : var5) {
            var7 += (int)var9.method3();
            float var10 = 6.0F + var9.method2();
            if (var10 > var6) {
               var6 = var10;
            }
         }

         this.method16(var6, var7);
         Gui2Extension var11 = Hitbox.method3(
            (Boolean)Framework7Extension2.this.field16.get(), this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(), (Gui2Extension)Framework7Extension2.this.field20.get()
         );
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var12 = ThreadModuleDump85.get().method5();
         float var13 = var11 == Gui2Extension.RIGHT ? var6 : 0.0F;
         if (Framework7Extension2.this.field10) {
            Framework7Extension2.this.field10 = !this.method5(var12, var6);
         } else if (!this.method5(var12, var6)) {
            if ((var11 == Gui2Extension.LEFT || var11 == Gui2Extension.CENTER)
               && var12.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.CHOIIHHOIHHIIORICIRHOCIOOCHIOI() + this.method15()) {
               Framework7Extension2.this.field10 = true;
            } else if (var11 == Gui2Extension.RIGHT && var12.HHHCHORHIHRCOHIOICICICHCRRICCI() > this.CHOIIHHOIHHIIORICIRHOCIOOCHIOI() + var13 * this.getScale()
               )
             {
               Framework7Extension2.this.field10 = true;
            } else {
               Framework7Extension2.this.field10 = !this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var12);
            }
         }

         if (!(Boolean)Framework7Extension2.this.field11.get()) {
            Framework7Extension2.this.field10 = false;
         }

         if ((Boolean)Framework7Extension2.this.field11.get() || (Boolean)Framework7Extension2.this.field12.get()) {
            if ((Boolean)Framework7Extension2.this.field14.get()) {
               Framework7Extension2.this.field24.method11(var1, var2 + var13, var3, 18.0F, 18.0F);
            }

            var1.method24(Framework7Extension2.this.field9, (int)(var2 + var13), (int)var3, 18, 18, -1);
         }

         if (!Framework7Extension2.this.field10) {
            Hitbox.method2(
               var1,
               this,
               var2 + this.method5(),
               var3,
               var11,
               (Boolean)Framework7Extension2.this.field14.get(),
               Framework7Extension2.this.field24,
               (Boolean)Framework7Extension2.this.field15.get(),
               (Float)Framework7Extension2.this.field19.get(),
               Framework7Extension2.this.field25,
               var5
            );
         }
      }

      @Override
      public void method16(float var1, float var2) {
         if (!(Boolean)Framework7Extension2.this.field11.get() && !(Boolean)Framework7Extension2.this.field12.get()) {
            super.method8(var1, var2);
         } else {
            super.method8(var1 + 18.0F, var2);
         }
      }

      private int method5() {
         if (!(Boolean)Framework7Extension2.this.field11.get() && !(Boolean)Framework7Extension2.this.field12.get()) {
            return 0;
         }

         Gui2Extension var1 = Hitbox.method3(
            (Boolean)Framework7Extension2.this.field16.get(), this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(), (Gui2Extension)Framework7Extension2.this.field20.get()
         );
         return var1 != Gui2Extension.LEFT && var1 != Gui2Extension.CENTER ? 0 : 18;
      }

      private float method15() {
         return 18.0F * this.getScale();
      }

      private boolean method5(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var1, float var2) {
         Gui2Extension var3 = Hitbox.method3(
            (Boolean)Framework7Extension2.this.field16.get(), this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(), (Gui2Extension)Framework7Extension2.this.field20.get()
         );
         float var4 = var3 == Gui2Extension.RIGHT ? var2 : 0.0F;
         return ThreadModuleDump63.method11() != null
            && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() > this.CHOIIHHOIHHIIORICIRHOCIOOCHIOI() + var4 * this.getScale()
            && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.CHOIIHHOIHHIIORICIRHOCIOOCHIOI() + this.method15() + var4 * this.getScale()
            && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > this.IRICRICICCOIICCCHRRHCCIRCRHICH()
            && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.IRICRICICCOIICCCHRRHCCIRCRHICH() + this.method15();
      }
   }
}
