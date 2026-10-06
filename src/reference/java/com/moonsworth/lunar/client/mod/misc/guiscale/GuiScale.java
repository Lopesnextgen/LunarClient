package com.moonsworth.lunar.client.mod.misc.guiscale;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModRestriction;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.render.EventRenderScale;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import lombok.Generated;

public class GuiScale extends AbstractFeature {
   private final HypixelLocationListener field8 = (HypixelLocationListener)this.method63(HypixelLocationListener.class);
   private final FloatOption field9 = (FloatOption)((Data)((Data)OptionFactory.method2("hotbarScale").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.25F, 2.0F))
      .method31();
   private final IntegerOption field10 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "inventoryScale"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(1, 5))
      .method31();

   public GuiScale() {
      super(false);
      this.handle(EventRenderScale.class, this::method4);
      this.method2(EventScreenChange.class, this::method5, 300);
      this.method2(ModTraits.field20, ModRestriction.method2(this, () -> {
         String text1 = this.field8.method7().field3;
         return text1 != null && text1.startsWith("RAVENGARD");
      }));
   }

   public String getId() {
      return "GUI_SCALE";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field9})).method6(1);
      lightingextension231.method9(new ClientOption[]{this.field10});
   }

   public boolean method2(GuiScreenBridge bridge5extension61) {
      return this.isEnabled() && bridge5extension61 instanceof GuiContainerBridge;
   }

   public double method3(GuiScreenBridge bridge5extension61) {
      int number2 = bridge5extension61 == null ? 0 : bridge5extension61.bridge$getInventoryScale();
      if (number2 <= 0) {
         return 1.0;
      }

      int number3 = (int)(LcuiScreen.method151().method3() / LcuiScreen.method20());
      return number3 <= 0 ? 1.0 : (double)number2 / number3;
   }

   private void method4(EventRenderScale highlightimpl_21) {
      highlightimpl_21.setScale((Float)this.field9.get());
   }

   private void method5(EventScreenChange highlightimpl71) {
      GuiScreenBridge bridge5extension62 = highlightimpl71.method1();
      if (bridge5extension62 instanceof GuiContainerBridge) {
         bridge5extension62.bridge$setInventoryScale((Integer)this.field10.get());
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3, ModCategory.field4}).method11(this);
   }

   @Generated
   public HypixelLocationListener method13() {
      return this.field8;
   }

   @Generated
   public FloatOption method14() {
      return this.field9;
   }

   @Generated
   public IntegerOption method15() {
      return this.field10;
   }
}
