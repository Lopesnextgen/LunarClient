package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.config.option.OptionUpdateListeners;
import com.moonsworth.lunar.client.config.option.trait.TraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitContainer;
import com.moonsworth.lunar.client.config.option.trait.TraitSnapshot;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.BuilderTraitType;
import com.moonsworth.lunar.client.util.ThreadModuleDump44;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class GuardedOption<T> implements ClientOption<T> {
   private final ClientOption<T> field1;
   private final BooleanSupplier field2;

   @Override
   public T getDefaultValue() {
      return this.field1.getDefaultValue();
   }

   @Override
   public void method3(T var1) {
      this.field1.method3((T)var1);
   }

   @Override
   public T getValue() {
      return this.field1.getValue();
   }

   @Override
   public void method10(T var1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method10((T)var1);
      }
   }

   @Override
   public void method11(T var1, boolean var2) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method11((T)var1, var2);
      }
   }

   @Override
   public void method12(T var1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method12((T)var1);
      }
   }

   @Override
   public void method13(Object var1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method13(var1);
      }
   }

   @Override
   public T get() {
      return this.field1.get();
   }

   @Override
   public Optional<T> method14(JsonElement var1) {
      return Optional.empty();
   }

   @Override
   public void method21(String var1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method21(var1);
      }
   }

   @Override
   public void reset() {
      if (!this.field2.getAsBoolean()) {
         this.field1.reset();
      }
   }

   @Override
   public void method19(ClientOption<?> var1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method19(var1);
      }
   }

   @Override
   public ClientOption<T> method20() {
      return this.field1.method20();
   }

   @Override
   public void method21(@NotNull ResolvedOptionNode<?> var1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method21(var1);
      }
   }

   @Override
   public @Nullable T method24(@NonNull Object var1) {
      return this.field1.method24(var1);
   }

   @Generated
   public GuardedOption(ClientOption<T> var1, BooleanSupplier var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Generated
   @Override
   public String getId() {
      return this.field1.getId();
   }

   @Generated
   @Override
   public String method3() {
      return this.field1.method3();
   }

   @Generated
   @Override
   public String method4() {
      return this.field1.method4();
   }

   @Generated
   @Override
   public String getName() {
      return this.field1.getName();
   }

   @Generated
   @Override
   public Class method5() {
      return this.field1.method5();
   }

   @Generated
   @Override
   public OptionUpdateListeners method6() {
      return this.field1.method6();
   }

   @Generated
   @Override
   public ClientOption method7(Consumer var1) {
      return this.field1.method7(var1);
   }

   @Generated
   @Override
   public ClientOption method8(Consumer var1) {
      return this.field1.method8(var1);
   }

   @Generated
   @Override
   public ClientOption method9(Runnable var1) {
      return this.field1.method9(var1);
   }

   @Generated
   @Override
   public String getValueAsString() {
      return this.field1.getValueAsString();
   }

   @Generated
   @Override
   public boolean isHidden() {
      return this.field1.isHidden();
   }

   @Generated
   @Override
   public void method16(JsonObject var1, boolean var2) {
      this.field1.method16(var1, var2);
   }

   @Generated
   @Override
   public void load(JsonObject var1) {
      this.field1.load(var1);
   }

   @Generated
   @Override
   public void method1(JsonObject var1) {
      this.field1.method1(var1);
   }

   @Generated
   @Override
   public boolean isDefault() {
      return this.field1.isDefault();
   }

   @Generated
   @Override
   public OptionWidget method18(GuiWidget var1) {
      return this.field1.method18(var1);
   }

   @Generated
   @Override
   public String getLanguagePath() {
      return this.field1.getLanguagePath();
   }

   @Generated
   @Override
   public List getChildren() {
      return this.field1.getChildren();
   }

   @Generated
   @Override
   public Collection method22() {
      return this.field1.method22();
   }

   @Generated
   @Override
   public ClientOption method1() {
      return this.field1.method1();
   }

   @Generated
   @Override
   public int priority() {
      return this.field1.priority();
   }

   @Generated
   public String method1(String var1, Object... var2) {
      return this.field1.OHROCHICOIOICHOCRROORRCIIICIHO(var1, var2);
   }

   @Generated
   public TraitContainer method2() {
      return this.field1.RRIHOHIHHHCHCIRRIORIIRCOOOOIHH();
   }

   @Generated
   public <T> Stream<T> method2(Class<T> var1) {
      return this.field1.method19(var1);
   }

   @Generated
   public <T> T method1(TraitType<T> var1) {
      return (T)this.field1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(var1);
   }

   @Generated
   public <T> T method5(TraitType<T> var1, T var2) {
      return (T)this.field1.method5(var1, var2);
   }

   @Generated
   public boolean method2(TraitType<?> var1) {
      return this.field1.method2(var1);
   }

   @Generated
   public <T> Optional<T> method3(TraitType<T> var1) {
      return this.field1.method3(var1);
   }

   @Generated
   public <T> T method4(TraitType<T> var1) {
      return (T)this.field1.RHRHIOOCICIORIOCIHHCIIRCRHHOII(var1);
   }

   @Generated
   public <TRAIT> TRAIT method1(TraitType<TRAIT> var1, TRAIT var2) {
      return (TRAIT)this.field1.method21(var1, var2);
   }

   @Generated
   public <TRAIT> TRAIT method2(TraitType<TRAIT> var1, ThreadModuleDump44<TRAIT> var2) {
      return (TRAIT)this.field1.method21(var1, var2);
   }

   @Generated
   public <TRAIT, B extends ThreadModuleDump44<TRAIT>> TRAIT method3(BuilderTraitType<TRAIT, B> var1, Consumer<B> var2) {
      return (TRAIT)this.field1.method21(var1, var2);
   }

   @Generated
   public <TRAIT> TRAIT method4(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends TRAIT> var2) {
      return (TRAIT)this.field1.method21(var1, var2);
   }

   @Generated
   public <TRAIT> TRAIT method5(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends TRAIT> var2) {
      return (TRAIT)this.field1.method5(var1, var2);
   }

   @Generated
   public <TRAIT> TRAIT method6(TraitType<TRAIT> var1, BiFunction<TraitType<TRAIT>, ? super TRAIT, ? extends TRAIT> var2) {
      return (TRAIT)this.field1.method21(var1, var2);
   }

   @Generated
   public <TRAIT> TRAIT method7(TraitType<? extends TRAIT> var1) {
      return (TRAIT)this.field1.method21(var1);
   }

   @Generated
   public void method8(TraitSnapshot var1) {
      this.field1.method21(var1);
   }

   @Generated
   public void method9(TraitContainer var1) {
      this.field1.method21(var1);
   }

   @Generated
   public void method10(TraitHost var1) {
      this.field1.method21(var1);
   }

   private interface Extension<T> {
      void method1(T var1);

      void method2(T var1, boolean var2);

      void method3(T var1);

      void method4(Object var1);

      void method5(String var1);

      void reset();

      void method6(ClientOption<?> var1);

      void method7(@NotNull ResolvedOptionNode<?> var1);

      @Nullable T method8(@NonNull Object var1);
   }
}
