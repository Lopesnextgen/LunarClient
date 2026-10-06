package com.moonsworth.lunar.genesis;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
public final class MixinHelper21 {
   private static final Collector<Object, ?, Optional<Object>> field1 = Collector.of(
      MixinHelper21.Data::new, MixinHelper21.Data::add, MixinHelper21.Data::method1, MixinHelper21.Data::getOptional, Characteristics.UNORDERED
   );
   private static final Object field2 = new Object();
   private static final Collector<Object, ?, Object> field3 = Collector.of(
      MixinHelper21.Data::new, (var0, var1) -> var0.add(var1 == null ? field2 : var1), MixinHelper21.Data::method1, var0 -> {
         Object var1 = var0.getElement();
         return var1 == field2 ? null : var1;
      }, Characteristics.UNORDERED
   );

   public static <T> Collector<T, ?, Optional<T>> toOptional() {
      return (Collector<T, ?, Optional<T>>)field1;
   }

   public static <T> Collector<T, ?, T> onlyElement() {
      return (Collector<T, ?, T>)field3;
   }

   private MixinHelper21() {
   }

   private static final class Data {
      static final int field1 = 4;
      @Nullable Object element = null;
      @Nullable List<Object> extras = null;

      Data() {
      }

      IllegalArgumentException multiples(boolean var1) {
         StringBuilder var2 = new StringBuilder().append("expected one element but was: <").append(this.element);

         for (Object var4 : this.extras) {
            var2.append(", ").append(var4);
         }

         if (var1) {
            var2.append(", ...");
         }

         var2.append('>');
         throw new IllegalArgumentException(var2.toString());
      }

      void add(Object var1) {
         Preconditions.checkNotNull(var1);
         if (this.element == null) {
            this.element = var1;
         } else if (this.extras == null) {
            this.extras = new ArrayList<>(4);
            this.extras.add(var1);
         } else {
            if (this.extras.size() >= 4) {
               throw this.multiples(true);
            }

            this.extras.add(var1);
         }
      }

      MixinHelper21.Data method1(MixinHelper21.Data var1) {
         if (this.element == null) {
            return var1;
         }

         if (var1.element == null) {
            return this;
         }

         if (this.extras == null) {
            this.extras = new ArrayList<>();
         }

         this.extras.add(var1.element);
         if (var1.extras != null) {
            this.extras.addAll(var1.extras);
         }

         if (this.extras.size() > 4) {
            this.extras.subList(4, this.extras.size()).clear();
            throw this.multiples(true);
         } else {
            return this;
         }
      }

      Optional<Object> getOptional() {
         if (this.extras == null) {
            return Optional.ofNullable(this.element);
         } else {
            throw this.multiples(false);
         }
      }

      Object getElement() {
         if (this.element == null) {
            throw new NoSuchElementException();
         } else if (this.extras == null) {
            return this.element;
         } else {
            throw this.multiples(false);
         }
      }
   }
}
