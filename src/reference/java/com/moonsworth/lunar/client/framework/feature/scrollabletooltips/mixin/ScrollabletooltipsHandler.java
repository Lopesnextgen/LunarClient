package com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.ScrollableTooltipsController;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.TooltipRender;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;
import com.moonsworth.lunar.client.ui.GuiResolution;

public class ScrollabletooltipsHandler implements Scrollabletooltips {
   public ScrollabletooltipsHandler() {
   }

   @Override
   public boolean method1(ScrollableTooltips scrollabletooltips1, ScrollableTooltipsController scrollabletooltips22) {
      return scrollabletooltips22.method17() ? true : !scrollabletooltips22.method16() && (Boolean)scrollabletooltips1.getStartAtTop().get();
   }

   @Override
   public void method2(TooltipRender data1, ScrollableTooltipsController scrollabletooltips22) {
      GuiResolution threadmoduledump713 = LcuiScreen.method151();
      com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips scrollabletooltips4 = scrollabletooltips22.method12();
      double value5 = scrollabletooltips4.method9().getValue();
      double value7 = data1.getWidth() * value5;
      double value9 = data1.getHeight() * value5;
      if (value7 > threadmoduledump713.getScaledWidth()) {
         scrollabletooltips4.method7().method1(-data1.getX() + scrollabletooltips4.method6(), 0L);
      }

      if (value9 > threadmoduledump713.getScaledHeight()) {
         scrollabletooltips4.method8().method1(-data1.getY() + scrollabletooltips4.method6(), 0L);
      }
   }
}
