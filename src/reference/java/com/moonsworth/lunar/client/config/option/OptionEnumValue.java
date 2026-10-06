package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.datafixers.util.Pair;
import com.lunarclient.dfu.serialization.Codec;
import com.lunarclient.dfu.serialization.DataResult;
import com.lunarclient.dfu.serialization.DynamicOps;
import com.lunarclient.dfu.serialization.Keyable;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump65;
import com.moonsworth.lunar.client.util.nameplate.Nameplate;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

public interface OptionEnumValue extends JsonProviderLegacy, Calculator2 {
   @Override
   String toString();

   String id();

   default PhosphorIconLegacy icon() {
      return null;
   }

   default String description() {
      return "";
   }

   default String getLanguagePath() {
      return "settings";
   }

   default JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.id());
      var1.addProperty("name", this.toString());
      var1.addProperty("description", this.description());
      if (this.icon() != null) {
         var1.addProperty("icon", this.icon().ordinal());
      }

      return var1;
   }

   static <E extends Enum<E> & OptionEnumValue> OptionEnumValue.EnumOptionCodec<E> method1(Supplier<E[]> var0) {
      return method2(var0, var0x -> var0x);
   }

   static <E extends Enum<E> & OptionEnumValue> OptionEnumValue.EnumOptionCodec<E> method2(Supplier<E[]> var0, Function<String, String> var1) {
      Enum[] var2 = (Enum[])var0.get();
      return new OptionEnumValue.EnumOptionCodec<>((E[])var2, method4((E[])var2, var1));
   }

   static <T extends OptionEnumValue> Codec<T> method3(Supplier<T[]> var0) {
      OptionEnumValue[] var1 = (OptionEnumValue[])var0.get();
      return new OptionEnumValue.Data<>((T[])var1, method4((T[])var1, var0x -> var0x), ThreadModuleDump65.method11(var1));
   }

   static <T extends OptionEnumValue> Function<String, T> method4(T[] var0, Function<String, String> var1) {
      if (var0.length > 16) {
         Map var2 = Arrays.stream(var0).collect(Collectors.toMap(var1x -> (String)var1.apply(var1x.id()), var0x -> (OptionEnumValue)var0x));
         return var1x -> (T)(var1x == null ? null : var2.get(var1x));
      } else {
         return var2x -> {
            for (OptionEnumValue var6 : var0) {
               if (((String)var1.apply(var6.id())).equals(var2x)) {
                  return (T)var6;
               }
            }

            return null;
         };
      }
   }

   static Keyable method5(final OptionEnumValue[] var0) {
      return new Keyable() {
         public <T> Stream<T> keys(DynamicOps<T> var1) {
            return Arrays.stream(var0).map(OptionEnumValue::id).map(var1::createString);
         }
      };
   }

   class Data<E extends OptionEnumValue> implements Codec<E> {
      private final Codec<E> field1;

      public Data(E[] var1, Function<String, E> var2, ToIntFunction<E> var3) {
         this.field1 = Nameplate.method7(
            Codec.stringResolver(OptionEnumValue::id, var2), Nameplate.method6(var3, var1x -> var1x >= 0 && var1x < var1.length ? var1[var1x] : null, -1)
         );
      }

      public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> var1, T var2) {
         return this.field1.decode(var1, var2);
      }

      public <T> DataResult<T> method1(E var1, DynamicOps<T> var2, T var3) {
         return this.field1.encode(var1, var2, var3);
      }
   }

   class EnumOptionCodec<E extends Enum<E> & OptionEnumValue> extends OptionEnumValue.Data<E> {
      private final Function<String, E> field2;

      public EnumOptionCodec(E[] var1, Function<String, E> var2) {
         super((E[])var1, var2, var0 -> var0.ordinal());
         this.field2 = var2;
      }

      @Nullable
      public E method1(@Nullable String var1) {
         return this.field2.apply(var1);
      }

      public E method2(@Nullable String var1, E var2) {
         return Objects.requireNonNullElse(this.method1(var1), (E)var2);
      }
   }
}
