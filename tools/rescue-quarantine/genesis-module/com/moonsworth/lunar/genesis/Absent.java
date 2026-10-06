package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

@GwtCompatible
final class Absent<T> extends Optional<T> {
   static final Absent<Object> field2 = new Absent<>();
   private static final long field3 = 0L;

   static <T> Optional<T> method2() {
      return (Optional<T>)field2;
   }

   private Absent() {
   }

   public boolean isPresent() {
      return false;
   }

   public T get() {
      throw new IllegalStateException("Optional.get() cannot be called on an absent value");
   }

   public T or(T value1) {
      return (T)Preconditions.checkNotNull(value1, "use Optional.orNull() instead of Optional.or(null)");
   }

   public Optional<T> method6(Optional<? extends T> serializablebase_21) {
      return (Optional<T>)Preconditions.checkNotNull(serializablebase_21);
   }

   public T method7(Supplier<? extends T> supplierextension1) {
      return (T)Preconditions.checkNotNull(supplierextension1.get(), "use Optional.orNull() instead of a Supplier that returns null");
   }

   public @Nullable T orNull() {
      return null;
   }

   public Set<T> asSet() {
      return Collections.emptySet();
   }

   public <V> Optional<V> method8(Function<? super T, V> mixinhelper24_21) {
      Preconditions.checkNotNull(mixinhelper24_21);
      return Optional.method1();
   }

   public boolean equals(@Nullable Object obj1) {
      return obj1 == this;
   }

   public int hashCode() {
      return 2040732332;
   }

   public String toString() {
      return "Optional.absent()";
   }

   private Object readResolve() {
      return field2;
   }
}
