package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.GuiResolution;
import java.util.List;

public class HudDebug extends AbstractFeature {
   public HudDebug() {
      super(false);
      this.method2(
         ModTraits.field1,
         TypedHudRenderer.method24(
            0.0F,
            0.0F,
            HudAnchor.BOTTOM_RIGHT,
            true,
            HudSize.method1(1, 100, 500, 1, 360, 1000),
            (arg1, arg2) -> List.of(
               String.format("AbsX: %.2f - AbsY: %.2f", arg1.ICRIHRIORRCRCOOCCCHHRIRICCHHII(), arg1.RIIIOCHHCIHOIOROOOHRIRICCCCHHC()),
               String.format(
                  "X: %.2f - Y: %,2f - Scale: %.2f - MC_Scale: %d", arg1.getX(), arg1.getY(), arg1.getScale(), new GuiResolution(this.mc).method3()
               ),
               String.format(
                  "Width: %.2f - Height: %.2f - ScaledWidth: %.2f - ScaledHeight: %.2f",
                  arg1.getWidth(),
                  arg1.getHeight(),
                  arg1.OIIRIIOICHRIRIRROIOORCHCHROCCI(),
                  arg1.IHIIHRCCCORCOOIHICRRIOIRHOROCR()
               ),
               String.format(
                  "LCUI_ScaleFactor: %d - LCUI_ScaledHeight: %d - LCUI_Scale: %.2f",
                  LcuiScreen.method151().method3(),
                  LcuiScreen.method151().getScaledHeight(),
                  LcuiScreen.getScale()
               )
            )
         )
      );
   }

   public String getId() {
      return "HUD_DEBUG";
   }

   protected ModDetails createDetails() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }
}
