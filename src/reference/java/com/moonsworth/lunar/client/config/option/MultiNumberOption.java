package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.MultiNumberOptionWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class MultiNumberOption<T extends Number & Comparable<T>> extends AbstractValueOption<List<T>> {
   private final @Nullable List<String> field7;

   public MultiNumberOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<List<T>> var2,
      List<T> var3,
      @Nullable List<String> var4
   ) {
      super(var1, var2, var3);
      this.field7 = var4;
   }

   public void method1(List<Double> var1) {
      ArrayList var2 = new ArrayList();

      for (Double var4 : var1) {
         var2.add(this.method3(var4));
      }

      this.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2);
   }

   public String method2(int var1) {
      Number var2 = this.get().get(var1);
      return String.format("%.2f", var2.floatValue());
   }

   private <K extends Number> K method3(Double var1) {
      Class var2 = ((Number)((List)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()).get(0)).getClass();
      Number var3;
      if (var2 == Integer.class) {
         var3 = (int)Math.round(var1);
      } else if (var2 == Float.class) {
         var3 = var1.floatValue();
      } else if (var2 == Byte.class) {
         var3 = (byte)Math.round(var1);
      } else if (var2 == Long.class) {
         var3 = Math.round(var1);
      } else if (var2 == Short.class) {
         var3 = (short)Math.round(var1);
      } else {
         var3 = var1;
      }

      return (K)var3;
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new MultiNumberOptionWidget<>(this, var1);
   }

   public static class Data<T extends Number & Comparable<T>> extends DefaultValueBuilder<MultiNumberOption.Data<T>, MultiNumberOption<T>, List<T>> {
      private @Nullable List<String> field14;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.MULTI_NUMBER;
      }

      @Override
      protected @Nullable Codec<List<T>> method3() {
         return null;
      }

      @Override
      protected @Nullable Function<MultiNumberOption<T>, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               MultiNumberOption var2 = (MultiNumberOption)this.option;
               List var3 = var2.field7;
               if (var3 != null) {
                  JsonArray var4 = new JsonArray();

                  for (String var6 : var3) {
                     var4.add(var6);
                  }

                  var1x.add("names", var4);
               }

               List var7 = (List)var2.ICCOIHCHIRROOOHIOIHOCIIHHICCCI();
               var1x.addProperty("integer", !var7.isEmpty() && var7.get(0) instanceof Integer);
               return var1x;
            }
         };
      }

      @Contract("_->this")
      public MultiNumberOption.Data<T> method4(@Nullable List<String> var1) {
         this.field14 = var1;
         return this;
      }

      @Contract("_->this")
      public MultiNumberOption.Data<T> method5(String... var1) {
         return this.method4(List.of(var1));
      }

      protected MultiNumberOption<T> method11() {
         if (this.defaultValue == null) {
            throw new OptionConfigException(this, "Value must be set!");
         } else {
            return new MultiNumberOption<>(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.field14);
         }
      }
   }
}
