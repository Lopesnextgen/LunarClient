package com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin;

import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.ScrollableTooltipsController;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.TooltipRender;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;

public class BasicTooltipScroll implements Scrollabletooltips {
   public BasicTooltipScroll() {
   }

   @Override
   public boolean method1(ScrollableTooltips scrollabletooltips1, ScrollableTooltipsController scrollabletooltips22) {
      return !scrollabletooltips22.method16() || !(Boolean)scrollabletooltips22.method10().get();
   }

   @Override
   public void method2(TooltipRender data1, ScrollableTooltipsController scrollabletooltips22) {
      com.moonsworth.lunar.client.framework.feature.scrollabletooltips.Scrollabletooltips scrollabletooltips3 = scrollabletooltips22.method12();
      double value4 = data1.getX() + scrollabletooltips3.method7().getValue();
      double value6 = data1.getY() + scrollabletooltips3.method8().getValue();
      this.method1(data1, scrollabletooltips22, value4, value6);
   }
}
