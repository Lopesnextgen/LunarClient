package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.TextAreaWidget;
import com.moonsworth.lunar.client.ui.widget.DropdownWidget;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui5;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionUpdateListeners;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lombok.Generated;

public class ResourcePackListWidget
   extends com.moonsworth.lunar.client.ui.widget.OptionWidget<ListOption<String>>
   implements EditState {
   private final float field16 = 140.0F;
   protected static final float field17 = 5.0F;
   protected static final float field18 = 17.0F;
   private final float field19;
   protected final List<GuiWidget> field20;
   private final TextAreaWidget field21;
   private final DropdownWidget field22;
   private final boolean field23;
   private final Consumer<List<String>> field24;

   public ResourcePackListWidget(ListOption<String> var1, GuiWidget var2) {
      this(var1, var2, 12.0F, true);
   }

   public ResourcePackListWidget(ListOption<String> var1, GuiWidget var2, float var3) {
      this(var1, var2, var3, true);
   }

   public ResourcePackListWidget(ListOption<String> var1, GuiWidget var2, float var3, boolean var4) {
      super(var1, var2);
      this.field19 = var3;
      this.field23 = var4;
      this.field22 = new DropdownWidget(this);
      this.field21 = new TextAreaWidget(this, "btnAddPack");
      this.field20 = new CopyOnWriteArrayList<>();
      this.field24 = var1x -> this.method1(null);
      this.option.CICORRHIOIIOORRRICCORIOIOCIHII(this.field24);
      this.field21.HORHROIOIOICIRHIOCOICHHHIHCIIO((var2x, var3x) -> {
         File var4x = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "resourcepacks");
         File var5 = Gui4.method7(null, var4x, "", new String[0]);
         if (var5 != null) {
            String var6 = Gui5.method2(var5, var4x);
            if (var1.contains(var6) || var1.add(var6)) {
               this.method1(null);
            }
         }

         return true;
      });
   }

   public ResourceLocationBridge getIcon() {
      return ResourceLocationBridge.create("lunar", "icons/assets/add-12x12.png");
   }

   public void method1(@Nullable String var1) {
      this.field20.clear();
      if (this.field23 && var1 != null) {
         var1 = var1.toLowerCase();
      }

      for (Object var4 : (List)this.option.get()) {
         if (var1 == null || (this.field23 ? var4.toString().toLowerCase().contains(var1) : var4.toString().contains(var1))) {
            ConfirmButtonWidget var5 = new ConfirmButtonWidget(this, var4.toString());
            this.field20.add(var5);
            var5.method6(() -> {
               this.option.remove(var5.getText());
               this.field20.remove(var5);
            });
         }
      }
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      this.method2(var1, var2, var3 - 5.0F, this.getHeight());
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      this.field22.method2(var1 + var3 - 4.0F, var2, 4.0F, var4 - 17.0F);
      int var5 = 0;

      for (GuiWidget var7 : this.field20) {
         var7.method2(var1, var2 + var5 * this.field19, var3, this.field19);
         var5++;
      }

      this.field21.method2(var1, var2 + var5 * this.field19, var3, 13.0F);
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
      throw new UnsupportedOperationException("Component draw outdated");
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field22.method5(var1);
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      if (this.field21.method1(var1)) {
         return this.field21.method6(var1, var2);
      }

      if (var1.method12() >= this.field22.getY()
         && var1.method12() < this.field22.getY() + this.field22.getHeight()) {
         MarkerModel.Data2 var3 = this.field22.method4(var1);

         for (GuiWidget var5 : this.field20) {
            if (var5.method1(var3) && var5.method6(var3, var2)) {
               return true;
            }
         }
      }

      return super.method6(var1, var2);
   }

   @Override
   public boolean method8(MarkerModel.Data2 var1, int var2) {
      return super.method2(var1, var2);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      for (GuiWidget var4 : this.field20) {
         var4.method4(var1, var2);
      }
   }

   @Override
   public void close() {
      OptionUpdateListeners var1 = (OptionUpdateListeners)this.option.method7(OptionTraits.field1);
      if (var1 != null) {
         var1.method2(this.field24);
      }
   }

   @Override
   public boolean isEditing() {
      for (GuiWidget var2 : this.field20) {
         if (var2 instanceof EditState var3 && var3.isEditing()) {
            return true;
         }
      }

      return false;
   }

   @Generated
   public DropdownWidget method10() {
      return this.field22;
   }
}
