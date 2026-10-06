package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.DropdownWidget;
import com.moonsworth.lunar.client.ui.widget.IconTextButton;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionUpdateListeners;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class CrosshairEditorWidget extends com.moonsworth.lunar.client.ui.widget.OptionWidget<MultiSelectOption> implements EditState {
   private static final float field16 = 140.0F;
   private static final float field17 = 5.0F;
   private static final float field18 = 17.0F;
   private final float field19;
   private final List<ConfirmButtonWidget> field20 = new ArrayList<>();
   private List<String> field21;
   private final IconTextButton field22;
   private final DropdownWidget field23;
   private final Function<String, String> field24;
   private final Consumer<Set<String>> field25;
   private boolean field26;

   public CrosshairEditorWidget(MultiSelectOption var1, GuiWidget var2) {
      this(var1, var2, 12.0F);
   }

   private CrosshairEditorWidget(MultiSelectOption var1, GuiWidget var2, float var3) {
      super(var1, var2);
      this.field24 = var1.method8();
      this.field19 = var3;
      this.field23 = new DropdownWidget(this);
      this.field22 = new IconTextButton(this, this.getIcon(), FontRegistry.method17(), var1.getName(), 544831865, 1081702777);
      this.field25 = var1x -> this.method3(null);
      this.option.CICORRHIOIIOORRRICCORIOIOCIHII(this.field25);
      this.field22.method41(() -> {
         String var2x = this.field22.getText().toLowerCase();
         if (!var2x.isBlank()) {
            List var3x = this.option.method7();
            if (!var3x.contains(var2x)) {
               if (this.field20.size() != 1) {
                  return;
               }

               var2x = this.field20.get(0).getText();
            }

            if (var1.method10(var2x)) {
               this.method2();
               this.method3(null);
               this.field22.setText("");
               this.field22.method17(false);
            }
         }
      });
      this.field22.field34 = () -> this.method3(this.field22.getText());
   }

   public ResourceLocationBridge getIcon() {
      return ResourceLocationBridge.create("lunar", "icons/assets/add-12x12.png");
   }

   private List<String> method1() {
      if (this.field21 == null) {
         Set var1 = (Set)this.option.get();
         this.field21 = this.option.method7().stream().sorted((var2, var3) -> {
            boolean var4 = var1.contains(var2);
            boolean var5 = var1.contains(var3);
            if (var4 == var5) {
               return this.field24.apply(var2).compareTo(this.field24.apply(var3));
            } else {
               return var4 ? -1 : 1;
            }
         }).toList();
      }

      return this.field21;
   }

   private void method2() {
      this.field21 = null;
   }

   private void method3(String var1) {
      String var2 = var1 != null ? var1.toLowerCase() : null;
      Set var3 = (Set)this.option.get();
      this.field20.clear();

      for (String var6 : this.method1()) {
         if (var2 == null || var6.toLowerCase().contains(var2) || this.field24.apply(var6).toLowerCase().contains(var2)) {
            boolean[] var7 = new boolean[]{var3.contains(var6)};
            ConfirmButtonWidget var8 = new ConfirmButtonWidget(this, this.field24.apply(var6), var7[0]);
            var8.method6(() -> {
               if (var7[0]) {
                  this.option.remove(var6);
               } else {
                  this.option.method10(var6);
               }

               this.method2();
               var8.setState(!var8.isState());
               var7[0] = var8.isState();
            });
            this.field20.add(var8);
         }
      }

      this.method2(this.x, this.y, this.width, this.getHeight());
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      this.method2(var1, var2, var3 - 5.0F, this.getHeight());
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      this.field22.method2(var1, var2 + 2.0F, var3, 13.0F);
      this.field23.method2(var1 + var3 - 4.0F, var2 + 17.0F, 4.0F, var4 - 17.0F - 4.0F);
      int var5 = 0;

      for (GuiWidget var7 : this.field20) {
         var7.method2(var1, var2 + var5 * this.field19 + 17.0F, var3, this.field19);
         var5++;
      }
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : Math.min(140.0F, 17.0F + this.field19 * this.field20.size() + 2.0F);
   }

   @Override
   public boolean method3() {
      return true;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      this.field26 = this.method3(var2);
      this.field22.method3(var1, var2, var3);
      var1.push();
      LcuiScreen.method111(var1, this.x, this.y + 17.0F, this.width, this.height - 17.0F, 1.0F);
      this.field23.method15(this.field19 * this.field20.size());
      this.field23.method5(var1, var2, var3);
      var1.pop();

      for (GuiWidget var5 : this.field20) {
         float var6 = var5.getY();
         var5.setY(var6 + this.field23.method3());
         boolean var7 = var5.getY() + var5.getHeight() < this.field23.getY();
         boolean var8 = var5.getY() > this.y + this.height;
         if (!var7 && !var8) {
            var5.method3(var1, var2, true);
         }

         var5.setY(var6);
      }

      var1.push();
      this.field23.method7(var1, var2, var3 && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() > this.x + this.width - 4.0F);
      LcuiScreen.method112(var1);
      var1.pop();
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field23.method5(var1);
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      if (this.field22.method3(var1)) {
         return this.field22.method18(var1, var2);
      }

      this.field22.method17(false);
      if (var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() >= this.field23.getY()
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.field23.getY() + this.field23.getHeight()) {
         if (var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= this.field23.getX()
            && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() <= this.field23.getX() + this.field23.getWidth()) {
            this.field23.method18(var1, var2);
         } else {
            MarkerModel.Data2 var3 = this.field23.method4(var1);

            for (GuiWidget var5 : this.field20) {
               if (var5.method1(var3) && var5.method6(var3, var2)) {
                  this.field22.setText("");
                  return true;
               }
            }
         }
      }

      return super.method18(var1, var2);
   }

   @Override
   public boolean method8(MarkerModel.Data2 var1, int var2) {
      if (!this.field22.method3(var1)) {
         this.field22.method17(false);
      }

      return super.method2(var1, var2);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field22.method18()) {
         this.field22.method4(var1, var2);
      } else {
         for (GuiWidget var4 : this.field20) {
            var4.method4(var1, var2);
         }
      }
   }

   @Override
   public void close() {
      OptionUpdateListeners var1 = (OptionUpdateListeners)this.option.method7(OptionTraits.field1);
      if (var1 != null) {
         var1.method2(this.field25);
      }
   }

   @Override
   public boolean isEditing() {
      if (this.field22.isEditing()) {
         return true;
      }

      for (GuiWidget var2 : this.field20) {
         if (var2 instanceof EditState var3 && var3.isEditing()) {
            return true;
         }
      }

      return false;
   }

   @Generated
   public DropdownWidget method14() {
      return this.field23;
   }

   @Generated
   public boolean method15() {
      return this.field26;
   }
}
