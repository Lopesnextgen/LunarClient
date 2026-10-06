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
import com.moonsworth.lunar.client.config.option.TraitBuilder;
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
import com.moonsworth.lunar.client.config.option.ClientOption;

public class ConditionalOption<T> implements ClientOption<T> {
   private final ClientOption<T> field1;
   private final BooleanSupplier field2;

   @Override
   public T getDefaultValue() {
      return this.field1.getDefaultValue();
   }

   @Override
   public void method3(T value1) {
      this.field1.method3((T)value1);
   }

   @Override
   public T getValue() {
      return this.field1.getValue();
   }

   @Override
   public void method10(T value1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method10((T)value1);
      }
   }

   @Override
   public void method11(T value1, boolean flag2) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method11((T)value1, flag2);
      }
   }

   @Override
   public void method12(T value1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method12((T)value1);
      }
   }

   @Override
   public void method13(Object obj1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method13(obj1);
      }
   }

   @Override
   public T get() {
      return this.field1.get();
   }

   @Override
   public Optional<T> method14(JsonElement element1) {
      return Optional.empty();
   }

   @Override
   public void method21(String text1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method21(text1);
      }
   }

   @Override
   public void reset() {
      if (!this.field2.getAsBoolean()) {
         this.field1.reset();
      }
   }

   @Override
   public void method19(ClientOption<?> lightingextension1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method19(lightingextension1);
      }
   }

   @Override
   public ClientOption<T> method20() {
      return this.field1.method20();
   }

   @Override
   public void method21(@NotNull BakedOptionNode<?> lightinghandler1) {
      if (!this.field2.getAsBoolean()) {
         this.field1.method21(lightinghandler1);
      }
   }

   @Override
   public @Nullable T method24(@NonNull Object obj1) {
      return this.field1.method24(obj1);
   }

   @Generated
   public ConditionalOption(ClientOption<T> lightingextension1, BooleanSupplier booleansupplier2) {
      this.field1 = lightingextension1;
      this.field2 = booleansupplier2;
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
   public ClientOption method7(Consumer consumer1) {
      return this.field1.method7(consumer1);
   }

   @Generated
   @Override
   public ClientOption method8(Consumer consumer1) {
      return this.field1.method8(consumer1);
   }

   @Generated
   @Override
   public ClientOption method9(Runnable runnable1) {
      return this.field1.method9(runnable1);
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
   public void method16(JsonObject json1, boolean flag2) {
      this.field1.method16(json1, flag2);
   }

   @Generated
   @Override
   public void load(JsonObject json1) {
      this.field1.load(json1);
   }

   @Generated
   @Override
   public void method1(JsonObject json1) {
      this.field1.method1(json1);
   }

   @Generated
   @Override
   public boolean isDefault() {
      return this.field1.isDefault();
   }

   @Generated
   @Override
   public OptionWidget method18(GuiWidget calculator2handler1) {
      return this.field1.method18(calculator2handler1);
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
   public String method1(String text1, Object... items2) {
      return this.field1.OHROCHICOIOICHOCRROORRCIIICIHO(text1, items2);
   }

   @Generated
   @Override
   public TraitContainer method2() {
      return this.field1.RRIHOHIHHHCHCIRRIORIIRCOOOOIHH();
   }

   @Generated
   @Override
   public <T> Stream<T> method2(Class<T> clazz1) {
      return this.field1.method19(clazz1);
   }

   @Generated
   @Override
   public <T> T method1(TraitType<T> lightoverlay91) {
      return (T)this.field1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(lightoverlay91);
   }

   @Generated
   @Override
   public <T> T method5(TraitType<T> lightoverlay91, T value2) {
      return (T)this.field1.HHRROIIHRRICIIHIIHICRHHRHOHHOO(lightoverlay91, value2);
   }

   @Generated
   @Override
   public boolean method2(TraitType<?> lightoverlay91) {
      return this.field1.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(lightoverlay91);
   }

   @Generated
   @Override
   public <T> Optional<T> method3(TraitType<T> lightoverlay91) {
      return this.field1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(lightoverlay91);
   }

   @Generated
   @Override
   public <T> T method4(TraitType<T> lightoverlay91) {
      return (T)this.field1.RHRHIOOCICIORIOCIHHCIIRCRHHOII(lightoverlay91);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method1(TraitType<TRAIT> lightoverlay91, TRAIT trait2) {
      return (TRAIT)this.field1.method21(lightoverlay91, trait2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method2(TraitType<TRAIT> lightoverlay91, TraitBuilder<TRAIT> threadmoduledump442) {
      return (TRAIT)this.field1.method21(lightoverlay91, threadmoduledump442);
   }

   @Generated
   @Override
   public <TRAIT, B extends TraitBuilder<TRAIT>> TRAIT method3(BuilderTraitType<TRAIT, B> lightoverlay9impl1, Consumer<B> consumer2) {
      return (TRAIT)this.field1.method21(lightoverlay9impl1, consumer2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method4(TraitType<TRAIT> lightoverlay91, Function<TraitType<TRAIT>, ? extends TRAIT> function2) {
      return (TRAIT)this.field1.method21(lightoverlay91, function2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method5(TraitType<TRAIT> lightoverlay91, Function<TraitType<TRAIT>, ? extends TRAIT> function2) {
      return (TRAIT)this.field1.HHRROIIHRRICIIHIIHICRHHRHOHHOO(lightoverlay91, function2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method6(TraitType<TRAIT> lightoverlay91, BiFunction<TraitType<TRAIT>, ? super TRAIT, ? extends TRAIT> function2) {
      return (TRAIT)this.field1.method21(lightoverlay91, function2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method7(TraitType<? extends TRAIT> lightoverlay91) {
      return (TRAIT)this.field1.method21(lightoverlay91);
   }

   @Generated
   @Override
   public void method8(TraitSnapshot lightoverlay41) {
      this.field1.method21(lightoverlay41);
   }

   @Generated
   @Override
   public void method9(TraitContainer lightoverlay2extension21) {
      this.field1.method21(lightoverlay2extension21);
   }

   @Generated
   @Override
   public void method10(TraitHost lightoverlay2extension1) {
      this.field1.method21(lightoverlay2extension1);
   }

   private interface Extension<T> {
      void method1(T value1);

      void method2(T value1, boolean flag2);

      void method3(T value1);

      void method4(Object obj1);

      void method5(String text1);

      void reset();

      void method6(ClientOption<?> lightingextension1);

      void method7(@NotNull BakedOptionNode<?> lightinghandler1);

      @Nullable T method8(@NonNull Object obj1);
   }
}
