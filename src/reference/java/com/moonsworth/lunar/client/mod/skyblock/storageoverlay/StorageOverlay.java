package com.moonsworth.lunar.client.mod.skyblock.storageoverlay;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.inventorymod.mixin.InventoryScreenPreserver;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.StorageOverlayListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.Storageoverlay2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.ChestsPerRow;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension4;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension5;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension6;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.EnumOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.DriverFieldType;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import lombok.Generated;

public class StorageOverlay extends AbstractFeature {
   private static final long field8 = 1000L;
   private final InventoryScreenPreserver field9 = (InventoryScreenPreserver)this.method63(InventoryScreenPreserver.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.GuiModuleManager field10 = (com.moonsworth.lunar.client.framework.feature.mod.GuiModuleManager)this.method63(
      com.moonsworth.lunar.client.framework.feature.mod.GuiModuleManager.class
   );
   private final StorageOverlayListener field11 = (StorageOverlayListener)this.method63(StorageOverlayListener.class);
   private final EnumOption<Gui2Extension3> field12 = (EnumOption<Gui2Extension3>)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
               "storageOverlayLockedSlots", Gui2Extension3.VISUAL
            )
            .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.DROPDOWN))
         .method10(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_LOCK02_CLOSE_STROKE))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "storageOverlayAnimationsEnabled"
            )
            .method4(true))
         .method15(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_ROCKET_SHIP_STROKE))
      .method31();
   private final EnumOption<ChestsPerRow> field14 = (EnumOption<ChestsPerRow>)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
               "storageOverlayChestsPerRow", ChestsPerRow.COUNT_3
            )
            .method11()
            .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.RADIO))
         .method10(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_INBOX_DEFAULT_STROKE))
      .method31();
   private final EnumOption<Gui2Extension> field15 = (EnumOption<Gui2Extension>)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
               "storageOverlayHeight", Gui2Extension.SMALL
            )
            .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.DROPDOWN))
         .method10(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_SWAP_ARROW_VERTICAL_STROKE))
      .method31();
   private final EnumOption<Gui2Extension4> field16 = (EnumOption<Gui2Extension4>)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
               "storageOverlayStyle", Gui2Extension4.FULLSCREEN
            )
            .method10(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_MONITOR01_STROKE))
         .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.DROPDOWN))
      .method31();
   private final EnumOption<Gui2Extension5> field17 = (EnumOption<Gui2Extension5>)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
               "storageOverlayTheme", Gui2Extension5.LIGHT
            )
            .method10(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_COLOR_PALETTE_STROKE))
         .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.DROPDOWN))
      .method31();
   private final EnumOption<Gui2Extension6> field18 = (EnumOption<Gui2Extension6>)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
               "storageOverlayScale", Gui2Extension6.DEFAULT
            )
            .method10(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_MAXIMIZE_LINE_ARROW_STROKE))
         .HORHROIOIOICIRHIOCOICHHHIHCIIO(DriverFieldType.DROPDOWN))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "storageOverlayAutoFocusInput"
         )
         .method15(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_TEXT_CURSOR_STROKE))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "storageOverlaySearchDescriptions"
            )
            .method4(true))
         .method15(com.moonsworth.lunar.client.driver.PhosphorIcon.PI_SEARCH_DEFAULT_STROKE))
      .method31();

   public StorageOverlay(Skyblock skyblock1) {
      super(false);
      this.method12(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method12(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      Storageoverlay2 storageoverlay22 = new Storageoverlay2(this, this.field11);
      this.field10.registerModule(storageoverlay22, (ModLifecycle)this.method14(ModTraits.field12, arg0 -> ModLifecycle.method13()));
      this.field9.method2((arg1x, arg2x, arg3) -> arg3 <= 1000L && storageoverlay22.method2(arg1x) && storageoverlay22.method2(arg2x));
   }

   @ConstantName
   public String getId() {
      return "STORAGE_OVERLAY";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field17, this.field13, this.field16});
         arg1x.method9(new ClientOption[]{this.field14, this.field15}).method1(new ClientOption[]{this.field16});
         arg1x.method9(new ClientOption[]{this.field18, this.field19, this.field20, this.field12});
      });
   }

   @Generated
   public EnumOption<Gui2Extension3> method13() {
      return this.field12;
   }

   @Generated
   public ToggleOption method14() {
      return this.field13;
   }

   @Generated
   public EnumOption<ChestsPerRow> method15() {
      return this.field14;
   }

   @Generated
   public EnumOption<Gui2Extension> method16() {
      return this.field15;
   }

   @Generated
   public EnumOption<Gui2Extension4> method17() {
      return this.field16;
   }

   @Generated
   public EnumOption<Gui2Extension5> method19() {
      return this.field17;
   }

   @Generated
   public EnumOption<Gui2Extension6> method21() {
      return this.field18;
   }

   @Generated
   public ToggleOption method22() {
      return this.field19;
   }

   @Generated
   public ToggleOption method23() {
      return this.field20;
   }
}
