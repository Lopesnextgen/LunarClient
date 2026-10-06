package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.config.option.trait.TraitContainer;
import com.moonsworth.lunar.client.config.option.trait.TraitSnapshot;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class CompositeOption<T> implements ClientOption<T> {
   private final ClientOption<T>[] field1;

   @SafeVarargs
   public CompositeOption(ClientOption<T>... var1) {
      this.field1 = var1;
      if (this.field1.length < 1) {
         throw new IllegalStateException("Must have at least one option for OptionCombiner!");
      }
   }

   @Override
   public String getId() {
      return this.field1[0].getId();
   }

   @Override
   public T getDefaultValue() {
      return this.field1[0].getDefaultValue();
   }

   @Override
   public void method3(T var1) {
      for (ClientOption var5 : this.field1) {
         var5.method3(var1);
      }
   }

   @Override
   public T getValue() {
      return this.field1[0].getValue();
   }

   @Override
   public void method10(T var1) {
      for (ClientOption var5 : this.field1) {
         var5.method10(var1);
      }
   }

   @Override
   public void method11(T var1, boolean var2) {
      for (ClientOption var6 : this.field1) {
         var6.method11(var1, var2);
      }
   }

   @Override
   public void method12(T var1) {
      for (ClientOption var5 : this.field1) {
         var5.method12(var1);
      }
   }

   @Override
   public void method13(Object var1) {
      for (ClientOption var5 : this.field1) {
         var5.method13(var1);
      }
   }

   @Override
   public T get() {
      return this.field1[0].get();
   }

   @Override
   public String getValueAsString() {
      return this.field1[0].getValueAsString();
   }

   @Override
   public boolean isHidden() {
      return this.field1[0].isHidden();
   }

   @Override
   public Optional<T> method14(JsonElement var1) {
      return this.field1[0].method14(var1);
   }

   @Override
   public void method21(String var1) {
      for (ClientOption var5 : this.field1) {
         var5.method21(var1);
      }
   }

   @Override
   public void load(JsonObject var1) {
      for (ClientOption var5 : this.field1) {
         var5.load(var1);
      }
   }

   @Override
   public void method1(JsonObject var1) {
      for (ClientOption var5 : this.field1) {
         var5.method1(var1);
      }
   }

   @Override
   public void reset() {
      for (ClientOption var4 : this.field1) {
         var4.reset();
      }
   }

   @Override
   public @Nullable OptionWidget<?> method18(GuiWidget var1) {
      return this.field1[0].method18(var1);
   }

   @Override
   public String getLanguagePath() {
      return this.field1[0].getLanguagePath();
   }

   @Override
   public void method19(ClientOption<?> var1) {
      for (ClientOption var5 : this.field1) {
         var5.method19(var1);
      }
   }

   @Override
   public ClientOption<T> method20() {
      ClientOption[] var1 = new ClientOption[this.field1.length];

      for (int var2 = 0; var2 < this.field1.length; var2++) {
         var1[var2] = this.field1[var2].method20();
      }

      return new CompositeOption<>(var1);
   }

   @Override
   public void method21(@NotNull ResolvedOptionNode<?> var1) {
      for (ClientOption var5 : this.field1) {
         var5.method21(var1);
      }
   }

   @Override
   public List<ClientOption<?>> getChildren() {
      ArrayList var1 = new ArrayList();

      for (ClientOption var5 : this.field1) {
         var1.addAll(var5.getChildren());
      }

      return var1;
   }

   public TraitContainer method2() {
      return this.field1[0].RRIHOHIHHHCHCIRRIORIIRCOOOOIHH();
   }

   public <TRAIT> @Nullable TRAIT method1(TraitType<TRAIT> var1, @Nullable TRAIT var2) {
      return (TRAIT)this.field1[0].method21(var1, var2);
   }

   public <TRAIT> @Nullable TRAIT method4(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends TRAIT> var2) {
      return (TRAIT)this.field1[0].method21(var1, var2);
   }

   public <TRAIT> TRAIT method5(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends TRAIT> var2) {
      return (TRAIT)this.field1[0].HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var2);
   }

   public <TRAIT> @Nullable TRAIT method6(TraitType<TRAIT> var1, BiFunction<TraitType<TRAIT>, ? super TRAIT, ? extends TRAIT> var2) {
      return (TRAIT)this.field1[0].method21(var1, var2);
   }

   public <TRAIT> @Nullable TRAIT method7(TraitType<? extends TRAIT> var1) {
      return (TRAIT)this.field1[0].method21(var1);
   }

   public void method8(TraitSnapshot var1) {
      this.field1[0].method21(var1);
   }

   public void method9(TraitContainer var1) {
      this.field1[0].method21(var1);
   }

   @Generated
   public ClientOption<T>[] method23() {
      return this.field1;
   }
}
