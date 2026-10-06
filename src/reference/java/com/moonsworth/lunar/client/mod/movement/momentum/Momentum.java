package com.moonsworth.lunar.client.mod.movement.momentum;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.momentum.MomentumRounding;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import org.joml.Math;

public class Momentum extends AbstractFeature {
   private static final double field8 = 0.05F;
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("useGroundSpeed").method31();
   private final EnumOption<MomentumRounding> field10 = (EnumOption<MomentumRounding>)OptionFactory.method10("rounding", MomentumRounding.DECIMAL_1)
      .method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("useAverageVelocity").method31();
   private final IntegerOption field12 = (IntegerOption)((Data)((Data)OptionFactory.method4("averagingPeriod").method4(8))
         .method7(1, 50))
      .method31();
   private long field13;
   private double field14;

   public Momentum() {
      super(false);
      this.method2(ModTraits.field1, TypedHudRenderer.method22(0.0F, 0.0F, HudAnchor.TOP_LEFT, HudSize.method1(10, 18, 22, 50, 56, 62), this::method4));
   }

   public String getId() {
      return "MOMENTUM";
   }

   protected String method18() {
      return "[0 m/s]";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.SETTINGS, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field9});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, arg1xx -> arg1xx.method9(new ClientOption[]{this.field12}));
         arg1x.method9(new ClientOption[]{this.field10});
      });
   }

   private String method4(boolean flag1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      double value3;
      double value5;
      double value7;
      if (bridge5extension_52 != null) {
         value3 = (bridge5extension_52.bridge$getPosX() - bridge5extension_52.bridge$lastTickX()) / 0.05F;
         value5 = (bridge5extension_52.bridge$getPosY() - bridge5extension_52.bridge$lastTickY()) / 0.05F;
         value7 = (bridge5extension_52.bridge$getPosZ() - bridge5extension_52.bridge$lastTickZ()) / 0.05F;
      } else {
         value3 = 0.0;
         value5 = 0.0;
         value7 = 0.0;
      }

      double value9;
      if ((Boolean)this.field9.get()) {
         value9 = Math.sqrt(value3 * value3 + value7 * value7);
      } else {
         value9 = Math.sqrt(value3 * value3 + value5 * value5 + value7 * value7);
      }

      if (!(Boolean)this.field11.get()) {
         return ((MomentumRounding)this.field10.get()).format(value9) + " m/s";
      }

      double value11 = 1.0 / ((Integer)this.field12.get()).intValue() * 1000.0;
      if (Ref.method14() - this.field13 >= value11) {
         this.field14 = (this.field14 + value9) / 2.0;
         this.field13 = Ref.method14();
      }

      return ((MomentumRounding)this.field10.get()).format(this.field14) + " m/s";
   }
}
