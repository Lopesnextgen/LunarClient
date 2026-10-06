package com.moonsworth.lunar.client.config.option;

import com.google.common.base.CaseFormat;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation2;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionUpdateListeners;
import com.moonsworth.lunar.client.config.option.OptionDisplay;
import com.moonsworth.lunar.client.config.option.trait.TraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitMutator;
import com.moonsworth.lunar.client.util.Annotation3;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public interface ClientOption<T>
   extends OptionFactory,
   OptionSupplier<ClientOption<T>, T>,
   JsonPersistable,
   TraitHost,
   TraitMutator,
   Calculator2,
   Cloneable {
   @Annotation3
   @Override
   String getId();

   default String method3() {
      return this.getId();
   }

   default String method4() {
      return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, this.getId());
   }

   default String getName() {
      OptionDisplay var1 = (OptionDisplay)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field2);
      if (var1 != null) {
         String var2 = var1.method2();
         if (var2 != null) {
            return var2;
         }
      }

      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.method3(), new Object[0]);
   }

   T getDefaultValue();

   void method3(T var1);

   T getValue();

   default Class<? extends ClientOption> method5() {
      return (Class<? extends ClientOption>)this.getClass();
   }

   default OptionUpdateListeners<T> method6() {
      return (OptionUpdateListeners<T>)this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(OptionTraits.field1, var0 -> OptionUpdateListeners.method6());
   }

   default <S extends ClientOption<T>> S method7(Consumer<T> var1) {
      this.method6().method1(var1);

      try {
         var1.accept(this.getValue());
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      return (S)this;
   }

   default <S extends ClientOption<T>> S method8(Consumer<T> var1) {
      this.method6().method1(var1);
      return (S)this;
   }

   default <S extends ClientOption<T>> S method9(Runnable var1) {
      this.method6().method1(var1x -> var1.run());
      return (S)this;
   }

   default void method10(T var1) {
      this.method11((T)var1, false);
   }

   void method11(T var1, boolean var2);

   void method12(T var1);

   void method13(Object var1);

   @Annotation2(method1 = Annotation2.Type.DYNAMICLISTENER_ISENABLED)
   T get();

   String getValueAsString();

   boolean isHidden();

   Optional<T> method14(JsonElement var1);

   default void method21(String var1) {
      throw new RuntimeException("This option doesn't support parseUpdate()");
   }

   default void method16(JsonObject var1, boolean var2) {
      this.load(var1);
   }

   @Override
   void load(JsonObject var1);

   @Override
   void method1(JsonObject var1);

   default void reset() {
      this.method10(this.getDefaultValue());
   }

   default boolean isDefault() {
      return Objects.equals(this.getValue(), this.getDefaultValue());
   }

   @Nullable
   OptionWidget<?> method18(GuiWidget var1);

   @Override
   String getLanguagePath();

   void method19(ClientOption<?> var1);

   ClientOption<T> method20();

   void method21(@NotNull ResolvedOptionNode<?> var1);

   List<ClientOption<?>> getChildren();

   @Nullable
   default Collection<ClientOption<?>> method22() {
      return null;
   }

   @Contract("->this")
   @Override
   default ClientOption<T> method1() {
      return this;
   }

   @Nullable
   default T method24(@NonNull Object var1) {
      Class var2 = var1.getClass();
      Class var3 = this.getDefaultValue().getClass();
      if (var2 == var3) {
         return (T)var1;
      }

      if (this.getDefaultValue() instanceof Number var4 && var1 instanceof Number var5) {
         if (var4 instanceof Double) {
            return (T)var5.doubleValue();
         }

         if (var4 instanceof Float) {
            return (T)var5.floatValue();
         }

         if (var4 instanceof Integer) {
            return (T)var5.intValue();
         }

         if (var4 instanceof Long) {
            return (T)var5.longValue();
         }

         if (var4 instanceof Short) {
            return (T)var5.shortValue();
         }

         if (var4 instanceof Byte) {
            return (T)var5.byteValue();
         }
      }

      if (var1 instanceof String var7) {
         return this.method14(new JsonPrimitive(var7)).orElse(null);
      } else {
         throw new RuntimeException("Invalid type for option: " + this.getId() + ", expected: " + var3.getSimpleName() + ", got: " + var2.getSimpleName());
      }
   }
}
