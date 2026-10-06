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
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation3;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType3;
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

public abstract class OptionBuilderBase<B extends OptionBuilderBase<B, O, T>, O extends ClientOption<T>, T> implements OptionSupplier<O, T> {
   @Annotation3
   protected final String field1;
   protected DriverFieldTypeLegacy field2 = this.method2();
   protected boolean field3 = this.method7();
   protected Supplier<String> field4;
   protected String field5;
   protected @Nullable DriverFieldTypeLegacy field6;
   protected @Nullable Codec<T> codec;
   protected boolean field7;
   protected @Nullable Function<O, OptionDataProvider> field8;
   protected @Nullable BooleanSupplier field9;
   protected @Nullable OptionFeatureLink field10;
   protected @Nullable Set<AdvancedOptionFlag> field11;
   protected boolean field12 = this.method8();
   protected @Nullable BiFunction<ClientOption<?>, GuiWidget, OptionWidget<?>> field13;

   protected OptionBuilderBase(@Annotation3 String var1) {
      this.field1 = var1;
   }

   protected abstract DriverFieldTypeLegacy method2();

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

   protected @Nullable Set<AdvancedOptionFlag> method9() {
      return null;
   }

   @Contract("_->this")
   public B method9(BiFunction<O, GuiWidget, OptionWidget<?>> var1) {
      this.field13 = var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method10(Function<O, OptionDataProvider> var1) {
      this.field8 = var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method11(Consumer<JsonObject> var1) {
      this.field8 = var2 -> new OptionJsonProvider(var2) {
         @Override
         public JsonElement provide() {
            JsonObject var1x = super.provide().getAsJsonObject();
            var1.accept(var1x);
            return var1x;
         }
      };
      return (B)this;
   }

   @Contract("_->this")
   public B method12(String var1) {
      this.field5 = var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method13(String var1) {
      this.field4 = () -> var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method14(Supplier<String> var1) {
      this.field4 = var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method15(com.moonsworth.lunar.client.driver.PhosphorIconLegacy var1) {
      this.field6 = var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method16(DriverFieldTypeLegacy var1) {
      this.field2 = var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method17(BooleanSupplier var1) {
      this.field9 = var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method18(Framework7Extension var1) {
      this.field10 = OptionFeatureLink.method1(var1);
      return (B)this;
   }

   @Contract("_->this")
   public B method19(OptionFeatureLink var1) {
      this.field10 = var1;
      return (B)this;
   }

   @Contract("_->this")
   public B method20(Codec<T> var1) {
      this.codec = var1;
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
   public B method22(boolean var1) {
      this.field3 = var1;
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
   public B method26(AdvancedOptionFlag... var1) {
      if (this.field11 != null) {
         this.field11.clear();
      }

      return this.method27(var1);
   }

   @Contract("_->this")
   public B method27(AdvancedOptionFlag... var1) {
      if (var1.length == 0) {
         return (B)this;
      }

      if (this.field11 == null) {
         this.field11 = new HashSet<>(2);
      }

      Collections.addAll(this.field11, var1);
      return (B)this;
   }

   @Contract("->this")
   public B method28() {
      return this.method27(AdvancedOptionFlag.ADVANCED);
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
   protected O method30(O var1) {
      if (this.field13 != null) {
         var1.method21(OptionTraits.field13, this.field13);
      }

      if (this.field8 != null) {
         var1.method21(OptionTraits.field10, this.field8.apply((O)var1));
      }

      if (this.field10 != null) {
         var1.method21(OptionTraits.field8, this.field10);
      }

      if (this.field2 != null || this.field9 != null || this.field4 != null || this.field6 != null || this.field12 != this.method8()) {
         var1.method21(OptionTraits.field2, OptionDisplay.method11(this.field2, this.field4, this.field6, this.field9, this.field12));
      }

      if (this.field11 != null) {
         var1.method21(OptionTraits.field3, this.field11);
      }

      if (this.field3) {
         var1.method21(OptionTraits.field12, ThreadModuleDumpType3.INSTANCE);
      } else {
         var1.method21(OptionTraits.field5, new OptionAlertHandler().method8());
      }

      if (this.field5 != null) {
         var1.method21(OptionTraits.field11, this.field5);
      }

      return (O)var1;
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
