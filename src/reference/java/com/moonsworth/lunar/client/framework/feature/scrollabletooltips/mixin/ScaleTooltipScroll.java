package com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin;

import com.moonsworth.lunar.client.util.math.Direction2D;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.ScrollableTooltipsController;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.TooltipRender;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;

public class ScaleTooltipScroll implements Scrollabletooltips {
   public ScaleTooltipScroll() {
   }

   @Override
   public boolean method1(ScrollableTooltips scrollabletooltips1, ScrollableTooltipsController scrollabletooltips22) {
      return scrollabletooltips22.method12().method9().method3();
   }

   @Override
   public void method2(TooltipRender data1, ScrollableTooltipsController scrollabletooltips22) {
      com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips scrollabletooltips3 = scrollabletooltips22.method12();
      boolean flag4 = scrollabletooltips3.method9().method4() == Direction2D.UP;
      int number5 = data1.getX();
      int number6 = data1.getY();
      double value7 = flag4 ? number5 + scrollabletooltips3.method7().getValue() : number5;
      double value9 = flag4 ? number6 + scrollabletooltips3.method8().getValue() : number6;
      this.method1(data1, scrollabletooltips22, value7, value9);
   }
}
