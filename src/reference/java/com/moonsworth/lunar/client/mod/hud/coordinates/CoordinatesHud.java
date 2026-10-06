package com.moonsworth.lunar.client.mod.hud.coordinates;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusListMode;
import com.moonsworth.lunar.client.framework.feature.coordinates.CoordinatesBiomeChildMod;
import com.moonsworth.lunar.client.framework.feature.coordinates.CoordinatesDirectionChildMod;
import com.moonsworth.lunar.client.framework.listener.BiomeListener;
import com.moonsworth.lunar.client.framework.listener.BiomeListener.BiomeDisplay;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class CoordinatesHud extends AbstractFeature {
   private final BiomeListener biomeListener = (BiomeListener)this.method63(BiomeListener.class);
   private final ToggleOption showWhileTyping = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showWhileTyping").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption textShadow = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption background = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.5F, 3.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption backgroundColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<ArmorStatusListMode> mode = (EnumOption<ArmorStatusListMode>)OptionFactory.method10("mode", ArmorStatusListMode.VERTICAL)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ModifierKeybindOption copyCoords = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
               "copyCoords"
            )
            .OOOHICCHHHRHCORIRCRHOCROROIOCR(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption moveChildrenIndividually = (ToggleOption)OptionFactory.method7("moveChildrenIndividually").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption decimalCoordinates = (ToggleOption)OptionFactory.method7("decimalCoordinates").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final CoordinatesHudEntry xChild = CoordinatesHudEntry.create(
      this, "X", () -> this.formatCoordinate(Ref.method7().bridge$getPosX()), "500"
   );
   private final CoordinatesHudEntry yChild = CoordinatesHudEntry.create(
      this, "Y", () -> this.formatCoordinate(Ref.method7().bridge$getBoundingBox().bridge$getMinY()), "62"
   );
   private final CoordinatesHudEntry zChild = CoordinatesHudEntry.create(
      this, "Z", () -> this.formatCoordinate(Ref.method7().bridge$getPosZ()), "250"
   );
   private final CoordinatesHudEntry renderCountChild = CoordinatesHudEntry.create(this, "C", () -> {
      int number1 = this.mc.bridge$getLevelRenderer().bridge$getMaximumRenderCount();
      int number2 = this.mc.bridge$getLevelRenderer().bridge$getUnculledRenderCount();
      return number2 + "/" + number1;
   }, "92/4269");
   private final CoordinatesBiomeChildMod biomeChild = CoordinatesBiomeChildMod.render(this, "Biome", "Plains");
   private final CoordinatesDirectionChildMod directionChild = CoordinatesDirectionChildMod.shouldRender(this, "Direction", "N");

   public CoordinatesHud() {
      super(true);
      this.registerOptions(ModTraits.field1, new CoordinatesHud.Data());
   }

   public String getId() {
      return "COORDINATES";
   }

   protected List<Framework7Extension> getChildMods() {
      return List.of(this.xChild, this.yChild, this.zChild, this.renderCountChild, this.directionChild, this.biomeChild);
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.mode, this.textShadow, this.showWhileTyping}).method3(this.moveChildrenIndividually::get);
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.background,
                  arg1xx -> arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                     this.border, arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.borderThickness})
                  )
               )
               .method3(this.moveChildrenIndividually::get);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.copyCoords}).method3(this.moveChildrenIndividually::get);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.decimalCoordinates, this.moveChildrenIndividually});
         }
      );
      ((SettingsSectionImpl)lightingextension231.method7(
            SettingsPage.COLOR, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.backgroundColor, this.borderColor})
         ))
         .method2(this.moveChildrenIndividually::get);
      this.copyCoords.method3(() -> {
         if (!Ref.method4().method40().method85().method19()) {
            if (Ref.method8() != null) {
               Bridge5Extension_5 bridge5extension_50 = Ref.method7();
               String text1x = String.format("X: %s Y: %s Z: %s", (int)bridge5extension_50.bridge$getPosX(), (int)bridge5extension_50.bridge$getPosY(), (int)bridge5extension_50.bridge$getPosZ());
               ClipboardUtils.method2(text1x);
               Ref.method4().method69().method3("Copied coordinates to clipboard!");
            }
         }
      });
   }

   public boolean moveChildrenTogether() {
      return !(Boolean)this.moveChildrenIndividually.get();
   }

   public void shouldRender(CoordinatesHudEntry coordinateschildhudmod1) {
      ModChildren alertextension2 = (ModChildren)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension2 != null) {
         for (Framework7Extension framework7extension4 : alertextension2.getChildren()) {
            if (framework7extension4 instanceof CoordinatesHudEntry coordinateschildhudmod5) {
               coordinateschildhudmod5.method16().method11(coordinateschildhudmod1.getLabelColor());
               coordinateschildhudmod5.method14().method11(coordinateschildhudmod1.getLabelColorOption());
            }
         }
      }
   }

   private String formatCoordinate(double value1) {
      if ((Boolean)this.decimalCoordinates.get()) {
         int number3 = (int)(value1 * 100.0);
         return String.valueOf(number3 / 100.0);
      } else {
         return String.valueOf(MathUtils.method9(value1));
      }
   }

   @NotNull
   public static String getCardinalDirection(float value0) {
      String[] items1 = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
      double value2 = MathUtils.method13(value0) + 180.0;
      value2 += 22.5;
      value2 %= 360.0;
      value2 /= 45.0;
      return items1[MathUtils.method9(value2)];
   }

   public BiomeDisplay getBiome() {
      return this.biomeListener.method5();
   }

   @Generated
   public ToggleOption getShowWhileTyping() {
      return this.showWhileTyping;
   }

   @Generated
   public ToggleOption getMoveChildrenIndividually() {
      return this.moveChildrenIndividually;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void render(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (flag4) {
            double value5 = 500.0;
            double value7 = 62.0;
            double value9 = 250.0;
            this.shouldRender(highlightimpl1.method2(), value5, value7, value9, 180.0F, 92, 4269, value2, value3, true);
         } else {
            double value15 = Ref.method7().bridge$getPosX();
            double value16 = Ref.method7().bridge$getBoundingBox().bridge$getMinY();
            double value17 = Ref.method7().bridge$getPosZ();
            int number11 = CoordinatesHud.this.mc.bridge$getLevelRenderer().bridge$getUnculledRenderCount();
            int number12 = CoordinatesHud.this.mc.bridge$getLevelRenderer().bridge$getMaximumRenderCount();
            double value13;
            if (CoordinatesHud.this.mc.bridge$getRenderViewEntity() != null) {
               value13 = CoordinatesHud.this.mc.bridge$getRenderViewEntity().bridge$getRotationYaw();
            } else {
               value13 = CoordinatesHud.this.mc.bridge$getPlayer().bridge$getRotationYaw();
            }

            this.shouldRender(highlightimpl1.method2(), value15, value16, value17, (float)value13, number11, number12, value2, value3, false);
         }
      }

      public boolean method30() {
         return CoordinatesHud.this.moveChildrenTogether();
      }

      public boolean shouldRender(boolean flag1) {
         if ((Boolean)CoordinatesHud.this.moveChildrenIndividually.get()) {
            return false;
         } else if (!(Boolean)CoordinatesHud.this.showWhileTyping.get() && Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            return false;
         } else if (CoordinatesHud.this.renderCountChild.isEnabled()) {
            return true;
         } else {
            boolean flag2 = ReducedDebugInfoNotifier.method1() && !flag1;
            if (flag2
               || !CoordinatesHud.this.xChild.isEnabled()
                  && !CoordinatesHud.this.yChild.isEnabled()
                  && !CoordinatesHud.this.zChild.isEnabled()
                  && !CoordinatesHud.this.biomeChild.isEnabled()
                  && !CoordinatesHud.this.directionChild.isEnabled()) {
               this.method58(0.0F, 0.0F);
               return false;
            } else {
               return true;
            }
         }
      }

      private void shouldRender(MixinHelper_4 mixinhelper_41, double value2, double value4, double value6, float value8, int number9, int number10, float value11, float value12, boolean flag13) {
         boolean flag14 = ReducedDebugInfoNotifier.method1() && !flag13;
         boolean flag15 = !flag14 && CoordinatesHud.this.xChild.isEnabled();
         boolean flag16 = !flag14 && CoordinatesHud.this.yChild.isEnabled();
         boolean flag17 = !flag14 && CoordinatesHud.this.zChild.isEnabled();
         boolean flag18 = !flag14 && CoordinatesHud.this.biomeChild.isEnabled();
         boolean flag19 = !flag14 && CoordinatesHud.this.directionChild.isEnabled();
         boolean flag20 = !flag14 && (Boolean)CoordinatesHud.this.directionChild.method19().get();
         boolean flag21 = CoordinatesHud.this.renderCountChild.isEnabled();
         Bridge10_2 bridge10_222 = Ref.method10();
         boolean flag23 = (Boolean)CoordinatesHud.this.background.get();
         boolean flag24 = (Boolean)CoordinatesHud.this.textShadow.get();
         if (flag23) {
            CoordinatesHud.this.backgroundColor.method11(mixinhelper_41, value11, value12, this.getWidth(), this.getHeight());
            if ((Boolean)CoordinatesHud.this.border.get()) {
               CoordinatesHud.this.borderColor.method11(mixinhelper_41, this, value11, value12, this.getWidth(), this.getHeight(), (Float)CoordinatesHud.this.borderThickness.get());
            }
         }

         mixinhelper_41.method38(value11, value12, 0.0F);
         float value26 = 0.0F;
         float value25;
         if (CoordinatesHud.this.mode.get() == ArmorStatusListMode.HORIZONTAL) {
            ColorOption lightingextension422227 = null;
            if (flag15) {
               lightingextension422227 = CoordinatesHud.this.xChild.method16();
            } else if (flag16) {
               lightingextension422227 = CoordinatesHud.this.yChild.method16();
            } else if (flag17) {
               lightingextension422227 = CoordinatesHud.this.zChild.method16();
            } else if (flag21) {
               lightingextension422227 = CoordinatesHud.this.renderCountChild.method16();
            }

            float value28 = flag23 ? 3.0F : 0.0F;
            value25 = 5.0F;
            if (!flag23 && lightingextension422227 != null) {
               value25 = lightingextension422227.method43(mixinhelper_41, "(", value25, value28, flag24);
            }

            String text29 = CoordinatesHud.getCardinalDirection(value8);
            if (flag15) {
               value25 = CoordinatesHud.this.xChild.method15().get()
                  ? CoordinatesHud.this.xChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "X: ", value25, value28, flag24)
                  : value25;
               value25 = CoordinatesHud.this.xChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, CoordinatesHud.this.formatCoordinate(value2), value25, value28, flag24);
               ColorOption lightingextension422230;
               if (flag20 && (text29.contains("W") || text29.contains("E"))) {
                  value25 = CoordinatesHud.this.directionChild.method21().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text29.contains("W") ? " (-)" : " (+)", value25, value28, flag24);
                  lightingextension422230 = CoordinatesHud.this.directionChild.method21();
               } else {
                  lightingextension422230 = CoordinatesHud.this.xChild.method14();
               }

               if (flag16 || flag17 || flag21) {
                  value25 = lightingextension422230.method43(mixinhelper_41, ", ", value25, value28, flag24);
               }
            }

            if (flag16) {
               value25 = CoordinatesHud.this.yChild.method15().get()
                  ? CoordinatesHud.this.yChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "Y: ", value25, value28, flag24)
                  : value25;
               value25 = CoordinatesHud.this.yChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, CoordinatesHud.this.formatCoordinate(value4), value25, value28, flag24);
               if (flag17 || flag21) {
                  value25 = CoordinatesHud.this.yChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, ", ", value25, value28, flag24);
               }
            }

            if (flag17) {
               value25 = CoordinatesHud.this.zChild.method15().get()
                  ? CoordinatesHud.this.zChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "Z: ", value25, value28, flag24)
                  : value25;
               value25 = CoordinatesHud.this.zChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, CoordinatesHud.this.formatCoordinate(value6), value25, value28, flag24);
               ColorOption lightingextension422248;
               if (flag20 && (text29.contains("N") || text29.contains("S"))) {
                  value25 = CoordinatesHud.this.directionChild.method22().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text29.contains("N") ? " (-)" : " (+)", value25, value28, flag24);
                  lightingextension422248 = CoordinatesHud.this.directionChild.method22();
               } else {
                  lightingextension422248 = CoordinatesHud.this.zChild.method14();
               }

               if (flag21) {
                  value25 = lightingextension422248.method43(mixinhelper_41, ", ", value25, value28, flag24);
               }
            }

            if (flag21) {
               value25 = CoordinatesHud.this.renderCountChild.method15().get()
                  ? CoordinatesHud.this.renderCountChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "C: ", value25, value28, flag24)
                  : value25;
               value25 = CoordinatesHud.this.renderCountChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, number9 + "/" + number10, value25, value28, flag24);
            }

            if (!flag23) {
               if (lightingextension422227 != null) {
                  value25 = lightingextension422227.method43(mixinhelper_41, ")", value25, value28, flag24);
               }
            } else {
               value26 += 4.0F;
            }

            if (flag19 && (Boolean)CoordinatesHud.this.directionChild.method17().get()) {
               boolean flag49 = flag15 || flag16 || flag17 || flag21 || flag18;
               if (flag49) {
                  text29 = " " + text29;
               }

               value25 = CoordinatesHud.this.directionChild.RRROIIRRRRICHCIIRHIRHRRHCICICC().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text29, value25, value28, flag24);
            }

            value26 += bridge10_222.method19();
         } else {
            value25 = 0.0F;
            value26 = 5.0F;
            float value45 = 0.0F;
            float value46 = 0.0F;
            float value47 = 0.0F;
            float value50 = 0.0F;
            float value31 = 0.0F;
            float value33 = 0.0F;
            if (flag15) {
               float value34 = CoordinatesHud.this.xChild.method15().get()
                  ? CoordinatesHud.this.xChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "X: ", 5.0F, value26, flag24)
                  : 5.0F;
               value25 = Math.max(
                  value25, CoordinatesHud.this.xChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, CoordinatesHud.this.formatCoordinate(value2), value34, value26, flag24)
               );
               value46 = value26;
               value26 += bridge10_222.method19() + 2;
            }

            if (flag16) {
               float value52 = CoordinatesHud.this.yChild.method15().get()
                  ? CoordinatesHud.this.yChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "Y: ", 5.0F, value26, flag24)
                  : 5.0F;
               value25 = Math.max(
                  value25, CoordinatesHud.this.yChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, CoordinatesHud.this.formatCoordinate(value4), value52, value26, flag24)
               );
               value45 = value26;
               value26 += bridge10_222.method19() + 2;
            }

            if (flag17) {
               float value53 = CoordinatesHud.this.zChild.method15().get()
                  ? CoordinatesHud.this.zChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "Z: ", 5.0F, value26, flag24)
                  : 5.0F;
               value25 = Math.max(
                  value25, CoordinatesHud.this.zChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, CoordinatesHud.this.formatCoordinate(value6), value53, value26, flag24)
               );
               value47 = value26;
               value26 += bridge10_222.method19() + 2;
            }

            if (flag21) {
               float value54 = CoordinatesHud.this.renderCountChild.method15().get()
                  ? CoordinatesHud.this.renderCountChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "C: ", 5.0F, value26, flag24)
                  : 5.0F;
               value25 = Math.max(value25, CoordinatesHud.this.renderCountChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, number9 + "/" + number10, value54, value26, flag24));
               value50 = value26;
               value26 += bridge10_222.method19() + 2;
            }

            if (flag18) {
               float value55 = CoordinatesHud.this.biomeChild.OHCCCOORCROHHHCIIHHIIOORRCRICI().get()
                  ? CoordinatesHud.this.biomeChild
                     .RCRIRCIIROOOHROHCORRORRHIIOCRI()
                     .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, CoordinatesHud.this.shouldRender("biome", new Object[0]) + ": ", 5.0F, value26, flag24)
                  : 5.0F;
               BiomeDisplay data335 = CoordinatesHud.this.getBiome();
               int number36 = CoordinatesHud.this.biomeChild.RRROIIRRRRICHCIIRHIRHRRHCICICC().method14(value11 + value12);
               if ((Boolean)CoordinatesHud.this.biomeChild.method17().get()) {
                  number36 = data335.method1();
               }

               mixinhelper_41.method19(bridge10_222, data335.name(), value55, value26, number36, flag24);
               value25 = Math.max(value25, value55 + bridge10_222.bridge$getStringWidth(data335.name()));
               value31 = value26;
               value26 += bridge10_222.method19() + 2;
            }

            String text56 = CoordinatesHud.getCardinalDirection(value8);
            if (flag19) {
               boolean flag57 = flag15 || flag16 || flag17 || flag21 || flag18;
               if (flag57) {
                  value25 += 20.0F;
               } else {
                  value25 = bridge10_222.bridge$getStringWidth(text56);
                  value26 = bridge10_222.method19() + 3;
               }

               float value37 = 12.0F;
               float value32 = value25 - value37 + 3.0F;
               float value38 = flag57 ? value37 - bridge10_222.bridge$getStringWidth(text56) : 0.0F;
               if (flag16) {
                  value33 = value45;
               } else if (!flag15 && !flag17) {
                  if (flag21) {
                     value33 = value50;
                  } else if (flag18) {
                     value33 = value31;
                  } else {
                     value33 = 3.0F;
                     value32 = 3.0F;
                     flag20 = false;
                  }
               } else if (flag15 && flag17) {
                  value33 = value46 + (value47 - value46) / 2.0F;
                  value32 -= 9.0F;
               } else if (flag15) {
                  value33 = value46;
                  flag20 = false;
               } else {
                  value33 = value47;
                  flag20 = false;
               }

               if ((Boolean)CoordinatesHud.this.directionChild.method17().get()) {
                  CoordinatesHud.this.directionChild.RRROIIRRRRICHCIIRHIRHRRHCICICC().HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text56, value32 + value38 / 2.0F, value33, flag24);
               }
            }

            if (flag19 && flag20) {
               if (flag15 && (text56.contains("W") || text56.contains("E"))) {
                  CoordinatesHud.this.directionChild
                     .method21()
                     .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text56.contains("W") ? "-" : "+", value25 - bridge10_222.bridge$getStringWidth("-"), value46, flag24);
               }

               if (flag17 && (text56.contains("N") || text56.contains("S"))) {
                  CoordinatesHud.this.directionChild
                     .method22()
                     .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text56.contains("N") ? "-" : "+", value25 - bridge10_222.bridge$getStringWidth("-"), value47, flag24);
               }
            }

            if (!flag23) {
               value26 += 8.0F;
            } else {
               value26 += 2.0F;
            }
         }

         this.method58(value25 != 0.0F ? value25 + 5.0F : 0.0F, value25 != 0.0F ? value26 : 0.0F);
         mixinhelper_41.method38(-value11, -value12, 0.0F);
      }
   }
}
