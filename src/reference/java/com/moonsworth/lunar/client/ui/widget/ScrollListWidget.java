package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.DropdownWidget;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import lombok.Generated;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class ScrollListWidget<T>
   extends com.moonsworth.lunar.client.ui.widget.OptionWidget<ListOption<T>>
   implements EditState {
   private static final float field16 = 140.0F;
   private static final float field17 = 5.0F;
   private static final float field18 = 12.0F;
   private static final float field19 = 10.0F;
   private final List<TextButtonWidget> field20 = new ArrayList<>();
   private final DropdownWidget field21 = new DropdownWidget(this);

   public ScrollListWidget(ListOption<T> var1, GuiWidget var2) {
      super(var1, var2);
      this.option.CICORRHIOIIOORRRICCORIOIOCIHII(var1x -> this.method1());
      this.method1();
   }

   private void method1() {
      this.field20.clear();
      List var1 = (List)this.option.get();
      Function var2 = this.option.method7();

      for (int var3 = 0; var3 < var1.size(); var3++) {
         int var4 = var3;
         Object var5 = var1.get(var4);
         TextButtonWidget var6 = new TextButtonWidget(this, var2 == null ? var5.toString() : (String)var2.apply(var5));
         var6.method4(() -> this.method6(var4));
         var6.method5(() -> this.method7(var4));
         var6.setIsFirst(var4 == 0);
         var6.method7(var4 == var1.size() - 1);
         this.field20.add(var6);
      }

      this.method2(this.x, this.y, this.width, this.height);
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      this.method2(var1, var2, var3 - 5.0F, this.getHeight());
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      float var5 = var3 - 10.0F;
      this.field21.method2(var1 + var5 - 4.0F, var2, 4.0F, var4 - 4.0F);

      for (int var6 = 0; var6 < this.field20.size(); var6++) {
         this.field20.get(var6).method2(var1, var2 + var6 * 12.0F, var5, 12.0F);
      }
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : Math.min(140.0F, 12.0F * this.field20.size() + 2.0F);
   }

   @Override
   public boolean method3() {
      return true;
   }

   private void method6(int var1) {
      ArrayList var2 = new ArrayList((Collection)this.option.get());
      if (var1 > 0 && var1 < var2.size()) {
         Object var3 = var2.get(var1 - 1);
         var2.set(var1 - 1, var2.get(var1));
         var2.set(var1, var3);
         this.option.method1(var2);
      }
   }

   private void method7(int var1) {
      ArrayList var2 = new ArrayList((Collection)this.option.get());
      if (var1 >= 0 && var1 < var2.size() - 1) {
         Object var3 = var2.get(var1 + 1);
         var2.set(var1 + 1, var2.get(var1));
         var2.set(var1, var3);
         this.option.method1(var2);
      }
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      var1.push();
      LcuiScreen.method111(var1, this.x, this.y, this.width, this.height, 1.0F);
      this.field21.method15(12.0F * this.field20.size());
      this.field21.method5(var1, var2, var3);
      var1.pop();
      if (!this.option.isDefault()) {
         boolean var4 = var3
            && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.y
            && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 12.0F
            && var2.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x + this.width - 10.0F;
         int var5 = ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, var4 ? 0.9F : 0.65F);
         LcuiScreen.method31(var1, LcuiScreen.field1, this.x + this.width - 8.0F, this.y + 3.0F, 6.0F, 6.0F, var5);
      }

      for (TextButtonWidget var10 : this.field20) {
         float var6 = var10.getY();
         var10.setY(var6 + this.field21.method3());
         boolean var7 = var10.getY() + var10.getHeight() < this.field21.getY();
         boolean var8 = var10.getY() > this.y + this.height;
         if (!var7 && !var8) {
            var10.method3(var1, var2, true);
         }

         var10.setY(var6);
      }

      var1.push();
      this.field21.method7(var1, var2, var3 && var2.HHHCHORHIHRCOHIOICICICHCRRICCI() > this.x + this.width - 4.0F);
      LcuiScreen.method112(var1);
      var1.pop();
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field21.method5(var1);
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      if (var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.y
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 12.0F
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x + this.width - 10.0F) {
         this.method7();
         return true;
      }

      if (var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.field21.getY()
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.field21.getY() + this.field21.getHeight()) {
         if (var1.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.field21.getX()
            && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() <= this.field21.getX() + this.field21.getWidth()) {
            this.field21.method18(var1, var2);
         } else {
            MarkerModel.Data2 var3 = this.field21.method4(var1);

            for (TextButtonWidget var5 : this.field20) {
               if (var5.method3(var3) && var5.method6(var3, var2)) {
                  return true;
               }
            }
         }
      }

      return super.method18(var1, var2);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      for (TextButtonWidget var4 : this.field20) {
         var4.method4(var1, var2);
      }
   }

   @Override
   public void close() {
   }

   @Override
   public boolean isEditing() {
      return false;
   }

   @Generated
   public DropdownWidget method14() {
      return this.field21;
   }
}
