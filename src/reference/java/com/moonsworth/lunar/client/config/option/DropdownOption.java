package com.moonsworth.lunar.client.config.option;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.DeviceSelectOptionWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.List;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class DropdownOption<T> extends AbstractValueOption<T> {
   private final List<T> field7;

   public DropdownOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<T> var2, @Nullable T var3, List<T> var4
   ) {
      super(var1, var2, (T)var3);
      this.field7 = var4;
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new DeviceSelectOptionWidget<>(this, var1);
   }

   @Generated
   public List<T> method7() {
      return this.field7;
   }

   public static class Data<T> extends DefaultValueBuilder<DropdownOption.Data<T>, DropdownOption<T>, T> {
      private @Nullable List<T> options;

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
      protected @Nullable Function<DropdownOption<T>, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               JsonArray var2 = new JsonArray();

               for (Object var4 : ((DropdownOption)this.option).field7) {
                  var2.add(var4.toString());
               }

               var1x.add("choices", var2);
               return var1x;
            }
         };
      }

      @Contract("_->this")
      public DropdownOption.Data<T> method4(List<T> var1) {
         this.options = var1;
         return this;
      }

      @SafeVarargs
      @Contract("_->this")
      public final DropdownOption.Data<T> method5(T... var1) {
         return this.method4(Lists.newArrayList(var1));
      }

      protected DropdownOption<T> method11() {
         if (this.options == null) {
            throw new OptionConfigException(this, "Options must be set!");
         } else {
            return new DropdownOption<>(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.options);
         }
      }
   }
}
