package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.OptionAlertHandler;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.config.option.OptionDisplay;
import com.moonsworth.lunar.client.driver.DriverFieldType;
import com.moonsworth.lunar.client.config.option.Identifier;
import com.moonsworth.lunar.client.framework.Flag;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.ClientOption;

public abstract class AbstractOptionBuilder<B extends AbstractOptionBuilder<B, O, T>, O extends ClientOption<T>, T> implements OptionProvider<O, T> {
   @Identifier
   protected final String field1;
   protected DriverFieldType field2 = this.method2();
   protected boolean field3 = this.method7();
   protected Supplier<String> field4;
   protected String field5;
   protected @Nullable DriverFieldType field6;
   protected @Nullable Codec<T> codec;
   protected boolean field7;
   protected @Nullable Function<O, OptionDataProvider> field8;
   protected @Nullable BooleanSupplier field9;
   protected @Nullable OptionFeatureLink field10;
   protected @Nullable Set<OptionFlag> field11;
   protected boolean field12 = this.method8();
   protected @Nullable BiFunction<ClientOption<?>, GuiWidget, OptionWidget<?>> field13;

   protected AbstractOptionBuilder(@Identifier String text1) {
      this.field1 = text1;
   }

   protected abstract DriverFieldType method2();

   protected abstract @Nullable Codec<T> method3();

   protected @Nullable Function<O, OptionDataProvider> method4() {
      return OptionJsonProvider::new;
   }

   protected @Nullable BooleanSupplier method5() {
      return null;
   }

   protected @Nullable OptionFeatureLink method6() {
      return null;
   }

   protected boolean method7() {
      return false;
   }

   protected boolean method8() {
      return false;
   }

   protected @Nullable Set<OptionFlag> method9() {
      return null;
   }

   @Contract("_->this")
   public B method9(BiFunction<O, GuiWidget, OptionWidget<?>> function1) {
      this.field13 = function1;
      return (B)this;
   }

   @Contract("_->this")
   public B method10(Function<O, OptionDataProvider> function1) {
      this.field8 = function1;
      return (B)this;
   }

   @Contract("_->this")
   public B method11(Consumer<JsonObject> consumer1) {
      this.field8 = arg2 -> new OptionJsonProvider(arg2) {
         @Override
         public JsonElement provide() {
            JsonObject json1x = super.provide().getAsJsonObject();
            consumer1.accept(json1x);
            return json1x;
         }
      };
      return (B)this;
   }

   @Contract("_->this")
   public B method12(String text1) {
      this.field5 = text1;
      return (B)this;
   }

   @Contract("_->this")
   public B method13(String text1) {
      this.field4 = () -> text1;
      return (B)this;
   }

   @Contract("_->this")
   public B method14(Supplier<String> supplier1) {
      this.field4 = supplier1;
      return (B)this;
   }

   @Contract("_->this")
   public B method15(com.moonsworth.lunar.client.driver.PhosphorIcon markerstype1) {
      this.field6 = markerstype1;
      return (B)this;
   }

   @Contract("_->this")
   public B method16(DriverFieldType markerstype1) {
      this.field2 = markerstype1;
      return (B)this;
   }

   @Contract("_->this")
   public B method17(BooleanSupplier booleansupplier1) {
      this.field9 = booleansupplier1;
      return (B)this;
   }

   @Contract("_->this")
   public B method18(Framework7Extension framework7extension1) {
      this.field10 = OptionFeatureLink.method1(framework7extension1);
      return (B)this;
   }

   @Contract("_->this")
   public B method19(OptionFeatureLink nameplate31) {
      this.field10 = nameplate31;
      return (B)this;
   }

   @Contract("_->this")
   public B method20(Codec<T> codec1) {
      this.codec = codec1;
      this.field7 = false;
      return (B)this;
   }

   @Contract("->this")
   public B method21() {
      this.codec = null;
      this.field7 = true;
      return (B)this;
   }

   @Contract("_->this")
   public B method22(boolean flag1) {
      this.field3 = flag1;
      return (B)this;
   }

   @Contract("->this")
   public B method23() {
      this.field3 = true;
      return (B)this;
   }

   @Contract("->this")
   public B method24() {
      this.field12 = false;
      return (B)this;
   }

   @Contract("->this")
   public B method25() {
      this.field12 = true;
      return (B)this;
   }

   @Contract("_->this")
   public B method26(OptionFlag... items1) {
      if (this.field11 != null) {
         this.field11.clear();
      }

      return this.method27(items1);
   }

   @Contract("_->this")
   public B method27(OptionFlag... items1) {
      if (items1.length == 0) {
         return (B)this;
      }

      if (this.field11 == null) {
         this.field11 = new HashSet<>(2);
      }

      Collections.addAll(this.field11, items1);
      return (B)this;
   }

   @Contract("->this")
   public B method28() {
      return this.method27(OptionFlag.ADVANCED);
   }

   @MustBeInvokedByOverriders
   protected void method29() {
      if (this.codec == null && !this.field7) {
         this.codec = this.method3();
      }

      if (this.field8 == null) {
         this.field8 = this.method4();
      }

      if (this.field9 == null) {
         this.field9 = this.method5();
      }

      if (this.field10 == null) {
         this.field10 = this.method6();
      }

      if (this.field11 == null) {
         this.field11 = this.method9();
      }
   }

   @Contract("_->param1")
   @MustBeInvokedByOverriders
   protected O method30(O value1) {
      if (this.field13 != null) {
         value1.method21(OptionTraits.field13, this.field13);
      }

      if (this.field8 != null) {
         value1.method21(OptionTraits.field10, this.field8.apply((O)value1));
      }

      if (this.field10 != null) {
         value1.method21(OptionTraits.field8, this.field10);
      }

      if (this.field2 != null || this.field9 != null || this.field4 != null || this.field6 != null || this.field12 != this.method8()) {
         value1.method21(OptionTraits.field2, OptionDisplay.method11(this.field2, this.field4, this.field6, this.field9, this.field12));
      }

      if (this.field11 != null) {
         value1.method21(OptionTraits.field3, this.field11);
      }

      if (this.field3) {
         value1.method21(OptionTraits.field12, Flag.INSTANCE);
      } else {
         value1.method21(OptionTraits.field5, new OptionAlertHandler().method8());
      }

      if (this.field5 != null) {
         value1.method21(OptionTraits.field11, this.field5);
      }

      return (O)value1;
   }

   @Contract("->new")
   protected abstract O method31();

   @Contract("->new")
   @Override
   public final O method1() {
      this.method29();
      return this.method30(this.method31());
   }

   @Generated
   @Override
   public String getId() {
      return this.field1;
   }
}
