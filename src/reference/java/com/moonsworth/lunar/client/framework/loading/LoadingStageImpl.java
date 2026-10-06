package com.moonsworth.lunar.client.framework.loading;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.GuiIterator;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SettingsTreeAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType3;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class LoadingStageImpl extends com.moonsworth.lunar.client.config.SettingsContainer implements Gui, JsonProviderLegacy, EventRegistrar {
   private final GuiIterator field2 = new GuiIterator();
   private final FloatOption field3 = (FloatOption)((Data)((Data)OptionFactory.method2("panelX").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(600.0F))
         .method8(500.0F, 1000.0F))
      .method31();
   private final FloatOption field4 = (FloatOption)((Data)((Data)OptionFactory.method2("panelY").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(247.0F))
         .method8(147.0F, 800.0F))
      .method31();
   private final TextOption field5 = (TextOption)OptionFactory.method12("exportPath").method31();
   private final TextOption field6 = (TextOption)OptionFactory.method12("exportScreenshotPath").method31();
   private final ToggleOption field7 = (ToggleOption)OptionFactory.method7("fastRewinding").method31();
   private final FloatOption field8 = (FloatOption)((Data)((Data)OptionFactory.method2("cameraSpeed").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.01F, 10.0F))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("alwaysForceChunksLoading").RIHRRCHRORHHOROCHHCCHHOIOIOHCR())
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("chunkFade").method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("devTools").RIHRRCHRORHHOROCHHCCHHOIOIOHCR())
      .method31();
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)OptionFactory.method18("fullscreen")
      .method5(KeyCode.KEY_ESCAPE)
      .method11()
      .method31();
   private final ModifierKeybindOption field13 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "undo"
         )
         .method2(KeyCombo.method2(KeyCode.KEY_Z)))
      .method11()
      .method31();
   private final ModifierKeybindOption field14 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "redo"
         )
         .method2(KeyCombo.method2(KeyCode.KEY_Y)))
      .method11()
      .method31();
   private final ModifierKeybindOption field15 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "save"
         )
         .method2(KeyCombo.method2(KeyCode.KEY_S)))
      .method11()
      .method31();
   private final ModifierKeybindOption field16 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "copy"
         )
         .method2(KeyCombo.method2(KeyCode.KEY_C)))
      .method11()
      .method31();
   private final ModifierKeybindOption field17 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "paste"
         )
         .method2(KeyCombo.method2(KeyCode.KEY_V)))
      .method11()
      .method31();
   private final ModifierKeybindOption field18 = (ModifierKeybindOption)OptionFactory.method18("playPause")
      .method5(KeyCode.KEY_SPACE)
      .method11()
      .method31();
   private final ModifierKeybindOption field19 = (ModifierKeybindOption)OptionFactory.method18("previousFrame")
      .method5(KeyCode.KEY_LEFT)
      .method11()
      .method31();
   private final ModifierKeybindOption field20 = (ModifierKeybindOption)OptionFactory.method18("nextFrame")
      .method5(KeyCode.KEY_RIGHT)
      .method11()
      .method31();
   private final ModifierKeybindOption field21 = (ModifierKeybindOption)OptionFactory.method18("remove")
      .method5(KeyCode.KEY_BACK)
      .method11()
      .method31();
   private final ModifierKeybindOption field22 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "removeAndCloseGap"
         )
         .method2(KeyCombo.method2(KeyCode.KEY_BACK)))
      .method11()
      .method31();
   private final ModifierKeybindOption field23 = (ModifierKeybindOption)OptionFactory.method18("cut")
      .method5(KeyCode.KEY_C)
      .method11()
      .method31();
   private final ModifierKeybindOption field24 = (ModifierKeybindOption)OptionFactory.method18("switchPreviewMode")
      .method5(KeyCode.KEY_F)
      .method11()
      .method31();
   private final ModifierKeybindOption field25 = (ModifierKeybindOption)OptionFactory.method18("screenshot")
      .method5(KeyCode.KEY_F2)
      .method11()
      .method31();

   public LoadingStageImpl() {
      this.handle(ScreenChangeEvent.class, this::init);
      this.init();
   }

   @Override
   public void method2() {
      super.init();

      for (ClientOption var2 : this.method13()) {
         var2.method21(OptionTraits.field6, ThreadModuleDumpType3.INSTANCE);
         OptionDataProvider var3 = (OptionDataProvider)var2.method1(OptionTraits.field10);
         if (var3 != null && var3.method2()) {
            var3.method4(OptionCategory.REWIND, null);
         }

         var2.method8(var2x -> {
            if (var3 != null && var3.method2()) {
               var3.method2(OptionCategory.REWIND);
            }

            this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         });
      }
   }

   @Override
   public void init() {
      super.init();
      ThreadModuleDump63.method4().method107().method3("rewind", this);
   }

   private void method2(ScreenChangeEvent var1) {
      if (DriverViewportLegacy.method50() == null || DriverViewportLegacy.method50().method61() == DriverRouteRegistryLegacy.field12) {
         this.field2.refresh();
      }
   }

   @Override
   protected void method10(SettingsTreeAssembler var1) {
      ((SettingsSectionImpl)var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field3, this.field4, this.field5, this.field6}))
         .method2(() -> true);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(SettingsPage.GENERAL, var1x -> {
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field7, this.field8, this.field9});
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(35);
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11}).method3(() -> com.moonsworth.lunar.client.framework.build.LunarBuildData.field4);
      });
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.CONTROLS,
         var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{
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
               this.field25
            }
         )
      );
   }

   @Override
   public String method5() {
      return "rewind.json";
   }

   @Nullable
   public JsonElement method128() {
      return this.provide();
   }

   public JsonElement provide() {
      JsonArray var1 = new JsonArray(this.method10().size());

      for (ClientOption var3 : this.method10()) {
         OptionDataProvider var4 = (OptionDataProvider)var3.method1(OptionTraits.field10);
         if (var4 != null) {
            var1.add(var4.provide());
         }
      }

      return var1;
   }

   @Override
   public void method7(boolean var1) {
      if (FeatureFlag.REWIND.isEnabled()) {
         super.method7(var1);
      }
   }

   public boolean method41() {
      return (Boolean)this.field11.get() && !com.moonsworth.lunar.client.framework.build.LunarBuildData.field4;
   }

   @Override
   public boolean method11() {
      return true;
   }

   @Generated
   public GuiIterator method15() {
      return this.field2;
   }

   @Generated
   public FloatOption method16() {
      return this.field3;
   }

   @Generated
   public FloatOption method17() {
      return this.field4;
   }

   @Generated
   public TextOption method18() {
      return this.field5;
   }

   @Generated
   public TextOption method19() {
      return this.field6;
   }

   @Generated
   public ToggleOption method20() {
      return this.field7;
   }

   @Generated
   public FloatOption method21() {
      return this.field8;
   }

   @Generated
   public ToggleOption method22() {
      return this.field9;
   }

   @Generated
   public ToggleOption method23() {
      return this.field10;
   }

   @Generated
   public ToggleOption method24() {
      return this.field11;
   }

   @Generated
   public ModifierKeybindOption method25() {
      return this.field12;
   }

   @Generated
   public ModifierKeybindOption method26() {
      return this.field13;
   }

   @Generated
   public ModifierKeybindOption method27() {
      return this.field14;
   }

   @Generated
   public ModifierKeybindOption method28() {
      return this.field15;
   }

   @Generated
   public ModifierKeybindOption method29() {
      return this.field16;
   }

   @Generated
   public ModifierKeybindOption method30() {
      return this.field17;
   }

   @Generated
   public ModifierKeybindOption method31() {
      return this.field18;
   }

   @Generated
   public ModifierKeybindOption method32() {
      return this.field19;
   }

   @Generated
   public ModifierKeybindOption method33() {
      return this.field20;
   }

   @Generated
   public ModifierKeybindOption method34() {
      return this.field21;
   }

   @Generated
   public ModifierKeybindOption method35() {
      return this.field22;
   }

   @Generated
   public ModifierKeybindOption method36() {
      return this.field23;
   }

   @Generated
   public ModifierKeybindOption method37() {
      return this.field24;
   }

   @Generated
   public ModifierKeybindOption method38() {
      return this.field25;
   }
}
