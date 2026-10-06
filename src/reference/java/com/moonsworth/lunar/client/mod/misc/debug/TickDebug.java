package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;

public class TickDebug extends AbstractFeature {
   public TickDebug() {
      super(false);
      this.method45(
         ModTraits.field1,
         TypedHudRenderer.method22(
            0.0F,
            0.0F,
            HudAnchor.TOP_LEFT,
            HudSize.method1(10, 18, 22, 50, 56, 62),
            arg1 -> (String)this.method1("tick", String.valueOf(EventTick.field1))
         )
      );
   }

   public String getId() {
      return "TICK_DEBUG";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method8().method11(this);
   }
}
