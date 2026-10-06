package com.moonsworth.lunar.client.framework.feature.scrollabletooltips;

import com.moonsworth.lunar.bridge.Bridge2_27;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge5_17;
import com.moonsworth.lunar.bridge.Bridge7_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.ScrollabletooltipsHandler;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.ScrollabletooltipsHandler2;
import com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.ScrollabletooltipsHandler3;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent.Data;
import com.moonsworth.lunar.client.event.render.TooltipRenderEvent.TooltipPreRenderEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScrollLegacy;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump95;
import java.util.List;
import lombok.Generated;

public class Scrollabletooltips2 {
   private final ToggleOption field1 = (ToggleOption)OptionFactory.method7("tooltipFreeScroll").method31();
   private final ToggleOption field2 = (ToggleOption)OptionFactory.method7("lineShiftMode").method31();
   private final Scrollabletooltips field3 = new Scrollabletooltips();
   private final ThreadModuleDump95 field4 = new ThreadModuleDump95();
   private final List<com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.Scrollabletooltips> field5 = List.of(
      new ScrollabletooltipsHandler2(), new ScrollabletooltipsHandler(), new ScrollabletooltipsHandler3()
   );
   private ItemStackBridge field6;
   private int field7;
   private boolean field8;
   private boolean field9;
   private int field10;

   public Scrollabletooltips2() {
      this.field1.CICORRHIOIIOORRRICCORIOIOCIHII(var1 -> {
         if (var1) {
            this.field2.method10(false);
         }
      });
      this.field2.CICORRHIOIIOORRRICCORIOIOCIHII(var1 -> {
         if (var1) {
            this.field1.method10(false);
         }
      });
   }

   public void method1(EventMouseScrollLegacy var1) {
      if (!var1.isCancelled() && this.field6 != null) {
         if (!this.method4() && !this.method3()) {
            ScrollabletooltipsType var2 = ThreadModuleDump63.method4().method40().method46().method13();
            if (this.method5(var2)) {
               boolean var3 = var1.method1() > 0.0;
               double var4 = var1.method1() * this.method6();
               switch (var2) {
                  case VERTICAL:
                     if ((Boolean)this.field2.get()) {
                        this.field10 = var4 > 0.0 ? this.field10 - 1 : this.field10 + 1;
                        this.field9 = true;
                     } else {
                        this.field3.method3(var4);
                     }
                     break;
                  case HORIZONTAL:
                     this.field3.method2(var4);
                     break;
                  case SCALE:
                     double var6 = 1.0 + 0.05 * Math.abs(var1.method1());
                     double var8 = var3 ? var6 : 1.0 / var6;
                     this.field3.method4(var8);
               }
            }
         }
      }
   }

   public void method2(ScrollableTooltips var1, Data var2) {
      for (com.moonsworth.lunar.client.framework.feature.scrollabletooltips.mixin.Scrollabletooltips var4 : this.field5) {
         if (var4.method1(var1, this)) {
            var4.method2(var2, this);
         }
      }

      this.field8 = true;
      this.field9 = false;
   }

   private boolean method3() {
      return !(ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension_3 var1)
         ? true
         : var1 instanceof Bridge2_27 || var1 instanceof Bridge5_17;
   }

   public boolean method4() {
      return ThreadModuleDump63.MC_VERSION >= 8 && this.field6 != null ? this.field6.bridge$getItem() instanceof Bridge7_4 : false;
   }

   private boolean method5(ScrollabletooltipsType var1) {
      if ((Boolean)this.field1.get()) {
         return true;
      }

      return switch (var1) {
         case VERTICAL -> this.field2.get() || this.field4.getHeight() > LcuiScreen.method151().getScaledHeight();
         case HORIZONTAL -> this.field4.getWidth() > LcuiScreen.method151().getScaledWidth();
         case SCALE -> true;
      };
   }

   private int method6() {
      return ThreadModuleDump63.method3().bridge$displayHeight() / 100;
   }

   public void method7(TooltipPreRenderEvent var1) {
      ItemStackBridge var2 = (ItemStackBridge)var1.method1().orElse(null);
      if (!this.method8(var2, this.field6)) {
         this.reset();
      }

      this.field6 = var2;
      this.field7 = this.method9();
   }

   private void reset() {
      this.field8 = false;
      this.field10 = 0;
      this.field3.method1(0, 0);
      this.field4.method1();
   }

   private boolean method8(ItemStackBridge var1, ItemStackBridge var2) {
      if (var1 == null || var2 == null) {
         return false;
      } else if (var1 == var2) {
         return true;
      } else if (var1.bridge$getItem() != var2.bridge$getItem()) {
         return false;
      } else {
         return this.field7 == -1 && this.method9() == -1 ? var1.bridge$getItem() == var2.bridge$getItem() : this.method9() == this.field7;
      }
   }

   private int method9() {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension_3 var1) {
         Bridge3_18 var3 = var1.bridge$getHoveredSlot();
         return var3 == null ? -1 : var3.bridge$getIndex();
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
   public ThreadModuleDump95 method13() {
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
   public void method19(int var1) {
      this.field10 = var1;
   }
}
