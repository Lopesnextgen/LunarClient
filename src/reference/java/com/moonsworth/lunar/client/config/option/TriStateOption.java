package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.ToggleGroupWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class TriStateOption extends EnumOption<DefaultedBoolean> {
   private final boolean field9;
   @Nullable
   private final String field10;

   public TriStateOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<DefaultedBoolean> var2,
      DefaultedBoolean var3,
      boolean var4,
      @Nullable String var5
   ) {
      super(var1, var2, var3, DefaultedBoolean.values(), false);
      this.field9 = var4;
      this.field10 = var5;
   }

   public boolean method1(boolean var1) {
      return this.get().orElse(var1);
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new ToggleGroupWidget(this, var1);
   }

   @Generated
   public boolean method7() {
      return this.field9;
   }

   @Nullable
   @Generated
   public String method8() {
      return this.field10;
   }

   public static class Data extends DefaultValueBuilder<TriStateOption.Data, TriStateOption, DefaultedBoolean> {
      private boolean field14 = true;
      @Nullable
      private String field15;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
         this.HIIIOHRRROCICIOIORRRIRCRCHHIII(DefaultedBoolean.DEFAULT);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.RADIO;
      }

      @Override
      protected Codec<DefaultedBoolean> method3() {
         return OptionEnumValue.method1(DefaultedBoolean::values);
      }

      @Override
      protected Function<TriStateOption, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               TriStateOption var2 = (TriStateOption)this.option;
               var1x.addProperty("value", var2.get() == null ? "" : var2.get().id());
               JsonArray var3 = new JsonArray();

               for (DefaultedBoolean var7 : DefaultedBoolean.values()) {
                  var3.add(var7.provide());
               }

               var1x.add("choices", var3);
               return var1x;
            }
         };
      }

      @Contract("->this")
      public TriStateOption.Data method11() {
         this.field14 = false;
         return this;
      }

      @Contract("_->this")
      public TriStateOption.Data method5(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         this.field15 = var1;
         return this;
      }

      protected TriStateOption method12() {
         return new TriStateOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.field14, this.field15);
      }
   }
}
