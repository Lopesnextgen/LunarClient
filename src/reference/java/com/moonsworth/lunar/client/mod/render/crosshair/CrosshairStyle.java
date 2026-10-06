package com.moonsworth.lunar.client.mod.render.crosshair;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.ClientOptionWidget;
import com.moonsworth.lunar.client.ui.widget.BiomePreviewWidget;
import com.moonsworth.lunar.client.ui.widget.EnumDropdownWidget;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairPattern;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairPresets;
import com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra.CrosshairShapeDrawer;
import com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra.CrosshairShapeRenderer;
import com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra.CrosshairTextureRenderer;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairType;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairMode;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairGridSize;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairScale;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairShape;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.EnumOption.Data;
import com.moonsworth.lunar.client.config.option.CrosshairDrawOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.DriverFieldType;
import com.moonsworth.lunar.client.driver.bridge.JsonSection;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class CrosshairStyle extends AbstractFeature {
   protected final EnumOption<CrosshairMode> field8 = (EnumOption<CrosshairMode>)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
            "crosshairMode", CrosshairMode.SIMPLE
         )
         .HORHROIOIOICIRHIOCOICHHHIHCIIO(EnumDropdownWidget::new))
      .method31();
   protected final ColorOption field9 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "color"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("crosshairOutline")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final FloatOption field11 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "outlineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.0F, 1.0F))
      .method31();
   protected final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "outlineColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2013265920))
      .method31();
   protected final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("crosshairColorVanilla")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("vanillaBlendingColor")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("customScale")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final EnumOption<CrosshairScale> field16 = (EnumOption<CrosshairScale>)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
            "crosshairScale", CrosshairScale.NORMAL
         )
         .HORHROIOIOICIRHIOCOICHHHIHCIIO(EnumDropdownWidget::new))
      .method31();
   protected final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("healthColorShift")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final ColorOption field18 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "healthShiftColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   protected final IntegerOption field19 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "healthShiftThreshold"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(5))
         .method7(1, 20))
      .method31();
   protected final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("crosshairDynamicBow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final FloatOption field21 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dynamicBowScale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(2.0F))
         .method8(1.0F, 10.0F))
      .method31();
   protected final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("crosshairDynamicAttack")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final FloatOption field23 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "dynamicAttackScale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(2.0F))
         .method8(1.0F, 10.0F))
      .method31();
   protected final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("visibleInSpectator")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final EnumOption<CrosshairGridSize> field25 = (EnumOption<CrosshairGridSize>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "gridSize", CrosshairGridSize.MEDIUM
      )
      .method31();
   protected final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("gridStretch")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final CrosshairType field27;
   protected final CrosshairTextureRenderer field28;
   protected final CrosshairShapeRenderer field29;
   @NotNull
   protected CrosshairPattern field30 = CrosshairPattern.method5("LCCH-9-ECBAgPAfAgQIEAA");
   protected float field31;
   protected float field32;

   CrosshairStyle(Crosshair crosshair1, boolean flag2, CrosshairType crosshairtype3) {
      super(flag2);
      this.field27 = crosshairtype3;
      this.field28 = this.method13();
      this.field29 = new CrosshairShapeRenderer(this);
      this.method15(ModTraits.field16, ChildModBinding.method3(crosshair1));
   }

   protected CrosshairTextureRenderer method13() {
      return new CrosshairTextureRenderer(this, this.method29().id);
   }

   public String method14() {
      return "LCCH-9-ECBAgPAfAgQIEAA";
   }

   public String getId() {
      throw new IllegalStateException("CrosshairStyle must be created using CrosshairStyle#create!");
   }

   public void load(JsonObject json1) {
      super.load(json1);
      if (json1.has("customCrosshair")) {
         JsonElement element2 = json1.get("customCrosshair");
         if (element2.isJsonPrimitive()) {
            this.method12(CrosshairPattern.method5(element2.getAsString()));
         }
      }
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      json1.addProperty("customCrosshair", this.field30.method4());
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      this.method5(lightingextension231);
      this.method6(lightingextension231);
      this.method7(lightingextension231);
      this.method8(lightingextension231);
   }

   protected void method5(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method1(
            "crosshairPreviewLabel",
            arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new ClientOption[]{
                  ((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
                                    "crosshairPreview"
                                 )
                                 .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.CROSSHAIR_PREVIEW))
                              .HORHROIOIOICIRHIOCOICHHHIHCIIO(BiomePreviewWidget::new))
                           .method18(this))
                        .ROORCHCIOHIRRHCHCIRRHHHCRHRCOI())
                     .method31()
               }
            )
         ))
         .method2(() -> this.field8.get() == CrosshairMode.CUSTOM && !Bridge.method18().method1(KeyCode.KEY_TAB));
   }

   protected void method6(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field8});
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{
                     ((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
                                       "crosshairPresets"
                                    )
                                    .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.CROSSHAIR_PRESET_LIST))
                                 .HORHROIOIOICIRHIOCOICHHHIHCIIO(ClientOptionWidget::new))
                              .method18(this))
                           .ROORCHCIOHIRRHCHCIRRHHHCRHRCOI())
                        .method31()
                  }
               )
               .method3(() -> this.field8.get() != CrosshairMode.PRESET);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{new CrosshairDrawOption(this), this.field25, this.field26})
               .method3(() -> this.field8.get() != CrosshairMode.CUSTOM || Bridge.method18().method1(KeyCode.KEY_TAB));
            arg1x.ROOOCICROROOHCIRRHHHCRCOROOHHH().method3(() -> this.field8.get() != CrosshairMode.CUSTOM || Bridge.method18().method1(KeyCode.KEY_TAB));
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29.field2}).method3(() -> this.field8.get() != CrosshairMode.SIMPLE);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29.field6})
               .method3(() -> this.field8.get() != CrosshairMode.SIMPLE || !this.field29.method14());
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29.field3})
               .method3(() -> this.field8.get() != CrosshairMode.SIMPLE || !this.field29.method13());
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29.field4, this.field29.field5})
               .method3(() -> this.field8.get() != CrosshairMode.SIMPLE || !this.field29.method12());
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field9})
               .method3(() -> (Boolean)this.field13.get() && !(Boolean)this.field14.get());
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field29.field7,
                  arg1xx -> {
                     arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29.field8, this.field29.field9, this.field29.field10});
                     arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                           this.field29.field11, arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29.field12})
                        )
                        .method3(this.field13::get);
                     arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                           this.field29.field13, arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29.field14})
                        )
                        .method3(() -> !this.method16());
                  }
               )
               .method3(() -> this.field8.get() != CrosshairMode.SIMPLE || !this.field29.method15());
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field10, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11, this.field12})
            );
            arg1x.HOHIHOCHHRCRRIIORHHOROHIROCCCC().method3(() -> this.field8.get() != CrosshairMode.SIMPLE);
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{
                     com.moonsworth.lunar.client.config.option.OptionFactory.method14("copyCode").method4(() -> this.method23()).method31(),
                     com.moonsworth.lunar.client.config.option.OptionFactory.method14("importCode").method4(() -> this.method24()).method31()
                  }
               )
               .method3(() -> this.field8.get() != CrosshairMode.SIMPLE);
         }
      );
   }

   protected void method7(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "extraRenderOptions",
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field14}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field17, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field19, this.field18})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field20, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field21}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field22, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field23}));
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field24});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field15, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16}));
         }
      );
   }

   protected void method8(RootSettingsBuilder lightingextension231) {
      if (this.field27 != CrosshairType.NORMAL) {
         lightingextension231.method9(
            new ClientOption[]{com.moonsworth.lunar.client.config.option.OptionFactory.method14("copyFromNormal").method5(100.0F).method4(() -> {
               Crosshair crosshair1x = (Crosshair)((ChildModBinding)this.method57(ModTraits.field16)).method1();
               Set set2 = ((OptionContainer)crosshair1x.field8.method57(ModTraits.field14)).method2();
               ((OptionContainer)this.method57(ModTraits.field14)).method4(arg1xx -> {
                  set2.stream().filter(arg1xxx -> Objects.equals(arg1xxx.getId(), arg1xx.getId())).findAny().ifPresentOrElse(arg1xxx -> {
                     try {
                        arg1xx.method19(arg1xxx);
                     } catch (IllegalArgumentException illegalargumentexception3) {
                        LunarLogger.warn("Copying crosshair option", illegalargumentexception3);
                     }
                  }, () -> LunarLogger.method5("Unrecognized crosshair option %s", new Object[]{arg1xx.getId()}));
                  return false;
               });
               this.method12(crosshair1x.field8.method35().method2());
            }).method31()}
         );
      }

      this.field10.method9(this.field28::reload);
      this.field11.method9(this.field28::reload);
      this.field25.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method12(this.field30.method3(arg1x, (Boolean)this.field26.get())));
      this.field8.method9(() -> {
         if (this.field30.isEmpty()) {
            this.method12(CrosshairPattern.method5(this.method14()));
         }
      });
   }

   @JsonSection("settings")
   public JsonElement method31() {
      JsonArray array1 = (JsonArray)super.method31();
      JsonObject json2 = new JsonObject();
      json2.addProperty("customCrosshair", this.field30.method4());
      array1.add(json2);
      return array1;
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.field28.reset();
      } else {
         this.field28.reload();
      }
   }

   public void method4() {
      super.method4();
      this.field30 = CrosshairPattern.method5("LCCH-9-ECBAgPAfAgQIEAA");
      this.field28.reset();
   }

   public void method12(@NotNull CrosshairPattern crosshair21) {
      this.field30 = crosshair21;
      this.field25.CHRIHRCRCIOIHIROIHROCHHHCOICOI(crosshair21.method12());
      this.field28.reload();
   }

   protected CrosshairShapeDrawer method15() {
      return this.field17.get()
            && Ref.method7() != null
            && Ref.method7().bridge$hasHealth()
            && Ref.method7().bridge$getHealth() < ((Integer)this.field19.get()).intValue()
         ? new CrosshairShapeDrawer(this.field18, (Boolean)this.field13.get(), true)
         : new CrosshairShapeDrawer(this.field9, (Boolean)this.field13.get(), (Boolean)this.field14.get());
   }

   protected float method14(boolean flag1) {
      if (flag1) {
         return this.field15.get() ? ((CrosshairScale)this.field16.get()).getScale() / 2.0F : LcuiScreen.method151().method3() / 2.0F;
      } else {
         return this.field15.get() ? (float)((CrosshairScale)this.field16.get()).getScale() / LcuiScreen.method151().method3() : 1.0F;
      }
   }

   public void method15(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data22, boolean flag3) {
      float value4 = data22.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
      float value5 = data22.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
      if ((Boolean)this.field24.get() || Ref.method7() == null || !Ref.method7().bridge$isSpectator()) {
         float value6 = 0.0F;
         Bridge5Extension_5 bridge5extension_57 = Ref.method7();
         if (bridge5extension_57 != null && Ref.method3().bridge$getCurrentScreen() == null) {
            if ((Boolean)this.field20.get()) {
               ItemStackBridge bridgeextension_48 = bridge5extension_57.bridge$getHeldItem();
               int number9 = bridge5extension_57.bridge$getTicksUsingItem();
               if (bridgeextension_48 != null && !bridgeextension_48.bridge$isEmpty() && number9 > 0 && Bridge.method28().method8().equals(bridgeextension_48.bridge$getItem())) {
                  float value10 = number9;
                  if (Ref.MC_VERSION <= 5) {
                     value10 = bridgeextension_48.bridge$getMaxItemUseDuration() - value10;
                  }

                  float value11 = MathUtils.lerp(this.field32, value10, mixinhelper_41.method43());
                  this.field32 = value10;
                  float value12 = Math.min(1.0F, value11 / 20.0F);
                  value6 += this.field21.get() * (1.0F - value12 * value12);
               }
            }

            if ((Boolean)this.field22.get()) {
               float value14 = Ref.method7().bridge$getSwingProgress(mixinhelper_41.method43());
               if (value14 != 0.0F) {
                  float value16 = 1.0F - value14;
                  value6 += this.field23.get() * value16 * value16;
               }
            }
         }

         float value15 = MathUtils.lerp(this.field31, value6, mixinhelper_41.method43());
         this.field31 = value6;
         value6 = value15;
         CrosshairShapeDrawer crosshairelytra17 = this.method15();
         float value18 = this.method14(flag3);
         if (this.field8.get() == CrosshairMode.SIMPLE) {
            this.field29.method1(mixinhelper_41, value4, value5, value18, value6 * 2.0F, crosshairelytra17);
         } else {
            this.field28.method1(mixinhelper_41, value4, value5, value18 + value6 / 2.0F, crosshairelytra17);
         }
      }
   }

   protected boolean method16() {
      return (Boolean)this.field10.get() && (Float)this.field11.get() > 0.0F;
   }

   @Nullable
   protected CrosshairShapeDrawer method17() {
      return this.method16() ? new CrosshairShapeDrawer(this.field12, false, false) : null;
   }

   protected float method19() {
      return (Float)this.field11.get() * 0.8F + 0.2F;
   }

   protected float method21() {
      return (Float)this.field11.get() * 2.0F;
   }

   public boolean method22() {
      if (this.field8.get() != CrosshairMode.SIMPLE) {
         return false;
      }

      CrosshairShape gui2extension41 = (CrosshairShape)this.field29.field2.get();
      return (gui2extension41 == CrosshairShape.CROSS || gui2extension41 == CrosshairShape.X || gui2extension41 == CrosshairShape.CIRCLE || gui2extension41 == CrosshairShape.TRIANGLE)
         && (Integer)this.field29.field3.get() % 2 == 0;
   }

   public void method23() {
      try {
         ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
         DataOutputStream output2 = new DataOutputStream(bytearrayoutputstream1);
         int number3;
         String text4;
         if (this.field8.get() == CrosshairMode.SIMPLE) {
            text4 = "LCCS";
            number3 = 0;
         } else {
            if (this.field30.isEmpty() || CrosshairPresets.method1(this.field30)) {
               return;
            }

            text4 = "LCCH";
            number3 = this.field30.method10();
            output2.write(this.field30.method7());
         }

         this.method24(output2);
         output2.close();
         com.moonsworth.lunar.client.framework.feature.crosshair.Crosshair crosshair5 = new com.moonsworth.lunar.client.framework.feature.crosshair.Crosshair(
            text4, number3, bytearrayoutputstream1.toByteArray()
         );
         ClipboardUtils.method2(crosshair5.toString());
         Ref.method4()
            .method69()
            .method3(this.field4.method67().method2("gui.crosshair_edit", "copiedToClipboard", new Object[0]));
      } catch (Throwable exception6) {
         LunarLogger.warn("Crosshair export failed", exception6);
         Ref.method4()
            .method69()
            .method7(NotificationType.ERROR, this.field4.method67().method2("gui.crosshair_edit", "error", new Object[]{exception6.getMessage()}));
      }
   }

   public void method24() {
      String text1 = ClipboardUtils.method1();
      GuiScreenBridge bridge5extension62 = this.mc.bridge$getCurrentScreen();
      this.mc
         .bridge$displayScreen(
            Bridge.method8()
               .method18(
                  new ConfirmScreen(
                     "gui.crosshairConfirm",
                     arg3 -> {
                        try {
                           try {
                              if (!arg3) {
                                 return;
                              }

                              com.moonsworth.lunar.client.framework.feature.crosshair.Crosshair crosshair4 = com.moonsworth.lunar.client.framework.feature.crosshair.Crosshair.method1(
                                 text1
                              );
                              if (crosshair4 == null) {
                                 Ref.method4()
                                    .method69()
                                    .method7(
                                       NotificationType.WARNING,
                                       this.field4.method67().method2("gui.crosshair_edit", "invalidCode", new Object[0])
                                    );
                                 return;
                              }

                              int index5 = 0;
                              if ("LCCH".equals(crosshair4.type())) {
                                 CrosshairPattern crosshair26 = CrosshairPattern.method6(crosshair4.method2(), crosshair4.method3());
                                 if (crosshair26.isEmpty() || CrosshairPresets.method1(crosshair26)) {
                                    return;
                                 }

                                 index5 = crosshair26.method8(crosshair4.method2());
                                 this.method12(crosshair26);
                                 this.field8.OIRHOOIICOCIOOHICRRRICORIHHIHC(CrosshairMode.CUSTOM);
                              } else {
                                 this.field8.OIRHOOIICOCIOOHICRRRICORIHHIHC(CrosshairMode.SIMPLE);
                              }

                              ByteArrayInputStream bytearrayinputstream13 = new ByteArrayInputStream(crosshair4.method3());
                              bytearrayinputstream13.skip(index5);
                              if (bytearrayinputstream13.available() > 0) {
                                 DataInputStream input7 = new DataInputStream(bytearrayinputstream13);
                                 this.method23(input7);
                                 input7.close();
                              }

                              Ref.method4()
                                 .method69()
                                 .method3(this.field4.method67().method2("gui.crosshair_edit", "loadedFromClipboard", new Object[0]));
                           } catch (Throwable exception11) {
                              LunarLogger.warn("Crosshair import failed " + text1, exception11);
                              Ref.method4()
                                 .method69()
                                 .method7(
                                    NotificationType.ERROR,
                                    this.field4.method67().method2("gui.crosshair_edit", "error", new Object[]{exception11.getMessage()})
                                 );
                           }
                        } finally {
                           this.mc.bridge$displayScreen(bridge5extension62);
                        }
                     }
                  )
               )
         );
   }

   protected void method23(DataInputStream input1) {
      boolean flag2 = input1.readBoolean();
      if (flag2) {
         this.field29.field2.OIRHOOIICOCIOOHICRRRICORIHHIHC(CrosshairShape.values()[input1.readInt()]);
         this.field29.field3.method1(input1.readInt());
         this.field29.field4.method1(input1.readInt());
         this.field29.field5.method1(input1.readInt());
         this.field29.field6.method1(input1.readInt());
         this.field29.field7.OIRHOOIICOCIOOHICRRRICORIHHIHC(input1.readBoolean());
         this.field29.field8.method1(input1.readInt());
         this.field29.field9.OIRHOOIICOCIOOHICRRRICORIHHIHC(input1.readBoolean());
         this.field29.field10.OIRHOOIICOCIOOHICRRRICORIHHIHC(input1.readBoolean());
         this.field29.field11.OIRHOOIICOCIOOHICRRRICORIHHIHC(input1.readBoolean());
         this.method25(input1, this.field29.field12);
         this.field29.field13.OIRHOOIICOCIOOHICRRRICORIHHIHC(input1.readBoolean());
         this.field29.field14.method1(input1.readFloat());
      }

      this.method25(input1, this.field9);
      this.field10.method10(input1.readBoolean());
      this.field11.method1(input1.readFloat());
      this.method25(input1, this.field12);
      this.field13.method10(input1.readBoolean());
      this.field14.method10(input1.readBoolean());
      this.field15.method10(input1.readBoolean());
      this.field16.OIRHOOIICOCIOOHICRRRICORIHHIHC(CrosshairScale.values()[input1.readInt()]);
      this.field17.method10(input1.readBoolean());
      this.method25(input1, this.field18);
      this.field19.method1(input1.readInt());
      this.field20.method10(input1.readBoolean());
      this.field21.method1(input1.readFloat());
      this.field22.method10(input1.readBoolean());
      this.field23.method1(input1.readFloat());
      this.field24.method10(input1.readBoolean());
   }

   protected void method24(DataOutputStream output1) {
      boolean flag2 = this.field8.get() == CrosshairMode.SIMPLE;
      output1.writeBoolean(flag2);
      if (flag2) {
         output1.writeInt(((CrosshairShape)this.field29.field2.get()).ordinal());
         output1.writeInt((Integer)this.field29.field3.get());
         output1.writeInt((Integer)this.field29.field4.get());
         output1.writeInt((Integer)this.field29.field5.get());
         output1.writeInt((Integer)this.field29.field6.get());
         output1.writeBoolean((Boolean)this.field29.field7.get());
         output1.writeInt((Integer)this.field29.field8.get());
         output1.writeBoolean((Boolean)this.field29.field9.get());
         output1.writeBoolean((Boolean)this.field29.field10.get());
         output1.writeBoolean((Boolean)this.field29.field11.get());
         this.method26(output1, this.field29.field12);
         output1.writeBoolean((Boolean)this.field29.field13.get());
         output1.writeFloat((Float)this.field29.field14.get());
      }

      this.method26(output1, this.field9);
      output1.writeBoolean((Boolean)this.field10.get());
      output1.writeFloat((Float)this.field11.get());
      this.method26(output1, this.field12);
      output1.writeBoolean((Boolean)this.field13.get());
      output1.writeBoolean((Boolean)this.field14.get());
      output1.writeBoolean((Boolean)this.field15.get());
      output1.writeInt(((CrosshairScale)this.field16.get()).ordinal());
      output1.writeBoolean((Boolean)this.field17.get());
      this.method26(output1, this.field18);
      output1.writeInt((Integer)this.field19.get());
      output1.writeBoolean((Boolean)this.field20.get());
      output1.writeFloat((Float)this.field21.get());
      output1.writeBoolean((Boolean)this.field22.get());
      output1.writeFloat((Float)this.field23.get());
      output1.writeBoolean((Boolean)this.field24.get());
   }

   private void method25(DataInputStream input1, ColorOption lightingextension42222) {
      lightingextension42222.method1(input1.readInt());
      lightingextension42222.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(input1.readBoolean());
      lightingextension42222.method21().method1(input1.readInt());
      lightingextension42222.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC(com.moonsworth.lunar.client.render.color.ColorAnimation.values()[input1.readInt()]);
   }

   private void method26(DataOutputStream output1, ColorOption lightingextension42222) {
      output1.writeInt(lightingextension42222.method13());
      output1.writeBoolean((Boolean)lightingextension42222.method19().get());
      output1.writeInt((Integer)lightingextension42222.method21().get());
      output1.writeInt(((com.moonsworth.lunar.client.render.color.ColorAnimation)lightingextension42222.method23().get()).ordinal());
   }

   public static CrosshairStyle method27(Crosshair crosshair0, boolean flag1, CrosshairType crosshairtype2) {
      final String text3 = crosshairtype2.id;
      return new CrosshairStyle(crosshair0, flag1, crosshairtype2) {
         @Override
         public String getId() {
            return text3;
         }
      };
   }

   @Generated
   public ToggleOption method28() {
      return this.field13;
   }

   @Generated
   public CrosshairType method29() {
      return this.field27;
   }

   @Generated
   public CrosshairTextureRenderer method30() {
      return this.field28;
   }

   @Generated
   public CrosshairShapeRenderer method34() {
      return this.field29;
   }

   @NotNull
   @Generated
   public CrosshairPattern method35() {
      return this.field30;
   }
}
