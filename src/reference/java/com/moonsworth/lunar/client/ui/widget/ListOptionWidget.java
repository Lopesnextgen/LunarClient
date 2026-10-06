package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public abstract class ListOptionWidget<O extends ClientOption<?>> extends OptionWidget<O> implements WidgetHooks, EditState {
   protected final List<OptionWidget<?>> field16;
   protected final List<OptionWidget<?>> field17;
   protected float height;
   protected String field18 = "";

   public ListOptionWidget(O var1, GuiWidget var2) {
      this((O)var1, var1.getChildren(), var2);
   }

   public ListOptionWidget(O var1, Collection<ClientOption<?>> var2, GuiWidget var3) {
      super((O)var1, var3);
      this.field16 = new ArrayList<>();

      for (ClientOption var5 : var2) {
         this.field16.add(var5.method18(this));
      }

      this.field17 = Collections.synchronizedList(new ArrayList<>());
      this.method4();
      this.method14((var1x, var2x) -> {
         for (GuiWidget var4 : this.field16) {
            if (var4.method1(var1x) && this.method18(var4, var1x) && var4.method7(var1x, var2x)) {
               if (this.method1(var1x, var2x, true)) {
                  LcuiScreen.method15();
                  this.method4();
               }

               return true;
            }
         }

         return false;
      });
      this.method4((var1x, var2x) -> {
         if (this.method9(var1x, var2x)) {
            return true;
         }

         for (OptionWidget var4 : this.field16) {
            if (!this.method24(var4) && var4.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1x) && var4.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var1x, var2x)) {
               if (this.method1(var1x, var2x, false)) {
                  LcuiScreen.method15();
                  this.method4();
               }

               return true;
            }
         }

         return false;
      });
      this.method11((var1x, var2x) -> {
         for (OptionWidget var4 : this.field16) {
            if (!this.method24(var4) && var4.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1x, var2x) && var4.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1x)) {
               if (this.method1(var1x, var2x, true)) {
                  this.method4();
               }

               return true;
            }
         }

         return false;
      });
   }

   protected boolean method1(MarkerModel.Data2 var1, int var2, boolean var3) {
      return true;
   }

   protected void method4() {
      float var1 = 0.0F;
      boolean var2 = false;
      float var3 = 0.0F;

      for (OptionWidget var5 : this.method25()) {
         boolean var6 = var5.method3();
         float var7 = var5.getHeight();
         if (var7 != 0.0F) {
            if (var6) {
               if (!var2) {
                  var2 = true;
                  var1 = var7;
               } else {
                  var2 = false;
                  var3 += Math.max(var1, var7);
                  var1 = 0.0F;
               }
            } else {
               if (var2) {
                  var2 = false;
                  var3 += var1;
                  var1 = 0.0F;
               }

               var3 += var7;
            }
         }
      }

      if (var2) {
         var3 += var1;
      }

      this.height = var3 + this.getHeight2();
   }

   protected float getWidth2() {
      return this.width;
   }

   protected float getHeight2() {
      return this.height;
   }

   protected boolean method7(MarkerModel.Data2 var1) {
      this.method4();
      return true;
   }

   protected boolean method9(MarkerModel.Data2 var1, int var2) {
      return var1.HHHCHORHIHRCOHIOICICICHCRRICCI() > this.x
            && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.x + this.getWidth2()
            && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > this.y
            && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + this.getHeight2()
         ? this.method7(var1)
         : false;
   }

   protected float method8() {
      return 0.0F;
   }

   protected float method9() {
      return 0.0F;
   }

   protected float method10() {
      return 0.0F;
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1, var2, var3);
      float var4 = 0.0F;
      int var5 = 0;
      float var6 = var2 + this.getHeight2();

      for (OptionWidget var8 : this.field16) {
         if (!this.field18.isBlank() && !this.field17.contains(var8)) {
            var8.method1(var1 - var3, var2, var3);
         } else if (!var8.getOption().isHidden()) {
            boolean var9 = var8.method3();
            float var10 = var9 ? var3 / 2.0F : var3;
            if (var5 == 2) {
               var5 = 0;
               var6 += var4;
               var4 = 0.0F;
            }

            if (!var9) {
               var5 = 0;
               var6 += var4;
               var4 = 0.0F;
            }

            var8.method1(var1 + var5 * (var3 / 2.0F) + this.method8(), var6 + this.method9(), var10 + this.method10());
            if (var8.getHeight() > var4) {
               var4 = var8.getHeight();
            }

            if (!var9) {
               var6 += var4;
               var4 = 0.0F;
            } else {
               var5++;
            }
         }
      }

      this.method4();
   }

   @Override
   public void update() {
      for (GuiWidget var2 : this.field16) {
         if (var2.method24()) {
            var2.update();
         }
      }
   }

   protected void method11(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      for (OptionWidget var4 : this.method25()) {
         if (!this.method24(var4)) {
            var4.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var2, var4.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var2));
         }
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
   }

   @Override
   public boolean method5(MarkerModel.Data2 var1) {
      return super.method5(var1)
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.x + this.getWidth2()
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + this.getHeight2();
   }

   @Nullable
   @Override
   public OptionWidget<?> method6(MarkerModel.Data2 var1) {
      if (this.method5(var1)) {
         return this;
      }

      if (!this.method11(var1)) {
         return null;
      }

      for (OptionWidget var3 : this.method25()) {
         if (!this.method24(var3)) {
            OptionWidget var4 = var3.method6(var1);
            if (var4 != null) {
               return var4;
            }
         }
      }

      return null;
   }

   @Override
   public void method14(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      if (var2.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.x + this.getWidth2() && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + this.getHeight2()) {
         super.method3(var1, var2, var3);
      } else {
         for (OptionWidget var5 : this.method25()) {
            if (!this.method24(var5) && var5.method5(var2)) {
               var5.method3(var1, var2);
            }
         }
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      for (GuiWidget var4 : this.field16) {
         if (var4.method24()) {
            var4.method4(var1, var2);
         }
      }
   }

   @Override
   public boolean method5(int var1) {
      if (super.method5(var1)) {
         return true;
      }

      for (GuiWidget var3 : this.field16) {
         if (var3.method24() && var3.method5(var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void close() {
      for (GuiWidget var2 : this.field16) {
         if (var2.method24()) {
            var2.close();
         }
      }
   }

   protected boolean method18(GuiWidget var1, MarkerModel.Data2 var2, GuiWidget... var3) {
      List var4 = Arrays.asList(var3);
      boolean var5 = true;

      for (int var6 = this.field16.size() - 1; var6 >= 0; var6--) {
         GuiWidget var7 = this.field16.get(var6);
         if (var7 == var1) {
            break;
         }

         if (!var4.contains(var7) && var7.method1(var2)) {
            var5 = false;
            break;
         }
      }

      return var5;
   }

   public boolean method19(String var1) {
      boolean var2 = false;
      this.field18 = var1;
      var1 = var1.toLowerCase();
      if (var1.isBlank()) {
         for (OptionWidget var9 : this.field16) {
            if (var9 instanceof ListOptionWidget var10) {
               var10.method19(var1);
            }
         }

         return false;
      } else {
         this.field17.clear();
         if (this.method20(this.getOption(), var1)) {
            this.field17.addAll(this.field16);
            return true;
         }

         for (OptionWidget var4 : this.field16) {
            if (var4 instanceof ListOptionWidget var5) {
               if (var5.method19(var1)) {
                  this.field17.add(var4);
                  var2 = true;
               }
            } else if (!(var4 instanceof SpacerWidget)) {
               ClientOption var6 = var4.getOption();
               if (this.method20(var6, var1)) {
                  this.field17.add(var4);
                  var2 = true;
               }
            }
         }

         return var2;
      }
   }

   private boolean method20(ClientOption<?> var1, String var2) {
      String var3 = ThreadModuleDump56.method4(var2);
      String var4 = var1.method3();
      String var5 = var4 + "Description";
      String var6 = var1.method1(var5, new Object[0]);
      if (!var1.getName().toLowerCase().contains(var2) && !var1.getId().toLowerCase().contains(var2)) {
         if (!var5.equals(var6) && var6.toLowerCase().contains(var2)) {
            return true;
         }

         String[] var7 = var1.getName().split(" ");

         for (String var11 : var7) {
            var11 = ThreadModuleDump56.method4(var11);
            if (var11.startsWith(var3)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   @Override
   public List<OptionWidget<?>> method1() {
      return this.field16;
   }

   public List<OptionWidget<?>> method25() {
      return this.field18.isBlank() ? this.field16 : this.field17;
   }

   @Override
   public boolean method2() {
      boolean var1 = false;

      for (OptionWidget var3 : this.field16) {
         if (var3 instanceof ListOptionWidget var4) {
            boolean var5 = var4.method2();
            if (var5) {
               var1 = true;
            }
         }

         if (com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget.method10(var3)) {
            var1 = true;
         }
      }

      return var1;
   }

   protected boolean method24(OptionWidget<?> var1) {
      return !var1.ICIHHIRHHIOCICHORCOOHRHRHHRCIR() || var1.getOption().isHidden();
   }

   @Override
   public boolean isEditing() {
      for (OptionWidget var2 : this.field16) {
         if (var2 instanceof EditState var3 && var3.isEditing()) {
            return true;
         }
      }

      return false;
   }
}
