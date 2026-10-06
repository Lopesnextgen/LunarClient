package com.moonsworth.lunar.client.mod.render.shinypots;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import lombok.Generated;

public class ShinyPots extends AbstractFeature {
   private final HashMap<Integer, Integer> field8 = new HashMap<>();
   private final HashMap<String, Integer> field9 = new HashMap<>();
   private final ToggleOption field10 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("coloredPotions")
      .method31();
   private final ToggleOption field11 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("renderGlintBehindPotion")
      .method31();
   private final ToggleOption field12 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("renderEntireSlot")
      .method31();
   private final EnumOption<ShinyPots.Type> field13 = (EnumOption<ShinyPots.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "glintShape", ShinyPots.Type.SQUARE
      )
      .method31();

   public ShinyPots() {
      super(false);
   }

   public String getId() {
      return "SHINY_POTS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field10});
         arg1x.method9(new ClientOption[]{this.field13}).HOCIIROHCHHIORICCRHIIRIIRCRCOR();
         arg1x.method9(new ClientOption[]{this.field11}).method2(new int[]{1});
         arg1x.method9(new ClientOption[]{this.field12}).OHHOOIIHIRCCCRCRRRCIICIHOOIRRH();
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method11(this);
   }

   public int method3(int[] items1) {
      if (!(Boolean)this.field10.get()) {
         return -8372020;
      } else {
         return items1.length == 0 ? 0 : items1[0];
      }
   }

   public int method4(ItemStackBridge bridgeextension_41) {
      if (!(Boolean)this.field10.get()) {
         return -8372020;
      }

      if (Ref.MC_VERSION >= 28) {
         throw new UnsupportedOperationException("Use the Layer States for color in 1.21.4+!");
      }

      if (Ref.MC_VERSION >= 5) {
         String text4 = bridgeextension_41.bridge$getDisplayName();
         Integer number5 = this.field9.get(text4);
         if (number5 == null) {
            number5 = Bridge.method28().method9().bridge$getColorFromItemStack(bridgeextension_41, 0) | 0xFF000000;
            this.field9.put(text4, number5);
         }

         return number5;
      } else {
         int index2 = bridgeextension_41.bridge$getItemDamage();
         Integer number3 = this.field8.get(index2);
         if (number3 == null) {
            number3 = Bridge.method28().method9().bridge$getColorFromItemStack(bridgeextension_41, 0) | 0xFF000000;
            this.field8.put(index2, number3);
         }

         return number3;
      }
   }

   public boolean method13() {
      return this.field13.get() == ShinyPots.Type.SQUARE;
   }

   @Generated
   public ToggleOption method14() {
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
   public EnumOption<ShinyPots.Type> method17() {
      return this.field13;
   }

   public enum Type implements OptionEnumValue {
      SQUARE("squareGlint"),
      BOTTLE("bottleGlint");

      private final String id;

      public String id() {
         return this.name();
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
