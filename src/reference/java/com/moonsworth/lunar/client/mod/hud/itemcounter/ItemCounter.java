package com.moonsworth.lunar.client.mod.hud.itemcounter;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemCounterEntry;
import com.moonsworth.lunar.client.event.player.EventInventoryUpdate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ItemSelectOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class ItemCounter extends AbstractFeature {
   private final FloatOption childScale = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "childScale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.5F, 1.5F))
      .method31();
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("itemCounterGrouped").method31();
   private final ToggleOption background = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "background"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   private final ColorOption backgroundColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final MultiSelectOption countedItems = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.ItemSelectOption.Data)OptionFactory.method28(
            "itemCounterCountedItems"
         )
         .method5(arg1 -> {
            boolean flag2 = this.countedItems.contains(arg1);
            if (flag2) {
               this.addElement(arg1);
            } else {
               this.detachChild((Framework7Extension)this.elementMods.remove(arg1));
            }

            LcuiScreen.method145();
         }))
      .method31();
   private final Map<String, ItemCounterElement> elementMods = new HashMap<>();
   private boolean dirty = true;

   public ItemCounter() {
      super(false);
      this.registerOptions(ModTraits.field1, new ItemCounter.Data());
      this.handle(EventInventoryUpdate.class, arg1 -> this.dirty = true);
      this.handle(EventTick.class, arg1 -> {
         if (this.dirty) {
            ModChildren alertextension2 = (ModChildren)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
            if (alertextension2 != null) {
               for (Framework7Extension framework7extension4 : alertextension2.getChildren()) {
                  if (framework7extension4.isEnabled() && framework7extension4 instanceof ItemCounterElement itemcounterelementchildmod5) {
                     itemcounterelementchildmod5.method13();
                  }
               }
            }

            this.dirty = false;
         }
      });
   }

   public String getId() {
      return "ITEM_COUNTER";
   }

   protected List<Framework7Extension> getChildMods() {
      return List.of();
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method7(new ClientOption[]{this.childScale})).method2(this.itemCounterGrouped::get);
      lightingextension231.method9(new ClientOption[]{this.itemCounterGrouped});
      lightingextension231.method13();
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.countedItems})).method2(this.itemCounterGrouped::get);
      ((SettingsSectionImpl)lightingextension231.method9(
            new ClientOption[]{this.background, this.border, this.backgroundColor, this.borderColor, this.borderThickness}
         ))
         .method2(() -> !(Boolean)this.itemCounterGrouped.get());
      this.countedItems.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         for (String text3 : arg1x) {
            this.addElement(text3);
         }
      });
      this.itemCounterGrouped.HORHIRROCIOIICIOHCOCCOOHIRCCRI(this::layoutElements);
   }

   public void reset() {
      this.registerOptions(ModTraits.field5);
      this.elementMods.clear();
      LcuiScreen.method145();
   }

   protected ModDetails createDetails() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field4}).method11(this);
   }

   private void addElement(String text1) {
      if (!this.elementMods.containsKey(text1)) {
         ItemCounterEntry itemcounter_22 = (ItemCounterEntry)ItemSelectOption.method11().get(text1);
         if (itemcounter_22 != null) {
            ItemCounterElement itemcounterelementchildmod3 = ItemCounterElement.create(this, itemcounter_22, false, () -> !(Boolean)this.itemCounterGrouped.get());
            this.elementMods.put(text1, itemcounterelementchildmod3);
            this.attachChild(itemcounterelementchildmod3);
         }
      }
   }

   private void attachChild(Framework7Extension framework7extension1) {
      ((ModChildren)this.getGroupedOption(ModTraits.field5, arg0 -> ModChildren.method3())).method2(framework7extension1);
   }

   private void detachChild(Framework7Extension framework7extension1) {
      ModChildren alertextension2 = (ModChildren)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field5);
      if (alertextension2 != null) {
         alertextension2.method3(framework7extension1);
         if (alertextension2.getChildren().isEmpty()) {
            this.registerOptions(ModTraits.field5);
         }
      }
   }

   private void layoutElements(boolean flag1) {
      ItemCounter.Data data2 = (ItemCounter.Data)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
      if (flag1) {
         data2.field9 = null;
      } else if (data2.field9 != null) {
         for (ItemCounterElement itemcounterelementchildmod4 : this.elementMods.values()) {
            if (itemcounterelementchildmod4.isEnabled()) {
               MixinCore9Extension mixincore9extension5 = (MixinCore9Extension)itemcounterelementchildmod4.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
               float value6 = mixincore9extension5.getScale();
               float value7 = data2.getScale();
               float value8 = mixincore9extension5.method1() * value6 - data2.field9.field1 + data2.ICRIHRIORRCRCOOCCCHHRIRICCHHII() * value7;
               float value9 = mixincore9extension5.method2() * value6 - data2.field9.field2 + data2.RIIIOCHHCIHOIOROOOHRIRICCCCHHC() * value7;
               mixincore9extension5.method27(HudAnchor.TOP_LEFT);
               mixincore9extension5.method17(value8 - 2.0F * value6, value9 - 2.0F * value6);
            }
         }

         data2.field9 = null;
         data2.CCROIHHHCOCHHOHORCIRHOCRROIOCI(0.0F, 0.0F);
      }

      BackgroundExecutor.method13(LcuiScreen::method145, 1);
   }

   @Generated
   public FloatOption getChildScale() {
      return this.childScale;
   }

   @Generated
   public ToggleOption getGroupedOption() {
      return this.itemCounterGrouped;
   }

   @Generated
   protected void setDirty(boolean flag1) {
      this.dirty = flag1;
   }

   private class Data extends HudElementBase {
      private ItemCounter.HudRectangle field9 = null;

      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void render(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (this.itemCounterGrouped == null) {
            float value18 = Float.MAX_VALUE;
            float value20 = 0.0F;
            float value22 = Float.MAX_VALUE;
            float value24 = 0.0F;

            for (ItemCounterElement itemcounterelementchildmod27 : ItemCounter.this.elementMods.values()) {
               if (itemcounterelementchildmod27.isEnabled()) {
                  MixinCore9Extension mixincore9extension28 = (MixinCore9Extension)itemcounterelementchildmod27.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
                  mixincore9extension28.method15(highlightimpl1.method3().method5());
                  float value12 = mixincore9extension28.getScale();
                  float value13 = mixincore9extension28.method1() * value12;
                  float value14 = mixincore9extension28.method2() * value12;
                  FloatFloatPair floatfloatpair15 = itemcounterelementchildmod27.method14();
                  float value16 = floatfloatpair15.firstFloat() * value12;
                  float value17 = floatfloatpair15.secondFloat() * value12;
                  value18 = Math.min(value18, value13);
                  value20 = Math.max(value20, value13 + value16);
                  value22 = Math.min(value22, value14);
                  value24 = Math.max(value24, value14 + value17);
               }
            }

            value18 = Math.round(value18);
            value22 = Math.round(value22);
            value20 = Math.round(value20);
            value24 = Math.round(value24);
            this.registerOptions(HudAnchor.TOP_LEFT);
            this.method59(value18 - 2.0F, value22 - 2.0F);
            this.method58(value20 - value18, value24 - value22);
            this.itemCounterGrouped = new ItemCounter.HudRectangle(value18, value22, value20 - value18, value24 - value22);
         } else {
            MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
            if ((Boolean)ItemCounter.this.background.get()) {
               ItemCounter.this.backgroundColor.method11(mixinhelper_45, value2, value3, this.getWidth(), this.getHeight());
               if ((Boolean)ItemCounter.this.border.get()) {
                  ItemCounter.this.borderColor.method11(mixinhelper_45, this, value2, value3, this.getWidth(), this.getHeight(), (Float)ItemCounter.this.borderThickness.get());
               }
            }

            for (ItemCounterElement itemcounterelementchildmod7 : ItemCounter.this.elementMods.values()) {
               if (itemcounterelementchildmod7.isEnabled()) {
                  MixinCore9Extension mixincore9extension8 = (MixinCore9Extension)itemcounterelementchildmod7.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
                  float value9 = mixincore9extension8.getScale();
                  float value10 = mixincore9extension8.method1() * value9 - this.itemCounterGrouped.field1 + value2;
                  float value11 = mixincore9extension8.method2() * value9 - this.itemCounterGrouped.field2 + value3;
                  mixinhelper_45.push();
                  mixinhelper_45.method38(value10, value11, 0.0F);
                  mixinhelper_45.scale(value9, value9, 1.0F);
                  itemcounterelementchildmod7.method5(mixinhelper_45);
                  mixinhelper_45.pop();
               }
            }
         }
      }

      public boolean reset(boolean flag1) {
         boolean flag2 = (Boolean)ItemCounter.this.itemCounterGrouped.get();
         if (flag2) {
            if (this.itemCounterGrouped != null && this.itemCounterGrouped.field1 + this.itemCounterGrouped.field2 + this.itemCounterGrouped.field3 + this.itemCounterGrouped.field4 == 0.0F) {
               this.method58(0.0F, 0.0F);
               return false;
            }
         } else {
            this.method58(0.0F, 0.0F);
            this.itemCounterGrouped = null;
         }

         return flag2;
      }

      public void registerOptions(RootSettingsBuilder lightingextension231) {
         ((SettingsSectionImpl)lightingextension231.method7(new ClientOption[]{this.ICHRCHCIHROCHRCIRCCCCHCRCCCHOH()}))
            .method2(() -> !(Boolean)ItemCounter.this.itemCounterGrouped.get());
      }
   }

   private class HudRectangle {
      private final float x;
      private final float y;
      private final float width;
      private final float height;

      private HudRectangle(float value1, float value2, float value3, float value4) {
         this.x = value1;
         this.y = value2;
         this.width = value3;
         this.height = value4;
      }

      public float x() {
         return this.x;
      }

      public float y() {
         return this.y;
      }

      public float registerOptions() {
         return this.width;
      }

      public float registerOptions() {
         return this.height;
      }
   }
}
