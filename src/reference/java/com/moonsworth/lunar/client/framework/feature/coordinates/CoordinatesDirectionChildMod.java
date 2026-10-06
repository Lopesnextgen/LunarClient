package com.moonsworth.lunar.client.framework.feature.coordinates;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHud;
import com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry;
import com.moonsworth.lunar.client.util.text.TextUtils;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class CoordinatesDirectionChildMod extends CoordinatesHudEntry {
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("cardinalDirection").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("directionAffect").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "directionAffectXColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "directionAffectZColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   protected CoordinatesDirectionChildMod(CoordinatesHud coordinates1, String text2, String text3) {
      super(coordinates1, text2, () -> CoordinatesHud.getCardinalDirection((float)Ref.method7().bridge$getRotationYaw()), text3);
   }

   protected com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry.Data method2(final CoordinatesHud coordinates1) {
      return new CoordinatesDirectionChildMod.Data(0.0F, 0.0F) {
         protected ToggleOptionBuilder method1(ToggleOptionBuilder data21x) {
            return (ToggleOptionBuilder)data21x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }

         protected ToggleOptionBuilder method2(ToggleOptionBuilder data21x) {
            return (ToggleOptionBuilder)data21x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }

         protected com.moonsworth.lunar.client.config.option.FloatOption.Data method1(com.moonsworth.lunar.client.config.option.FloatOption.Data data1x) {
            return (com.moonsworth.lunar.client.config.option.FloatOption.Data)data1x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }
      };
   }

   protected ToggleOptionBuilder method1(ToggleOptionBuilder data21) {
      return (ToggleOptionBuilder)data21.ORICHRORRORHORHOIHCRHOORCRRHOI(() -> !(Boolean)this.field13.get());
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.registerOptions(lightingextension231);
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field13, this.field14})
      );
      ((SettingsSectionImpl)lightingextension231.method5(
            this.labelColor, new ClientOption[]{this.field15, this.field16}
         ))
         .method2(() -> !(Boolean)this.field14.get());
   }

   public static CoordinatesDirectionChildMod method4(CoordinatesHud coordinates0, String text1, String text2) {
      final String text3 = "COORDINATES_" + TextUtils.toUpperSnakeCase(text1) + "_CHILD";
      return new CoordinatesDirectionChildMod(coordinates0, text1, text2) {
         public String getId() {
            return text3;
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
      public Data(float value2, float value3) {
         super(CoordinatesDirectionChildMod.this, value2, value3);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         Bridge10_2 bridge10_25 = Ref.method10();
         int number6 = bridge10_25.method19();
         boolean flag7 = ReducedDebugInfoNotifier.method1() && !flag4;
         boolean flag8 = !flag7 && (Boolean)CoordinatesDirectionChildMod.this.field13.get();
         boolean flag9 = (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get();
         boolean flag10 = !flag7 && (Boolean)CoordinatesDirectionChildMod.this.field14.get();
         boolean flag11 = flag8 && (Boolean)CoordinatesDirectionChildMod.this.OHHIORIORORORROCRIRICRIOHOIOCC.get();
         boolean flag12 = (Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
         boolean flag13 = flag8 && !flag12 && (Boolean)this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII.get();
         float value14 = flag12 ? 4.0F : 2.0F;
         if (!flag8 && !flag10) {
            this.method11(0.0F, 0.0F);
         } else {
            String text15 = flag4
               ? CoordinatesDirectionChildMod.this.CHCOCCOIIRIRHHIHOOIRRIOICCCCCO
               : (String)CoordinatesDirectionChildMod.this.HCROCIRIOCHHHRCCRCRHHRRORROCOO.get();
            String text16 = CoordinatesDirectionChildMod.this.tag + ": ";
            float value17 = value2;
            float value18 = value3;
            float value19 = 0.0F;
            if (flag10) {
               value19 += number6 * 2 + 2;
            }

            if (flag8) {
               value19 += number6;
               if (flag10) {
                  value19 += value14;
               }
            }

            float value20 = flag8 ? bridge10_25.bridge$getStringWidth(text15) : bridge10_25.bridge$getStringWidth("+");
            if (flag11) {
               value20 += bridge10_25.bridge$getStringWidth(text16);
            }

            if (flag13) {
               value20 += 9.0F;
            }

            value20 += value14 * 2.0F;
            value19 += value14 * 2.0F;
            value18 += value14;
            value17 += value14;
            this.method11(value20, value19);
            MixinHelper_4 mixinhelper_421 = highlightimpl1.method2();
            if (flag12) {
               this.HROIRCHIHORCHCHCRICOOOIOOHIRCH.method11(mixinhelper_421, value2, value3, this.getWidth(), this.getHeight());
               if ((Boolean)this.OIRIICOHRIHHOCCCIICCRRCICOOCHI.get()) {
                  this.CRROIIOHCOROIIOROHHCHIRRCORCRH
                     .method11(mixinhelper_421, this, value2, value3, this.getWidth(), this.getHeight(), (Float)this.HCHIRRHHICRCCIOOHCOICHHIORICHH.get());
               }
            }

            float value22 = value2 + value20 / 2.0F;
            if (flag10) {
               if (text15.contains("W") || text15.contains("E")) {
                  CoordinatesDirectionChildMod.this.field15.method11(mixinhelper_421, text15.contains("W") ? "-" : "+", value22, value18, flag9);
               }

               value18 += number6 + value14;
            }

            if (flag8) {
               if (flag13) {
                  value17 = this.OOOCCCRICCHOORCCRHHRHHCOOCORRC.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_421, "[", value17, value18, flag9);
               }

               if (flag11) {
                  value17 = CoordinatesDirectionChildMod.this.labelColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_421, text16, value17, value18, flag9);
               }

               value17 = this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_421, text15, value17, value18, flag9);
               if (flag13) {
                  this.OOOCCCRICCHOORCCRHHRHHCOOCORRC.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_421, "]", value17, value18, flag9);
               }

               value18 += number6 + value14;
            }

            if (flag10 && (text15.contains("N") || text15.contains("S"))) {
               CoordinatesDirectionChildMod.this.field15.method11(mixinhelper_421, text15.contains("N") ? "-" : "+", value22, value18, flag9);
            }
         }
      }
   }
}
