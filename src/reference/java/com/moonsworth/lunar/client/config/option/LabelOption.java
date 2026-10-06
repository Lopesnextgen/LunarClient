package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.SpacerWidget;
import com.moonsworth.lunar.client.ui.widget.ListOptionRowWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class LabelOption extends AbstractValueOption<String> {
   private final boolean field7;
   private final @Nullable Supplier<String> field8;

   public LabelOption(
      @Annotation(method1 = Annotation.Type.SETTING_LABELS) String var1,
      @Nullable Codec<String> var2,
      String var3,
      boolean var4,
      @Nullable Supplier<String> var5
   ) {
      super(var1, var2, var3);
      this.field7 = var4;
      this.field8 = var5;
   }

   @Override
   public String getName() {
      return this.field8 == null ? method3(this) : this.field8.get();
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return !this.getChildren().isEmpty() ? new ListOptionRowWidget(this, var1) : new SpacerWidget(this, var1);
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + ".labels";
   }

   @Override
   public String toString() {
      return this.getId();
   }

   @Generated
   public boolean method7() {
      return this.field7;
   }

   public static class Data extends DefaultValueBuilder<LabelOption.Data, LabelOption, String> {
      private boolean field14;
      private @Nullable Supplier<String> field15;

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.CATEGORY;
      }

      @Override
      protected @Nullable Codec<String> method3() {
         return null;
      }

      @Override
      protected boolean method7() {
         return true;
      }

      @Override
      protected Function<LabelOption, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public boolean method2() {
               String var1x = this.option.getId();
               return var1x != null && !var1x.isBlank();
            }
         };
      }

      protected Data(@Annotation(method1 = Annotation.Type.SETTING_BUTTONS) String var1) {
         super(var1);
      }

      @Contract("->this")
      public LabelOption.Data method11() {
         this.field14 = true;
         return this;
      }

      @Contract("_->this")
      public LabelOption.Data method6(boolean var1) {
         this.field14 = !var1;
         return this;
      }

      @Contract("->this")
      public LabelOption.Data method12() {
         return (LabelOption.Data)super.HIHHHCRIRHHOCIROHOHIOHHIIHHRHO();
      }

      @Contract("_->this")
      public LabelOption.Data method8(Supplier<String> var1) {
         this.field15 = var1;
         return this;
      }

      protected LabelOption method13() {
         if (this.defaultValue == null) {
            this.defaultValue = "";
         }

         return new LabelOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.field14, this.field15);
      }
   }
}
