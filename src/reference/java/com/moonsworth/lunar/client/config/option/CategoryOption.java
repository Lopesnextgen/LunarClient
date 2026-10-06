package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.CollapsibleListOptionWidget;
import com.moonsworth.lunar.client.config.option.AlertExtension;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.Annotation3;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.intellij.lang.annotations.Subst;

public class CategoryOption extends AbstractBooleanOption {
   public CategoryOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, Codec<Boolean> var2, Boolean var3) {
      super(var1, var2, var3);
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new CollapsibleListOptionWidget(this, var1);
   }

   @Override
   public void reset() {
      super.reset();

      for (ClientOption var2 : this.getChildren()) {
         var2.reset();
      }
   }

   @Override
   public boolean isDefault() {
      if (!super.isDefault()) {
         return false;
      }

      for (ClientOption var2 : this.getChildren()) {
         if (!var2.isDefault()) {
            return false;
         }
      }

      return true;
   }

   public static class Data extends BooleanOptionBuilder<CategoryOption.Data, CategoryOption> {
      private final List<OptionSupplier<?, ?>> field15 = new ArrayList<>();

      public Data(@Annotation3 @Annotation(method1 = Annotation.Type.SETTING_BUTTONS) String var1) {
         super(var1);
      }

      public CategoryOption.Data method1(@Subst("optionId") String var1, String var2, String var3, ClientOption<?>... var4) {
         CategoryOption.Data var5;
         if (this.field15.stream().anyMatch(var1x -> var1x instanceof CategoryOption.Data var2x && var2x.getId().equals(var2))) {
            var5 = (CategoryOption.Data)this.field15
               .stream()
               .filter(var1x -> var1x instanceof CategoryOption.Data var2x && var2x.getId().equals(var2))
               .findFirst()
               .get();
         } else {
            var5 = (CategoryOption.Data)((CategoryOption.Data)new CategoryOption.Data(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC + "." + var1)
                  .ROICHOCCIOCHCIHOIHIHICCORIROCC(var3))
               .method3(var4)
               .OHICCHCORCORRRHCHRCCIROCCHCCRC();
            this.method3(var5);
         }

         this.method11();
         return var5;
      }

      public CategoryOption.Data method3(OptionSupplier<?, ?>... var1) {
         for (OptionSupplier var5 : var1) {
            if (!this.field15.contains(var5)) {
               this.field15.add(var5);
            }
         }

         return this.method11();
      }

      public CategoryOption.Data method11() {
         ArrayList var1 = new ArrayList<>(this.field15);
         this.field15.clear();

         for (int var2 = var1.size() - 1; var2 >= 0; var2--) {
            OptionSupplier var3 = (OptionSupplier)var1.get(var2);
            if (var3 instanceof CategoryOption.Data) {
               this.field15.add(var3);
               var1.remove(var2);
            }
         }

         this.field15.sort(Comparator.comparing(OptionSupplier::getId));
         var1.sort(Comparator.comparing(OptionSupplier::getId));
         this.field15.addAll(var1);
         return this;
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.CATEGORY;
      }

      public CategoryOption method5(CategoryOption var1) {
         super.method17(var1);
         var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            OptionTraits.field4, AlertExtension.method3(this.field15.stream().map(OptionSupplier::method1).collect(Collectors.toUnmodifiableList()))
         );
         return var1;
      }

      protected CategoryOption method12() {
         return new CategoryOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.field7);
      }
   }
}
