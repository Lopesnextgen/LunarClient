package com.moonsworth.lunar.client.mod.player.inventorymod;

import com.moonsworth.lunar.bridge.GuiChestBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.inventorymod.mixin.InventoryScreenPreserver;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.mod.hud.inventoryhud.InventoryHud;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.player.slotlocking.SlotLocking;
import com.moonsworth.lunar.client.mod.player.EnchantmentSpriteTextDecoration;
import com.moonsworth.lunar.client.mod.player.inventorysearch.InventorySearch;
import com.moonsworth.lunar.client.mod.player.slotbinding.SlotBinding;
import com.moonsworth.lunar.client.mod.player.hotbarkeyoverlay.HotbarKeyOverlay;
import com.moonsworth.lunar.client.mod.player.itemdropprotection.ItemDropProtection;

public class InventoryMods extends AbstractFeature {
   private final InventoryScreenPreserver field8 = (InventoryScreenPreserver)this.method63(InventoryScreenPreserver.class);
   private final SlotLocking field9 = new SlotLocking(this);
   private final SlotBinding field10 = new SlotBinding(this);
   private final InventoryHud field11 = new InventoryHud(this);
   private final InventorySearch field12 = new InventorySearch(this);
   private final EnchantmentSpriteTextDecoration field13 = new EnchantmentSpriteTextDecoration(this);
   private final ItemDropProtection field14 = new ItemDropProtection(this);
   private final HotbarKeyOverlay field15 = new HotbarKeyOverlay(this);
   private final ToggleOption field16 = (ToggleOption)OptionFactory.method7("dontResetCursor").method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("cursorResetChestScreensOnly")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field18 = (FloatOption)((Data)((Data)OptionFactory.method2("resetCursorTimeout").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.3F))
         .method8(0.1F, 5.0F))
      .method31();

   public InventoryMods() {
      super(false);
      this.field8.method2((arg1, arg2, arg3) -> {
         if (!this.isEnabled()) {
            return false;
         } else if (!(Boolean)this.field16.get()) {
            return false;
         } else if ((Boolean)this.field17.get() && !(arg1 instanceof GuiChestBridge)) {
            return false;
         } else {
            return arg1.getClass() != arg2.getClass() ? false : arg3 <= (long)((Float)this.field18.get() * 1000.0F);
         }
      });
   }

   @ConstantName
   public String getId() {
      return "INVENTORY_MOD";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method11(this);
   }

   protected boolean method24(String text1) {
      return text1.equals("INVENTORY_SEARCH");
   }

   protected List<Framework7Extension> method9() {
      return List.of(this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field16});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field18, this.field17}))
         .method2(new ClientOption[]{this.field16});
   }

   @Generated
   public InventoryScreenPreserver method13() {
      return this.field8;
   }

   @Generated
   public SlotLocking method14() {
      return this.field9;
   }

   @Generated
   public SlotBinding method15() {
      return this.field10;
   }

   @Generated
   public InventoryHud method16() {
      return this.field11;
   }

   @Generated
   public InventorySearch method17() {
      return this.field12;
   }

   @Generated
   public EnchantmentSpriteTextDecoration method19() {
      return this.field13;
   }

   @Generated
   public ItemDropProtection method21() {
      return this.field14;
   }

   @Generated
   public HotbarKeyOverlay method22() {
      return this.field15;
   }

   @Generated
   public ToggleOption method23() {
      return this.field16;
   }

   @Generated
   public ToggleOption method24() {
      return this.field17;
   }

   @Generated
   public FloatOption method25() {
      return this.field18;
   }
}
