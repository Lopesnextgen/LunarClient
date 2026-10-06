package com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.ScrollableTooltipsController;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.TooltipRender;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;
import com.moonsworth.lunar.client.ui.GuiResolution;

public interface Scrollabletooltips {
   boolean method1(ScrollableTooltips scrollabletooltips1, ScrollableTooltipsController scrollabletooltips22);

   void method2(TooltipRender data1, ScrollableTooltipsController scrollabletooltips22);

   default void method3(TooltipRender data1, ScrollableTooltipsController scrollabletooltips22, double value3, double value5) {
      GuiResolution threadmoduledump717 = LcuiScreen.method151();
      com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips scrollabletooltips8 = scrollabletooltips22.method12();
      double value9 = scrollabletooltips8.method9().getValue();
      double value11 = scrollabletooltips8.method5(value3, data1.getWidth() * value9, threadmoduledump717.getScaledWidth());
      double value13 = scrollabletooltips8.method5(value5, data1.getHeight() * value9, threadmoduledump717.getScaledHeight());
      if (value3 != value11) {
         scrollabletooltips8.method7().method1(-data1.getX() + value11, 0L);
      }

      if (value5 != value13) {
         scrollabletooltips8.method8().method1(-data1.getY() + value13, 0L);
      }
   }
}
