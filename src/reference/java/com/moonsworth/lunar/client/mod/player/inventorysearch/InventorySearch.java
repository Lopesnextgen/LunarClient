package com.moonsworth.lunar.client.mod.player.inventorysearch;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.inventorymod.slot.inventorysearch.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.inventorymod.slot.inventorysearch.InventorySearchOverlay;
import com.moonsworth.lunar.client.framework.feature.mod.GuiModuleManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension5;
import com.moonsworth.lunar.client.framework.feature.mod.impl.click.storageoverlay.mixin.Gui2Extension6;
import com.moonsworth.lunar.client.event.input.EventKeyInput;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.input.KeyInputType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.EnumOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.FormattingCodes;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Locale;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;

public class InventorySearch extends AbstractFeature {
   private final GuiModuleManager field8 = (GuiModuleManager)this.method63(GuiModuleManager.class);
   private final InventorySearchOverlay field9;
   private final EnumOption<Gui2Extension5> field10 = (EnumOption<Gui2Extension5>)((Data)((Data)OptionFactory.method10(
               "inventorySearchTheme", Gui2Extension5.LIGHT
            )
            .method10(PhosphorIcon.PI_COLOR_PALETTE_STROKE))
         .method16(com.moonsworth.lunar.client.driver.DriverFieldType.DROPDOWN))
      .method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("invSearchOnlyOnSkyblock").method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("invSearchSearchLore").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<Gui2Extension6> field13 = (EnumOption<Gui2Extension6>)((Data)((Data)OptionFactory.method10(
               "storageOverlayScale", Gui2Extension6.DEFAULT
            )
            .method10(PhosphorIcon.PI_MAXIMIZE_LINE_ARROW_STROKE))
         .method16(com.moonsworth.lunar.client.driver.DriverFieldType.DROPDOWN))
      .method31();
   private final EnumOption<Gui2Extension> field14 = (EnumOption<Gui2Extension>)OptionFactory.method10(
         "invSearchPosition", Gui2Extension.BOTTOM_MIDDLE
      )
      .method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("invSearchHighlightMatching").method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "invSearchHighlightMatchingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("invSearchOpenWithKeybind").method31();
   private final ModifierKeybindOption field18 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "invSearchOpenKeybind"
         )
         .method5(KeyCode.KEY_F)
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("invSearchResetOnClose").method31();

   public InventorySearch(InventoryMods inventorymod1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(inventorymod1));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> !(Boolean)this.field11.get() || IslandUtils.isOnIsland()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method2(ModTraits.field18, arg0 -> arg0.RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(1));
      if (Ref.MC_VERSION >= 1) {
         this.field8
            .method3(
               this.field9 = new InventorySearchOverlay(
                  () -> ((Gui2Extension5)this.field10.get()).getTheme(),
                  this::method13,
                  this.field14::get,
                  () -> this.method14("search", new Object[0]),
                  this.field17::get
               ),
               (ModLifecycle)this.method14(ModTraits.field12, arg0 -> ModLifecycle.method13())
            );
         this.handle(EventRenderHologramItem.class, this::method1);
         this.handle(EventKeyInput.class, this::method2);
      } else {
         this.field9 = null;
      }
   }

   @VersionGate(min = 1)
   private void method1(EventRenderHologramItem data51) {
      if (!this.field9.method11().isBlank() && this.field9.isOpen()) {
         if (!this.method3(this.field9.method11(), data51.method3().bridge$getItemStack())) {
            data51.method1(-1442840576);
         } else if ((Boolean)this.field15.get()) {
            data51.method1(this.field16.method14(0.0F));
         }
      }
   }

   @VersionGate(min = 1)
   private void method2(EventKeyInput highlightimpl131) {
      if ((Boolean)this.field17.get()) {
         if (this.field8.isOverlayActive(this.field9)) {
            if (highlightimpl131.method4() == KeyInputType.PRESS) {
               if (!this.field9.isOpen() || ((KeyBind)this.field18.get()).hasModifiers()) {
                  int number2 = ModifierKeybindOption.method18();
                  if (this.field18.method5(highlightimpl131.method1(), number2)) {
                     if (this.field9.isOpen()) {
                        this.field9.close();
                        if ((Boolean)this.field19.get()) {
                           this.field9.method6();
                        }
                     } else {
                        this.field9.open();
                        this.field9.method10();
                     }

                     highlightimpl131.setCancelled(true);
                  }
               }
            }
         }
      }
   }

   private boolean method3(String text1, ItemStackBridge bridgeextension_42) {
      if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty()) {
         text1 = text1.toLowerCase(Locale.ROOT);
         boolean flag3 = text1.startsWith("lore:");
         if (flag3) {
            text1 = text1.substring(5);
         }

         flag3 |= this.field12.get();
         text1 = text1.trim();
         if (FormattingCodes.getTextWithoutFormattingCodes(bridgeextension_42.bridge$getDisplayName()).toLowerCase(Locale.ROOT).contains(text1)) {
            return true;
         }

         String text4 = text1;
         return flag3
            && bridgeextension_42.bridge$getTooltip(Ref.method7(), false)
               .stream()
               .anyMatch(arg1x -> FormattingCodes.getTextWithoutFormattingCodes(arg1x).toLowerCase(Locale.ROOT).contains(text4));
      } else {
         return false;
      }
   }

   private double method13() {
      if (this.field13.get() != Gui2Extension6.DEFAULT) {
         int number1 = ((Gui2Extension6)this.field13.get()).getScale();
         return (double)number1 / LcuiScreen.method151().method3();
      } else {
         return 1.0;
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10, this.field13, this.field14, this.field11, this.field12});
      lightingextension231.method7(this.field15, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16}));
      lightingextension231.method7(this.field17, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field18, this.field19}));
   }

   @ConstantName
   public String getId() {
      return "INVENTORY_SEARCH";
   }

   @Generated
   public EnumOption<Gui2Extension5> method14() {
      return this.field10;
   }

   @Generated
   public ToggleOption method15() {
      return this.field11;
   }

   @Generated
   public ToggleOption method16() {
      return this.field12;
   }

   @Generated
   public EnumOption<Gui2Extension6> method17() {
      return this.field13;
   }

   @Generated
   public EnumOption<Gui2Extension> method19() {
      return this.field14;
   }

   @Generated
   public ToggleOption method21() {
      return this.field15;
   }

   @Generated
   public ColorOption method22() {
      return this.field16;
   }

   @Generated
   public ToggleOption method23() {
      return this.field17;
   }

   @Generated
   public ModifierKeybindOption method24() {
      return this.field18;
   }

   @Generated
   public ToggleOption method25() {
      return this.field19;
   }
}
