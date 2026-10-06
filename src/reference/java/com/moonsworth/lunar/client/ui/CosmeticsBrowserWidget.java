package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.ui.widget.DropdownWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.ui.widget.IconTextButton;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lombok.Generated;

public abstract class CosmeticsBrowserWidget extends GuiWidget implements EditState {
   private final IconTextButton field16;
   private final ProgressBarWidget field17;
   private final ProgressBarWidget field18;
   private final DropdownWidget field19 = new DropdownWidget(this);
   private List<GuiWidget> entries;
   private List<GuiWidget> field20;
   private final CosmeticsScreen field21;

   public CosmeticsBrowserWidget(CosmeticsScreen var1) {
      super(null);
      this.field21 = var1;
      this.field17 = new ProgressBarWidget(this, "btnReturn", ResourceLocationBridge.create("lunar", "icons/cosmetics/back-40x40.png"));
      this.field18 = new ProgressBarWidget(this, "btnAddSkin", ResourceLocationBridge.create("lunar", "icons/add-64x64.png"));
      this.field16 = new IconTextButton(this, FontRegistry.field14, "searchPlaceholder", 553648127, 905969663);
      this.entries = new ArrayList<>();
      this.field20 = new ArrayList<>();
      this.method4((var1x, var2) -> {
         if (this.field16.method3(var1x)) {
            return this.field16.method18(var1x, var2);
         }

         if (this.field18.method3(var1x)) {
            return this.method7();
         }

         if (this.field19.method1(var1x)) {
            this.field19.method18(var1x, var2);
            com.moonsworth.lunar.client.ui.LcuiScreen.method15();
            return false;
         }

         for (GuiWidget var4 : this.field20) {
            if (var4.method1(var1x)) {
               return var4.method6(var1x, var2);
            }
         }

         return this.method1(var1x, var2, var0 -> var0::method6);
      });
      this.method3((var1x, var2) -> {
         this.field16.method2(var1x, var2);
         return this.method1(var1x, var2, var0 -> var0::method8);
      });
   }

   private boolean method1(MarkerModel.Data2 var1, int var2, Function<GuiWidget, ThreadModuleDump51> var3) {
      MarkerModel.Data2 var4 = this.field19.method4(var1);

      for (GuiWidget var6 : this.entries) {
         boolean var7 = var6.getY() + var6.getHeight() + this.field19.method3() < this.field19.getY();
         boolean var8 = var6.getY() + this.field19.method3() > this.field19.getY() + this.field19.getHeight();
         if (var6.getX() >= this.x + 40.0F
            && var1.method9() > this.y + 40.0F
            && var1.method9() < this.y + this.height - 20.0F
            && !var7
            && !var8
            && var6.method1(var4)) {
            return ((ThreadModuleDump51)var3.apply(var6)).accept(var4, var2);
         }
      }

      return false;
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, this.x - 1.0F, this.y, this.width + 2.0F, this.height, 4.0F, 1612586526);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 3.25F, 1075715614);
      com.moonsworth.lunar.client.ui.LcuiScreen.method117(var1, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 5.0F, -1306912744);
      com.moonsworth.lunar.client.ui.LcuiScreen.method104(var1, this.x, this.y + 1.0F, 40.0F, this.height - 2.0F, 5.0F, 905969664);
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x + 40.5F, this.y + 1.0F, 0.5F, this.height - 2.0F, 553648127);
      this.field16.method3(var1, var2, var3);
      this.field18.method3(var1, var2, var3 && this.field18.method3(var2));
      this.field17.method3(var1, var2, var3 && this.field17.method3(var2));

      for (GuiWidget var5 : this.field20) {
         var5.method3(var1, var2, var3 && var5.method1(var2));
      }

      var1.push();
      com.moonsworth.lunar.client.ui.LcuiScreen.method111(var1, this.x + 60.0F, this.y + 40.0F, this.width - 60.0F, this.height - 60.0F, 1.0F);
      this.field19.method5(var1, var2, true);
      MarkerModel.Data2 var10 = this.field19.method4(var2);
      boolean var11 = var2.method12() > this.field19.getY()
         && var2.method12() < this.field19.getY() + this.field19.getHeight();

      for (GuiWidget var7 : this.entries) {
         boolean var8 = var7.getY() + var7.getHeight() + this.field19.method3() < this.field19.getY();
         boolean var9 = var7.getY() + this.field19.method3() > this.field19.getY() + this.field19.getHeight();
         if (var7.getX() >= this.x + 20.0F && !var8 && !var9) {
            var7.method3(var1, var10, !this.field19.method17() && var11);
         }
      }

      this.field19.method7(var1, var2, true);
      com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
      var1.pop();
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      this.field16.method2(var1 + 60.0F, var2 + 15.0F, 220.0F, 18.0F);
      this.field18.method2(var1 + 285.0F, var2 + 15.0F, 18.0F, 18.0F);
      this.field19.method2(var1 + 285.0F, var2 + 38.0F, 3.0F, var4 - 58.0F);
      this.method9(var1, var2, var3, var4);
      this.method8(var1, var2, var3, var4);
      this.field17.method2(var1 + 7.0F, var2 + var4 - 26.0F - 7.0F, 26.0F, 26.0F);
   }

   @Override
   public void update() {
      this.entries.forEach(GuiWidget::update);
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field19.method5(var1);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      this.field16.method17(true);
      this.field16.method4(var1, var2);
   }

   @Override
   public boolean isEditing() {
      return this.field16.isEditing();
   }

   @Override
   public void close() {
   }

   public String method1(String var1, Object... var2) {
      return this.field21.method1(var1, var2);
   }

   public abstract boolean method7();

   public abstract void method8(float var1, float var2, float var3, float var4);

   public abstract void method9(float var1, float var2, float var3, float var4);

   @Generated
   public IconTextButton method10() {
      return this.field16;
   }

   @Generated
   public List<GuiWidget> getEntries() {
      return this.entries;
   }

   @Generated
   public List<GuiWidget> method14() {
      return this.field20;
   }
}
