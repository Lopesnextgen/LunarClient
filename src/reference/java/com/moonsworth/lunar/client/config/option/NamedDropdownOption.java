package com.moonsworth.lunar.client.config.option;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.NavigationButtonWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.List;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class NamedDropdownOption<T> extends AbstractValueOption<T> {
   private final List<T> field7;
   private final @Nullable Function<T, String> field8;

   public NamedDropdownOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<T> var2,
      T var3,
      List<T> var4,
      @Nullable Function<T, String> var5
   ) {
      super(var1, var2, (T)var3);
      this.field7 = var4;
      this.field8 = var5;
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new NavigationButtonWidget<>(this, var1, this.field8);
   }

   @Generated
   public List<T> getOptions() {
      return this.field7;
   }

   @Generated
   public @Nullable Function<T, String> method7() {
      return this.field8;
   }

   public static class Data<T> extends DefaultValueBuilder<NamedDropdownOption.Data<T>, NamedDropdownOption<T>, T> {
      protected @Nullable List<T> options;
      protected Function<T, String> field14;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.DROPDOWN;
      }

      @Override
      protected @Nullable Codec<T> method3() {
         return null;
      }

      @Override
      protected @Nullable Function<NamedDropdownOption<T>, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               NamedDropdownOption var2 = (NamedDropdownOption)this.option;
               if (var2.getValue() != null) {
                  JsonArray var3 = new JsonArray();

                  for (Object var5 : var2.getOptions()) {
                     JsonObject var6 = new JsonObject();
                     var6.addProperty("id", var5.toString());
                     var6.addProperty("name", var2.field8 != null ? var2.field8.apply((T)var5) : var5.toString());
                     var6.addProperty("description", "");
                     var3.add(var6);
                  }

                  var1x.add("choices", var3);
               }

               return var1x;
            }
         };
      }

      @Contract("_->this")
      public NamedDropdownOption.Data<T> method4(Function<T, String> var1) {
         this.field14 = var1;
         return this;
      }

      @Contract("_->this")
      public NamedDropdownOption.Data<T> method5(List<T> var1) {
         this.options = var1;
         return this;
      }

      @SafeVarargs
      @Contract("_->this")
      public final NamedDropdownOption.Data<T> method6(T... var1) {
         return this.method5(Lists.newArrayList(var1));
      }

      protected NamedDropdownOption<T> method11() {
         if (this.options == null) {
            throw new OptionConfigException(this, "Options must be set!");
         } else {
            return new NamedDropdownOption<>(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.options, this.field14);
         }
      }
   }
}
