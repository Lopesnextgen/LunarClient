package com.moonsworth.lunar.client.mod.render.itemcustomizer;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.util.math.MathUtils;

public class HeldItemAnimations extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("heldItemScaleSwing")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("customHeldItemSwingSpeed")
      .method31();
   private final FloatOption field10 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("heldItemSwingSpeed")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.0F, 2.0F))
      .method31();
   private final ToggleOption field11 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("heldItemIgnoreHaste")
      .method31();
   private final ToggleOption field12 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("heldItemIgnoreMiningFatigue")
      .method31();
   private final ToggleOption field13 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("heldItemRotationlessDrink")
      .method31();
   private final ToggleOption field14 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("cancelReEquip")
      .method31();

   public HeldItemAnimations(ItemCustomizer itemcustomizer1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(itemcustomizer1));
   }

   public String getId() {
      return "HELD_ITEM_ANIMATIONS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field11, this.field12, this.field13, this.field14});
      lightingextension231.method7(this.field9, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10}));
   }

   public boolean method13() {
      return this.isEnabled() && (Boolean)this.field8.get();
   }

   public boolean method14() {
      return this.isEnabled() && (Boolean)this.field11.get();
   }

   public boolean method15() {
      return this.isEnabled() && (Boolean)this.field12.get();
   }

   public boolean method16() {
      return this.isEnabled() && (Boolean)this.field13.get();
   }

   public boolean method17() {
      return this.isEnabled() && (Boolean)this.field14.get();
   }

   public float method19() {
      return !this.field9.get() ? 1.0F : MathUtils.method16((Float)this.field10.get(), 0.0F, 2.0F, 4.0F, 0.5F);
   }
}
