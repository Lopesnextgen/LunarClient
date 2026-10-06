package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.CyclingEnumOptionWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class EnumOption<T extends Enum<T> & OptionEnumValue> extends AbstractValueOption<T> {
   private final Enum<T>[] field7;
   private final boolean field8;

   public EnumOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<T> var2, T var3, Enum<T>[] var4, boolean var5
   ) {
      super(var1, var2, (T)var3);
      this.field7 = var4;
      this.field8 = var5;
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new CyclingEnumOptionWidget<>(this, var1, this.field7);
   }

   public static class Data<T extends Enum<T> & OptionEnumValue> extends DefaultValueBuilder<EnumOption.Data<T>, EnumOption<T>, T> {
      private @Nullable Enum<T>[] field14;
      private boolean field15 = false;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.RADIO;
      }

      @Override
      protected Codec<T> method3() {
         return null;
      }

      @Override
      protected @Nullable Function<EnumOption<T>, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               EnumOption var2 = (EnumOption)this.option;
               var1x.addProperty("value", var2.get() == null ? "" : ((OptionEnumValue)((Enum)var2.get())).id());
               var1x.addProperty("hideRecommended", var2.field8);
               if (var2.ICCOIHCHIRROOOHIOIHOCIIHHICCCI() != null) {
                  JsonArray var3 = new JsonArray();

                  for (Enum var7 : (Enum[])((Enum)var2.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()).getClass().getEnumConstants()) {
                     var3.add(((OptionEnumValue)var7).provide());
                  }

                  var1x.add("choices", var3);
               }

               return var1x;
            }
         };
      }

      @SafeVarargs
      @Contract("_->this")
      public final EnumOption.Data<T> method4(Enum<T>... var1) {
         this.field14 = var1;
         return this;
      }

      @Contract("->this")
      public EnumOption.Data<T> method11() {
         this.field15 = true;
         return this;
      }

      @Contract("_->this")
      public EnumOption.Data<T> method6(boolean var1) {
         this.field15 = var1;
         return this;
      }

      protected EnumOption<T> method12() {
         if (this.defaultValue == null) {
            throw new OptionConfigException(this, "Value must be set!");
         }

         if (this.field14 == null) {
            this.field14 = (Enum<T>[])this.defaultValue.getClass().getEnumConstants();
         }

         if (this.codec == null) {
            this.codec = OptionEnumValue.method1((Supplier<T[]>)(() -> (Enum[])this.defaultValue.getClass().getEnumConstants()));
         }

         return new EnumOption<>(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.field14, this.field15);
      }
   }
}
