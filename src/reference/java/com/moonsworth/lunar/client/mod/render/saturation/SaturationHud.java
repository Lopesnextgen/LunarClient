package com.moonsworth.lunar.client.mod.render.saturation;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.framework.Ref;

public class SaturationHud extends AbstractFeature {
   public SaturationHud(Framework7Extension framework7extension1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(framework7extension1));
      this.method45(
         ModTraits.field1,
         TypedHudRenderer.method23(
            0.0F, 0.0F, HudAnchor.TOP_RIGHT, HudSize.method1(10, 18, 22, 40, 56, 62), this::method2, HudConditionSet.method5().method3(false).method8()
         )
      );
   }

   public String getId() {
      return "SATURATION_HUD_CHILD";
   }

   private String method2(boolean flag1) {
      if (flag1) {
         return "0";
      } else {
         return Ref.method7() != null && Ref.method7().bridge$getFoodStats() != null
            ? (int)Math.ceil(Ref.method7().bridge$getFoodStats().bridge$getSaturationLevel()) + ""
            : null;
      }
   }
}
