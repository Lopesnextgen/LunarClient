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
import com.moonsworth.lunar.client.config.option.ClientOption;

public class OptionCombiner<T> implements ClientOption<T> {
   private final ClientOption<T>[] field1;

   @SafeVarargs
   public OptionCombiner(ClientOption<T>... items1) {
      this.field1 = items1;
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
   public void method3(T value1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.method3(value1);
      }
   }

   @Override
   public T getValue() {
      return this.field1[0].getValue();
   }

   @Override
   public void method10(T value1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.method10(value1);
      }
   }

   @Override
   public void method11(T value1, boolean flag2) {
      for (ClientOption lightingextension6 : this.field1) {
         lightingextension6.method11(value1, flag2);
      }
   }

   @Override
   public void method12(T value1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.method12(value1);
      }
   }

   @Override
   public void method13(Object obj1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.method13(obj1);
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
   public Optional<T> method14(JsonElement element1) {
      return this.field1[0].method14(element1);
   }

   @Override
   public void method21(String text1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.method21(text1);
      }
   }

   @Override
   public void load(JsonObject json1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.load(json1);
      }
   }

   @Override
   public void method1(JsonObject json1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.method1(json1);
      }
   }

   @Override
   public void reset() {
      for (ClientOption lightingextension4 : this.field1) {
         lightingextension4.reset();
      }
   }

   @Override
   public @Nullable OptionWidget<?> method18(GuiWidget calculator2handler1) {
      return this.field1[0].method18(calculator2handler1);
   }

   @Override
   public String getLanguagePath() {
      return this.field1[0].getLanguagePath();
   }

   @Override
   public void method19(ClientOption<?> lightingextension1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.method19(lightingextension1);
      }
   }

   @Override
   public ClientOption<T> method20() {
      ClientOption[] items1 = new ClientOption[this.field1.length];

      for (int index2 = 0; index2 < this.field1.length; index2++) {
         items1[index2] = this.field1[index2].method20();
      }

      return new OptionCombiner<>(items1);
   }

   @Override
   public void method21(@NotNull BakedOptionNode<?> lightinghandler1) {
      for (ClientOption lightingextension5 : this.field1) {
         lightingextension5.method21(lightinghandler1);
      }
   }

   @Override
   public List<ClientOption<?>> getChildren() {
      ArrayList list1 = new ArrayList();

      for (ClientOption lightingextension5 : this.field1) {
         list1.addAll(lightingextension5.getChildren());
      }

      return list1;
   }

   @Override
   public TraitContainer method2() {
      return this.field1[0].RRIHOHIHHHCHCIRRIORIIRCOOOOIHH();
   }

   @Override
   public <TRAIT> @Nullable TRAIT method1(TraitType<TRAIT> lightoverlay91, @Nullable TRAIT trait2) {
      return (TRAIT)this.field1[0].method21(lightoverlay91, trait2);
   }

   @Override
   public <TRAIT> @Nullable TRAIT method4(TraitType<TRAIT> lightoverlay91, Function<TraitType<TRAIT>, ? extends TRAIT> function2) {
      return (TRAIT)this.field1[0].method21(lightoverlay91, function2);
   }

   @Override
   public <TRAIT> TRAIT method5(TraitType<TRAIT> lightoverlay91, Function<TraitType<TRAIT>, ? extends TRAIT> function2) {
      return (TRAIT)this.field1[0].HHRROIIHRRICIIHIIHICRHHRHOHHOO(lightoverlay91, function2);
   }

   @Override
   public <TRAIT> @Nullable TRAIT method6(TraitType<TRAIT> lightoverlay91, BiFunction<TraitType<TRAIT>, ? super TRAIT, ? extends TRAIT> function2) {
      return (TRAIT)this.field1[0].method21(lightoverlay91, function2);
   }

   @Override
   public <TRAIT> @Nullable TRAIT method7(TraitType<? extends TRAIT> lightoverlay91) {
      return (TRAIT)this.field1[0].method21(lightoverlay91);
   }

   @Override
   public void method8(TraitSnapshot lightoverlay41) {
      this.field1[0].method21(lightoverlay41);
   }

   @Override
   public void method9(TraitContainer lightoverlay2extension21) {
      this.field1[0].method21(lightoverlay2extension21);
   }

   @Generated
   public ClientOption<T>[] method23() {
      return this.field1;
   }
}
