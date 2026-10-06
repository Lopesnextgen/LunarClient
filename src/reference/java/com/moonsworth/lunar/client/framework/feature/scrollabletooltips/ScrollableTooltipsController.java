package com.moonsworth.lunar.client.framework.feature.scrollabletooltips;

import com.moonsworth.lunar.bridge.GuiContainerCreativeBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5_17;
import com.moonsworth.lunar.bridge.Bridge7_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.ScrollabletooltipsHandler;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.ScaleTooltipScroll;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.BasicTooltipScroll;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.TooltipRender;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScroll;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.DoubleRectangle;
import java.util.List;
import lombok.Generated;

public class ScrollableTooltipsController {
   private final ToggleOption field1 = (ToggleOption)OptionFactory.method7("tooltipFreeScroll").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field2 = (ToggleOption)OptionFactory.method7("lineShiftMode").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final Scrollabletooltips field3 = new Scrollabletooltips();
   private final DoubleRectangle field4 = new DoubleRectangle();
   private final List<com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.Scrollabletooltips> field5 = List.of(
      new ScaleTooltipScroll(), new ScrollabletooltipsHandler(), new BasicTooltipScroll()
   );
   private ItemStackBridge field6;
   private int field7;
   private boolean field8;
   private boolean field9;
   private int field10;

   public ScrollableTooltipsController() {
      this.field1.CICORRHIOIIOORRRICCORIOIOCIHII(arg1 -> {
         if (arg1) {
            this.field2.method10(false);
         }
      });
      this.field2.CICORRHIOIIOORRRICCORIOIOCIHII(arg1 -> {
         if (arg1) {
            this.field1.method10(false);
         }
      });
   }

   public void method1(EventMouseScroll highlightimpl41) {
      if (!highlightimpl41.isCancelled() && this.field6 != null) {
         if (!this.method4() && !this.method3()) {
            ScrollabletooltipsType scrollabletooltipstype2 = Ref.method4().method40().method46().method13();
            if (this.method5(scrollabletooltipstype2)) {
               boolean flag3 = highlightimpl41.method1() > 0.0;
               double value4 = highlightimpl41.method1() * this.method6();
               switch (scrollabletooltipstype2) {
                  case VERTICAL:
                     if ((Boolean)this.field2.get()) {
                        this.field10 = value4 > 0.0 ? this.field10 - 1 : this.field10 + 1;
                        this.field9 = true;
                     } else {
                        this.field3.method3(value4);
                     }
                     break;
                  case HORIZONTAL:
                     this.field3.method2(value4);
                     break;
                  case SCALE:
                     double value6 = 1.0 + 0.05 * Math.abs(highlightimpl41.method1());
                     double value8 = flag3 ? value6 : 1.0 / value6;
                     this.field3.method4(value8);
               }
            }
         }
      }
   }

   public void method2(ScrollableTooltips scrollabletooltips1, TooltipRender data2) {
      for (com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.Scrollabletooltips scrollabletooltips4 : this.field5) {
         if (scrollabletooltips4.method1(scrollabletooltips1, this)) {
            scrollabletooltips4.method2(data2, this);
         }
      }

      this.field8 = true;
      this.field9 = false;
   }

   private boolean method3() {
      return !(Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_31)
         ? true
         : bridge5extension_31 instanceof GuiContainerCreativeBridge || bridge5extension_31 instanceof Bridge5_17;
   }

   public boolean method4() {
      return Ref.MC_VERSION >= 8 && this.field6 != null ? this.field6.bridge$getItem() instanceof Bridge7_4 : false;
   }

   private boolean method5(ScrollabletooltipsType scrollabletooltipstype1) {
      if ((Boolean)this.field1.get()) {
         return true;
      }

      return switch (scrollabletooltipstype1) {
         case VERTICAL -> this.field2.get() || this.field4.getHeight() > LcuiScreen.method151().getScaledHeight();
         case HORIZONTAL -> this.field4.getWidth() > LcuiScreen.method151().getScaledWidth();
         case SCALE -> true;
      };
   }

   private int method6() {
      return Ref.method3().bridge$displayHeight() / 100;
   }

   public void method7(EventRenderTooltipPre data21) {
      ItemStackBridge bridgeextension_42 = (ItemStackBridge)data21.HHCOCHOIIIOOROCORRRRORORIOOHRC().orElse(null);
      if (!this.method8(bridgeextension_42, this.field6)) {
         this.reset();
      }

      this.field6 = bridgeextension_42;
      this.field7 = this.method9();
   }

   private void reset() {
      this.field8 = false;
      this.field10 = 0;
      this.field3.method1(0, 0);
      this.field4.method1();
   }

   private boolean method8(ItemStackBridge bridgeextension_41, ItemStackBridge bridgeextension_42) {
      if (bridgeextension_41 == null || bridgeextension_42 == null) {
         return false;
      } else if (bridgeextension_41 == bridgeextension_42) {
         return true;
      } else if (bridgeextension_41.bridge$getItem() != bridgeextension_42.bridge$getItem()) {
         return false;
      } else {
         return this.field7 == -1 && this.method9() == -1 ? bridgeextension_41.bridge$getItem() == bridgeextension_42.bridge$getItem() : this.method9() == this.field7;
      }
   }

   private int method9() {
      if (Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_31) {
         SlotBridge bridge3_183 = bridge5extension_31.bridge$getHoveredSlot();
         return bridge3_183 == null ? -1 : bridge3_183.bridge$getIndex();
      } else {
         return -1;
      }
   }

   @Generated
   public ToggleOption method10() {
      return this.field1;
   }

   @Generated
   public ToggleOption method11() {
      return this.field2;
   }

   @Generated
   public Scrollabletooltips method12() {
      return this.field3;
   }

   @Generated
   public DoubleRectangle method13() {
      return this.field4;
   }

   @Generated
   public List<com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.Scrollabletooltips> getTransformers() {
      return this.field5;
   }

   @Generated
   public ItemStackBridge method14() {
      return this.field6;
   }

   @Generated
   public int method15() {
      return this.field7;
   }

   @Generated
   public boolean method16() {
      return this.field8;
   }

   @Generated
   public boolean method17() {
      return this.field9;
   }

   @Generated
   public int method18() {
      return this.field10;
   }

   @Generated
   public void method19(int number1) {
      this.field10 = number1;
   }
}
