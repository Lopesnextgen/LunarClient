package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.Animation;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.ui.AnimationTimer;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class ExpandableListOptionWidget extends ListOptionWidget<ToggleOption> implements WidgetHooks {
   private static final ResourceLocationBridge field19 = ResourceLocationBridge.create("lunar", "icons/settings/arrow-right-18x18.png");
   private final AnimationTimer field20;
   private final HoverAnimation field21 = new HoverAnimation(100L);

   public ExpandableListOptionWidget(ToggleOption var1, GuiWidget var2) {
      super(var1, var2);
      this.field21.start();
      this.field20 = new Animation(200L);
      this.field20.start();
   }

   @Override
   protected boolean method7(MarkerModel.Data2 var1) {
      if (var1.method9() < this.x + 32.0F) {
         this.field21.start();
         boolean var2 = !(Boolean)this.option.get();
         this.option.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2);
         this.field20.start();
         this.option.method3(var2);
      } else {
         this.field20.start();
         this.option.method3(!this.option.method9());
      }

      return super.method7(var1);
   }

   public void method2(boolean var1) {
      this.option.method3(var1);
   }

   @Override
   public boolean method1(MarkerModel.Data2 var1) {
      return this.getOption().isHidden() ? false : super.method11(var1);
   }

   @Override
   protected float getHeight2() {
      return 14.0F;
   }

   @Override
   protected float method8() {
      return 4.0F;
   }

   @Override
   protected float method9() {
      return 3.0F;
   }

   @Override
   protected float method10() {
      return -10.0F;
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : (this.option.method9() ? this.height + 7.0F : this.getHeight2());
   }

   @Override
   public boolean method3() {
      return !this.option.method9();
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = 36.0F;
      float var5 = 11.0F;
      float var6 = this.option.method9() ? this.width / 2.0F : this.width;
      String var7 = this.option.getName();
      float var8 = var6 - (var4 + var5 + 1.0F);
      if (FontRegistry.method14().method4(var7) > var8) {
         if (FontRegistry.method12().method4(var7) > var8) {
            FontRegistry.method10().method13(var1, var7, this.x + var4, this.y + 2.5F, -4079426);
         } else {
            FontRegistry.method12().method13(var1, var7, this.x + var4, this.y + 2.0F, -4079426);
         }
      } else {
         FontRegistry.method14().method13(var1, var7, this.x + var4, this.y + 1.5F, -4079426);
      }

      LcuiScreen.method101(
         var1,
         this.x,
         this.y + 2.0F,
         30.0F,
         10.0F,
         5.0F,
         var3 && this.method1(var2) && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + 20.0F && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() < this.x + 34.0F
            ? 1088611042
            : 551740130,
         true,
         true,
         true,
         true
      );
      LcuiScreen.method51(var1, this.x + 1.0F, this.y + 3.0F, 28.0F, 8.0F, 2.5F, 905969663, true, true, true, true);
      boolean var9 = (Boolean)this.option.get();
      float var10 = 10.0F;
      float var11 = var9 ? this.field21.method9() : 1.0F - this.field21.method9();
      LcuiScreen.method101(var1, this.x + var10 * var11, this.y + 2.0F, 20.0F, 10.0F, 5.0F, var9 ? -1356212614 : -1344396974, true, true, true, true);
      LcuiScreen.method51(var1, this.x + 1.0F + var10 * var11, this.y + 3.0F, 18.0F, 8.0F, 2.5F, 905969663, true, true, true, true);
      FontRegistry.method9()
         .method14(
            var1, this.method5(this.option.get() ? "on" : "off", new Object[0]), this.x + var10 * var11 + 10.0F, this.y + 3.0F, -1
         );
      boolean var12 = var3
         && this.method1(var2)
         && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + 20.0F
         && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() > this.x + 34.0F;
      float var13 = this.option.method9() ? this.field20.method9() : 1.0F - this.field20.method9();
      var1.push();
      var1.method38(this.x + var6 - var5, this.y + 7.0F, 1.0F);
      var1.method42(90.0F * var13);
      LcuiScreen.method31(var1, field19, -4.0F, -4.0F, 8.0F, 8.0F, var12 ? -1 : -1711276033);
      var1.pop();
      if (this.option.method9()) {
         LcuiScreen.method117(var1, this.x, this.y + this.getHeight2() + 1.0F, this.width - 2.0F, this.height + 6.0F - this.getHeight2(), 6.0F, 548450480);
         this.method3(var1, var2);
      }

      super.method3(var1, var2, var3);
   }
}
