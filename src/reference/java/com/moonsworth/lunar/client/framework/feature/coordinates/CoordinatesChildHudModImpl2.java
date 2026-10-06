package com.moonsworth.lunar.client.framework.feature.coordinates;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.hud.coordinates.Coordinates;
import com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry;
import com.moonsworth.lunar.client.util.ThreadModuleDump46;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class CoordinatesChildHudModImpl2 extends CoordinatesHudEntry {
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("cardinalDirection").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("directionAffect").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "directionAffectXColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "directionAffectZColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();

   protected CoordinatesChildHudModImpl2(Coordinates var1, String var2, String var3) {
      super(var1, var2, () -> Coordinates.getCardinalDirection((float)ThreadModuleDump63.method7().bridge$getRotationYaw()), var3);
   }

   protected com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry.Data method2(final Coordinates var1) {
      return new CoordinatesChildHudModImpl2.Data(0.0F, 0.0F) {
         protected ToggleOptionBuilder method1(ToggleOptionBuilder var1x) {
            return (ToggleOptionBuilder)var1x.method17(var1::moveChildrenTogether);
         }

         protected ToggleOptionBuilder method2(ToggleOptionBuilder var1x) {
            return (ToggleOptionBuilder)var1x.method17(var1::moveChildrenTogether);
         }

         protected com.moonsworth.lunar.client.config.option.FloatOption.Data method1(com.moonsworth.lunar.client.config.option.FloatOption.Data var1x) {
            return (com.moonsworth.lunar.client.config.option.FloatOption.Data)var1x.method17(var1::moveChildrenTogether);
         }
      };
   }

   protected ToggleOptionBuilder method1(ToggleOptionBuilder var1) {
      return (ToggleOptionBuilder)var1.method17(() -> !(Boolean)this.field13.get());
   }

   public void method2(RootSettingsAssembler var1) {
      super.registerOptions(var1);
      var1.method2(
         SettingsPage.GENERAL, var1x -> var1x.method9(new ClientOption[]{this.field13, this.field14})
      );
      ((SettingsSectionImpl)var1.method4(
            this.labelColor, new ClientOption[]{this.field15, this.field16}
         ))
         .method2(() -> !(Boolean)this.field14.get());
   }

   public static CoordinatesChildHudModImpl2 method4(Coordinates var0, String var1, String var2) {
      final String var3 = "COORDINATES_" + ThreadModuleDump46.method5(var1) + "_CHILD";
      return new CoordinatesChildHudModImpl2(var0, var1, var2) {
         public String getId() {
            return var3;
         }
      };
   }

   @Generated
   public ToggleOption method17() {
      return this.field13;
   }

   @Generated
   public ToggleOption method19() {
      return this.field14;
   }

   @Generated
   public ColorOption method21() {
      return this.field15;
   }

   @Generated
   public ColorOption method22() {
      return this.field16;
   }

   private class Data extends com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry.Data {
      public Data(float var2, float var3) {
         super(CoordinatesChildHudModImpl2.this, var2, var3);
      }

      public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
         Bridge10_2 var5 = ThreadModuleDump63.method10();
         int var6 = var5.method19();
         boolean var7 = ReducedDebugInfoNotifier.method1() && !var4;
         boolean var8 = !var7 && (Boolean)CoordinatesChildHudModImpl2.this.field13.get();
         boolean var9 = (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get();
         boolean var10 = !var7 && (Boolean)CoordinatesChildHudModImpl2.this.field14.get();
         boolean var11 = var8 && (Boolean)CoordinatesChildHudModImpl2.this.OHHIORIORORORROCRIRICRIOHOIOCC.get();
         boolean var12 = (Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
         boolean var13 = var8 && !var12 && (Boolean)this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII.get();
         float var14 = var12 ? 4.0F : 2.0F;
         if (!var8 && !var10) {
            this.method11(0.0F, 0.0F);
         } else {
            String var15 = var4
               ? CoordinatesChildHudModImpl2.this.CHCOCCOIIRIRHHIHOOIRRIOICCCCCO
               : (String)CoordinatesChildHudModImpl2.this.HCROCIRIOCHHHRCCRCRHHRRORROCOO.get();
            String var16 = CoordinatesChildHudModImpl2.this.tag + ": ";
            float var17 = var2;
            float var18 = var3;
            float var19 = 0.0F;
            if (var10) {
               var19 += var6 * 2 + 2;
            }

            if (var8) {
               var19 += var6;
               if (var10) {
                  var19 += var14;
               }
            }

            float var20 = var8 ? var5.bridge$getStringWidth(var15) : var5.bridge$getStringWidth("+");
            if (var11) {
               var20 += var5.bridge$getStringWidth(var16);
            }

            if (var13) {
               var20 += 9.0F;
            }

            var20 += var14 * 2.0F;
            var19 += var14 * 2.0F;
            var18 += var14;
            var17 += var14;
            this.method11(var20, var19);
            MixinHelper_4 var21 = var1.method2();
            if (var12) {
               this.HROIRCHIHORCHCHCRICOOOIOOHIRCH.method11(var21, var2, var3, this.getWidth(), this.getHeight());
               if ((Boolean)this.OIRIICOHRIHHOCCCIICCRRCICOOCHI.get()) {
                  this.CRROIIOHCOROIIOROHHCHIRRCORCRH
                     .method11(var21, this, var2, var3, this.getWidth(), this.getHeight(), (Float)this.HCHIRRHHICRCCIOOHCOICHHIORICHH.get());
               }
            }

            float var22 = var2 + var20 / 2.0F;
            if (var10) {
               if (var15.contains("W") || var15.contains("E")) {
                  CoordinatesChildHudModImpl2.this.field15.method11(var21, var15.contains("W") ? "-" : "+", var22, var18, var9);
               }

               var18 += var6 + var14;
            }

            if (var8) {
               if (var13) {
                  var17 = this.OOOCCCRICCHOORCCRHHRHHCOOCORRC.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var21, "[", var17, var18, var9);
               }

               if (var11) {
                  var17 = CoordinatesChildHudModImpl2.this.labelColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var21, var16, var17, var18, var9);
               }

               var17 = this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var21, var15, var17, var18, var9);
               if (var13) {
                  this.OOOCCCRICCHOORCCRHHRHHCOOCORRC.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var21, "]", var17, var18, var9);
               }

               var18 += var6 + var14;
            }

            if (var10 && (var15.contains("N") || var15.contains("S"))) {
               CoordinatesChildHudModImpl2.this.field15.method11(var21, var15.contains("N") ? "-" : "+", var22, var18, var9);
            }
         }
      }
   }
}
