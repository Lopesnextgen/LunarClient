package com.moonsworth.lunar.client.mod.hud.itemcounter;

import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemCounterRenderer;
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemCounterEntry;
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemCounterRenderer.ItemCounterTextSide;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.text.TextUtils;
import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class ItemCounterElement extends AbstractFeature {
   private final ItemCounterEntry entry;
   private final ItemCounterRenderer renderer;
   private final ToggleOption renderItemIcon = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderItemIcon").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<ItemCounterTextSide> textSide = (EnumOption<ItemCounterTextSide>)OptionFactory.method10("textSide", ItemCounterTextSide.NONE).method31();
   private final ToggleOption background = (ToggleOption)OptionFactory.method7("background").method31();
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

   private ItemCounterElement(ItemCounter itemcounter1, @NotNull ItemCounterEntry itemcounter_22, boolean flag3, BooleanSupplier booleansupplier4) {
      super(flag3);
      this.renderer = new ItemCounterRenderer(itemcounter_22);
      this.entry = itemcounter_22;
      this.method17(ModTraits.field16, ChildModBinding.method5(booleansupplier4, itemcounter1));
      this.method17(ModTraits.field1, new ItemCounterElement.Data());
   }

   public String getId() {
      throw new IllegalStateException("ItemCounterElement must be created using ItemCounterElement.create()!");
   }

   public void render(boolean flag1) {
      if (flag1) {
         this.getParentCounter().method11(true);
      }
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.renderItemIcon, this.textSide});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.background,
               arg1xx -> arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.border, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.borderThickness})
               )
            );
         }
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.method9(new ClientOption[]{this.backgroundColor}).method3(() -> !(Boolean)this.background.get());
         arg1x.method9(new ClientOption[]{this.borderColor}).method3(() -> !(Boolean)this.border.get());
      });
   }

   protected ModDetails createDetails() {
      return ModDetails.method7().method4(() -> this.entry.toString()).method11(this);
   }

   public void refresh() {
      this.renderer.method3();
   }

   public void render(MixinHelper_4 mixinhelper_41) {
      ((ItemCounterElement.Data)this.method7(ModTraits.field1)).method2(mixinhelper_41, 0.0F, 0.0F);
   }

   public FloatFloatPair measure() {
      return this.renderer.method2((ItemCounterTextSide)this.textSide.get(), (Boolean)this.renderItemIcon.get());
   }

   private ItemCounter getParentCounter() {
      return (ItemCounter)((ChildModBinding)this.method7(ModTraits.field16)).method1();
   }

   public static ItemCounterElement create(@NotNull ItemCounter itemcounter0, @NotNull ItemCounterEntry itemcounter_21, boolean flag2, BooleanSupplier booleansupplier3) {
      final String text4 = "ITEM_COUNTER_" + TextUtils.toUpperSnakeCase(itemcounter_21.method1().replace("minecraft:", "")) + "_CHILD";
      return new ItemCounterElement(itemcounter0, itemcounter_21, flag2, booleansupplier3) {
         @Override
         public String getId() {
            return text4;
         }
      };
   }

   @Generated
   public ItemCounterEntry getEntry() {
      return this.entry;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER);
      }

      public void render(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         FloatFloatPair floatfloatpair5 = ItemCounterElement.this.measure();
         this.getParentCounter(floatfloatpair5.firstFloat(), floatfloatpair5.secondFloat());
         MixinHelper_4 mixinhelper_46 = highlightimpl1.method2();
         if ((Boolean)ItemCounterElement.this.background.get()) {
            ItemCounterElement.this.backgroundColor.method11(mixinhelper_46, value2, value3, this.getWidth(), this.getHeight());
            if ((Boolean)ItemCounterElement.this.border.get()) {
               ItemCounterElement.this.borderColor
                  .method11(mixinhelper_46, this, value2, value3, this.getWidth(), this.getHeight(), (Float)ItemCounterElement.this.borderThickness.get());
            }
         }

         this.registerOptions(mixinhelper_46, value2, value3);
      }

      private void registerOptions(MixinHelper_4 mixinhelper_41, float value2, float value3) {
         RenderItemBridge bridge5_194 = ItemCounterElement.this.mc.bridge$getRenderItem();
         mixinhelper_41.method44(arg0 -> arg0.method29().method33());
         float value5 = bridge5_194.bridge$getZLevel();
         ItemCounterElement.this.renderer
            .method1(mixinhelper_41, bridge5_194, value2, value3, (ItemCounterTextSide)ItemCounterElement.this.textSide.get(), (Boolean)ItemCounterElement.this.renderItemIcon.get());
         mixinhelper_41.method44(arg0 -> arg0.method29().method33());
         bridge5_194.bridge$setZLevel(value5);
      }

      public boolean shouldRender(boolean flag1) {
         if ((Boolean)ItemCounterElement.this.getParentCounter().method14().get()) {
            this.getParentCounter(0.0F, 0.0F);
            return false;
         } else {
            return (Boolean)ItemCounterElement.this.background.get()
               || ItemCounterElement.this.textSide.get() != ItemCounterTextSide.NONE
               || (Boolean)ItemCounterElement.this.renderItemIcon.get();
         }
      }

      public float getScale() {
         float value1 = super.getScale();
         return value1 == 1.0F ? (Float)ItemCounterElement.this.getParentCounter().method13().get() : value1;
      }
   }
}
