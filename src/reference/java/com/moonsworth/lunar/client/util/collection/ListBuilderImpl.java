package com.moonsworth.lunar.client.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class ListBuilderImpl<T, L extends List<T>> implements ListBuilder<T> {
   L field1;

   @Override
   public <B> B method3(Collection<T> list1) {
      this.field1.removeAll(list1);
      return (B)this;
   }

   @Override
   public <B> B method2(Collection<T> list1) {
      this.field1.addAll(list1);
      return (B)this;
   }

   @Contract("_->this")
   public <B> B method3(Predicate<? super T> predicate1) {
      this.field1.removeIf(predicate1);
      return (B)this;
   }

   public L build() {
      return this.field1;
   }

   public List<T> method4() {
      return Collections.unmodifiableList(this.field1);
   }

   public static <T> ListBuilderImpl<T, List<T>> method5() {
      return new ListBuilderImpl<>(new ArrayList<>());
   }

   public static <T, L extends List<T>> ListBuilderImpl<T, List<T>> method6(L l0) {
      return new ListBuilderImpl<>(l0);
   }

   public static <T, L extends List<T>> ListBuilderImpl<T, List<T>> method7(L l0, Class<T> clazz1) {
      return new ListBuilderImpl<>(l0);
   }

   public static <T> ListBuilderImpl.LazyListBuilder<T, List<T>> method8() {
      return new ListBuilderImpl.LazyListBuilder<>(ArrayList::new);
   }

   public static <T, L extends List<T>> ListBuilderImpl.LazyListBuilder<T, L> method9(Supplier<L> supplier0) {
      return new ListBuilderImpl.LazyListBuilder<>(supplier0);
   }

   public static <T, L extends List<T>> ListBuilderImpl.LazyListBuilder<T, L> method10(Supplier<L> supplier0, Class<T> clazz1) {
      return new ListBuilderImpl.LazyListBuilder<>(supplier0);
   }

   @Generated
   protected ListBuilderImpl(L l1) {
      this.field1 = (L)l1;
   }

   public static class LazyListBuilder<T, L extends List<T>> extends ListBuilderImpl<T, L> {
      private final Supplier<L> field2;

      protected LazyListBuilder(Supplier<L> supplier1) {
         super(null);
         this.field2 = supplier1;
      }

      @Override
      public <B> B method3(Collection<T> list1) {
         if (this.CRHIRRCIROIIORRHICHHORCCROOHHI != null) {
            this.CRHIRRCIROIIORRHICHHORCCROOHHI.removeAll(list1);
         }

         return (B)this;
      }

      @Override
      public <B> B method2(Collection<T> list1) {
         if (this.CRHIRRCIROIIORRHICHHORCCROOHHI == null) {
            this.CRHIRRCIROIIORRHICHHORCCROOHHI = this.field2.get();
         }

         this.CRHIRRCIROIIORRHICHHORCCROOHHI.addAll(list1);
         return (B)this;
      }

      @Override
      public <B> B method3(Predicate<? super T> predicate1) {
         if (this.CRHIRRCIROIIORRHICHHORCCROOHHI != null) {
            this.CRHIRRCIROIIORRHICHHORCCROOHHI.removeIf(predicate1);
         }

         return (B)this;
      }

      @Nullable
      @Override
      public L build() {
         return (L)this.CRHIRRCIROIIORRHICHHORCCROOHHI;
      }

      @Nullable
      @Override
      public List<T> method4() {
         return this.CRHIRRCIROIIORRHICHHORCCROOHHI == null ? null : super.method4();
      }
   }
}
