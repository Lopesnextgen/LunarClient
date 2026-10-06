package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.SliderOptionWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class DynamicDropdownOption extends AbstractValueOption<String> {
   private final Callable<List<String>> field7;
   private final @Nullable Function<String, String> field8;

   public DynamicDropdownOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<String> var2,
      String var3,
      Callable<List<String>> var4,
      @Nullable Function<String, String> var5
   ) {
      super(var1, var2, var3);
      this.field7 = var4;
      this.field8 = var5;
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new SliderOptionWidget(this, null);
   }

   @Generated
   public Callable<List<String>> method7() {
      return this.field7;
   }

   @Generated
   public @Nullable Function<String, String> method8() {
      return this.field8;
   }

   public static class Data extends DefaultValueBuilder<DynamicDropdownOption.Data, DynamicDropdownOption, String> {
      private @Nullable Callable<List<String>> field14;
      private @Nullable Function<String, String> field15;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.DROPDOWN;
      }

      @Override
      protected @Nullable Codec<String> method3() {
         return Codec.STRING;
      }

      @Override
      protected @Nullable Function<DynamicDropdownOption, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               DynamicDropdownOption var2 = (DynamicDropdownOption)this.option;
               var1x.addProperty("value", var2.get());
               JsonArray var3 = new JsonArray();

               try {
                  for (String var5 : var2.field7.call()) {
                     JsonObject var6 = new JsonObject();
                     var6.addProperty("id", var5);
                     var6.addProperty("name", var2.field8 != null ? var2.field8.apply(var5) : var5);
                     var3.add(var6);
                  }
               } catch (Exception var7) {
                  throw new RuntimeException(var7);
               }

               var1x.add("choices", var3);
               return var1x;
            }
         };
      }

      @Contract("_->this")
      public DynamicDropdownOption.Data method4(Callable<List<String>> var1) {
         this.field14 = var1;
         return this;
      }

      @Contract("_->this")
      public DynamicDropdownOption.Data method5(@Nullable Function<String, String> var1) {
         this.field15 = var1;
         return this;
      }

      protected DynamicDropdownOption method11() {
         if (this.field14 == null) {
            throw new OptionConfigException(this, "Provider must be set!");
         } else {
            return new DynamicDropdownOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.field14, this.field15);
         }
      }
   }
}
