package com.moonsworth.lunar.client.mod.hud.fps;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;

public class Fps extends AbstractFeature {
   private final ToggleOption reverseOrder = (ToggleOption)OptionFactory.method7("reverseOrder").method31();

   public Fps() {
      super(false);
      this.registerOptions(
         ModTraits.field1,
         TypedHudRenderer.method22(
            0.0F,
            0.0F,
            HudAnchor.TOP_LEFT,
            HudSize.method1(10, 18, 22, 50, 56, 62),
            arg1 -> this.method14(
               this.reverseOrder.get() ? "reverse" : "fps", new Object[]{this.method1("fps", this.mc.bridge$getDebugFPS())}
            )
         )
      );
   }

   public String getId() {
      return "FPS";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.reverseOrder});
   }
}
