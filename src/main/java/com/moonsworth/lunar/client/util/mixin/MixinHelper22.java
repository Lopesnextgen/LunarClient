package com.moonsworth.lunar.client.util.mixin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class MixinHelper22<T, L extends List<T>> implements MixinHelper2<T> {
   L field1;

   @Override
   public <B> B method3(Collection<T> var1) {
      this.field1.removeAll(var1);
      return (B)this;
   }

   @Override
   public <B> B method2(Collection<T> var1) {
      this.field1.addAll(var1);
      return (B)this;
   }

   @Contract("_->this")
   public <B> B method3(Predicate<? super T> var1) {
      this.field1.removeIf(var1);
      return (B)this;
   }

   public L build() {
      return this.field1;
   }

   public List<T> method4() {
      return Collections.unmodifiableList(this.field1);
   }

   public static <T> MixinHelper22<T, List<T>> method5() {
      return new MixinHelper22<>(new ArrayList<>());
   }

   public static <T, L extends List<T>> MixinHelper22<T, List<T>> method6(L var0) {
      return new MixinHelper22<>(var0);
   }

   public static <T, L extends List<T>> MixinHelper22<T, List<T>> method7(L var0, Class<T> var1) {
      return new MixinHelper22<>(var0);
   }

   public static <T> MixinHelper22.Data2<T, List<T>> method8() {
      return new MixinHelper22.Data2<>(ArrayList::new);
   }

   public static <T, L extends List<T>> MixinHelper22.Data2<T, L> method9(Supplier<L> var0) {
      return new MixinHelper22.Data2<>(var0);
   }

   public static <T, L extends List<T>> MixinHelper22.Data2<T, L> method10(Supplier<L> var0, Class<T> var1) {
      return new MixinHelper22.Data2<>(var0);
   }

   @Generated
   protected MixinHelper22(L var1) {
      this.field1 = (L)var1;
   }

   public static class Data2<T, L extends List<T>> extends MixinHelper22<T, L> {
      private final Supplier<L> field2;

      protected Data2(Supplier<L> var1) {
         super(null);
         this.field2 = var1;
      }

      @Override
      public <B> B method3(Collection<T> var1) {
         if (this.field1 != null) {
            this.field1.removeAll(var1);
         }

         return (B)this;
      }

      @Override
      public <B> B method2(Collection<T> var1) {
         if (this.field1 == null) {
            this.field1 = this.field2.get();
         }

         this.field1.addAll(var1);
         return (B)this;
      }

      @Override
      public <B> B method3(Predicate<? super T> var1) {
         if (this.field1 != null) {
            this.field1.removeIf(var1);
         }

         return (B)this;
      }

      @Nullable
      @Override
      public L build() {
         return (L)this.field1;
      }

      @Nullable
      @Override
      public List<T> method4() {
         return this.field1 == null ? null : super.method4();
      }
   }
}
