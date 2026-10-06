package com.moonsworth.lunar.client.mod.misc.hypixel;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.TpsTracker;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.mod.misc.hypixel.HypixelMods;
import com.moonsworth.lunar.client.config.option.ConstantName;
import org.jetbrains.annotations.Nullable;

public class HypixelTps extends AbstractFeature {
   private final HypixelLocationListener locationListener = (HypixelLocationListener)this.method63(HypixelLocationListener.class);
   private final TpsTracker tpsTracker = (TpsTracker)this.method63(TpsTracker.class);
   private final IntegerOption rollingAverageWindow = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "rollingAverageWindow"
            )
            .method4(10))
         .method7(1, 30))
      .method31();
   private String tpsText = "";

   public HypixelTps(HypixelMods hypixelmod1) {
      super(false);
      this.registerOptions(ModTraits.field16, ChildModBinding.method3(hypixelmod1));
      this.registerOptions(ModTraits.field1, new HypixelTps.Data(0.0F, 0.0F, HudAnchor.BOTTOM_CENTER_L));
      this.handle(EventSecond.class, this::updateTps);
   }

   private void updateTps(EventSecond highlightimpl41) {
      this.tpsText = this.tpsTracker.method2((Integer)this.rollingAverageWindow.get());
   }

   @ConstantName
   public String getId() {
      return "HYPIXEL_TPS";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.rollingAverageWindow});
   }

   private class Data extends TypedHudRenderer<String> {
      private static final String DEFAULT_TPS_TEXT = "TPS: 20.0";

      public Data(float value2, float value3, HudAnchor gui2extension24) {
         super(value2, value3, gui2extension24);
      }

      public HudSize getSize() {
         return new HudSize(10, 18, 30, 50, 100, 200);
      }

      @Nullable
      public String registerOptions(boolean flag1) {
         return flag1 ? "TPS: 20.0" : HypixelTps.this.tpsText;
      }

      public boolean shouldRender(boolean flag1) {
         return HypixelTps.this.locationListener.method7().method2() ? false : super.shouldRender(flag1);
      }
   }
}
